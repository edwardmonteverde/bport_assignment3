package As3;

public class UnionFind {

	int[] rootArr; // root array, if rootArr[x] = x, x is a root node
	int size; // number of elements/vertices

	public UnionFind(int size) {

		if (size <= 0)
			throw new IllegalArgumentException("Size <= 0 is not allowed");

		this.size = size;
		rootArr = new int[size + 1];
	}

	public void makeSet() {

		for (int x = 1; x < size; x++) {
			rootArr[x] = -1; // Set array to its own index
		}
	}

	// Find which component/set a belongs to
	private int find(int a) {
		if (rootArr[a] < 0) {
			return a;
		}

		else {
			rootArr[a] = find(rootArr[a]);
			return rootArr[a];
		}
	}

	// Return whether or not the elements 'a' and
	// 'b' are in the same components/set.
	public boolean connected(int a, int b) {
		return find(a) == find(b);
	}

	// Return the number of elements in this UnionFind/Disjoint set
	public int size() {
		return size;
	}

	// Unify the components/sets containing elements 'a' and 'b'
	public void unify(int a, int b) {

		int root1 = find(a);
		int root2 = find(b);

		if (rootArr[root2] < rootArr[root1]) {
			rootArr[root1] = root2;
		} else {
			if (rootArr[root1] == rootArr[root2]) {
				rootArr[root1]--;
			}
			rootArr[root2] = root1;
		}

	}

}
