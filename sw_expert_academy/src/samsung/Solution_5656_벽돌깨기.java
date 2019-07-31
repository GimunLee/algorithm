package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_�������� {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int[][] map;
	private static int[][] map_tmp;
	private static int N;
	private static int W;
	private static int H;
	private static int[][] shooted;
	private static int[] maxHByWIndex;
	private static int[] maxHByWIndex_tmp;
	private static int[][] queue;
	private static int blockCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // ������ �� �� �ִ� Ƚ��
			W = Integer.parseInt(st.nextToken()); // Width (Column)
			H = Integer.parseInt(st.nextToken()); // Height (Row)

			map = new int[H][W];
			map_tmp = new int[H][W];

			maxHByWIndex = new int[W];
			maxHByWIndex_tmp = new int[W];
			queue = new int[5000][2];
			shooted = new int[H][W];

			set = new int[N];
			blockCnt = 0;

			ans = Integer.MAX_VALUE;
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] >= 1) {
						maxHByWIndex[c] += 1;
						blockCnt++;
					}
				}
			} // end of for(input)

			// N�� ���� ���� �ּ� ���� ��. ������ §�ٸ�? ��Ʈ��ŷ��?
			solve(0);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int[] set;

	// �ߺ� ����
	private static void solve(int len) {
		if (len == N) { // ������ �� ��ġ�� ��� ������ ��,

			for (int i = 0; i < H; i++) { // ���������� ����
				map_tmp[i] = Arrays.copyOf(map[i], W);
			}
			maxHByWIndex_tmp = Arrays.copyOf(maxHByWIndex, W);

			ans_tmp = blockCnt;
			dropBall(map_tmp, maxHByWIndex_tmp, set);

			ans = ans > ans_tmp ? ans_tmp : ans;

			return;
		}

		for (int i = 0; i < W; i++) {
			set[len] = i;
			solve(len + 1);
		}
	}

	private static int ans;
	private static int ans_tmp;

	private static void dropBall(int[][] map_tmp, int[] maxHByWIndex_tmp, int[] set) {
		// �ش� ��ġ�� ���� ���� �� ���� ������ ��������. �� �ִ� ���̸� �����س������� ó��

		for (int i = 0; i < set.length; i++) { // ���� Ƚ����ŭ �ݺ�
			int c = set[i]; // ����Ʈ���� ��ġ (Column)
			if(maxHByWIndex_tmp[c] <= 0 ) {
				continue;
			}
			
			int r = H - maxHByWIndex_tmp[c]; // �ش� ��ġ�� ���� ���� ��ġ

			int front = -1;
			int rear = -1;

			queue[++rear][0] = r; // ������
			queue[rear][1] = c;

			while (front != rear) {
				// queue.poll();
				int rr = queue[++front][0];
				int cc = queue[front][1];
				int bombRange = map_tmp[rr][cc] - 1; // ���� ����
				shooted[rr][cc] += 1; // ������ 1�̻��̹Ƿ� ù ������ ������ �÷���

				for (int bomb = 1; bomb <= bombRange; bomb++) {
					for (int di = 0; di < dr.length; di++) {
						int nR = rr + dr[di] * bomb;
						int nC = cc + dc[di] * bomb;

						if (nR < 0 || nC < 0 || nR >= H || nC >= W) { // ������ ����� ���
							continue;
						}

						if (map_tmp[nR][nC] >= 1 && shooted[nR][nC] == 0) {
							queue[++rear][0] = nR;
							queue[rear][1] = nC;
							shooted[nR][nC] += 1;
						}
					}
				}
			}

			// ��� �����ϱ� (�����ϸ鼭 shooted '0'���� �ٲ��ֱ�)
			for (int tc = 0; tc < W; tc++) {
				int[] stack = new int[H];
				int top = -1;

				for (int tr = (H - maxHByWIndex_tmp[tc]); tr < H; tr++) {
					if (shooted[tr][tc] != 0 && map_tmp[tr][tc] != 0) {
						shooted[tr][tc] = 0;
						ans_tmp--;
					} else {
						stack[++top] = map_tmp[tr][tc];
					}
					map_tmp[tr][tc] = 0;
				}

				int tr = H - 1;
				maxHByWIndex_tmp[tc] = top + 1;
				while (top != -1) { // ��� ä���
					map_tmp[tr--][tc] = stack[top--];
				}
			}
		}

	}

} // end of class
