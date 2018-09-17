package io.karon.adventofcode.seventeen;

class Day14 {

	static class Star1 {

		static int getResult() {
			String[] grid = new String[128];
			for (int row = 0; row < grid.length; row++) {
				String input = getInput() + "-" + Integer.toString(row);
				int[] lengths = Day10.getFormattedInput(input);
				grid[row] = Day10.Star2.getDenseHash(lengths);
			}

			for (int i = 0; i < grid.length; i++) {
				grid[i] = hexToBinary(grid[i]);
			}

			return countFilledSquares(grid);
		}

		private static int countFilledSquares(String[] grid) {
			int count = 0;

			for (String hex : grid) {
				for (int j = 0; j < hex.length(); j++) {
					String hexAsString = String.valueOf(hex.charAt(j));
					int hexAsInt = Integer.parseInt(hexAsString, 16);
					count += Integer.bitCount(hexAsInt);
				}
			}

			return count;
		}

	}

	static class Star2 {

		static int getResult() {
			String[] grid = new String[128];
			for (int row = 0; row < grid.length; row++) {
				String input = getInput() + "-" + Integer.toString(row);
				int[] lengths = Day10.getFormattedInput(input);
				grid[row] = Day10.Star2.getDenseHash(lengths);
			}

			for (int i = 0; i < grid.length; i++) {
				grid[i] = hexToBinary(grid[i]);
			}

			Node[][] nodeGrid = gridToBinaryGrid(grid);

			return countRegions(nodeGrid);
		}

		private static Node[][] gridToBinaryGrid(String[] grid) {
			Node[][] nodeGrid = new Node[128][128];

			for (int i = 0; i < grid.length; i++) {
				String hex = grid[i];
				for (int j = 0; j < hex.length(); j++) {
					nodeGrid[i][j] = new Node(hex.charAt(j) == '1');
				}
			}

			return nodeGrid;
		}

		private static class Node {

			boolean used;

			Node(boolean used) {
				this.used = used;
			}

		}

		private static int countRegions(Node[][] nodeGrid) {
			int count = 0;

			for (int x = 0; x < nodeGrid.length; x++) {
				for (int y = 0; y < nodeGrid[x].length; y++) {
					if (nodeGrid[x][y].used) {
						count++;
						clearRegion(nodeGrid, x, y);
					}
				}
			}

			return count;
		}

		private static void clearRegion(Node[][] nodeGrid, int x, int y) {
			nodeGrid[x][y].used = false;
			if (x - 1 >= 0 && nodeGrid[x - 1][y].used) {
				clearRegion(nodeGrid, x - 1, y);
			}
			if (x + 1 < nodeGrid.length && nodeGrid[x + 1][y].used) {
				clearRegion(nodeGrid, x + 1, y);
			}
			if (y - 1 >= 0 && nodeGrid[x][y - 1].used) {
				clearRegion(nodeGrid, x, y - 1);
			}
			if (y + 1 < nodeGrid.length && nodeGrid[x][y + 1].used) {
				clearRegion(nodeGrid, x, y + 1);
			}
		}

	}

	private static String hexToBinary(String hex) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < hex.length(); i++) {
			String hexAsString = String.valueOf(hex.charAt(i));
			int hexAsInt = Integer.parseInt(hexAsString, 16);
			String hexAsBinary = Integer.toBinaryString(hexAsInt);

			for (int pad = hexAsBinary.length(); pad < 4; pad++) {
				stringBuilder.append("0");
			}

			stringBuilder.append(hexAsBinary);
		}

		return stringBuilder.toString();
	}

	private static String getInput() {
		return "hwlqcszp";
	}

}
