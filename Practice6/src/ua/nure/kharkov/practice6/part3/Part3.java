package ua.nure.kharkov.practice6.part3;

public class Part3 {
	public static void main(String[] args) {
		Parking parking = new Parking(10);
		parking.add("Audi");
		parking.add("Lanos");
		parking.add("Reno");
		parking.add("BMW");
		parking.printParking();
		System.out.println();
		parking.remove("Lanos");
		parking.printParking();
		System.out.println();
		parking.add("Honda");
		parking.printParking();

	}
}
