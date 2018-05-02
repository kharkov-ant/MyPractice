package ua.nure.kharkov.practice5.part3;

public class Part3 {
	public static void main(String[] args) {
		// Thread t1 = new MyThread1();
		// Thread t2 = new MyThread2();
		// t1.start();
		// t2.start();
		Counters c = new Counters();
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					for (int i = 0; i < 3; i++) {
						if (c.getCount1() == c.getCount2()) {
							System.out.println("count1 = count2");
						} else if (c.getCount1() > c.getCount2()) {
							System.out.println("count1 > count2");
						} else {
							System.out.println("count1 < count2");
						}
						c.setCount1(c.getCount1() + 1);
						try {
							sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						c.setCount2(c.getCount2() + 1);
					}
				}
			}.start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");

		Counters c2 = new Counters();

		synchronized ("literal") {
			for (int i = 0; i < 5; i++) {
				new Thread() {
					public void run() {
						for (int i = 0; i < 3; i++) {
							if (c2.getCount1() == c2.getCount2()) {
								System.out.println("count1 = count2");
							} else if (c2.getCount1() > c2.getCount2()) {
								System.out.println("count1 > count2");
							} else {
								System.out.println("count1 < count2");
							}
							c2.setCount1(c2.getCount1() + 1);
							try {
								sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							c2.setCount2(c2.getCount2() + 1);
						}
					}
				}.start();
			}
		}
	}
}

class Counters {
	private int count1 = 0;
	private int count2 = 0;

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}
}
