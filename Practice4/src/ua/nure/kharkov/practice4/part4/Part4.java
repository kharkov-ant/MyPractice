package ua.nure.kharkov.practice4.part4;

public class Part4 {
	public static void main(String[] args) {
		Parser parser = new Parser("part4.txt");
		for (String str : parser) {
			System.out.println(str);
		}
	}
}
