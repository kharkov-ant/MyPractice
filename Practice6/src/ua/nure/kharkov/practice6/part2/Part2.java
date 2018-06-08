package ua.nure.kharkov.practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
	private List<Integer> list1;
	private List<Integer> list2;

	public void init() {
		list1 = new ArrayList<Integer>();
		list2 = new LinkedList<Integer>();
		for (int i = 0; i < 10000; i++) {
			list1.add(i);
			list2.add(i);
		}
	}

	public void remove(List<Integer> list, int k) {
		long time = System.currentTimeMillis();
		int tmp = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.size() == 1) {
				break;
			}
			tmp += (k - 1);
			while (tmp >= list.size()) {
				tmp = tmp - list.size();
			}
			list.remove(tmp);
		}
		System.out.println(System.currentTimeMillis() - time);
	}

	public static void main(String[] args) {
		Part2 part2 = new Part2();
		part2.init();
		part2.remove(part2.list1, 3);
		part2.remove(part2.list2, 3);
	}

}
	