package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [S/W �����ذ� �⺻] 9���� - ��Ģ���� ��ȿ�� �˻�
 */
public class Solution_1233_��Ģ������ȿ���˻� {
	static int Answer = 1;
	static int chk_index = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			Answer = 1;
			int N = Integer.parseInt(br.readLine().trim()); // ����� ����
			String[] tree = new String[201];
			StringTokenizer st;

			for (int i = 1; i <= N / 2; i++) { // �ڽİ�
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken(); // �ϳ� ������
//				���ڵ� ���ڵ� ������ �ֱ�
				tree[i] = st.nextToken(); // ������ or ����
				st.nextToken(); // ������
				// ¦���� �� �������������� �ڽ��� �� ���̹Ƿ� �� ������.
				if (i == N / 2 && N % 2 == 0)
					break;
				st.nextToken(); // ������
			}

			for (int i = N / 2 + 1; i <= N; i++) { // ������ �ش��ϴ� ��
				st = new StringTokenizer(br.readLine(), " ");
				tree[i] = st.nextToken(); // ���� �ֱ� i ������ ���� ��
			}

//			��Ʈ�� �����ڰ� �ƴ� ��
			if (!checkOpe(tree[1])) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}

			for (int i = 1; i <= N / 2; i++) { // ������ Ž��
				// �ڱⰡ �������� ��,
				if (checkOpe(tree[i])) {

				} else { // �ڱⰡ ������ ��,
					if (tree[i * 2] != null) {
						if (!checkOpe(tree[i * 2])) { // ���� �ڽ��� �����̸�
							Answer = 0;
							break;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + Answer);
		}
		br.close();
	}

	// ���������� �Ǻ�
	public static boolean checkOpe(String input) {
		if (input.equals("*") || input.equals("-") || input.equals("/") || input.equals("+")) {
			return true;
		} else {
			return false;
		}
	}
}
