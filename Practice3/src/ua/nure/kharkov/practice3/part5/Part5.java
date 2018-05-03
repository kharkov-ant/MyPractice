package ua.nure.kharkov.practice3.part5;

public class Part5 {
	public static void main(String[] args) {
		System.out.println(decimal2Roman(54));
	}
	
	public static String decimal2Roman(int x) {
		char[] rom   = { 'I','V','X','L', 'C'};
		int[]  value = {  1,  5,  10,  50,  100};
		String romnumb = "";
		for (int i=rom.length-1;i>=0;i--) {
			while (x>=value[i]) {
				if(x>=value[i]*4) {
					x-=value[i]*4;
					romnumb+=rom[i];
					romnumb+=rom[i+1];
				}else {
					x-=value[i];
					romnumb+=rom[i];
				}
			}
		}
		return romnumb;
	}
	
	public static int roman2Decimal(String s) {
		return 2;
	}
}
