package ua.nure.kharkov.practice4.part5;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
	public static void main(String[] args) {
		String tmp;
		String[] properties;
		ResourceBundle rs;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			tmp = scanner.nextLine();
			if (tmp.equals("stop")) {
				break;
			} else {
				properties = tmp.split(" ");
				rs = ResourceBundle.getBundle("resources", new Locale(properties[1]));
				System.out.println(rs.getString(properties[0]));
			}
		}
		scanner.close();
	}
}
