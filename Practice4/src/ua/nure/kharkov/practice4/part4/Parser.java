package ua.nure.kharkov.practice4.part4;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.kharkov.practice4.Util;

public class Parser implements Iterable<String> {
	private String input;

	public Parser(final String name) {
		input = Util.getInput(name);
	}

	@Override
	public Iterator<String> iterator() {
		return new IteratorI();
	}

	private class IteratorI implements Iterator<String> {

		private static final String REGEX = "([^.!?]+[.!?])";
		private Matcher m;
		private Pattern p;
		private boolean hasnext;

		{
			p = Pattern.compile(REGEX);
			m = p.matcher(input);
		}
		
		public boolean hasNext() {
			if (m.find()) {
				hasnext = true;
			} else {
				hasnext=false;
			}
			return hasnext;
		}

		@Override
		public String next() {
			String result="";
			if (hasnext) {
				result = m.group().trim();
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
