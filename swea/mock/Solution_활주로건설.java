package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Ȱ�ַΰǼ� {
	private static int[][] map;
	private static int N, X;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			// -- end of input
			int answer = 0;
			int[] colMap = new int[N];
			for (int k = 0; k < N; k++) {
				if (solution(map[k])) {
					answer++;
				}
				// column check
				for (int i = 0; i < N; i++) {
					colMap[i] = map[i][k];
				}
				if (solution(colMap)) {
					answer++;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		} // end of TestCase
		System.out.println(sb.toString());
	} // end of main

	private static boolean solution(int[] eachMap) {
		boolean isCheck = true;
		boolean[] build = new boolean[N];
		int length = 1;
		// �ȵǴ��� Ȯ��
		for (int i = 0; i < N - 1; i++) {
			int next = i + 1;
			if (Math.abs(eachMap[i] - eachMap[next]) >= 2) { // �� ������ Ȱ�ַΰ� �� �� ����
				isCheck = false;
				break;
			}

			if (eachMap[i] - eachMap[next] == -1) { // �������� ���
				if (length < X) { // �ȵǴ� ���
					isCheck = false;
					break;
				}
				length = 1;
			} else if (eachMap[i] - eachMap[next] == 1) { // �������� ���
				// Ȱ�ַ� ���̸�ŭ ��ź���� Ȯ��
				length = 1;
				int height = eachMap[next];
				while (next + 1 < N) {
					next++;
					if (height != eachMap[next]) {
						break;
					} else {
						length++;
						if (length == X) {
							break;
						}
					}
				}
				if (length < X) { // ��ġ �Ұ���
					isCheck = false;
					break;
				}
				i = next - 1;
				length = 0;
			} else { // ��ź�� ���
				length++; // ��ź�� ���� -> Ȱ�ַθ� ��ġ�� �� �ִ��� Ȯ���� ����
			}
		}
		return isCheck;
	}
} // end of class
