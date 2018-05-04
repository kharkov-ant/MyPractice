package ua.nure.kharkov.practice4.part3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.kharkov.practice4.Util;

public class Part3 {
	public static void main(String[] args) {
		String tmp;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			tmp = scanner.nextLine();
			if (tmp.equals("stop")) {
				break;
			} else {
				System.out.println(findTypes(tmp));
			}
		}
		scanner.close();
	}

	public static String findTypes(String type) {
		String input = Util.getInput("part3.txt");
		Map<String, String> REGEX = new HashMap<String, String>();
		REGEX.put("String", "[A-Za-zа-яёА-ЯЁ]{2,}");
		REGEX.put("double", "[-+]?\\d*\\.\\d+");
		REGEX.put("char", "[A-Za-zа-яёА-ЯЁ]+");
		REGEX.put("int", "(?<=\\s|^)\\d+(?=\\s|$)");
		StringBuilder str = new StringBuilder();
		if (type.equals("char")) {
			Pattern p = Pattern.compile(REGEX.get(type));
			Matcher m = p.matcher(input);
			while (m.find()) {
				if (m.group().length() == 1) {
					str.append(m.group());
					str.append(" ");
				}
			}
		} else {
			Pattern p = Pattern.compile(REGEX.get(type));
			Matcher m = p.matcher(input);
			while (m.find()) {
				str.append(m.group());
				str.append(" ");
			}
		}
		return str.toString();
	}
}
