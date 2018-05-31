package ua.nure.kharkov.practice5.part1;

public class Part1 {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new FirstThread();
        t1.start();
        t1.join();
        Thread t = new Thread(new SecondThread());
        t.start();
        
	}
}

class FirstThread extends Thread {
	public void run() {
		for (int i = 0; i < 4; i++) {
			System.out.println(getName());
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondThread implements Runnable {
	public void run() {
		for (int i = 0; i < 4; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
