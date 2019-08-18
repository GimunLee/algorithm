package selena_help;

import java.util.Arrays;

public class N_Tech01 {
	public static void main(String[] args) throws Exception {
		System.out.println(new N_Tech01().solution("ababsas"));
	} // end of main

	public String solution(String s) {
		Alpha[] alpha = new Alpha[26];

		for (int i = 0; i < alpha.length; i++) {
			alpha[i] = new Alpha();
		}

		s = s.toLowerCase(); // �ҹ��� ��ȯ

		for (int i = 0; i < s.length(); i++) {
			int num = (s.charAt(i) - '0') - 49; // idx ġȯ

			alpha[num].c = s.charAt(i);
			alpha[num].cnt++; // ���ĺ� ���� ����
		}

		Arrays.sort(alpha);

		String answer = "";

		if (alpha[0].cnt == alpha[1].cnt) { // 2�� �̻��� ��,
			int max = alpha[0].cnt;
			for (int i = 0; i < alpha.length; i++) {
				if (alpha[i].cnt == max) {
					answer += alpha[i].c;
				} else {
					break;
				}
			}
		} else { // �ϳ��� ��,
			answer += alpha[0].c;
		}
		return answer;
	}

	private static class Alpha implements Comparable<Alpha> {
		char c;
		int cnt;

		Alpha() {

		}

		@Override
		public int compareTo(Alpha o) {
			return o.cnt - this.cnt;
		}
	} // end of Alpha
} // end of class
