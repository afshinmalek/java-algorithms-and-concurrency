
class TestRedBlackTree {
    public static void main(String[] args) {

        RedBlackBST<Integer, Integer> tree = new RedBlackBST<>();
        for (int i = 1; i <= 60; i++) {
            tree.put(i, i);
        }
        // Print the tree in order
        System.out.println("In-order traversal of the Red-Black Tree:");
        tree.inOrderTraversal();
        // draw the tree
        System.out.println();

        // delete the keys 1 to 20 from the tree

        System.out.println();
        for (int i = 1; i <= 20; i++) {
            tree.delete(i);
        }
        // Print the tree in order after deletion
        System.out.println("In-order traversal after deleting keys 1 to 20:");
        tree.inOrderTraversal();

        System.out.println();

        RedBlackBST<String, String> tree2 = new RedBlackBST<>();
        String[] keys = { "Y", "L", "P", "M", "X", "H", "C", "R", "A", "E", "S", "T", "B", "C", "A" };
        for (String key : keys) {
            tree2.put(key, key);
        }

        // draw the tree
        System.out.println();

    }

}

//@formatter:off

/*
"In-order traversal of the Red-Black Tree:"
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 


In-order traversal after deleting keys 1 to 20:
21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 

 */