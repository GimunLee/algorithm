package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * �ٻ̰��� �ϴ°� �ƴ϶�, �� ���� ��Ʈ���鼭 �ؾ���
 * */
public class Solution_5656_�������� {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int[][] map; // ���� �� �����͸� �����ϴ� ����
	private static int N; // ������ ��� Ƚ��
	private static int W; // ���� ���� ���� ���� 
	private static int H; // ���� ���� ���� ����
	private static int[][] shooted; // ������ ���� ������ �ϴ��� üũ�ϴ� ����
	private static int[] maxHByWIndex; // �ش� ���� �ִ� ����� ���� �����ϴ� ����
	private static int[][] queue; // ������ �ѹ� �������� ��, ������ �޴� ����� �ѹ��� ó���ϱ� ���� ť
	private static int blockCnt; // ����� �� ������ �����ϴ� ����
	private static int ans; // ������ ������ ����
	private static int ans_tmp; // ������ �ӽ� ���� ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder sb = new StringBuilder(); // ������ �����ߴٰ� ����ϴ� ����
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // ������ �� �� �ִ� Ƚ��
			W = Integer.parseInt(st.nextToken()); // ���� ���� ���� ���� / Width (Column)
			H = Integer.parseInt(st.nextToken()); // ���� ���� ���� ���� / Height (Row)

			map = new int[H][W]; // ����� ���¸� �����ϴ� �� ����
			maxHByWIndex = new int[W]; // ���� �� ���� �����ϴ� ����� ������ �����ϴ� ����
			 
			queue = new int[1000][2]; // ������ �ѹ� �������� ��, ������ �޴� ����� �ѹ��� ó���ϱ� ���� ť 
			shooted = new int[H][W]; // �ش� ����� �¾Ҵ��� �� �¾Ҵ���, �����ϴ� ����

			set = new int[N]; // ������ ����߸� ���� �� ���� (�ߺ�����)
			blockCnt = 0; // ����� �� ������ �����ϴ� ����
			ans = Integer.MAX_VALUE; // �� �׽�Ʈ���̽� �� ������ �����ϴ� ����
			
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] >= 1) { // ����� ��������, 
						maxHByWIndex[c] += 1; // �ش� ���� ����� ������ ������ ����
						blockCnt++; // ����� �� ������ �÷���
					}
				}
			} // end of for(input)

			solve(0); // ������ N�� �� ��, ����߸� ��ġ�� �ߺ������� ����

			sb.append("#").append(tc).append(" ").append(ans).append("\n"); // �׽�Ʈ���̽� �� ���� ����
		} 
		System.out.println(sb.toString()); // ���� ��� 
	} // end of main()

	private static int[] set; // ������ ����߸�, �ߺ� ������ �����ϴ� ����

	/**
	 * ������ ����߸� ��ġ�� �����ϰ�, ��� �̾��� �� ������ ���ʴ�� ����߷���
	 * */
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
			dropBall(map_tmp, maxHByWIndex_tmp, set); // ���� ������ ����߸� ��ġ�� ������ ����߷��� 
			ans = ans > ans_tmp ? ans_tmp : ans; // ���� ���� ����
			return;
		}

		for (int i = 0; i < W; i++) {
			set[len] = i; // �ߺ� ���� ���� 
			solve(len + 1); // �ߺ� ���� ��� ȣ��
		}
	}

	/**
	 * ������ ����߷����� �Լ�
	 * */
	private static void dropBall(int[][] map_tmp, int[] maxHByWIndex_tmp, int[] set) {
		
		// �ش� ��ġ�� ���� ���� �� ���� ������ �������� ���� Ž������ �ʰ�, �ִ� ���̸� �����س������� ó��
		for (int i = 0; i < set.length; i++) { // ���� Ƚ����ŭ �ݺ�
			boolean[] isBomb = new boolean[W];
			int c = set[i]; // ����߸��� ��ġ (Column)
			if(maxHByWIndex_tmp[c] <= 0 ) { // �ش� ���� ���� ����� ���ٸ� ����߸��� ����
				continue;
			}
			
			// �ش� ��ġ�� ���� ���� ��ġ (�迭������ �����̹Ƿ� �ִ� ���̿��� ����� ���ָ� ������ �ε����� ����)
			int r = H - maxHByWIndex_tmp[c];
			
			int front = -1; // queue�� poll�� �� ��, �ε��� ����
			int rear = -1; // queue�� add�� �� ��, �ε��� ����

			queue[++rear][0] = r; // ������ ó�� ������ ����� ���� ��ġ�� add
			queue[rear][1] = c;  // ������ ó�� ������ ����� ���� ��ġ�� add

			while (front != rear) {
				// queue.poll();
				int rr = queue[++front][0];
				int cc = queue[front][1];
				int bombRange = map_tmp[rr][cc] - 1; // ���� ����
				shooted[rr][cc] += 1; // queue�� �� ����� �ε����� ������ 1 �̻��̹Ƿ�, ù ������ ������ �÷���
				isBomb[cc] = true;
				
				for (int bomb = 1; bomb <= bombRange; bomb++) { // ���� ������ŭ Ž��
					for (int di = 0; di < dr.length; di++) {
						int nR = rr + dr[di] * bomb; // ���߽����� ���� ����� ���� �ε���
						int nC = cc + dc[di] * bomb; // ���߽����� ���� ����� ���� �ε���

						if (nR < 0 || nC < 0 || nR >= H || nC >= W) { // ������ ����� ���
							continue;
						}

						// ����������, �ش� �ڸ��� ����� �ְ�, �ѹ��� �������� ���� ���
						if (map_tmp[nR][nC] >= 1 && shooted[nR][nC] == 0) {
							// queue add
							queue[++rear][0] = nR; 
							queue[rear][1] = nC;
							shooted[nR][nC] += 1; // ���� ������ ó��
						}
					}
				}
			}

			int[] stack = new int[H]; // ������ �迭�� �����̹Ƿ�, stack�� FILO�� �̿�
			int top = -1; // stack�� �ε��� ����
			// ��� �����ϱ� (�����ϸ鼭 shooted '0'���� �ٲ��ֱ�)
			for (int tc = 0; tc < W; tc++) {
				if(!isBomb[tc]) {
					continue;
				} 

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
