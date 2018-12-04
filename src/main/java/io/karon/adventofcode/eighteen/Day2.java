package io.karon.adventofcode.eighteen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


class Day2 {

	static class Star1 {

		static int getResult() {
			String[] formattedInput = getFormattedInput();
			Map<Character, Integer> letterCount = new HashMap<>();
			int numberOfDuplicates = 0;
			int numberOfTriplets = 0;

			for (String letters : formattedInput) {
				for (int i = 0; i < letters.length(); ++i) {
					letterCount.putIfAbsent(letters.charAt(i), 0);
					letterCount.computeIfPresent(letters.charAt(i), (ignored, count) -> count + 1);
				}

				for (int count : letterCount.values()) {
					if (count == 2) {
						++numberOfDuplicates;
						break;
					}
				}

				for (int count : letterCount.values()) {
					if (count == 3) {
						++numberOfTriplets;
						break;
					}
				}

				letterCount.clear();
			}

			return numberOfDuplicates * numberOfTriplets;
		}

	}

	static class Star2 {

		static String getResult() {
			String[] formattedInput = getFormattedInput();

			String box1 = "";
			String box2 = "";

			for (int i = 0; i < formattedInput.length; ++i) {
				for (int j = i + 1; j < formattedInput.length; ++j) {
					String commonLetters = computeCommonLetters(formattedInput[i], formattedInput[j]);
					if (commonLetters.length() == formattedInput[i].length() - 1) {
						return commonLetters;
					}
				}
			}

			return "";
		}

		private static String computeCommonLetters(String box1, String box2) {
			StringBuilder stringBuilder = new StringBuilder();

			for (int i = 0; i < box1.length(); ++i) {
				if (box1.charAt(i) == box2.charAt(i)) {
					stringBuilder.append(box1.charAt(i));
				}
			}

			return stringBuilder.toString();
		}

	}

	private static String[] getFormattedInput() {
		return getInput().split("\\n");
	}

	private static String getInput() {
		return "vtnihorkulbfvjcyzmsjgdxplw\n"
				+ "vtnihorvujbfejcyzmsqgdlpaw\n"
				+ "vtnihoriulbzejcyzmsrgdxpaw\n"
				+ "vtsihowkulbfejcyzmszgdxpaw\n"
				+ "vtnizorkunbfejcyzmsqgdypaw\n"
				+ "vtnihorkdlbfojcyzmsqgdfpaw\n"
				+ "vtpioorkulbfejcysmsqgdxpaw\n"
				+ "vtnvhorkulbfhjcyzmsqgdipaw\n"
				+ "vtrihorkylbaejcyzmsqgdxpaw\n"
				+ "vtnigorkulbfejcyzmszgjxpaw\n"
				+ "rtnihorkklbfejcyzmslgdxpaw\n"
				+ "vtnihorkqlbfejcyzmsqgmppaw\n"
				+ "vgnisorkulbfejcyzmsqgdkpaw\n"
				+ "atnihorkulbfejcyzmdbgdxpaw\n"
				+ "vtnihorkulbfejcezmsqqixpaw\n"
				+ "vtnihorkucbfejcxzmsqgdypaw\n"
				+ "vtnihorkulxfajcyzmsqgyxpaw\n"
				+ "vbnihorkulbfescyzmsqgdxpae\n"
				+ "vanshorkulbfejcyzjsqgdxpaw\n"
				+ "vtnihoukulbfejcyzmwqgdxpbw\n"
				+ "vtndhorkulbfejcyxmgqgdxpaw\n"
				+ "ztnihlrkupbfejcyzmsqgdxpaw\n"
				+ "vtoihkrkulbfejhyzmsqgdxpaw\n"
				+ "vtnihorkalbiejcyzmsqgdxeaw\n"
				+ "vtnihorhulcfejcyzqsqgdxpaw\n"
				+ "vtnshotkulbfejcyzmsqvdxpaw\n"
				+ "vtnihoryulbfejcyzmsqgpspaw\n"
				+ "vtnihorkukbfmjcyzmsqgdxpcw\n"
				+ "vtnihorkulbfejcybmsqgdupxw\n"
				+ "vlnihorkulbfejcyzmsqgdmpac\n"
				+ "vtnihorkulbfejcezmfqgdkpaw\n"
				+ "vpnihorkulbfejcyzmsqfdxyaw\n"
				+ "vtnihorkulbjejcysmcqgdxpaw\n"
				+ "vdnihorkulffejcyzisqgdxpaw\n"
				+ "vtnihorkulkfsjcyzrsqgdxpaw\n"
				+ "vtnihorkulqfegoyzmsqgdxpaw\n"
				+ "vtnihorkulbfeyhyzgsqgdxpaw\n"
				+ "vnnihorkulbfejcyzmdqgdxpfw\n"
				+ "vtnihorkulstejcyzmsqgdxpak\n"
				+ "vtnphorkujbfejcczmsqgdxpaw\n"
				+ "vtpihorkulbfejcyzmskgdxiaw\n"
				+ "vtnihorkulbfejcdzmxqsdxpaw\n"
				+ "vtnihorgulbfticyzmsqgdxpaw\n"
				+ "veniuorkulbfejcyzmsqgdmpaw\n"
				+ "vhnihorkclbfejyyzmsqgdxpaw\n"
				+ "vtnihorkulbfejcyzmrqgsrpaw\n"
				+ "dtnihorkzlhfejcyzmsqgdxpaw\n"
				+ "vtnizorkulbfejcyzhsqgdxdaw\n"
				+ "vtnihohkulbfejcybmpqgdxpaw\n"
				+ "vtnihbrzulbfejcyzmsqgdppaw\n"
				+ "stnihorkulmfejcyzmsqgdxeaw\n"
				+ "vtnihorkulbfejmgzwsqgdxpaw\n"
				+ "vtnihcrkulbfnjdyzmsqgdxpaw\n"
				+ "vxxihorkulbfejcyzmsqddxpaw\n"
				+ "vtnhhorkulbfejcyzmsqgdpiaw\n"
				+ "vtnihoakulbfvjcyzmmqgdxpaw\n"
				+ "vtbbhorkulbfejcyqmsqgdxpaw\n"
				+ "vtnihnukulbfejcxzmsqgdxpaw\n"
				+ "vteihorgulkfejcyzmsqgdxpaw\n"
				+ "vbnihorkulbfejcyzmsqgdxpmt\n"
				+ "itnihorkulbuejcyzmsqgdxpxw\n"
				+ "vtnfhqrkulbfejcwzmsqgdxpaw\n"
				+ "vtnihorkubbfedcyzmsqpdxpaw\n"
				+ "rtnihorkulhfejcyzmsqgdxpah\n"
				+ "vtnihorzulbfejcyqmsqqdxpaw\n"
				+ "vtnihorkulbfeecyzmsqgdcgaw\n"
				+ "vtniuorkulbfejpyzmsqxdxpaw\n"
				+ "vtnicorkilbfajcyzmsqgdxpaw\n"
				+ "vtzihorkulbfejcyzmsqgaxpkw\n"
				+ "vtnihomkulbfejcyzmsqgvmpaw\n"
				+ "vznihockulbfejcyzmsqgdjpaw\n"
				+ "vtqmhorkulhfejcyzmsqgdxpaw\n"
				+ "ptnihorkulbfsjcyzbsqgdxpaw\n"
				+ "ftnihorkulbfejcepmsqgdxpaw\n"
				+ "vtnhhorkulbfejyyzxsqgdxpaw\n"
				+ "vtnihorkulbfejcyzmsiwdxpxw\n"
				+ "vtnikorkulbfejvyzmnqgdxpaw\n"
				+ "vtnihorkulbgejoyzmsqhdxpaw\n"
				+ "vtnihorkulbwejqylmsqgdxpaw\n"
				+ "vtnihorkdlbfejcyzwsqgdrpaw\n"
				+ "vtnihorkulbfojcyzmtugdxpaw\n"
				+ "vtnihonkulbtejcyzxsqgdxpaw\n"
				+ "vtnihorkulrfevcyzmsqgdxpcw\n"
				+ "vtnioorkulbfejcynmsqgdxpad\n"
				+ "vtnihorkudffejcyzhsqgdxpaw\n"
				+ "vtnihorkelbfejcqzmsqgdxbaw\n"
				+ "jtnihokkulbfejcyzmsqgdrpaw\n"
				+ "ztnihorrulbfejcyzlsqgdxpaw\n"
				+ "vtwiforkulbfejcyzmsqpdxpaw\n"
				+ "vtnihopvulbfejcyzmsqgzxpaw\n"
				+ "vtnihzrkulafejcyzmsqgdxpaj\n"
				+ "vtnixoekulbfejcyzmsqgdxpak\n"
				+ "vtnihorkulbfejxyzmsqgdxhlw\n"
				+ "vtnihorkulbfwjcyzmmqcdxpaw\n"
				+ "vtnihorkulbfejcywmsdgdxzaw\n"
				+ "vtnihorkulbfejfyzmsqggxuaw\n"
				+ "vtnihnrkurbfejcyzmsqggxpaw\n"
				+ "vtuihorkulbkejcyzmsqgdxpww\n"
				+ "vtnihoriuljfejcyzssqgdxpaw\n"
				+ "vtnihorkulyftjcezmsqgdxpaw\n"
				+ "vtnihorkklbfeccyzmsqgdppaw\n"
				+ "vtnihorkulbfdpcyzmsqgdxpcw\n"
				+ "vtnihqrkulgfejcyzmeqgdxpaw\n"
				+ "vtnihorktlbfejdyzmswgdxpaw\n"
				+ "vinihzrkulbfeacyzmsqgdxpaw\n"
				+ "vtuihorkupbfejcyzmsqgdxplw\n"
				+ "vtnihorkulbfcjcyzmlqgdxpsw\n"
				+ "vtnihorkllbfejcyzmsqgdxvak\n"
				+ "qtnihorkulbfdjcyzusqgdxpaw\n"
				+ "vtniherkulbhejcyzmsqgzxpaw\n"
				+ "vtnzhorgulbfejcyzmsqgdxpew\n"
				+ "vtnihoykulhfjjcyzmsqgdxpaw\n"
				+ "vtnihookulyfejcyzmsqgdxqaw\n"
				+ "jtnihorkulbfejcyzmssgdxpaq\n"
				+ "vtnicorkulwfejcyzmsxgdxpaw\n"
				+ "wtnihorkuubfejcyzmsqgdxpam\n"
				+ "vtnihorkuggfejcyzmsdgdxpaw\n"
				+ "vtnihurkulbfgjcyzmsqrdxpaw\n"
				+ "ptnihorkuabfejcyzmsqgqxpaw\n"
				+ "vtrihoykulbfejcyzmsqgdxpam\n"
				+ "otnihorkulbfejcyzmpqgdxpjw\n"
				+ "vtyihorkulbfejdyznsqgdxpaw\n"
				+ "vtnihornulbfrjcizmsqgdxpaw\n"
				+ "vtnihfrlulbfejcyzmsqgdxpah\n"
				+ "atnihorkulbfejcyossqgdxpaw\n"
				+ "vtnihorkuljfejcyzysqgdxpow\n"
				+ "vtniyorkulbfejcyzmsqgdxbaz\n"
				+ "venihorkulbfejctzmqqgdxpaw\n"
				+ "vtrihorkulbfejcyjmsqgdxpfw\n"
				+ "venitorkulbfexcyzmsqgdxpaw\n"
				+ "vtbihorkulbfejcyzmwqgdxpyw\n"
				+ "vtnihorkuubfejxyzmsqgdxkaw\n"
				+ "vtqihorkulbnejcyzmsqgdxnaw\n"
				+ "vteihorkurbfejcyzmsqwdxpaw\n"
				+ "vtgjhorkxlbfejcyzmsqgdxpaw\n"
				+ "ytniworkulbfejcyzmsqgdxptw\n"
				+ "vtnihorkulbfejcyzmsvgixhaw\n"
				+ "dtnihorkusbfejcyzmsqidxpaw\n"
				+ "vtnihurkulbfejcdzmkqgdxpaw\n"
				+ "vtgihorkulbfejcyzhsqgdxpaf\n"
				+ "vtniudrkulbfeacyzmsqgdxpaw\n"
				+ "vtnihorkulbfejcyemsokdxpaw\n"
				+ "vtnihorkulbfejcyjmwqgdxpag\n"
				+ "vtnihorpulbfetcpzmsqgdxpaw\n"
				+ "ctnzhorkulbfejcyzmfqgdxpaw\n"
				+ "vanihorkuhbwejcyzmsqgdxpaw\n"
				+ "vtnihonkurbfejcyzvsqgdxpaw\n"
				+ "vtnihgrkulbcejcbzmsqgdxpaw\n"
				+ "vtnihorkutbfeacyzmsqcdxpaw\n"
				+ "vtniaorkuhbfqjcyzmsqgdxpaw\n"
				+ "vtnihorkylbfozcyzmsqgdxpaw\n"
				+ "vtnihorkulbfejcypmfqgdxpbw\n"
				+ "vtrphonkulbfejcyzmsqgdxpaw\n"
				+ "vtnihorkulbdejcywmsqydxpaw\n"
				+ "vtnikorkulbfejvyzknqgdxpaw\n"
				+ "vznihorkulbfejcyzmsqbdxpam\n"
				+ "vtmghorkulbfejcyzmsqghxpaw\n"
				+ "vtnihorkulbfejcyzmshglxpfw\n"
				+ "vtiihorkulbfejcjzmsqgdxoaw\n"
				+ "rtnihorkulbfejcuzmsqgdxpow\n"
				+ "vtnthoikulbuejcyzmsqgdxpaw\n"
				+ "vtniholkulbfcjcyzmsqgdxpvw\n"
				+ "vdnihorkulbbejcyzmsqgdxdaw\n"
				+ "ntnihorkulbfojcyzmsqgdxzaw\n"
				+ "vtniqorkulbfejcyzklqgdxpaw\n"
				+ "vtnihorkulhfejcpzmsqgdxprw\n"
				+ "vhnihorkulqfejcyzmsqgdapaw\n"
				+ "vtnihorkolafejcyzmsqadxpaw\n"
				+ "vtnihorkulbpejcyzasqgtxpaw\n"
				+ "vtnihgiyulbfejcyzmsqgdxpaw\n"
				+ "dtnihorkulbffjcyzmsqgdfpaw\n"
				+ "vtnvhorhulbfejcyzmpqgdxpaw\n"
				+ "vtniholkulbfebcyzmsqgnxpaw\n"
				+ "vunihorkulbbejcyznsqgdxpaw\n"
				+ "vtnihorkulbfehcyomsqgaxpaw\n"
				+ "vtnihorkllboejcyzmsigdxpaw\n"
				+ "vtnihwrkulbfejcywmsqgdxiaw\n"
				+ "vtnoherkulbfbjcyzmsqgdxpaw\n"
				+ "vtnbhorkulbfejcyzmsqgkxpaa\n"
				+ "vtnihorkilbfdjxyzmsqgdxpaw\n"
				+ "vtnfhorkuvbfejcyzmsqgixpaw\n"
				+ "vtnyhorkulbpejcyzmsqgdxjaw\n"
				+ "vtnihomkalbfejcyzmqqgdxpaw\n"
				+ "vtnihorkulbfejcybmsqgjxvaw\n"
				+ "vtnihorkulbfgjcnzmsqbdxpaw\n"
				+ "vtnihorkulbfejcyzmpqgsxpap\n"
				+ "lmnihorkulbfejcizmsqgdxpaw\n"
				+ "vtmahkrkulbfejcyzmsqgdxpaw\n"
				+ "vtnihorkulbfujcyrcsqgdxpaw\n"
				+ "vtnzhorkulbfejcyzjvqgdxpaw\n"
				+ "vtnicorkulbfejmyzmsqgdxvaw\n"
				+ "vtnyhojkulbfejcyzmsngdxpaw\n"
				+ "vtnuhorkulbfejcyzlsqgdxpmw\n"
				+ "vtnihorkulufejcgzmtqgdxpaw\n"
				+ "vtnihfrkulbfejnyzmsigdxpaw\n"
				+ "vtnzhorkulbdejnyzmsqgdxpaw\n"
				+ "vttihorkulbfejcyzmyqgdxwaw\n"
				+ "vknihorkulbfejnylmsqgdxpaw\n"
				+ "vtnihorkulbfejcfrmsqgdxpaz\n"
				+ "vtnchormulbfejcyzmsqgdopaw\n"
				+ "vtnihorkulbfebcyzusqgdxpam\n"
				+ "jtnihorwulbfejcyzksqgdxpaw\n"
				+ "ctnihodkutbfejcyzmsqgdxpaw\n"
				+ "vonihorkultfejcyzmsqgixpaw\n"
				+ "vtnihorkulbqejcyzmsqgdypcw\n"
				+ "vfnihorkulbfbjcyzmsqcdxpaw\n"
				+ "utnihorkulbfejcyqmsqgdxraw\n"
				+ "vtnihorkjllfejcyzmskgdxpaw\n"
				+ "vtnihorkulbfejcyvisqgdapaw\n"
				+ "vtnihorkclzfejcyzmsqvdxpaw\n"
				+ "vtnihwrkvlffejcyzmsqgdxpaw\n"
				+ "vtnihlrkulbfejcrzmsqydxpaw\n"
				+ "vtnihorkulbfbjtysmsqgdxpaw\n"
				+ "vtnihorkulbfxjcepmsqgdxpaw\n"
				+ "ttnihorkulbfejpyzmsqgdxpaz\n"
				+ "vtnwhorkolbfejcdzmsqgdxpaw\n"
				+ "vtnihorkulbfejcyzdsqgdxppn\n"
				+ "vtnwporkulbfercyzmsqgdxpaw\n"
				+ "vtnshorxuvbfejcyzmsqgdxpaw\n"
				+ "vtnihxrkulbfejcyomsqfdxpaw\n"
				+ "vtnwhorkrljfejcyzmsqgdxpaw\n"
				+ "vqnihorkulbfejcyzmtqgdxpuw\n"
				+ "vtnnhorkulbfhrcyzmsqgdxpaw\n"
				+ "vtuihwrkulbfedcyzmsqgdxpaw\n"
				+ "vtgbhorkucbfejcyzmsqgdxpaw\n"
				+ "vtnitorkulbfejcozmsqgdkpaw\n"
				+ "otnihomkulbfejcyzmsqgdxhaw\n"
				+ "vtnihgrkulbfrjcyzmsqxdxpaw\n"
				+ "vtnihorkulbfujcyzmsqghxpzw\n"
				+ "vsnihopkhlbfejcyzmsqgdxpaw\n"
				+ "vtniherkulbfejcyzmpzgdxpaw\n"
				+ "vtnxhorkulbfejcszmsqgdxcaw\n"
				+ "vtnihorkulbfejctzmsxadxpaw\n"
				+ "vtnihorkslbmejcyzmsqgnxpaw\n"
				+ "vtnwhorgulbfegcyzmsqgdxpaw\n"
				+ "vtnihorkulbfsjcyzmsqgdxiau\n"
				+ "vtnihorkulbfejcyzmsqldxpbj\n"
				+ "vtnghorkulbfmjcyzmsqgdxpaa\n"
				+ "vtnihorkulbfetcyzmpqudxpaw\n"
				+ "vtnbhorkulbfejcyzmsqgdupyw\n"
				+ "ntnihorhulbfejwyzmsqgdxpaw\n"
				+ "vjnihorkulbfejcyqosqgdxpaw\n"
				+ "vtiihorbulbfejcbzmsqgdxpaw\n"
				+ "vtnihorkulbfejxlzmpqgdxpaw\n"
				+ "vtnihorkolbfejcyfmsqgdxraw\n"
				+ "vtnihqrrulbfedcyzmsqgdxpaw\n"
				+ "ctnihorkulbfejcyzmsqgdxpsy\n"
				+ "vtnihorkulbfkjcezmspgdxpaw\n"
				+ "ztnihorkulbmcjcyzmsqgdxpaw\n"
				+ "vinihorkulbfedcyzmsqgdxpau\n";
	}

}
