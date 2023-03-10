package UninhabitedIsland;


/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/154540
 */

/*
 * 유사문제: EscapeMaze
 */

/*
 * DFS를 사용
 * 한칸씩 돌아가면서 DFS를 진행.
 * 붙어있는 칸의 경우 DFS로 해결되기에 다음에 차례가 오면 DFS를 수행하지 않게됨. 
 */


import java.util.PriorityQueue;
import java.util.Stack;
class Solution {
	char[][] maps;
	boolean[][] visited;
	int maxRow;
	int maxCol;
	
	Point[] nearOffset;
	
	int[] answer;
	PriorityQueue<Integer> priorityQueue;
    
	
	
	public int[] solution(String[] maps) {
        init(maps);
        
        for(int i = 0; i < maxRow; i++) {
        	for(int j = 0; j < maxCol; j++) {
        		BFS(i, j);
        	}
        }
        
        int size = priorityQueue.size();
        
        if(size == 0) {
        	return new int[]{-1};
        }
        answer = new int[size];
        for(int i = 0; i < size; i++) {
        	answer[i] = priorityQueue.poll();
        }
        return answer;
    }
    
    public void init(String[] maps) {
    	maxRow = maps.length;
        maxCol = maps[0].length();
        
        this.maps = new char[maxRow][maxCol];
        this.visited = new boolean[maxRow][maxCol];
        for(int i = 0; i < maxRow; i++) {
        	for(int j = 0; j < maxCol; j++) {
        		this.maps[i][j] = maps[i].charAt(j);
        		
        		if(this.maps[i][j] == 'X') visited[i][j] = true;
        		else visited[i][j] = false;
        	}
        }
        
        priorityQueue = new PriorityQueue<>();
        
        nearOffset = new Point[4];
        nearOffset[0] = new Point(1, 0);
        nearOffset[1] = new Point(-1, 0);
        nearOffset[2] = new Point(0, 1);
        nearOffset[3] = new Point(0, -1);
    }

    public void BFS(int row, int col) {
    	Point start = new Point(row, col);
    	int totalFood = 0;
    	
    	Stack<Point> stack = new Stack<>();
    	
    	if(visited[start.getRow()][start.getCol()] == true) {
    		return;
    	}
    	
    	visited[start.getRow()][start.getCol()] = true;
    	stack.push(start);
    	while(stack.isEmpty() == false) {
    		Point temp = stack.pop();
    		totalFood += maps[temp.getRow()][temp.getCol()] - '0';
    		
    		for(int i = 0; i < 4; i++) {
    			Point near = new Point(temp.getRow() + nearOffset[i].getRow(), temp.getCol() + nearOffset[i].getCol());
    			if(near.checkBounds() && visited[near.getRow()][near.getCol()] == false) {
    				visited[near.getRow()][near.getCol()] = true;
    				stack.push(near);
    			}
    		}
    	}
    	
    	priorityQueue.add(totalFood);
    }
    
    class Point{
    	int row;
    	int col;
    	
    	public Point(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    	public int getRow() {return row;}
    	public int getCol() {return col;}
    	
    	public boolean checkBounds() {
    		if(row < 0) return false;
    		if(col < 0) return false;
    		if(row >= maxRow) return false;
    		if(col >= maxCol) return false;
    		return true;
    	}
    }

}