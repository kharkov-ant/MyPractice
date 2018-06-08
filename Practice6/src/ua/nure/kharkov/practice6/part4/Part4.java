package ua.nure.kharkov.practice6.part4;

public class Part4 {
	public static void main(String[] args) {
		Graph graph = new Graph(10);
        System.out.println(graph.add(5,7));
        System.out.println(graph.add(7,5));
        System.out.println(graph.remove(7,5));
    }
}
