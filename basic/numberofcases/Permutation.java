package com.numberofcases;

import java.util.ArrayList;
import java.util.Arrays;

/** Permutation�� �پ��� �������� �˾ƺ�. (���� �ӵ� : ArrayList < Array) */

public class Permutation {
	static final int[] input = { 1, 2, 3, 4 }; // ����������
	static final int N = input.length; // ������ ������������ ��
	static final int R = 3; // ������ �ϼ��� ��

	static ArrayList<Integer> list; // ArrayList�� ����, ������ ������ ����
	static int[] array; // Array�� ����, ������ ������ ����

	static boolean[] visited; // ������ ������ �߿��ϱ� ������ �ʿ��� ����
	public static void main(String[] args) {

		/** ���� ArrayList */
		System.out.println("-- ���� ArrayList -------------------");
		list = new ArrayList<>();
		visited = new boolean[R];
		nPr_list(0,0);

		/** ���� Array */
		System.out.println("-- ���� Array -----------------------");
		array = new int[R];
		nPr_array(0, 0);

		/** ���� Function1 */
		System.out.println("-- ���� Function1 -------------------");
		array = new int[R];
		nPr_function1(0, 0);

		/** ���� Function2 */
		System.out.println("-- ���� Function2 -------------------");
		array = new int[R];
		nPr_function2(4, 3);
		
	} // end of main

	/**
	 * ArrayList�� ����� �ݺ���+��� ���� �Լ�
	 * 
	 * @param idx ���� ���������Ϳ��� �̰����ϴ� �ε���
	 * @param len ���� ���� ������ ����
	 */
	private static void nPr_list(int idx, int len) {
		if (len == R) { // �������� : ������ �ϼ����� ���
			System.out.println(list);
			return;
		}
		for (int i = idx; i < N; i++) {
			list.add(input[i]);
			nPr_list(i + 1, len + 1);
			list.remove(list.size() - 1);
		}
	} // end of nPr_list()
	
	/**
	 * Array�� ����� �ݺ���+��� ���� �Լ�
	 * 
	 * @param idx ���� ���������Ϳ��� �̰����ϴ� �ε���
	 * @param len ���� ���� ������ ��, �� ������ �迭�� �ε���
	 */
	private static void nPr_array(int idx, int len) {
		if (len == R) { // �������� : ������ �ϼ����� ���
			System.out.println(Arrays.toString(array));
			return;
		}

		for (int i = idx; i < N; i++) {
			array[len] = input[i];
			nPr_array(i + 1, len + 1);
		}
	} // end of nPr_array()

	/**
	 * @param idx ���� ���������Ϳ��� �̰����ϴ� �ε���
	 * @param len ���� ���� ������ ��, �� ������ �迭�� �ε���
	 */
	private static void nPr_function1(int idx, int len) {
		if (len == R) {
			System.out.println(Arrays.toString(array));
			return;
		} else if (idx == N) {
			return;
		}

		array[len] = input[idx];
		nPr_function1(idx + 1, len + 1);
		nPr_function1(idx + 1, len);

	} // end of nPr_function1()

	private static void nPr_function2(int n, int r) {
		if (n < r) {
			return;
		} else if (r == 0) { // �ϳ��� ������ �ϼ���
			System.out.println(Arrays.toString(array));
		} else {
			array[r - 1] = input[n - 1];
			nPr_function2(n - 1, r - 1); // ������ ���
			nPr_function2(n - 1, r); // �������� ���� ���
		}
	} // end of nPr_function2()
} // end of class