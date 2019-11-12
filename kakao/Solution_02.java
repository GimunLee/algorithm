package kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_02 {
	public static void main(String[] args) {
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";

		int[] answer = solution(s);
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

	public static int[] solution(String s) {
		ArrayList<ArrayList<Integer>> all_list = parsing(s); // �Է°� �Ľ�

		// �� ����Ʈ ������ ������ ����
		Collections.sort(all_list, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.size() - o2.size();
			}
		});

		boolean[] chk = new boolean[100_001]; // �̹� �־����� Ȯ���ϴ� ����

		int[] answer = new int[all_list.size()];

		for (int i = 0; i < all_list.size(); i++) {
			for (int j = 0; j < all_list.get(i).size(); j++) {
				if (!chk[all_list.get(i).get(j)]) {
					answer[i] = all_list.get(i).get(j);
					chk[all_list.get(i).get(j)] = true;
				}
			}
		}
		return answer;
	} // end of func(solution)

	public static ArrayList<ArrayList<Integer>> parsing(String s) {
		ArrayList<ArrayList<Integer>> all_list = new ArrayList<ArrayList<Integer>>();
		String[] parsed_s = s.substring(2, s.length() - 2).split("\\},\\{"); // �� �� �����, �߰� �����ڷ� �Ľ�
		for (int i = 0; i < parsed_s.length; i++) {
			ArrayList<Integer> each_list = new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(parsed_s[i], ",");
			while (st.hasMoreElements()) { // ���Ұ� �� ������
				each_list.add(Integer.parseInt(st.nextToken())); // ���� �ֱ�
			}
			all_list.add(each_list); // �� ���� ����Ʈ�� ��ü ����Ʈ�� ����
		}
		return all_list;
	} // end of func(parsing)
} // end of Solution
