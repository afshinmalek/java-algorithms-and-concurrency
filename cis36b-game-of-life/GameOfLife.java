import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLife extends JFrame {
  private int gridSize;
  private JPanel[][] gridPanels;
  private int[][] currentGrid;
  private int[][] nextGrid;
  private volatile boolean isRunning = false; // Flag to control the thread
  private Thread gameThread; // Thread for updating generations

  public GameOfLife() {
    // Prompt for grid size
    String input = JOptionPane.showInputDialog(this, "Enter grid size (N):", "Game of Life",
        JOptionPane.QUESTION_MESSAGE);
    try {
      gridSize = Integer.parseInt(input);
      if (gridSize <= 0)
        throw new NumberFormatException();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Invalid input. Using default size 20.", "Error", JOptionPane.ERROR_MESSAGE);
      gridSize = 20;
    }

    // Initialize arrays
    gridPanels = new JPanel[gridSize][gridSize];
    currentGrid = new int[gridSize][gridSize];
    nextGrid = new int[gridSize][gridSize];

    // Set up JFrame
    setTitle("Conway's Game of Life");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Create grid panel
    JPanel gridPanel = new JPanel(new GridLayout(gridSize, gridSize));
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        gridPanels[i][j] = new JPanel();
        gridPanels[i][j].setBackground(Color.WHITE);
        gridPanels[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
        int row = i, col = j;
        gridPanels[i][j].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            toggleCell(row, col);
          }
        });
        gridPanel.add(gridPanels[i][j]);
      }
    }
    add(gridPanel, BorderLayout.CENTER);

    // Create button panel
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");
    startButton.addActionListener(e -> startGame());
    stopButton.addActionListener(e -> stopGame());
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(null);
  }

  // Toggle cell state on click
  private void toggleCell(int row, int col) {
    if (currentGrid[row][col] == 0) {
      currentGrid[row][col] = 1;
      gridPanels[row][col].setBackground(Color.BLACK);
    } else {
      currentGrid[row][col] = 0;
      gridPanels[row][col].setBackground(Color.WHITE);
    }
  }

  // Start the game
  private void startGame() {
    if (!isRunning) {
      isRunning = true;
      gameThread = new Thread(() -> {
        while (isRunning) {
          computeNextGeneration();
          SwingUtilities.invokeLater(this::updateGrid); // Update GUI on EDT
          try {
            Thread.sleep(500); // Pause for 500ms
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
          }
        }
      });
      gameThread.start();
    }
  }

  // Stop the game
  private void stopGame() {
    isRunning = false;
    if (gameThread != null) {
      gameThread.interrupt(); // Interrupt the thread if it's sleeping
    }
  }

  // Compute the next generation
  private void computeNextGeneration() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        int neighbors = countNeighbors(i, j);
        if (currentGrid[i][j] == 1) {
          // Live cell rules
          if (neighbors < 2 || neighbors > 3) {
            nextGrid[i][j] = 0; // Dies
          } else {
            nextGrid[i][j] = 1; // Survives
          }
        } else {
          // Dead cell rule
          if (neighbors == 3) {
            nextGrid[i][j] = 1; // Born
          } else {
            nextGrid[i][j] = 0; // Stays dead
          }
        }
      }
    }
  }

  // Count live neighbors
  private int countNeighbors(int row, int col) {
    int count = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0)
          continue;
        int newRow = row + i;
        int newCol = col + j;
        if (newRow >= 0 && newRow < gridSize && newCol >= 0 && newCol < gridSize) {
          count += currentGrid[newRow][newCol];
        }
      }
    }
    return count;
  }

  // Update the grid display
  private void updateGrid() {
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        currentGrid[i][j] = nextGrid[i][j];
        gridPanels[i][j].setBackground(currentGrid[i][j] == 1 ? Color.BLACK : Color.WHITE);
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameOfLife game = new GameOfLife();
      game.setVisible(true);
    });
  }
}