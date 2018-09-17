package io.karon.adventofcode.seventeen;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


class Day18 {

	static class Star1 {

		static long getResult() {
			Operation[] operations = getFormattedInput();
			Map<String, Long> registers = new HashMap<>();
			long frequencyLastSoundPlayed = 0;
			long rcvValue;

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

		static int getResult() throws InterruptedException {
			Program program0 = new Program("p", 0L);
			Program program1 = new Program("p", 1L);

			LinkedBlockingQueue<Long> queue0;
			LinkedBlockingQueue<Long> queue1 = new LinkedBlockingQueue<>();
			int count = 0;

			do {
				queue0 = program0.continueProcess(queue1);
				queue1 = program1.continueProcess(queue0);
				count += queue1.size();
			} while (!queue0.isEmpty() || !queue1.isEmpty());

			return count;
		}

		private static class Program {

			int i = 0;
			Operation[] operations = getFormattedInput();
			Map<String, Long> registers = new HashMap<>();

			Program(String register, long value) {
				registers.put(register, value);
			}

			LinkedBlockingQueue<Long> continueProcess(LinkedBlockingQueue<Long> inputQueue) throws InterruptedException {
				LinkedBlockingQueue<Long> outputQueue = new LinkedBlockingQueue<>();

				while (i >= 0 && i < operations.length) {
					switch (operations[i].operationType) {
						case SND: {
							outputQueue.put(operations[i].getValue(0, registers));
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
							if (inputQueue.isEmpty()) {
								return outputQueue;
							} else {
								String register = operations[i].getKey(0, registers);
								registers.put(register, inputQueue.poll());
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

				return outputQueue;
			}


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

		for (int i = 0; i < inputs.length; i++) {
			String[] operationData = inputs[i].split(" ");
			operations[i] = new Operation(operationData);
		}

		return operations;
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
