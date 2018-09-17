package io.karon.adventofcode.seventeen;

class Day15 {

	private static final int FACTOR_A = 16807;
	private static final int FACTOR_B = 48271;

	static class Star1 {

		private static final int FORTY_MILLIONS = 40000000;

		static int getResult() {
			int count = 0;
			long a = getInputGeneratorA();
			long b = getInputGeneratorB();

			for (int i = 0; i < FORTY_MILLIONS; i++) {
				a = operate(a, FACTOR_A);
				b = operate(b, FACTOR_B);

				String binaryA = to16BitsBinaryString(a);
				String binaryB = to16BitsBinaryString(b);

				if (binaryA.equals(binaryB)) {
					count++;
				}
			}

			return count;
		}

	}

	static class Star2 {

		private static final int FIVE_MILLIONS = 5000000;
		private static final int MULTIPLE_FOR_A = 4;
		private static final int MULTIPLE_FOR_B = 8;

		static int getResult() {
			int count = 0;
			long a = getInputGeneratorA();
			long b = getInputGeneratorB();

			for (int i = 0; i < FIVE_MILLIONS; i++) {
				a = operateUntilMultipleFound(a, FACTOR_A, MULTIPLE_FOR_A);
				b = operateUntilMultipleFound(b, FACTOR_B, MULTIPLE_FOR_B);

				String binaryA = to16BitsBinaryString(a);
				String binaryB = to16BitsBinaryString(b);

				if (binaryA.equals(binaryB)) {
					count++;
				}
			}

			return count;
		}

		private static long operateUntilMultipleFound(long input, int factor, int multiple) {
			boolean multipleFound = false;
			long result = operate(input, factor);
			while (!multipleFound) {
				if (result % multiple == 0) {
					multipleFound = true;
				} else {
					result = operate(result, factor);
				}
			}
			return result;
		}

	}

	private static long operate(long input, int factor) {
		return (input * factor) % 2147483647;
	}

	private static String to16BitsBinaryString(long value) {
		StringBuilder stringBuilder = new StringBuilder();

		String binary = Long.toBinaryString(value);
		if (binary.length() > 16) {
			binary = binary.substring(binary.length() - 16);
		}

		for (int pad = binary.length(); pad < 16; pad++) {
			stringBuilder.append("0");
		}
		stringBuilder.append(binary);

		return stringBuilder.toString();
	}

	private static int getTestInputGeneratorA() {
		return 65;
	}

	private static int getTestInputGeneratorB() {
		return 8921;
	}

	private static int getInputGeneratorA() {
		return 634;
	}

	private static int getInputGeneratorB() {
		return 301;
	}

}
