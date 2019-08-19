package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * [S/W �����ذ� �⺻] 8���� - ��ȣ��1
 */
public class Solution_1228_��ȣ��1 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 10; i++) {
			LinkedList<Integer> ll = new LinkedList<Integer>();
			int N = Integer.parseInt(br.readLine().trim()); // ���� ��ȣ���� ����
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // ���� ��ȣ��
			for (int j = 0; j < N; j++) {
				ll.add(Integer.parseInt(st.nextToken())); // ���� ��ȣ�� �߰�
//				System.out.print(ll.get(j) + " ");
			}

			int cmd_n = Integer.parseInt(br.readLine().trim()); // ��ɾ��� ����
			st = new StringTokenizer(br.readLine(), " "); // ��ɾ�

			for (int j = 0; j < cmd_n; j++) {
//				System.out.println(ll.toString());
				String cmd = st.nextToken(); // ��ɾ�
				int position = Integer.parseInt(st.nextToken()); // �߰��Ǵ� ��ġ
				int num_n = Integer.parseInt(st.nextToken()); // �߰��Ǵ� ��ɾ��� ����
				for (int k = 0; k < num_n; k++) {
					int encode = Integer.parseInt(st.nextToken());
					ll.add(position + k, encode);
				}
			}
			System.out.print("#" + i);
			for (int j = 0; j < 10; j++) {
				System.out.print(" " + ll.get(j));
			}
			System.out.println();
		}
	}
}
