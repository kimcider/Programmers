package TicTacTo;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/160585?language=java
 */

public class Solution {
	int OCounter;
	int XCounter;
	
	boolean OTictacto;
	boolean XTictacto;
	
	public int solution(String[] board) {
		
		/*
		 * 완전 다시짜보자
		 * 
		 * 나올 수 없는 경우의 수 나열
		 * 
		 * 1. X의 갯수가 O의 갯수보다 많을 경우
		 * 		O의 순서가 X의 순서보다 빠르기 때
		 * 2. O의 갯수가 X의 갯수보다 2개 이상 많을경우
		 * 		O와 X가 번갈아가며 두기 때
		 * 3. O틱택토와 X틱택토가 동시에 되는 경우
		 * 		한 사람이 틱택토가 되면 게임이 끝나기 때
		 * 4. O틱택토인데 O와 X의 갯수가 같은 경우
		 * 		O틱택토로 끝났을 경우, O가 X보다 하나 더 많아야함.
		 * 5. X틱택토인데 O의 갯수가 X보다 많은 경우
		 * 		
		 */
		
		OXCounter(board);
		// 1. X의 갯수가 O의 갯수보다 많을 경우
		if(XCounter > OCounter) return 0;
		// 2. O의 갯수가 X의 갯수보다 2개 이상 많을경우
		if(OCounter > XCounter + 1) return 0;
		
		int numberOfTictackto = tictactoCalculator(board);
		if(OTictacto == true && XTictacto == true) {
			return 0;
		}
		if(OTictacto == true) {
			if(OCounter == XCounter + 1) {
				return 1;
			}else return 0;
		}
		if(XTictacto == true) {
			if(OCounter == XCounter) {
				return 1;
			}else return 0;
		}
		
		return 1;
    }
	
	public int tictactoCalculator(String[] board) {
		int counter = 0;
		
		int OCounter = 0;
		int XCounter = 0;
		
		boolean OChecker = true;
		boolean XChecker = true;
		
		for(int i = 0; i < 3; i++) {
			OChecker = true;
			XChecker = true;
			for(int j = 0; j < 3; j++) {
				if(board[i].charAt(j) != 'O'){
					OChecker = false;
				}
				if(board[i].charAt(j) != 'X') {
					XChecker = false;
				}
			}
			if(OChecker == true) {
				OCounter++;
			}
			if(XChecker == true) {
				XCounter ++;
			}
			OChecker = true;
			XChecker = true;
			for(int j = 0; j < 3; j++) {
				if(board[j].charAt(i) != 'O'){
					OChecker = false;
				}
				if(board[j].charAt(i) != 'X') {
					XChecker = false;
				}
			}
			if(OChecker == true) {
				OCounter++;
			}
			if(XChecker == true) {
				XCounter ++;
			}
		}
		
		OChecker = true;
		XChecker = true;
		for(int i = 0; i < 3; i++) {
			if(board[i].charAt(i) != 'O') {
				OChecker = false;
			}
			if(board[i].charAt(i) != 'X') {
				XChecker = false;
			}
		}
		if(OChecker == true) {
			OCounter++;
		}
		if(XChecker == true) {
			XCounter ++;
		}
		
		OChecker = true;
		XChecker = true;
		for(int i = 0; i < 3; i++) {
			if(board[i].charAt(2-i) != 'O') {
				OChecker = false;
			}
			if(board[i].charAt(2-i) != 'X') {
				XChecker = false;
			}
		}
		if(OChecker == true) {
			OCounter++;
		}
		if(XChecker == true) {
			XCounter ++;
		}
		
		counter = OCounter + XCounter;
		
		if(OCounter > 0) {
			OTictacto = true;
		}
		if(XCounter > 0) {
			XTictacto = true;
		}
		return counter;
	}
	
	public void OXCounter(String[] board) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i].charAt(j) == 'O') {
					OCounter++;
				}
				if(board[i].charAt(j) == 'X') {
					XCounter++;
				}
			}
		}
	}
}
