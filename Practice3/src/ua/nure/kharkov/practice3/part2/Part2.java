package ua.nure.kharkov.practice3.part2;

import ua.nure.kharkov.practice3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(min(input));
        System.out.println(max(input));
        System.out.println();
    }
    public static String min(String input){
        StringBuilder newstr = new StringBuilder("Min: ");
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(input);
        int count = Integer.MAX_VALUE;
        while (m.find()){
            if (m.group().length() < count){
                count = m.group().length();
            }
        }
        m = p.matcher(input);
        while (m.find()){
            Pattern p1 = Pattern.compile(m.group());
            Matcher m1 = p1.matcher(newstr);
            if (m.group().length() == count){
                if (!m1.find()){
                    newstr.append(m.group()).append(", ");
                }
            }
        }
        newstr.delete(newstr.length()-2,newstr.length());
        return newstr.toString();
    }


    public static String max(String input){
        StringBuilder newstr = new StringBuilder("Max: ");
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(input);
        int count = Integer.MIN_VALUE;
        while (m.find()){
            if (m.group().length() > count){
                count = m.group().length();
            }
        }
        m = p.matcher(input);
        while (m.find()){
            Pattern p1 = Pattern.compile(m.group());
            Matcher m1 = p1.matcher(newstr);
            if (m.group().length() == count){
                if (!m1.find()){
                    newstr.append(m.group()).append(", ");
                }
            }
        }
        newstr.delete(newstr.length()-2,newstr.length());
        return newstr.toString();
    }
}
