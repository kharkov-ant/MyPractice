package ua.nure.kharkov.practice3.part1;
import ua.nure.kharkov.practice3.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part1 {
    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(convertN1(input));
        System.out.println(convertN2(input));
        System.out.println(convertN3(input));
        System.out.println(convertN4(input));
    }

    public static String convertN1(String input) {
        StringBuilder newstr = new StringBuilder();
//        ArrayList<String> list = new ArrayList<>();
//        for (String str : input.split(System.lineSeparator())) {
//            list.add(str);
//        }
//        for (int i = 1; i < list.size(); i++) {
//            String[] str = list.get(i).split(";");
//            newstr.append(str[0])
//                    .append(" ==> ")
//                    .append(str[2])
//                    .append(System.lineSeparator());
//        }
        Pattern p = Pattern.compile("(.+)(;.+;)(.+@.+)");
        Matcher m = p.matcher(input);
        while(m.find()){
            newstr.append(m.group(1))
                    .append(" ==> ")
                    .append(m.group(3))
                    .append(System.lineSeparator());
        }
        return newstr.toString();
    }

    public static String convertN2(String input) {
        StringBuilder newstr = new StringBuilder();
//        ArrayList<String> list = new ArrayList<>();
//        for (String str : input.split(System.lineSeparator())) {
//            list.add(str);
//        }
//        for (int i = 1; i < list.size(); i++) {
//            String[] str = list.get(i).split(";");
//            newstr.append(str[1])
//                    .append(" (email: ")
//                    .append(str[2]).append(")")
//                    .append(System.lineSeparator());
//        }
        Pattern p = Pattern.compile("(.+;)(.+)(;)(.+@.+)");
        Matcher m = p.matcher(input);
        while(m.find()){
            newstr.append(m.group(2))
                    .append(" (email: ")
                    .append(m.group(4))
                    .append(")")
                    .append(System.lineSeparator());
        }
        return newstr.toString();
    }

    public static String convertN3(String input) {
        StringBuilder newstr = new StringBuilder();
        Pattern p = Pattern.compile("(.+)(;.+;)(.+)(@.+)");
        Matcher m = p.matcher(input);
        while(m.find()){
            Pattern p1 = Pattern.compile(m.group(4)+".+");
            Matcher m1 = p1.matcher(newstr);
            if (!(m1.find())) {
                newstr.append(m.group(4)).append(" ==> ").append(m.group(1)).append(System.lineSeparator());
            } else {
                Pattern p2 = Pattern.compile(m.group(4)+"( ==> .+)");
                Matcher m2 = p2.matcher(newstr);
                m2.find();
                newstr.insert(m2.end(),","+m.group(1));
            }
        }
        return newstr.toString();
    }

    public static String convertN4(String input) {
        StringBuilder newstr = new StringBuilder();
//        ArrayList<String> list = new ArrayList<>();
//        for (String str : input.split(System.lineSeparator())) {
//            list.add(str);
//        }
//        int a;
//        for (int i = 1; i < list.size(); i++) {
//            a = (int)(Math.random()*9000+1000);
//            newstr.append(list.get(i))
//                    .append(";")
//                    .append(a)
//                    .append(System.lineSeparator());
//        }
        Pattern p = Pattern.compile("(.+;.+;.+@.+)");
        Matcher m = p.matcher(input);
        while (m.find()){
            int a = (int)(Math.random()*9000+1000);
            newstr.append(m.group(1))
                    .append(";")
                    .append(a)
                    .append(System.lineSeparator());
        }
        return newstr.toString();
    }
}
