package ua.nure.kharkov.practice3.part3;

import ua.nure.kharkov.practice3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.println(upper(input));;
        System.out.println();
    }
    public static String upper(String input){
        StringBuilder newstr = new StringBuilder(input);
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(input);
        while (m.find()) {
            newstr.replace(m.start(), m.start()+1, m.group().substring(0, 1)
                    .toUpperCase());
        }
        return newstr.toString();
    }
}
