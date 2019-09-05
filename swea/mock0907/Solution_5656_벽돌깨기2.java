package swea.mock0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_��������2 {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N, W, H;
	private static int[][] map;
	private static int[] remainBlockEachCnt;
	private static boolean[] isExplosion;
	private static int remainBlockTotalCnt;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			remainBlockEachCnt = new int[W];
			remainBlockTotalCnt = 0;
			isExplosion = new boolean[W];

			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] != 0) {
						remainBlockEachCnt[c]++;
						remainBlockTotalCnt++;
					}
				}
			} // end of for(input)

			ANSER = Integer.MAX_VALUE; // �� �׽�Ʈ���̽� �� ������ �����ϴ� ����
			solve(0);
			sb.append("#").append(tc).append(" ").append(ANSER).append("\n"); // �׽�Ʈ���̽� �� ���� ����
		}
		System.out.println(sb.toString()); // ���� ���
	} // end of main

	private static void solve(int cnt) {
		if (cnt == N || remainBlockTotalCnt == 0) {
			ANSER = remainBlockTotalCnt < ANSER ? remainBlockTotalCnt : ANSER;
			return;
		}

		for (int c = 0; c < W; c++) {
			if (remainBlockEachCnt[c] > 0) { // �ش� ���� ���� ���� ���� ��
				int[][] tmpMap = copyMap(map); // �ٲٱ� �� ���������� ����
				int[] remainBlockEachTempCnt = Arrays.copyOf(remainBlockEachCnt, W);
				boolean[] isExplosionTemp = Arrays.copyOf(isExplosion, W);
				int remainBlockTotalTempCnt = remainBlockTotalCnt; // �ٲٱ� �� ���������� ����
				int r = H - remainBlockEachCnt[c]; // ���� ���� ����� ����
				dropExplosion(r, c); // ��� ����
				dropBlock(); // ��� ������
				solve(cnt + 1);
				map = tmpMap;
				remainBlockTotalCnt = remainBlockTotalTempCnt;
				remainBlockEachCnt = remainBlockEachTempCnt;
				isExplosion = isExplosionTemp;
			}

		}
	} // end of func(solve)

	private static int ANSER;

	private static void dropExplosion(int r, int c) {
		if (map[r][c] == 0) {
			return;
		}
		int rangeOfExplosion = map[r][c];
		map[r][c] = 0;
		isExplosion[c] = true;
		remainBlockTotalCnt--;
		remainBlockEachCnt[c]--;
		for (int i = 1; i < rangeOfExplosion; i++) {
			for (int dir = 0; dir < dr.length; dir++) {
				int nR = r + dr[dir] * i;
				int nC = c + dc[dir] * i;

				if (nR < 0 || nC < 0 || nR >= H || nC >= W) {
					continue;
				}
				dropExplosion(nR, nC);
			}
		}
	}

	static void dropBlock() {
		for (int c = 0; c < W; c++) {
			if (isExplosion[c]) {
				int curR = H; // �̵��ؾ��� ��ġ
				for (int r = H - 1; r >= 0; r--) {
					if (map[r][c] != 0) {
						curR--;
						map[curR][c] = map[r][c];
						if (r != curR)
							map[r][c] = 0;
					}
				}
			}
		}
	} // end of func(dropBlock2)

	private static int[][] copyMap(int[][] origin) {
		int[][] tmpMap = new int[H][W];
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				tmpMap[r][c] = origin[r][c];
			}
		}
		return tmpMap;
	} // end of func(copyMap)
} // end of class
