# Parallel Distance Calculation — Fork/Join

Distance calculation over 10 million point pairs, comparing single-threaded time with Fork/Join at several thresholds.

## Run

```bash
javac *.java
java Main
```

Compile each project folder on its own. Some projects define classes with the same names (for example, `Edge` and `G50` appear in both the Shortest Path and MST projects), so do not compile the whole repo together.
