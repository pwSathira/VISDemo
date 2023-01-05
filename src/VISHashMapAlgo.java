import java.util.HashMap;
import java.util.Scanner;

public class VISHashMapAlgo {
    public static void main(String args[]){
        HashMap<String, String> M = new HashMap<String, String>();// create a HashMap to store key-value pairs
        Scanner sc = new Scanner(System.in);
        boolean complete = false;

        while(!complete){
            System.out.print("(HashMap) Enter (i)nsert, (d)elete, (s)earch, (p)rint or (q)uit: ");
            String userIn = sc.next().toLowerCase();  // convert user input to lower case
            if(userIn.charAt(0) == 'i'){ // if user entered "i", insert a new key-value pair into the HashMap
                System.out.print("Enter potential obstacle: ");
                String key = sc.next();
                System.out.print("Enter the distance from vehicle (in km): ");
                String value = sc.next();
                M = addKey(key, value, M);// call the addKey() method to insert the key-value pair
            } else if (userIn.charAt(0) == 'd') {  // if user entered "d", delete a key-value pair from the HashMap
                System.out.print("Enter the delete term: ");
                String key = sc.next();
                M = removeKey(key, M);// call the removeKey() method to delete the key-value pair
            } else if (userIn.charAt(0) == 's') {
                System.out.print("Enter the search key: "); // if user entered "s", search the HashMap for a given key
                String key = sc.next();
                System.out.println("The result is : " + M.get(key)); // use the get() method to search for the given key
            } else if (userIn.charAt(0) == 'p') {  // if user entered "p", print all key-value pairs in the HashMap
                for (String key : M.keySet()){
                    String value = M.get(key);
                    System.out.println(key + " is " + value + "km away.");
                }
            } else if (userIn.charAt(0) == 'q') {// if user entered "q", quit the program
                complete = true;
            } else {
                System.out.println("Input not recognized try again!"); // if user input is not recognized, prompt them to try again
            }
        }
        sc.close();
    }

    /*
     * This method searches the given HashMap for the given key
     * key - the key to search for
     * m - the HashMap to search
     * returns - the value associated with the given key, or "search term does not exist!" if the key is not found
     */
    public static String searchKey(String key, HashMap<String, String> m) {
        String value = "";
        value = m.get(key);
        if (value == null) {
            return "search term does not exist!";
        }
        return value;
    }
    /*
     * This method removes the given key-value pair into the given HashMap
     * k - the key to delete
     * m - the HashMap to insert into
     * returns - the updated HashMap
     */
    public static HashMap<String, String> removeKey(String key, HashMap<String, String> m) {
        // remove the key-value pair with the given key from the given HashMap
        m.remove(key);
        return m;
    }

    /*
     * This method inserts the given key-value pair into the given HashMap
     * k - the key to insert
     * v - the value to insert
     * m - the HashMap to insert into
     * returns - the updated HashMap
     */
    public static HashMap<String, String> addKey(String k, String v, HashMap<String,String> m) {
        m.put(k,v);
        return m;
    }
}