//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class VISTreeAlgo {
    private Node root = null;

    public static void main(String[] args) {
        VISTreeAlgo visTreeAlgo = new VISTreeAlgo();
        Scanner sc = new Scanner(System.in);
        boolean complete = false;

        while(!complete) {
            System.out.print("Enter (i)nsert, (d)elete, (s)earch, (p)rint or (q)uit: ");
            String userIn = sc.next().toLowerCase();
            String key;
            if (userIn.charAt(0) == 'i') {
                System.out.print("Enter potential obstacle: ");
                key = sc.next();
                System.out.print("Enter the distance from vehicle (in km): ");
                String value = sc.next();
                visTreeAlgo.put(key, value);
            } else if (userIn.charAt(0) == 'd') {
                System.out.print("Enter the delete term: ");
                key = sc.next();
                visTreeAlgo.delete(key);
            } else if (userIn.charAt(0) == 's') {
                System.out.print("Enter the search key: ");
                key = sc.next();
                System.out.println("The result is : " + visTreeAlgo.get(key));
            } else if (userIn.charAt(0) == 'p') {
                visTreeAlgo.print();
            } else if (userIn.charAt(0) == 'q') {
                complete = true;
            } else {
                System.out.println("Input not recognized try again!");
            }
        }

        sc.close();
    }

    public VISTreeAlgo() {
    }

    public void put(String key, String value) {
        this.root = this.put(this.root, new Entry(key, value));
    }

    private Node put(Node node, Entry entry) {
        if (node == null) {
            return new Node(entry);
        } else {
            if (node.entry.getKey().compareTo(entry.getKey()) > 0) {
                node.left = this.put(node.left, entry);
            } else if (node.entry.getKey().compareTo(entry.getKey()) < 0) {
                node.right = this.put(node.right, entry);
            } else {
                node.entry.setValue(entry.getValue());
            }

            return node;
        }
    }

    public String get(String key) {
        return this.get(this.root, key);
    }

    private String get(Node node, String key) {
        if (node == null) {
            return null;
        } else if (node.entry.getKey().compareTo(key) > 0) {
            return this.get(node.left, key);
        } else {
            return node.entry.getKey().compareTo(key) < 0 ? this.get(node.right, key) : node.entry.getValue();
        }
    }

    public void delete(String key) {
        this.root = this.delete(this.root, key);
    }

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        } else {
            return node.left == null ? node : this.findMin(node.left);
        }
    }

    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node.right;
        } else {
            node.left = this.removeMin(node.left);
            return node;
        }
    }

    private Node delete(Node node, String key) {
        if (node == null) {
            return null;
        } else {
            if (node.entry.getKey().compareTo(key) > 0) {
                node.left = this.delete(node.left, key);
            } else {
                if (node.entry.getKey().compareTo(key) >= 0) {
                    if (node.left == null) {
                        return node.right;
                    }

                    if (node.right == null) {
                        return node.left;
                    }

                    Node minNode = this.findMin(node.right);
                    minNode.right = this.removeMin(node.right);
                    minNode.left = node.left;
                    return minNode;
                }

                node.right = this.delete(node.right, key);
            }

            return node;
        }
    }

    public void print() {
        this.print(this.root);
    }

    public void print(Node node) {
        if (node != null) {
            this.print(node.left);
            System.out.println(node.entry.getKey() + " is " + node.entry.getValue() + "km away.");
            this.print(node.right);
        }

    }

    public static class Entry {
        private String key;
        private String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private static class Node {
        private Entry entry;
        private Node left;
        private Node right;

        public Node(Entry entry) {
            this.entry = entry;
        }
    }
}
