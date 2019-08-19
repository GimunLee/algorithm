package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1. BFS 2. �÷��̵���� (�׷��� ���� �ִ� �Ÿ��� ������ķ� �����.) 2-1. ���� -> �޸������̼� -> DP
 *
 * �θ� �����Ѵ�.
 * 
 */

public class Main_2462_Ű����_Prof {
	private static int[][] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // �л��� ��, 2 <= N <= 500
		int M = Integer.parseInt(st.nextToken()); // �� Ƚ��, 2 <= M <= N*(N-1)/2

		a = new int[N + 1][N + 1]; // 1 ~ N ������ Ȱ��, ���� ���

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int small = Integer.parseInt(st.nextToken()); // ����
			int tall = Integer.parseInt(st.nextToken()); // ū
			a[small][tall] = 1;
		}

		// ���� ��Ŀ��� ������� ���� 0 ��ĭ�� �÷��� ������ Ȱ���ϰ���
		for (int i = 0; i < a.length; i++) {
			a[i][0] = -1; // �ʱ�ȭ
		}

		int totalN = 0; // ������ ��Ȯ�� �˰ԵǴ� �л� �ο� ��

//		System.out.println("��ȸ�ϱ� ��");
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}

		for (int i = 1; i < a.length; i++) {
			up(i); // dfs�� ��ȸ
		}
		// ������ Ű�� ���� ���̵�, ū ���̵��� ���� ���ؼ� N-1�̸� ������ ��Ȯ�� �� �� �ִ� �����
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a.length; j++) {
				a[0][i] += a[j][i]; // ���� i�� �����ϴ� �������� ������ ����
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (a[0][i] + a[i][0] == N - 1) {
				totalN++;
			}
		}

//		System.out.println("��ȸ�� ��");
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}

		System.out.println(totalN);
	} // end of main

	public static void up(int v) {
		if (a[v][0] != -1) { // ���� v�� ���ؼ� �θ� üũ�ص� ���¶��
			return;
		}

		// ���� v������ �θ� ���������� ������Ŀ� ����
		for (int i = 1; i < a.length; i++) {
			if (a[v][i] == 1) {
				up(i); // ��� ȣ��
				for (int j = 1; j < a.length; j++) {
					a[v][j] |= a[i][j];
				}
			}
		}

		// ��������� 0��° ĭ�� v������ �θ��� ���� ����� �����ص���
		int sum = 1;
		for (int j = 0; j < a.length; j++) {
			sum += a[v][j]; // ������ �θ�� 1, ������������ 0�̴ϱ�. �� ���ϸ�, �θ��� ������ ��
		}
		a[v][0] = sum;
	}
} // end of class
