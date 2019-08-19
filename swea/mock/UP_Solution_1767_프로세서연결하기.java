package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Solution_1767_���μ��������ϱ� {
	private static int[][] map; // �Է��� ������ 2���� ���� �迭
	private static ArrayList<Core> coreList; // �ھ ������ ArrayList ����
	private static int[] dr = { -1, 1, 0, 0 }; // row : �� �� �� ��
	private static int[] dc = { 0, 0, -1, 1 }; // column : �� �� �� ��

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��
		StringBuilder sb = new StringBuilder(); // ��� ��, �ð��� ���̱� ���� StringBuilder ����

		for (int tc = 1; tc <= TC; tc++) {
			maxCoreCnt = Integer.MIN_VALUE; // �ִ�� ����Ǵ� �ھ��� ���� ����
			minCoreLen = Integer.MAX_VALUE; // �ּҷ� ����Ǵ� ������ ���� ����

			int N = Integer.parseInt(br.readLine().trim()); // map�� ũ��
			map = new int[N][N];
			coreList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					// �ܰ��� �̹� ����Ǿ� �ֱ� ������, Ž������ �����մϴ�.
					if (map[r][c] == 1 && r != 0 && c != 0 && r != N - 1 && c != N - 1) {
						coreList.add(new Core(r, c)); // Ž���� �ھ �����մϴ�.
					}
				}
			} // end of for input

			dfs(0, 0, 0); // (�ھ��� �ε���:idx, ����� �ھ��� ����:coreCnt, ������ ����:len)

			sb.append('#').append(tc).append(' ').append(minCoreLen).append('\n'); // �ѹ��� ����ϱ� ���� ó���Դϴ�.
		} // end of for of testCase
		System.out.println(sb.toString()); // ���� ���

	} // end of main

	static int maxCoreCnt = Integer.MIN_VALUE; // �ִ�� ����Ǵ� �ھ��� ���� ����
	static int minCoreLen = Integer.MAX_VALUE; // �ּҷ� ����Ǵ� ������ ���� ����

	/**
	 * core�� dir�� �޾� ������ ��ġ�غ��ϴ�. ���� ��ġ�� �����ϴ� ���, -1�� ��ȯ�մϴ�.
	 * 
	 * @param core : ���� Ž���ϴ� �ھ�
	 * @param dir : ���� ����
	 * */
	private static int setLine(Core core, int dir) {
		int len = 0; // ������ ����
		int r = core.r; // ���� �ھ��� ��ġ r
		int c = core.c; // ���� �ھ��� ��ġ c

		while (true) {
			int nR = r + dr[dir]; // ������ ��ġ�� ��ġ r
			int nC = c + dc[dir]; // ������ ��ġ�� ��ġ c

			// ������ ����� �ݺ����� �ߴ��մϴ�.
			if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map.length - 1) {
				break;
			}

			// �̹� ��ġ�� ������ �ִ� ���, �ش� �ھ�� �ش� �������� ��ġ���� ���ϹǷ� -1�� ��ȯ���ݴϴ�.
			if (map[nR][nC] != 0) {
				return -1;
			}

			len++; // ���ǿ� �����ϹǷ�, ������ ���̸� �ø��ϴ�.
			r = nR; // ���� ������ ��ġ�� ���� ��ġ�� �̵��մϴ�.
			c = nC; // ���� ������ ��ġ�� ���� ��ġ�� �̵��մϴ�.
		}

		// ������ ��ġ�� �� �ִ� ����̹Ƿ�, ���� �ھ��� ��ġ�� �ٽ� �������ݴϴ�.
		r = core.r; 
		c = core.c;

		// ������ ������ ��ġ�մϴ�.
		for (int i = 0; i < len; i++) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];
			map[nR][nC] = 1;
			r = nR;
			c = nC;
		}
		return len; // ������ �ּ� ���̸� ����������ϹǷ�, ������ ���̸� ��ȯ���ݴϴ�.
	} // end of setLine()
	
	/**
	 * �ϳ��� �ھ �����¿�� �����غ���, idx�� �÷� ���� �ھ �����غ��ϴ�.
	 * �ϳ��� ������ �ϼ��Ǵ� ���, coreCnt�� len�� �̿��� ������ �������ݴϴ�.
	 * 
	 * @param idx     : �ھ��� �ε���
	 * @param coreCnt : �ھ��� ���� ����
	 * @param len     : �ھ �����ϴ� ������ ����
	 */
	private static void dfs(int idx, int coreCnt, int len) {

		if (idx == coreList.size()) { // ���� ���� : ��� �ھ Ž���� ���,
			if (coreCnt > maxCoreCnt) { // ���� ���� �ھ�� ���� ���� �ھ �� ���� ���, ������ �������ݴϴ�.  
				maxCoreCnt = coreCnt; 
				minCoreLen = len;
			}
			else if (coreCnt == maxCoreCnt) { // ���� ���� �ھ��� ������ ���� ���� �ھ� ������ ���ٸ�,
				minCoreLen = Math.min(minCoreLen, len); // ������ ���̸� �������ݴϴ�.
			}
			return;
		}

		Core core = coreList.get(idx); // ���� Ž���� Core�� �����ɴϴ�.
		
		// 4����(�����¿�)�� Ž���մϴ�.
		for (int i = 0; i < dr.length; i++) {
			int ret = setLine(core, i); // �ش� �ھ ������ ��ġ�� �� �ִ��� Ȯ���մϴ�.

			if (ret == -1) { // ���� ��ġ�� �����ϴ� ���,
				dfs(idx + 1, coreCnt, len); // ���� �ھ Ž���մϴ�.

			} else { // ���� ��ġ�� �����ϴ� ���,
				// ���� �ھ Ž���ϰ�, �ھ� ��ġ�� ���������Ƿ� �ھ��� ������ �÷��ְ�, ������ ���̸� �������ݴϴ�.
				dfs(idx + 1, coreCnt + 1, len + ret);

				// ���� ���� : �ش� �ھ��� ��ġ�� �����߾, �� ��찡 �ּ��� �ƴ� �� �����Ƿ� �ٽ� ���ͽ����ݴϴ�.
				int nR = core.r;
				int nC = core.c;

				while (true) {
					nR += dr[i];
					nC += dc[i];

					if (nR < 0 || nC < 0 || nR > map.length - 1 || nC > map.length - 1) {
						break;
					}

					map[nR][nC] = 0;
				}
			}
		}

	} // end of dfs()
	private static class Core {
		int r;
		int c;

		Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Core
} // end of class

