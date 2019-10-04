package programmers.level3;

public class Solution_UP_�Ա��ɻ� {
	public static void main(String[] args) {
		int n = 6;
		int[] times = { 7, 10 }; // 28
		System.out.println(solution(n, times));
	} // end of main

	private static int[] sortedTimes; // times�� ������ �迭
	
	public static long solution(int n, int[] times) {
		long answer = 0; // ���ʿ��� 0
		sortedTimes = times; // times�� �����ϱ� ���� sortedTimes�� ����
		quickSort(0, times.length - 1); // quickSort ���� ���� 
		// Arrays.sort(times); // ���̺귯�� ���
		
		long start = 0; // ó�� start�� 0
		long end = Long.MAX_VALUE; // ó�� end�� ���� ū ������ ����

		while (true) {
			if (start > end) { // start�� end���� Ŀ���ٸ�, ���� Ž���� ��� ��ģ ���
				break;
			}
			
			long mid = (start + end) / 2; // start�� end�� ���ϰ� 2�� ���� ���� �����̶�� �����Ͽ� ǰ

			long sum = 0; // mid��ŭ�� �ð��� �־�������, mide�� �� �Ա� �ɻ���� �ð����� ���� ���밡���� �ο��� ���ϱ� ���� ����
			for (int i = 0; i < sortedTimes.length; i++) { // �� �ð��� Ž���ϸ鼭
				sum += mid / sortedTimes[i]; // �ش� mid�ð����� ó���� �� �ִ� �ο��� ������
				if (sum >= n) { // sum�� �̹� n���� ó���� �� �ִٸ� �� ���غ� �ʿ䰡 ����
					break;
				}
			}
			if (sum < n) { // �ش� mid �ð��� n���� ó�����Ѵٸ�
				start = mid + 1; // �ð��� �� Ű����
			} else { // �ش� mid�ð��� n���� ó���� �� �ִٸ�,
				end = mid - 1; // ������ ���� ã�� ���� ���� �� �۰� �غ�
				answer = mid; // �������� ����
			}
		}
		return answer; // ���� ��ȯ
	} // end of func(solution)

	/** ������ ����Ʈ �Լ� */
	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}

		int pivot = sortedTimes[(first + last) / 2];
		int i = first - 1;
		int j = last + 1;

		while (true) {
			while (sortedTimes[++i] < pivot) {
			}
			while (sortedTimes[--j] > pivot) {
			}

			if (i >= j) {
				break;
			}
			int tmp = sortedTimes[i];
			sortedTimes[i] = sortedTimes[j];
			sortedTimes[j] = tmp;
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);
		return;
	} // end of func(quickSort)
}
