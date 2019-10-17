package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1766_������ {
	private static ArrayList<Integer>[] listArray;
	private static int[] indegree;
	private static int N, M;
	private static StringBuilder sb;
	private static PriorityQueue<Integer> pQueue;

	public static void main(String[] args) throws Exception {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ������ ��
		M = Integer.parseInt(st.nextToken()); // ���� ������ ���� ������ ��
		listArray = new ArrayList[N + 1];
		indegree = new int[N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			if (listArray[before] == null) {
				listArray[before] = new ArrayList<Integer>();
			}
			listArray[before].add(after);
			indegree[after]++;
		}
		// -- end of input
		pQueue = new PriorityQueue<Integer>();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) { // ���� Ǯ����ϴ� ������ ���ٸ�
				pQueue.add(i); // �켱 0�ΰ� �� ����
			}
		}

		while (!pQueue.isEmpty()) {
			int before = pQueue.poll();
			sb.append(before).append(" ");
			indegree[before]--;
			if (listArray[before] == null) {
				continue;
			}
			for (int i = 0; i < listArray[before].size(); i++) {
				int after = listArray[before].get(i);
				indegree[after]--;
				if (indegree[after] == 0) {
					pQueue.add(after);
				}
			}
		}
		System.out.println(sb.toString());
	} // end of main

}
