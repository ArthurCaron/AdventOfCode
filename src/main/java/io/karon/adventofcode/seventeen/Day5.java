package io.karon.adventofcode.seventeen;

class Day5 {

	static class Star1 {

		static int getResult() {
			int[] input = getFormattedInput();

			int steps = 0;
			int currentIndex = 0;

			while (currentIndex < input.length && currentIndex >= 0) {
				steps++;
				int move = input[currentIndex];
				input[currentIndex]++;
				currentIndex += move;
			}

			return steps;
		}

	}

	static class Star2 {

		static int getResult() {
			int[] input = getFormattedInput();

			int steps = 0;
			int currentIndex = 0;

			while (currentIndex < input.length && currentIndex >= 0) {
				steps++;
				int move = input[currentIndex];
				if (move >= 3) {
					input[currentIndex]--;
				} else {
					input[currentIndex]++;
				}
				currentIndex += move;
			}

			return steps;
		}

	}

	private static int[] getFormattedInput() {
		String[] input = getInput().split("\\n");
		int[] formattedInput = new int[input.length];

		for (int i = 0; i < input.length; ++i) {
			formattedInput[i] = Integer.parseInt(input[i]);
		}

		return formattedInput;
	}

	private static String getInput() {
		return "0\n"
				+ "1\n"
				+ "1\n"
				+ "-2\n"
				+ "-2\n"
				+ "-2\n"
				+ "-2\n"
				+ "-4\n"
				+ "2\n"
				+ "0\n"
				+ "-9\n"
				+ "1\n"
				+ "-5\n"
				+ "-12\n"
				+ "-9\n"
				+ "-10\n"
				+ "-7\n"
				+ "-7\n"
				+ "-2\n"
				+ "-10\n"
				+ "-2\n"
				+ "0\n"
				+ "-6\n"
				+ "-14\n"
				+ "-6\n"
				+ "-24\n"
				+ "-10\n"
				+ "0\n"
				+ "-4\n"
				+ "1\n"
				+ "1\n"
				+ "-30\n"
				+ "-31\n"
				+ "-24\n"
				+ "0\n"
				+ "-20\n"
				+ "-24\n"
				+ "-10\n"
				+ "-2\n"
				+ "0\n"
				+ "-28\n"
				+ "-8\n"
				+ "-3\n"
				+ "-23\n"
				+ "-6\n"
				+ "2\n"
				+ "-41\n"
				+ "-36\n"
				+ "-2\n"
				+ "-11\n"
				+ "-39\n"
				+ "-27\n"
				+ "-21\n"
				+ "-48\n"
				+ "-38\n"
				+ "-4\n"
				+ "-29\n"
				+ "-49\n"
				+ "-9\n"
				+ "-57\n"
				+ "-33\n"
				+ "-60\n"
				+ "-42\n"
				+ "-12\n"
				+ "-21\n"
				+ "-19\n"
				+ "-21\n"
				+ "-52\n"
				+ "-65\n"
				+ "-62\n"
				+ "-26\n"
				+ "-64\n"
				+ "-67\n"
				+ "-12\n"
				+ "-22\n"
				+ "-21\n"
				+ "-30\n"
				+ "-31\n"
				+ "-20\n"
				+ "-54\n"
				+ "-57\n"
				+ "-20\n"
				+ "-18\n"
				+ "-46\n"
				+ "-1\n"
				+ "1\n"
				+ "-76\n"
				+ "-25\n"
				+ "-80\n"
				+ "-60\n"
				+ "-80\n"
				+ "-36\n"
				+ "-30\n"
				+ "-85\n"
				+ "-21\n"
				+ "-89\n"
				+ "-62\n"
				+ "-66\n"
				+ "-4\n"
				+ "-39\n"
				+ "-64\n"
				+ "-39\n"
				+ "-88\n"
				+ "-17\n"
				+ "-5\n"
				+ "-69\n"
				+ "-90\n"
				+ "-14\n"
				+ "-2\n"
				+ "-13\n"
				+ "-76\n"
				+ "1\n"
				+ "-10\n"
				+ "-10\n"
				+ "-28\n"
				+ "0\n"
				+ "-96\n"
				+ "-16\n"
				+ "-33\n"
				+ "-90\n"
				+ "-56\n"
				+ "-25\n"
				+ "-9\n"
				+ "-4\n"
				+ "-110\n"
				+ "-54\n"
				+ "-72\n"
				+ "-92\n"
				+ "-127\n"
				+ "-112\n"
				+ "-38\n"
				+ "-17\n"
				+ "-114\n"
				+ "-82\n"
				+ "-35\n"
				+ "-51\n"
				+ "-41\n"
				+ "-3\n"
				+ "-14\n"
				+ "-69\n"
				+ "-102\n"
				+ "-72\n"
				+ "-6\n"
				+ "-118\n"
				+ "-80\n"
				+ "-111\n"
				+ "-96\n"
				+ "-45\n"
				+ "-43\n"
				+ "-19\n"
				+ "-37\n"
				+ "-24\n"
				+ "-75\n"
				+ "-75\n"
				+ "-115\n"
				+ "-54\n"
				+ "-52\n"
				+ "-19\n"
				+ "-123\n"
				+ "-151\n"
				+ "-122\n"
				+ "-96\n"
				+ "-20\n"
				+ "-46\n"
				+ "-67\n"
				+ "-128\n"
				+ "-123\n"
				+ "-9\n"
				+ "-43\n"
				+ "-34\n"
				+ "-160\n"
				+ "-111\n"
				+ "2\n"
				+ "-164\n"
				+ "-158\n"
				+ "-63\n"
				+ "-67\n"
				+ "-2\n"
				+ "-145\n"
				+ "-103\n"
				+ "-80\n"
				+ "-53\n"
				+ "-148\n"
				+ "-103\n"
				+ "-47\n"
				+ "0\n"
				+ "-178\n"
				+ "-147\n"
				+ "-57\n"
				+ "-152\n"
				+ "-46\n"
				+ "-173\n"
				+ "-119\n"
				+ "-184\n"
				+ "-69\n"
				+ "-113\n"
				+ "-112\n"
				+ "-51\n"
				+ "-33\n"
				+ "-187\n"
				+ "-172\n"
				+ "-172\n"
				+ "-122\n"
				+ "-56\n"
				+ "-59\n"
				+ "-24\n"
				+ "-204\n"
				+ "-86\n"
				+ "-65\n"
				+ "-152\n"
				+ "-119\n"
				+ "-201\n"
				+ "-75\n"
				+ "-16\n"
				+ "-106\n"
				+ "-159\n"
				+ "-152\n"
				+ "-77\n"
				+ "-29\n"
				+ "-9\n"
				+ "-39\n"
				+ "-49\n"
				+ "-141\n"
				+ "-211\n"
				+ "-23\n"
				+ "-145\n"
				+ "-96\n"
				+ "-94\n"
				+ "-84\n"
				+ "-99\n"
				+ "-66\n"
				+ "-9\n"
				+ "-135\n"
				+ "-185\n"
				+ "-15\n"
				+ "-184\n"
				+ "-123\n"
				+ "-152\n"
				+ "-94\n"
				+ "-67\n"
				+ "-43\n"
				+ "-127\n"
				+ "-3\n"
				+ "-21\n"
				+ "-11\n"
				+ "-76\n"
				+ "-129\n"
				+ "-139\n"
				+ "-65\n"
				+ "-185\n"
				+ "-15\n"
				+ "-215\n"
				+ "-163\n"
				+ "-232\n"
				+ "-1\n"
				+ "-173\n"
				+ "-81\n"
				+ "-148\n"
				+ "-12\n"
				+ "1\n"
				+ "-251\n"
				+ "-183\n"
				+ "-214\n"
				+ "-162\n"
				+ "-213\n"
				+ "-247\n"
				+ "-165\n"
				+ "-232\n"
				+ "-107\n"
				+ "-75\n"
				+ "-190\n"
				+ "-205\n"
				+ "-138\n"
				+ "-149\n"
				+ "-232\n"
				+ "-99\n"
				+ "-57\n"
				+ "-148\n"
				+ "-231\n"
				+ "-188\n"
				+ "-141\n"
				+ "-193\n"
				+ "-39\n"
				+ "-208\n"
				+ "-245\n"
				+ "-102\n"
				+ "-247\n"
				+ "-1\n"
				+ "-76\n"
				+ "-13\n"
				+ "-20\n"
				+ "-263\n"
				+ "-146\n"
				+ "-158\n"
				+ "-81\n"
				+ "-270\n"
				+ "-162\n"
				+ "-70\n"
				+ "-215\n"
				+ "-286\n"
				+ "-98\n"
				+ "-186\n"
				+ "-184\n"
				+ "-147\n"
				+ "-146\n"
				+ "-160\n"
				+ "-172\n"
				+ "-42\n"
				+ "-48\n"
				+ "-14\n"
				+ "-175\n"
				+ "-299\n"
				+ "-293\n"
				+ "-214\n"
				+ "-198\n"
				+ "-128\n"
				+ "-272\n"
				+ "-108\n"
				+ "-94\n"
				+ "-102\n"
				+ "-108\n"
				+ "-53\n"
				+ "2\n"
				+ "-172\n"
				+ "-41\n"
				+ "-293\n"
				+ "-14\n"
				+ "-256\n"
				+ "-92\n"
				+ "-121\n"
				+ "-140\n"
				+ "-294\n"
				+ "-54\n"
				+ "-121\n"
				+ "-221\n"
				+ "-145\n"
				+ "-260\n"
				+ "-298\n"
				+ "-82\n"
				+ "-284\n"
				+ "-238\n"
				+ "-15\n"
				+ "-159\n"
				+ "-159\n"
				+ "-213\n"
				+ "-31\n"
				+ "-44\n"
				+ "-61\n"
				+ "-203\n"
				+ "-247\n"
				+ "-39\n"
				+ "-157\n"
				+ "-130\n"
				+ "-347\n"
				+ "-272\n"
				+ "-23\n"
				+ "-185\n"
				+ "-162\n"
				+ "-337\n"
				+ "-91\n"
				+ "-72\n"
				+ "-91\n"
				+ "-315\n"
				+ "-144\n"
				+ "-165\n"
				+ "-360\n"
				+ "-173\n"
				+ "-258\n"
				+ "-275\n"
				+ "-99\n"
				+ "-81\n"
				+ "-16\n"
				+ "-72\n"
				+ "-150\n"
				+ "-238\n"
				+ "-45\n"
				+ "-7\n"
				+ "-344\n"
				+ "-364\n"
				+ "-339\n"
				+ "-54\n"
				+ "-61\n"
				+ "-24\n"
				+ "-324\n"
				+ "-321\n"
				+ "-294\n"
				+ "-104\n"
				+ "-87\n"
				+ "-165\n"
				+ "-113\n"
				+ "-4\n"
				+ "-306\n"
				+ "-198\n"
				+ "-147\n"
				+ "-136\n"
				+ "-360\n"
				+ "-217\n"
				+ "-20\n"
				+ "-391\n"
				+ "-169\n"
				+ "-209\n"
				+ "-12\n"
				+ "-95\n"
				+ "-164\n"
				+ "-215\n"
				+ "-239\n"
				+ "-87\n"
				+ "-341\n"
				+ "-241\n"
				+ "-340\n"
				+ "-343\n"
				+ "-372\n"
				+ "-305\n"
				+ "-252\n"
				+ "-398\n"
				+ "-208\n"
				+ "-284\n"
				+ "-28\n"
				+ "-11\n"
				+ "-222\n"
				+ "-360\n"
				+ "-190\n"
				+ "-9\n"
				+ "-233\n"
				+ "-68\n"
				+ "-14\n"
				+ "-220\n"
				+ "-34\n"
				+ "-87\n"
				+ "-392\n"
				+ "-84\n"
				+ "-41\n"
				+ "-187\n"
				+ "-59\n"
				+ "-247\n"
				+ "-258\n"
				+ "-143\n"
				+ "-102\n"
				+ "-208\n"
				+ "-182\n"
				+ "-254\n"
				+ "-67\n"
				+ "-182\n"
				+ "-279\n"
				+ "-339\n"
				+ "-200\n"
				+ "-445\n"
				+ "-43\n"
				+ "-120\n"
				+ "-418\n"
				+ "-273\n"
				+ "-201\n"
				+ "-113\n"
				+ "-394\n"
				+ "-4\n"
				+ "-197\n"
				+ "-313\n"
				+ "-116\n"
				+ "-62\n"
				+ "-323\n"
				+ "-47\n"
				+ "-14\n"
				+ "-24\n"
				+ "-416\n"
				+ "-150\n"
				+ "-28\n"
				+ "-288\n"
				+ "-461\n"
				+ "0\n"
				+ "-388\n"
				+ "-375\n"
				+ "-424\n"
				+ "-302\n"
				+ "-4\n"
				+ "-75\n"
				+ "-54\n"
				+ "-288\n"
				+ "-212\n"
				+ "-436\n"
				+ "-414\n"
				+ "-447\n"
				+ "-362\n"
				+ "-120\n"
				+ "-467\n"
				+ "-135\n"
				+ "-93\n"
				+ "-268\n"
				+ "-8\n"
				+ "-192\n"
				+ "-342\n"
				+ "-466\n"
				+ "-162\n"
				+ "-387\n"
				+ "-348\n"
				+ "-351\n"
				+ "-236\n"
				+ "-123\n"
				+ "-51\n"
				+ "-225\n"
				+ "-259\n"
				+ "-52\n"
				+ "-14\n"
				+ "-26\n"
				+ "-347\n"
				+ "-327\n"
				+ "-206\n"
				+ "-37\n"
				+ "-77\n"
				+ "-316\n"
				+ "-278\n"
				+ "-195\n"
				+ "-348\n"
				+ "-330\n"
				+ "-235\n"
				+ "-125\n"
				+ "-36\n"
				+ "-323\n"
				+ "-10\n"
				+ "-77\n"
				+ "-133\n"
				+ "-353\n"
				+ "-109\n"
				+ "-51\n"
				+ "-134\n"
				+ "-281\n"
				+ "-227\n"
				+ "-483\n"
				+ "-406\n"
				+ "-11\n"
				+ "-356\n"
				+ "-443\n"
				+ "-104\n"
				+ "-264\n"
				+ "-90\n"
				+ "-489\n"
				+ "-39\n"
				+ "-145\n"
				+ "-318\n"
				+ "-399\n"
				+ "-238\n"
				+ "-434\n"
				+ "-145\n"
				+ "-122\n"
				+ "-507\n"
				+ "-196\n"
				+ "-156\n"
				+ "-251\n"
				+ "-370\n"
				+ "-207\n"
				+ "-534\n"
				+ "-171\n"
				+ "-117\n"
				+ "-416\n"
				+ "-226\n"
				+ "-393\n"
				+ "-133\n"
				+ "-391\n"
				+ "-347\n"
				+ "-510\n"
				+ "-121\n"
				+ "-84\n"
				+ "2\n"
				+ "-110\n"
				+ "-427\n"
				+ "-456\n"
				+ "-184\n"
				+ "-295\n"
				+ "-337\n"
				+ "-444\n"
				+ "-143\n"
				+ "-120\n"
				+ "-163\n"
				+ "-351\n"
				+ "-36\n"
				+ "-483\n"
				+ "-315\n"
				+ "-240\n"
				+ "-462\n"
				+ "-367\n"
				+ "-237\n"
				+ "-277\n"
				+ "-102\n"
				+ "-426\n"
				+ "-250\n"
				+ "-240\n"
				+ "-503\n"
				+ "-567\n"
				+ "-324\n"
				+ "-555\n"
				+ "-14\n"
				+ "-197\n"
				+ "-24\n"
				+ "-371\n"
				+ "-484\n"
				+ "-54\n"
				+ "-13\n"
				+ "-432\n"
				+ "-343\n"
				+ "-54\n"
				+ "-450\n"
				+ "-374\n"
				+ "-28\n"
				+ "-154\n"
				+ "-216\n"
				+ "-314\n"
				+ "-122\n"
				+ "-281\n"
				+ "-495\n"
				+ "-351\n"
				+ "-365\n"
				+ "-528\n"
				+ "-545\n"
				+ "-429\n"
				+ "-411\n"
				+ "-93\n"
				+ "-230\n"
				+ "-170\n"
				+ "-188\n"
				+ "-227\n"
				+ "-499\n"
				+ "-562\n"
				+ "-275\n"
				+ "-412\n"
				+ "-597\n"
				+ "-64\n"
				+ "-303\n"
				+ "-374\n"
				+ "-262\n"
				+ "-359\n"
				+ "-549\n"
				+ "-579\n"
				+ "-379\n"
				+ "-143\n"
				+ "-598\n"
				+ "-273\n"
				+ "-618\n"
				+ "-449\n"
				+ "-425\n"
				+ "-441\n"
				+ "-251\n"
				+ "-135\n"
				+ "-150\n"
				+ "-521\n"
				+ "-561\n"
				+ "-460\n"
				+ "-79\n"
				+ "-252\n"
				+ "-336\n"
				+ "-27\n"
				+ "-331\n"
				+ "-335\n"
				+ "-46\n"
				+ "-555\n"
				+ "-121\n"
				+ "-447\n"
				+ "-563\n"
				+ "-617\n"
				+ "-42\n"
				+ "-125\n"
				+ "-92\n"
				+ "-472\n"
				+ "-41\n"
				+ "-164\n"
				+ "-450\n"
				+ "-372\n"
				+ "-584\n"
				+ "-327\n"
				+ "-278\n"
				+ "-307\n"
				+ "-378\n"
				+ "-513\n"
				+ "-52\n"
				+ "-55\n"
				+ "-551\n"
				+ "-81\n"
				+ "-550\n"
				+ "-472\n"
				+ "-347\n"
				+ "-664\n"
				+ "-348\n"
				+ "-150\n"
				+ "-88\n"
				+ "-7\n"
				+ "-559\n"
				+ "-475\n"
				+ "-553\n"
				+ "-342\n"
				+ "-20\n"
				+ "-411\n"
				+ "-574\n"
				+ "-419\n"
				+ "-363\n"
				+ "-176\n"
				+ "-379\n"
				+ "-429\n"
				+ "-548\n"
				+ "-649\n"
				+ "-178\n"
				+ "-449\n"
				+ "-594\n"
				+ "-536\n"
				+ "-386\n"
				+ "-108\n"
				+ "-552\n"
				+ "-179\n"
				+ "-578\n"
				+ "-398\n"
				+ "-281\n"
				+ "-3\n"
				+ "-93\n"
				+ "-706\n"
				+ "-679\n"
				+ "-623\n"
				+ "-140\n"
				+ "-682\n"
				+ "-59\n"
				+ "-710\n"
				+ "-416\n"
				+ "-390\n"
				+ "-217\n"
				+ "-679\n"
				+ "-540\n"
				+ "-85\n"
				+ "-31\n"
				+ "-403\n"
				+ "-28\n"
				+ "-15\n"
				+ "-105\n"
				+ "-388\n"
				+ "-571\n"
				+ "-103\n"
				+ "-136\n"
				+ "-404\n"
				+ "-555\n"
				+ "-667\n"
				+ "-189\n"
				+ "-460\n"
				+ "-433\n"
				+ "-278\n"
				+ "-310\n"
				+ "-300\n"
				+ "-393\n"
				+ "-383\n"
				+ "-203\n"
				+ "-632\n"
				+ "-482\n"
				+ "-371\n"
				+ "-385\n"
				+ "-265\n"
				+ "-197\n"
				+ "-100\n"
				+ "-512\n"
				+ "-668\n"
				+ "-291\n"
				+ "-626\n"
				+ "-384\n"
				+ "-40\n"
				+ "-21\n"
				+ "-411\n"
				+ "-288\n"
				+ "-187\n"
				+ "-56\n"
				+ "-556\n"
				+ "-455\n"
				+ "-114\n"
				+ "-560\n"
				+ "-205\n"
				+ "-22\n"
				+ "-442\n"
				+ "-38\n"
				+ "-260\n"
				+ "-492\n"
				+ "-276\n"
				+ "-621\n"
				+ "-202\n"
				+ "-183\n"
				+ "-5\n"
				+ "-345\n"
				+ "-25\n"
				+ "-500\n"
				+ "-633\n"
				+ "-476\n"
				+ "-47\n"
				+ "-778\n"
				+ "-726\n"
				+ "-628\n"
				+ "-308\n"
				+ "-715\n"
				+ "-705\n"
				+ "-247\n"
				+ "-670\n"
				+ "-699\n"
				+ "-136\n"
				+ "-521\n"
				+ "-311\n"
				+ "-773\n"
				+ "-333\n"
				+ "-721\n"
				+ "-77\n"
				+ "-76\n"
				+ "-197\n"
				+ "-101\n"
				+ "-31\n"
				+ "-6\n"
				+ "-701\n"
				+ "-640\n"
				+ "-678\n"
				+ "-421\n"
				+ "-778\n"
				+ "-627\n"
				+ "-359\n"
				+ "-789\n"
				+ "-463\n"
				+ "-99\n"
				+ "-557\n"
				+ "-796\n"
				+ "-12\n"
				+ "-678\n"
				+ "-591\n"
				+ "-359\n"
				+ "-711\n"
				+ "-175\n"
				+ "-82\n"
				+ "-18\n"
				+ "-347\n"
				+ "-601\n"
				+ "-819\n"
				+ "0\n"
				+ "-40\n"
				+ "-32\n"
				+ "-454\n"
				+ "-680\n"
				+ "-783\n"
				+ "-269\n"
				+ "-744\n"
				+ "-726\n"
				+ "-336\n"
				+ "-563\n"
				+ "-152\n"
				+ "-782\n"
				+ "-651\n"
				+ "-674\n"
				+ "-788\n"
				+ "-311\n"
				+ "-640\n"
				+ "-525\n"
				+ "-54\n"
				+ "-317\n"
				+ "-312\n"
				+ "-328\n"
				+ "-128\n"
				+ "-162\n"
				+ "-133\n"
				+ "-769\n"
				+ "-669\n"
				+ "-611\n"
				+ "-553\n"
				+ "-247\n"
				+ "-174\n"
				+ "-217\n"
				+ "-497\n"
				+ "-202\n"
				+ "-450\n"
				+ "-486\n"
				+ "-102\n"
				+ "-35\n"
				+ "-273\n"
				+ "-436\n"
				+ "-282\n"
				+ "-343\n"
				+ "-544\n"
				+ "-602\n"
				+ "-171\n"
				+ "-444\n"
				+ "-865\n"
				+ "-206\n"
				+ "-486\n"
				+ "-5\n"
				+ "-566\n"
				+ "-444\n"
				+ "-496\n"
				+ "-142\n"
				+ "-502\n"
				+ "-9\n"
				+ "-359\n"
				+ "-330\n"
				+ "-797\n"
				+ "-735\n"
				+ "-726\n"
				+ "-66\n"
				+ "-290\n"
				+ "-716\n"
				+ "-494\n"
				+ "-796\n"
				+ "-373\n"
				+ "-519\n"
				+ "-502\n"
				+ "-78\n"
				+ "-622\n"
				+ "-602\n"
				+ "-408\n"
				+ "-511\n"
				+ "-114\n"
				+ "-330\n"
				+ "-794\n"
				+ "-102\n"
				+ "-795\n"
				+ "-882\n"
				+ "-264\n"
				+ "-771\n"
				+ "-832\n"
				+ "-729\n"
				+ "-527\n"
				+ "-264\n"
				+ "-4\n"
				+ "-12\n"
				+ "-517\n"
				+ "-516\n"
				+ "-85\n"
				+ "-899\n"
				+ "-693\n"
				+ "-759\n"
				+ "-367\n"
				+ "-844\n"
				+ "-377\n"
				+ "-207\n"
				+ "-590\n"
				+ "-551\n"
				+ "-93\n"
				+ "-810\n"
				+ "-449\n"
				+ "-464\n"
				+ "-111\n"
				+ "-161\n"
				+ "-154\n"
				+ "-823\n"
				+ "-60\n"
				+ "-523\n"
				+ "-265\n"
				+ "-219\n"
				+ "-903\n"
				+ "-170\n"
				+ "-601\n"
				+ "-785\n"
				+ "-144\n"
				+ "-439\n"
				+ "-190\n"
				+ "-275\n"
				+ "-72\n"
				+ "-75\n"
				+ "-175\n"
				+ "-137\n"
				+ "-842\n"
				+ "-336\n"
				+ "-169\n"
				+ "-245\n"
				+ "-480\n"
				+ "-751\n"
				+ "-363\n"
				+ "-610\n"
				+ "-505\n"
				+ "-771\n"
				+ "-85\n"
				+ "-337\n"
				+ "-307\n"
				+ "-687\n"
				+ "-731\n"
				+ "-118\n"
				+ "-313\n"
				+ "-541\n"
				+ "-490\n"
				+ "-485\n"
				+ "-647\n"
				+ "-104\n"
				+ "-101\n"
				+ "-324\n"
				+ "-57\n"
				+ "-5\n"
				+ "-610\n"
				+ "-251\n"
				+ "-841\n"
				+ "-97\n"
				+ "-562\n"
				+ "-664\n"
				+ "-765\n"
				+ "-969\n"
				+ "-449\n"
				+ "-172\n"
				+ "-846\n"
				+ "-465\n"
				+ "-212\n"
				+ "-600\n"
				+ "-62\n"
				+ "-399\n"
				+ "-923\n"
				+ "-62\n"
				+ "-400\n"
				+ "-197\n"
				+ "-144\n"
				+ "-699\n"
				+ "-730\n"
				+ "-754\n"
				+ "-748\n"
				+ "-405\n"
				+ "-518\n"
				+ "-633\n"
				+ "-893\n"
				+ "-675\n"
				+ "-717\n"
				+ "-380\n"
				+ "-464\n"
				+ "-193\n"
				+ "-590\n"
				+ "-888\n"
				+ "-64\n"
				+ "-111\n"
				+ "-905\n"
				+ "-774\n"
				+ "-209\n"
				+ "-492\n"
				+ "-64\n"
				+ "-589\n"
				+ "-952\n"
				+ "-59\n"
				+ "-5\n"
				+ "-615\n"
				+ "-208\n"
				+ "-131\n"
				+ "-867\n"
				+ "-594\n"
				+ "-668\n"
				+ "-253\n"
				+ "-75\n"
				+ "-418\n"
				+ "-5\n"
				+ "-394\n"
				+ "-456\n"
				+ "-266\n"
				+ "-669\n"
				+ "-335\n"
				+ "-687\n"
				+ "-661\n"
				+ "-73\n"
				+ "-446\n"
				+ "-828\n"
				+ "-413\n"
				+ "-643\n"
				+ "-410\n"
				+ "-91\n"
				+ "-25\n"
				+ "-84\n"
				+ "-335\n"
				+ "-943\n"
				+ "-355\n"
				+ "-155\n"
				+ "-778\n"
				+ "-1013\n"
				+ "-772\n"
				+ "-976\n"
				+ "-305\n"
				+ "-575\n"
				+ "-1060\n"
				+ "-148\n"
				+ "-931\n"
				+ "-588\n"
				+ "-204\n"
				+ "-56\n"
				+ "-102\n"
				+ "-467\n"
				+ "-937\n"
				+ "-481\n"
				+ "-890\n"
				+ "-503\n"
				+ "-379\n"
				+ "-442\n"
				+ "-774\n"
				+ "-316\n"
				+ "-732\n";
	}

//	// Same values as above, but with manual formatting
//	private static int[] getFormattedInput() {
//		return new int[] {
//				0, 1, 1, -2, -2, -2, -2, -4, 2, 0, -9, 1, -5, -12, -9, -10,
//				-7, -7, -2, -10, -2, 0, -6, -14, -6, -24, -10, 0, -4, 1, 1,
//				-30, -31, -24, 0, -20, -24, -10, -2, 0, -28, -8, -3, -23, -6,
//				2, -41, -36, -2, -11, -39, -27, -21, -48, -38, -4, -29, -49,
//				-9, -57, -33, -60, -42, -12, -21, -19, -21, -52, -65, -62,
//				-26, -64, -67, -12, -22, -21, -30, -31, -20, -54, -57, -20,
//				-18, -46, -1, 1, -76, -25, -80, -60, -80, -36, -30, -85, -21,
//				-89, -62, -66, -4, -39, -64, -39, -88, -17, -5, -69, -90, -14,
//				-2, -13, -76, 1, -10, -10, -28, 0, -96, -16, -33, -90, -56,
//				-25, -9, -4, -110, -54, -72, -92, -127, -112, -38, -17, -114,
//				-82, -35, -51, -41, -3, -14, -69, -102, -72, -6, -118, -80,
//				-111, -96, -45, -43, -19, -37, -24, -75, -75, -115, -54, -52,
//				-19, -123, -151, -122, -96, -20, -46, -67, -128, -123, -9,
//				-43, -34, -160, -111, 2, -164, -158, -63, -67, -2, -145, -103,
//				-80, -53, -148, -103, -47, 0, -178, -147, -57, -152, -46,
//				-173, -119, -184, -69, -113, -112, -51, -33, -187, -172, -172,
//				-122, -56, -59, -24, -204, -86, -65, -152, -119, -201, -75,
//				-16, -106, -159, -152, -77, -29, -9, -39, -49, -141, -211,
//				-23, -145, -96, -94, -84, -99, -66, -9, -135, -185, -15, -184,
//				-123, -152, -94, -67, -43, -127, -3, -21, -11, -76, -129,
//				-139, -65, -185, -15, -215, -163, -232, -1, -173, -81, -148,
//				-12, 1, -251, -183, -214, -162, -213, -247, -165, -232, -107,
//				-75, -190, -205, -138, -149, -232, -99, -57, -148, -231, -188,
//				-141, -193, -39, -208, -245, -102, -247, -1, -76, -13, -20,
//				-263, -146, -158, -81, -270, -162, -70, -215, -286, -98, -186,
//				-184, -147, -146, -160, -172, -42, -48, -14, -175, -299, -293,
//				-214, -198, -128, -272, -108, -94, -102, -108, -53, 2, -172,
//				-41, -293, -14, -256, -92, -121, -140, -294, -54, -121, -221,
//				-145, -260, -298, -82, -284, -238, -15, -159, -159, -213, -31,
//				-44, -61, -203, -247, -39, -157, -130, -347, -272, -23, -185,
//				-162, -337, -91, -72, -91, -315, -144, -165, -360, -173, -258,
//				-275, -99, -81, -16, -72, -150, -238, -45, -7, -344, -364,
//				-339, -54, -61, -24, -324, -321, -294, -104, -87, -165, -113,
//				-4, -306, -198, -147, -136, -360, -217, -20, -391, -169, -209,
//				-12, -95, -164, -215, -239, -87, -341, -241, -340, -343, -372,
//				-305, -252, -398, -208, -284, -28, -11, -222, -360, -190, -9,
//				-233, -68, -14, -220, -34, -87, -392, -84, -41, -187, -59,
//				-247, -258, -143, -102, -208, -182, -254, -67, -182, -279,
//				-339, -200, -445, -43, -120, -418, -273, -201, -113, -394, -4,
//				-197, -313, -116, -62, -323, -47, -14, -24, -416, -150, -28,
//				-288, -461, 0, -388, -375, -424, -302, -4, -75, -54, -288,
//				-212, -436, -414, -447, -362, -120, -467, -135, -93, -268,
//				-8, -192, -342, -466, -162, -387, -348, -351, -236, -123,
//				-51, -225, -259, -52, -14, -26, -347, -327, -206, -37, -77,
//				-316, -278, -195, -348, -330, -235, -125, -36, -323, -10,
//				-77, -133, -353, -109, -51, -134, -281, -227, -483, -406,
//				-11, -356, -443, -104, -264, -90, -489, -39, -145, -318, -399,
//				-238, -434, -145, -122, -507, -196, -156, -251, -370, -207,
//				-534, -171, -117, -416, -226, -393, -133, -391, -347, -510,
//				-121, -84, 2, -110, -427, -456, -184, -295, -337, -444, -143,
//				-120, -163, -351, -36, -483, -315, -240, -462, -367, -237,
//				-277, -102, -426, -250, -240, -503, -567, -324, -555, -14,
//				-197, -24, -371, -484, -54, -13, -432, -343, -54, -450, -374,
//				-28, -154, -216, -314, -122, -281, -495, -351, -365, -528,
//				-545, -429, -411, -93, -230, -170, -188, -227, -499, -562,
//				-275, -412, -597, -64, -303, -374, -262, -359, -549, -579,
//				-379, -143, -598, -273, -618, -449, -425, -441, -251, -135,
//				-150, -521, -561, -460, -79, -252, -336, -27, -331, -335, -46,
//				-555, -121, -447, -563, -617, -42, -125, -92, -472, -41, -164,
//				-450, -372, -584, -327, -278, -307, -378, -513, -52, -55,
//				-551, -81, -550, -472, -347, -664, -348, -150, -88, -7, -559,
//				-475, -553, -342, -20, -411, -574, -419, -363, -176, -379,
//				-429, -548, -649, -178, -449, -594, -536, -386, -108, -552,
//				-179, -578, -398, -281, -3, -93, -706, -679, -623, -140, -682,
//				-59, -710, -416, -390, -217, -679, -540, -85, -31, -403, -28,
//				-15, -105, -388, -571, -103, -136, -404, -555, -667, -189,
//				-460, -433, -278, -310, -300, -393, -383, -203, -632, -482,
//				-371, -385, -265, -197, -100, -512, -668, -291, -626, -384,
//				-40, -21, -411, -288, -187, -56, -556, -455, -114, -560, -205,
//				-22, -442, -38, -260, -492, -276, -621, -202, -183, -5, -345,
//				-25, -500, -633, -476, -47, -778, -726, -628, -308, -715,
//				-705, -247, -670, -699, -136, -521, -311, -773, -333, -721,
//				-77, -76, -197, -101, -31, -6, -701, -640, -678, -421, -778,
//				-627, -359, -789, -463, -99, -557, -796, -12, -678, -591,
//				-359, -711, -175, -82, -18, -347, -601, -819, 0, -40, -32,
//				-454, -680, -783, -269, -744, -726, -336, -563, -152, -782,
//				-651, -674, -788, -311, -640, -525, -54, -317, -312, -328,
//				-128, -162, -133, -769, -669, -611, -553, -247, -174, -217,
//				-497, -202, -450, -486, -102, -35, -273, -436, -282, -343,
//				-544, -602, -171, -444, -865, -206, -486, -5, -566, -444,
//				-496, -142, -502, -9, -359, -330, -797, -735, -726, -66, -290,
//				-716, -494, -796, -373, -519, -502, -78, -622, -602, -408,
//				-511, -114, -330, -794, -102, -795, -882, -264, -771, -832,
//				-729, -527, -264, -4, -12, -517, -516, -85, -899, -693, -759,
//				-367, -844, -377, -207, -590, -551, -93, -810, -449, -464,
//				-111, -161, -154, -823, -60, -523, -265, -219, -903, -170,
//				-601, -785, -144, -439, -190, -275, -72, -75, -175, -137,
//				-842, -336, -169, -245, -480, -751, -363, -610, -505, -771,
//				-85, -337, -307, -687, -731, -118, -313, -541, -490, -485,
//				-647, -104, -101, -324, -57, -5, -610, -251, -841, -97, -562,
//				-664, -765, -969, -449, -172, -846, -465, -212, -600, -62,
//				-399, -923, -62, -400, -197, -144, -699, -730, -754, -748,
//				-405, -518, -633, -893, -675, -717, -380, -464, -193, -590,
//				-888, -64, -111, -905, -774, -209, -492, -64, -589, -952, -59,
//				-5, -615, -208, -131, -867, -594, -668, -253, -75, -418, -5,
//				-394, -456, -266, -669, -335, -687, -661, -73, -446, -828,
//				-413, -643, -410, -91, -25, -84, -335, -943, -355, -155, -778,
//				-1013, -772, -976, -305, -575, -1060, -148, -931, -588, -204,
//				-56, -102, -467, -937, -481, -890, -503, -379, -442, -774,
//				-316, -732
//		};
//	}

}
