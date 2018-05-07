package ua.nure.kharkov.SummaryTask3.entity;

import java.util.ArrayList;
import java.util.List;

public class OldCards {
	private List<OldCard> oldcards;

	public List<OldCard> getQuestions() {
		if (oldcards == null) {
			oldcards = new ArrayList<OldCard>();
		}
		return oldcards;
	}

	@Override
	public String toString() {
		if (oldcards == null || oldcards.size() == 0) {
			return "OldCards contains no cards";
		}
		StringBuilder result = new StringBuilder();
		for (OldCard oldcard : oldcards) {
			result.append(oldcard).append('\n');
		}
		return result.toString();
	}
}
