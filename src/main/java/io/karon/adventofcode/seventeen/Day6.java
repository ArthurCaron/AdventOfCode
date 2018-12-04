package io.karon.adventofcode.seventeen;

import java.util.HashMap;
import java.util.Map;


class Day6 {

	static class Star1 {

		static int getResult() {
			return Day6.getResult(1);
		}

	}

	static class Star2 {

		static int getResult() {
			return Day6.getResult(2);
		}

	}

	private static int getResult(int repetitionNumber) {
		int[] memoryBanks = getFormattedInput();
		int nbSteps = 0;
		Node root = new Node();
		root.getOrAdd(memoryBanks);

		int nbOfTimesFound = 0;

		while (nbOfTimesFound < repetitionNumber) {
			if (nbOfTimesFound == repetitionNumber - 1) {
				nbSteps++;
			}
			redistribute(memoryBanks);
			nbOfTimesFound = root.getOrAdd(memoryBanks);
		}

		return nbSteps;
	}

	private static int[] redistribute(int[] memoryBanks) {
		int biggestBankIndex = 0;
		int biggestBankValue = 0;

		for (int i = 0; i < memoryBanks.length; ++i) {
			if (memoryBanks[i] > biggestBankValue) {
				biggestBankValue = memoryBanks[i];
				biggestBankIndex = i;
			}
		}

		memoryBanks[biggestBankIndex] = 0;

		while (biggestBankValue > 0) {
			biggestBankIndex = (biggestBankIndex + 1) % memoryBanks.length;
			memoryBanks[biggestBankIndex]++;
			biggestBankValue--;
		}

		return memoryBanks;
	}

	static class Node {

		Map<Integer, Node> children = new HashMap<>();

		int nbOfTimesFound = 0;

		int getOrAdd(int[] memoryBanks) {
			return getOrAdd(memoryBanks, 0);
		}

		private int getOrAdd(int[] memoryBanks, int index) {
			Node node = children.get(memoryBanks[index]);

			if (node == null) {
				Node newNode = new Node();
				children.put(memoryBanks[index], newNode);
				index++;
				newNode.add(memoryBanks, index);
			} else {
				index++;
				if (index < memoryBanks.length) {
					return node.getOrAdd(memoryBanks, index);
				} else {
					node.nbOfTimesFound++;
					return node.nbOfTimesFound;
				}
			}

			return 0;
		}

		private void add(int[] memoryBanks, int index) {
			if (index >= memoryBanks.length) {
				return;
			}

			Node newNode = new Node();
			children.put(memoryBanks[index], newNode);
			newNode.add(memoryBanks, index + 1);
		}

	}

	private static int[] getFormattedInput() {
		String[] input = getInput().split("[\\t\\n]");
		int[] formattedInput = new int[input.length];

		for (int i = 0; i < input.length; ++i) {
			formattedInput[i] = Integer.parseInt(input[i]);
		}

		return formattedInput;
	}

	private static String getInput() {
		return "4\t1\t15\t12\t0\t9\t9\t5\t5\t8\t7\t3\t14\t5\t12\t3\n";
	}

//	// Same values as above, but with manual formatting
//	private static int[] getFormattedInput() {
//		return new int[] {
//				4, 1, 15, 12, 0, 9, 9, 5, 5, 8, 7, 3, 14, 5, 12, 3
//		};
//	}

}
