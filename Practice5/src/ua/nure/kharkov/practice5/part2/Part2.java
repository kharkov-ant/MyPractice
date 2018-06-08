package ua.nure.kharkov.practice5.part2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

public class Part2 {
	public static void main(String[] args) throws InterruptedException {
		InputStream STD_IN = System.in;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(
					System.lineSeparator().getBytes("Windows-1251"));
			
			Spam.main(args);
			System.setIn(bais);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			System.setIn(STD_IN);
		}

	}
}
