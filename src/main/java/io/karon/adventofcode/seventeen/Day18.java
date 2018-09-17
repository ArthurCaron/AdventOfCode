package io.karon.adventofcode.seventeen;

import java.util.HashMap;
import java.util.Map;


class Day18 {

	static class Star1 {

		static int getResult() {
			Operation[] operations = getFormattedInput();
			Map<String, Integer> registers = new HashMap<>();
			int frequencyLastSoundPlayed = 0;
			int rcvValue;

			/*

    snd X plays a sound with a frequency equal to the value of X.
    set X Y sets register X to the value of Y.
    add X Y increases register X by the value of Y.
    mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
    mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
    rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
    jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instruction,
    	an offset of -1 jumps to the previous instruction, and so on.)

Many of the instructions can take either a register (a single letter) or a number. The value of a register is the integer it contains; the value of a number is that number.

After each jump instruction, the program continues with the instruction to which the jump jumped.
	After any other instruction, the program continues with the next instruction. Continuing (or jumping) off either end of the program terminates it.
			 */
			int i = 0;
			while (i >= 0 && i < operations.length) {
				switch (operations[i].operationType) {
					case SND: {
						frequencyLastSoundPlayed = operations[i].getValue(0, registers);
						i++;
						break;
					}
					case SET: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, operations[i].getValue(1, registers));
						i++;
						break;
					}
					case ADD: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) + operations[i].getValue(1, registers));
						i++;
						break;
					}
					case MUL: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) * operations[i].getValue(1, registers));
						i++;
						break;
					}
					case MOD: {
						String register = operations[i].getKey(0, registers);
						registers.put(register, registers.get(register) % operations[i].getValue(1, registers));
						i++;
						break;
					}
					case RCV: {
						if (operations[i].getValue(0, registers) != 0) {
							rcvValue = frequencyLastSoundPlayed;
							return rcvValue;
						}
						i++;
						break;
					}
					case JGZ: {
						if (operations[i].getValue(0, registers) > 0) {
							i += operations[i].getValue(1, registers);
						} else {
							i++;
						}
						break;
					}
				}
			}

			return 0;
		}

	}

	static class Star2 {

		static int getResult() {
			return 0;
		}

	}

	private enum OperationType {
		SND("snd"),
		SET("set"),
		ADD("add"),
		MUL("mul"),
		MOD("mod"),
		RCV("rcv"),
		JGZ("jgz");

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

		Integer getValue(int index, Map<String, Integer> registers) {
			try {
				return Integer.parseInt(values[index]);
			} catch (NumberFormatException e) {
				return registers.computeIfAbsent(values[index], key -> 0);
			}
		}

		String getKey(int index, Map<String, Integer> registers) {
			registers.putIfAbsent(values[index], 0);
			return values[index];
		}

	}

	private static Operation[] getFormattedInput() {
		String[] inputs = getInput().split("\\n");
		Operation[] operations = new Operation[inputs.length];

		for (int i = 0; i < inputs.length; i++) {
			String[] operationData = inputs[i].split(" ");
			operations[i] = new Operation(operationData);
		}

		return operations;
	}

	private static String getTestInput() {
		return "set a 1\n"
				+ "add a 2\n"
				+ "mul a a\n"
				+ "mod a 5\n"
				+ "snd a\n"
				+ "set a 0\n"
				+ "rcv a\n"
				+ "jgz a -1\n"
				+ "set a 1\n"
				+ "jgz a -2";
	}

	private static String getInput() {
		return "set i 31\n"
				+ "set a 1\n"
				+ "mul p 17\n"
				+ "jgz p p\n"
				+ "mul a 2\n"
				+ "add i -1\n"
				+ "jgz i -2\n"
				+ "add a -1\n"
				+ "set i 127\n"
				+ "set p 622\n"
				+ "mul p 8505\n"
				+ "mod p a\n"
				+ "mul p 129749\n"
				+ "add p 12345\n"
				+ "mod p a\n"
				+ "set b p\n"
				+ "mod b 10000\n"
				+ "snd b\n"
				+ "add i -1\n"
				+ "jgz i -9\n"
				+ "jgz a 3\n"
				+ "rcv b\n"
				+ "jgz b -1\n"
				+ "set f 0\n"
				+ "set i 126\n"
				+ "rcv a\n"
				+ "rcv b\n"
				+ "set p a\n"
				+ "mul p -1\n"
				+ "add p b\n"
				+ "jgz p 4\n"
				+ "snd a\n"
				+ "set a b\n"
				+ "jgz 1 3\n"
				+ "snd b\n"
				+ "set f 1\n"
				+ "add i -1\n"
				+ "jgz i -11\n"
				+ "snd a\n"
				+ "jgz f -16\n"
				+ "jgz a -19\n";
	}

}
