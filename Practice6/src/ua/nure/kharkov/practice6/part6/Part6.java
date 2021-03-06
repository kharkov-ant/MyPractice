
package ua.nure.kharkov.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private String text;

	private Map<String, Integer> map = new HashMap<String, Integer>();

	private SortByValue sbv = new SortByValue(map);

	private Map<String, Integer> map2 = new TreeMap<String, Integer>(sbv);

	private String[] arr;

	public final void arrInitialize() {

		try {
			StringBuilder sb = new StringBuilder();
			Scanner sc = new Scanner(new File(getFilename()), "Windows-1251");
			while (sc.hasNext()) {
				sb.append(sc.next() + " ");
			}
			text = sb.toString();
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + getFilename() + " not found");
		}
		arr = initialize();

	}

	public final String[] initialize() {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile("[A-za-zÀ-ßà-ÿ]+");
		Matcher m = p.matcher(text);
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		return sb.toString().split(" ");

	}

	public void frequency() {
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				int local = map.get(arr[i]) + 1;
				map.put(arr[i], local);
				continue;
			}
			map.put(arr[i], 1);
		}
		map2.putAll(map);
	}

	public void length() {

		for (int i = 0; i < arr.length; i++) {

			if (map.containsKey(arr[i])) {
				continue;
			}
			map.put(arr[i], arr[i].length());
		}
		map2.putAll(map);

	}

	public String inverseString(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(String.valueOf(str.charAt(i)).toUpperCase());

		}
		return sb.toString();
	}

	public String inverse() {
		int counter = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; counter < 3 && i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				sb.append(inverseString(arr[i]) + System.lineSeparator());
				counter++;
				continue;
			}
			map.put(arr[i], arr[i].length());
		}
		return sb.toString();
	}

	public void output() {
		int i = 0;
		for (Map.Entry<String, Integer> entry : map2.entrySet()) {

			if (i == 3) {
				break;
			}

			System.out.println(entry.getKey() + " " + entry.getValue());
			i++;
		}
	}

	public boolean consoleOutput(String str, String filename, String str2,
			String operation) {
		if (!(str.equals("--input") || str.equals("-i"))) {
			System.err.println("Wrong operation");
			return false;
		}
		setFilename(filename);
		if (!(str2.equals("--task") || str2.equals("-t"))) {
			System.err.println("Wrong task");
			return false;
		}
		if (operation.equals("frequency")) {
			this.arrInitialize();
			this.frequency();
			this.output();
			return true;
		} else if (operation.equals("length")) {
			this.arrInitialize();
			this.length();
			this.output();
			return true;
		} else if (operation.equals("duplicates")) {
			this.arrInitialize();
			System.out.println(this.inverse());
		}
		return true;
	}

	public static void main(String[] args) {
		Part6 part6 = new Part6();
		part6.consoleOutput(args[0], args[1], args[2], args[3]);
	}

	private static class SortByValue implements Comparator<String>,
			Serializable {

		private static final long serialVersionUID = -1845240008206825790L;

		public SortByValue(Map<String, Integer> map) {
			this.map = map;
		}

		private Map<String, Integer> map;

		@Override
		public int compare(String o1, String o2) {
			if (map.get(o1) >= map.get(o2)) {
				return -1;
			} else {
				return 1;
			}
		}

	}
}
