import java.util.NoSuchElementException; // Import needed for deleteMin/delete if tree is empty

public class RedBlackBST<Key extends Comparable<Key>, Value> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;
  private Node root;

  private class Node {
    Key key;
    Value val;
    Node left, right;
    boolean color;
    int N; // Use size instead of N for clarity if preferred

    Node(Key key, Value val, int N, boolean color) {
      this.key = key;
      this.val = val;
      this.N = N; // size of subtree rooted here
      this.color = color; // color of parent link
    }
  }

  // --- Helper Methods ---

  private boolean isRed(Node x) {
    if (x == null)
      return false; // null links are black
    return x.color == RED;
  }

  private int size(Node x) {
    if (x == null)
      return 0;
    return x.N;
  }

  public int size() {
    return size(root);
  }

  public boolean isEmpty() {
    return root == null;
  }

  // --- Rotations ---

  // make a right-leaning link lean to the left
  private Node rotateLeft(Node h) {
    // assert (h != null) && isRed(h.right);
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = h.color; // Preserve color of original root h
    h.color = RED; // Make new left child red
    x.N = h.N;
    h.N = size(h.left) + size(h.right) + 1;
    return x;
  }

  // make a left-leaning link lean to the right
  private Node rotateRight(Node h) {
    // assert (h != null) && isRed(h.left);
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = h.color; // Preserve color of original root h
    h.color = RED; // Make new right child red
    x.N = h.N;
    h.N = size(h.left) + size(h.right) + 1;
    return x;
  }

  // --- Color Flip ---
  // Flips the colors of a node and its two children.
  // Assumes h is BLACK and children are RED (e.g., after insert/balance)
  // OR h is RED and children are BLACK (e.g., before moveRedLeft/Right)
  private void flipColors(Node h) {
    // assert (h != null);
    // Null checks added for robustness, though typically called when children exist
    // assert !isRed(h) && isRed(h.left) && isRed(h.right) // case 1
    // || isRed(h) && !isRed(h.left) && !isRed(h.right); // case 2
    h.color = !h.color;
    if (h.left != null)
      h.left.color = !h.left.color;
    if (h.right != null)
      h.right.color = !h.right.color;
  }

  // --- Insertion ---

  public void put(Key key, Value val) {
    if (key == null)
      throw new IllegalArgumentException("first argument to put() is null");
    if (val == null) {
      delete(key); // Standard behavior for null value? Or throw exception?
      return;
    }
    root = put(root, key, val);
    root.color = BLACK; // Root is always black
    // assert check();
  }

  // insert the key-value pair in the subtree rooted at h
  private Node put(Node h, Key key, Value val) {
    if (h == null)
      return new Node(key, val, 1, RED); // Insert new node as RED

    int cmp = key.compareTo(h.key);
    if (cmp < 0)
      h.left = put(h.left, key, val);
    else if (cmp > 0)
      h.right = put(h.right, key, val);
    else
      h.val = val; // Update value if key exists

    // Fix-up any right-leaning links
    if (isRed(h.right) && !isRed(h.left))
      h = rotateLeft(h);
    // Fix-up two reds in a row
    if (isRed(h.left) && isRed(h.left.left))
      h = rotateRight(h);
    // Split 4-nodes
    if (isRed(h.left) && isRed(h.right))
      flipColors(h);

    h.N = size(h.left) + size(h.right) + 1; // Update size
    return h;
  }

  // --- Deletion Helper Methods ---

  // Assuming h is red and both h.left and h.left.left
  // are black, make h.left or one of its children red.
  private Node moveRedLeft(Node h) {
    // assert (h != null);
    // assert isRed(h) && !isRed(h.left) && (h.left == null || !isRed(h.left.left));

    flipColors(h); // h becomes BLACK, children become RED
    // Check if we need to borrow from the right sibling
    if (h.right != null && isRed(h.right.left)) {
      h.right = rotateRight(h.right);
      h = rotateLeft(h);
      flipColors(h); // Flip colors back after rotations
    }
    return h;
  }

  // Assuming h is red and both h.right and h.right.left
  // are black, make h.right or one of its children red.
  private Node moveRedRight(Node h) {
    // assert (h != null);
    // assert isRed(h) && !isRed(h.right) && (h.right == null ||
    // !isRed(h.right.left));

    flipColors(h); // h becomes BLACK, children become RED
    // Check if borrowing created issue on left (consecutive reds)
    if (h.left != null && isRed(h.left.left)) { // Check h.left exists!
      h = rotateRight(h);
      flipColors(h); // Flip colors back after rotation
    }
    return h;
  }

  // Restore red-black tree invariant
  private Node balance(Node h) {
    // assert (h != null);

    // Fix right-leaning red link
    if (isRed(h.right) && !isRed(h.left))
      h = rotateLeft(h); // Added !isRed(h.left)
    // Fix two consecutive left-leaning red links
    if (isRed(h.left) && isRed(h.left.left))
      h = rotateRight(h);
    // Split 4-node (or merge colors after delete)
    if (isRed(h.left) && isRed(h.right))
      flipColors(h);

    h.N = size(h.left) + size(h.right) + 1; // Update size
    return h;
  }

  // --- Deletion Min ---

  public void deleteMin() {
    if (isEmpty())
      throw new NoSuchElementException("BST underflow");

    // If both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMin(root);
    if (!isEmpty())
      root.color = BLACK; // Root must be black
    // assert check();
  }

  // delete the key-value pair with the minimum key rooted at h
  private Node deleteMin(Node h) {
    if (h.left == null) // Min found (base case)
      return null;

    // Ensure current node's left child is not a 2-node
    if (!isRed(h.left) && (h.left.left == null || !isRed(h.left.left)))
      h = moveRedLeft(h);

    h.left = deleteMin(h.left); // Recurse down

    return balance(h); // Balance on the way up
  }

  // --- Deletion Max (Similar to deleteMin, for completeness) ---

  public void deleteMax() {
    if (isEmpty())
      throw new NoSuchElementException("BST underflow");

    // if both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMax(root);
    if (!isEmpty())
      root.color = BLACK;
    // assert check();
  }

  // delete the key-value pair with the maximum key rooted at h
  private Node deleteMax(Node h) {
    if (isRed(h.left)) // Push red link right
      h = rotateRight(h);

    if (h.right == null) // Max found (base case)
      return null;

    // Ensure current node's right child is not a 2-node
    if (!isRed(h.right) && (h.right.left == null || !isRed(h.right.left)))
      h = moveRedRight(h);

    h.right = deleteMax(h.right); // Recurse down

    return balance(h); // Balance on the way up
  }

  // --- Deletion General ---

  public void delete(Key key) {
    if (key == null)
      throw new IllegalArgumentException("argument to delete() is null");
    if (!contains(key))
      return; // Or throw exception?

    // If both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = delete(root, key);
    if (!isEmpty())
      root.color = BLACK; // Root must be black
    // assert check();
  }

  // delete the key-value pair with the given key rooted at h
  private Node delete(Node h, Key key) {
    // assert get(h, key) != null; // Assumes key is present

    if (key.compareTo(h.key) < 0) { // Move left
      // Ensure left child is not a 2-node on the way down
      if (!isRed(h.left) && (h.left == null || !isRed(h.left.left))) // Check h.left null!
        h = moveRedLeft(h);
      if (h.left != null) // Check h.left still exists after moveRedLeft
        h.left = delete(h.left, key);
    } else { // Move right or found key
      if (isRed(h.left)) // Ensure node is not leaning right unnecessarily
        h = rotateRight(h);

      // Found key at a leaf or node with only left child (after potential rotation)
      if (key.compareTo(h.key) == 0 && (h.right == null))
        return null;

      // Ensure right child is not a 2-node on the way down
      // Need null check for h.right here!
      if (h.right != null && !isRed(h.right) && !isRed(h.right.left)) {
        h = moveRedRight(h);
      }

      // Found key at internal node
      if (key.compareTo(h.key) == 0) {
        // Find minimum node in right subtree
        Node minNode = min(h.right);
        h.key = minNode.key; // Replace h's key/val
        h.val = get(h.right, minNode.key); // Need get helper or direct access
        // h.val = minNode.val; // If val is accessible
        h.right = deleteMin(h.right); // Delete min from right subtree
      } else { // Key not found yet, move right
        if (h.right != null) // Check h.right still exists
          h.right = delete(h.right, key);
      }
    }
    return balance(h); // Balance on the way up
  }

  // --- Min, Max, Get ---

  public Key min() {
    if (isEmpty())
      throw new NoSuchElementException("calls min() with empty symbol table");
    return min(root).key;
  }

  // the smallest key in subtree rooted at x; null if no such key
  private Node min(Node x) {
    // assert x != null;
    if (x.left == null)
      return x;
    else
      return min(x.left);
  }

  public Key max() {
    if (isEmpty())
      throw new NoSuchElementException("calls max() with empty symbol table");
    return max(root).key;
  }

  // the largest key in the subtree rooted at x; null if no such key
  private Node max(Node x) {
    // assert x != null;
    if (x.right == null)
      return x;
    else
      return max(x.right);
  }

  public Value get(Key key) {
    if (key == null)
      throw new IllegalArgumentException("argument to get() is null");
    return get(root, key);
  }

  // value associated with the given key in subtree rooted at x; null if no such
  // key
  private Value get(Node x, Key key) {
    while (x != null) {
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
        x = x.left;
      else if (cmp > 0)
        x = x.right;
      else
        return x.val;
    }
    return null;
  }

  public boolean contains(Key key) {
    return get(key) != null;
  }

  // --- In-order Traversal ---
  public void inOrderTraversal() {
    inOrderTraversal(root);
  }

  private void inOrderTraversal(Node node) {
    if (node == null) {
      return;
    }
    inOrderTraversal(node.left);
    System.out.print(node.key + " ");
    inOrderTraversal(node.right);
  }

}