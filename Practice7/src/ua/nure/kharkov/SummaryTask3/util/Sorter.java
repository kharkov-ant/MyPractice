package ua.nure.kharkov.SummaryTask3.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.kharkov.SummaryTask3.entity.OldCard;
import ua.nure.kharkov.SummaryTask3.entity.OldCards;

public class Sorter {
	
	public static final Comparator<OldCard> SORT_OLDCARD_BY_COUNTRY = new Comparator<OldCard>() {

		@Override
		public int compare(OldCard o1, OldCard o2) {
			return o1.getCountry().compareTo(o2.getCountry());
		}

	};
	
	public static final Comparator<OldCard> SORT_OLDCARD_BY_YEAR = new Comparator<OldCard>() {
		@Override
		public int compare(OldCard o1, OldCard o2) {
			return o1.getYear() - o2.getYear();
		}
	};
	
	public static final Comparator<OldCard> SORT_OLDCARD_BY_SEND = new Comparator<OldCard>() {
		@Override
		public int compare(OldCard o1, OldCard o2) {
			if (o1.isSend() && !o2.isSend()) {
				return -1;
			}
			if (o2.isSend() && !o1.isSend()) {
				return 1;
			}
			return 0;
		}
	};
	
	public static final void sortOldCardByCountry(OldCards oldcards) {
		Collections.sort(oldcards.getOldCards(), SORT_OLDCARD_BY_COUNTRY);
	}
	
	public static final void sortOldCardByYear(OldCards oldcards) {
		Collections.sort(oldcards.getOldCards(), SORT_OLDCARD_BY_YEAR);
	}
	
	public static final void sortOldCardBySend(OldCards oldcards) {
		Collections.sort(oldcards.getOldCards(), SORT_OLDCARD_BY_SEND);
	}

}
