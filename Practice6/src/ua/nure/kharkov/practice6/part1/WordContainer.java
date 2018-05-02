package ua.nure.kharkov.practice6.part1;

import java.util.ArrayList;
import java.util.List;

//you can extend this class from one of the core container
//or aggregate it inside of class 
public class WordContainer {
	private List<Word> list = new ArrayList<>();

	public void add(Word word) {
		boolean flag = true;
		for (Word w : list) {
			if (w.getWord().equals(word.getWord())) {
				w.setFrequency(w.getFrequency() + 1);
				flag = false;
			}
		}
		if (flag) {
			list.add(word);
		}
	}

	public List<Word> getWordContainer() {
		return list;
	}
}
