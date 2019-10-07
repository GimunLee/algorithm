package programmers.level2;

public class Solution_�踷��� {
	public static void main(String[] args) {
		String arrangement = "()(((()())(())()))(())";
		System.out.println(solution(arrangement));
	}

	private static int solution(String arrangement) {
		int answer = 0;
		int open = 0;
		for (int i = 0; i < arrangement.length(); i++) {
			char c = arrangement.charAt(i);
			if (c == '(') {
				open++;
			} else if (arrangement.charAt(i - 1) == '(') { // �������϶�,
				open--;
				answer += open;
			} else {
				answer++;
				open--;
			}
		}
		return answer;
	}
}
