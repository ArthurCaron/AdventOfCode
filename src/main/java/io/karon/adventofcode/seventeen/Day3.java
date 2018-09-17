package io.karon.adventofcode.seventeen;

class Day3 {

	static class Star1 {

		static int getResult() {
			int input = getInput();

			if (input == 1) {
				return 0;
			}

			int size = calculateGridSize(input);
			int[][] grid = new int[size][size];
			State center = new State(size / 2, size / 2, 1);

			grid[center.x][center.y] = center.value;

			for (int i = 1; i <= center.x; i++) {
				fillGridSpiral(grid, i);
			}

			State inputDataLocation = findInputDataLocation(grid, input);

			return distanceToCenter(center, inputDataLocation);
		}

			private static int calculateGridSize(int input) {
				int size = 1;

				while ((size * size) < input) {
					size += 2;
				}

				return size;
			}

		private static void fillGridSpiral(int[][] grid, int index) {
			int center = grid[0].length / 2;
			int x = center + index - 1;
			int y = center + index - 1;

			int value = grid[x][y];

			value++;
			x++;
			grid[x][y] = value;

			State state = new State(x, y, value);
			goTop(grid, state);
			goLeft(grid, state);
			goBot(grid, state);
			goRight(grid, state);
		}

		private static void goTop(int[][] grid, State state) {
			while (grid[state.x - 1][state.y] != 0) {
				state.y--;
				state.value++;
				grid[state.x][state.y] = state.value;
			}
		}

		private static void goLeft(int[][] grid, State state) {
			while (grid[state.x][state.y + 1] != 0) {
				state.x--;
				state.value++;
				grid[state.x][state.y] = state.value;
			}
		}

		private static void goBot(int[][] grid, State state) {
			while (grid[state.x + 1][state.y] != 0) {
				state.y++;
				state.value++;
				grid[state.x][state.y] = state.value;
			}
		}

		private static void goRight(int[][] grid, State state) {
			while (grid[state.x][state.y - 1] != 0 && state.x + 1 < grid.length) {
				state.x++;
				state.value++;
				grid[state.x][state.y] = state.value;
			}
		}

		private static State findInputDataLocation(int[][] grid, int input) {
			for (int x = 0; x < grid.length; x++) {
				if (grid[x][0] == input) {
					return new State(x, 0, input);
				} else if (grid[x][grid.length - 1] == input) {
					return new State(x, grid.length - 1, input);
				}
			}
			for (int y = 1; y < grid.length - 1; y++) {
				if (grid[0][y] == input) {
					return new State(0, y, input);
				} else if (grid[grid.length - 1][y] == input) {
					return new State(grid.length - 1, y, input);
				}
			}

			throw new RuntimeException("not found");
		}

		private static int distanceToCenter(State center, State inputDataLocation) {
			int xDistance = Math.abs(center.x - inputDataLocation.x);
			int yDistance = Math.abs(center.y - inputDataLocation.y);

			return xDistance + yDistance;
		}

	}

	static class Star2 {

		static int getResult() {
			int input = getInput();

			if (input == 1) {
				return 0;
			}

			int size = calculateGridSize(input);
			int[][] grid = new int[size][size];
			State center = new State(size / 2, size / 2, 1);

			grid[center.x][center.y] = center.value;

			State inputDataLocation = null;
			for (int i = 1; i <= center.x; i++) {
				inputDataLocation = fillGridSpiral(grid, i, input);
				if (inputDataLocation != null) {
					break;
				}
			}

			return inputDataLocation.value;
		}

		private static int calculateGridSize(int input) {
			int size = 1;

			while ((size * size) < input) {
				size += 2;
			}

			return 15;
		}

		private static State fillGridSpiral(int[][] grid, int index, int input) {
			int center = grid[0].length / 2;
			int x = center + index - 1;
			int y = center + index - 1;

			int value = grid[x][y];

			x++;
			State state = new State(x, y, value);
			state.value = calculateNextValue(grid, state);
			if (state.value > input) {
				return state;
			}
			grid[x][y] = state.value;

			State result;
			result = goTop(grid, state, input);
			if (result != null) {
				return result;
			}
			result = goLeft(grid, state, input);
			if (result != null) {
				return result;
			}
			result = goBot(grid, state, input);
			if (result != null) {
				return result;
			}
			result = goRight(grid, state, input);
			if (result != null) {
				return result;
			}
			return null;
		}

		private static int calculateNextValue(int[][] grid, State state) {
			int nextValue = 0;

			if (state.x - 1 >= 0) {
				nextValue += grid[state.x - 1][state.y];

				if (state.y - 1 >= 0) {
					nextValue += grid[state.x - 1][state.y - 1];
				}
				if (state.y + 1 < grid.length) {
					nextValue += grid[state.x - 1][state.y + 1];
				}
			}
			if (state.x + 1 < grid.length) {
				nextValue += grid[state.x + 1][state.y];

				if (state.y - 1 >= 0) {
					nextValue += grid[state.x + 1][state.y - 1];
				}
				if (state.y + 1 < grid.length) {
					nextValue += grid[state.x + 1][state.y + 1];
					;
				}
			}
			if (state.y - 1 >= 0) {
				nextValue += grid[state.x][state.y - 1];
			}
			if (state.y + 1 < grid.length) {
				nextValue += grid[state.x][state.y + 1];
			}
			return nextValue;
		}

		private static State goTop(int[][] grid, State state, int input) {
			while (grid[state.x - 1][state.y] != 0) {
				state.y--;
				state.value = calculateNextValue(grid, state);
				if (state.value > input) {
					return state;
				}
				grid[state.x][state.y] = state.value;
			}
			return null;
		}

		private static State goLeft(int[][] grid, State state, int input) {
			while (grid[state.x][state.y + 1] != 0) {
				state.x--;
				state.value = calculateNextValue(grid, state);
				if (state.value > input) {
					return state;
				}
				grid[state.x][state.y] = state.value;
			}
			return null;
		}

		private static State goBot(int[][] grid, State state, int input) {
			while (grid[state.x + 1][state.y] != 0) {
				state.y++;
				state.value = calculateNextValue(grid, state);
				if (state.value > input) {
					return state;
				}
				grid[state.x][state.y] = state.value;
			}
			return null;
		}

		private static State goRight(int[][] grid, State state, int input) {
			while (grid[state.x][state.y - 1] != 0 && state.x + 1 < grid.length) {
				state.x++;
				state.value = calculateNextValue(grid, state);
				if (state.value > input) {
					return state;
				}
				grid[state.x][state.y] = state.value;
			}
			return null;
		}

	}

	private static class State {

		int x;

		int y;

		int value;

		State(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	private static int getInput() {
		return 265149;
	}

}
