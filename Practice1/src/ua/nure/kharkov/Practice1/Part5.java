package ua.nure.kharkov.Practice1;

public class Part5 {
	public static void main(String[] args) {
		String str = args[0];
		int a, d, x, sum = 0;
		a = Integer.parseInt(str);
		for (int i = 0; i < str.length(); i++) {
			d = a / 10;
			x = a % 10;
			a = d;
			sum += x;
		}
		System.out.println(sum);
	}
}
