package swea.mock0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1767_��������_other {
	static int N, W, H, map[][];
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int ans, remains; // ���� ���� ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			ans = Integer.MAX_VALUE;
			remains = 0;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0)
						remains++;
				}
			}
			dfs(1);
			System.out.println("#" + t + " " + ans);
		}
	}

	static void dfs(int cnt) {
		if (cnt > N || remains == 0) {
			if (remains < ans)
				ans = remains;
			return;
		}
		for (int j = 0; j < W; j++) {
			// ���纻
			int[][] temp_map = new int[H][W];
			int temp_remains = remains;
			for (int x = 0; x < H; x++) {
				for (int y = 0; y < W; y++) {
					temp_map[x][y] = map[x][y];
				}
			}
			if (shoot(j)) { // ���ŵ� ĭ�� ������
				dfs(cnt + 1);
				map = temp_map; // ��Ʈ��ŷ
				remains = temp_remains;
			}
		}
	}

	static boolean shoot(int j) {
		boolean remove = false;
		for (int i = 0; i < H; i++) {
			if (map[i][j] != 0) {
				bomb(i, j); // ���� ĭ ����
				down(); // �Ʒ��� ������
				remove = true;
				break;
			}
		}
		return remove;
	}

	static void bomb(int i, int j) {
		if (map[i][j] == 0)
			return;
		int v = map[i][j] - 1; // �ֺ� vĭ���� �����ؾ� ��
		map[i][j] = 0; // �ش� ĭ ����
		remains--;
		for (int k = 1; k <= v; k++) {
			for (int d = 0; d < 4; d++) {
				int px = i + dx[d] * k;
				int py = j + dy[d] * k;
				if (px < 0 || px >= H || py < 0 || py >= W)
					continue; // ���� �ʰ�
				bomb(px, py);
			}
		}
	}

	static void down() {
		for (int j = 0; j < W; j++) {
			int ti = H; // �̵��ؾ��� ��ġ
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][j] != 0) {
					ti--;
					map[ti][j] = map[i][j];
					if (i != ti)
						map[i][j] = 0;
				}
			}
		}
	}
}