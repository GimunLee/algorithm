package programmers;

import java.util.Arrays;

public class Solution_����Ʈ {
	public static void main(String[] args) {
		int[] people = { 10, 20, 30, 40, 50, 60, 70, 80, 90 }; // 5
		int limit = 100;
		System.out.println(solution(people, limit));

	} // end of main

	public static int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);

		boolean[] visited = new boolean[people.length];
		int n = people.length;

		here: for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > i; j--) { // ������ ���� ���ſ� ��� ���� Ÿ��
				// ��Ʈ�� 1 : ���� ������ ����� Ż �� ���ٸ�, �� �̻��� ������� ���� Ż �� ����
				// ��Ʈ�� 2 : ���� �ο� �� ��ŭ �ʿ��� ->
//				if (visited[j]) {
//					continue;
//				}
				n--;
				int sum = people[i] + people[j];
				if (sum <= limit) {
					visited[j] = true;
					answer++;
					continue here;
				} else {
					visited[j] = true;
					answer++;
				}
			}
			answer++;
		}
		return answer;
	}
} // end of class
