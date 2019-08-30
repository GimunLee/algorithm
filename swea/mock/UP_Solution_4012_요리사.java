package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2019.08.27.(ȭ)
 * 2019.08.27.(ȭ) Upload
 */
public class UP_Solution_4012_�丮�� {
	private static int N; // ������ ������ ����ִ� ���� ��(row)�� ��(column) ���� ����
	private static int[][] map; // ������ ������ ������ �迭

	private static int[] aSet; // a ������ ����� �ε����� ������ �迭 (����)
	private static int[] bSet; // b ������ ����� �ε����� ������ �迭 (����)
	private static boolean[] visited; // a ������ ������ �������� �����ϴ� �迭

	private static int[] combSet; // aSet�� bSet�� �ε����� 2���� �̾� ������ ���� ������ �� ������ �迭 (����)
	private static int value; // a,b �� ������ ���� ������ �ӽ� ����

	private static int ANS; // ���� ����

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // ������ �ѹ��� ����ϱ� ���� StringBuilder
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // ��(row)�� ��(column) ���� ��
			map = new int[N + 1][N + 1]; // �� ���� (������ ����� �ʰ� ���� ���� �ϱ� ���� N+1�� ����)

			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 1; c <= N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // end of for(Input)

			aSet = new int[N / 2]; // a ������ ����� �ε����� ������ �迭 ����
			bSet = new int[N / 2]; // b ������ ����� �ε����� ������ �迭 ����
			visited = new boolean[N + 1]; // a ������ ������ �������� �����ϴ� �迭 ����
			ANS = Integer.MAX_VALUE; // ���� ���� �ʱ�ȭ
			combSet = new int[2]; // �� ������ ���� ������ �� ���� �迭 ����

			solve(0, 1); // �켱 a ������ ����� �ε����� b ������ ����� �ε����� ����

			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	/**
	 * a ������ ���鶧 ���� N/2��ŭ �ε����� �̰�, �������� b ������ �ε����� ����. len : ������� ���� �ε��� �� idx :
	 * ���տ��� ������ �̾ƾ��ϴ� ���� �ε���
	 */
	private static void solve(int len, int idx) {
		if (len == N / 2) { // �� ������� ���ݸ�ŭ �̾�����,
			int tmpIdx = 0; // b ������ �����ϱ� ���� �ӽ� �ε��� ����
			for (int i = 1; i < visited.length; i++) {
				if (!visited[i]) { // visited�� ���� false��� b ���Ŀ��� ����� �� ����
					bSet[tmpIdx++] = i;
				}
			}
			value = 0; // a ������ ���� �����ϱ� ���� ����
			comb(aSet, 0, 0); // a ������ ��ü �ε��� �߿� 2���� ������ ����
			int a = value; // a ������ ���� ����
			value = 0; // b ������ ���� �����ϱ� ���� ����
			comb(bSet, 0, 0); // b ������ ��ü �ε��� �߿� 2���� ������ ����
			int b = value; // b ������ ���� ����
			int tmp = Math.abs(a - b); // �������� ���� ���
			ANS = ANS > tmp ? tmp : ANS; // ����� ���Ͽ� ���� ���� ����
			return;
		}

		for (int i = idx; i <= N; i++) {
			aSet[len] = i; // �ε����� a ���� ���� �迭�� ����
			if (aSet[0] != 1) { // a ���İ� b ������ ���� b ���İ� a ������ ���� ����ϴ� ���� �Ȱ����Ƿ�, �װ��� �ɷ��ֱ� ���� ó��
				return;
			}
			visited[i] = true; // a ���Ŀ� �������Ƿ�, b ���Ŀ��� ������� ����
			solve(len + 1, i + 1); // ��� ȣ��
			visited[i] = false;
		}
	} // end of func(solve)

	/**
	 * a ������ ������ �� 2���� �̾�, ���� ����Ѵ�. ex) aSet = 1,2,3 -> (1,2) (2,3) (1,3)
	 * inputSolveSet : �� ���Ŀ� ���̴� ����� ���� �迭 len : ������� ���� ������ �� idx : ������ �̾ƾ��ϴ� ������
	 * �ε���
	 */
	private static void comb(int[] inputSolveSet, int len, int idx) {
		if (len == 2) { // 2����ŭ �̾Ҵٸ�
			value += map[combSet[0]][combSet[1]] + map[combSet[1]][combSet[0]]; // �� ����
			return;
		}

		for (int i = idx; i < N / 2; i++) {
			combSet[len] = inputSolveSet[i];
			comb(inputSolveSet, len + 1, i + 1);
		}
	} // end of func(comb)
} // end of class
