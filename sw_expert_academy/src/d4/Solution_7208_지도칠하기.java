import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7208_����ĥ�ϱ� {
	static int N;
	static int[] color;
	static int[][] m;
	private static int min;
	private static int[] nc;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // 3 <= N <= 8
			color = new int[N]; // ���� �ʱ� ����

			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < color.length; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}

			m = new int[N][N]; // �������
			for (int i = 0; i < m.length; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < m.length; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			for (int i = 0; i < m.length; i++) {
//				System.out.println(Arrays.toString(m[i]));
//			}
			min = Integer.MAX_VALUE; // �ּ� ���淮
			nc = new int[N];
			dfs(0, 0);

			sb.append('#').append(tc).append(' ').append(min).append('\n');
		} // end of for of TestCase
		System.out.println(sb.toString());
	} // end of main

	/** ���� step�� ���� ������ ä���, ���ȣ��, step:�ܰ�, cnt: �� �ܰ迡�� ������ �ٲ� �����Ǽ� */
	private static void dfs(int step, int cnt) {
		if (step == N) {
			// ���� ������ ����� ���� ĥ�� ������ �ٸ� ���� ����� ���� => min�� ������Ʈ
			if (min > cnt) {
				min = cnt;
			}
		} else { // �ݺ�����
			// ���� step ���� ��� ����� ĥ�غ��� ���ȣ��
			go: for (int i = 1; i <= 4; i++) {
				nc[step] = i;
				// ������ ������ ������ �ٸ� ��츸 ����
				for (int j = 0; j < step; j++) { // ���� ���� ������ ĥ�� ����� ��
					if (m[step][j] == 1 && nc[j] == i) {
						continue go;
					}
				}
				if (color[step] != nc[step]) {
					if (min > cnt + 1) { // ����ġ��
						dfs(step + 1, cnt + 1);
					}
				} else {
					dfs(step + 1, cnt);
				}
			}
		}
	}
} // end of class
