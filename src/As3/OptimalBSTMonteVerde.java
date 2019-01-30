package As3;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class OptimalBSTMonteVerde {

	double[][] cost;
	int[] keys;
	double[] prob;
	Scanner scan;
	int numKeys;

	public OptimalBSTMonteVerde(File inputFile) throws Exception {
		readBST(inputFile);
		makeAndPrintBST();
		System.out.println("Calculated/final cost and root matrix:");
		printFinal();
	}

	public void readBST(File inputFile) throws Exception {

		try {
			scan = new Scanner(inputFile);
		} catch (Exception e) {
			System.out.println("Failed due to " + e);
			System.exit(1);
		}

		numKeys = Integer.parseInt(scan.next());
		keys = new int[numKeys];
		prob = new double[numKeys];
		cost = new double[numKeys + 2][numKeys + 1];

		// read keys into keys[] array
		for (int i = 0; i < numKeys; i++)
			keys[i] = Integer.parseInt(scan.next());

		// read probabilities/frequenices into prob[] array
		for (int i = 0; i < numKeys; i++)
			prob[i] = Double.parseDouble(scan.next());

		// initialize C[i,i] as supplied probabilities/frequencies
		for (int i = 1; i <= numKeys; i++) {
			cost[i][i] = prob[i - 1];
		}

		// TEST CALLS
		System.out.println("Key array:");
		System.out.println(Arrays.toString(keys).replace(", ", "\t").replace("[", "").replace("]", "") + "\n");

		System.out.println("Probability array:");
		System.out.println(Arrays.toString(prob).replace(", ", "\t").replace("[", "").replace("]", "") + "\n");

		System.out.println("Initial cost 2D array before calculations/trimming:");

		System.out.print(" \t0\t");
		for (int i = 1; i <= numKeys; i++)
			System.out.print("" + i + "\t");
		System.out.println("");

		for (int i = 0; i <= numKeys + 1; i++) {
			System.out.print(" " + i + "\t");
			System.out.println(Arrays.toString(cost[i]).replace(", ", "\t").replace("[", "").replace("]", ""));
		}
		System.out.println("\n");

	}

	public void makeAndPrintBST() {

		double minK;
		for (int dif = 1; dif < numKeys; dif++) {
			for (int i = 1; i <= (numKeys - dif); i++) {
				int j = i + dif;
				minK = 0;
				double tempMin = Integer.MAX_VALUE;
				for (int k = i; k <= j; k++) {
					double c = cost[i][k - 1] + cost[k + 1][j];
					if (tempMin > c) {
						tempMin = c;
						minK = k;
					}
				}

				cost[i][j] = tempMin + (Arrays.stream(prob, i - 1, j).sum());
				cost[j][i - 1] = minK;

			}
		}

	}

	public void printFinal() {
		double[][] finalArray = cost;

		// print out indices
		System.out.print(" \t0\t");
		for (int i = 1; i <= numKeys; i++)
			System.out.print("" + i + "\t");
		System.out.println("");

		// print out array, skip the first and last rows of empty zeroes
		for (int i = 1; i <= numKeys; i++) {
			System.out.print(" " + i + "\t");
			System.out.println(Arrays.toString(finalArray[i]).replace(", ", "\t").replace("[", "").replace("]", ""));

		}
	}
}