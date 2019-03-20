package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �ȳ��� : dfs 200ms 16*16 : dfs ����ġ�� �޸������̼� : ���
 */
public class Solution_1865_��ö�����Ϻй� {
	private static double[][] p;
	private static double max;
	private static int N;
	private static int[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		long time = System.currentTimeMillis();
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine()); // 1 <= N <= 16

			p = new double[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken()) / 100D; // 1 <= Pij <= 100
				}
			}
			select = new int[N];
			for (int i = 0; i < N; i++) {
				select[i] = i;
			}
			max = 0;
			f(0, 100d);

			System.out.printf("#%d %.6f\n", testCase, max); // �ݿø� �Ҽ��� �Ʒ� 6°�ڸ����� ���
		} // end of for testCase
		System.out.println(System.currentTimeMillis() - time + " ms");
	} // end of main

	public static void f(int step, double pp) {
		if (step == N) {
			if (max < pp) {
				max = pp;
			}
		} else {
			for (int i = step; i < N; i++) {
				swap(step, i);
				if (max < pp * p[step][select[step]]) {
					f(step + 1, pp * p[step][select[step]]);
				}
				swap(step, i);
			}
		}
	}

	public static void swap(int i, int j) {
		int temp = select[i];
		select[i] = select[j];
		select[j] = temp;
	}
} // end of class
