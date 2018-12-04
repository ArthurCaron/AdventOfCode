package io.karon.adventofcode.seventeen;

import java.util.HashMap;
import java.util.Map;


class Day21 {

	static class Star1 {

		static int getResult() {
			return calculateNumberOfActivatedPixelsAfterNumberOfIterations(5);
		}

	}

	static class Star2 {

		static int getResult() {
			return calculateNumberOfActivatedPixelsAfterNumberOfIterations(18);
		}

	}

	private static int calculateNumberOfActivatedPixelsAfterNumberOfIterations(int numberOfIterations) {
		PatternMatcher patternMatcher = getFormattedInput();
		char[][] grid = getInitialGrid();

		for (int iteration = 0; iteration < numberOfIterations; iteration++) {
			if (grid.length % 2 == 0) {
				int smallerGridNumber = grid.length / 2;
				char[][] newGrid = new char[smallerGridNumber * 3][smallerGridNumber * 3];

				for (int i = 0; i < smallerGridNumber; ++i) {
					for (int j = 0; j < smallerGridNumber; j++) {
						int indexI = i * 2;
						int indexJ = j * 2;
						char[][] smaller = new char[][] {
								{ grid[indexI][indexJ], grid[indexI][indexJ + 1] },
								{ grid[indexI + 1][indexJ], grid[indexI + 1][indexJ + 1] }
						};

						String[] output = patternMatcher.getMatch(smaller);
						int indexINew = i * 3;
						int indexJNew = j * 3;

						newGrid[indexINew][indexJNew] = output[0].charAt(0);
						newGrid[indexINew][indexJNew + 1] = output[0].charAt(1);
						newGrid[indexINew][indexJNew + 2] = output[0].charAt(2);

						newGrid[indexINew + 1][indexJNew] = output[1].charAt(0);
						newGrid[indexINew + 1][indexJNew + 1] = output[1].charAt(1);
						newGrid[indexINew + 1][indexJNew + 2] = output[1].charAt(2);

						newGrid[indexINew + 2][indexJNew] = output[2].charAt(0);
						newGrid[indexINew + 2][indexJNew + 1] = output[2].charAt(1);
						newGrid[indexINew + 2][indexJNew + 2] = output[2].charAt(2);
					}
				}

				grid = newGrid;
			} else {
				int smallerGridNumber = grid.length / 3;
				char[][] newGrid = new char[smallerGridNumber * 4][smallerGridNumber * 4];

				for (int i = 0; i < smallerGridNumber; ++i) {
					for (int j = 0; j < smallerGridNumber; j++) {
						int indexI = i * 3;
						int indexJ = j * 3;
						char[][] smaller = new char[][] {
								{ grid[indexI][indexJ], grid[indexI][indexJ + 1], grid[indexI][indexJ + 2] },
								{ grid[indexI + 1][indexJ], grid[indexI + 1][indexJ + 1],
										grid[indexI + 1][indexJ + 2] },
								{ grid[indexI + 2][indexJ], grid[indexI + 2][indexJ + 1],
										grid[indexI + 2][indexJ + 2] }
						};

						String[] output = patternMatcher.getMatch(smaller);
						int indexINew = i * 4;
						int indexJNew = j * 4;

						newGrid[indexINew][indexJNew] = output[0].charAt(0);
						newGrid[indexINew][indexJNew + 1] = output[0].charAt(1);
						newGrid[indexINew][indexJNew + 2] = output[0].charAt(2);
						newGrid[indexINew][indexJNew + 3] = output[0].charAt(3);

						newGrid[indexINew + 1][indexJNew] = output[1].charAt(0);
						newGrid[indexINew + 1][indexJNew + 1] = output[1].charAt(1);
						newGrid[indexINew + 1][indexJNew + 2] = output[1].charAt(2);
						newGrid[indexINew + 1][indexJNew + 3] = output[1].charAt(3);

						newGrid[indexINew + 2][indexJNew] = output[2].charAt(0);
						newGrid[indexINew + 2][indexJNew + 1] = output[2].charAt(1);
						newGrid[indexINew + 2][indexJNew + 2] = output[2].charAt(2);
						newGrid[indexINew + 2][indexJNew + 3] = output[2].charAt(3);

						newGrid[indexINew + 3][indexJNew] = output[3].charAt(0);
						newGrid[indexINew + 3][indexJNew + 1] = output[3].charAt(1);
						newGrid[indexINew + 3][indexJNew + 2] = output[3].charAt(2);
						newGrid[indexINew + 3][indexJNew + 3] = output[3].charAt(3);
					}
				}

				grid = newGrid;
			}
		}

		int count = 0;
		for (char[] xGrid : grid) {
			for (char yGrid : xGrid) {
				if (yGrid == '#') {
					count++;
				}
			}
		}
		return count;
	}

	private static class PatternMatcher {

		Map<String, String[]> twoByTwoPatternMapping = new HashMap<>();
		Map<String, String[]> threeByThreePatternMapping = new HashMap<>();

		void add(Pattern pattern) {
			String patternForMapping;
			if (pattern.twoByTwo) {
				patternForMapping = pattern.input[0] + pattern.input[1];
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[0].charAt(1) + pattern.input[0].charAt(0)
						+ pattern.input[1].charAt(1) + pattern.input[1].charAt(0);
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// rotate
				patternForMapping = "" + pattern.input[0].charAt(1) + pattern.input[1].charAt(1)
						+ pattern.input[0].charAt(0) + pattern.input[1].charAt(0);
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[1].charAt(1) + pattern.input[0].charAt(1)
						+ pattern.input[1].charAt(0) + pattern.input[0].charAt(0);
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// rotate
				patternForMapping = pattern.input[1] + pattern.input[0];
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[1].charAt(1) + pattern.input[1].charAt(0)
						+ pattern.input[0].charAt(1) + pattern.input[0].charAt(0);
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// rotate
				patternForMapping = "" + pattern.input[1].charAt(0) + pattern.input[0].charAt(0)
						+ pattern.input[1].charAt(1) + pattern.input[0].charAt(1);
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[0].charAt(0) + pattern.input[1].charAt(0)
						+ pattern.input[0].charAt(1) + pattern.input[1].charAt(1);
				twoByTwoPatternMapping.put(patternForMapping, pattern.output);
			} else {
				patternForMapping = pattern.input[0] + pattern.input[1] + pattern.input[2];
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[0].charAt(2) + pattern.input[0].charAt(1) + pattern.input[0].charAt(0)
						+ pattern.input[1].charAt(2) + pattern.input[1].charAt(1) + pattern.input[1].charAt(0)
						+ pattern.input[2].charAt(2) + pattern.input[2].charAt(1) + pattern.input[2].charAt(0);
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// rotate
				patternForMapping = "" + pattern.input[0].charAt(2) + pattern.input[1].charAt(2) + pattern.input[2].charAt(2)
						+ pattern.input[0].charAt(1) + pattern.input[1].charAt(1) + pattern.input[2].charAt(1)
						+ pattern.input[0].charAt(0) + pattern.input[1].charAt(0) + pattern.input[2].charAt(0);
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[2].charAt(2) + pattern.input[1].charAt(2) + pattern.input[0].charAt(2)
						+ pattern.input[2].charAt(1) + pattern.input[1].charAt(1) + pattern.input[0].charAt(1)
						+ pattern.input[2].charAt(0) + pattern.input[1].charAt(0) + pattern.input[0].charAt(0);
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// rotate
				patternForMapping =  pattern.input[2] + pattern.input[1] + pattern.input[0];
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[2].charAt(2) + pattern.input[2].charAt(1) + pattern.input[2].charAt(0)
						+ pattern.input[1].charAt(2) + pattern.input[1].charAt(1) + pattern.input[1].charAt(0)
						+ pattern.input[0].charAt(2) + pattern.input[0].charAt(1) + pattern.input[0].charAt(0);
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// rotate
				patternForMapping = "" + pattern.input[2].charAt(0) + pattern.input[1].charAt(0) + pattern.input[0].charAt(0)
						+ pattern.input[2].charAt(1) + pattern.input[1].charAt(1) + pattern.input[0].charAt(1)
						+ pattern.input[2].charAt(2) + pattern.input[1].charAt(2) + pattern.input[0].charAt(2);
				threeByThreePatternMapping.put(patternForMapping, pattern.output);

				// flip
				patternForMapping = "" + pattern.input[0].charAt(0) + pattern.input[1].charAt(0) + pattern.input[2].charAt(0)
						+ pattern.input[0].charAt(1) + pattern.input[1].charAt(1) + pattern.input[2].charAt(1)
						+ pattern.input[0].charAt(2) + pattern.input[1].charAt(2) + pattern.input[2].charAt(2);
				threeByThreePatternMapping.put(patternForMapping, pattern.output);
			}
		}

		String[] getMatch(char[][] grid) {
			String patternForMatching;

			if (grid.length == 2) {
				patternForMatching = "" + grid[0][0] + grid[0][1] + grid[1][0] + grid[1][1];
				return twoByTwoPatternMapping.get(patternForMatching);
			} else {
				patternForMatching = "" + grid[0][0] + grid[0][1] + grid[0][2] + grid[1][0] + grid[1][1] + grid[1][2] + grid[2][0] + grid[2][1] + grid[2][2];
				return threeByThreePatternMapping.get(patternForMatching);
			}
		}

	}

	private static class Pattern {

		boolean twoByTwo;
		String[] input;
		String[] output;

		Pattern(String data) {
			String[] beforeAfter = data.split(" => ");

			input = beforeAfter[0].split("/");
			twoByTwo = input.length == 2;
			output = beforeAfter[1].split("/");
		}

	}

	private static char[][] getInitialGrid() {
		return new char[][] {
				{ '.', '#', '.' },
				{ '.', '.', '#' },
				{ '#', '#', '#' }
		};
	}

	private static PatternMatcher getFormattedInput() {
		String[] inputs = getInput().split("\\n");
		PatternMatcher patternMatcher = new PatternMatcher();

		for (String input : inputs) {
			patternMatcher.add(new Pattern(input));
		}

		return patternMatcher;
	}

	private static String getTestInput() {
		return "../.# => ##./#../...\n"
				+ ".#./..#/### => #..#/..../..../#..#";
	}

	private static String getInput() {
		return "../.. => ##./##./.##\n"
				+ "#./.. => .../.#./##.\n"
				+ "##/.. => .../.##/#.#\n"
				+ ".#/#. => ##./#../#..\n"
				+ "##/#. => .##/#.#/#..\n"
				+ "##/## => ..#/.#./.##\n"
				+ ".../.../... => #.../.##./...#/#...\n"
				+ "#../.../... => ...#/..../..#./..##\n"
				+ ".#./.../... => ..../.##./###./....\n"
				+ "##./.../... => ###./#.##/..#./..#.\n"
				+ "#.#/.../... => #.../.#../#..#/..#.\n"
				+ "###/.../... => ..##/.##./#.../....\n"
				+ ".#./#../... => #.##/..../..../#.##\n"
				+ "##./#../... => .#.#/.#.#/##../.#..\n"
				+ "..#/#../... => .###/####/.###/##..\n"
				+ "#.#/#../... => ..../.#.#/..../####\n"
				+ ".##/#../... => .##./##.#/.###/#..#\n"
				+ "###/#../... => ####/...#/###./.###\n"
				+ ".../.#./... => ..##/#..#/###./###.\n"
				+ "#../.#./... => ###./..##/.#.#/.#.#\n"
				+ ".#./.#./... => ..#./..#./##.#/##..\n"
				+ "##./.#./... => #..#/###./..#./#.#.\n"
				+ "#.#/.#./... => .###/#.../.#.#/.##.\n"
				+ "###/.#./... => #.##/##../#.#./...#\n"
				+ ".#./##./... => #.##/#.##/#.##/.###\n"
				+ "##./##./... => ..##/#..#/.###/....\n"
				+ "..#/##./... => #..#/.##./##../####\n"
				+ "#.#/##./... => ###./###./..##/..##\n"
				+ ".##/##./... => ###./##.#/.##./###.\n"
				+ "###/##./... => ##../#..#/##../....\n"
				+ ".../#.#/... => ##.#/..#./..##/##..\n"
				+ "#../#.#/... => #..#/.###/.#../#.#.\n"
				+ ".#./#.#/... => ####/#.##/.###/###.\n"
				+ "##./#.#/... => #.../####/...#/.#.#\n"
				+ "#.#/#.#/... => ...#/.#.#/#..#/#.##\n"
				+ "###/#.#/... => ###./#.##/##.#/..##\n"
				+ ".../###/... => ..../##.#/.#../..##\n"
				+ "#../###/... => ####/..##/.##./.###\n"
				+ ".#./###/... => #.#./#.#./#.../#..#\n"
				+ "##./###/... => #..#/..##/#.##/#.#.\n"
				+ "#.#/###/... => .##./##.#/.#../####\n"
				+ "###/###/... => ####/##.#/.#../#.#.\n"
				+ "..#/.../#.. => #..#/#.##/.###/.###\n"
				+ "#.#/.../#.. => .##./#.../.#.#/....\n"
				+ ".##/.../#.. => .#.#/.#.#/##../####\n"
				+ "###/.../#.. => .#.#/.##./####/##.#\n"
				+ ".##/#../#.. => .###/.###/.###/#...\n"
				+ "###/#../#.. => ..##/#.../#.#./..#.\n"
				+ "..#/.#./#.. => #.#./##../##../####\n"
				+ "#.#/.#./#.. => ..../..##/#..#/..#.\n"
				+ ".##/.#./#.. => #.##/#..#/##.#/.##.\n"
				+ "###/.#./#.. => ...#/#.../#.#./.#..\n"
				+ ".##/##./#.. => .##./#..#/.##./...#\n"
				+ "###/##./#.. => ##.#/##.#/.##./...#\n"
				+ "#../..#/#.. => ##../..#./..#./#.#.\n"
				+ ".#./..#/#.. => #.#./##../#..#/#.##\n"
				+ "##./..#/#.. => #.##/###./###./.#.#\n"
				+ "#.#/..#/#.. => ..../...#/...#/#..#\n"
				+ ".##/..#/#.. => #..#/#.#./..##/.##.\n"
				+ "###/..#/#.. => ##../.#.#/.#../#.#.\n"
				+ "#../#.#/#.. => ####/.##./.##./.##.\n"
				+ ".#./#.#/#.. => ...#/.##./..#./.##.\n"
				+ "##./#.#/#.. => .#.#/.##./..#./.#.#\n"
				+ "..#/#.#/#.. => .#../##.#/##../#...\n"
				+ "#.#/#.#/#.. => .#.#/..#./#.../##..\n"
				+ ".##/#.#/#.. => ..#./#.#./###./#...\n"
				+ "###/#.#/#.. => ..../#.#./..##/##.#\n"
				+ "#../.##/#.. => .##./##../.#../..##\n"
				+ ".#./.##/#.. => ##../#.#./#.../####\n"
				+ "##./.##/#.. => ###./###./#.#./..##\n"
				+ "#.#/.##/#.. => ...#/#..#/..#./###.\n"
				+ ".##/.##/#.. => ..##/####/..../#.##\n"
				+ "###/.##/#.. => .#.#/#.../.##./#...\n"
				+ "#../###/#.. => ..#./.#.#/#..#/.##.\n"
				+ ".#./###/#.. => ####/..../####/#.##\n"
				+ "##./###/#.. => .###/..../#.#./####\n"
				+ "..#/###/#.. => ###./#.#./.#.#/#...\n"
				+ "#.#/###/#.. => #.#./#.#./..##/.##.\n"
				+ ".##/###/#.. => #.##/.###/.##./#.##\n"
				+ "###/###/#.. => #..#/.#../.#../.##.\n"
				+ ".#./#.#/.#. => .#../.##./##../..##\n"
				+ "##./#.#/.#. => .##./#.##/...#/#.#.\n"
				+ "#.#/#.#/.#. => ##.#/###./#.#./..#.\n"
				+ "###/#.#/.#. => ..../##../.###/###.\n"
				+ ".#./###/.#. => .#.#/.###/..../#..#\n"
				+ "##./###/.#. => #.../..#./#..#/.#..\n"
				+ "#.#/###/.#. => .#../##.#/##.#/.###\n"
				+ "###/###/.#. => #..#/.#.#/#.#./..#.\n"
				+ "#.#/..#/##. => .#../.###/...#/#.##\n"
				+ "###/..#/##. => ...#/...#/..##/...#\n"
				+ ".##/#.#/##. => #.#./###./.##./####\n"
				+ "###/#.#/##. => #.#./...#/...#/....\n"
				+ "#.#/.##/##. => ###./#.../##.#/..#.\n"
				+ "###/.##/##. => .#../#.../.###/.#..\n"
				+ ".##/###/##. => #.../..#./..#./.###\n"
				+ "###/###/##. => .#../.#../####/###.\n"
				+ "#.#/.../#.# => ##.#/##../...#/##.#\n"
				+ "###/.../#.# => ###./###./#..#/###.\n"
				+ "###/#../#.# => .###/..#./.#../#...\n"
				+ "#.#/.#./#.# => ##.#/.##./.#.#/##.#\n"
				+ "###/.#./#.# => ...#/...#/#.##/.##.\n"
				+ "###/##./#.# => #.../##../#.../....\n"
				+ "#.#/#.#/#.# => ####/.#../..##/..##\n"
				+ "###/#.#/#.# => ##../####/#.##/..##\n"
				+ "#.#/###/#.# => ##../..../..../####\n"
				+ "###/###/#.# => .#../.#.#/.###/.#.#\n"
				+ "###/#.#/### => ##../####/###./...#\n"
				+ "###/###/### => ###./#..#/##../.##.\n";
	}

}
