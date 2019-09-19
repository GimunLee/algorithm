package programmers;

import java.util.HashMap;

public class Solution_�����������Ѽ���_lib {
	public static void main(String[] args) {
		String[] participant = { "a", "b", "b", "c", "d", "d" };
		String[] completion = { "a", "b", "c", "d", "d" };

		solution(participant, completion);
	}

	public static String solution(String[] participant, String[] completion) {
		String ANSWER = "";
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < completion.length; i++) {
			String s = completion[i];
			if (hashMap.get(s) == null) { // ���°�aa
				hashMap.put(s, 1);
			} else {
				int tmp = hashMap.get(s);
				hashMap.put(s, tmp + 1);
			}
		}
		
		for (int i = 0; i < participant.length; i++) {
			String s = participant[i];
			if (hashMap.get(s) == null || hashMap.get(s) == 0) { // ���°�
				ANSWER = s;
				break;
			} else {
				int tmp = hashMap.get(s);
				hashMap.put(s, tmp - 1);
			}
		}
		return ANSWER;
	}
}
