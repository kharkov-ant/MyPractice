package ua.nure.kharkov.practice3.part5;

public class Part5 {
	public static void main(String[] args) {
		for (int i = 1; i <= 20; i++) {
			System.out.println(i + " ====> " + decimal2Roman(i) +
					" ====> " + roman2Decimal(decimal2Roman(i)));
		}
		System.out.println("...");
		for (int i = 94; i <= 100; i++) {
			System.out.println(i + " ====> " + decimal2Roman(i) +
					" ====> " + roman2Decimal(decimal2Roman(i)));
		}
	}

	public static String decimal2Roman(int x) {
		String[] rom = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C" };
		int[] value = { 1, 4, 5, 9, 10, 40, 50, 90, 100 };
		StringBuilder str = new StringBuilder();
		int i = value.length - 1;
		while (x > 0) {
			while (value[i] > x) {
				i--;
			}
			str.append(rom[i]);
			x -= value[i];
		}
		return str.toString();
	}

	public static int roman2Decimal(String s) {
		char[] rom = { 'I', 'V', 'X', 'L', 'C' };
		int[] value = { 1, 5, 10, 50, 100 };
		int result = 0;
		for (int j = 0; j < rom.length; j++) {
			if (s.charAt(s.length() - 1) == rom[j]) {
				result += value[j];
				break;
			}
		}
		int tmp = result;
		if (s.length() > 1) {
			for (int i = s.length() - 2; i >= 0; i--) {
				for (int j = 0; j < rom.length - 1; j++) {
					if (s.charAt(i) == rom[j]) {
						if (value[j] < tmp) {
							result -= value[j];
							tmp = value[j];
						} else {
							result += value[j];
						}
						break;
					}
				}
			}
			if (s.charAt(0) == 'X' && s.charAt(1) == 'L' && result > 60) {
				result -= 20;
			}
			if (s.charAt(0) == 'X' && s.charAt(1) == 'C' && result < 90) {
				result += 80;
			}
		}
		return result;
	}
}
