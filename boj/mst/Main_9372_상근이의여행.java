package boj.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_9372_������ǿ��� {
	private static int N, M;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // ������ ��
			M = Integer.parseInt(st.nextToken()); // ������� ����

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

			}
			sb.append(N - 1).append("\n");
		} // end of TestCase
		System.out.println(sb.toString());

	} // end of main

} // end of Class
