package ua.nure.kharkov.practice6.part1;

import java.util.Comparator;

public class Word {
	private String word;

	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
class FrequencyCompare implements Comparator<Word> {
	@Override
	public int compare(Word o1, Word o2) {
		return -(o1.getFrequency() - o2.getFrequency());
	}
}

class WordCompare implements Comparator<Word> {
	@Override
	public int compare(Word o1, Word o2) {
		return o1.getWord().compareTo(o2.getWord());
	}
}
