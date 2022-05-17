package cw4;

import java.util.HashMap;

public class Driver {

    public static void main(String[] args) {
        String truthStatement1 = "(p -> (p && q || (r || q)))";
        String truthStatement2 = "(p -> !(q && r))";

        HashMap<Character, Boolean> values1 = new HashMap<Character, Boolean>();
        values1.put('p', true);
        values1.put('q', true);
        values1.put('r', true);

        Jexl test1 = new Jexl();

        System.out.println(test1.plTrueJexl(truthStatement1, values1));
        System.out.println(test1.plTrueJexl(truthStatement2, values1));

    }
}
