package ua.nure.kharkov.Practice1;

public class Part7 {
	public static void main(String[] args) {
		String number1 = args[0], number2 = args[1], number3 = args[2], number4 = args[3], number5 = args[4],
				number6 = args[5], number7 = args[6], number8 = args[7];
		System.out.println(number1 + " ==> " + Part7.chars2digits(number1) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number1)));
		System.out.println(number2 + " ==> " + Part7.chars2digits(number2) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number2)));
		System.out.println(number3 + " ==> " + Part7.chars2digits(number3) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number3)));
		System.out.println(number4 + " ==> " + Part7.chars2digits(number4) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number4)));
		System.out.println(number5 + " ==> " + Part7.chars2digits(number5) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number5)));
		System.out.println(number6 + " ==> " + Part7.chars2digits(number6) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number6)));
		System.out.println(number7 + " ==> " + Part7.chars2digits(number7) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number7)));
		System.out.println(number8 + " ==> " + Part7.chars2digits(number8) + " ==> "
				+ Part7.digits2chars(Part7.chars2digits(number8)));
	}

	public static int chars2digits(String number) {
		int rezult = 0;
		for (int i = 0; i <= number.length(); i++) {
			if (i == number.length() - 1) {
				rezult = (number.charAt(i) - 64);
				break;
			}
			if ((i + 1) <= number.length() - 1) {
				rezult = 26 * (rezult + (number.charAt(i) - 64));
			}
			if ((i + 1) == number.length() - 1) {
				rezult = rezult + (number.charAt(i + 1) - 64);
				break;
			}
		}
		// 26*arb[0]+arb[1];
		// 26*(26*arb[0]+arb[1])+arb[2];
		// 26*((26*(26*arb[0]+arb[1])+arb[2])+arb[3])
		return rezult;
	}

	public static String digits2chars(int number) {
		String str = "";
		char c1 = 'q', c2 = 'q';
		int a;
		if (number > 26) {
			while (number > 26) {
				if (number % 26 == 0) {
					a = (number - 1) / 26;
					c1 = 'Z';
				} else {
					a = number / 26;
					c1 = (char) (number % 26 + 64);
				}
				number = a;
				str = c1 + str;
			}
			c2 = (char) (number + 64);
		} else {
			c2 = (char) (number + 64);
		}
		return c2 + str;
	}

	public static String rightColumn(String number) {
		String right = "";
		int left;
		left = chars2digits(number);
		left++;
		right = digits2chars(left);
		return right;
	}
}
