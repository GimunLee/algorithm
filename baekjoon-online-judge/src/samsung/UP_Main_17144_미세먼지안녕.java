package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Main_17144_�̼������ȳ� {
	private static final int[] dr = { -1, 0, 1, 0 }; // �̼����� Ȯ�� ���� (����)
	private static final int[] dc = { 0, 1, 0, -1 }; // �̼����� Ȯ�� ���� (����)
	// ����û����� ȸ����Ű�� ����
	// 0 : ���� ����û����(�ݽð�) / 1 : �Ʒ��� ����û����(�ð�)
	private static final int[][][] move = { 
			{ { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } },
			{ { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } } }; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken()); // map�� ��
		int C = Integer.parseInt(st.nextToken()); // map�� ��
		int T = Integer.parseInt(st.nextToken()); // T�ʰ� ����

		int[][] map = new int[R][C]; // map ����
		int[] airCleaner = new int[2]; // ����û���� row ��ǥ / col�� 0���� ����
		int airCleaner_idx = 0; // ����û���� ���� �����ϱ� ���� index
		int dustSum = 0; // ������ �ѹ��� ���� ���� �̼����� �ѷ�

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); // �� ����
				if (map[r][c] == -1) { // ����û������ ��
					airCleaner[airCleaner_idx++] = r;
				} else if (map[r][c] != 0) { // �̼������� ��
					dustSum += map[r][c]; // �̼����� �ѷ�
				}
			}
		} // end of for(input)

		int[][] map_temp; // �̼������� Ȯ���ϰų� ȸ����ų�� ������ �ӽ� map
		
		for (int t = 1; t <= T; t++) { // T�ʸ�ŭ ����
			map_temp = new int[R][C]; // ���ʸ��� map_temp �����
			map_temp[airCleaner[0]][0] = -1; // map_temp�� ����û���� ��ġ ����
			map_temp[airCleaner[1]][0] = -1; // map_temp�� ����û���� ��ġ ����
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) { // �̼������� ��,
						int spread_cnt = 0; // Ȯ��� �����
						int spread_mass = 0; // Ȯ��Ǵ� ��
						
						for (int i = 0; i < dr.length; i++) { // 4�������� �̼����� Ȯ��
							int nR = r + dr[i];
							int nC = c + dc[i];

							if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // ������ ��� ��,
								continue;
							}
							
							if (map[nR][nC] == -1) { // Ȯ��� �� ���� �� (����û����)
								continue;
							}
							
							spread_cnt++; // Ȯ��� �����
							spread_mass = map[r][c] / 5; // Ȯ��Ǵ� �� : map[r][c]/5
							dustSum += spread_mass; // Ȯ��� ��ŭ dustSum�� ���ϱ�
							map_temp[nR][nC] += spread_mass; // map_temp�� ǥ��
						}
						// Ȯ��ǰ� ���� �� : map[r][c] - (map[r][c]/5)*(Ȯ��� �����)
						map_temp[r][c] += (map[r][c] - (spread_cnt * spread_mass));
						dustSum -= (spread_cnt * spread_mass);
					}
				}
			} // end of for(spreading)

			// Ȯ��� ������ �����ִ� map_temp�� map�� ����
			for (int i = 0; i < R; i++) {
				map[i] = Arrays.copyOf(map_temp[i], C);
			}

			// ����û�� ����
			for (int i = 0; i < 2; i++) { // ��, �Ʒ� ����û�� 
				int cR = airCleaner[i]; // ����û���� ��ġ���� ȸ�� ����
				int cC = 0;
				
				for (int j = 0; j < 4; j++) { // 4�������� ȸ��
					while (true) {
						int nR = cR + move[i][j][0]; 
						int nC = cC + move[i][j][1];

						if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // ������ ����� �ٸ� �������� �����
							break;
						}

						if (map[nR][nC] == -1) { // ����û����� dustSum���� ����
							dustSum -= map_temp[cR][cC];
							break;
						}

						if (map[cR][cC] == -1) { // ����û���⿡�� �����ϴ� ���� 0���� Ȯ��
							map[nR][nC] = 0;
						} else { // map_temp�� ���� ��ġ ���� map ���� ���� ����
							map[nR][nC] = map_temp[cR][cC]; 
						}
						// ȸ����Ű��
						cR = nR; 
						cC = nC;
					}
				}
			} // end of cleaning for(one time)
		} // end of for(time)
		System.out.println(dustSum); // �� ���
	} // end of main
} // end of class
