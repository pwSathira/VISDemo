import java.util.Scanner;

public class VISTreeAlgo {
    public static void main(String args[]){
        // create a new VISNode object
        VISTreeAlgo visTree = new VISTreeAlgo();
        Scanner sc = new Scanner(System.in);
        boolean complete = false;

        while(!complete){
            System.out.print("Enter (i)nsert, (d)elete, (s)earch, (p)rint or (q)uit: ");
            String userIn = sc.next().toLowerCase();
            if(userIn.charAt(0) == 'i'){ // if user entered "i", insert a new key-value pair into VISNode
                System.out.print("Enter potential obstacle: ");
                String key = sc.next();
                System.out.print("Enter the distance from vehicle (in km): ");
                String value = sc.next();
                visTree.put(key, value);
            } else if (userIn.charAt(0) == 'd') { // if user entered "d", delete a key-value pair from the VISNode
                System.out.print("Enter the delete term: ");
                String key = sc.next();
                visTree.delete(key);
            } else if (userIn.charAt(0) == 's') {  // if user entered "s", search the VISNode for a given key
                System.out.print("Enter the search key: ");
                String key = sc.next();
                System.out.println("The result is : " + visTree.get(key));
            } else if (userIn.charAt(0) == 'p') { // if user entered "p", print all key-value pairs in the VISNode
                visTree.print();
            } else if (userIn.charAt(0) == 'q') {// if user entered "q", quit the program
                complete = true;
            } else {
                System.out.println("Input not recognized try again!");
            }
        }
        sc.close();
    }
    public static class Entry {
        // class to store key-value pairs
        private String key;
        private String value;
        // constructor to create a new Entry object with the given key and value
        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
        // getter method for the key
        public String getKey() {
            return key;
        }
        // setter method for the key
        public void setKey(String key) {
            this.key = key;
        }
        // getter method for the value
        public String getValue() {
            return value;
        }
        // setter method for the value
        public void setValue(String value) {
            this.value = value;
        }
    }
    private static class Node {
        // class to store a node in the binary search tree
        private Entry entry; // the entry (key-value pair) stored in this node
        private Node left; // reference to the left child node
        private Node right; // reference to the right child node

        public Node(Entry entry) { // constructor to create a new Node object with the given entry
            this.entry = entry;
        }
    }
    private Node root; // reference to the root node of the binary search tree
    public VISTreeAlgo() { // constructor to create a new VISNode object
        root = null;// initialize the root node to null
    }
    public void put(String key, String value) { // method to insert a new key-value pair into the binary search tree
        root = put(root, new Entry(key, value)); // call the private put() method to insert the key-value pair
    }

    /*
     * This method inserts the given entry (key-value pair) into the binary search tree
     * node - the root node of the subtree to insert the entry into
     * entry - the entry (key-value pair) to insert
     * returns - the root node of the updated subtree
     */
    private Node put(Node node, Entry entry) {
        if (node == null) { // if the subtree is empty, create a new Node object with the given entry and return it
            return new Node(entry);
        } else if (node.entry.getKey().compareTo(entry.getKey()) > 0) {
            // if the given entry's key is less than the current node's key, insert the entry into the left subtree
            node.left = put(node.left, entry);
        } else if (node.entry.getKey().compareTo(entry.getKey()) < 0) {
            // if the given entry's key is greater than the current node's key, insert the entry into the right subtree
            node.right = put(node.right, entry);
        } else {
            // if the given entry's key is equal to the current node's key, update the current node's value with the given entry's value
            node.entry.setValue(entry.getValue());
        }

        return node; // return the root node of the updated subtree
    }
    public String get(String key) {
        return get(root, key);
    }
    private String get(Node node, String key) {
        if (node == null) { // if the subtree is empty, create a new Node object with the given entry and return it
            return null;
        } else if (node.entry.getKey().compareTo(key) > 0) {
            // if the given entry's key is less than the current node's key, get the entry in the left subtree
            return get(node.left, key);
        } else if (node.entry.getKey().compareTo(key) < 0) {
            // if the given entry's key is greater than the current node's key, get the entry in the right subtree
            return get(node.right, key);
        } else {
            // if the given entry's key is equal to the current node's key, get the current node's value
            return node.entry.getValue();
        }
    }
    public void delete(String key) {
        root = delete(root, key); // call the private delete() method to delete the node with the given key
    }

    /*
     * This method finds the node with the minimum key in the given subtree
     * node - the root node of the subtree to search in
     * returns - the node with the minimum key in the subtree
     */
    private Node findMin(Node node) {
        if (node == null) { // if the subtree is empty, return null
            return null;
        } else if (node.left == null) { // if the left child node is null, return the current node
            return node;
        } else {  // otherwise, recursively search for the minimum key in the left subtree
            return findMin(node.left);
        }
    }
    /*
     * This method removes the node with the minimum key from the given subtree
     * node - the root node of the subtree to remove the minimum node from
     * returns - the root node of the updated subtree
     */
    private Node removeMin(Node node) {
        if (node == null) {  // if the subtree is empty, return null
            return null;
        } else if (node.left == null) { // if the left child node is null, return the right child node
            return node.right;
        } else {  // otherwise, recursively remove the minimum node from the left subtree and return the updated subtree
            node.left = removeMin(node.left);
            return node;
        }
    }
    /*
     * This method deletes the node with the given key from the given subtree
     * node - the root node of the subtree to delete the node from
     * key - the key of the node to delete
     * returns - the root node of the updated subtree
     */
    private Node delete(Node node, String key) {
        if (node == null) { // if the subtree is empty, return null
            return null;
        } else if (node.entry.getKey().compareTo(key) > 0) {
            // if the given key is less than the current node's key, delete the node with the given key from the left subtree
            node.left = delete(node.left, key);
        } else if (node.entry.getKey().compareTo(key) < 0) {
            // if the given key is greater than the current node's key, delete the node with the given key from the right subtree
            node.right = delete(node.right, key);
        } else {  // if the given key is equal to the current node's key, delete the current node
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {  // if the current node has two child nodes, find the minimum key in the right subtree
                Node minNode = findMin(node.right);
                minNode.right = removeMin(node.right); // remove the minimum node from the right subtree
                minNode.left = node.left;
                return minNode;
            }
        }
        return node;
    }
    public void print(){
        print(root); // call the private print() method to print the entries in the tree in ascending order
    }
    public void print(Node node){
        if (node != null){ // if the subtree is not empty
            print(node.left); // recursively print the entries in the left subtree
            System.out.println(node.entry.getKey() + " is " + node.entry.getValue()+ "km away."); // print the current node's entry
            print(node.right); // recursively print the entries in the right subtree
        }
    }
}