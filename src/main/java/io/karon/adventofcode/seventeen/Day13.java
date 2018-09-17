package io.karon.adventofcode.seventeen;

import java.util.ArrayList;
import java.util.List;


class Day13 {

	static class Star1 {

		static int getResult() {
			List<SecurityLayer> securityLayers = getFormattedInput();

			int severity = 0;

			for (SecurityLayer securityLayer : securityLayers) {
				if (securityLayer.caught(securityLayer.depth)) {
					severity += securityLayer.getSeverity();
				}
			}

			return severity;
		}

	}

	static class Star2 {

		static int getResult() {
			List<SecurityLayer> securityLayers = getFormattedInput();

			int delay = 0;
			while (isCaught(securityLayers, delay)) {
				delay++;
			}

			return delay;
		}

		private static boolean isCaught(List<SecurityLayer> securityLayers, int delay) {
			for (SecurityLayer securityLayer : securityLayers) {
				if (securityLayer.caught(securityLayer.depth + delay)) {
					return true;
				}
			}

			return false;
		}

	}

	static class SecurityLayer {

		int depth;
		int range;
		int severity;
		int period;

		SecurityLayer(String line) {
			String[] values = line.split("\\W+");
			depth = Integer.parseInt(values[0]);
			range = Integer.parseInt(values[1]);
			severity = depth * range;
			period = ((range - 1) * 2);
		}

		boolean caught(int elapsedRounds) {
			return (elapsedRounds % period) == 0;
		}

		int getSeverity() {
			return severity;
		}

	}

	private static List<SecurityLayer> getFormattedInput() {
		List<SecurityLayer> securityLayers = new ArrayList<>();

		String[] lines = getInput().split("\\n");
		for (String line : lines) {
			SecurityLayer securityLayer = new SecurityLayer(line);
			securityLayers.add(securityLayer);
		}

		return securityLayers;
	}

	private static String getInput() {
		return "0: 4\n"
				+ "1: 2\n"
				+ "2: 3\n"
				+ "4: 4\n"
				+ "6: 6\n"
				+ "8: 5\n"
				+ "10: 6\n"
				+ "12: 6\n"
				+ "14: 6\n"
				+ "16: 8\n"
				+ "18: 8\n"
				+ "20: 9\n"
				+ "22: 12\n"
				+ "24: 8\n"
				+ "26: 8\n"
				+ "28: 8\n"
				+ "30: 12\n"
				+ "32: 12\n"
				+ "34: 8\n"
				+ "36: 12\n"
				+ "38: 10\n"
				+ "40: 12\n"
				+ "42: 12\n"
				+ "44: 10\n"
				+ "46: 12\n"
				+ "48: 14\n"
				+ "50: 12\n"
				+ "52: 14\n"
				+ "54: 14\n"
				+ "56: 12\n"
				+ "58: 14\n"
				+ "60: 12\n"
				+ "62: 14\n"
				+ "64: 18\n"
				+ "66: 14\n"
				+ "68: 14\n"
				+ "72: 14\n"
				+ "76: 14\n"
				+ "82: 14\n"
				+ "86: 14\n"
				+ "88: 18\n"
				+ "90: 14\n"
				+ "92: 17\n";
	}

}
