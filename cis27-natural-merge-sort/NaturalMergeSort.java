class Node {
  char val;
  Node next;

  Node(char val) {
    this.val = val;
    this.next = null;
  }
}

public class NaturalMergeSort {

  // Main function to sort the linked list using natural merge sort
  public static Node naturalMergeSort(Node head) {
    if (head == null || head.next == null) {
      return head; // Empty or single-node list is already sorted
    }

    while (true) {
      boolean merged = false; // Tracks if any merges occurred in this pass
      Node dummy = new Node('0'); // Dummy node to build the new list
      Node tail = dummy; // Tail pointer for appending merged runs
      Node current = head; // Current position in the list

      while (current != null) {
        // Get the first natural run
        Node[] run1 = getRun(current);
        if (run1 == null)
          break;
        Node start1 = run1[0];
        current = run1[1];

        // If no more nodes remain, append the last run and exit inner loop
        if (current == null) {
          tail.next = start1;
          break;
        }

        // Get the second natural run
        Node[] run2 = getRun(current);
        Node start2 = run2[0];
        current = run2[1];

        // Merge the two runs and append to the new list
        MergeResult mr = merge(start1, start2);
        tail.next = mr.head;
        tail = mr.tail;
        merged = true; // Merge occurred
      }

      head = dummy.next; // Update head to the new list
      if (!merged)
        break; // If no merges, the list is sorted
    }

    return head;
  }

  // Helper class to return head and tail of merged list
  static class MergeResult {
    Node head;
    Node tail;

    MergeResult(Node h, Node t) {
      head = h;
      tail = t;
    }
  }

  // Merges two sorted linked lists iteratively
  public static MergeResult merge(Node a, Node b) {
    Node dummy = new Node('0');
    Node tail = dummy;

    while (a != null && b != null) {
      if (a.val <= b.val) {
        tail.next = a;
        a = a.next;
      } else {
        tail.next = b;
        b = b.next;
      }
      tail = tail.next;
    }

    // Append remaining nodes
    tail.next = (a != null) ? a : b;
    while (tail.next != null) {
      tail = tail.next;
    }

    return new MergeResult(dummy.next, tail);
  }

  // Extracts the next natural run from the list
  public static Node[] getRun(Node current) {
    if (current == null)
      return null;
    Node start = current;
    while (current.next != null && current.next.val >= current.val) {
      current = current.next;
    }
    Node end = current;
    Node nextStart = current.next;
    current.next = null; // Cut the run
    return new Node[] { start, nextStart };
  }

  // Utility to print the linked list
  public static void printList(Node head) {
    Node current = head;
    while (current != null) {
      System.out.print(current.val);
      if (current.next != null)
        System.out.print(" -> ");
      current = current.next;
    }
    System.out.println();
  }

  // Utility to create a linked list from an array
  public static Node createList(char[] arr) {
    if (arr.length == 0)
      return null;
    Node head = new Node(arr[0]);
    Node current = head;
    for (int i = 1; i < arr.length; i++) {
      current.next = new Node(arr[i]);
      current = current.next;
    }
    return head;
  }

  public static void main(String[] args) {
    char[] arr = { 'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E' };
    Node head = createList(arr);
    System.out.println("Original list:");
    printList(head);
    head = naturalMergeSort(head);
    System.out.println("Sorted list:");
    printList(head);
  }
}

/*
 * Original list:
 * M -> E -> R -> G -> E -> S -> O -> R -> T -> E -> X -> A -> M -> P -> L -> E
 * Sorted list:
 * A -> E -> E -> E -> E -> G -> L -> M -> M -> O -> P -> R -> R -> S -> T -> X
 */