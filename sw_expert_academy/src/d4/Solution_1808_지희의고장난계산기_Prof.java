import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1808_�����ǰ��峭����_Prof {
	private static int min;
	private static boolean[] key;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {

			key = new boolean[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < key.length; i++) {
				key[i] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
			}
			int X = Integer.parseInt(br.readLine().trim()); // 1<= X <= 10^6
			min = Integer.MAX_VALUE; // �ּҹ�ư�� ���� Ƚ��
			go(X, 0, true, 0);
			System.out.println("#" + tc + " " + (min == Integer.MAX_VALUE ? -1 : min));
		}
	}

	/** ��ư ������ ����� �մ� ��� ���� ������ ��� ȣ�� */
	private static void go(int X, int push, boolean complete, int num) {
		if (X == 1) { // ��������
			// �ּҰ� ������Ʈ
			if (min > push) {
				min = push;
			}
		} else if (complete) { // �ݺ�����
			// ���ڸ� ����� ��� ���
			for (int i = 0; i < key.length; i++) {
				if (key[i] && i != 0 && X >= i) { // ������ �ִ� ��ư, 0���� �����ϸ� �ȵ�
					go(X, push + 1, false, i); // ������ ����
				}
				if (key[i] && i != 0 && i != 1 && X % i == 0) { // ���� �� �ִ� ��ư, 0���� �����ϸ� �ȵ�,
					go(X / i, push + 2, true, 0); // 1�ڸ� ���� �ϼ�
				}
			}
		} else if (!complete) { // ���� ���ؿ� num�� ����� �ִ� ���ڸ� �߰��� ���̱�
			for (int i = 0; i < key.length; i++) {
				if (key[i] && X >= num * 10 + i) { // ���� ����
					go(X, push + 1, false, num * 10 + i); // ������ ����
				}
				if (key[i] && X % (num * 10 + i) == 0) { // ���ڸ� �ϼ�
					go(X / (num * 10 + i), push + 2, true, 0);
				}
			}
		}
	} // end of main
} // end of class
