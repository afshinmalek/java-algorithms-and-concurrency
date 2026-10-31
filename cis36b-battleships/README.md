# Battleships — Networked GUI Game

Swing Battleships on 25x25 player and tracking grids, with socket networking for server/client play.

## Run

```bash
javac Battleships.java
java Battleships server localhost 5000
# In a second terminal: java Battleships client localhost 5000
```

Compile each project folder on its own. Some projects define classes with the same names (for example, `Edge` and `G50` appear in both the Shortest Path and MST projects), so do not compile the whole repo together.
