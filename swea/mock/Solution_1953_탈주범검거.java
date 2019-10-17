package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_Ż�ֹ��˰� {
	private static final int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 }; // �����¿�
	private static int[][] dirByType = { { 0, 0, 0, 0 }, // ��ĭ
			{ 1, 2, 3, 4 }, // 1
			{ 1, 2, 0, 0 }, // 2 ���� ����
			{ 3, 4, 0, 0 }, // 3 �¿� ����
			{ 1, 4, 0, 0 }, // 4 ��� ����
			{ 2, 4, 0, 0 }, // 5 �Ͽ� ����
			{ 2, 3, 0, 0 }, // 6 ���� ����
			{ 1, 3, 0, 0 } // 7 ���� ����
	};

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Queue<Pair> queue = new LinkedList<>();
			int N = Integer.parseInt(st.nextToken()); // ���� ũ��
			int M = Integer.parseInt(st.nextToken()); // ���� ũ��
			int R = Integer.parseInt(st.nextToken()); // ��Ȧ �Ѳ� r
			int C = Integer.parseInt(st.nextToken()); // ��Ȧ �Ѱ� c
			int L = Integer.parseInt(st.nextToken()); // �ҿ�� �ð�
			int[][] map = new int[N][M];
			queue.add(new Pair(R, C));

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 1;
			int time = 1;
			boolean[][] visited = new boolean[N][M];

			while (!queue.isEmpty()) {
				if (time++ == L) {
					break;
				}
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					Pair p = queue.poll();
					int curType = map[p.r][p.c]; // ���� �ִ� ���� �ϼ��� type
					visited[p.r][p.c] = true;

					for (int d = 0; d < dirByType[curType].length; d++) {
						int dir = dirByType[curType][d];
						if (dir == 0) { // �� �� ���� ��찡 ������
							break;
						}
						int nR = p.r + dr[dir];
						int nC = p.c + dc[dir];

						if (nR < 0 || nC < 0 || nR >= N || nC >= M) { // ������ ��� ��,
							continue;
						}
						if (visited[nR][nC]) {
							continue;
						}
						// �� �� �ִ��� Ȯ��
						if (canGo(map[nR][nC], dir)) {
							answer++;
							queue.add(new Pair(nR, nC));
							visited[nR][nC] = true;
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		} // end of for(testCase)
		System.out.println(sb.toString());
	} // end of main

	/** ���� ĭ�� ��ġ�� �ϼ����� �ش� dir�� ���� �� �� �ִ��� üũ */
	private static boolean canGo(int type, int dir) {
		boolean flag = false;
		switch (type) {
		case 1: // ��� ����
			flag = true;
			break;
		case 2: // ���� ����
			if (dir == 1 || dir == 2)
				flag = true;
			break;
		case 3: // �¿� ����
			if (dir == 3 || dir == 4)
				flag = true;
			break;
		case 4: // ���� ����
			if (dir == 2 || dir == 3)
				flag = true;
			break;
		case 5: // ���� ����
			if (dir == 1 || dir == 3)
				flag = true;
			break;
		case 6: // ��� ����
			if (dir == 1 || dir == 4)
				flag = true;
			break;
		case 7: // �Ͽ� ����
			if (dir == 2 || dir == 4)
				flag = true;
			break;
		}
		return flag;
	}

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
} // end of class
