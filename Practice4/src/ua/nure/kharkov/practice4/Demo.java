package ua.nure.kharkov.practice4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import ua.nure.kharkov.practice4.part1.Part1;
import ua.nure.kharkov.practice4.part2.Part2;
import ua.nure.kharkov.practice4.part3.Part3;
import ua.nure.kharkov.practice4.part4.Part4;
import ua.nure.kharkov.practice4.part5.Part5;

public class Demo {
	private static final InputStream STD_IN = System.in;
	private static final String ENCODING = "Windows-1251";

	public static void main(String[] args) throws Exception {
		System.out.println("=========================== PART1");
		Part1.main(args);

		System.out.println("=========================== PART2");
		Part2.main(args);

		System.out.println("=========================== PART3");
		// set the mock input
		System.setIn(new ByteArrayInputStream(
				"char^String^int^double^stop".replace("^", System.lineSeparator()).getBytes(ENCODING)));
		Part3.main(args);
		// restore the standard input
		System.setIn(STD_IN);

		System.out.println("=========================== PART4");
		Part4.main(args);

		System.out.println("=========================== PART5");
		// set the mock input
		System.setIn(new ByteArrayInputStream(
				"table ru^table en^apple ru^stop".replace("^", System.lineSeparator()).getBytes(ENCODING)));
		Part5.main(args);
		// restore the standard input
		System.setIn(STD_IN);

	}
}
