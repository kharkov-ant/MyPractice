package ua.nure.kharkov.practice4.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception {
		writeFile();
		sortWriteNumbers();
		toConsole();
	}

	public static int[] generate() {
		int arr[] = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 50);
		}
		return arr;
	}

	public static void writeFile() throws Exception {
		StringBuilder newstr = new StringBuilder();
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("part2.txt"), "Windows-1251");
		int[] arr = generate();
		for (int a : arr) {
			newstr.append(a).append(" ");
		}
		out.write(newstr.toString());
		out.close();
	}

	public static String readFile() throws FileNotFoundException {
		Scanner s = new Scanner(new File("part2.txt"), "Windows-1251");
		StringBuilder content = new StringBuilder();
		while (s.hasNextLine()) {
			content.append(s.nextLine());
		}
		s.close();
		return content.toString();
	}

	public static void sortWriteNumbers() throws Exception {
		StringBuilder newstr = new StringBuilder();
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("part2_sorted.txt"), "Windows-1251");
		String[] arr = readFile().split(" ");
		int[] newarr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			newarr[i] = Integer.valueOf(arr[i]);
		}
		newarr = sort(newarr);
		for (int a : newarr) {
			newstr.append(a).append(" ");
		}
		out.write(newstr.toString());
		out.close();
	}

	public static int[] sort(int[] arr) {
		boolean flag = false;
        int t;
        while(!flag) {
            flag = true;
            for (int i = 0; i < arr.length-1; i++) {
                if(arr[i] > arr[i+1]){
                    flag = false;
                    t = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = t;
                }
            }
        }
		return arr;
	}

	public static void toConsole() throws FileNotFoundException {
		Scanner s = new Scanner(new File("part2.txt"), "UTF-8");
		StringBuilder content = new StringBuilder();
		while (s.hasNextLine()) {
			content.append("input ==> ").append(s.nextLine()).append(" ");
		}
		Scanner s1 = new Scanner(new File("part2_sorted.txt"), "UTF-8");
		StringBuilder content1 = new StringBuilder();
		while (s1.hasNextLine()) {
			content1.append("output ==> ").append(s1.nextLine()).append(" ");
		}
		s.close();
		s1.close();
		System.out.println(content.toString());
		System.out.println(content1.toString());
	}
}
