import java.util.*;

public class Trie<Value> {
    // if we are storing ASCII characters only then radix = 128
    private static final int R = 128;
    private Node root; // of the trie

    private static class Node {
        Object val;
        Node[] next = new Node[128];
    }

    // PUT operation, add a key/value to the trie
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }

        if (key.length() == d) {
            x.val = val;
        } else {
            // keep going deeper, building the prefix
            char c = key.charAt(d);
            x.next[c] = put(x.next[c], key, val, d+1);
        }

        return x;
    }

    // GET operation, return the value if the key exists in the trie
    @SuppressWarnings("unchecked")
    public Value get(String key) {
        Node x = get(root, key, 0);

        if (x == null) {
            return null;
        }

        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }

        if (key.length() == d) {
            return x;
        }

        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    // Current size of the trie (= number of keys stored)
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }

        int count = 0;
        if (x.val != null) {
            count++;
        }

        // Explore the sub-tries
        for (char c = 0; c < R; c++) {
            count += size(x.next[c]);
        }

        return count;
    }

    // LIST all the keys stored in the trie
    public Iterable<String> keys() {
        Stack<String> collected = new Stack<>();
        collect(root, "", collected);
        return collected;
    }

    private void collect(Node x, String prefix, Stack<String> collected) {
        if (x == null) {
            return;
        }

        if (x.val != null) {
            collected.add(prefix);
        }

        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, collected);
        }
    }

    // LIST keys that start with a given prefix
    public Iterable<String> keysThatStartWith(String prefix) {
        Stack<String> collected = new Stack<>();

        // find the root node for the subtrie for the given prefix
        Node x = get(root, prefix, 0);

        collect(x, prefix, collected);
        return collected;
    }

    // MAIN
    public static void main(String[] args) {
        String[] words = {"she", "sells", "sea", "shells", "by", "the", "sea"};
        Trie<Integer> trie = new Trie<>();
        for (String w : words) {
            trie.put(w, 1);
        }

        System.out.println("--- has key 'sea':");
        System.out.println(trie.get("sea") != null);

        System.out.println("--- has key 'shore':");
        System.out.println(trie.get("shore") != null);

        System.out.println("--- keys in the trie:");
        for (String key : trie.keys()) {
            System.out.println(key);
        }

        System.out.println("--- keys that start with 'sh':");
        for (String key : trie.keysThatStartWith("sh")) {
            System.out.println(key);
        }

        System.out.println("--- adding 'shore'");
        trie.put("shore", 1);

        System.out.println("--- keys that start with 'sh':");
        for (String key : trie.keysThatStartWith("sh")) {
            System.out.println(key);
        }
    }
}
