package cw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

    public boolean innerPlTrue(ArrayList<Object> toParse, boolean[] value){

        while(toParse.size() > 1){
            ArrayList<Object> subCharList = new ArrayList<>();
            for(int i = 0; i < toParse.size(); i++){
                if(toParse.get(i).equals("(")){
                    subCharList.clear();
                } else if(toParse.get(i).equals(")")){
                    toParse.set(i, calculate(subCharList));
                    for(int j = 1; j < subCharList.size()+2; j++){
                        toParse.remove(i-j);
                    }
                    return(innerPlTrue(toParse, value));
                }
                if(!toParse.get(i).equals("(")){
                    System.out.println(toParse);
                    subCharList.add(toParse.get(i));
                }
            }
        }
        return calculate(toParse);
    }

    public boolean plTrue(String toParse, boolean[] value) {
        if(toParse.length() == 0) {
            return true;
        }
        else if(toParse.length() == 1) {
            return value[0];
        }

        toParse = toParse.replace(" ", "");

        ArrayList<Object> charList = new ArrayList<>(Arrays.asList(toParse.split("")));

        ArrayList<String> usedChars = new ArrayList<>();

        for (int i = 0; i < charList.size(); i++) {
            String tempHolder = (String) charList.get(i);
            if(tempHolder.contentEquals(tempHolder.toLowerCase()) && tempHolder.matches("[^v>^()]")) {
                if(!usedChars.contains(tempHolder)){
                    usedChars.add(tempHolder.toLowerCase());
                }
            }
        }

        for(int i = 0; i < usedChars.size(); i++){
            for(int j = 0; j < charList.size(); j++){
                if(charList.get(j).equals(usedChars.get(i))){
                    charList.set(j, true);
                    System.out.println(charList);
                }
            }
        }

        while(charList.size() > 1){
            ArrayList<Object> subCharList = new ArrayList<>();
            for(int i = 0; i < charList.size(); i++){
                if(charList.get(i).equals("(")){
                    subCharList.clear();
                } else if(charList.get(i).equals(")")){
                    charList.set(i, calculate(subCharList));
                    for(int j = 1; j < subCharList.size()+2; j++){
                        charList.remove(i-j);
                    }
                    return(innerPlTrue(charList, value));
                }
                if(!charList.get(i).equals("(")){
                    subCharList.add(charList.get(i));
                }
            }
        }
        return calculate(charList);
    }

    public Boolean calculate(ArrayList<Object> toParse){
        if(toParse.size() == 1){
            return (boolean) toParse.get(0);
        }

        ArrayList<Object> subParse = new ArrayList<>();

        for(int i = 0; i < toParse.size(); i++){
            if(toParse.get(i).equals("¬")){
                if((boolean)toParse.get(i+1)){
                    toParse.set(i+1, false);
                } else if(!(boolean)toParse.get(i+1)){
                    toParse.set(i+1, true);
                }
                subParse.add(toParse.get(i));
            }
        }

        Collections.reverse(subParse);

        for(int i = 0; i < subParse.size(); i++){
            toParse.remove(i);
        }

        if(toParse.size() == 1){
            return (boolean) toParse.get(0);
        }

        if(toParse.get(1).equals("^")){
            toParse.set(1, (boolean)toParse.get(0) && (boolean)toParse.get(2));
            toParse.remove(2);
            toParse.remove(0);
            return calculate(toParse);
        }

        if(toParse.get(1).equals("v")){
            toParse.set(1, (boolean)toParse.get(0) || (boolean)toParse.get(2));
            toParse.remove(2);
            toParse.remove(0);
            return calculate(toParse);
        }

        if(toParse.get(1).equals(">")){
            toParse.set(1, !(boolean)toParse.get(0) || (boolean)toParse.get(2));
            toParse.remove(2);
            toParse.remove(0);
            return calculate(toParse);
        }
        if(toParse.get(1).equals("<>")){
            toParse.set(1, (!(boolean)toParse.get(0) | (boolean)toParse.get(2)) && (!(boolean)toParse.get(2) | (boolean)toParse.get(0)));
            toParse.remove(2);
            toParse.remove(0);
            return calculate(toParse);
        }
        return false;
    }
}
