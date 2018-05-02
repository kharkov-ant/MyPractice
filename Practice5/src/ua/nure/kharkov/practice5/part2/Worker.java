package ua.nure.kharkov.practice5.part2;

public class Worker extends Thread {
	private String message;
	private int time;

	Worker(String message, int time) {
		this.message = message;
		this.time = time;
	}

	public void run() {
		while (true) {
			System.out.println(message);
			try {
				sleep(time);
			} catch (InterruptedException e) {
				// e.printStackTrace();
				break;
			}
		}
	}
}
