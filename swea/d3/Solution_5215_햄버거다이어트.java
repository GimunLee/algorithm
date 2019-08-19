package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_�ܹ��Ŵ��̾�Ʈ {
	static int N, L; // N : ����� ��, L : ���� Į�θ�
	static int[] score_arr, calory_arr; // score : ����, calory : Į�θ�
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim()); // Test Case

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // ��� ��
			L = Integer.parseInt(st.nextToken()); // ���� Į�θ�

			score_arr = new int[N];
			calory_arr = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				score_arr[i] = Integer.parseInt(st.nextToken());
				calory_arr[i] = Integer.parseInt(st.nextToken());
			}

			maxScore = 0;

			search(0, 0, 0); // index, score, calory
			System.out.println("#" + tc + " " + maxScore);
		}
	}

	private static void search(int idx, int score, int calory) {
		if (calory > L ) { // ����� �� Į�θ� > limit
			return;
		}
		if (idx == N) {
			System.out.println(maxScore);
			maxScore = Math.max(maxScore, score);
			return;
		}
		search(idx + 1, score + score_arr[idx], calory + calory_arr[idx]);
		search(idx + 1, score, calory);
	}
}
