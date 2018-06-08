package ua.nure.kharkov.practice6.part4;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	private int k = 0;
	private Set<Edge> set = new HashSet<>();

	public Graph(int k) {
		this.k = k;
	}

	public boolean add(int from, int to) {
		if (from > k || to > k || from == 0 || to == 0) {
			throw new IllegalArgumentException();
		}
		Edge edge = new Edge(from, to);
		return set.add(edge);
	}

	public boolean remove(int from, int to) {
		if (from > k || to > k || from == 0 || to == 0) {
			throw new IllegalArgumentException();
		}
		Edge edge = new Edge(from, to);
		return set.remove(edge);
	}
}
class Edge {
    private int from;
    private int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }

        if ((from == ((Edge) o).from && to == ((Edge) o).to) || (to == ((Edge) o).from && from == ((Edge) o).to)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = from + to;
        result = 31 * result + result;
        return result;

    }
}
