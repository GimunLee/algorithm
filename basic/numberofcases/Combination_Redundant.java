package com.numberofcases;

import java.util.ArrayList;
import java.util.Arrays;

/** Redundant Combination�� �پ��� �������� �˾ƺ�. (���� �ӵ� : ArrayList < Array) */

public class Combination_Redundant {
	static final int[] input = { 1, 2, 3 }; // ����������
	static final int N = input.length; // ������ ������������ ��
	static final int R = 2; // ������ �ϼ��� ��

	static ArrayList<Integer> list; // ArrayList�� ����, ������ ������ ����
	static int[] array; // Array�� ����, ������ ������ ����

	public static void main(String[] args) {
		/** �ߺ����� ArrayList */
		System.out.println("-- �ߺ����� ArrayList -------------------");
		list = new ArrayList<>();
		nHr_list(0, 0);

		/** �ߺ����� Array */
		System.out.println("-- �ߺ����� Array -----------------------");
		array = new int[R];
		nHr_array(0, 0);

		/** �ߺ����� Function1 */
		System.out.println("-- �ߺ����� Function1 -------------------");
		array = new int[R];
		nHr_function1(0, 0);

		/** �ߺ����� Function2 */
		System.out.println("-- �ߺ����� Function2 -------------------");
		array = new int[R];
		nHr_function2(N, R);
	} // end of main

	/**
	 * ArrayList�� ����� �ݺ���+��� ���� �Լ�
	 * 
	 * @param idx ���� ���������Ϳ��� �̰����ϴ� �ε���
	 * @param len ���� ���� ������ ����
	 */
	private static void nHr_list(int idx, int len) {
		if (len == R) { // �������� : ������ �ϼ����� ���
			System.out.println(list);
			return;
		}
		for (int i = idx; i < N; i++) {
			list.add(input[i]);
			nHr_list(i, len + 1); // Combination�� �ٸ��� 'i+1'�� ���� �ʴ´�.
			list.remove(list.size() - 1);
		}
	} // end of nHr_list()

	/**
	 * Array�� ����� �ݺ���+��� ���� �Լ�
	 * 
	 * @param idx ���� ���������Ϳ��� �̰����ϴ� �ε���
	 * @param len ���� ���� ������ ��, �� ������ �迭�� �ε���
	 */
	private static void nHr_array(int idx, int len) {
		if (len == R) { // �������� : ������ �ϼ����� ���
			System.out.println(Arrays.toString(array));
			return;
		}

		for (int i = idx; i < N; i++) {
			array[len] = input[i];
			nHr_array(i, len + 1); // Combination�� �ٸ��� 'i+1'�� ���� �ʴ´�.
		}
	} // end of nHr_array()

	/**
	 * @param idx ���� ���������Ϳ��� �̰����ϴ� �ε���
	 * @param len ���� ���� ������ ��, �� ������ �迭�� �ε���
	 */
	private static void nHr_function1(int idx, int len) {
		if (len == R) { // �ϳ��� ������ �ϼ���
			System.out.println(Arrays.toString(array));
			return;
		} else if (idx == N) {
			return;
		}

		array[len] = input[idx];
		nHr_function1(idx, len + 1);
		nHr_function1(idx + 1, len);

	} // end of nHr_function1()

	private static void nHr_function2(int n, int r) {
		if (r == 0) { // �ϳ��� ������ �ϼ���
			System.out.println(Arrays.toString(array));
			return;
		}else if (n == 0) {
			return;
		} 
		array[r - 1] = input[n - 1];
		nHr_function2(n, r - 1); // ������ ���
		nHr_function2(n - 1, r); // �������� ���� ���
	} // end of nHr_function2()
	
} // end of class
