package practiceb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jdk.jfr.Unsigned;

public class Solution_Day03_4038_�ܾ�����ϴ�Ƚ�� {
	private static final double D = 2;
	private static final double M = Math.pow(2, 64);

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long TC = Integer.parseInt(br.readLine().trim()); // Test Case ��
		StringBuilder sb = new StringBuilder();
		for (long tc = 1; tc <= TC; tc++) {
			long ANSER = 0;
			String B = br.readLine();
			String S = br.readLine();
			String[] hashTable = new String[S.length()];

			double firstKey = getFirstKey(S);

			String firstB = B.substring(0, S.length());
			double hash = getFirstKey(firstB);
			if (firstKey == hash) {
				ANSER++;
			}

			int idx = 0;
			for (int i = S.length(); i < B.length(); i++) {
				char newString = B.charAt(i); // ���� ���� ����
				char oldString = B.charAt(idx); // �����ϴ� ����
				double oldStringD = Math.pow(2, S.length()) % M; // �����ϴ� ������ �����ִ� ��
				double oldHash = (oldString * oldStringD) % M;
				double newHash = ((hash - oldHash) * D) % M;
				hash = (newHash) + (newString * D) % M;
				if (firstKey == hash) {
					ANSER++;
				}
				idx++;
			}
			sb.append("#").append(tc).append(" ").append(ANSER).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static double getFirstKey(String S) {
		double key = 0;

		for (int i = 0; i < S.length(); i++) {
			double DVAL = Math.pow(2, S.length() - i) % M;
			key += S.charAt(i) * DVAL;
		}

		if (key < 0) {
			key = -key;
		}

		return key % M;
	}
}
