package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Solution_5656_�������� {
	private static int[] dr = { -1, 1, 0, 0 }; private static int[] dc = { 0, 0, -1, 1 };
	private static int[][] map; // ���� �� �����͸� �����ϴ� ����
	private static int N,W,H; // N : ������ ��� Ƚ��, W : ���� ���� ���� ����, H : ���� ���� ���� ����
	private static int[][] shooted; // ������ ���� ������ �ϴ��� üũ�ϴ� ����
	private static int[] maxHByWIndex; // �ش� ���� �ִ� ����� ���� �����ϴ� ����
	private static int[][] queue; // ������ �ѹ� �������� ��, ������ �޴� ����� �ѹ��� ó���ϱ� ���� ť
	private static int[] set; // ������ ����߸� ��ġ�� �ߺ� ������ �����ϴ� ����
	private static int blockCnt, ans, ans_tmp; // blockCnt : ����� �� ����, ans : ���� ����, ans_tmp : ���� �ӽ� ���� 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // ������ �����ߴٰ� ����ϴ� ����
		queue = new int[500][2]; // ť�� �迭�� �� ��, �ѹ��� �����ϰ� ����� �� ����
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // ������ �� �� �ִ� Ƚ��
			W = Integer.parseInt(st.nextToken()); // ���� ���� ���� ���� / Width (Column)
			H = Integer.parseInt(st.nextToken()); // ���� ���� ���� ���� / Height (Row)

			map = new int[H][W]; // ����� ���¸� �����ϴ� �� ����
			maxHByWIndex = new int[W]; // ���� �� ���� �����ϴ� ����� ������ �����ϴ� ����
			blockCnt = 0; // ����� �� ������ �����ϴ� ����

			ans = Integer.MAX_VALUE; // �� �׽�Ʈ���̽� �� ������ �����ϴ� ����

			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] >= 1) { // ����� ��������,
						maxHByWIndex[c] += 1; // �ش� ���� ��� ������ �÷���
						blockCnt++; // ����� �� ������ �÷���
					}
				}
			} // end of for(input)
			
			shooted = new int[H][W]; // �ش� ����� �¾Ҵ��� �� �¾Ҵ���, �����ϴ� ����
			set = new int[N]; // ������ ����߸� ���� �� ���� (�ߺ�����)

			solve(0); // ������ N�� �� ��, ����߸� ���� ��ġ�� �ߺ������� ����
			sb.append("#").append(tc).append(" ").append(ans).append("\n"); // �׽�Ʈ���̽� �� ���� ����
		}
		System.out.println(sb.toString()); // ���� ���
	} // end of main()

	/**
	 * ������ ����߸� ��ġ�� �����ϰ�, ��� �̾��� �� ������ ���ʴ�� ����߷���
	 * �ش� ���� ����� ���� ��쿡�� ����߸��� ����
	 * ����ȭ �ؾ��ϴ� ��� : ����� �ϳ� ����߸���, �ش� ���� ����� ���� ��찡 ���� �� ����
	 */
	private static void solve(int len) {
		if (len == N) { // ������ �� ��ġ�� ��� ������ ��,
			// ���� �����͸� �ӽ� ������ ����
			int[][] map_tmp = new int[H][W]; // ���� ���� �����س���, ����� ��Ʈ�� ����
			for (int i = 0; i < H; i++) {
				map_tmp[i] = Arrays.copyOf(map[i], W);
			}
			int[] maxHByWIndex_tmp = new int[W]; // ����� ��Ʈ�������� ���ŵǴ� �ӽ� ����
			maxHByWIndex_tmp = Arrays.copyOf(maxHByWIndex, W);
			// --

			ans_tmp = blockCnt;
			drop(map_tmp, maxHByWIndex_tmp, set); // ���� ������ ����߸� ��ġ�� ������ ����߷���
			ans = ans > ans_tmp ? ans_tmp : ans; // ���� ���� ����
			return;
		}

		for (int i = 0; i < W; i++) {
			if(maxHByWIndex[i] != 0) { // �ش� ���� ����� ������ ����߸��� ����
				set[len] = i; // �ߺ� ���� ����
				solve(len + 1); // �ߺ� ���� ��� ȣ��
			}
		}
	} // end of func(solve)

	/**
	 * ������ ����߷����� �Լ�
	 */
	private static void drop(int[][] map_tmp, int[] maxHByWIndex_tmp, int[] set) {
		// �ش� ��ġ�� ���� ���� �� ���� ������ �������� ���� Ž������ �ʰ�, �ִ� ���̸� �����س��� ó��
		for (int i = 0; i < set.length; i++) { // ���� Ƚ����ŭ �ݺ�
			boolean[] isBomb = new boolean[W]; // �ش� ���� ������ ������ üũ�� ���� (down �Լ����� ���)
			
			int c = set[i]; // ����߸��� ��ġ (Column)
			if (maxHByWIndex_tmp[c] <= 0) { // �ش� ���� ���� ����� ���ٸ� ����߸��� ���� (����ȭ �ؾ���)
				continue;
			}

			// �ش� ��ġ�� ���� ���� ��ġ (�迭������ �����̹Ƿ� �ִ� ���̿��� ���� ����� ���ָ� ������ �ε����� ����)
			int r = H - maxHByWIndex_tmp[c];

			int front = -1; // queue�� poll�� �� ��, �ε��� ����
			int rear = -1; // queue�� add�� �� ��, �ε��� ����

			queue[++rear][0] = r; // ������ ó�� ������ ����� ���� ��ġ�� add
			queue[rear][1] = c; // ������ ó�� ������ ����� ���� ��ġ�� add

			while (front != rear) {
				// queue.poll();
				int rr = queue[++front][0];
				int cc = queue[front][1];
				
				int bombRange = map_tmp[rr][cc] - 1; // ���� ����
				shooted[rr][cc] += 1; // queue�� �� ����� �ε����� ������ 1 �̻��̹Ƿ�, ù ������ ������ �÷���
				isBomb[cc] = true; // ��Ʈ�����Ƿ� true�� �ٲ���

				for (int bomb = 1; bomb <= bombRange; bomb++) { // ���� ������ŭ Ž��
					for (int di = 0; di < dr.length; di++) {
						int nR = rr + dr[di] * bomb; // ���߽����� ���� ����� ���� �ε���
						int nC = cc + dc[di] * bomb; // ���߽����� ���� ����� ���� �ε���

						if (nR < 0 || nC < 0 || nR >= H || nC >= W) { // ������ ����� ���
							continue;
						}

						// ����������, �ش� �ڸ��� ����� �ְ�, �ѹ��� �������� ���� ���
						if (map_tmp[nR][nC] >= 1 && shooted[nR][nC] == 0) {
							// queue�� �־���
							queue[++rear][0] = nR;
							queue[rear][1] = nC;
							shooted[nR][nC] += 1; // �ش� ����� ���� ������ ó��
						}
					}
				}
			} // end of while(queue)
			// ����� �����ؼ� ���� ���� ���� ��ϵ��� ������
			down(map_tmp,isBomb,maxHByWIndex_tmp); 
		} // end of for(set)
	} // end of func(drop)

	/**
	 * ������ �ִ� �κ��� ����� ������ (stack ���)
	 * */
	private static void down(int[][] map_tmp, boolean[] isBomb, int[] maxHByWIndex_tmp) {
		int[] stack = new int[H]; // ������ �迭�� �����̹Ƿ�, stack�� FILO�� �̿�
		int top = -1; // stack�� �ε��� ����
		
		// ��� �����ϱ� (�����ϸ鼭 shooted '0'���� �ٲ��ֱ�)
		for (int tc = 0; tc < W; tc++) {
			if (!isBomb[tc]) { // ������ ���� ���̶�� Ž������ ����
				continue;
			}

			for (int tr = (H - maxHByWIndex_tmp[tc]); tr < H; tr++) {
				if (shooted[tr][tc] != 0 && map_tmp[tr][tc] != 0) { // ����� �����ߴٸ�
					shooted[tr][tc] = 0;
					ans_tmp--; // �ӽ� ���� ������ ���ҽ�����
				} else { // �ƴ϶��, ���� �����ִ� ����̹Ƿ� map�� ����������ϹǷ� stack�� ������ 
					stack[++top] = map_tmp[tr][tc]; 
				}
				map_tmp[tr][tc] = 0; // ���ÿ� ���������Ƿ�, ��� '0'���� �������
			}

			int tr = H - 1; // ����� ä��� ���� �����ϴ� ��ġ
			maxHByWIndex_tmp[tc] = top + 1; // ���� ����� ���� ����
			while (top != -1) { // stack�� �ִ� ��ŭ ��� ä���
				map_tmp[tr--][tc] = stack[top--];
			}
		}
	} // end of func(down)
} // end of class
