package io.karon.adventofcode.seventeen;

import java.util.HashMap;
import java.util.Map;


class Day23 {

	static class Star1 {

		static int getResult() {
			Operation[] operations = getFormattedInput();
			Map<String, Long> registers = new HashMap<>();

			int mulCount = 0;
			int i = 0;
			while (i >= 0 && i < operations.length) {
				switch (operations[i].operationType) {
					case SET: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, operations[i].getValue(1, registers));
						++i;
						break;
					}
					case SUB: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) - operations[i].getValue(1, registers));
						++i;
						break;
					}
					case MUL: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) * operations[i].getValue(1, registers));
						mulCount++;
						++i;
						break;
					}
					case JNZ: {
						if (operations[i].getValue(0, registers) != 0) {
							i += operations[i].getValue(1, registers);
						} else {
							++i;
						}
						break;
					}
				}
			}

			return mulCount;
		}

	}

	static class Star2 {

		static int getResult() {
			Operation[] operations = getFormattedInput();
			Map<String, Long> registers = new HashMap<>();
			registers.put("a", 1L);

			int mulCount = 0;
			int i = 0;
			int count = 0;
			while (i >= 0 && i < operations.length) {
				count++;
				switch (operations[i].operationType) {
					case SET: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, operations[i].getValue(1, registers));
						++i;
						break;
					}
					case SUB: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) - operations[i].getValue(1, registers));
						++i;
						break;
					}
					case MUL: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) * operations[i].getValue(1, registers));
						mulCount++;
						++i;
						break;
					}
					case JNZ: {
						if (operations[i].getValue(0, registers) != 0) {
							i += operations[i].getValue(1, registers);
						} else {
							++i;
						}
						break;
					}
				}
			}

			return mulCount;
		}

	}
	private enum OperationType {
		SET("set"),
		SUB("sub"),
		MUL("mul"),
		JNZ("jnz");

		private String value;

		OperationType(String value) {
			this.value = value;
		}

		static OperationType getOperationType(String value) {
			for (OperationType operationType : OperationType.values()) {
				if (operationType.value.equalsIgnoreCase(value)) {
					return operationType;
				}
			}
			return null;
		}

	}

	private static class Operation {

		OperationType operationType;
		String[] values;

		Operation(String[] inputs) {
			operationType = OperationType.getOperationType(inputs[0]);
			values = new String[inputs.length - 1];
			System.arraycopy(inputs, 1, values, 0, inputs.length - 1);
		}

		Long getValue(int index, Map<String, Long> registers) {
			try {
				return Long.parseLong(values[index]);
			} catch (NumberFormatException e) {
				return registers.computeIfAbsent(values[index], key -> 0L);
			}
		}

		String getKey(int index, Map<String, Long> registers) {
			registers.putIfAbsent(values[index], 0L);
			return values[index];
		}

	}

	private static Operation[] getFormattedInput() {
		String[] inputs = getInput().split("\\n");
		Operation[] operations = new Operation[inputs.length];

		for (int i = 0; i < inputs.length; ++i) {
			String[] operationData = inputs[i].split(" ");
			operations[i] = new Operation(operationData);
		}

		return operations;
	}

	private static String getInput() {
		return "set b 99\n"
				+ "set c b\n"
				+ "jnz a 2\n"
				+ "jnz 1 5\n"
				+ "mul b 100\n"
				+ "sub b -100000\n"
				+ "set c b\n"
				+ "sub c -17000\n"
				+ "set f 1\n"
				+ "set d 2\n"
				+ "set e 2\n"
				+ "set g d\n"
				+ "mul g e\n"
				+ "sub g b\n"
				+ "jnz g 2\n"
				+ "set f 0\n"
				+ "sub e -1\n"
				+ "set g e\n"
				+ "sub g b\n"
				+ "jnz g -8\n"
				+ "sub d -1\n"
				+ "set g d\n"
				+ "sub g b\n"
				+ "jnz g -13\n"
				+ "jnz f 2\n"
				+ "sub h -1\n"
				+ "set g b\n"
				+ "sub g c\n"
				+ "jnz g 2\n"
				+ "jnz 1 3\n"
				+ "sub b -17\n"
				+ "jnz 1 -23\n";
	}

}

