package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �а��� �ű�� BruteForce : ���� ��Ȯ�� ����, �ð��ʰ� �Ұ��� Greedy : ���� ���ǿ��� ����ϴ� �����δ� �Ұ���, ū
 * ������ �Ϻθ� ū ���� ������ ������ ���� ����, ������ ¥������ �̸� ���س���, ���Ѵ�. DP : ������, ������ȹ��, �����غ��� ���ؼ�
 * ū �ظ� ã��
 * 
 */

public class Algo2_����_05_�̱⹮_Prof {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // ����� ���ڿ��� ������ ���� 
		
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int W = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());

			int[] pack = new int[N]; // ���� �뷮�� ������ ������ �迭
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < pack.length; i++) {
				pack[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[W + 1];
			final int M = Integer.MAX_VALUE; // �Ұ���

			for (int i = 0; i < dp.length; i++) { // ù��° ������ ����
				if (i % pack[0] == 0) {
					dp[i] = i / pack[0];
				} else {
					dp[i] = M;
				}
			}

			// �ι�° ���� ������ �ѹ��� ��� ó��
			for (int i = 1; i < pack.length; i++) { // ���� ����
				for (int j = pack[i]; j < dp.length; j++) {
					if (dp[j - pack[i]] != M && dp[j] > dp[j - pack[i]] + 1) {
						dp[j] = dp[j - pack[i]] + 1; // ��ȭ��
					}
				}
			}

//			System.out.println("#" + tc + " " + (dp[W] == M ? -1 : dp[W]));
			sb.append('#').append(tc).append(' ').append(dp[W] == M ? -1 : dp[W]).append('\n');
		} // end of testCase

		System.out.print(sb.toString());
	} // end of main
} // end of class
