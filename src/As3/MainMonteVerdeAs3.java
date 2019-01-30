package As3;

import java.io.File;

public class MainMonteVerdeAs3 {

	public static void main(String args[]) {

		/*
		 * PART 2 - Minimum Spanning Tree
		 */

		try {
			if (args.length > 0) {
				System.out.println("Processing file\n");
				System.out.println("----- PART 2: MINIMUM SPANNING TREE -----\n");
				new KruskalsMSTMonteVerde(new File(args[0]));

			} else {
				System.out.println("No file enteredddd.");
				System.exit(1);
			}

		} catch (Exception e) {
			System.out.println("Fail" + e);
			e.printStackTrace();

			System.exit(1);
		}

		/*
		 * PART 3 - Binary Search Tree
		 */

		try {
			if (args.length > 0) {
				System.out.println("----- PART 3: OPTIMAL BINARY SEARCH TREE -----\n");
				System.out.println("Finding optimal binary search tree:");
				new OptimalBSTMonteVerde(new File(args[1]));

			} else {
				System.out.println("No file entered.");
				System.exit(1);
			}

		} catch (Exception e) {
			System.out.println("Fail" + e);
			e.printStackTrace();
			System.exit(1);
		}

	}

}
