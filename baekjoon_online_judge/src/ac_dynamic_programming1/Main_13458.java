package ac_dynamic_programming1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ���� ����
 */
public class Main_13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()) + 1;
		int[] test_room = new int[N]; // ������ ��
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < N; i++) {
			test_room[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken()); // �� ������
		int C = Integer.parseInt(st.nextToken()); // �� ������

		long sum = 0;
		for (int i = 1; i < N; i++) {
			test_room[i] -= B;
			sum++;
			if (test_room[i] > 0 && B < C) {
				long tmp = (test_room[i] / C);
				if (test_room[i] % C > 0) {
					tmp += 1;
				}
				sum += tmp;
			} else if (test_room[i] > 0 && B >= C) {
				long tmp = (test_room[i] / C);
				if (test_room[i] % C > 0) {
					tmp += 1;
				}
				sum += tmp;
			}
		}
		System.out.println(sum);
	}
}
