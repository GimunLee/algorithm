package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * �ڱ� ������ ���ư���
 */
public class Solution_4408_�ڱ�����ε��ư��� {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim()); // T : �׽�Ʈ���̽� ��

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N : ���ư��� �� �л����� ��

			int[] room = new int[401];

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken()); // ���� ��
				int end = Integer.parseInt(st.nextToken()); // ���ư� ��

				room[start] = i;
				room[end] = i;
			}

//			System.out.println(Arrays.toString(room));
			
			int ans = 1;
			
			for (int i = 1; i < 401; i++) {
				if(room[i] !=0) { // ������ �л��� ������
//					System.out.println(room[i]+"��° �л�");
					for (int j = i+1; j < 401; j++) {
						if(room[j] != 0) {
							if(room[i] == room[j]) { // ���� ����
//								System.out.println("if : "+room[i] + " " + room[j]);
								room[i] = 0;
								room[j] = 0;
								break;
								
							}else{
//								System.out.println("else : " +room[i] + " " + room[j]);
								if(room[j] == N) {
									break;
								}
								ans++;
								continue;
							}
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}

	}
}
