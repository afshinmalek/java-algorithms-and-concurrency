import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Battleships extends JFrame {
  private static final int GRID_SIZE = 25;
  private PlayerGrid playerGrid;
  private TrackingGrid trackingGrid;
  private NetworkHandler networkHandler;
  private boolean isMyTurn;
  private String lastShot;

  // GUI components
  private JPanel playerPanel;
  private JPanel trackingPanel;
  private JTextArea messageArea;
  private JTextField inputField;

  public Battleships(String mode, String host, int port) throws IOException {
    // Initialize grids
    playerGrid = new PlayerGrid();
    trackingGrid = new TrackingGrid();

    // Place ships randomly
    placeShipsRandomly();

    // Set up networking
    boolean isServer = mode.equals("server");
    networkHandler = new NetworkHandler(host, port, isServer, this::onMessageReceived);
    isMyTurn = isServer; // Server starts

    // Set up GUI
    setTitle("Battleships");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Player's grid (non-clickable)
    playerPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
    playerPanel.setMinimumSize(new Dimension(300, 500)); // Prevent shrinking too small
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20)); // Ensure buttons are visible
        button.setEnabled(false);
        playerPanel.add(button);
      }
    }

    // Opponent's grid (clickable)
    trackingPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
    trackingPanel.setMinimumSize(new Dimension(300, 500)); // Prevent shrinking too small
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20)); // Ensure buttons are visible
        int row = i;
        int col = j;
        button.addActionListener(e -> {
          if (isMyTurn) {
            String coord = toCoord(row, col);
            lastShot = coord;
            networkHandler.sendMessage(coord);
            isMyTurn = false;
          }
        });
        trackingPanel.add(button);
      }
    }

    // Communication box
    messageArea = new JTextArea(5, 30);
    messageArea.setEditable(false);
    inputField = new JTextField(30);
    inputField.addActionListener(e -> {
      String message = inputField.getText();
      networkHandler.sendMessage(message);
      messageArea.append("You: " + message + "\n");
      messageArea.repaint();
      inputField.setText("");
    });
    JPanel commPanel = new JPanel(new BorderLayout());
    commPanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);
    commPanel.add(inputField, BorderLayout.SOUTH);

    // Arrange components with adjustable JSplitPane
    JSplitPane gridsPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, playerPanel, trackingPanel);
    gridsPane.setOneTouchExpandable(true); // Add arrows for quick expansion
    gridsPane.setDividerLocation(0.5); // Start with even split
    gridsPane.setResizeWeight(0.5); // Maintain proportional resizing
    add(gridsPane, BorderLayout.CENTER);
    add(commPanel, BorderLayout.SOUTH);

    setMinimumSize(new Dimension(800, 600)); // Ensure window has enough space
    pack();
    setVisible(true);

    // Initial grid update
    updateGrids();
  }

  private void placeShipsRandomly() {
    List<Ship> ships = new ArrayList<>();
    ships.add(new Ship("aircraft carrier", 5));
    for (int i = 0; i < 2; i++)
      ships.add(new Ship("battleship", 4));
    for (int i = 0; i < 2; i++)
      ships.add(new Ship("submarine", 3));
    for (int i = 0; i < 2; i++)
      ships.add(new Ship("destroyer", 3));
    for (int i = 0; i < 4; i++)
      ships.add(new Ship("patrol boat", 2));

    Random random = new Random();
    for (Ship ship : ships) {
      boolean placed = false;
      while (!placed) {
        int row = random.nextInt(GRID_SIZE);
        int col = random.nextInt(GRID_SIZE);
        boolean horizontal = random.nextBoolean();
        if (playerGrid.canPlaceShip(ship, row, col, horizontal)) {
          playerGrid.placeShip(ship, row, col, horizontal);
          placed = true;
        }
      }
    }
  }

  private String toCoord(int row, int col) {
    return "" + (char) ('A' + col) + (row + 1);
  }

  private int[] fromCoord(String coord) {
    try {
      if (coord.length() < 2)
        return null;
      char colChar = coord.charAt(0);
      int col = colChar - 'A';
      int row = Integer.parseInt(coord.substring(1)) - 1;
      if (col < 0 || col >= GRID_SIZE || row < 0 || row >= GRID_SIZE)
        return null;
      return new int[] { row, col };
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private void onMessageReceived(String message) {
    SwingUtilities.invokeLater(() -> {
      if (message == null) {
        messageArea.append("Error: Received null message\n");
        messageArea.repaint();
        return;
      }
      // Handle game responses (hit, miss, sunk, win)
      if (message.startsWith("hit") || message.equals("miss")) {
        // Response to my shot
        int[] coord = fromCoord(lastShot);
        if (coord != null) {
          trackingGrid.recordShot(coord[0], coord[1], message);
          messageArea.append("Your shot " + lastShot + ": " + message + "\n");
          if (message.contains("sunk")) {
            String[] parts = message.split(" ");
            messageArea.append("You sunk opponent's " + parts[2] + "\n");
          }
          if (message.contains("you win")) {
            messageArea.append("You win!\n");
          }
          updateGrids();
          messageArea.repaint();
        }
      } else {
        // Try to parse as opponent's shot
        int[] coord = fromCoord(message);
        if (coord != null) {
          // Opponent's shot
          messageArea.append("Opponent guesses: " + message + "\n");
          String response = playerGrid.processShot(coord[0], coord[1]);
          messageArea.append("Response: " + response + "\n");
          networkHandler.sendMessage(response);
          if (response.contains("sunk")) {
            String[] parts = response.split(" ");
            messageArea.append("Opponent sunk your " + parts[2] + "\n");
          }
          if (response.contains("you win")) {
            messageArea.append("You lose!\n");
          } else {
            isMyTurn = true;
          }
          updateGrids();
          messageArea.repaint();
        } else {
          // Treat as chat message
          messageArea.append("Opponent: " + message + "\n");
          messageArea.repaint();
        }
      }
    });
  }

  private void updateGrids() {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        // Player grid
        JButton playerButton = (JButton) playerPanel.getComponent(i * GRID_SIZE + j);
        String playerDisplay = playerGrid.getCellDisplay(i, j);
        playerButton.setText(playerDisplay);
        playerButton.setBackground(
            playerDisplay.equals("X") ? Color.RED
                : playerDisplay.equals("S") ? Color.GRAY : playerDisplay.equals("O") ? Color.WHITE : Color.BLUE);

        // Tracking grid
        JButton trackingButton = (JButton) trackingPanel.getComponent(i * GRID_SIZE + j);
        String trackingDisplay = trackingGrid.getCellDisplay(i, j);
        trackingButton.setText(trackingDisplay);
        trackingButton.setBackground(
            trackingDisplay.equals("X") ? Color.RED : trackingDisplay.equals("O") ? Color.WHITE : Color.BLUE);
      }
    }
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java Battleships server|client host port");
      return;
    }
    String mode = args[0];
    String host = args[1];
    int port = Integer.parseInt(args[2]);
    try {
      new Battleships(mode, host, port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class Ship {
  private String type;
  private int size;
  private List<int[]> positions = new ArrayList<>();
  private boolean[] hits;

  public Ship(String type, int size) {
    this.type = type;
    this.size = size;
    hits = new boolean[size];
  }

  public void setPositions(int startRow, int startCol, boolean horizontal) {
    positions.clear();
    for (int i = 0; i < size; i++) {
      positions.add(horizontal ? new int[] { startRow, startCol + i } : new int[] { startRow + i, startCol });
    }
  }

  public void hit(int row, int col) {
    for (int i = 0; i < size; i++) {
      int[] pos = positions.get(i);
      if (pos[0] == row && pos[1] == col) {
        hits[i] = true;
        break;
      }
    }
  }

  public boolean isSunk() {
    for (boolean hit : hits) {
      if (!hit)
        return false;
    }
    return true;
  }

  public String getType() {
    return type;
  }

  public List<int[]> getPositions() {
    return positions;
  }

  public int getSize() {
    return size;
  }
}

class PlayerGrid {
  private static final int SIZE = 25;
  private Ship[][] shipGrid = new Ship[SIZE][SIZE];
  private boolean[][] hitGrid = new boolean[SIZE][SIZE];
  private List<Ship> ships = new ArrayList<>();

  public boolean canPlaceShip(Ship ship, int row, int col, boolean horizontal) {
    int size = ship.getSize();
    if (horizontal) {
      if (col + size > SIZE)
        return false;
      for (int i = 0; i < size; i++) {
        if (shipGrid[row][col + i] != null)
          return false;
      }
    } else {
      if (row + size > SIZE)
        return false;
      for (int i = 0; i < size; i++) {
        if (shipGrid[row + i][col] != null)
          return false;
      }
    }
    return true;
  }

  public void placeShip(Ship ship, int row, int col, boolean horizontal) {
    ship.setPositions(row, col, horizontal);
    for (int[] pos : ship.getPositions()) {
      shipGrid[pos[0]][pos[1]] = ship;
    }
    ships.add(ship);
  }

  public String processShot(int row, int col) {
    if (hitGrid[row][col])
      return "already hit";
    hitGrid[row][col] = true;
    if (shipGrid[row][col] == null)
      return "miss";
    Ship ship = shipGrid[row][col];
    ship.hit(row, col);
    if (ship.isSunk()) {
      return isAllSunk() ? "hit sunk " + ship.getType() + " you win" : "hit sunk " + ship.getType();
    }
    return "hit";
  }

  private boolean isAllSunk() {
    for (Ship ship : ships) {
      if (!ship.isSunk())
        return false;
    }
    return true;
  }

  public String getCellDisplay(int row, int col) {
    if (shipGrid[row][col] != null) {
      return hitGrid[row][col] ? "X" : "S";
    }
    return hitGrid[row][col] ? "O" : ".";
  }
}

class TrackingGrid {
  private static final int SIZE = 25;
  private int[][] shotGrid = new int[SIZE][SIZE]; // 0: not shot, 1: miss, 2: hit

  public void recordShot(int row, int col, String result) {
    shotGrid[row][col] = result.equals("miss") ? 1 : 2;
  }

  public String getCellDisplay(int row, int col) {
    return shotGrid[row][col] == 0 ? "." : shotGrid[row][col] == 1 ? "O" : "X";
  }
}

class NetworkHandler {
  private Socket socket;
  private PrintWriter writer;
  private BufferedReader reader;
  private MessageListener listener;

  public NetworkHandler(String host, int port, boolean isServer, MessageListener listener) throws IOException {
    this.listener = listener;
    if (isServer) {
      ServerSocket serverSocket = new ServerSocket(port);
      socket = serverSocket.accept();
      serverSocket.close();
    } else {
      socket = new Socket(host, port);
    }
    writer = new PrintWriter(socket.getOutputStream(), true);
    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    new Thread(() -> {
      try {
        String message;
        while ((message = reader.readLine()) != null) {
          listener.onMessageReceived(message);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  public void sendMessage(String message) {
    writer.println(message);
  }

  @FunctionalInterface
  public interface MessageListener {
    void onMessageReceived(String message);
  }
}
