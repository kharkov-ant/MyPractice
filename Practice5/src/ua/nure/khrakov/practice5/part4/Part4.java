package ua.nure.khrakov.practice5.part4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part4 {
	public static void main(String[] args) throws FileNotFoundException {
		oneThread();
		multiThread();
	}

	public static int[][] readFile() {
		Scanner s = null;
		try {
			s = new Scanner(new File("part4.txt"), "Windows-1251");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder content = new StringBuilder();
		while (s.hasNextLine()) {
			content.append(s.nextLine());
		}
		s.close();
		String[] array = content.toString().split(" ");
		int arr[][] = new int[4][100], k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 100; j++) {
				arr[i][j] = Integer.valueOf(array[k]);
				k++;
			}
		}
		return arr;
	}
	
	public static void oneThread() {
		int arr[][] = readFile();
		long time = System.currentTimeMillis();
		int max = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 100; j++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		System.out.println(max);
		System.out.println(System.currentTimeMillis() - time);
	}
	
	public static int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (arr[i] > max) {
				max = arr[i];
			}

		}
		return max;
	}
	public static void multiThread() {
		int arr[][] = readFile();
		long time = System.currentTimeMillis();
		int[] temp = new int[4];
		Thread[] myThread = new Thread[4];
		for (int i = 0; i < 4; i++) {
			final int count = i;
			myThread[count] = new Thread() {
				public void run() {
					temp[count] = max(arr[count]);
				};
			};
			myThread[count].start();
		}
		for (Thread array : myThread) {
			try {
				array.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int max = temp[0];
		for (int array : temp) {
			if (array > max){
				max = array;
			}
		}
		System.out.println(max);
		System.out.println(System.currentTimeMillis() - time);
	}
}