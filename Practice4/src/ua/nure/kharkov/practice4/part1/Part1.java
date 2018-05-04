		package ua.nure.kharkov.practice4.part1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.kharkov.practice4.Util;

public class Part1 {
	public static void main(String[] args) {
	    String input = Util.getInput("part1.txt");
	    System.out.println(upper(input));
	}
	public static String upper(String input) {
		StringBuilder newstr = new StringBuilder(input);
		Pattern p = Pattern.compile("[A-Za-zа-яёА-ЯЁ]{4,}");
		Matcher m = p.matcher(input);
		while(m.find()) {																																													
			newstr.replace(m.start(),m.end(),m.group().toUpperCase());
		}
		return newstr.toString();
	}
}
