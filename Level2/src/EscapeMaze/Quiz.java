package EscapeMaze;

public class Quiz {
	public static void main(String[]args) {
		Solution solution = new Solution();
		String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		int answer = solution.solution(maps);
	}
}
