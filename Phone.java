import java.util.*;

/*
    Given an input in the form '223', print all letter combinations based off
    a regular dialpad. E.g., the key '2' is associated with the letters 'A', 'B'
    and 'C' on your phone.

    So '22' should return:
    {"AA", "AB", "AC", "BA", "BB", "BC", "CA", "CB", "CC"}
*/

public class Phone {
    private static final char[][] LETTERS = {
        {'0'},
        {'1'},
        {'A', 'B', 'C'},
        {'D', 'E', 'F'},
        {'G', 'H', 'I'},
        {'J', 'K', 'L'},
        {'M', 'N', 'O'},
        {'P', 'Q', 'R', 'S'},
        {'T', 'U', 'V'},
        {'W', 'X', 'Y', 'Z'}
    };

    public static String[] combos(String input) {
        ArrayList<String> collected = new ArrayList<>();
        combos("", input.charAt(0), input.substring(1), collected);
        return collected.toArray(new String[0]);
    }

    private static void combos(String prefix, char c, String remaining, ArrayList<String> collected) {
        char[] letters = LETTERS[Character.getNumericValue(c)];
        for (char l : letters) {
            if (remaining.length() == 0) {
                collected.add(prefix + l);
            } else {
                combos(prefix + l, remaining.charAt(0), remaining.substring(1), collected);
            }
        }
    }

    public static void main(String[] args) {
        String[] combos = combos(args[0]);
        for (String c : combos)
            System.out.println(c);
    }
}
