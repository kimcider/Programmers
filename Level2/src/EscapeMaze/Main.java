package EscapeMaze;

public class Main {
	public static void main(String[]args) {
		
		Solution2 solution = new Solution2();
		
//		String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		String[] maps = {"SEOOL","XXXXO","OOOOO","OXXXX","OOOOO"};
//		Solution solution = new Solution();
//		int answer = solution.solution(maps);
//		System.out.println(answer);
		
		System.out.println(solution.solution(maps));
	}
}
