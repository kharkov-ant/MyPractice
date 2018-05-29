package test.ua.nure.kharkov.practice4;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.kharkov.practice4.part1.Part1;

public class Part1Test {

	@Test
	public void testUpper() {
		assertEquals("HELLO WORLD hi",Part1.upper("Hello world hi"));
	}

}
