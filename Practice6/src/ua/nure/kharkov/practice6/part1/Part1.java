package ua.nure.kharkov.practice6.part1;

import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	private WordContainer c = new WordContainer();
	StringBuilder str = new StringBuilder();

	public void read() {
		String tmp;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			tmp = scanner.nextLine();
			if (tmp.equals("stop")) {
				break;
			} else {
				str.append(tmp);
				str.append(System.lineSeparator());
			}
		}
		Pattern p = Pattern.compile("\\w+");
		Matcher m = p.matcher(str.toString());
		while(m.find()) {																																													
			c.add(new Word(m.group()));
		}
		scanner.close();
	}

	public void write() {
		Collections.sort(c.getWordContainer(), new WordCompare());
		Collections.sort(c.getWordContainer(), new FrequencyCompare());
		for (Word el : c.getWordContainer()) {
			System.out.print(el.getWord() + ":");
			System.out.print(el.getFrequency());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Part1 part = new Part1();
		part.read();
		part.write();
	}
}
