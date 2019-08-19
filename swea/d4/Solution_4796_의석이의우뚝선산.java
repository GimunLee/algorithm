package swea.d4;

import java.util.Scanner;

public class Solution_4796_�Ǽ����ǿ�Ҽ��� {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			int N = sc.nextInt();
			int[] m = new int[N];
			
			for (int i = 0; i < N; i++) {
				m[i] = sc.nextInt();
			}
			
			int ans = 0;
			
			for (int i = 0; i < N; i++) {
				int j = i + 1;
				
				if (j + 1 < m.length && m[i] < m[j]) { // ���� �� �ִ� ���,
					int tmp = 1; // �ش� ���츮���� ���� ���츮�� ����
					while (j + 1 < m.length && m[j] < m[j + 1]) { // ū ���츮�� ���ö����� ������ �б�
						tmp++;
						j++;
					}
					while (j + 1 < m.length && m[j] > m[j + 1]) { // ���� ���츮���� ���� ���츮 ������ ���� ������ �б�
						ans += tmp;
						j++;
					}
					i = j - 1; // �� ���� �Ѿ��
				}
			}
			System.out.println("#" + tc + " " + ans);
		} // end of for TestCase
	} // end of main
} // end of class
