package test;

import java.util.*;

public class Solution_02 {
	static int[] arr, set;
	static boolean[] visit;
	static int r;
	static int result = Integer.MIN_VALUE;

	public static int solution(int[] v) {
		// �迭 v ���� ������ Ȱ��
		arr = new int[v.length];
		for (int i = 0; i < v.length; i++) {
			arr[i] = v[i];
		}

		visit = new boolean[arr.length];
		r = v.length;
		set = new int[r];
		permu(0);
		return result;
	}

	// permuutation ����
	public static void permu(int len) {
		if (len == r) { // ������ ������� ��Ȳ
			int num = cal(); // ���� �迭�� ���밪�� ��
			if (result < num)
				result = num; // result���� ũ�� ����
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!visit[i]) {
				set[len] = arr[i];
				visit[i] = true;
				permu(len + 1);
				visit[i] = false;
			}
		}
	}

	// ���밪 ���
	public static int cal() {
		int sum = 0;
		for (int i = 0; i < set.length - 1; i++) {
			sum += Math.abs(set[i] - set[i + 1]);
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {

		int[] v = { 20, 8, 10, 1, 4, 15 };

		System.out.println(solution(v));
	}

}
