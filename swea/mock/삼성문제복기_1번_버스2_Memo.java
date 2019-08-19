package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ������ ��ֹ��� ���� �����Ͽ� ���������� ������ �� �ִ� ����� ���� ���϶�. ������ NxN ũ���̸�, ������ (0,0)���� ����Ͽ�
 * (N-1, N-1)�� �����ؾ��Ѵ�. ������ ������ �� �ִ� ������ ������ ���� ��������. �������κ��� �������� ������ ����. ���� �����ϱ�
 * ���ؼ��� �Ʒ�ó�� ������ �����ؾ��ϴ� �濡 ��ֹ��� ������Ѵ�. ������ �����Ҷ��� ���������� ������ �ٲ���Ѵ�. (�ٷ� �� �ٲ۴�)
 * ���������� ��߰� ������������ ������ ���� Ȥ�� ���ι����̾���Ѵ�.
 *
 * �ð����⵵ : �����ϴ°��(3) * �����ϴ°��(3).... 30�� / 3^30���� (���� �� ������)
 * 
 * DFS (����) - �ݺ��������ϸ� ��Ʈ��ŷ�� ��ƴ�. BFS (ť) - ��Ʈ���� �� ������ �־����. ���� vs ť - ť�� �޸𸮿�
 * ����ϴ�.
 * -------------------------------------------------------------------------------------
 * �ùķ��̼� DFS (�ݺ� : �ӵ��� �������� ��Ʈ��ŷ�ϱ� ��ƴ�, ��� : �ӵ��� �������� ��Ʈ��ŷ�ϱ� ����)
 */

public class �Ｚ��������_1��_����2_Memo {
	private static int cnt;
	private static int[][] m;
	private static int N;
	private static int[][][] memo; // ����� ��� ������ ������ ����, �޸������̼�

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			m = new int[N + 1][N + 1]; // ����ð� �̵溸������

			for (int i = 0; i < m.length; i++) {
				m[i][N] = 1;
				m[N][i] = 1;
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " "); // �ɰ����� trim() ���ص���
				for (int j = 0; j < N; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			memo = new int[N][N][3]; // ���� ��� ��尡 ���ϰ���� ����� ���� ����
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < 3; k++) {
						memo[i][j][k] = -1; // ��� ���Ѱ��� ǥ���ϱ� ���ؼ� -1�� �ʱ�ȭ
					}
				}
			}

			cnt = 0; // ������������ �� �� �ִ� ����� ��
			if (m[0][0] == 0 && m[0][1] == 0) { // ���η� ������ ���� �� �ִ� ���
				cnt += dfs(0, 0, 0);
			}
			if (m[0][0] == 0 && m[1][0] == 0) { // ���η� ������ ���� �� �ִ� ���
				cnt += dfs(0, 0, 2);
			}
			System.out.println("#" + tc + " " + cnt);
		} // end of for TestCase
	} // end of main

	/**
	 * r�� c�� mode : ����, 0:����, 1:�밢, 2:���� (r,c) ������ ���� ��ǥ
	 * r,c��ġ�� ������ mode ���¿��� ������������ �� �� �ִ� ����� ���� ����
	 */
	public static int dfs(int r, int c, int mode) {
		// �̹� ����� ���°��̶��, ���ȣ�������ʰ� ������ �� ���� ����
		if (memo[r][c][mode] != -1) {
			return memo[r][c][mode];
		}

		if (mode == 0) {// ����
			if (r == N - 1 && c + 1 == N - 1) {// ������ġ���� üũ
				return 1;
			} else {// ������ �ƴϸ� ��ĭ ���� : ���� / �밢
				if (m[r][c + 2] == 0) {
					cnt += dfs(r, c + 1, 0); // ����
				}

				if (m[r][c + 2] == 0 && m[r + 1][c + 2] == 0 && m[r + 1][c + 1] == 0) {
					cnt += dfs(r, c + 1, 1); // �밢
				}
			}
		} else if (mode == 1) {// �밢
			// ������ �ƴϹǷ� ��ĭ ���� : ����/ �밢/ ����
			if (m[r + 1][c + 2] == 0) {
				cnt += dfs(r + 1, c + 1, 0); // ����
			}
			if (m[r + 1][c + 2] == 0 && m[r + 2][c + 1] == 0 && m[r + 2][c + 2] == 0) {
				cnt += dfs(r + 1, c + 1, 1); // �밢
			}
			if (m[r + 2][c + 1] == 0) {
				cnt += dfs(r + 1, c + 1, 2); // ����
			}
		} else {// ����
			if (r + 1 == N - 1 && c == N - 1) {// ������ġ���� üũ
				return 1;
			} else {// ������ �ƴϸ� ��ĭ ���� : �밢/����
				if (m[r + 2][c] == 0) {
					cnt += dfs(r + 1, c, 2); // ����
				}

				if (m[r + 1][c + 1] == 0 && m[r + 2][c] == 0 && m[r + 2][c + 1] == 0) {
					cnt += dfs(r + 1, c, 1); // �밢
				}
			}
		}
		memo[r][c][mode] = cnt;
		return cnt;
	}
} // end of class
