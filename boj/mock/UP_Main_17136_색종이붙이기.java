package boj.mock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Main_17136_�����̺��̱� {
	final static int[] paper = { 0, 1, 2, 3, 4, 5 }; // ������ ũ�⸦ �����س��� ����
	static int[] paper_cnt = { 0, 5, 5, 5, 5, 5 }; // ������ ������ ������ �����س��� ����
	private static ArrayList<Pair> list; // map���� 1�� ��ġ(�����̸� ���� ��)�� ��� ���� list ����
	private static int[][] map; // input�� ������ 2���� �迭
	private static boolean[][] canWork; // �����̸� �ٿ����� ������, true�� ��� �����̸� ���� �� �ֽ��ϴ�.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		list = new ArrayList<>();
		canWork = new boolean[10][10];

		for (int r = 0; r < 10; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 10; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) { // input�� 1�� ���� list�� �����մϴ�.
					list.add(new Pair(r, c));
					canWork[r][c] = true; // �����̸� ���� �� �ִٰ� ǥ���մϴ�.
				}
			}
		} // end of for input

		min_cnt = Integer.MAX_VALUE; // �ּ��� ������ �����ϱ� ���� ������ �� ���� ū ���� �����մϴ�.

		dfs(0, 0); // �����̸� ���� �� �ִ� ��ġ(idx), ���� �����ϱ� ���� ���� ������ ����(cnt)

		// ���� �ѹ��� ���� ���� ���� ���� �����̸� ���� �� ���� ���� -1�� ����մϴ�.
		min_cnt = (min_cnt == Integer.MAX_VALUE) ? -1 : min_cnt;
		System.out.println(min_cnt);
	} // end of main

	/** �����̸� ���� ��ġ(idx)�� �������� ����(type)�� �޾�, �����̸� ���� �� �ִ��� Ȯ���մϴ�. */
	private static boolean chk(int idx, int type) {
		Pair p = list.get(idx); // �ش� idx�� row�� column ���� �����ɴϴ�.
		boolean flag = false; // �����̸� ���� �� �ִ���, ������ �Ǻ��� �� �ִ� �����Դϴ�.

		if (paper_cnt[type] == 0) { // ���� 5���� �����̸� �� ��ٸ�, �� �����̴� ���� ���մϴ�.
			return false;
		}

		// �ش� idx�� ��ġ���� �������� ũ�⸸ŭ Ȯ���մϴ�.
		here: for (int i = p.r; i < p.r + paper[type]; i++) {
			for (int j = p.c; j < p.c + paper[type]; j++) {
				if (i < 0 || j < 0 || i >= 10 || j >= 10) { // ������ ��� ���,
					flag = true;
					break here; // �� Ȯ������ �ʰ� ���� for���� �ѹ��� Ż���մϴ�.
				}

				if (!canWork[i][j]) { // �̹� �ٸ� �����̰� �پ��ִ� ���,
					flag = true;
					break here; // �� Ȯ������ �ʰ� ���� for���� �ѹ��� Ż���մϴ�.
				}
			}
		}
		// flag�� true��� �ش� idx�� type�� �����̸� ������ ���ϱ� ������ false�� return ���ݴϴ�.
		return flag ? false : true;
	} // end of chk()

	static int min_cnt; // �ϳ��� ������ �ϼ����� �� �������� �������� �ּ� ����

	/** �����̸� ���� ��ġ(idx)�� �ϳ��� ������ �ϼ����� ���, min_cnt�� �������ֱ� ���� cnt�� ������ �ٴմϴ�. */
	private static void dfs(int idx, int cnt) {
		if (min_cnt < cnt) { // Backtracking : �̹� ���� �ִ� �ּ��� ������ �Ѿ��� ������ ���̻� �������� �ʽ��ϴ�.
			return;
		}
		if (idx == list.size()) { // ���� ���� : map�� ��� 1�� Ž�� �Ϸ�
			min_cnt = (min_cnt > cnt) ? cnt : min_cnt; // �ּ� ���� ����
			return;
		}

		Pair p = list.get(idx);

		// �ش� idx�� �̹� �ٸ� �����̰� �پ��ִ� ���, ���� �����̸� ���� ���� Ž���ϵ��� �մϴ�.
		if (!canWork[p.r][p.c]) {
			dfs(idx + 1, cnt);
		}

		for (int type = 5; type >= 0; type--) { // ū �����̺��� �����ϸ� Backtracking ������ ����, �ð��� ���� �� �ֽ��ϴ�.
			boolean flag = chk(idx, type); // �����̸� ���� �� �ִ��� Ȯ���մϴ�.
			if (flag) { // �����̸� ���� �� �ִ� ���
				for (int r = p.r; r < p.r + paper[type]; r++) { // �����̸� �ٿ��ݴϴ�.
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = false;
					}
				}
				// �������� �������� 5�� �ۿ� �����Ƿ�, �������� ������ �ٿ��ݴϴ�.
				paper_cnt[type]--;

				dfs(idx + 1, cnt + 1); // �����̸� �ٿ����Ƿ�, ������ Ž���ϱ����� idx�� cnt�� 1�� ���ϰ� ���ȣ���մϴ�.

				// ���󺹱� : ���� ���տ��� ���� ���� ������ ���� �� �����Ƿ�, �����̸� �ٽ� ���ݴϴ�.
				paper_cnt[type]++;
				for (int r = p.r; r < p.r + paper[type]; r++) {
					for (int c = p.c; c < p.c + paper[type]; c++) {
						canWork[r][c] = true;
					}
				}
			}
		}
	} // end of dfs()

	private static class Pair {
		int r; // map�� row
		int c; // map�� column

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
