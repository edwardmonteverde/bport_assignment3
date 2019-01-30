package As3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KruskalsMSTMonteVerde {

	Scanner scan;
	Writer writer;
	int vert1, vert2, weight, numVals, totalWeight;
	UnionFind unionFind;
	File outFileName;

	PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
	LinkedList<Edge> list = new LinkedList<Edge>();

	public KruskalsMSTMonteVerde(File inputFile) throws Exception {

		findMST(inputFile);

		// This output is only for quick check purposes, actual output file is
		// still created
		System.out.println("Output of minimum spanning tree is as follows");
		System.out.println("(Note: only for quick check purposes, data is written to output file as well):\n");
		System.out.println(output(list));
		System.out.println("");

		// Write to actual output file
		makeOutFile("mstoutput.txt");
		write(output(list));

	}

	public void initializeHeap(File inFile) throws Exception {

		try {
			scan = new Scanner(inFile);
		} catch (Exception e) {
			System.out.println("Failed because" + e);
			System.exit(1);
		}
		Pattern ptrn = Pattern.compile("c");

		while (scan.findInLine(ptrn) != null)
			scan.nextLine();
		numVals = Integer.parseInt(scan.next());
		unionFind = new UnionFind(numVals + 1);

		while (scan.hasNextLine()) {
			vert1 = Integer.parseInt(scan.next());
			vert2 = Integer.parseInt(scan.next());
			weight = Integer.parseInt(scan.next());

			Edge tempEdge = new Edge(vert1, vert2, weight);
			queue.offer(tempEdge);

		}

	}

	public void findMST(File inFile) throws Exception {
		System.out.println("Path is " + inFile.getAbsolutePath());
		initializeHeap(inFile);

		unionFind.makeSet();

		while (list.size() < numVals - 1) {
			Edge tempEdge = queue.poll();
			int x = tempEdge.getVertexOne();
			int y = tempEdge.getVertexTwo();

			if (unionFind.connected(x, y) == false) {
				unionFind.unify(x, y);
				list.add(tempEdge);
				totalWeight += tempEdge.getWeight();
			}

		}

	}

	private String output(LinkedList<Edge> linklist) {
		return (linklist.toString().replace(", ", System.lineSeparator()).replace("[", "").replace("]", "")
				+ System.lineSeparator() + "Total Weight: " + totalWeight);

	}

	private void write(String string) {
		try {
			writer.write(string);
			writer.flush();
		} catch (IOException e) {
			System.exit(1);
		}

	}

	private void makeOutFile(String outFile) {
		outFileName = new File(outFile);
		try {
			writer = new PrintWriter(outFileName);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}

}
