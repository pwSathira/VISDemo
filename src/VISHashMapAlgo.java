//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class VISHashMapAlgo {
    public VISHashMapAlgo() {
    }

    public static void main(String[] args) {
        HashMap<String, String> M = new HashMap();
        Scanner sc = new Scanner(System.in);
        boolean complete = false;

        while(true) {
            while(!complete) {
                System.out.print("(HashMap) Enter (i)nsert, (d)elete, (s)earch, (p)rint or (q)uit: ");
                String userIn = sc.next().toLowerCase();
                String key;
                if (userIn.charAt(0) == 'i') {
                    System.out.print("Enter potential obstacle: ");
                    key = sc.next();
                    System.out.print("Enter the distance from vehicle (in km): ");
                    String value = sc.next();
                    M = addKey(key, value, M);
                } else if (userIn.charAt(0) == 'd') {
                    System.out.print("Enter the delete term: ");
                    key = sc.next();
                    M = removeKey(key, M);
                } else if (userIn.charAt(0) == 's') {
                    System.out.print("Enter the search key: ");
                    key = sc.next();
                    System.out.println("The result is : " + (String)M.get(key));
                } else if (userIn.charAt(0) == 'p') {
                    Iterator var6 = M.keySet().iterator();

                    while(var6.hasNext()) {
                        key = (String)var6.next();
                        String value = (String)M.get(key);
                        System.out.println(key + " is " + value + "km away.");
                    }
                } else if (userIn.charAt(0) == 'q') {
                    complete = true;
                } else {
                    System.out.println("Input not recognized try again!");
                }
            }

            sc.close();
            return;
        }
    }

    public static String searchKey(String key, HashMap<String, String> m) {
        String value = "";
        value = (String)m.get(key);
        return value == null ? "search term does not exist!" : value;
    }

    public static HashMap<String, String> removeKey(String key, HashMap<String, String> m) {
        m.remove(key);
        return m;
    }

    public static HashMap<String, String> addKey(String k, String v, HashMap<String, String> m) {
        m.put(k, v);
        return m;
    }
}
