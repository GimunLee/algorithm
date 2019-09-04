package boj.mock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_UP_17281_�߱� {
	private static int N; // �̴� ��
	private static int[][] hitter; // idx : Ÿ�� ��ȣ // [n][x] : n�̴� �� x�� ������ ģ ���

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // �̴� ��
		hitter = new int[N][9]; // �̴�, Ÿ�� ����ŭ ����

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < hitter[i].length; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				hitter[i][j] = tmp;
			}
		} // end of for(input)

		set[3] = 0; // 4�� Ÿ�ڴ� 1�� Ÿ�ڷ� ����
		setHitter(0); // Ÿ���� ������ �����ϰ� ���� ����
		System.out.println(ans); // ���� ���
	} // end of main

	private static int[] set = new int[9]; // Ÿ�� ���� ����
	private static boolean[] isSet = new boolean[9]; // ������ �̱� ���� ����
	private static int ans = 0; // ���� ����

	/** 9���� Ÿ���� ������ �����ϴ� ���� �Լ� */
	private static void setHitter(int len) {
		if (len == 3) { // 4�� Ÿ�ڴ� 1�� Ÿ�ڷ� �����̹Ƿ�, �ٷ� �������� Ÿ�ڷ� �ѱ�ϴ�.
			setHitter(len + 1);
			return;
		}
		
		if (len == 9) { // 9���� Ÿ���� ������ ��� ������ ��
			int tmp_ans = 0; // �ӽ� ���� 
			int nHitter = 0; // ���� Ÿ�ڸ� ��� �ִ� set �迭�� �ε���

			// ���� ����
			here: for (int i = 0; i < N; i++) {
				int out_cnt = 0; // �� ���Ӵ� out ���� ����
				boolean[] base = new boolean[4]; // 1: 1��, 2: 2��, 3: 3��
				
				while (true) { // �����ƿ��� �� ������ ���� ����
					for (int j = nHitter; j < set.length; j++) { // Ÿ�� ����ŭ �ݺ�
						nHitter = (j + 1 == 9) ? 0 : j + 1; // ���� �̴� ��, ĥ ������ Ÿ�� ������ ����  
						int state = hitter[i][set[j]]; // �ش� Ÿ���� �ش� �̴� ���� ģ ��
						if (state == 4) { // Ȩ���� ��, 1�� ���ϱ�
							tmp_ans++;
						}

						if (state != 0) { // Ÿ�ڰ� �ƴٸ�
							for (int k = base.length - 1; k > 0; k--) { // �������� ���̽� Ȯ���ϸ� ģ ����ŭ �����ֱ� (���� ��Ȳ)
								if (base[k]) { // ���̽��� ���ڰ� �ִٸ�
									if (k + state >= 4) { // ����
										tmp_ans++;
										base[k] = false; // �ش� ���̽��� �������
									} else { // �������� ���� ��� ����
										base[k + state] = true;
										base[k] = false;
									}
								}
							}
							if (state < 4) { // ���̽��� �ִ� ������ �����ϰ�, ���� Ÿ�� ����
								base[state] = true;
							}
						} else { // Ÿ�ڰ� ġ�� ���ߴٸ�
							out_cnt++; // �ƿ� ī��Ʈ ����
							if (out_cnt >= 3) { // �����ƿ��̸� 
								continue here; // ���� �̴����� ������.
							}
						}
					}
				} // end of while(until three out)
			} // end of for(inning)
			ans = tmp_ans > ans ? tmp_ans : ans; // ���� ����
			return;
		}
		
		for (int i = 1; i < set.length; i++) {
			if (!isSet[i]) { // ���� ���� Ÿ�ڶ��
				set[len] = i; // set�� �ش� Ÿ�� ��ȣ ����
				isSet[i] = true; // ���� ���� ǥ��
				setHitter(len + 1); // ���� Ÿ�ڷ� ��� ȣ��
				isSet[i] = false; // �ٽ� ���� �� �ֵ��� false ó��
			}
		}
	} // end of setHitter
} // end of class
