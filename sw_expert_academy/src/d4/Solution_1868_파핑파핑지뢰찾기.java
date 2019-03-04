package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1868_������������ã�� {
	public static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 }; // ��
	public static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 }; // ��

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 1 <= N <= 300
			char[][] m = new char[N + 2][N + 2]; // �ܰ� ���پ� ������� �������ε�
			for (int i = 1; i <= N; i++) {
				String s = br.readLine();
				for (int j = 1; j <= N; j++) {
					m[i][j] = s.charAt(j - 1);
				}
			}

			int[][] cnt = new int[N + 2][N + 2]; // ��ź�� ����, �ܰ� ���پ� ������� �������ε�
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (m[i][j] == '*') { // ��ź �ڸ��̸�, ī���� �����ʰ�, ǥ���صα�
						cnt[i][j] = -9; // ��ź���� ����, ������� �ʴ� ���ڷ� ǥ��
						continue; // ��ź �ڸ��� �ֺ��� ī���� ���� �ʴ´�
					}

					// ������ 8ĭ�� ��ź�� � ������� ī����
					for (int k = 0; k < dy.length; k++) {
						if (m[i + dy[k]][j + dx[k]] == '*') {
							cnt[i][j]++;
						}
					}
				}
			}

			boolean[][] visited = new boolean[N + 2][N + 2]; // �ܰ� ������ ������� ����
			// �ܰ��� ĭ���� �湮 �� ������ ó������
			for (int i = 0; i < visited.length; i++) {
				visited[0][i] = true;
				visited[N + 1][i] = true;
				visited[i][0] = true;
				visited[i][N + 1] = true;
			}

			int result = 0; // ��� Ŭ���ؾ� ��� ���� �ִ��� ī������ ����
			// cnt[][] ���� 0�� ĭ�� ã�Ƽ� BFS �� Ž��
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i][j] == false && cnt[i][j] == 0) { // �湮���� ���� ĭ�̰�, 0�̸�
						bfs(visited, cnt, i, j); // ������ 0 ������ �湮, ���⿡ ������ 0�� �ƴ� ����ĭ 1���� �湮
						result++;
					}
				}
			}
			// 0�� ���������� �ʾ�����, ������ ĭ�� üũ�ϱ�
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i][j] == false && cnt[i][j] > 0) {
						result++;
					}
				}
			}

			System.out.println("#" + testCase + " " + result);
		} // end of for testCase
	} // end of main

	public static int[][] q = new int[900000][2]; // ť�� �ۿ� �ѹ��� ����

	public static void bfs(boolean[][] visited, int[][] cnt, int r, int c) {
		int front = -1; // ť �ʱ�ȭ
		int rear = -1;
		// ���� ������ ������ ť�� �ְ� BFS����
		q[++rear][0] = r;
		q[rear][1] = c;
		visited[r][c] = true;
		while (front != rear) { // ť�� �����Ͱ� ������ �ݺ�
			r = q[++front][0]; // ť���� 1�� ������
			c = q[front][1];
			// �湮�۾�
			for (int i = 0; i < dy.length; i++) { // �湮�����ʾҰ�, ������ ĭ�� �����̸�
				if (visited[r + dy[i]][c + dx[i]] == false && cnt[r + dy[i]][c + dx[i]] > 0) {
					visited[r + dy[i]][c + dx[i]] = true;
				}
			}

			// �湮���� �ʾҰ�, ������ ĭ�̸� ť�� �ֱ�
			for (int i = 0; i < dy.length; i++) {
				if (visited[r + dy[i]][c + dx[i]] == false) {
					q[++rear][0] = r + dy[i];
					q[rear][1] = c + dx[i];
					visited[r + dy[i]][c + dx[i]] = true; // �湮�� ������ ǥ��
				}
			}

		}
	} // end of bfs()
} // end of class
