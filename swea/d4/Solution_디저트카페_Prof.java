package swea.d4;

/**
 * ������ǥ (r,c)�� ���ϰ�, �簢���� ��(w), ����(h)�� ���ѵ�
 * �簢 ������ �� �������� ������ ����� �ʴ� �� ��ȿ�� �˻縦 �ϰ�,
 * �簢�� ������ ���鼭 �۾�
 * 		����Ʈ ī�� ��ȣ�� ī�����ϸ鼭 ��ġ�� ��ȣ�� �ִ��� üũ
 * 			��ġ�� ��ȣ�� ������ ����Ʈī�� ���� : (w + h) * 2
 * �ִ� �湮�� �� �ִ� ����Ʈī���� ������ ���
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_����Ʈī��_Prof {

	private static int[][] m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int ts = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= ts; t++) {

			int N = Integer.parseInt(br.readLine().trim()); // ���� ũ��, 4<=N<=20
			m = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int maxCnt = -1; // �簢���� �Ұ����� ���� -1�� ����ϱ� ���� �ʱⰪ -1�� ����

			// ��� ������ ���ؼ�, ��� �簢�� ũ�⸦ ������
			for (int r = 0; r < N; r++) { // ��
				for (int c = 0; c < N; c++) { // ��
					for (int w = 1; w < N; w++) { // ��
						if (r + w >= N || c + w >= N)
							continue; // ���� ���
						for (int h = 1; h < N; h++) { // ����
							if (r + h + w >= N || c - h < 0)
								continue; // ���� ���

							int cnt = go(r, c, w, h); // �簢�� ��翡�� �湮�� �� �ִ� ī�� ������ ����
							if (maxCnt < cnt)
								maxCnt = cnt;
						}
					}
				}
			}

			System.out.println("#" + t + " " + maxCnt);

		}
	}

	/** �簢�� ��翡�� �湮�� �� �ִ� ī�� ������ ���� */
	public static int go(int r, int c, int w, int h) {
		boolean[] flag = new boolean[101];

		for (int i = 0; i <= w; i++) {

			if (flag[m[r + i][c + i]]) { // ���� ���� ������ -1�� �����Ѵ�.
				return -1;
			} else {
				flag[m[r + i][c + i]] = true;
			}

			if (flag[m[r + h + i][c - h + i]]) { // ���� ���� ������ -1�� �����Ѵ�.
				return -1;
			} else {
				flag[m[r + h + i][c - h + i]] = true;
			}

		}
		for (int i = 1; i < h; i++) { // �簢���� �������� üũ�� �����Ƿ�
			if (flag[m[r + i][c - i]])
				return -1; // ���� ī���ȣ �ߺ�
			flag[m[r + i][c - i]] = true;

			if (flag[m[r + w + i][c + w - i]])
				return -1;
			flag[m[r + w + i][c + w - i]] = true;
		}

		return (w + h) * 2;
	}

}
