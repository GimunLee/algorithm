package etc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
����)
������������ ���ĵǾ� �ְ� ũ�Ⱑ ���� ���� �迭 3���� �Է¹޴´�.
�迭 �������� ��� �����ϴ� ���ڸ� ��ȯ�϶�. 
�ð����⵵�� O(N) ���Ͽ��� �ϸ�, �ؽø��� ����ؼ��� �� �ȴ�.

{1, 2, 3, 4, 5}
{3, 4, 5, 6, 7}
{2, 3, 9, 10, 11}

�Ǽ��� �� :
(1) continue loop�� �ϴ���, ���� �����ڸ� �Դ´�.
�� i = 0���� �ϰ�, continue�� �ϸ� i++�� ��, �������� 1�� �ٲ��.

(2) cnt �ø��� ������� �ϸ�, ���Ұ� ��ĥ �� ���� �� ������ �߻���.

(3) ���� ������ �ƴ϶�. binary search�� ã��

 */

public class findAllSameNumFromArrays {
	static int number_bound = 100_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");

		System.out.println("�迭 ���� (��) : ");
		int cnt_array = sc.nextInt();

		System.out.println("���� ���� (��) : ");
		int cnt_element = sc.nextInt();

		Random r = new Random();
		int[][] input_array = new int[cnt_array][cnt_element];

		for (int a = 0; a < cnt_array; a++) {
			for (int e = 0; e < cnt_element; e++) {
				input_array[a][e] = r.nextInt(number_bound) + 1;
			}
			Arrays.sort(input_array[a]);
		}

		long start_time = System.currentTimeMillis();
		ArrayList<Integer> answer = Solution(input_array);
		long end_time = System.currentTimeMillis();

		System.out.println("���� �ð� : " + (end_time - start_time));

//      print_input_array(input_array);

		System.out.println("����");

//      for (int a = 0; a < answer.size(); a++) {
//         sb.append(answer.get(a) + " ");
//      }
//      System.out.println(sb.toString());
	}

	static ArrayList<Integer> Solution(int[][] input_array) {
		ArrayList<Integer> ret = new ArrayList();

		// �⺻ ��� ũ�� ���� ����
		int array_cnt = input_array.length;
		int element_cnt = input_array[0].length;

		// �� ��ĺ� ���� �ε���
		int[] search_idx = new int[array_cnt];

		// ã�� ����
		searching: while (true) {

			// �ִ� ���۰� ã��
			int cur_max = Integer.MIN_VALUE;

			// �ִ� ã��
			for (int ac = 0; ac < array_cnt; ac++) {
				if (cur_max < input_array[ac][search_idx[ac]]) {
					cur_max = input_array[ac][search_idx[ac]];
				}
			}

			loop: for (int ac = 0; ac < array_cnt; ac++) {

				// case 1: �ִ񰪰� ���ų� (�ĺ� ���)
				// case 2 : Ŀ�� �� (���ο� �ĺ�)
				// 2 case �� ���� �� ���� �Ʒ� �ݺ���.
				while (input_array[ac][search_idx[ac]] < cur_max) {
					search_idx[ac]++;

					// �� �̻� �� �� ���� ��, Ž�� ��
					if (search_idx[ac] == element_cnt)
						break searching;
				}

				// �ִ� ���ŵ� ���
				if (input_array[ac][search_idx[ac]] > cur_max) {
					cur_max = input_array[ac][search_idx[ac]];
					ac = -1;
					continue loop;
				}
			}

			// ���� �ݺ����� ��� ����� ���
			ret.add(cur_max);

			// ������ ��������, ���� �� ĭ�� �÷��ֱ�.
			for (int ac = 0; ac < array_cnt; ac++) {
				search_idx[ac]++;

				if (search_idx[ac] == element_cnt) {
					break searching;
				}
			}
		}
		return ret;
	}

	private static void print_input_array(int[][] input) {
		for (int r = 0; r < input.length; r++) {
			System.out.println(Arrays.toString(input[r]));
		}
	}
}