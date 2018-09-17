package io.karon.adventofcode.seventeen;

import java.util.HashMap;
import java.util.Map;


class Day22 {

	static class Star1 {

		private static final int NUMBER_OF_BURSTS = 10000;

		static int getResult() {
			String[] inputs = getInput().split("\\n");
			Map<String, NodeStatus> grid = getFormattedInput(inputs);
			Direction virusDirection = Direction.TOP;
			int x = inputs.length / 2;
			int y = inputs[0].length() / 2;

			int newInfectedCount = 0;
			for (int i = 0; i < NUMBER_OF_BURSTS; i++) {
				String coordinates = coordinatesToString(x, y);
				NodeStatus nodeStatus = grid.getOrDefault(coordinates, NodeStatus.CLEAN);
				if (nodeStatus == NodeStatus.INFECTED) {
					virusDirection = virusDirection.turnRight();
					grid.remove(coordinates);
				} else {
					virusDirection = virusDirection.turnLeft();
					grid.put(coordinates, NodeStatus.INFECTED);
					newInfectedCount++;
				}

				if (virusDirection.isBot()) {
					x++;
				} else if (virusDirection.isLeft()) {
					y--;
				} else if (virusDirection.isTop()) {
					x--;
				} else {
					y++;
				}
			}

			return newInfectedCount;
		}

	}

	static class Star2 {

		private static final int NUMBER_OF_BURSTS = 10000000;

		static int getResult() {
			String[] inputs = getInput().split("\\n");
			Map<String, NodeStatus> grid = getFormattedInput(inputs);
			Direction virusDirection = Direction.TOP;
			int x = inputs.length / 2;
			int y = inputs[0].length() / 2;

			int newInfectedCount = 0;
			for (int i = 0; i < NUMBER_OF_BURSTS; i++) {
				String coordinates = coordinatesToString(x, y);
				NodeStatus nodeStatus = grid.getOrDefault(coordinates, NodeStatus.CLEAN);
				if (nodeStatus == NodeStatus.CLEAN) {
					virusDirection = virusDirection.turnLeft();
					grid.put(coordinates, NodeStatus.WEAKENED);
				} else if (nodeStatus == NodeStatus.WEAKENED) {
					grid.put(coordinates, NodeStatus.INFECTED);
					newInfectedCount++;
				} else if (nodeStatus == NodeStatus.INFECTED) {
					virusDirection = virusDirection.turnRight();
					grid.put(coordinates, NodeStatus.FLAGGED);
				} else if (nodeStatus == NodeStatus.FLAGGED) {
					virusDirection = virusDirection.reverse();
					grid.remove(coordinates);
				}

				if (virusDirection.isBot()) {
					x++;
				} else if (virusDirection.isLeft()) {
					y--;
				} else if (virusDirection.isTop()) {
					x--;
				} else {
					y++;
				}
			}

			return newInfectedCount;
		}

	}

	private static String coordinatesToString(int x, int y) {
		return x + "," + y;
	}

	private enum NodeStatus {
		CLEAN,
		WEAKENED,
		INFECTED,
		FLAGGED
	}

	private enum Direction {
		BOT,
		TOP,
		LEFT,
		RIGHT;

		Direction turnRight() {
			if (isBot()) {
				return LEFT;
			} else if (isLeft()) {
				return TOP;
			} else if (isTop()) {
				return RIGHT;
			} else {
				return BOT;
			}
		}

		Direction reverse() {
			if (isBot()) {
				return TOP;
			} else if (isTop()) {
				return BOT;
			} else if (isRight()) {
				return LEFT;
			} else {
				return RIGHT;
			}
		}

		Direction turnLeft() {
			if (isBot()) {
				return RIGHT;
			} else if (isRight()) {
				return TOP;
			} else if (isTop()) {
				return LEFT;
			} else {
				return BOT;
			}
		}

		boolean isBot() {
			return Direction.BOT.name().equals(this.name());
		}

		boolean isTop() {
			return Direction.TOP.name().equals(this.name());
		}

		boolean isLeft() {
			return Direction.LEFT.name().equals(this.name());
		}

		boolean isRight() {
			return Direction.RIGHT.name().equals(this.name());
		}
	}

	private static Map<String, NodeStatus> getFormattedInput(String[] inputs) {
		Map<String, NodeStatus> grid = new HashMap<>();

		for (int i = 0; i < inputs.length; i++) {
			for (int j = 0; j < inputs[i].length(); j++) {
				if (inputs[i].charAt(j) == '#') {
					grid.put(coordinatesToString(i, j), NodeStatus.INFECTED);
				}
			}
		}

		return grid;
	}

	private static String getInput() {
		return ".###.#.#####.##.#...#....\n"
				+ "..####.##.##.#..#.....#..\n"
				+ ".#####.........#####..###\n"
				+ "#.#..##..#.###.###.#.####\n"
				+ ".##.##..#.###.###...#...#\n"
				+ "#.####..#.#.##.##...##.##\n"
				+ "..#......#...#...#.#....#\n"
				+ "###.#.#.##.#.##.######..#\n"
				+ "###..##....#...##....#...\n"
				+ "###......#..#..###.#...#.\n"
				+ "#.##..####.##..####...##.\n"
				+ "###.#.#....######.#.###..\n"
				+ ".#.##.##...##.#.#..#...##\n"
				+ "######....##..##.######..\n"
				+ "##..##.#.####.##.###.#.##\n"
				+ "#.###.#.##....#.##..####.\n"
				+ "#.#......##..####.###.#..\n"
				+ "#..###.###...#..#.#.##...\n"
				+ "#######..#.....#######..#\n"
				+ "##..##..#..#.####..###.#.\n"
				+ "..#......##...#..##.###.#\n"
				+ "....##..#.#.##....#..#.#.\n"
				+ "..#...#.##....###...###.#\n"
				+ "#.#.#.#..##..##..#..#.##.\n"
				+ "#.####.#......#####.####.\n";
	}

}
