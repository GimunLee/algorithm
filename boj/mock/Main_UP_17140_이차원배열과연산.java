package boj.mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_UP_17140_�������迭������ {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// A[r][c] = k �� ���� �ð����ϱ�
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] A = new int[100][100]; // 100�� �Ѿ �� ����

		for (int rr = 0; rr < 3; rr++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int cc = 0; cc < 3; cc++) {
				A[rr][cc] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		int size_r = 3; // �ʱ��� row : 3
		int size_c = 3; // �ʱ��� column : 3

		for (int time = 0; time <= 100; time++) { // 100�ð��� �Ѿ�� -1 ���
			if (A[r - 1][c - 1] == k) { // ó������ ���� �� �����Ƿ�, �� ó�� Ȯ������
				System.out.println(time); // ���� ��� �� ����
				return;
			}
			
			int size = Integer.MIN_VALUE; // �� R,C ���� �� ���� �� �ִ� �ִ� ����
			int[][] A_tmp; // ���� �������� �ӽ� A ����
			ArrayList<Pair> list; // �� R,C ���꿡�� ���� ������ ������ ����
			
			if (size_r >= size_c) { // R ����
				A_tmp = new int[size_r][size_c * 2]; // R �����̹Ƿ�, ���� ������ ���� ���� �ִ� ({��}*2)ĭ�� �ʿ�
				for (int rr = 0; rr < size_r; rr++) {
					list = new ArrayList<>(); // �� �ະ�� list ����
					int[] chk = new int[101]; // list�� �̹� �ִ� ������, indexing���� �ӵ� ����ȭ�Ͽ� Ž��
					int idx = 1; // ó������ ������ 1�� �������
					for (int cc = 0; cc < size_c; cc++) {
						int val = A[rr][cc]; // A���� �� ��������
						if (val == 0) { // val�� 0�̸� ������ ���� ����
							continue;
						}
						if (chk[val] == 0) { // chk �迭�� Ȯ�� ��, ó�� ���� ���
							list.add(new Pair(val, 1)); // list�� �־���
							chk[val] = idx++; // Ȯ�� �迭�� list�� ���°�� ���ִ��� ǥ��
							
						} else { // chk �迭�� Ȯ�� ��, �̹� �ִ� ���� ���
							list.get(chk[val] - 1).cnt++; // list�� �ִ� ������ �÷���
						}
					} // end of for(column)
					
					size = size < (idx - 1) ? idx - 1 : size; // �������� ���� ������ ��������
					Collections.sort(list); // ������ ����
					
					//-- �ӽ� A ������ ä����
					int temp_c = 0; 
					for (int i = 0; i < list.size(); i++) {
						A_tmp[rr][temp_c++] = list.get(i).val;
						A_tmp[rr][temp_c++] = list.get(i).cnt;
					}
				} // end of for(row)
				size_c = size * 2; // ��� R ������ ���� ��, column�� ���̸� ��������
			} // end of if(R����)
			
			else { // C ���� (R ����� Ž�� ������ �ٸ�)
				A_tmp = new int[size_r * 2][size_c]; // C �����̹Ƿ�, ���� ������ ���� ���� �ִ� ({��}*2)ĭ�� �ʿ�
				for (int cc = 0; cc < size_c; cc++) {
					list = new ArrayList<>();
					int[] chk = new int[101];
					int idx = 1;
					for (int rr = 0; rr < size_r; rr++) {
						int val = A[rr][cc];
						if (val == 0) {
							continue;
						}
						if (chk[val] == 0) { 
							list.add(new Pair(val, 1));
							chk[val] = idx++;
							
						} else { 
							list.get(chk[val] - 1).cnt++;
						}
					} // end of for(row)
					
					size = size < (idx - 1) ? idx - 1 : size; 
					Collections.sort(list);
					
					int temp_r = 0;
					for (int i = 0; i < list.size(); i++) {
						A_tmp[temp_r++][cc] = list.get(i).val;
						A_tmp[temp_r++][cc] = list.get(i).cnt;
					}
				}
				size_r = size * 2;
			} // end of if(C����)

			for (int rr = 0; rr < size_r; rr++) {
				for (int cc = 0; cc < size_c; cc++) {
					A[rr][cc] = A_tmp[rr][cc];
				}
			}
		} // end of for(one time)
		System.out.println("-1"); // 100 �ð����� ���� ������ ���� ���
	} // end of main

	private static class Pair implements Comparable<Pair> {
		int val;
		int cnt;

		Pair(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair o) {
			int tmp = this.cnt - o.cnt;
			if (tmp == 0) { // ������ ���� ��, ������ ����
				tmp = this.val - o.val;
			}
			return tmp;
		}
	} // end of Pair
} // end of class
