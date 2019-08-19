package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Solution_2117_Ȩ������� {
	private static int[] dr = { -1, 1, 0, 0 }; private static int[] dc = { 0, 0, -1, 1 };
	private static int N, M; // N : ������ ũ��, M : �ϳ��� ���� ������ �� �ִ� ���
	private static int homeTotalCnt; // ���� �� ����
	private static int ans;
	private static int[][] queue; // Ȩ ����� �����ϴ� ��ġ

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // ������ �ѹ��� ����ϱ� ���� ����
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // ������ ũ�� (5 <= N <= 20)
			M = Integer.parseInt(st.nextToken()); // �ϳ��� ���� ������ �� �ִ� ��� (1 <= M <= 10)

			int[][] map = new int[N][N]; // �� ���� 
			homeTotalCnt = 0; // ���� �� ������ ������ ����
			ans = Integer.MIN_VALUE; // ���� ���� (���غ��� �ʰ� ���� ������ ���� ��)
			queue = new int[500][2]; // Ȩ ����� �����ϴ� ��ġ ���� ����

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); 
					if (map[r][c] == 1) { // ���� ���,
						homeTotalCnt++; // ���� �� ������ ����������
					}
				}
			} // end of for(input)

			// �� ��ġ���� Ȩ ����� �����غ�
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					 // ������ ������� üũ�غ�
					solve(map, new boolean[N][N], r, c);
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
	
	/**
	 * ����ȸ���� � ��� ���
	 * */
	private static int getCostByK(int K) {
		return K * K + (K - 1) * (K - 1);
	} // end of func(getCostByK)

	/**
	 * ����ȸ���� ������ ���
	 * */
	private static int getBenefit(int homeCnt, int k) {
		return (homeCnt * M) - k;
	} // end of func(getBenefit)

	/** 
	 * ������ ������� Ȩ ����� ������, ���� ������ ���� 
	 * */
	private static void solve(int[][] map, boolean[][] visited, int r, int c) {
		int front = -1;
		int rear = -1;

		queue[++rear][0] = r;
		queue[rear][1] = c;

		int homeCnt = 0; // �ش� r,c ���� Ȩ ��� ���񽺸� �� ��, ���� �ȿ� �ִ� ���� ����
		int tmpK = 1; // ����ȸ���� � ����
		
		while (front != rear) {
			int size = rear; // ó�� � �������� ���񽺸� ���� ��,
			while (front != size) {
				int rr = queue[++front][0];
				int cc = queue[front][1];
				visited[rr][cc] = true;

				if (map[rr][cc] == 1) { // ���� ���
					homeCnt++;
				}

				for (int j = 0; j < dr.length; j++) {
					int nR = rr + dr[j];
					int nC = cc + dc[j];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // ������ ��� ���
						continue;
					}
					
					if (visited[nR][nC]) { // �̹� �湮�� ���
						continue;
					}

					visited[nR][nC] = true;
					queue[++rear][0] = nR;
					queue[rear][1] = nC;
				}
			} // end of while(one K)
			int ansTemp = getBenefit(homeCnt, getCostByK(tmpK));
			if (ansTemp >= 0) { // ���ظ� ���� �ʴ� ����̹Ƿ�, '0'�� ���Խ��������
				ans = ans < homeCnt ? homeCnt : ans; // ���� ����
			}
			 // ���� ������ ������ �� ��� �Ǵ� ��� ���� ���� ���ٶ�
			if ((tmpK > 2 * N - 1) || (homeCnt == homeTotalCnt)) { 
				return;
			}
			tmpK++; // � ������ �÷���
		} // end of while(Queue)
	} // end of func(solve)
} // end of class
