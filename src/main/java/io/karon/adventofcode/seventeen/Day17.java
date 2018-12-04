package io.karon.adventofcode.seventeen;

class Day17 {

	static class Star1 {

		private static final int MAX_VALUES = 2018;

		static int getResult() {
			int forwardSteps = getInput();
			int[] circularBuffer = new int[MAX_VALUES];
			circularBuffer[0] = 0;

			int currentPosition = 0;
			for (int i = 1; i < MAX_VALUES; ++i) {
				int index = currentPosition + forwardSteps;
				while (index >= i) {
					index -= i;
				}
				currentPosition = index + 1;

				System.arraycopy(circularBuffer, index, circularBuffer, currentPosition, i - index);

				circularBuffer[currentPosition] = i;
			}

			currentPosition += 1;
			if (currentPosition >= MAX_VALUES) {
				currentPosition -= MAX_VALUES;
			}

			return circularBuffer[currentPosition];
		}

	}

	static class Star2 {

		private static final int MAX_VALUES = 50000000;

		static int getResult() {
			int forwardSteps = getInput();

			int indexOfZero = 0;
			int valueAfterZero = 0;
			int currentPosition = 0;
			for (int i = 1; i < MAX_VALUES; ++i) {
				currentPosition += forwardSteps;
				while (currentPosition >= i) {
					currentPosition -= i;
				}

				currentPosition += 1;
				if (currentPosition == indexOfZero + 1) {
					valueAfterZero = i;
				}

				if (indexOfZero >= currentPosition) {
					indexOfZero++;
					if (indexOfZero >= MAX_VALUES) {
						indexOfZero -= MAX_VALUES;
					}
				}
			}

			return valueAfterZero;
		}

	}

	private static int getInput() {
		return 312;
	}

}
