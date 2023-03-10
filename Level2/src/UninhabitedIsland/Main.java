package UninhabitedIsland;

public class Main {
	public static void main(String[]args) {
		Solution sol = new Solution();
		
		String[] str1 = {"X591X","X1X5X","X231X", "1XXX1"};
		print(sol.solution(str1));
	}
	
	private static void print(int[] answer) {
		System.out.print("[");
		for(int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]);
			if(i != answer.length - 1) System.out.print(", ");
		}
		System.out.print("]");
	}
}
