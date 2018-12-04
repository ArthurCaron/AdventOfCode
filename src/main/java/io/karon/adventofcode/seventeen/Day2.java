package io.karon.adventofcode.seventeen;

import java.util.function.Function;


class Day2 {

	static class Star1 {

		static int getResult() {
			return calculateChecksum(Star1::rowDiff);
		}

		private static int rowDiff(int[] row) {
			int smallest = Integer.MAX_VALUE;
			int biggest = Integer.MIN_VALUE;

			for (int cell : row) {
				smallest = Math.min(smallest, cell);
				biggest = Math.max(biggest, cell);
			}

			return biggest - smallest;
		}

	}

	static class Star2 {

		static int getResult() {
			return calculateChecksum(Star2::rowDiff);
		}

		private static int rowDiff(int[] row) {
			for (int i = 0; i < row.length; ++i) {
				for (int j = 0; j < row.length; j++) {
					if (j == i) {
						continue;
					}

					if (row[i] % row[j] == 0) {
						return row[i] / row[j];
					}
				}

			}

			throw new IllegalArgumentException(
					"In this row, there is no combination of two numbers that are evenly divisible");
		}

	}

	private static int calculateChecksum(Function<int[], Integer> rowDiff) {
		int checksum = 0;

		for (int[] row : getFormattedInput()) {
			checksum += rowDiff.apply(row);
		}

		return checksum;
	}

	private static int[][] getFormattedInput() {
		String[] lines = getInput().split("\\n");
		int[][] formattedInput = new int[lines.length][];

		for (int i = 0; i < lines.length; ++i) {
			String[] values = lines[i].split("\\t");
			formattedInput[i] = new int[values.length];

			for (int j = 0; j < values.length; j++) {
				formattedInput[i][j] = Integer.parseInt(values[j]);
			}
		}

		return formattedInput;
	}

	private static String getInput() {
		return "62\t1649\t1731\t76\t51\t1295\t349\t719\t52\t1984\t2015\t2171\t981\t1809\t181\t1715\n"
						+ "161\t99\t1506\t1658\t84\t78\t533\t242\t1685\t86\t107\t1548\t670\t960\t1641\t610\n"
						+ "95\t2420\t2404\t2293\t542\t2107\t2198\t121\t109\t209\t2759\t1373\t1446\t905\t1837\t111\n"
						+ "552\t186\t751\t527\t696\t164\t114\t530\t558\t307\t252\t200\t481\t142\t205\t479\n"
						+ "581\t1344\t994\t1413\t120\t112\t656\t1315\t1249\t193\t1411\t1280\t110\t103\t74\t1007\n"
						+ "2536\t5252\t159\t179\t4701\t1264\t1400\t2313\t4237\t161\t142\t4336\t1061\t3987\t2268\t4669\n"
						+ "3270\t1026\t381\t185\t293\t3520\t1705\t1610\t3302\t628\t3420\t524\t3172\t244\t295\t39\n"
						+ "4142\t1835\t4137\t3821\t3730\t2094\t468\t141\t150\t3982\t147\t4271\t1741\t2039\t4410\t179\n"
						+ "1796\t83\t2039\t1252\t84\t1641\t2165\t1218\t1936\t335\t1807\t2268\t66\t102\t1977\t2445\n"
						+ "96\t65\t201\t275\t257\t282\t233\t60\t57\t200\t216\t134\t72\t105\t81\t212\n"
						+ "3218\t5576\t5616\t5253\t178\t3317\t6147\t5973\t2424\t274\t4878\t234\t200\t4781\t5372\t276\n"
						+ "4171\t2436\t134\t3705\t3831\t3952\t2603\t115\t660\t125\t610\t152\t4517\t587\t1554\t619\n"
						+ "2970\t128\t2877\t1565\t1001\t167\t254\t2672\t59\t473\t2086\t181\t1305\t162\t1663\t2918\n"
						+ "271\t348\t229\t278\t981\t1785\t2290\t516\t473\t2037\t737\t2291\t2521\t1494\t1121\t244\n"
						+ "2208\t2236\t1451\t621\t1937\t1952\t865\t61\t1934\t49\t1510\t50\t1767\t59\t194\t1344\n"
						+ "94\t2312\t2397\t333\t1192\t106\t2713\t2351\t2650\t2663\t703\t157\t89\t510\t1824\t125";
	}

//	// Same values as above, but with manual formatting
//	private static int[][] getFormattedInput() {
//		return new int[][] {
//				{ 62, 1649, 1731, 76, 51, 1295, 349, 719, 52, 1984, 2015, 2171, 981, 1809, 181, 1715 },
//				{ 161, 99, 1506, 1658, 84, 78, 533, 242, 1685, 86, 107, 1548, 670, 960, 1641, 610 },
//				{ 95, 2420, 2404, 2293, 542, 2107, 2198, 121, 109, 209, 2759, 1373, 1446, 905, 1837, 111 },
//				{ 552, 186, 751, 527, 696, 164, 114, 530, 558, 307, 252, 200, 481, 142, 205, 479 },
//				{ 581, 1344, 994, 1413, 120, 112, 656, 1315, 1249, 193, 1411, 1280, 110, 103, 74, 1007 },
//				{ 2536, 5252, 159, 179, 4701, 1264, 1400, 2313, 4237, 161, 142, 4336, 1061, 3987, 2268, 4669 },
//				{ 3270, 1026, 381, 185, 293, 3520, 1705, 1610, 3302, 628, 3420, 524, 3172, 244, 295, 39 },
//				{ 4142, 1835, 4137, 3821, 3730, 2094, 468, 141, 150, 3982, 147, 4271, 1741, 2039, 4410, 179 },
//				{ 1796, 83, 2039, 1252, 84, 1641, 2165, 1218, 1936, 335, 1807, 2268, 66, 102, 1977, 2445 },
//				{ 96, 65, 201, 275, 257, 282, 233, 60, 57, 200, 216, 134, 72, 105, 81, 212 },
//				{ 3218, 5576, 5616, 5253, 178, 3317, 6147, 5973, 2424, 274, 4878, 234, 200, 4781, 5372, 276 },
//				{ 4171, 2436, 134, 3705, 3831, 3952, 2603, 115, 660, 125, 610, 152, 4517, 587, 1554, 619 },
//				{ 2970, 128, 2877, 1565, 1001, 167, 254, 2672, 59, 473, 2086, 181, 1305, 162, 1663, 2918 },
//				{ 271, 348, 229, 278, 981, 1785, 2290, 516, 473, 2037, 737, 2291, 2521, 1494, 1121, 244 },
//				{ 2208, 2236, 1451, 621, 1937, 1952, 865, 61, 1934, 49, 1510, 50, 1767, 59, 194, 1344 },
//				{ 94, 2312, 2397, 333, 1192, 106, 2713, 2351, 2650, 2663, 703, 157, 89, 510, 1824, 125 }
//		};
//	}

}
