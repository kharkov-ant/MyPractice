package ua.nure.khrakov.practice5.part4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class GenerateMatrix {
	public static void main(String[] args) {
		 writeFile();
	}
	public static int[][] generate() {
		int arr[][] = new int[4][100];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 100; j++) {
				arr[i][j] = (int) (Math.random() * (1000 - 100) + 100);
			}
		}
		return arr;
	}
	
	public static void writeFile() {
		StringBuilder newstr = new StringBuilder();
		OutputStreamWriter out;
		int[][] arr = generate();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 100; j++) {
				newstr.append(arr[i][j]).append(" ");
			}
			newstr.append(System.lineSeparator());
		}
		try {
			out = new OutputStreamWriter(new FileOutputStream("part4.txt"), "Windows-1251");
			try {
				out.write(newstr.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
