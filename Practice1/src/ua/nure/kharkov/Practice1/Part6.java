package ua.nure.kharkov.Practice1;

public class Part6 {
	public static void main(String[] args) {
		String str = args[0];
		int numb = 2, i = 0, k = 0, n1;
		n1 = Integer.parseInt(str);
		int[] arr = new int[n1];
		while (k < arr.length) {
			if (check(numb)) {
				arr[i] = numb;
				k++;
				i++;
			}
			numb++;
		}
		for (int ar : arr) {
			System.out.print(ar + " ");
		}
		System.out.println();
	}

	public static boolean check(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++)
			if (n % i == 0)
				return false;
		return true;
	}
}
