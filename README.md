# Java Algorithms and Concurrency

Java projects from Peralta Community College District coursework: CIS 27 Data Structures & Algorithms (Spring 2025) and CIS 36B Java Programming II (Spring 2025). Selected and cleaned for portfolio presentation; original Canvas submission filenames with `-1` suffixes were renamed to match their class names.

## Projects

### CIS 27 — Data Structures & Algorithms
- [cis27-redblack-bst](cis27-redblack-bst/) — Generic red-black BST with insert, delete, and traversal
- [cis27-shortest-path](cis27-shortest-path/) — Dijkstra's algorithm, field-station assignment on a 50-node graph
- [cis27-mst-cable-network](cis27-mst-cable-network/) — Lazy Prim's MST for a minimum cable network
- [cis27-spelling-checker](cis27-spelling-checker/) — Dictionary spelling checker with correction suggestions
- [cis27-maze-union-find](cis27-maze-union-find/) — Union-find maze generation
- [cis27-natural-merge-sort](cis27-natural-merge-sort/) — Natural merge sort on a linked list

### CIS 36B — Java Programming II
- [cis36b-game-of-life](cis36b-game-of-life/) — Conway's Game of Life, Swing GUI with threaded generations
- [cis36b-battleships](cis36b-battleships/) — Networked Swing Battleships, 25x25 grids
- [cis36b-supplier-consumer-threads](cis36b-supplier-consumer-threads/) — Supplier-consumer coordination with wait/notify
- [cis36b-concurrency-distance](cis36b-concurrency-distance/) — Fork/Join distance calculation over 10 million point pairs

## How to run

Each project folder has its own README with its run command. Compile one folder at a time:

```bash
cd cis27-redblack-bst
javac *.java
java TestRedBlackTree
```

Do not compile the whole repo together: some projects define classes with the same names (for example, `Edge` and `G50` in both the Shortest Path and MST projects).

## Notes
- Spelling Checker requires a `words.txt` dictionary file in its folder; that file was not part of the Canvas submission.
- Maze is library code with no `main` method; a sample maze is shown in its source comments.
