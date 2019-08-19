package swea.d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1245_������ {
	static int[][] arr; // [n][0] : x��ǥ, [n][1] : ����

	/** �η� ���ϴ� ���� */
	public static double f(int n1, int n2) {
		int d = Math.abs(arr[n1][0] - arr[n2][0]);
		return (arr[n1][1] * arr[n2][1]) / (d * d);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			int N = Integer.parseInt(br.readLine().trim());

			arr = new int[N][2]; // [n][0] : x��ǥ, [n][1] : ����

			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = 0;
	
			while(st.hasMoreTokens()) {
				arr[idx][0] = Integer.parseInt(st.nextToken()); // x��ǥ
				arr[idx][1] = Integer.parseInt(st.nextToken()); // ����
				idx++;
				
			}
			
			System.out.println(f(0,1));

			System.out.println("#" + tc + " ");
		}

	} // end of main
} // end of class
