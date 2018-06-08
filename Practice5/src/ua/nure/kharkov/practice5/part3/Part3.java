package ua.nure.kharkov.practice5.part3;

public class Part3 {
	public static void main(String[] args) throws InterruptedException {
		Counters counter = new Counters();
		Counters counter2 = new Counters();
		for (int i = 0; i < 4; i++) {
			new MyThread(counter).start();
		}
		Thread.sleep(400);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		for (int i = 0; i < 4; i++) {
			new MyThread2(counter2).start();
		}
	}
}

class MyThread extends Thread {
	Counters counter;

	MyThread(Counters counter) {
		this.counter = counter;
	}

	public void run() {
		synchronized (counter) {
			if (counter.count1 == counter.count2) {
				System.out.println("count1 = count2");
			} else if (counter.count1 > counter.count2) {
				System.out.println("count1 > count2");
			} else {
				System.out.println("count1 < count2");
			}
			counter.stepCount1();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter.stepCount2();
		}
	}
}

class MyThread2 extends Thread {
	Counters counter;

	MyThread2(Counters counter) {
		this.counter = counter;
	}

	public void run() {
		if (counter.count1 == counter.count2) {
			System.out.println("count1 = count2");
		} else if (counter.count1 > counter.count2) {
			System.out.println("count1 > count2");
		} else {
			System.out.println("count1 < count2");
		}
		counter.stepCount1();
		try {
			sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		counter.stepCount2();
	}
}

class Counters {
	int count1 = 0;
	int count2 = 0;

	public void stepCount1() {
		int c = this.count1;
		c++;
		this.count1 = c;
	}

	public void stepCount2() {
		int c = this.count2;
		c++;
		this.count2 = c;
	}
}
