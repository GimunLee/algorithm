package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ������ ���α׷��� ���� ��û
 */
public class Solution_6719 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine().trim());
		for (int i = 1; i <= tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // ���� ��
			int K = Integer.parseInt(st.nextToken()); // ������ ���� ��

			String[] tmp = br.readLine().split(" ");
			int[] M = new int[tmp.length]; // ���̵�

			for (int j = 0; j < tmp.length; j++) {
				M[j] = Integer.parseInt(tmp[j]);
			}

			Arrays.sort(M);

			double Answer = 0;
			
			for (int j = M.length - K; j < M.length ; j++) {
				Answer = (Answer + M[j]) / 2;
			}
			
			System.out.printf("#%d %f\n", i, Answer);
		}
	}
}
