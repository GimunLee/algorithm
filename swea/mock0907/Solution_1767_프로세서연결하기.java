package swea.mock0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_���μ��������ϱ� {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N x N
			map = new int[N][N];
			ArrayList<Core> coreList = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1 && r != N && c != N && r != 0 && c != 0) { // Core�� ���
						coreList.add(new Core(r, c));
					}
				}
			} // end of for(input)

			accessCoreCntANS = Integer.MIN_VALUE;
			accessCoreLenANS = Integer.MAX_VALUE;
			chooseCore(coreList, 0, 0, 0);
			sb.append("#").append(tc).append(" ").append(accessCoreLenANS).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int accessCoreCntANS;
	private static int accessCoreLenANS;

	/** ������ ��ġ�� Core�� ��� Core�� ��ġ�غ���. */
	private static void chooseCore(ArrayList<Core> coreList, int idx, int accessCoreCnt, int accessCoreLen) {

		if (idx == coreList.size()) { // �� Ž���غ�����
			if (accessCoreCntANS < accessCoreCnt) {
				accessCoreCntANS = accessCoreCnt; // �ھ� ����
				accessCoreLenANS = accessCoreLen; // ���� ����
			} else if (accessCoreCntANS == accessCoreCnt) {
				accessCoreLenANS = accessCoreLenANS > accessCoreLen ? accessCoreLen : accessCoreLenANS; // ���� ����
			}
			return;
		}

		// �ھ� ������ �� �ִ��� Ȯ��, ������ �� ������ �����ϱ�
		Core core = coreList.get(idx);
		for (int dir = 0; dir < dr.length; dir++) {
			int result = setCable(core, dir);
			if (result != -1) {
				chooseCore(coreList, idx + 1, accessCoreCnt + 1, accessCoreLen + result);
				int r = core.r;
				int c = core.c;
				for (int i = 0; i < result; i++) {
					int nR = r + dr[dir];
					int nC = c + dc[dir];
					map[nR][nC] = 0;
					r = nR;
					c = nC;
				}
			}
		}
		chooseCore(coreList, idx + 1, accessCoreCnt, accessCoreLen);
	}

	// ���� ��ġ�ϱ�
	private static int setCable(Core core, int dir) {
		boolean canSetCable = false;
		int coreLen = 0;
		int r = core.r;
		int c = core.c;
		while (true) {
			int nR = r + dr[dir];
			int nC = c + dc[dir];

			if (nR < 0 || nC < 0 || nR >= map.length || nC >= map.length) { // ��ġ ����
				canSetCable = true;
				break;
			}

			if (map[nR][nC] != 0) {
				break;
			}
			r = nR;
			c = nC;
		}
		r = core.r;
		c = core.c;
		if (canSetCable) { // �ش� ������ ���� ��ġ ����
			while (true) {
				int nR = r + dr[dir];
				int nC = c + dc[dir];
				if (nR < 0 || nC < 0 || nR >= map.length || nC >= map.length) { // ��ġ ����
					break;
				}
				coreLen++;
				map[nR][nC] = 2;
				r = nR;
				c = nC;
			}
			return coreLen;
		} else {
			return -1;
		}
	}

	private static class Core {
		int r, c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
} // end of Class
