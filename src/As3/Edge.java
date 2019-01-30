package As3;

public class Edge implements Comparable<Edge> {

	final int x;
	final int y;
	final int weight;

	public Edge(int x, int y, int weight) {
		if (x < 0)
			throw new IllegalArgumentException("Must be non-negative int");
		if (y < 0)
			throw new IllegalArgumentException("Must be non-negative int");

		this.x = x;
		this.y = y;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public int getVertexOne() {
		return this.x;
	}

	public int getVertexTwo() {
		return this.y;
	}

	public int compareTo(Edge that) {
		return Integer.compare(this.weight, that.weight);
	}

	public String toString() {
		return String.format("Edge: %d - %d / Weight: %d\n", x, y, weight);
	}
}
