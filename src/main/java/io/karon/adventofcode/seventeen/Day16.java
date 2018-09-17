package io.karon.adventofcode.seventeen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Day16 {

	static class Star1 {

		static String getResult() {
			String[] programs = getPrograms();
			List<Operation> operations = getOperations(getFormattedInput());

			for (Operation operation : operations) {
				if (operation.isSpin()) {
					programs = spin(programs, (Spin)operation);
				} else if (operation.isExchange()) {
					exchange(programs, (Exchange)operation);
				} else if (operation.isPartner()) {
					partner(programs, (Partner)operation);
				}
			}

			return programOrder(programs);
		}

	}

	static class Star2 {

		private static final int ONE_BILLION = 100000;

		static String getResult() {
			String[] programs = getPrograms();
			List<Operation> operations = getOperations(getFormattedInput());
			Tree tree = new Tree();

			for (int i = 0; i < ONE_BILLION; i++) {
				for (Operation operation : operations) {
					if (operation.isSpin()) {
						programs = spin(programs, (Spin) operation);
					} else if (operation.isExchange()) {
						exchange(programs, (Exchange) operation);
					} else if (operation.isPartner()) {
						partner(programs, (Partner) operation);
					}
				}
				if (tree.findAndAddDanseState(programs)) {
					int period = i;
					while (i + period < ONE_BILLION) {
						i += period;
					}
				}
			}

			return programOrder(programs);
		}

		private static class Tree {

			Map<String, Tree> subTrees;

			Tree() {
				subTrees = new HashMap<>();
			}

			boolean findAndAddDanseState(String[] programs) {
				return findAndAddDanseState(programs, 0);
			}

			private boolean findAndAddDanseState(String[] programs, int currentIndex) {
				Tree subTree = subTrees.get(programs[currentIndex]);

				if (subTree == null) {
					subTree = new Tree();
					subTrees.put(programs[currentIndex], subTree);
					if (currentIndex == programs.length - 1) {
						return false;
					}
				} else {
					if (currentIndex == programs.length - 1) {
						return true;
					}
				}

				return subTree.findAndAddDanseState(programs, currentIndex + 1);
			}

		}

	}

	private static List<Operation> getOperations(String[] formattedInput) {
		List<Operation> operations = new ArrayList<>();

		for (String input : formattedInput) {
			if (input.charAt(0) == 's') {
				operations.add(new Spin(input));
			} else if (input.charAt(0) == 'x') {
				operations.add(new Exchange(input));
			} else if (input.charAt(0) == 'p') {
				operations.add(new Partner(input));
			}
		}

		return operations;
	}

	private static String programOrder(String[] programs) {
		StringBuilder stringBuilder = new StringBuilder();

		for (String program : programs) {
			stringBuilder.append(program);
		}

		return stringBuilder.toString();
	}

	private static String[] spin(String[] programs, Spin operation) {
		String[] programsNewOrder = new String[programs.length];

		for (int i = 0; i < programs.length; i++) {
			int spinnedIndex = (i + operation.spinValue) % programs.length;
			programsNewOrder[spinnedIndex] = programs[i];
		}

		return programsNewOrder;
	}

	private static void exchange(String[] programs, Exchange operation) {
		int firstIndex = Integer.parseInt(operation.programIndexes[0]);
		int secondIndex = Integer.parseInt(operation.programIndexes[1]);

		swapIndexes(programs, firstIndex, secondIndex);
	}

	private static void partner(String[] programs, Partner operation) {
		int firstIndex = findIndex(programs, operation.programNames[0]);
		int secondIndex = findIndex(programs, operation.programNames[1]);

		swapIndexes(programs, firstIndex, secondIndex);
	}

	private static int findIndex(String[] programs, String name) {
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].equals(name)) {
				return i;
			}
		}
		return 0;
	}

	private static void swapIndexes(String[] programs, int firstIndex, int secondIndex) {
		String tmp = programs[firstIndex];
		programs[firstIndex] = programs[secondIndex];
		programs[secondIndex] = tmp;
	}

	private interface Operation {

		boolean isSpin();

		boolean isExchange();

		boolean isPartner();

	}

	private static class Spin implements Operation {

		int spinValue;

		Spin(String input) {
			spinValue = Integer.parseInt(input.substring(1));
		}

		@Override
		public boolean isSpin() {
			return true;
		}

		@Override
		public boolean isExchange() {
			return false;
		}

		@Override
		public boolean isPartner() {
			return false;
		}

	}

	private static class Exchange implements Operation {

		String[] programIndexes;

		Exchange(String input) {
			programIndexes = input.substring(1).split("/");
		}

		@Override
		public boolean isSpin() {
			return false;
		}

		@Override
		public boolean isExchange() {
			return true;
		}

		@Override
		public boolean isPartner() {
			return false;
		}

	}

	private static class Partner implements Operation {

		String[] programNames;

		Partner(String input) {
			programNames = input.substring(1).split("/");
		}

		@Override
		public boolean isSpin() {
			return false;
		}

		@Override
		public boolean isExchange() {
			return false;
		}

		@Override
		public boolean isPartner() {
			return true;
		}

	}

	private static String[] getTestPrograms() {
		String[] programs = new String[5];

		for (int i = 0; i < programs.length; i ++) {
			programs[i] = String.valueOf((char)(i + 97));
		}

		return programs;
	}

	private static String[] getPrograms() {
		String[] programs = new String[16];

		for (int i = 0; i < programs.length; i ++) {
			programs[i] = String.valueOf((char)(i + 97));
		}

		return programs;
	}

	private static String[] getFormattedInput() {
		return getInput().split(",");
	}

	private static String getTestInput() {
		return "s1,x3/4,pe/b";
	}

	private static String getInput() {
		return "x0/5,s2,x2/10,s11,x4/12,pg/k,x13/10,s11,x3/14,pe/p,s10,x8/4,s1,x6/15,s4,x14/12,pc/l,x5/0,s2,x9/15,s8,x8/1,pm/g,x15/4,s5,x0/7,pj/h,x13/10,s10,x1/14,s1,x0/2,s2,x7/14,s10,x2/5,pk/o,x15/13,s9,x5/8,pn/f,x15/2,s1,x6/4,pa/i,x8/14,pc/o,x5/7,s7,x3/12,s12,x9/15,pi/m,x8/12,s8,x10/14,s10,x5/2,pg/a,x3/14,s9,x7/8,pf/k,s5,x6/10,pm/p,x12/5,s14,x6/9,pe/c,x11/4,s6,x13/12,pb/i,x10/11,s8,x6/5,s1,x3/11,s5,x9/8,s1,x4/7,s14,x9/6,pp/k,s12,x2/4,s8,x3/12,s8,x11/1,pc/f,x14/9,pe/b,x15/10,s10,x9/0,s10,x6/14,s7,x4/9,s12,x3/15,s4,pc/o,x0/8,pl/k,x12/11,s15,x3/1,s3,x2/13,s7,x5/8,s6,x4/7,pm/b,x10/2,s8,x11/12,s8,x10/14,pe/j,x7/15,s6,x4/12,s10,x1/6,pm/g,x15/11,pk/n,x8/0,s5,pi/f,x4/11,po/p,s15,x2/3,pc/a,x11/4,pj/m,x7/8,s15,x0/5,s8,x9/15,pb/n,x0/14,s10,x11/1,po/p,x8/3,pg/k,x6/15,s8,x3/13,s13,x1/11,s11,x0/3,pi/a,x12/1,s2,x10/0,s7,pc/h,x12/9,pi/j,x1/13,s4,x0/7,s3,x9/8,s3,pa/f,x3/7,pl/j,x0/8,s11,x9/12,s10,x6/13,pe/a,s11,x9/7,pc/o,x3/1,pa/n,x2/7,s5,x15/12,pg/m,x10/3,s3,x15/13,s2,x1/11,pk/b,x12/6,s4,x0/13,pd/f,s8,x1/10,s13,x0/3,s8,x2/6,s9,x12/14,pp/i,x13/5,pb/f,s12,x12/15,pc/k,s1,x3/13,pb/l,x11/0,s11,x3/12,s2,pp/m,x1/15,ph/n,x0/12,s10,x6/4,s1,x1/5,po/k,s2,x6/2,s12,x8/12,pm/i,x1/9,s12,x3/10,s14,x13/0,s8,x14/2,pn/b,x5/0,s14,x1/8,s8,x15/11,pl/f,x5/1,s2,x3/13,pd/g,x4/10,s3,x15/7,s13,x9/12,pc/n,x2/13,s10,x6/5,pf/b,x12/1,pc/g,x4/11,s7,x8/0,s11,x15/3,pl/o,s13,x2/8,s1,x4/12,s3,pj/f,x2/14,pl/g,x15/13,pb/d,x10/14,s12,x8/9,s4,x13/4,pi/o,s1,x2/0,s14,x1/10,s7,x7/5,pf/n,s1,x6/4,pe/d,x15/10,s14,pc/l,x5/1,s2,x4/9,s11,x13/6,s5,x8/12,s15,x13/6,pb/g,x7/15,pj/h,x13/5,s11,x3/1,s8,x5/0,pl/i,x10/11,pd/f,x0/6,s3,x8/7,s4,pm/i,x1/0,s5,x9/6,s5,pn/b,x10/7,pc/h,x1/9,s9,x14/12,pi/m,x5/7,po/j,x14/10,ph/m,x3/0,s3,x11/13,pj/p,x3/10,s10,x0/15,s7,x12/10,s2,x7/5,pg/d,x1/2,ph/i,x3/6,pm/k,x1/7,s10,x11/6,s13,x7/13,s11,x15/0,s3,x5/3,po/c,s15,x2/4,pl/a,x8/7,s1,x12/0,s8,x1/9,pj/o,x7/15,s5,x2/10,s6,pa/f,x15/9,s3,pg/l,x13/12,pd/n,x14/6,s13,x7/13,s3,x1/6,ph/c,x3/12,s10,x9/8,s2,x3/6,pf/l,x8/1,pd/a,x4/11,s11,x9/14,po/i,x2/1,s1,ph/e,x11/9,s1,x2/7,s9,x11/1,s13,x0/8,pa/j,x15/12,s3,x6/14,pc/p,x7/9,pm/j,x5/11,s13,x8/10,s8,x6/4,s9,x12/5,pd/i,x11/1,s10,x6/10,s14,x7/11,s12,x12/2,s4,x5/11,pl/g,x13/15,ph/k,x9/11,s8,x7/2,s6,x5/11,pm/g,x14/15,s3,x11/9,pk/h,x12/15,s3,x5/14,pe/a,x2/12,s7,x15/14,s6,x10/5,s2,x1/0,pb/i,x4/10,pk/f,x9/13,pb/o,x2/0,s14,x7/15,s15,x13/5,s6,x9/14,s15,x12/10,s4,x9/0,s1,pg/d,s13,x12/7,pp/k,x13/1,s1,x12/6,s10,x13/7,pe/n,x1/4,s7,x11/12,s13,x8/13,pg/j,x2/12,s12,x4/14,po/d,x5/15,s6,x14/8,s15,x11/2,s4,x4/6,s15,x14/7,s11,x15/4,pf/m,x7/14,s13,x10/12,s3,x3/6,s7,x2/13,s8,x0/6,pj/a,x15/10,pb/d,s15,x7/2,s8,x5/11,s11,x6/15,s15,x13/1,s3,x14/5,s14,x15/3,pk/n,x14/8,s8,x3/6,s6,x8/11,pm/p,x12/14,pc/o,x13/3,s14,x8/4,pi/m,x7/14,pd/g,x9/0,s13,x15/4,s7,x10/9,s13,x6/13,s11,x5/1,s15,x6/14,s6,x9/3,pj/k,x1/13,s4,x7/8,s6,x10/2,pb/h,x9/4,pd/n,x15/11,s10,x1/9,s10,pp/f,x11/8,pj/h,x3/13,s8,x10/11,s8,x3/8,s11,x12/9,pn/p,s5,x0/2,s2,x1/11,pj/m,x0/14,s7,x5/15,s14,x11/2,pg/p,x4/7,pa/b,x12/14,s14,x15/6,s2,x14/9,s15,x3/13,s4,x2/8,ph/j,x4/6,s9,x12/10,pp/e,x1/4,ph/f,x12/11,s10,x4/2,s12,x3/1,pe/p,x14/11,s6,x7/6,s6,x1/5,pc/f,s11,x6/3,s8,pe/i,s5,x10/11,s9,x9/15,s15,x3/7,s11,x15/13,s1,x0/8,s12,x4/15,pk/l,x0/13,pe/m,x15/6,s9,x2/12,s3,x11/1,s4,x2/5,s7,x15/7,pn/k,x11/3,s4,x7/13,s3,x4/5,pf/m,x3/15,s10,x12/10,s4,x9/2,s12,pb/i,x14/1,s6,x11/13,s10,x3/7,s2,x1/12,s2,x13/10,s14,pf/e,s6,x2/12,s7,x14/1,ph/j,x12/8,pe/f,x5/0,pc/o,x3/11,s2,x1/5,pj/f,x6/0,s8,x10/1,pk/e,x5/8,pb/j,x7/10,s5,x12/9,pl/p,x0/1,pg/c,x9/11,s3,x12/1,s10,x2/7,s15,x9/11,pp/o,x3/6,s13,x11/14,s9,x10/7,pc/m,x12/6,s10,x5/9,s14,x6/2,pl/d,x12/15,s4,x0/14,pn/o,s13,x3/12,ph/c,x7/5,po/b,x12/0,s7,pp/j,x2/9,s2,x0/5,pb/k,x4/1,s2,x7/5,s11,x9/14,s11,x2/5,s13,x6/10,s9,x8/9,pa/g,x1/15,s9,x11/5,s6,x9/12,pd/j,x14/0,pf/k,x6/12,pl/e,x8/7,s8,x11/10,pj/c,x9/12,s2,x13/11,po/l,x7/5,pg/d,x1/14,s7,x13/0,s12,x6/15,s2,x12/13,s1,x14/6,po/e,x2/13,s10,x0/14,pa/k,s8,x11/1,pf/g,x7/2,s3,x3/6,s5,x1/4,s14,x7/13,s8,x14/0,pk/l,x13/11,s8,x2/9,s3,po/c,x3/5,s14,x14/2,s1,x8/6,s10,x15/7,pp/l,x12/5,pn/h,x9/14,s4,x12/13,pp/m,x7/15,pf/o,x12/8,s13,x4/11,s8,x10/14,pg/d,x3/5,s9,x10/2,po/k,x1/0,pc/n,x9/10,s3,x1/5,s4,x14/13,s6,x12/10,pb/f,x2/1,s1,x6/9,s3,x2/10,pj/l,x9/8,s10,x3/13,po/m,x2/1,s6,x15/3,s11,x2/6,s8,x8/7,pf/a,x9/2,s12,x14/5,pg/m,s12,x9/10,pj/e,x7/3,s12,pi/f,x2/8,pe/k,x3/7,s11,x15/12,pi/a,x10/4,s6,x1/2,s7,x13/4,pb/p,s14,pi/j,x12/10,s2,x11/1,s2,x8/14,s15,x4/11,s7,pb/g,s11,x2/3,s5,x15/8,pp/l,x1/7,s14,x15/12,s7,x2/6,po/k,x13/4,s3,x6/7,s8,x11/5,pm/n,x15/10,s12,x8/13,s10,po/e,x15/14,pi/j,x10/2,s6,x4/9,po/m,x5/7,s3,pn/f,x13/15,s9,x8/10,s7,x4/13,s3,x5/7,s15,x4/1,pl/d,s9,pj/e,x7/2,pk/a,x14/11,s11,x5/13,s11,x12/9,s11,pd/b,x6/10,s8,x4/2,s8,x9/8,ph/g,x1/4,pl/n,x12/7,pg/b,s14,x4/9,pe/j,x13/8,s7,pd/g,x9/0,s8,x10/1,s1,x8/0,s12,x7/1,s2,x6/15,s9,x5/2,pl/a,x6/0,s11,ph/f,x1/4,pn/a,x5/2,s5,x8/10,s2,x6/2,s5,po/l,x0/12,pe/b,x7/4,pg/d,x10/5,ph/i,x1/8,s14,pf/c,x4/13,pn/j,x0/5,s3,x15/14,pd/g,x6/13,s5,x10/12,pp/l,x6/15,s7,x12/13,s11,x8/14,pb/g,x6/10,s11,x2/11,pm/e,x5/6,pa/g,x7/11,pe/f,x13/8,s15,x7/9,s9,x5/15,pk/p,x1/10,s14,x15/8,s2,x1/5,s2,x11/4,s2,x0/2,s13,x11/5,s9,pb/c,x8/10,s1,x1/11,pa/l,x14/5,s7,x6/2,pg/n,x1/12,s12,x7/4,pj/h,x2/8,po/d,x3/6,s5,x12/1,ph/a,x13/9,s4,x7/3,s13,x6/9,s7,x13/7,s8,x4/1,pd/g,x7/9,s6,x0/8,po/p,x1/2,pi/h,x3/0,s2,x2/15,s14,x0/10,s15,x13/2,pj/g,x8/12,pm/k,x7/11,s13,x13/0,pa/n,x11/8,s10,x2/10,s12,x5/1,s12,x0/9,s10,x13/11,pj/l,x10/15,pe/b,x8/14,s13,ph/m,x13/1,s3,x6/11,s5,x10/13,s1,x9/15,pg/o,x5/0,s11,x4/6,ph/k,x14/2,pj/n,x3/11,s8,x6/9,s1,x12/1,s8,x2/4,s7,x9/0,pa/e,x8/7,s4,x3/12,pc/i,x8/1,pn/f,x2/5,s13,x9/12,s2,x11/5,s1,x1/14,s10,x2/7,pm/j,x3/9,pg/d,s13,x10/14,s4,x11/13,pi/h,x9/10,pd/p,x5/6,s10,x4/0,pe/k,x15/14,pm/d,x13/7,pc/h,s1,x6/2,pb/i,x8/11,pp/d,s2,ph/l,x9/13,pk/d,x14/2,pl/h,x0/9,s8,x2/10,s10,x11/9,pb/n,x15/10,s6,x14/3,s1,x10/4,s3,x7/11,pm/k,x12/5,pg/f,s2,x0/8,s1,x14/3,pb/k,x7/15,pi/h,x14/8,pc/m,x1/3,ph/i,x6/14,pc/g,x12/4,s7,x6/1,s3,x15/13,s11,x3/11,s2,x14/6,s13,x4/12,s12,x3/8,s5,x15/11,s2,x14/4,s10,x9/0,s14,pf/h,x11/8,s15,x6/12,s14,x4/9,s4,x2/14,pc/a,x9/5,s2,ph/g,x14/3,s13,x15/1,pb/j,x14/3,pe/n,x7/6,s6,x13/2,s6,x10/8,s1,x7/11,s10,x3/2,s4,x11/7,pl/g,x0/12,s3,x11/1,s5,x14/7,ph/i,x5/6,pe/m,x2/8,s1,x0/4,s11,x12/9,s13,x8/3,pg/p,s3,x4/6,s7,x1/0,s14,pe/c,x3/14,ph/p,x0/13,pn/b,x7/8,s3,x15/12,s14,x0/11,po/f,s8,x10/7,s11,x12/14,pn/p,x9/5,pf/i,x10/11,s8,x12/3,ph/p,x14/4,pk/m,x2/8,s2,po/p,x9/13,pf/h,x4/15,s8,x14/12,s8,x11/7,s15,pc/b,x14/3,s2,x13/6,s1,pm/d,x11/10,s7,x13/2,s9,pi/a,x10/3,pk/p,x2/14,pc/o,x13/3,s15,x4/12,s13,x10/15,s3,x6/13,pk/e,x0/14,s10,x7/10,s8,x1/9,s4,x12/10,pc/p,x8/5,pe/h,x6/14,s9,x7/3,s13,x6/10,s5,x9/4,s10,x0/1,s5,x8/9,pc/f,x12/2,s13,x15/8,pi/b,s15,x14/0,s8,x2/5,s5,x6/7,pf/a,x1/4,pc/h,x3/9,s15,x11/12,s13,x2/3,s7,x0/8,s6,x13/3,s13,x11/5,pg/k,x0/15,s15,x4/9,pd/l,s5,x10/12,s1,pj/i,x3/5,pn/e,x2/0,s9,x14/5,s12,x6/7,s7,x12/15,s10,x11/6,s4,x9/8,s6,x1/5,pp/o,x9/6,s5,x8/12,s4,x13/3,pg/m,x15/12,pl/k,s8,pa/m,x5/13,s4,x6/8,s15,x1/13,s9,x12/15,s15,x8/11,s12,x9/6,pk/h,x0/10,pf/i,x3/11,pj/e,s7,x5/6,pl/a,x4/3,pf/o,s12,x2/1,s6,x12/4,pk/j,x3/6,s8,x12/9,s13,x2/6,pn/b,x3/12,ph/f,x6/7,pb/c,s11,x9/3,s14,pl/a,x0/2,pg/k,x5/8,s3,x15/4,pc/f,x7/14,s9,x11/12,s6,x6/15,s6,x2/4,pk/m,x13/7,s5,x1/2,s13,x5/10,pb/c,x2/4,s4,x13/8,s11,x1/11,pp/i,x7/8,pc/j,x13/10,s4,x11/3,s7,ph/f,x1/10,pk/a,x11/0,s12,x6/13,s15,x10/15,s7,x2/13,s15,x10/14,s6,po/l,x12/0,s14,x13/6,s6,x11/5,s9,x12/6,pn/j,x3/9,s4,x10/15,s3,pd/e,x14/4,s1,x6/13,s9,x0/14,pa/j,x10/11,s15,x1/13,pm/e,x0/5,s11,x1/10,pa/i,x5/8,s12,pe/k,x15/4,s7,x14/8,s4,x15/13,pd/n,s13,x5/11,s3,x10/13,s1,x12/7,s13,x13/8,s3,x11/7,pb/a,x2/5,pn/k,x3/15,s2,x8/1,s15,x15/5,s7,x3/12,s8,x6/5,s14,x7/12,pl/d,x9/2,s1,x7/10,s9,x5/3,pg/n,x4/2,s14,x7/0,pp/d,x2/13,pn/a,x6/9,pe/k,x14/0,pp/i,x1/13,s5,x8/3,s11,x2/5,pf/g,s4,x13/10,s9,pm/e,s4,x14/4,pb/h,x2/5,s7,x0/13,pg/n,x11/12,pa/p,x0/9,s12,x1/15,pj/n,x3/0,s5,po/k,x5/4,s6,x2/13,pg/f,x1/3,pa/d,x12/9,s8,x6/13,pl/m,x5/15,ph/c,x1/7,s11,pf/i,x6/3,pp/d,x11/5,pa/h,x3/8,s13,x12/11,s10,x7/14,pb/o,x12/15,pl/p,x7/13,s9,x4/0,pc/i,x10/15,pm/k,s4,x2/14,pg/c,s4,po/n,x13/5,s8,pm/l,x9/15,s4,pj/d,x3/11,pi/p,x13/5,pa/b,x0/4,s6,pn/c,x1/8,s5,x2/5,s8,x11/8,s2,pb/a,x15/10,s1,x8/13,ph/m,x4/6,pd/a,s6,x1/8,s7,x14/10,s4,x1/13,s2,pk/f,x11/9,pb/l,x15/5,pg/j,x8/2,pn/e,x0/5,s10,x8/11,pj/c,x5/14,pi/h,x8/11,s9,x4/5,s13,x10/14,s15,x7/0,pc/j,x5/4,pg/a,s3,x9/7,s7,x12/8,s12,x0/1,s1,pd/n,s8,x4/14,po/l,x5/3,pk/n,x8/2,s10,x3/1,pg/d,x0/14,po/l,x11/12,pc/j,x4/1,s2,x3/10,pe/f,s5,x5/2,s13,x4/8,s15,x13/9,s4,x0/7,po/c,s13,x15/14,s3,x9/12,s12,x10/8,s13,x0/2,s8,x9/6,s14,x0/11,pi/l,x10/12,pb/h,x14/7,s15,x9/5,pf/e,x6/10,pj/d,x2/14,s7,x6/5,s13,x0/15,ph/m,x10/12,s8,x8/4,s8,x10/9,s14,x12/5,s5,x1/11,s3,x15/4,s10,x1/0,po/l,x6/3,s13,x13/2,pi/f,x10/9,s13,x1/14,pk/e,x8/5,s15,x10/3,pc/p,x4/5,s1,x6/10,pm/b,x15/1,s10,x13/7,s15,pp/k,x15/8,s4,x14/11,s3,x15/10,s3,x6/9,pe/i,x13/4,s10,x11/2,s10,x9/3,pn/d,x4/10,s6,x6/12,s12,x7/8,pf/a,x13/10,s6,x6/11,s3,x0/10,s15,x11/5,pi/d,x1/12,pp/e,x15/11,pf/h,x6/14,s9,x10/12,s5,x3/6,s11,x0/2,s9,x9/10,s11,x5/12,s8,x8/6,s11,x13/15,pn/m,x12/10,pe/j,x4/2,pc/o,x6/1,pn/h,x15/7,s4,pa/e,x2/12,pj/d,x0/7,s10,x6/12,s10,x0/14,s11,pi/c,x7/6,pk/h,x0/14,pp/g,x2/8,pc/f,x6/13,s14,x10/15,pg/m,x1/12,s13,x15/13,pn/a,x5/3,s15,x6/2,s3,x4/8,s13,pe/j,x11/13,s11,x15/9,s4,x12/2,s4,x4/14,s15,x13/9,s7,x1/14,s1,x5/3,s14,x4/11,pg/f,x0/3,po/i,x8/15,pc/f,x10/13,ph/d,x7/6,s15,x13/3,s14,pm/k,x5/0,pa/n,x4/1,pp/m,x2/15,pj/n,x12/8,pc/g,x0/10,pl/i,x1/5,s8,x6/11,s8,x14/2,pn/f,x12/0,s5,x8/4,s11,x5/15,pd/i,s1,x12/3,s8,x8/10,pg/h,x6/13,pm/j,x0/14,pb/e,x4/3,pm/a,x12/11,pb/n,x7/9,s2,x2/12,pf/h,s6,x11/10,pg/j,x14/9,s5,x8/12,s5,x6/11,s2,x8/14,s11,pp/n,x1/3,s10,x12/13,pd/j,s1,x8/11,s10,x6/4,po/p,x12/7,pc/a,x6/0,s7,x3/5,s1,x13/4,s11,x5/0,s13,x2/13,s2,x14/3,s8,x15/1,s12,pl/f,x3/8,pd/h,x9/7,s15,pg/k,x14/0,s4,x7/2,pe/h,x10/8,pd/g,s2,x6/7,s14,x3/9,s11,x4/0,s7,x7/6,s13,x10/13,pj/a,x14/12,s8,x6/5,ph/d,s9,x15/10,pn/g,x6/1,po/e,x12/10,pp/c,x1/3,s8,x6/9,pk/d,x5/1,pc/g,x10/3,s2,pd/m,s2,x4/2,s14,x5/1,s12,x7/4,pk/p,x2/1,s2,x12/0,s13,pc/d,x11/14,s3,x8/4,ph/f,x7/12,s3,x13/2,pa/e,s2,x11/15,s12,x10/4,pi/j,x9/13,s13,x12/4,pb/p,x3/8,s14,x14/10,s14,x6/4,pj/l,x3/0,pd/e,x11/13,s4,pp/o,s5,pj/c,x2/6,pp/n,x9/5,pi/h,x0/8,s1,x6/10,pm/j,x7/1,s5,x2/12,s11,x15/3,s7,x9/1,s13,x10/4,s8,x13/5,pe/g,x15/10,pk/n,x5/14,pa/f,x2/3,s11,x6/5,s11,x11/9,pe/p,x13/7,s6,x3/4,s2,x5/0,s2,x10/9,s14,x14/0,s14,x13/8,po/i,x10/7,s7,x3/15,s7,x4/11,pn/h,x15/0,s15,x12/1,s8,x8/9,pg/f,x13/15,s10,x7/6,pc/n,x9/13,s11,po/e,x15/2,s14,x9/13,s8,x5/1,pd/b,x9/0,s12,x4/1,s2,x5/7,s6,x2/14,s9,x7/12,po/c,x5/4,s6,x11/1,pj/d,x10/12,s1,x9/7,s6,x15/11,pp/k,x6/12,s9,x0/3,s9,x9/7,s8,x3/1,pb/g,x9/11,pe/f,x5/8,s5,x9/12,s12,x8/13,s12,x14/3,pp/k,x9/0,s15,x6/12,ph/o,x5/10,s3,x0/7,pe/i,x8/14,s3,x9/6,s9,x4/13,s12,pf/b,x2/3,s10,x7/13,s14,x12/8,pi/o,x15/14,s1,x0/7,pn/c,x13/14,s13,x10/7,s5,x3/8,po/p,x4/12,s5,x8/6,s15,x14/12,s9,x9/0,pd/j,x3/4,pe/n,x13/15,s15,pk/d,x11/1,s1,x4/5,s4,pj/l,s5,x2/0,pe/g,x15/5,pk/b,x9/11,s11,x7/1,s15,x11/12,pn/l,x8/2,s3,x5/3,pc/i,s6,x1/10,s8,x11/5,s12,x7/14,po/f,x10/8,s10,x3/7,s8,x0/10,pj/n,s7,x15/14,ph/c,x11/0,s11,x7/10,s2,x15/5,s2,x12/3,pf/m,x5/13,pb/n,x12/3,s11,x0/15,s14,x11/5,s9,x15/0,s4,x4/7,pa/f,x0/2,s15,x11/15,s3,x10/0,s10,x1/14,ph/p,x2/8,s12,x5/13,pi/f,x9/14,pk/d,s9,x7/15,s12,x0/14,pf/l,x3/12,pm/k,x11/4,s6,x2/9,pe/p,x7/0,po/i,x13/5,pn/p,x3/10,pf/h,x7/5,pc/m,x2/14,s4,x13/11,pg/f,x0/3,s7,x10/5,s7,x9/2,s11,x3/15,pk/o,x4/12,s13,x10/2,s8,pp/n,x7/9,s3,x3/5,s8,x10/0,s3,x5/4,pc/k,x7/3,s2,x0/15,pa/b,s14,x6/9,pe/f,x12/15,s15,pn/c,x1/4,s14,x10/0,s1,x15/3,pd/l,x12/13,s3,x15/8,ph/i,x4/2,s10,x10/12,s1,x13/7,pl/b,s13,x5/9,s15,x1/0,s6,x12/10,pn/k,x7/8,pi/d,s7,x9/4,po/c,x6/12,s3,x9/15,s10,pb/g,x3/5,pi/f,x7/11,pn/d,x4/12,s2,x8/13,s10,x1/0,s3,x12/4,pl/h,x3/11,s10,x9/2,s11,pi/e,x11/1,s12,x13/12,s9,x5/14,s3,x6/8,s12,x13/11,s5,x7/1,s8,x10/6,po/n,s13,x14/4,s15,x6/13,s14,x7/0,s2,x12/11,pb/l,x7/10,s15,x3/9,s9,x11/15,s5,x10/6,pj/i,x11/8,s14,x13/1,s11,x0/14,pk/h,x5/8,s8,x10/0,pm/n,x3/5,s7,x0/8,s10,x2/10,s10,x12/0,ph/p,x8/4,s8,pn/m,x12/0,pb/k,x14/7,s1,x6/2,s2,x9/8,s14,x1/3,s10,x10/8,s14,x9/4,s7,x3/1,po/e,x8/12,pa/b,x13/11,s5,x5/3,s14,x9/4,s11,pc/g,x13/0,s15,x3/10,s15,x12/6,s12,x15/9,pa/l,x1/11,s13,x13/14,s13,x8/10,pd/n,s4,x3/11,pb/p,x1/4,s1,x10/8,s7,x1/14,s4,x11/13,s15,x15/14,s8,x13/8,s4,x5/15,s11,x7/8,s14,x0/1,s2,x8/14,pl/h,x11/1,s6,x2/14,pj/m,x13/0,s14,x15/14,pf/p,x13/5,pm/b,x6/1,pk/j,x5/15,s6,x9/11,s8,x13/8,s2,x15/1,s15,x4/8,s11,x2/15,pg/n,x5/3,ph/f,s1,x6/15,s8,x10/1,s9,x8/6,s5,x4/1,s15,x15/8,s8,x10/3,po/i,x6/7,pd/e,s3,x0/5,s4,x13/2,pb/a,s5,x15/1,s11,x14/9,po/m,x4/3,s7,x6/12,s13,x7/5,pi/g,x14/0,s12,x12/10,s1,x13/1,pk/d,x11/14,s12,x0/3,s4,x10/2,s14,x0/12,pi/b,s4,x13/6,pc/j,x14/4,s12,pe/p,x2/13,ph/l,s15,x12/3,pe/n,s15,x6/5,pm/j,x4/13,ph/o,x3/5,s12,x10/1,s1,x6/9,s2,pf/l,s5,pi/o,x12/1,s15,x6/0,pj/m,x11/1,pg/p,x12/9,s2,x8/11,s15,x9/14,s7,x13/2,s14,x1/15,s13,x9/13,s13,x5/2,s5,x6/3,s2,x0/9,pk/o,x6/13,s10,x0/5,pp/a,s1,x3/6,s11,x15/13,pm/e,s2,x3/14,pg/b,x6/11,s1,x4/2,s11,x1/15,po/d,x5/10,s4,x8/7,pa/b,x11/15,pj/e,x8/4,s6,x2/3,pc/n,x13/8,pj/k,x5/2,s2,x15/13,pd/c,x11/2,pf/p,s9,x8/0,pj/a,x9/3,pp/e,s14,x4/10,s6,x0/7,pb/g,x10/11,pl/o,x14/7,s10,x0/11,pa/p,x15/6,s4,x5/10,s14,x14/9,pi/f,s11,pg/l,x4/6,pi/n,x14/2,s8,x8/0,pm/h,x13/2,s4,x9/3,s8,x11/2,s2,x15/3,pp/g,x1/2,s3,x7/9,s5,x10/14,s9,pm/n,x8/2,pj/f,x3/14,po/c,s13,x8/2,s1,x5/13,pf/b,x4/2,pd/j,x5/0,s12,x8/1,pe/b,x14/3,pp/h,x4/11,s7,x12/10,pi/k,x5/13,s7,x2/8,s6,x1/10,s3,x2/0,s2,x6/1,s12,ph/l,x13/10,s9,x9/12,s5,x3/2,po/g,x11/7,s5,x5/12,s10,x11/0,s13,x7/9,s4,x13/8,s9,pb/k,x6/1,s12,x0/7,s10,pe/j,x6/8,s10,x14/3,ph/n,x12/9,s7,x14/10,s11,x2/5,s3,x4/3,s12,x9/7,pf/k,x0/15,s11,x5/12,s6,x15/10,s9,x0/4,pp/b,x8/2,pd/o,x4/12,s13,x10/5,pe/l,x3/12,pm/d,x7/13,s13,x6/10,s11,x3/8,pf/l,x7/5,s7,x2/0,s11,x5/12,s4,x6/14,s1,x8/7,pm/j,x0/13,pi/h,x3/6,pb/o,x0/8,s12,x3/13,pp/g,x4/10,s14,pc/f,x9/15,s6,x11/5,pi/m,x14/4,s4,x12/11,pk/l,s3,x2/7,s15,x4/10,ph/g,s5,x8/12,pm/f,x14/2,s2,x5/11,s1,x8/3,s1,x5/11,po/a,x14/9,pp/c,x8/7,s3,x13/4,pk/e,x11/10,pi/d,x5/7,s1,x13/14,s7,x9/4,pb/h,x15/5,s12,x6/9,po/i,x7/8,pn/l,s6,x11/2,s8,x10/8,pk/f,x14/7,pn/p,x2/0,pi/h,x10/9,s14,x0/6,pg/j,s13,pf/n,x2/5,ph/a,s7,x9/8,s11,x1/7,pi/c,x11/4,s13,x12/1,s13,x11/5,s6,x0/10,pg/n,x12/14,pl/p,x7/3,s15,x13/0,po/e,x3/12,s3,x14/0,pf/h,x15/2,pe/b,x11/1,s10,x10/9,s14,x2/7,pc/f,x1/5,s4,x2/13,pl/i,x0/15,s12,x8/6,s8,x5/12,s2,x2/10,pj/e,x1/14,s14,x8/15,s7,x2/7,pk/a,x14/3,s5,x1/11,s1,x4/15,pb/j,x2/11,pd/p,x9/5,pm/j,x15/12,pp/e,x14/11,s10,x4/7,pg/k,s9,x13/2,s11,x3/8,pp/d,x15/10,s11,pb/h,s15,x1/5,pk/n,x12/7,s5,x15/8,s3,x10/7,pd/c,x1/5,pe/p,s4,x12/15,s12,po/d,x4/3,s11,x12/1,pe/g,s5,x3/0,s6,x15/13,s8,x0/12,pl/i,x8/5,s6,pk/e,x11/15,s9,x12/10,pj/i,x9/4,pa/c,x8/7,s7,pl/g,x1/14,s1,x9/4,s12,po/c,x15/3,pf/b,x11/6,s1,pi/e,s5,x0/10,pn/c,x5/13,s11,x12/0,pb/k,x1/2,s1,x4/14,s10,pn/f,x2/15,s8,x3/14,s11,x12/5,pg/m,s2,x4/10,s2,x3/8,pf/k,x11/10,pe/o,x1/4,pk/p,x3/0,s8,x4/14,pi/c,x8/13,s3,x4/3,s13,x10/13,po/p,s6,x5/15,pc/g,x3/7,s12,x15/13,pb/l,s10,x0/2,s13,pi/d,x7/10,s1,x2/15,s10,x5/9,s3,x0/1,pf/g,x5/6,s8,x9/0,pi/p,x1/11,ph/l,x14/7,s12,x15/0,s9,x7/12,pk/f,x1/5,s5,x3/9,s3,x4/11,pl/h,x14/5,pg/i,x8/1,pm/k,x3/9,s14,x1/4,s15,x12/11,pb/g,x2/13,s11,x9/10,s7,pn/p,x3/5,s11,x15/2,pd/g,x9/8,s7,x1/12,s2,x3/5,s1,pc/n,s1,x14/10,s12,x5/9,s5,x7/15,s8,x9/10,s1,x6/5,pk/a,x8/14,s6,x9/10,s3,x12/4,s6,x14/8,pi/f,x15/13,s8,pp/g,x12/6,pf/c,x10/2,s13,pm/a,x15/14,s1,x10/6,pj/d,x13/4,s11,x1/15,s1,x10/6,s11,x7/5,s3,x14/4,pn/f,x11/8,s1,x10/5,s4,x9/15,pb/h,x13/8,s10,x0/14,s7,x2/5,s5,x4/13,pp/f,x3/7,s14,x8/2,s2,x0/15,s13,x1/13,s11,x12/3,s14,x7/6,s10,x0/2,s15,x6/14,s7,x9/12,pg/j,x4/13,s3,x5/1,pd/b,s3,x2/12,s15,x9/4,pk/j,x15/11,s1,x4/8,pd/i,x2/14,s11,x10/8,po/a,x6/5,s3,x14/15,s7,x2/8,s3,x7/12,pk/p,x1/10,pm/a,x6/12,s8,x2/7,ph/k,x3/5,s4,x0/2,pc/i,x5/11,s7,x0/7,s9,x14/2,s1,x8/3,s3,x1/11,s9,x4/2,s13,x14/11,s6,x9/2,s10,x8/3,s13,x10/9,s1,x15/1,s13,pa/k,x11/10,s3,x15/8,s1,x12/1,pn/c,x4/0,pl/o,x3/10,pg/k,x13/1,s10,x15/6,pj/a,x3/13,s7,pp/d,x11/6,s1,x15/0,s14,x7/11,s4,x1/8,pj/e,x11/14,po/d,x7/4,s12,x2/8,pa/j,x13/11,s13,x10/0,s9,x7/12,s8,x3/11,pd/k,x10/4,s12,x15/5,s14,x7/11,s14,x8/12,pj/l,x10/2,pa/o,x9/0,s13,x4/11,pj/c,x10/3,pe/g,x1/11,s4,x14/0,s8,x13/12,pd/a,x10/4,s11,x12/8,pe/f,x4/11,pm/b,x13/8,pd/j,s3,x7/11,s12,x1/5,pk/l,x7/15,s7,x2/0,s8,x10/14,s6,x6/3,ph/i,x11/5,s6,x6/0,s4,x14/10,pf/k,s3,x11/2,s9,x12/15,s2,x2/3,s5,pp/i,x6/9,pc/d,x13/4,pi/f,x7/15,pm/a,x3/8,pe/c,x11/5,pk/f,x2/6,pb/n,x9/8,s12,x11/3,s1,x6/2,s11,x13/15,s4,x6/7,s4,x8/3,s9,x13/9,pd/g,x1/10,s11,x5/15,pe/j,x14/10,s10,x15/4,s2,x8/2,s11,x9/15,s10,pf/d,x4/2,ph/c,x8/15,s1,x7/12,pg/e,x15/2,s14,pp/a,s7,x12/1,pj/h,s10,x6/10,pc/p,x12/14,s3,x8/10,s8,x2/7,s10,x1/3,s13,x15/11,s2,x9/4,pj/b,x11/13,pa/n,x9/10,s1,x5/13,pm/i,s14,x12/7,s13,x9/2,s5,x10/11,pb/o,x5/3,s9,x7/9,pg/a,s2,x5/11,s11,x2/1,pj/h,x0/9,s10,x1/12,s3,x14/2,s8,x1/3,s6,x14/10,pb/e,x11/6,s10,x2/13,s10,x3/5,s9,x14/0,pf/l,x8/2,pk/i,x7/11,ph/f,x1/3,s5,x0/6,pi/k,x11/4,s15,x10/0,s3,x5/3,s5,x15/10,s11,x13/1,pm/e,x7/6,s6,x0/3,s5,x9/13,s9,x5/2,ph/j,s3,x1/12,pg/f,x15/9,po/a,s7,x11/8,pc/e,s1,x9/12,s10,x11/3,s14,x14/2,pl/m,x0/8,s15,x14/1,s6,x10/2,pa/g,x1/11,s6,x14/0,s3,x8/4,ph/d,x2/1,pf/c,x4/14,pp/e,x2/13,s3,x7/15,s13,x10/13,s13,x9/4,pm/j,x15/0,pk/a,x13/3,s15,x2/8,pn/i,x6/10,s3,x13/8,pg/o,x15/1,s2,x9/2,pi/k,x14/15,s12,x6/0,s1,x9/7,pm/b,s1,x2/1,s15,x12/14,pi/n,x9/8,s13,x10/13,s15,x1/6,s15,x2/11,s6,x7/8,s8,x13/5,pc/h,x8/0,s6,x9/4,pb/o,x13/14,ph/c,x8/5,s11,x3/15,s6,x5/1,s7,x7/8,s12,x13/1,s11,x2/7,s13,x8/5,s5,x2/1,s11,x11/15,pp/a,x12/5,po/c,x9/0,pn/m,x5/8,pd/f,x11/13,s8,x1/4,s13,x5/11,po/j,x4/8,s1,x12/15,s7,x14/2,s9,x6/4,ph/e,x5/12,s14,x2/13,pk/f,x9/3,pm/d,x7/1,pi/a,x8/0,pl/k,x4/1,pj/o,x13/0,s6,x15/2,pb/g,x11/13,s15,x12/8,s14,x6/13,pk/e,s2,x10/1,s1,x14/6,s2,x9/15,s3,x14/0,s14,x9/8,pp/i,x1/2,s4,x10/11,s13,x7/2,pg/e,x12/6,ph/p,x4/14,s5,x0/5,pc/j,s5,x13/3,s7,x4/14,pm/e,x7/15,s15,x14/13,pi/a,s9,x7/0,pe/c,x13/2,s12,x4/0,s14,x7/3,pi/m,x12/6,s1,pp/n,x8/14,s9,x12/10,po/f,x6/2,s5,x15/9,pj/n,x4/6,s7,x5/14,s2,x7/3,po/e,x0/1,s7,x6/12,pi/k,x1/8,pm/h,x0/6,pi/b,x8/10,pc/j,x14/6,s10,x2/1,s11,x14/13,s2,x10/7,pp/l,x3/1,s10,x12/5,s7,x4/6,pe/d,x15/11,s1,x1/12,s8,x8/10,s12,x7/12,pa/c,x2/8,pb/n,x0/6,s3,x8/13,s14,x2/3,s15,x8/5,s15,pe/i,x10/0,ph/k,x4/13,pf/o,x2/11,pg/i,x0/6,s3,x5/7,pa/h,x6/8,s8,x5/11,s11,x2/4,pi/l,x0/3,pe/g,x4/11,pl/h,x12/3,s2,x0/1,s14,x11/12,s9,x15/8,s3,x13/12,s7,x5/2,s6,x0/13,pp/e,x3/2,pf/c,x11/1,po/i,x15/13,pj/n,x6/9,s14,pf/c,x2/10,s2,x0/14,s4,x13/4,pb/e,x0/12,pf/n,x10/11,s10,pg/a,x8/1,s6,x11/2,pe/j,x14/5,s15,x7/2,s6,pd/a,x14/15,pp/f,x10/3,pc/g,x13/11,pe/p,x12/7,s6,x8/3,pk/f,x0/11,pl/m,x15/9,pk/d,x2/3,pe/l,x7/14,s7,x3/0,s4,pm/j,x7/5,pc/a,x15/6,pf/j,x9/5,s2,x8/4,pe/m,x15/6,pf/p,x11/10,s4,x8/4,s7,x7/14,s13,pa/e,x3/12,s1,x9/7,s14,x10/15,s14,x9/11,s7,x10/1,s10,x8/3,pg/l,s11,pb/f,x13/2,ph/c,s9,x10/11,s10,x15/0,s12,x5/1,pk/g,x8/2,pp/n,x7/9,s12,x0/5,s2,x2/12,pl/i,x14/11,s10,x6/1,ph/a,s3,pn/f,x3/7,s3,x10/4,s4,x2/5,s1,x12/0,s3,x10/9,s1,x13/8,pi/b,x5/9,s15,x11/0,pg/j,x7/2,s4,x0/6,pc/h,x12/15,s13,x11/5,pa/j,x13/8,s13,x6/10,s3,x3/9,s3,x15/8,pl/h,x0/14,s1,x10/2,s15,x13/3,s11,x11/0,pc/a,x13/7,s13,x8/2,s9,x6/12,pe/b,x10/7,s12,x8/1,pf/c,x13/15,s4,x10/14,s2,x5/9,s3,x10/13,s5,x3/9,pe/i,x10/2,s6,x12/7,pd/g,x10/4,pi/f,x5/11,pj/k,s15,x12/0,s4,pd/b,x3/1,pc/e,x13/8,s7,x11/1,s10,x14/0,ph/k,s6,x5/11,s9,x8/14,s14,pj/o,x1/13,pa/k,x6/2,s2,x10/14,pn/l,x1/6,s14,x7/3,s2,x5/2,pi/o,x4/13,s15,x10/2,s13,pc/n,x5/0,po/e,x4/2,pc/d,x14/11,s4,x5/2,pk/e,x4/13,s2,x0/1,pg/d,x12/3,s7,x6/8,pj/l,x2/10,s6,x11/14,pm/p,x5/7,s3,x11/3,pl/b,x10/7,s2,x6/5,pa/p,x15/13,s7,x2/6,pd/f,x13/9,s15,x7/5,pj/i,x13/0,pm/c,x7/6,pf/e,x2/5,pk/j,x8/11,pb/f,x10/1,pl/m,x14/5,pg/c,x0/1,s5,x8/13,pk/f,x0/7,s13,x9/13,s1,x10/14,pn/c,x13/3,pd/b,x10/8,pp/j,x15/13,s7,x5/6,s6,x11/8,s2,pm/o,s13,x13/14,pd/h,x3/8,pn/b,x0/12,s9,x1/8,s3,x3/2,s8,x13/1,s1,x4/15,po/a,x6/13,s1,x12/8,pc/k,x10/0,pa/p,s1,x2/6,pc/b,x11/7,s10,pl/p,x1/13,s9,x15/10,pj/n,x8/12,pd/b,x5/14,pk/e,x12/2,pp/h,x4/8,s10,x0/9,pl/f,x10/14,s6,x8/6,pk/h,x13/4,pb/l,x1/5,s3,x6/8,s11,x9/7,pk/g,x15/5,s2,x11/10,s2,x0/8,pn/p,x10/11,s6,x1/5,s12,x3/0,pk/d,x11/8,s2,x15/7,s15,pf/b,x4/14,s5,x10/0,s11,x3/14,s10,x7/11,s4,x15/5,s3,x3/8,pi/g,s6,x2/13,s2,x3/12,pj/m,x0/5,s7,x12/1,s8,x0/2,s9,x15/6,pd/h,x9/8,pf/m,x15/6,pa/o,x13/0,s10,x1/2,s10,x7/0,s9,pp/e,x13/10,pc/d,x1/6,s5,x13/3,pe/h,x14/6,s14,x0/13,s5,x9/1,s3,x4/0,pa/o,x6/7,s14,x8/14,s14,pm/l,s5,x15/10,s7,x2/0,s12,x3/12,pk/p,s8,x6/8,s14,x1/10,pf/o,s11,x0/7,s13,x3/8,s10,x9/2,pj/i,x8/15,s14,x13/5,s3,x14/11,pp/m,x1/8,pa/c,s7,x11/15,pp/g,x12/6,po/k,x7/5,s5,pp/f,x15/1,s3,x7/2,pe/g,s14,x15/14,pc/f,x13/11,pk/i,s8,x9/0,s12,x7/12,pn/l,x5/15,s1,x9/7,s10,x5/11,ph/e,x12/3,s8,x4/2,pf/i,s8,x7/15,s11,x5/0,pp/m,x14/6,s3,x15/10,s3,x1/12,pb/k,x15/11,pi/d,x3/12,s10,pj/p,x15/7,s3,x5/3,pm/f,x2/8,s11,x12/1,s9,x7/15,pi/d,x1/2,s14,x6/10,pj/c,x7/11,s2,x6/9,pp/m,x8/11,s2,x1/7,s3,x14/6,pl/h,x12/4,pg/f,x0/3,s12,x14/1,pc/i,x9/4,pb/k,x0/15,pl/o,x8/2,s11,x11/4,pb/m,s11,x13/7,s10,x5/10,s9,x8/15,pd/j,s9,x0/9,pf/l,x4/13,s13,x5/15,pm/d,x10/13,pb/n,x6/12,s15,x4/2,s8,x6/15,s12,x9/5,pf/h,x2/14,s8,x12/0,s10,x15/6,s1,x11/13,pk/n,x9/6,pc/h,x4/2,s5,x10/14,s2,x8/4,pj/m,x11/5,s14,x15/0,s7,pl/b,x8/13,pk/p,s4,x9/14,pc/g,x10/6,pm/p,s15,x2/0,s13,x8/10,pa/g,x0/5,pd/m,x10/6,s9,x3/12,s8,x13/4,pc/a,x10/14,s11,pb/h,x1/12,pe/k,x11/10,s7,x6/0,pd/b,x5/9,pm/n,x2/7,s15,x10/0,s5,x13/2,po/i,x4/1,pn/h,x6/14,s3,x7/4,s9,x10/13,s2,x5/7,s14,x8/12,pi/g,x6/0,s15,x11/15,po/n,x8/4,pa/p,x12/15,s10,x13/0,s9,x11/5,pj/k,x12/3,pc/f,x9/13,s15,x1/11,s1,x15/9,s15,x14/4,s12,x12/8,s7,x14/9,s1,x8/15,s14,x11/4,s10,x8/5,pg/e,s9,x4/0,s1,x5/15,s6,x12/4,s4,x0/15,pd/h,x10/7,s13,x13/5,pp/n,x8/15,s15,x4/3,pk/b,s9,x11/5,s10,x6/8,pp/o,x0/2,pn/j,x11/15,pa/f,x10/14,s8,x9/12,ph/l,x0/5,s6,x14/10,s6,x7/4,s9,x15/3,pg/b,x7/6,s2,pf/o,s4,x1/8,pp/m,s5,x0/3,s9,x9/11,s3,pi/l,s15,x10/13,s10,x7/2,pm/d,x10/11,pa/j,x14/7,s13,x11/4,pp/f,x10/3,pe/j,x12/1,ph/c,x14/0,pi/a,x12/8,pf/m,x9/6,s1,x8/4,s11,x10/7,pe/p,s5,pl/h,x8/15,s11,x14/7,pn/c,s12,x12/8,pe/p,x2/4,s8,x6/8,pj/g,x4/10,s1,x9/0,s5,x10/14,s14,x9/2,s2,x6/13,s2,x12/14,s4,pc/p,s3,x15/5,s9,x12/3,s4,x0/5,pl/n,x4/2,s2,x14/3,s3,x11/13,s15,x7/4,pk/f,x11/1,s9,x4/3,s13,x7/9,pg/h,x8/3,s14,x10/15,pd/n,x1/11,pb/o,x10/7,s14,x8/6,pf/i,x13/4,s7,pn/k,x14/3,s2,po/a,x11/7,s5,x6/13,pg/f,x4/5,s12,x11/8,s12,x6/0,pm/n,x8/10,ph/i,x15/13,pj/m,x14/2,pa/h,x9/15,s6,x2/6,pe/i,x3/7,ph/p,x13/4,s9,x5/7,pa/b,x15/13,s4,x0/12,s12,x5/4,pf/e,x8/2,s3,x4/10,s7,x11/1,pi/o,x6/3,s1,x4/9,s10,x14/8,s14,x2/5,s8,x1/12,pd/a,x13/0,s3,x3/5,pf/g,x2/1,s2,x0/4,po/e,x3/12,s14,x5/8,pg/f,x3/15,s4,x13/1,pl/d,s6,x14/11,s5,x0/10,ph/b,x8/13,s7,pl/m,x5/7,s14,x11/15,pf/j,x0/5,s1,x6/1,s4,x9/8,pg/l,x7/1,s3,x4/5,s12,x12/15,pn/p,x3/2,pj/d,x9/10,s10,x5/2,s12,x3/7,s4,x8/15,s9,pk/h,x1/14,s3,x8/0,s9,x3/13,s14,x2/1,s4,x7/6,s4,x12/5,pi/m,x6/13,ph/n,x9/12,s15,x3/14,s11,x0/2,s8,x5/3,pi/l,x2/8,pe/p,x5/7,pl/n,x1/11,pm/k,x15/14,pg/d,x7/4,s2,x15/8,po/p,x6/10,s9,x8/15,s4,x1/3,pl/j,x4/0,s15,x1/3,pe/n,x4/7,s7,x11/12,s9,x13/6,pk/o,x0/11,s15,x8/15,s6,x3/4,s3,x14/1,s11,x4/11,s5,x12/14,s7,x9/5,s15,x1/6,s4,x14/3,s14,pd/p,x15/9,po/m,x7/10,s3,x4/6,s11,x7/5,s10,x2/1,s6,x6/12,s11,x5/1,pp/i,x3/9,s10,x15/1,s15,pf/b,x7/11,pi/p,x10/0,s8,x4/2,pg/n,x14/15,pa/j,x8/0,s7,pb/g,x11/2,s9,x4/1,pc/p,x13/14,s13,x6/1,s2,x5/12,s13,x7/9,s14,x11/1,s6,x15/4,s1,x0/8,s11,x12/9,s7,x3/0,s4,x2/9,s6,x12/8,s12,x4/13,s11,x7/15,pn/b,x8/14,pc/j,x10/2,s3,x6/13,s9,x14/5,s10,x10/2,s10,x3/15,s12,x6/5,s15,x0/7,s11,x5/15,s3,x14/9,s15,x5/2,s12,x11/10,s2,pn/k,s12,x9/8,pc/g,x5/15,s6,x4/7,s1,x9/10,s9,x12/0,s14,pn/h,x6/1,pb/m,x3/8,s11,x0/6,pi/f,x5/2,pd/h,x0/9,pk/n,x2/12,s14,x5/10,pd/a,x4/2,s5,x6/1,po/k,x15/7,pa/h,x8/13,s15,x12/5,pj/m,x11/1,pn/i,x14/12,s1,x3/4,s10,pd/c,x8/9,pj/a,s15,x4/3,s11,x7/10,pn/i,x4/12,s15,pp/b,x14/0,s1,x1/4,s1,x7/2,pf/l,s7,x10/14,s2,x9/6,pj/c,x0/11,s12,x7/10,pf/i,x9/12,s3,x3/2,s6,x7/11,s5,x10/13,s11,x8/12,pc/d,x5/13,s4,x11/14,pn/f,x12/3,s9,x11/9,s14,x14/3,pa/c,x4/0,s1,x12/6,pi/f,x8/9,s8,x4/6,pm/p,x1/14,s13,x4/5,pc/k,s12,x13/11,pp/b,x1/4,s15,x13/0,s10,pe/k,x4/9,s6,ph/i,s8,x7/5,s15,x4/15,pm/e,x3/7,s14,pl/c,x5/6,s1,x4/3,pi/b,s2,x9/5,s12,x0/10,pl/h,x12/13,pj/i,x0/15,s3,x5/13,s2,x6/12,pf/h,x7/15,s3,x10/3,pj/e,x4/1,s8,x5/13,pk/m,x10/1,pc/g,x15/12,s3,x3/7,pb/d,x6/1,pa/m,x10/0,pg/f,x14/8,s11,x3/2,pn/o,s9,x7/4,s9,x15/14,s3,x3/11,pa/c,x12/0,s6,x15/13,s10,x8/7,pg/e,x0/9,s10,x14/2,s6,x6/0,s3,x1/5,s12,x8/9,s7,x3/4,s1,x13/11,s10,x8/10,s15,x4/5,pc/n,x11/10,s14,x4/2,ph/b,x0/15,pk/i,x4/1,s3,x14/2,s8,x1/8,po/b,x15/10,pn/h,x12/4,pk/g,x1/15,s12,x11/9,s10,x1/4,pb/a,x15/3,s8,x10/11,pn/i,x1/7,s2,x2/14,s14,x7/3,pk/a,x11/12,s13,x10/1,pc/l,x14/9,s5,x15/3,pm/g,x8/2,s11,x15/3,s7,x13/10,pf/o,x8/14,pi/n,x15/12,s9,x8/10,s2,x13/3,s6,x14/4,s8,x12/13,pd/l,x11/10,s8,x8/1,s4,x13/2,pj/g,x4/11,s7,pf/e,x8/9,s3,x1/14,s12,pn/d,x15/6,s10,x5/13,s7,x11/7,s13,x10/5,s4,x9/4,pb/p,x14/0,ph/f,x11/6,s1,x0/15,s9,x11/9,s9,x3/1,s9,x11/9,pl/j,x5/14,pc/m,x10/9,pi/h,x13/3,s7,pf/p,x15/0,pa/i,s1,x11/4,ph/f,x0/1,s14,pl/j,x4/10,s1,x6/15,s4,x4/11,s2,x5/1,s14,x14/2,s9,x4/9,pk/g,x15/0,pc/o,x1/5,s12,pa/g,x12/4,pk/j,x6/3,po/b,x2/5,s13,x6/8,s6,x13/5,pe/j,x11/7,s12,x1/8,pb/k,x5/12,s1,x14/1,pn/p,x13/10,s13,x11/5,s5,pb/i,s13,x2/0,pj/k,x15/4,s9,x7/2,pd/p,x4/3,pn/h,x9/13,pe/i,x10/2,po/b,s10,x9/12,pf/e,x11/10,pl/c,x3/4,s11,x9/14,s10,x1/2,s3,x13/14,pi/p,x3/0,s1,x11/2,s7,x12/8,s8,x5/9,pg/c,x14/15,s3,pj/n,s2,x6/1,pa/f,x2/0,s8,x14/4,pk/j,x12/13,pp/a,s7,x1/7,pf/n,x5/3,s1,pk/h,x15/9,pe/n,x3/4,s11,x13/8,pi/k,x0/15,ph/a,x5/13,s9,x14/3,s5,x15/2,pc/g,s15,x4/10,s9,x8/13,s7,x2/15,s14,x10/4,pd/i,s4,x9/3,pl/b,x13/10,pc/a,x14/3,s8,x8/5,po/k,x3/15,pl/c,s15,x13/14,pa/b,x10/3,s12,x13/12,pp/l,x2/14,s7,x6/5,s6,x4/15,s9,x13/7,s13,x0/5,s13,x11/10,s2,x15/8,s8,x11/14,s6,pk/h,x10/12,s4,x2/4,s10,x10/8,pp/o,x15/12,s7,x2/7,s12,x11/12,s9,pl/j,s13,x15/0,s12,x7/9,s10,x10/8,s7,x3/15,po/p,x0/4,s3,x5/3,pi/m,s13,x14/15,s14,x1/11,s15,x5/10,s14,x6/12,s6,x10/13,s2,pp/b,x14/7,pf/m,x13/6,s14,x14/15,s10,x4/2,s2,x15/12,s9,x4/11,pj/b,x13/2,pm/n,x1/4,s6,x9/0,s3,x10/7,s14,x1/3,s6,x4/12,s13,x13/9,pe/h,x6/2,s14,pa/b,x13/11,pp/m,x2/15,s11,pb/j,x8/3,s9,x0/1,pm/l,s9,x2/6,s5,x0/7,s3,x5/1,s12,x8/10,s13,po/n,x2/5,pb/f,x8/6,pm/h,x12/3,pl/k,x10/0,pj/n,x5/12,s10,x2/9,pc/a,x12/1,pb/i,x7/5,pc/a,x4/11,pf/e,x12/9,pb/d,x2/3,pp/o,x1/15,s4,x13/9,s5,x5/12,pg/d,x15/4,s15,x5/9,pk/c,x12/1,pm/l,x8/10,s12,pj/e,s13,x13/12,s14,x5/8,s2,x2/6,s6,pd/i,x9/8,s2,x11/10,s4,x13/15,pm/j,s10,x7/6,s10,x4/9,s2,pa/l,x13/6,s11,x4/15,s7,x6/12,s3,x0/5,s12,x9/12,pn/c,s12,pk/h,x13/15,s2,x11/3,pl/e,x0/7,pi/m,x11/2,s7,x3/1,s5,x15/4,s13,x0/10,s8,x12/3,s2,x5/4,s15,x9/1,pn/d,x2/14,s14,x13/12,s14,x5/4,pp/j,s9,x10/7,s14,x4/3,s4,x11/5,s6,x14/3,s11,x15/11,pd/i,s1,x4/6,s6,x11/0,pp/m,x8/12,s2,x6/9,s7,x12/1,s1,x0/6,pc/f,x14/8,s9,x9/2,pm/i,s15,x10/15,s12,x1/8,pn/o,x9/14,s13,x10/12,s5,x7/3,pi/d,s4,x1/8,pn/f,x10/4,pp/e,x2/14,s11,x5/15,ph/j,x9/4,s15,x5/10,s7,x4/6,s7,x7/1,pk/m,x3/5,pg/i,x8/13,s9,x1/14,s14,x7/13,s9,x11/3,s2,x7/10,s12,x5/0,pm/o,x11/6,s3,x3/7,s9,x15/4,s12,x9/1,pi/l,x3/13,s7,x7/5,s10,x3/1,s9,x4/10,s8,x11/2,pg/d,x14/13,s10,x0/12,s6,x11/13,s9,x4/15,s2,x8/11,s12,x10/4,s14,x3/5,pi/j,x0/11,s14,x7/10,pf/n,x15/12,pe/j,x4/13,pl/a,x5/3,pd/f,x1/9,s1,x7/2,s14,x10/3,po/a,x14/15,pm/e,x1/9,s10,x10/12,pi/p,s7,x8/0,s7,x5/12,pa/k,s5,x9/6,s7,x15/3,ph/d,x9/12,pi/l,x10/15,s10,x8/7,pc/b,x1/9,s4,x13/15,s11,x3/2,pk/l,x7/15,s11,x6/10,s13,x5/7,s3,pd/i,x3/2,pm/e,x7/1,pg/k,x6/0,s12,x14/10,s2,x6/11,s2,pp/l,x13/0,s13,x8/11,s7,x1/15,pg/i,x3/6,s11,x4/8,pb/a,x10/7,pg/n,x12/1,s1,x15/11,pi/l,x9/6,pf/o,x3/1,s15,x13/8,s5,x3/15,s10,x4/13,s3,x1/8,pk/d,x6/13,pg/j,x15/8,s14,x7/14,s11,x15/13,ph/f,x11/4,s8,x8/6,s6,x13/3,pm/i,x6/14,s7,x10/4,s7,x7/12,pl/b,x4/13,s12,x5/1,s9,x14/11,s5,pa/e,x13/12,s13,x2/8,pg/i,x15/14,pm/j,x12/7,s9,x1/6,s8,x10/7,s11,x0/3,pp/a,x8/12,s14,x6/10,s14,pk/g,s9,x13/1,s1,x6/0,s6,x3/12,pc/j,x9/7,s13,pg/e,x15/3,s3,x8/11,pc/i,x0/2,pn/k,x5/7,pi/d,x15/8,s1,x10/6,pn/o,x1/3,s6,x7/10,pi/a,x3/9,s9,x14/5,s10,po/j,x9/15,s7,x3/11,pa/g,x8/2,s7,x11/7,pc/f,x13/8,s6,x5/11,pp/h,x14/7,s12,x1/11,pa/c,x15/10,s5,x14/7,s14,x5/6,pp/k,x0/1,s7,x12/6,s13,x10/2,pa/m,x12/0,pd/h,x1/2,pn/e,x9/10,pf/l,s1,x12/1,pk/h,s5,x9/14,s14,x6/12,po/e,x3/5,s15,x15/12,s12,x2/10,pl/j,x11/5,s9,x12/14,pc/i,x6/9,pm/l,x12/2,pb/g,x3/8,pe/m,s2,x9/12,pj/n,x7/14,s6,x0/13,s13,x5/11,s9,x2/1,s5,x7/13,s8,x12/5,pd/b,x6/0,s13,x9/12,s15,x15/3,s7,x2/1,pc/m,x11/4,s9,x3/2,s3,x0/12,s3,x5/10,s6,x9/1,ph/n,x3/8,pc/g,x0/5,pe/b,x3/2,s15,x6/8,s14,x0/11,pm/p,s13,x9/10,s10,x12/13,s5,x3/0,s1,x1/9,pg/n,x2/6,s6,x7/0,s2,x2/11,s3,x3/14,s14,x12/2,s11,x1/6,pk/h,x11/14,pg/p,x0/7,pn/d,x13/4,s12,pk/a,x14/3,s9,x9/1,s2,x4/0,s8,x11/13,pi/n,x12/3,s11,x11/2,pp/f,x13/10,s10,x9/15,ph/g,s4,x13/12,pi/d,s1,x6/10,s3,x9/12,s3,x14/2,pl/g,x6/15,s7,pi/n,x2/8,s4,x3/0,pj/e,x13/9,s1,x14/0,s9,x13/6,s2,x10/2,s15,x14/5,s8,x9/12,s7,x1/4,pa/k,x14/9,pi/d,x11/4,po/f,x15/7,s9,x5/6,pd/e,x12/8,s8,x0/5,pf/n,x2/9,s12,x5/0,pe/k,s15,x14/2,s1,x13/7,s13,x8/12,s4,x9/7,s11,x2/13,s15,x0/11,s7,x7/4,pj/a,x5/15,s13,po/l,x0/6,pa/g,x8/1,s15,x5/11,s10,x0/1,s2,x12/6,pe/f,x0/5,s11,x12/3,s5,x8/1,s10,x12/14,s10,x4/13,pg/a,x14/3,s7,x13/15,s11,x12/7,s3,po/f,x9/6,s8,x7/1,pn/c,s7,x11/4,s5,x14/7,s7,x12/8,s11,x11/10,s4,x12/0,s10,po/e,s12,x13/2,s15,x5/4,pj/m,x1/14,s15,x0/10,pi/p,x9/7,pb/f,x0/8,s13,x5/4,s8,x13/9,s5,x10/2,s10,x13/3,pg/h,x4/10,pp/e,x13/14,s8,pk/l,x6/4,s3,x10/1,pd/p,x11/5,s14,x14/3,s4,x5/6,s3,x8/0,s4,x9/12,s9,x2/10,pj/a,s11,x7/15,pb/i,x14/10,s15,x0/11,s1,x2/10,pm/g,s12,x9/13,s5,x8/5,s8,x4/10,s9,x7/0,s9,x11/5,s1,pa/l,x10/13,pf/m,x8/14,pk/l,x5/11,s7,x13/10,pn/e,x1/7,s8,pk/f,x15/11,s4,pj/l,x5/9,s7,x13/6,s11,po/e,x2/15,s2,x13/11,pn/p,x6/8,s9,x1/7,pc/f,x0/13,pj/o,s5,x11/2,s15,x10/13,s15,x3/14,s9,x12/9,pn/h,s9,pg/m,x1/5,pn/p,x2/15,s8,x9/11,s3,x6/3,po/l,x1/2,pe/g,x5/8,pa/n,x3/7,s12,pi/h,s3,x4/5,s4,x3/15,s11,x13/12,po/m,x9/10,s5,x13/1,s7,x14/2,s3,x13/4,pe/f,x1/15,s14,x9/3,s9,pp/m,x2/8,s8,ph/b,x11/13,pn/j,x1/7,s15,x4/5,pc/e,x12/14,pi/j,x5/13,s8,x2/6,s9,x7/10,pd/p,x5/2,s4,x4/1,s10,x14/10,pb/g,x11/2,ph/f,x0/6,pn/g,s13,x1/5,s15,x15/12,pm/b,x14/13,pa/o,x6/0,pn/l,x3/11,s10,x8/0,po/d,x13/15,s11,x0/9,pe/a,x10/15,pg/f,x3/11,pm/j,x0/5,pd/a,x6/1,ph/e,x14/12,s8,pc/g,x4/5,s6,x15/12,s4,x8/4,s8,x10/12,pa/o,x9/5,pb/i,x3/15,pa/p,x1/2,pd/l,x9/3,pb/j,x0/1,pk/n,s2,x15/10,pb/a,x0/8,pj/p,x3/15,pn/l,s4,x4/13,s2,x8/15,pc/p,x5/12,s13,x13/8,pm/o,x15/2,pi/c,x8/4,pa/h,x3/9,s7,pi/g,s3,x4/10,s9,x11/7,s15,x8/2,s1,x13/4,pl/c,x5/11,s12,x15/6,s7,x10/8,pb/f,x12/7,pk/l,x5/4,s15,x6/3,s2,x1/4,s15,x8/7,s2,x14/15,s14,x2/13,s6,x0/9,s2,x1/3,pc/a,x0/9,s1,pe/j,x1/6,s14,x15/11,pp/h,x14/7,pm/g,x15/13,s1,x2/5,s7,x1/12,s9,x8/9,s9,x2/3,s6,pl/i,x5/1,pk/b,s15,x10/3,pj/n,s5,x5/4,pf/c,x1/6,s9,x10/4,pd/n,x5/15,pc/h,x8/4,s15,po/a,x13/1,s13,x6/10,s4,x3/5,pc/i,x4/9,s1,x15/7,pj/g,x14/12,s15,x11/0,s12,x10/1,pc/m,x0/15,pe/p,x5/7,pg/i,x2/15,s6,x6/13,s6,x7/0,s15,x5/13,po/l,s7,x6/15,s8,x3/8,s3,x1/13,s15,x2/9,pg/a,x3/0,pi/c,x2/11,s8,x4/12,pe/k,x5/8,s9,x9/14,pf/j,x11/1,pb/n,x4/10,s11,pd/e,x2/12,pk/h,x9/8,s13,pe/g,s2,ph/m,x15/1,s11,pj/i,s3,x12/14,pc/o,x11/13,pe/k,x14/15,s12,x9/1,s6,x5/13,pi/o,x4/6,s15,pf/l,x8/10,s9,x11/14,s14,x10/9,s1,x7/14,pk/h,x0/8,pi/l,x15/3,pg/b,x2/13,s7,x9/12,s14,x10/3,pd/i,s12,x1/7,s13,x6/3,s14,x15/12,s15,pn/m,x7/5,s15,x14/1,pe/j,x11/3,s1,x9/10,pn/h,x1/2,pk/p,x14/12,pd/i,x1/4,pj/f,x9/2,s2,x1/14,s13,x15/7,pi/b,x1/14,pa/h,x2/5,s3,x8/10,s11,x12/9,pi/c,x14/0,pn/j,x2/3,s8,x15/6,pl/b,x2/7,s5,x8/14,s7,x9/6,pa/e,x10/5,pd/h,s7,x2/9,pb/n,x13/10,s1,x4/5,ph/o,x3/8,pn/i,s15,x5/15,s6,x14/13,pg/c,x10/11,s15,pa/i,x3/14,ph/f,s8,x8/6,pk/c,x10/5,s10,x6/7,s12,x15/11,pd/n,x7/6,s5,x13/4,pf/g,x11/2,pl/b,x7/12,s9,x9/13,s4,x14/5,pn/h,x4/13,pg/m,s14,x6/5,pe/a,x14/4,pk/n,s5,po/m,x6/7,s15,x2/12,pc/b,s14,x4/6,s15,x2/8,ph/j,x0/11,pp/i,x5/14,s5,x13/2,s6,x15/5,pg/n,x8/3,s12,x11/4,s9,x1/14,pb/p,x12/2,s6,x14/9,s3,x3/10,pe/m,x13/7,ph/a,x0/10,pi/c,x11/5,s15,x1/7,s9,x6/9,s3,x11/4,s1,x8/7,s15,x0/12,s2,x5/13,s1,x12/11,s10,x3/5,s12,x7/13,pg/e,x1/12,pc/m,x13/11,s4,x15/4,s8,x11/8,s8,x5/0,s14,x9/1,s12,x4/2,pe/j,x6/14,s3,x4/15,s3,x9/2,s10,x10/14,s2,x11/12,pn/a,x13/14,s12,po/i,x5/6,pg/c,x3/12,s13,x0/14,s10,x3/7,s12,x1/0,pj/l,x14/15,s8,x1/12,s8,x10/15,s13,x0/9,s1,x11/1,pk/p,x12/13,s8,x11/1,s9,pn/c,x7/6,s12,x8/1,s1,pf/h,x10/0,s10,x4/7,pa/n,x11/15,s1,x5/3,pg/i,x4/14,s15,x7/3,pf/m,x11/10,s9,x1/8,s9,pi/j,x11/3,s7,x14/4,s5,pm/f,x6/7,s13,x5/10,s10,x0/4,s6,x8/2,s13,x9/4,po/p,s8,x11/3,s1,x5/13,ph/i,x8/2,pj/c,x5/14,pn/p,x2/11,s4,x1/6,s4,x12/10,s13,x15/4,s14,x1/3,s10,x5/9,s9,x14/7,po/k,x10/4,s10,x9/1,pa/h,x5/10,s7,x15/3,s5,x2/1,pp/b,x14/12,s5,x6/3,s12,x11/0,s15,x6/15,pg/h,x1/0,s3,x14/3,s7,x12/11,pp/i,x7/14,pn/b,s9,x6/5,pj/c,x8/10,ph/k,x0/13,s9,x15/2,pn/j,x14/6,pl/g,x1/5,pn/c,x3/12,pf/k,x5/0,ph/d,x15/6,pf/b,x7/10,pm/c,s1,x0/3,pn/k,x2/1,ph/b,x12/6,s4,x15/0,pe/l,x3/9,s8,x0/11,s12,x13/10,pd/b,s7,x15/8,s6,x1/5,s5,x13/8,pj/p,s12,x3/10,pf/c,x5/14,s15,x10/15,s7,x6/5,s15,x10/15,s7,x12/5,s5,x13/8,s11,x7/4,s11,x8/11,pg/k,x5/1,pb/i,s3,x2/14,pj/c,x15/4,s10,x6/12,pp/l,x13/7,s14,x12/1,s13,x5/2,s7,x4/1,s11,x6/0,s13,x8/2,s15,x12/15,pm/g,x9/2,pl/h,x5/11,s15,x9/3,pp/b,x7/11,s15,pf/e,s6,x5/13,s2,x2/6,s8,x11/14,s5,x1/7,ph/j,x9/13,s15,x6/2,pc/e,x5/15,s2,x12/9,ph/d,x6/7,pj/b,x8/2,po/m,x10/12,s5,x7/15,pn/f,s11,x8/13,pe/g,x1/0,s15,x8/10,s7,x0/15,pb/o,x1/2,s1,x8/13,s12,x15/3,s1,x6/0,s13,x2/1,pf/l,x8/9,s5,x4/11,pa/p,x1/10,s3,x12/7,pk/g,x0/4,s13,x12/15,pd/i,x5/9,pb/a,x1/4,s13,x3/11,s10,x5/0,pf/i,x14/6,s6,x4/10,s5,x2/13,pl/k,x8/1,pm/i,x14/3,s6,x7/13,s15,x9/0,s4,x3/11,s5,x4/2,pk/a,x3/13,pb/d,x0/5,s7,pk/m,s2,x6/10,s7,x5/9,pa/b,x8/13,s14,x11/10,pf/l,x0/13,pm/d,x9/14,s5,x10/11,s15,x13/3,s13,x12/6,pb/c,x10/9,s5,x1/13,s1,x10/8,s4,x2/1,s3,x14/7,s3,x8/13,s6,x4/0,s4,x10/11,pg/h,x1/9,s6,x4/14,pd/e,x13/7,s3,x9/14,pl/f,x12/11,pa/m,s2,x4/6,s7,x1/12,pp/c,x5/15,pg/h,x10/14,s7,pb/n,x13/0,pj/k,x3/2,pm/h,x1/7,s14,pb/i,s10,x0/15,s2,x13/2,s10,x11/3,pe/p,x0/4,s9,x6/5,pb/d,s11,x15/14,s9,pe/f,x3/5,pn/b,x12/11,pc/g,s8,x13/15,s12,x8/6,pd/m,x5/15,pk/h,x2/10,po/l,x6/14,s10,x4/15,s8,x14/13,s15,x3/6,s13,x14/7,s8,x10/13,s12,x1/15,s15,x5/4,pn/h,x15/9,s13,x4/6,s14,x3/1,pp/d,x5/14,s13,x2/3,s10,x15/11,s14,pj/m,x2/8,s6,po/l,x15/0,pj/b,x9/6,s8,x11/14,pp/h,s7,x10/7,s9,x13/9,s14,x3/8,pf/j,x13/10,pb/m,x5/8,s7,x11/6,s6,x14/10,s7,x13/7,pc/o,x15/0,s5,x1/2,s12,x14/5,s1,x7/4,pa/l,x6/2,s9,x9/14,pe/i,x12/8,pc/l,x10/0,pj/k,x9/4,pc/n,s9,pk/e,x3/0,pd/f,x12/1,s3,x0/7,pp/o,x4/2,s7,x14/15,ph/f,x1/4,s9,x6/10,s8,x4/15,pb/p,x13/8,s9,x5/4,s12,x15/1,pf/d,x6/14,s4,x13/10,ph/i,s11,x9/6,pc/e,x3/8,pi/b,x14/7,po/n,x15/2,pe/p,s10,x3/11,s6,x7/4,s9,pb/c,x8/0,s1,x10/5,s6,x8/7,s3,x10/4,s15,x8/9,s7,x3/7,s4,x1/8,s8,x0/6,s7,x13/11,pk/m,x1/2,s13,ph/f,x5/0,pc/d,x4/2,s2,x9/14,s11,x6/7,s9,x14/10,pa/p,x5/8,pm/e,x1/10,ph/l,x12/11,pe/n,x10/2,po/h,x13/3,pe/j,x2/4,s2,x9/12,pf/o,x3/15,s15,x7/9,s10,x5/4,pp/h,s2,x14/8,pi/a,x7/9,pp/j,x6/8,s4,x15/2,pk/m,x1/14,s1,x2/15,s2,x7/0,ph/l,x11/12,s8,x10/6,pi/c,x8/14,s9,x15/9,s5,x11/6,s12,x5/9,s3,x10/0,s15,x8/5,s9,x10/4,pm/e,x8/14,pk/j,x2/5,s13,x7/10,s2,x4/15,s10,x14/12,s3,x9/6,pl/d,x14/0,pe/p,x5/12,s2,x8/11,ph/l,x6/15,pg/d,x1/4,ph/m,x6/11,s6,x14/8,s7,x15/5,pk/i,x2/9,s14,x11/12,pm/l,x2/8,s15,x10/9,s6,x8/5,s15,x15/3,pg/j,x14/13,s9,x8/2,s4,x1/15,pa/m,x8/12,s5,x14/2,s12,ph/c,x9/5,po/e,s4,x1/4,s15,x0/7,pn/b,x14/5,pl/d,x1/15,pm/e,x9/13,pd/h,s10,x5/2,pn/f,x14/3,pe/i,x11/9,pj/l,x13/1,pp/d,x0/14,pk/j,x2/8,s2,x12/14,pe/a,x2/6,pl/o,s10,x10/3,s8,x5/11,pm/g,x3/8,s11,x15/9,pk/i,x11/12,s11,x10/1,pj/m,x15/3,s3,x12/4,pf/l,x9/7,ph/k,x12/4,s10,x3/2,pj/o,s8,x12/6,s6,x11/13,pb/i,x12/14,s14,x4/13,s15,x15/10,s13,x7/1,pa/j,x14/11,s14,x4/6,s6,x14/1,s10,x10/5,s4,x13/15,pm/l,x0/5,ph/o,x12/2,s8,x6/14,pf/k,x13/8,po/i,x7/4,pk/j,x0/2,s3,x13/14,s9,x0/10,s11,x4/9,pm/l,x14/1,s5,ph/e,x8/3,s14,pl/n,x12/11,pk/c,x4/5,s2,x7/8,s9,x14/12,pn/j,x15/10,pi/a,x2/9,s8,x3/1,s14,x2/15,s4,x14/1,s7,x10/0,s1,x13/14,pg/l,x10/15,s7,pa/c,x4/8,pd/b,x1/13,s6,x14/12,pj/a,x7/4,s1,x14/12,s15,pb/e,x7/13,s12,x9/3,s11,x4/6,s15,x8/11,s7,x2/5,s7,x10/13,s15,x0/15,s4,x4/7,s12,x11/8,s11,x1/13,s8,x0/14,pm/a,x6/9,s14,x4/8,s1,x5/6,pf/o,x9/10,pj/l,x14/11,po/e,x3/9,s15,x5/14,pp/i,x9/10,s8,x4/13,s12,x14/5,s3,x12/15,s11,x7/8,s9,x13/10,s13,pl/o,x7/14,pd/i,x12/6,s4,x15/5,s9,x11/14,pg/f,x3/5,pe/o,x6/4,s2,x15/1,pa/j,x4/14,pk/o,s4,ph/c,x2/5,s9,x11/6,s1,x3/13,s10,x14/10,s11,x1/0,s1,x12/14,s15,x7/3,pp/l,s15,x0/8,pe/k,x13/15,pp/j,x0/4,pd/l,x15/8,pk/n,x6/14,pp/m,s13,x7/8,s3,x1/0,s12,x7/5,pn/b,x3/13,s4,x0/15,s5,x1/7,pm/c,x13/3,pn/p,x11/7,s8,x3/0,s13,x11/13,s10,x10/9,pf/g,x14/15,s2,x3/6,pj/p,x7/10,s13,x5/4,s11,x7/12,pg/f,x10/5,pm/d,x4/12,s3,x0/9,pb/j,x10/14,pa/i,x7/0,s6,x3/4,s9,x8/7,pj/b,x2/13,s9,x1/3,s6,x8/15,s15,x0/2,s15,x10/14,s12,x7/1,s6,x2/9,po/p,x12/7,s5,x1/13,s4,x11/6,pj/e,x13/12,s12,x11/7,s10,pg/b,s12,x6/4,pf/i,s9,x0/12,s13,x9/8,s15,x15/4,s11,x11/10,pe/j,x7/13,pp/l,x9/3,pj/o,x7/5,s6,x3/10,s7,x5/8,s4,pa/m,x6/13,pk/l,x7/11,s13,x14/4,s12,x3/15,po/e,x6/9,s3,x11/8,s14,x7/5,s13,x8/1,pf/i,x3/15,s4,x12/2,s10,x7/0,s9,x15/3,s2,x8/9,ph/o,x13/12,pi/l,x11/14,s9,x15/10,s8,x3/7,s7,x13/9,pk/p,x1/10,s1,pf/c,x12/2,s2,x9/11,s4,x14/13,pb/h,x9/1,s12,x15/7,s10,x10/9,s4,x4/12,pk/o,x10/5,s3,x4/8,ph/d,x13/14,po/l,x10/1,s12,x0/2,pp/m,s12,x1/14,s13,x7/6,s1,x2/13,s6,x9/14,pb/o,x6/5,pl/j,x11/10,pp/h,x7/5,po/j,x14/9,pc/b,x13/10,s13,x14/1,s12,x6/0,s11,x5/1,s1,x3/7,s11,x12/8,s10,x15/1,po/i,x0/3,pp/k,x10/12,pm/h,s1,x9/8,s4,x11/0,s6,x14/2,s10,pd/l,x9/10,s4,x6/12,s5,x4/2,s12,x9/0,s14,x4/13,pc/b,x10/2,s8,x6/13,s4,pi/p,x0/1,s12,pf/a,x7/10,pk/l,x2/9,pn/o,x13/0,s8,x14/15,s8,x2/4,s12,x1/7,pj/f,x6/11,pc/o,x1/13,pm/k,x0/4,s7,pd/e,s5,x9/5,s15,x4/10,s12,x8/6,s6,x1/3,s7,pc/i,s14,x8/6,s10,x7/12,pd/f,x2/13,pn/m,x8/7,po/b,x4/1,s11,x13/12,pm/f,x15/9,s4,x12/10,s6,x15/8,s14,x11/7,pj/c,x2/8,s9,x14/11,pn/g,s12,x15/0,s1,x2/5,ph/m,x1/3,s10,x10/7,pd/i,x13/1,s15,x12/5,s13,x4/8,s2,x6/2,pf/l,x4/5,s12,x14/0,pa/h,x8/4,s15,x15/14,pl/e,x13/9,s3,x5/4,s2,x11/0,s12,x8/15,po/n,x5/11,s12,x2/6,s2,x0/13,pk/b,s2,x11/15,s15,x9/7,pe/a,x13/11,s9,x5/1,s11,x6/10,pc/m,x7/13,ph/k,x8/15,pb/o,x4/5,s6,x13/10,pf/e,x4/12,s8,x15/3,s15,x10/11,s4,x5/14,s8,x7/4,pa/h,x5/12,s4,x15/6,s14,x0/10,s9,x8/1,s9,x6/14,pk/j,s14,x13/15,s3,x9/0,s12,pm/c,x14/13,pp/l,x4/10,pf/b,s14,pn/c,s7,x3/13,s6,x2/14,s15,x10/5,s13,x6/8,pl/a,x5/14,s8,x2/3,s2,x8/12,s12,x11/2,pb/n,s14,x8/13,s3,x14/1,s6,x7/15,s9,x12/11,s12,x6/10,s3,x15/4,s15,x8/3,s3,x14/4,pc/l,x6/15,po/e,x7/2,pj/h,x11/4,pm/l,x15/3,pd/b,x10/9,s7,x13/0,s6,x9/2,s2,x4/14,s13,x8/13,s6,x6/11,pk/i,x3/15,s4,x9/14,pb/f,x15/12,pd/a,x10/3,pf/o,x2/12,s3,x9/14,pa/n,s2,x0/2,pb/i,x11/7,pj/a,x8/9,s11,x4/6,s5,x15/3,s14,pf/e,x10/8,pk/l,s13,x6/5,s4,x10/7,s5,x5/0,s9,pb/i,s13,x7/15,s13,x0/6,s13,x11/15,pg/h,x8/4,s10,x0/1,s8,x12/7,s6,x11/14,pf/i,x13/5,s2,x12/10,s8,x15/5,pa/e,s11,x3/2,po/f,x11/12,s3,x0/5,pa/d,x8/14,s6,x15/13,pp/f,x10/7,pc/m,x14/6,po/k,x1/7,s8,x5/3,pg/f,x15/0,s2,x2/8,s4,pi/p,x7/10,pd/h,x13/4,pk/n,x0/8,pm/e,s1,x15/3,s2,x1/10,s8,x4/5,pf/l,x6/0,s14,x8/11,s3,ph/b,s12,x2/1,s10,x11/15,s5,x8/6,s2,x7/10,pn/i,x0/5,s4,x1/14,pp/o,s7,x10/13,s13,x6/5,pi/m,x10/11,pn/j,x9/12,s9,x11/0,pd/e,x7/2,pm/j,x0/5,pg/i,x15/8,pe/a,x11/9,s11,x2/7,s15,x8/15,pn/c,x0/1,pd/o,x14/8,s3,x7/2,pl/n,x4/3,pa/p,x11/1,s4,x10/9,s8,x15/1,pb/o,x12/9,pi/f,x10/0,s5,x8/1,s15,x6/0,pn/j,x8/12,s2,x6/0,pa/b,s6,x15/8,s4,x4/9,pp/k,x1/0,s15,x14/4,pa/n,x9/13,pj/e,x12/5,pk/l,x7/11,pe/j,x12/8,s8,x9/5,pk/n,x15/7,pg/d,s4,x10/13,pk/o,x0/5,pe/b,s3,x2/10,pa/i,x15/0,pl/k,x14/9,ph/a,x6/12,s13,pg/l,x11/14,s15,x1/7,s4,x12/6,s5,x0/11,pb/k,x8/10,s1,x11/6,pl/g,s4,x13/9,pp/m,x11/14,pe/g,x9/10,ph/a,x4/1,s5,x2/8,s10,x3/11,s15,x9/10,pk/m,x8/12,po/i,x15/1,pn/m,x7/10,pj/a,x3/13,s9,x1/12,pc/m,x13/4,po/d,x12/7,s10,x10/6,s1,x14/8,s1,x12/1,s9,pe/h,x0/14,s1,x9/13,pm/g,x8/15,pi/j,s7,x6/4,s10,x3/5,pl/b,x12/7,pi/c,x13/11,s1,x3/10,pa/b,x6/8,s12,x4/10,pi/j,x3/11,s8,x7/6,ph/g,x9/2,s6,x4/0,s2,x12/11,s12,x8/1,s13,x14/5,s2,pi/l,x0/12,pn/g,x1/11,pb/e,x2/13,s11,x7/0,pp/f,x5/6,s6,x7/9,s3,x1/3,s14,x10/14,s5,pi/c,x0/1,s5,pk/j,x10/14,pf/o,x11/2,pl/j,x13/5,s14,x12/3,s1,pp/e,x8/9,s2,x6/4,pg/k,x9/7,s4,x15/4,s14,x7/3,s3,x5/0,s13,x8/3,s4,x5/10,s8,x9/4,s14,x10/0,ph/b,x13/14,pl/c,x8/0,pj/e,s15,x7/13,s10,x12/0,s12,x15/1,s2,x4/13,pd/f,x15/11,s9,pi/b,x6/3,s12,x7/12,s13,x15/9,s6,x3/1,s6,x4/13,pa/c,x3/15,s4,x4/0,s10,x9/6,pi/p,x7/0,s6,x11/8,s10,x13/3,s3,x0/6,s5,pn/e,x11/12,pm/c,x2/7,pn/k,s8,x4/13,pi/f,x11/10,s2,x4/3,s12,x13/5,s9,x3/14,s7,pe/l,x0/7,s5,x9/14,pm/c,x6/10,s2,x0/14,pa/n,x7/2,pb/h,x12/6,pc/p,x10/7,ph/e,x14/3,s1,x4/9,s11,x15/7,pf/c,x6/13,s3,x12/3,pk/m,s7,x4/6,s10,x0/2,s2,x15/11,s14,x3/6,s14,x12/0,s14,x15/8,s2,x10/7,po/a,x4/2,pd/k,x10/13,s3,x8/4,s7,x7/1,pc/i,x3/2,s2,x14/15,s3,x1/0,s14,x4/12,s2,x3/6,po/p,s11,x14/2,s10,x1/13,pb/i,x15/3,po/d,x2/8,pa/h,x4/9,s2,x3/10,pj/g,x7/15,pa/o,x11/0,pd/m,x7/6,pg/l,x10/8,po/c,x15/5,pg/p,s11,x12/0,s14,x9/6,s12,x1/8,s6,x5/6,pk/h,x9/7,pl/e,x6/3,s5,x10/5,s11,x14/13,pj/m,x2/11,s14,x5/14,s12,x10/9,pk/g,x13/12,s7,x11/6,s9,x7/14,pc/n,s5,x0/9,pa/e,s2,x10/1,s12,x2/5,po/j,x8/6,s4,x11/1,pa/m,x9/4,s14,x14/1,ph/n,x15/10,s2,x1/8,pd/g,x5/4,pf/l,x12/7,s14,x9/5,pk/g,s1,x10/14,s8,x4/13,pa/j,x12/0,s10,x8/3,s10,x9/11,s8,x10/2,pg/k,s1,x13/11,s1,x5/7,s1,x2/15,pd/n,x13/3,pb/j,x4/12,s10,pf/a,s6,x5/15,s5,pg/p,x10/14,s5,x4/7,pm/l,x14/0,s3,x2/9,pn/d,x7/0,s4,x15/3,s3,x6/2,pi/p,s15,x7/8,po/l,x4/2,s3,x3/10,s12,x1/9,s3,x12/15,pk/h,s15,x1/0,s7,pd/a,s4,x11/3,s11,x8/2,s9,x0/13,pj/f,x2/14,s11,x9/12,s2,x3/7,s3,x14/1,s1,x3/8,pp/m,s3,x1/5,pn/e,x0/6,s13,x13/1,pk/j,x14/12,s5,pd/e,x1/9,s11,x6/10,s4,x3/8,s8,x7/0,s7,x5/15,s4,x8/2,s6,pp/b,x10/0,s4,x4/12,s5,x13/9,s13,x6/12,pg/f,x4/9,ph/k,x1/12,s2,pl/m,x7/5,s9,x10/15,po/c,x9/14,s13,x15/4,s14,x3/12,s10,x10/8,pk/d,x3/9,pl/j,x2/10,s8,pa/f,x6/0,s10,x1/4,s13,pb/l,x3/2,pm/p,x7/11,s12,pl/h,x13/6,s14,x11/3,pe/n,x14/12,s6,x13/4,s3,x12/7,s6,x0/8,s1,x2/10,s3,x7/9,pf/o,s6,x4/15,pi/a,x0/11,s3,x7/14,ph/b,x12/0,s12,x15/6,s8,pl/o,x2/13,pj/k,x1/8,s10,pa/m,x13/12,s2,x5/9,pd/n,x6/15,s8,x13/8,pe/a,x1/10,pk/j,x15/2,s15,x4/7,po/d,x3/6,s9,x2/9,s5,x4/1,s6,x2/14,s9,x5/10,s6,x13/7,s3,x14/1,pa/i,x11/0,pb/g,x3/6,s3,x8/4,s3,x15/0,pc/o,s6,x14/6,s5,x11/5,s12,x4/13,s15,x10/1,s2,x14/7,pp/k,x1/4,pl/f,x5/13,pb/p,x0/14,s8,x15/5,s8,x11/3,s4,x15/12,pn/f,x6/2,pe/p,x7/1,s14,pn/a,s4,x13/6,pf/d,s1,x10/11,s2,pg/i,x13/15,s4,x5/9,s4,x3/7,s6,x12/0,s12,x6/1,pa/d,x15/14,pf/n,x2/6,pj/c,x4/12,s5,x8/5,s15,x11/6,s10,pp/h,x9/1,pb/n,x14/13,s2,x2/0,s11,x14/8,pf/m,x15/1,pj/n,x2/14,s1,x5/6,pd/b,s3,x0/2,pn/o,x1/8,s2,x11/3,s10,x2/14,s12,x8/4,s9,x13/7,s2,x3/9,s2,x4/8,pk/m,s15,x6/1,s8,x10/9,s6,x8/14,pf/p,x4/9,s6,x7/13,pi/a,x0/9,s15,x12/2,s4,x4/7,pj/o,x12/3,s10,x2/5,pa/m,x12/0,pn/d,x14/15,pc/o,s15,x12/10,pd/j,x2/9,s11,x4/11,s13,x10/0,s13,x13/7,pe/i,x15/0,s5,pl/k,x10/3,s2,x7/1,s14,x9/12,pf/a,s5,x6/2,s5,x15/10,s10,x4/3,s8,x5/12,pn/d,s5,x4/13,s1,x1/8,s11,x4/5,s10,x6/13,pe/g,x10/9,s13,x7/12,pl/o,x0/4,s1,x3/2,s15,x8/0,s15,x13/11,pj/e,x10/9,pg/h,x5/8,pp/f,x10/12,s2,x1/9,pk/j,x8/2,pf/a,x12/9,s10,x6/3,pd/j,x11/0,pf/c,x8/10,s15,x15/11,s7,x1/8,s12,x9/2,pn/o,x10/11,s12,pd/h,x13/6,pj/k,s6,x15/3,ph/g,x8/6,s15,pe/f,x3/14,pl/c,x4/1,pj/h,x0/13,s4,x6/7,pl/b,s14,x13/12,s2,x14/5,s12,x0/3,pg/m,x13/1,s7,x0/15,s15,x3/13,s9,x4/2,s6,x6/12,s3,x9/14,s11,x2/11,s1,x7/12,pc/k,x8/5,s11,pm/p,s12,x1/0,s11,x12/14,s8,x7/10,s6,x4/0,pj/f,x8/10,s6,pk/d,s4,x7/4,s1,x6/3,s8,x4/5,s9,x13/1,pi/p,s15,x2/8,po/d,x3/14,pn/k,x8/11,s2,x15/2,po/h,x6/4,s6,x12/0,s14,x2/7,pc/e,x10/6,s3,x14/11,pm/h,x6/15,pf/l,s3,x11/0,s12,x3/1,s9,x13/4,s9,x0/11,s9,x9/8,s6,po/i,x13/14,pa/c,x2/3,s7,x15/8,s8,x14/10,s5,x0/13,s11,x11/8,pb/d,x1/5,s6,x2/8,s2,x13/4,po/f,x7/10,pk/g,x0/9,s5,x8/2,s6,x11/12,s1,x10/14,s4,x11/3,s5,x13/1,s14,x2/5,s15,x1/15,s9,x14/7,s12,x0/13,s3,x6/10,s15,x2/12,pd/e,x15/10,pj/n,x12/11,s9,x10/5,pb/g,x0/12,pa/e,s11,x14/11,pj/f,s5,x0/6,s13,x4/13,s11,x3/9,pi/e,x2/11,pg/a,x12/8,pp/i,x4/0,ph/f,x7/11,s6,x14/4,s4,x2/1,pa/j,x5/6,s6,x0/1,s6,x2/5,pp/g,s10,pn/b,x12/13,s9,x11/15,s5,x3/4,s6,x9/10,pm/o,x1/3,s2,x11/14,pf/p,x5/1,pk/o,x7/11,s7,x12/1,pc/l,x6/4,s4,pf/e,x9/2,s13,x7/14,s14,x5/9,pk/p,x15/10,pg/j,x11/3,s2,pf/o,s10,x6/10,s15,x3/5,s11,x1/0,pl/g,x14/6,s13,x0/8,s15,x4/2,pd/m,x0/8,s15,x3/1,s12,x14/7,s7,x12/2,s14,x8/10,s3,x12/0,s12,x10/3,s5,x13/5,s10,x15/6,pj/g,x9/4,s6,x7/5,pk/e,x12/14,s1,x7/5,pj/h,x11/10,pa/m,x3/7,pi/f,x12/15,pa/j,x0/2,s1,x9/12,s4,x13/1,pi/d,x8/2,s1,x13/12,s2,x5/4,s10,x15/9,s8,x5/1,pn/a,x12/3,s10,x6/9,pg/c,s6,x12/15,pn/b,x11/14,s10,x15/4,s15,pg/m,x0/14,pp/h,x15/11,s9,x14/1,s1,x7/12,pl/i,x11/6,s4,x2/10,s6,x4/0,s14,x14/10,pb/a,x5/3,s4,x0/9,s11,x4/14,s11,x8/7,pd/m,x12/13,s5,x4/7,s5,x11/10,s4,x9/15,s3,x13/6,s9,pi/o,x11/15,s8,x7/12,s9,x14/1,s14,x6/7,pj/l,x13/4,pp/a,x11/0,pc/l,x14/10,pn/i,x15/4,s5,x0/10,pp/o,x5/13,s4,x10/4,s10,x5/6,s5,x4/11,ph/c,x2/1,s6,x0/10,s11,x1/14,s5,x3/2,pj/m,x7/5,s6,x15/12,s15,x8/0,po/c,x10/15,pm/i,x4/9,s12,x8/0,s15,pk/f,x14/7,s9,pj/l,x10/4,s10,x3/11,s2,x4/5,pk/h,x2/7,s10,x13/1,s11,x11/6,s2,x10/2,pd/c,x3/4,pg/f,x10/8,pe/o,x7/13,s14,x0/10,s10,pm/g,x13/14,s4,x5/4,s11,x1/13,s5,pb/l,x9/11,s3,x8/12,s7,x1/10,s11,x12/5,s12,x6/10,s11,x12/2,pe/j,x8/15,s6,x6/0,s1,x12/8,pk/g,s1,x15/4,pj/o,s13,x0/5,s10,x11/8,s6,x12/6,s11,x11/10,s9,x14/3,s14,pb/p,x4/15,pn/k,x5/13,s9,x8/11,s15,x2/3,s13,x11/6,pg/i,x5/8,pf/h,x10/15,pk/p,x14/1,ph/m,x0/7,s8,x10/15,pe/i,s7,x11/1,s7,x3/5,pl/g,x0/1,s11,x6/5,pj/i,x9/12,pf/d,x8/14,s6,x0/13,pa/g,x5/2,s11,x1/9,s15,x4/12,s13,x6/14,s3,pf/c,x13/1,s1,pp/m,x3/8,pf/c,x1/10,pk/j,s15,x15/4,s13,x3/12,s8,x2/9,pi/l,x7/1,s15,x8/12,s11,x11/2,pn/k,x15/0,s5,x3/8,pj/c,x1/13,s2,x10/5,s13,x6/11,s2,x4/15,po/h,x6/14,s14,x2/12,s5,x3/7,pm/n,x13/14,pf/c,x11/1,s10,x7/15,s9,x2/1,s8,po/b,x3/6,pk/h,x2/14,po/l,s8,x1/13,s5,x6/2,s1,x11/7,s5,pf/c,x1/13,s10,x3/9,s15,x0/7,pd/g,x6/13,pi/m,x15/5,pn/c,x10/12,s4,x11/0,s1,x6/1,s4,x8/3,s1,x15/4,s11,x13/11,pe/d,x3/15,s13,x11/5,po/n,x0/4,s9,x10/12,pd/p,x13/8,s7,x4/0,pg/l,x6/10,s9,pp/o,x9/13,s13,pn/c,x14/4,s15,x8/15,po/m,x14/5,pl/d,s11,x4/7,s15,x5/14,s4,x4/0,s3,x2/5,s13,x6/3,s12,x1/0,s5,x4/8,pj/m,x6/13,s7,x5/1,pg/l,s5,x14/9,s7,x5/13,pb/m,x0/7,s8,x8/9,pi/k,x13/7,s9,x10/5,po/c,x11/14,s4,x5/8,pl/b,x15/9,s7,x11/3,s3,pf/j,x13/15,s8,x9/10,s5,x2/6,s6,x13/9,pe/n,x15/10,s5,x11/4,pa/m,x3/8,s2,pi/f,x7/4,s3,x12/5,pg/p,x4/1,pk/n,x6/13,s14,x1/7,s6,x2/3,s4,x1/9,ph/i,x12/8,s9,x5/7,s14,x2/1,s10,x5/9,pd/o,x7/8,pj/e,x3/6,pk/h,x14/5,pf/d,x9/10,s15,x1/8,s11,x6/15,pj/m,x12/2,pk/h,x7/10,pj/a,s4,x8/2,s8,x4/0,s5,x9/5,s9,x8/4,s11,x10/6,s13,x3/15,s13,x2/0,s10,x3/6,s10,x4/2,pn/e,x9/3,s8,x7/12,s13,x9/6,s7,x13/4,pb/k,x11/14,s5,x4/6,pi/e,x1/0,pa/g,x13/10,s10,x5/11,pl/p,x2/4,pb/o,x10/8,s1,x0/13,s10,pl/p,s6,x4/10,s2,x14/11,pi/m,x8/12,pa/b,x5/10,s8,x3/8,s1,x12/5,pi/d,s13,x9/6,s3,x3/8,s2,x15/11,s11,pn/b,x10/8,s3,x4/2,s11,x14/13,s6,x4/12,pc/g,x9/0,s8,x2/5,pl/i,x14/7,s6,x4/6,s7,pj/e,x7/0,s15,x13/12,s10,x15/0,s11,x12/5,s4,x1/7,pd/i,x9/14,s11,ph/c,x4/0,s12,x7/15,s15,pj/i,x2/10,s5,x0/15,s14,x7/14,s2,x2/12,s8,x0/14,s4,x9/1,s7,x11/14,pl/m,s11,x2/8,s14,pa/n,x7/3,s9,x11/0,pp/d,x13/4,s1,x8/9,s2,x7/1,pc/m,x8/9,s8,x4/3,s5,x2/15,pp/a,x0/14,s8,x10/8,s14,x2/13,pj/f,x1/14,s8,x13/9,s9,x8/11,pl/o,x10/14,s14,x0/11,pp/d,x5/13,pa/g,x12/3,s5,x4/2,s9,x9/15,s3,x3/14,pl/e,x2/15,pj/i,x0/3,pk/h,x13/9,s15,x2/10,pl/i,x8/6,s7,pd/f,x13/0,pk/l,x1/4,po/h,x2/7,s2,x13/4,pd/c,x1/3,s15,pe/o,x6/12,s7,x9/2,pd/f,x14/10,s5,x2/6,s5,x0/12,s5,x5/3,s15,x13/15,pl/k,x0/14,pd/b,s13,x1/8,s4,x4/6,pe/j,x7/11,pl/p,x5/6,s9,pk/g,x11/12,pf/p,x10/7,s14,x0/5,pa/b,x2/14,s4,x13/15,pd/c,x7/0,pj/o,x4/3,pk/m,x1/9,s6,x8/3,ph/j,s4,x5/9,s5,x4/7,pe/k,x10/9,pm/f,x5/14,s10,x11/8,s3,x12/1,s6,x4/15,s4,x2/1,pi/o,x13/0,s7,x11/3,s7,x13/14,s10,x11/15,pg/k,x7/1,pf/o,x10/6,pl/m,x4/5,s5,x3/11,s7,pi/e,x8/1,pc/b,x11/4,pk/i,x10/1,pp/g,s5,x14/13,s12,x4/11,s10,x3/14,s1,x10/6,s12,x8/5,pi/d,x3/10,pk/f,x2/0,s13,x10/12,s1,x9/2,s10,x5/3,pj/l,x9/15,s9,pa/i,x0/6,s5,x14/12,pg/e,x11/10,s12,x5/8,pf/j,x14/11,pn/p,x5/9,s14,x4/1,ph/o,x13/2,pn/g,x14/0,po/k,s4,x4/13,pn/j,x11/10,s8,x2/14,s14,x7/15,pc/h,x12/4,po/b,x9/15,s12,x6/12,pg/p,x2/9,s3,x6/4,s12,x1/0,s7,x4/5,s7,x9/15,pm/j,x3/14,s5,x7/1,pc/h,x10/5,pe/g,x14/11,pk/o,x13/8,pb/d,x1/4,s2,x2/12,s6,x7/11,s13,x0/3,s11,x13/9,pn/f,s14,x14/5,s1,x7/1,s8,x9/5,s2,x3/0,s9,x13/10,s6,x7/15,s4,pe/a,x13/9,pl/h,x14/8,pi/p,x9/1,s6,x13/12,pd/j,x5/8,pn/k,x13/11,s2,x8/15,pm/c,x0/9,pj/a,x7/8,pk/l,x5/3,s5,x14/7,pn/o,x0/11,s1,x7/12,s7,x8/5,pl/k,s7,x9/1,s5,x0/5,s15,x15/11,s10,x6/1,s12,pg/j,x13/3,pb/i,x5/15,s8,x7/0,s4,x2/6,s9,x4/1,s15,x12/0,s11,x6/3,pg/o,x7/8,pk/d,x14/1,po/b,x6/8,s11,x7/4,s13,pp/a,x0/9,pe/f,x8/5,pp/b,x0/15,s8,x6/10,pd/e,s7,x14/4,s9,x3/10,pp/o,x5/6,s13,x1/4,s13,x3/5,pe/f,x11/0,s6,x13/1,s14,x6/12,s15,x7/2,s4,x12/4,pi/d,x1/3,pe/m,x14/8,s14,pj/n,x13/4,pc/a,x0/7,s10,x12/4,pg/l,s11,x15/9,s12,x14/8,pn/m,s6,x0/7,s1,x8/10,pe/g,x11/12,s14,x0/6,pl/c,x3/11,pn/k,x15/14,s15,x10/6,pm/e,x14/2,pa/l,x12/7,pb/d,x2/6,s14,x4/14,pl/c,x0/10,s12,x14/5,s15,x7/6,pn/f,x0/13,s4,x6/12,s12,x2/15,pc/i,s8,pb/g,s6,pf/n,x5/11,s1,x13/14,pj/e,x10/1,pd/m,x6/15,pp/j,x7/1,pd/k,x11/6,s1,x7/1,s3,x14/10,s14,pe/f,s4,x7/11,s4,pk/j,x2/5,pc/f,x0/6,pj/k,x15/8,s1,x7/0,s2,x8/12,s4,x14/11,s8,x0/1,pf/c,x6/14,s4,x13/8,s11,x10/6,s13,pm/l,x15/4,s12,x5/6,s13,x12/9,pi/k,x7/13,s5,x10/12,s12,x9/7,pl/c,x1/14,po/i,x7/10,s6,x5/1,s11,x4/11,s13,x13/7,pd/f,x2/11,pj/m,x9/5,pi/g,x3/0,po/d,x14/5,s5,x3/8,s9,x5/2,pb/i,x14/7,s14,x15/13,s13,x9/4,s13,pm/e,x6/11,s5,x3/7,pn/d,x13/14,pj/l,x11/8,s5,pe/g,x13/9,s9,po/p,x7/2,pe/k,x3/6,s10,x14/7,s13,x1/6,s14,x3/2,s1,x13/12,pg/j,x11/5,s6,x2/14,s6,x4/10,pi/b,x0/8,s1,x2/14,s12,x13/5,s13,x10/15,s13,x12/6,s12,x0/11,pf/g,x15/1,pb/d,x7/10,s12,pk/l,x14/4";
	}

}
