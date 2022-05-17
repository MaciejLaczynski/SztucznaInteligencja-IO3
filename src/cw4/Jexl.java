package cw4;

import org.apache.commons.jexl2.*;
import org.apache.commons.logging.*;

import java.util.HashMap;

public class Jexl {

    public boolean plTrueJexl(String toEvaluate, HashMap<Character, Boolean> value){

        String[] split = {"", ""};

        if(toEvaluate.contains("->")){
            split = toEvaluate.split("->");

            split[0] = split[0].replace("(", "");
            split[1] = split[1].substring(0, split[1].length()-1);

            System.out.println("split[0] = " + split[0]);
            System.out.println("split[1] = " + split[1]);

            toEvaluate = split[1];
        }

        JexlEngine jexl = new JexlEngine();
        jexl.setSilent(true);
        jexl.setLenient(true);

        Expression expression = jexl.createExpression(toEvaluate);
        JexlContext context = new MapContext();

        for(Character key : value.keySet()){
            context.set(key.toString(), value.get(key));
        }

        Boolean secondPart = (Boolean) expression.evaluate(context);

        Boolean firstPart = value.get(split[0].charAt(0));

        return firstPart ? secondPart : true;
    }
}
