package programmers.level3;

public class Solution_UP_���� {
	public static void main(String[] args) {
		int[] input = { 130, 130, 130, 130 };
		int M = 485;
		int answer = solution(input, M);
		System.out.println(answer);

	}

	public static int solution(int[] budgets, int M) {
		int answer = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < budgets.length; i++) {
			max = Math.max(budgets[i], max);
		} // �ִ� �̱�

		int start = 0; // ó�� start�� 0���� ��
		int end = max; // ó�� end�� ��û�� ���� �� ���� ū ������ ����

		// ���� Ž�� ����
		while (true) {
			if (start > end) { // start�� end�� �Ѿ�ٸ�, Ž�� ����
				break;
			}

			int mid = (start + end) / 2; // ������ ������ ������

			long tmpSum = 0; // mid��� ������ ����������, ���ǿ� �����ϴ��� Ȯ���ϱ� ���� �� ����
			for (int i = 0; i < budgets.length; i++) {
				if (budgets[i] > mid) { // ��û�� ������ ������ ���꺸�� ū ���, 
					tmpSum += mid; // ������ �������� ������
				} else { // ��û�� �������� ������ ��,
					tmpSum += budgets[i]; // ��û�� ������ ������
				}
			}

			if (tmpSum > M) { // �־��� M���� ũ�ٸ�, �� ���� ������ ������
				end = mid - 1;
			} else { // �־��� M���� �۴ٸ�, �� ���� ������ ������
				start = mid + 1;
				answer = Math.max(answer, mid); // ���� ����
			}
		}
		return answer; // ���� ��ȯ
	} // end of func(solution)
}
