package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17136_�����̺��̱� {
	final static int[] paper = { 0, 1, 2, 3, 4, 5 }; // ������ ũ��
	static int[] paper_cnt = { 0, 5, 5, 5, 5, 5 }; // ������ ����
	private static ArrayList<Pair> list;
	private static int[][] map;
	private static boolean[][] canWork;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		list = new ArrayList<>();
		canWork = new boolean[10][10];

		for (int r = 0; r < 10; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 10; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					list.add(new Pair(r, c));
					canWork[r][c] = true;
				}
			}
		} // end of for input

		min_cnt = Integer.MAX_VALUE;

		dfs(0, 0); // �����̸� ���� �� �ִ� idx, �ּ� ���� cnt

		min_cnt = (min_cnt == Integer.MAX_VALUE) ? -1 : min_cnt;
		System.out.println(min_cnt);
	}

	static int min_cnt;

	private static void dfs(int idx, int cnt) {
		if (min_cnt < cnt) { // ���� �� �� ����
			return;
		}

		if (idx == list.size()) { // ���� ����
			min_cnt = (min_cnt > cnt) ? cnt : min_cnt; // ����
			return;
		}

		Pair p = list.get(idx);

		if (!canWork[p.r][p.c]) {
			dfs(idx + 1, cnt);
		}

		for (int type = 5; type >= 0; type--) { // ū �����̺���
			boolean flag = chk(idx, type); // ���ϼ� �ִ��� Ȯ��

			if (flag) { // �����ϴ� ���

				for (int r = p.r; r < p.r + paper[type]; r++) {
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = false;
					}
				}
				paper_cnt[type]--;
				dfs(idx + 1, cnt + 1);
				paper_cnt[type]++;
				for (int r = p.r; r < p.r + paper[type]; r++) {
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = true;
					}
				}
			}
		}
	} // end of dfs()

	private static boolean chk(int idx, int type) {
		Pair p = list.get(idx);
		boolean flag = false;

		if (paper_cnt[type] == 0) {
			return false;
		}

		here: for (int i = p.r; i < p.r + paper[type]; i++) {
			for (int j = p.c; j < p.c + paper[type]; j++) {
				if (i < 0 || j < 0 || i >= 10 || j >= 10) {
					flag = true;
					break here;
				}

				if (!canWork[i][j]) {
					flag = true;
					break here;
				}
			}
		}
		return flag ? false : true;
	} // end of chk()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
