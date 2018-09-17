package io.karon.adventofcode.seventeen;

class Day10 {

	static class Star1 {

		static int getResult() {
			int[] lengths = getFormattedInput();
			int[] sparseHash = getIntArray();
			int currentPosition = 0;
			int skipSize = 0;

			for (int length : lengths) {
				currentPosition = calculateCurrentPosition(length, sparseHash, currentPosition, skipSize);
				skipSize++;
			}

			return sparseHash[0] * sparseHash[1];
		}

		private static int[] getFormattedInput() {
			String[] input = getInput().split(",");
			int[] array = new int[input.length];
			for (int i = 0; i < input.length; i++) {
				array[i] = Integer.parseInt(input[i]);
			}
			return array;
		}

	}

	static class Star2 {

		static String getResult() {
			int[] lengths = getFormattedInput(getInput());
			return getDenseHash(lengths);
		}

		static String getDenseHash(int[] lengths) {
			int[] sparseHash = getIntArray();
			int currentPosition = 0;
			int skipSize = 0;

			for (int round = 0; round < 64; round++) {
				for (int length : lengths) {
					currentPosition = calculateCurrentPosition(length, sparseHash, currentPosition, skipSize);
					skipSize++;
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < 16; i++) {
				String xoredValue = paddedXor(sparseHash, i * 16, 16);
				stringBuilder.append(xoredValue);
			}

			return stringBuilder.toString();
		}

		private static String paddedXor(int[] array, int startingIndex, int length) {
			StringBuilder stringBuilder = new StringBuilder();

			int xor = xor(array, startingIndex, length);
			String xorAsHex = Integer.toHexString(xor);

			for (int pad = xorAsHex.length(); pad < 2; pad++) {
				stringBuilder.append("0");
			}
			stringBuilder.append(xorAsHex);

			return stringBuilder.toString();
		}

		private static int xor(int[] array, int startingIndex, int length) {
			int xor = 0;
			for (int i = 0; i < length; i++) {
				xor ^= array[startingIndex + i];
			}
			return xor;
		}

	}

	private static int calculateCurrentPosition(int length, int[] sparseHash, int currentPosition, int skipSize) {
		int position1 = currentPosition;
		int position2 = position1 + length - 1;
		while (position2 >= sparseHash.length) {
			position2 -= sparseHash.length;
		}

		for (int i = 0; i < length / 2; i++) {
			swap(sparseHash, position1, position2);

			position1 += 1;
			position1 = (position1 >= sparseHash.length) ? (position1 - sparseHash.length) : position1;

			position2 -= 1;
			position2 = (position2 < 0) ? (sparseHash.length + position2) : position2;
		}

		currentPosition += length + skipSize;
		while (currentPosition >= sparseHash.length) {
			currentPosition -= sparseHash.length;
		}

		return currentPosition;
	}

	private static void swap(int[] array, int position1, int position2) {
		int tmp = array[position1];
		array[position1] = array[position2];
		array[position2] = tmp;
	}

	private static int[] getIntArray() {
		int[] intArray = new int[256];

		for (int i = 1; i < intArray.length; i++) {
			intArray[i] = i;
		}

		return intArray;
	}

	static int[] getFormattedInput(String input) {
		int[] array = new int[input.length() + 5];
		for (int i = 0; i < input.length(); i++) {
			array[i] = input.charAt(i);
		}
		array[input.length()] = 17;
		array[input.length() + 1] = 31;
		array[input.length() + 2] = 73;
		array[input.length() + 3] = 47;
		array[input.length() + 4] = 23;
		return array;
	}

	private static String getInput() {
		return "83,0,193,1,254,237,187,40,88,27,2,255,149,29,42,100";
	}

}
