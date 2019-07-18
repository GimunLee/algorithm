package d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �鸸 ���� ������Ʈ
 */
public class Solution_1859 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int tc_n = 1; tc_n <= tc; tc_n++) {
			int N = Integer.parseInt(br.readLine().trim()); // 2 <= N <= 1,000,000
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			long benefit = 0; // ������ �������� ������ ����, long type ������ ��� (100���̹Ƿ�, int�� 21��)
			int max = 0; // �ŸŰ��� �ִ밪�� ������ ����
			// �ڿ������� Ž���ض�
			for (int i = N - 1; i >= 0; i--) { // 0 <= �ŸŰ� <= 10,000
				if (max < input[i]) {
					max = input[i]; // �������� �ʴ� ���� �̵�
				} else { // �ִ밪�� �ƴ� ���
					int num = max - input[i]; // ������ ����
					benefit += num; // ����
				}
			}
			System.out.println("#" + tc_n + " " + benefit);
		} // end of testCase
	} // end of main
} // end of class
