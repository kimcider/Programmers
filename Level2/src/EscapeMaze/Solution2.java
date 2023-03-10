package EscapeMaze;

/*
 * String[] maps를 그대로 이용하지 않고, 이를 char[][] 혹은 int[][]로 변환해서 사용. 
 * visit여부도 boolean[][]를 만들어서 사용.
 * 
 * 상하좌우 찾을 때 offset[][]을 만들어서 for문으로 한번에 해결하기. 
 */

import java.util.Queue;
import java.util.LinkedList;
public class Solution2 {
	Point[] offset;
	char[][] maps;
	boolean[][] visited;
	int maxRow;
	int maxCol;
	
	public int solution(String[] maps) {
		offset = new Point[4];
		offset[0] = new Point(-1 , 0);
		offset[1] = new Point(1 , 0);
		offset[2] = new Point(0 , -1);
		offset[3] = new Point(0 , 1);
		
		maxRow = maps.length;
		maxCol = maps[0].length();
		
		Point startPoint = null;
		Point leverPoint = null;
		Point endPoint = null;
		
		this.maps = new char[maxRow][maxCol];
		this.visited = new boolean[maxRow][maxCol];
		
		for(int i = 0; i < maxRow; i++) {
			for(int j = 0; j < maxCol; j++) {
				this.maps[i][j] = maps[i].charAt(j);
				switch(this.maps[i][j]) {
				case 'S':
					startPoint = new Point(i, j);
					break;
				case 'L':
					leverPoint = new Point(i, j);
					break;
				case 'E':
					endPoint = new Point(i, j);
					break;
				}
				
				if(this.maps[i][j] == 'X') {
					visited[i][j] = true;
				}else {
					visited[i][j] = false;;
				}
			}
		}
		
		int startToLever = BFS(startPoint, leverPoint);
		int leverToEnd = BFS(leverPoint, endPoint);
		
		System.out.println("toLever: "+startToLever + ", toEnd: " + leverToEnd);
		for(int i = 0; i < maxRow; i++) {
			for(int j = 0; j < maxCol; j++) {
				if(visited[i][j] == true)System.out.print("X");
				else if(visited[i][j] == false)System.out.print("O");
			}
			System.out.println();
		}
		if(startToLever == -1) return -1;
		if(leverToEnd == -1) return -1;
		return startToLever + leverToEnd;
	}
	
	public int BFS(Point start, Point end) {
		Queue<Point> queue = new LinkedList<>();
//		boolean[][] tempVisit = visited.clone();
		/*
		 * 클론이 뭐지 ?
		 * 
		 */
		boolean[][] tempVisit = new boolean[maxRow][maxCol];
		for(int i = 0; i < maxRow; i++) {
			for(int j = 0; j < maxCol; j++) {
				tempVisit[i][j] = visited[i][j];
			}
		}
		queue.add(start);
		tempVisit[start.row][start.col] = true;

		while(queue.isEmpty() == false) {
			Point tempPoint = queue.poll();
			
			if(tempPoint.equals(end)) {
				return tempPoint.counter;
			}
			
			for(int i = 0; i < 4; i++) {
				Point nearPoint = new Point(tempPoint.row + offset[i].row, tempPoint.col + offset[i].col);
				if(nearPoint.checkBounds() == true && tempVisit[nearPoint.row][nearPoint.col] == false) {
					
					tempVisit[nearPoint.row][nearPoint.col] = true;
					nearPoint.setCounter(tempPoint.counter + 1);
					queue.add(nearPoint);
				}
			}
		}
		return -1;
	}
	
	class Point{
		int row;
		int col;
		int counter;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
			counter = 0;
		}
		
		public void plus(Point other) {
			row = row + other.row;
			col = col + other.col;
		}
		public void setCounter(int n) {
			counter = n;
		}
		public boolean equals(Point other) {
			if(row != other.row) return false;
			if(col != other.col) return false;
			return true;
		}
		
		public boolean checkBounds() {
			if(row < 0) return false;
			if(col < 0) return false;
			if(row >= maxRow) return false;
			if(col >= maxCol) return false;
			return true;
		}
	}
}

