package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Solution_2112_��ȣ�ʸ� {
	private static int D; // ��ȣ �ʸ��� �β� (3<= D <=13)
	private static int W; // ��ȣ �ʸ��� ���� ũ�� (1<= D <=20)
	private static int K; // �հ� ���� (1<= K <=D)

	private static int[][] map; // ��ȣ �ʸ�
	private static int[][] tmp_map; // ��ȣ �ʸ��� ���� �����͸� �����ص� ����

	private static int[] set; // ��ǰ�� ������ ��(row)�� �ߺ� ������ �̾� ������ ����
	private static int change; // ��ǰ�� ó���ؾ��ϴ� ��(row)�� ���� (��Ʈ��ŷ�� ���� ����)
	private static int ans; // ���� ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // ��� �ð��� �Ƴ��� ���� ������ �����ص� ����
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // ��ȣ �ʸ��� �β� (3<= D <=13)
			W = Integer.parseInt(st.nextToken()); // ��ȣ �ʸ��� ���� ũ�� (1<= W <=20)
			K = Integer.parseInt(st.nextToken()); // �հ� ���� (1<= K <=D)

			map = new int[D][W]; // ��ȣ �ʸ� ���� ����
			tmp_map = new int[D][W]; // ������ ������ ��ȣ �ʸ� ���� ����
			set = new int[D]; // ��ǰ�� ������ ��(row)�� �ߺ� ������ �̾� ������ ����

			// 0 : A, 1 : B
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					tmp_map[r][c] = map[r][c]; // ���� ������ ����
				}
			} // end of for(input)

			ans = Integer.MAX_VALUE; // ���� ���� : �ּڰ��� ���ؾ��ϹǷ� ���� ū ������ �ʱ�ȭ

			// ��ǰ�� �������� �ʰ� ���ǿ� �����Ѵٸ�, ���� 0 ���� �� ��ݺ�
			if (checkFilm()) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}

			change = 0;
			permu(0); // ��ǰ�� ������ ��(row)�� �ߺ� ������ �̾��ش�.
			sb.append("#").append(tc).append(" ").append(ans).append("\n"); // ���� ����
		} // end of for(TestCase)
		System.out.println(sb.toString()); // ���� ���
	} // end of main

	/**
	 * ���� ��ȣ �ʸ��� ���ǿ� �����ϴ��� �˻� �� boolean �� ��ȯ
	 */
	private static boolean checkFilm() {

		for (int c = 0; c < W; c++) { // ��(column) ������ �ݺ���
			int r = 0;
			boolean flag = false;

			while (r < D) { // �β���ŭ �ݺ�
				int each_cnt_A = 0; // �� ��(column)���� A�� ���ӵǴ��� �Ǻ��� ����
				int each_cnt_B = 0; // �� ��(column)���� B�� ���ӵǴ��� �Ǻ��� ����

				// ���ӵǴ� A �˻�
				while (r < D && map[r][c] == 0) { // ��(row)�� D���� �۰�, A�� ���� �ݺ�
					each_cnt_A++; // ���ӵǱ� ������ �ش� ������ ����������
					r++;
				}
				if (each_cnt_A >= K) { // �ݺ����� ������ ��, ���ӵ� A�� ���� ���ǿ� �����ϸ�
					flag = true;
					break; // break �ϴ� ������ �� ��(row)���� �ϳ��� �����ص� �Ǳ� ������
				}

				// ���ӵǴ� B �˻�
				while (r < D && map[r][c] == 1) {
					each_cnt_B++; // ���ӵǱ� ������ �ش� ������ ����������
					r++;
				}
				if (each_cnt_B >= K) { // �ݺ����� ������ ��, ���ӵ� A�� ���� ���ǿ� �����ϸ�
					flag = true;
					break;
				}
			}
			if (!flag) {
				return false; // ������ �� �ʿ䰡 ����
			}
		} // end of for(each column check)
		return true;
	} // end of func(checkFilm)

	/**
	 * ��ǰ�� ������ ��(row)�� �� ex) set = { 0 , 1 , 0 , 2 , 2 } �� ��, 1��° ���� A�� 3,4��° ���� B��
	 * ��ǰ ���� len : ������� ���� �ߺ� ������ ����
	 */
	private static void permu(int len) {
		if (len == D) { // �ߺ� ������ ��� �̾��� ��,
			if (change >= ans) { // �̹� ���� ����� ���亸�� ��ǰ�� ó���ؾ��ϴ� ��(row) ������ ������, �˻����� �ʴ´�. (��Ʈ��ŷ)
				return;
			}

			// ��ǰ �����ؼ� Type �ٲٱ�
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					int changeType = set[i] - 1; // �����󿡼��� 0 : A, 1 : B �̹Ƿ� �ϳ��� ���༭ �ٲٱ�
					for (int j = 0; j < W; j++) { // ��(column)��ŭ �ݺ�
						map[i][j] = changeType;
					}
				}
			}

			// ��ǰ�� �ٲ� ��, �˻��غ���
			if (checkFilm()) { // ���� ���� ���, ������ ��������
				ans = change < ans ? change : ans;
			}

			// �ٸ� ��츦 Ȯ���ؾ��ϹǷ�, ��ȣ �ʸ��� ���¸� ���󺹱ͽ�Ŵ
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					for (int j = 0; j < W; j++) {
						map[i][j] = tmp_map[i][j];
					}
				}
			}
			return;
		}

		// 0 : ��ǰ ���� ����, 1 : A�� ������, 2 : B�� ������
		for (int i = 0; i < 3; i++) {
			if (i != 0 && set[len] == 0) { // �ش� ��(row)�� ��ǰ�� �����ؾ� �ϰ�, �ش� ��(row)�� ��ǰ�� ó������ �ʾҴ� ��� 
				change++; // ��ǰ�� ó���ؾ�����ϴ� ��(row) ������ ����������
			}
			if (i == 0 && set[len] != 0) { // �ش� ��(row)�� ��ǰ�� �������� �ʰ�, �ش� ��(row)�� ��ǰ�� ó���ߴ� ���
				change--; // ��ǰ�� ó���ؾ�����ϴ� ��(row) ������ ���ҽ�����
			}

			set[len] = i; // �ߺ� ���� ����
			permu(len + 1); // ��� ȣ��
		}
	}
} // end of class
