package ua.nure.kharkov.practice5.part2;

import java.util.Scanner;

public class Spam {
	private String[] messages;
	private int[] times;
	Thread[] threads2;

	Spam(String[] messages, int[] times) {
		this.messages = messages;
		this.times = times;
	}

	public void start() {
		Thread[] threads = new Thread[times.length];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Worker(messages[i], times[i]);
			threads[i].start();
			threads2 = threads;
		}
	}

	public void stop() {
		for (int i = 0; i < threads2.length; i++) {
			threads2[i].interrupt();
		}
	}

	public static void main(String[] args) {
		String[] messages = new String[] { "@@@", "bbbbbbb" };
		int[] times = new int[] { 333, 222 };
		Spam s = new Spam(messages, times);
		s.start();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			if (scanner.nextLine().isEmpty()) {
				s.stop();
			}
		}
		scanner.close();
	}
}
