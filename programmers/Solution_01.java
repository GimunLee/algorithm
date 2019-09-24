package programmers;

public class Solution_01 {
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = { 4, 10, 15 };
		int[] supplies = { 20, 5, 10 };
		int k = 30;
		// result : 2
		System.out.println(solution(stock, dates, supplies, k));

	} // end of main

	public static int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;

		int lastDate = 0; // 0�Ϻ��� ����
		here: for (int i = 0; i < dates.length; i++) { // ������
			int date = dates[i];
			int supplie = supplies[i];

			int sub = date - lastDate;

			if ((stock - sub) <= 0) { // �ݵ�� ���޹޾ƾ� �ϴ� ���
				stock = (stock - sub) + supplie;
				lastDate = date;
				answer++;
				continue;
			} else { // ������ �� �޾��� �� ������ ������ �Ǵ��� Ȯ��
				int idx = i + 1;
				while (idx < dates.length) {
					if (stock - (dates[idx] - lastDate) <= 0) {
						i = idx - 1;
						continue here;
					}
					idx++;
				}
				continue;
			}
		}

		return answer;
	}
} // end of class
