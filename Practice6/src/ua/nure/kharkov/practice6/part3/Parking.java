package ua.nure.kharkov.practice6.part3;

import java.util.Arrays;

public class Parking {
	private Object[] array;

	Parking(int size) {
		array = new Object[size];
	}

	public void add(Object obj) {
		boolean flag = true;
		for (int i = 0; i < array.length; i++) {
			if (obj.equals(array[i])) {
				System.out.println("Такой автомобиль уже есть ");
				flag = false;
			}
		}
		if (flag) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					array[i] = obj;
					break;
				}
			}
		}
	}

	public void remove(Object obj) {
		for (int i = 0; i < array.length; i++) {
			if (obj.equals(array[i])) {
				array[i] = null;
				break;
			}
		}

	}

	public Object[] toArray() {
		return Arrays.copyOf(array, array.length);
	}

	public void printParking() {
		for (int i = 0; i < toArray().length; i++) {
			if (toArray()[i] == null) {
				System.out.print("| |");
			} else {
				System.out.print("| " + toArray()[i] + " |");
			}
		}
	}
}
