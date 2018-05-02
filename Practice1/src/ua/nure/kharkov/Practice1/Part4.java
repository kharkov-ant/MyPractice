package ua.nure.kharkov.Practice1;

public class Part4 {
	public static void main(String[] args) {
		String a = args[0], b = args[1];
		int a1, b1;
		a1 = Integer.parseInt(a);
		b1 = Integer.parseInt(b);
		while (b1 != 0) {
			int c = a1 % b1;
			a1 = b1;
			b1 = c;
		}
		System.out.println(a1);
	}
}
