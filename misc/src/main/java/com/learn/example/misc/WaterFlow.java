package com.learn.example.misc;

public class WaterFlow {
	
	public static void shortestFlowPath(int[][] height, int longitude, int latitude) {
		int xVerticesN = height.length;
		int yVerticesN = height[0].length;
		int[][] shortestDistance = new int[xVerticesN][yVerticesN];
		int[][] shortestPath = new int[xVerticesN][yVerticesN];
		int[][] parentX =  new int[xVerticesN][yVerticesN];
		int[][] parentY =  new int[xVerticesN][yVerticesN];
		boolean[][] visited = new boolean[xVerticesN][yVerticesN];
		
		for(int x = 0; x < xVerticesN; ++x) {
			for(int y = 0; y < yVerticesN; ++y) {
				shortestDistance[x][y] = Integer.MAX_VALUE;
				shortestPath[x][y] = Integer.MAX_VALUE;
				visited[x][y] = false;
				parentX[x][y] = -2;
				parentY[x][y] = -2;
				
			}
		}
		
		shortestPath[longitude][latitude] = 0;
		shortestDistance[longitude][latitude] = 0;
		parentX[longitude][latitude] = -1;
		parentY[longitude][latitude] = -1;
		
		int shortestFlow = Integer.MAX_VALUE;
		int shortestFlowX = -1;
		int shortestFlowY = -1;

		outer: for(int x = 0; x < xVerticesN; ++x) {
			for(int y = 0; y < yVerticesN; ++y) {
				System.out.println("X: " + x + " and  Y: " + y);
				int shortestDist = Integer.MAX_VALUE;
				int shortestVertX = -1;
				int shortestVertY = -1;
				int shortestP = Integer.MAX_VALUE;
				for(int i = 0; i < xVerticesN; ++i) {
					for(int j = 0; j < yVerticesN; ++j) {
						if((j == 0 || j == yVerticesN - 1)) {
							if(shortestPath[i][j] < shortestP) {
								shortestFlow = shortestDistance[i][j];
								shortestFlowX = i;
								shortestFlowY = j;
								shortestP = shortestPath[i][j];
							} else if(shortestPath[i][j] <= shortestP && shortestDistance[i][j] < shortestFlow) {
								shortestFlow = shortestDistance[i][j];
								shortestFlowX = i;
								shortestFlowY = j;
								shortestP = shortestPath[i][j];
							}
						}
						if(!visited[i][j] && shortestDistance[i][j] < shortestDist) {
							shortestDist = shortestDistance[i][j];
							shortestVertX = i; 
							shortestVertY = j;
						}
					}
				}
				
				
				
				if(shortestVertX == -1 || shortestVertY == -1) {
					System.out.println("No move: " + shortestVertX + "," + shortestVertY );
					break outer;
				} else {
					System.out.println("Have a move: " + shortestVertX + "," + shortestVertY );
				}
				visited[shortestVertX][shortestVertY] = true;
				
				if(shortestVertX > 0 && height[shortestVertX - 1][shortestVertY] > 0 &&
						height[shortestVertX - 1][shortestVertY] <= height[shortestVertX][shortestVertY] &&
						shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX - 1][shortestVertY] < 
						shortestDistance[shortestVertX - 1][shortestVertY]) {
					shortestDistance[shortestVertX - 1][shortestVertY] = 
							shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX - 1][shortestVertY];
					shortestPath[shortestVertX - 1][shortestVertY] = shortestPath[shortestVertX][shortestVertY] + 1;
					parentX[shortestVertX - 1][shortestVertY] = shortestVertX;
					parentY[shortestVertX - 1][shortestVertY] = shortestVertY;
				}

				if(shortestVertY > 0 && height[shortestVertX][shortestVertY - 1] > 0 &&
						height[shortestVertX][shortestVertY - 1] <= height[shortestVertX][shortestVertY] &&
						shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX][shortestVertY - 1] < 
						shortestDistance[shortestVertX][shortestVertY - 1]) {
					shortestDistance[shortestVertX][shortestVertY - 1] = 
							shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX][shortestVertY - 1];
					shortestPath[shortestVertX][shortestVertY - 1] = shortestPath[shortestVertX][shortestVertY] + 1;
					parentX[shortestVertX][shortestVertY - 1] = shortestVertX;
					parentY[shortestVertX][shortestVertY - 1] = shortestVertY;
				}

				if(shortestVertX < xVerticesN - 1 && height[shortestVertX + 1][shortestVertY] > 0 &&
						height[shortestVertX + 1][shortestVertY] <= height[shortestVertX][shortestVertY] &&
						shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX + 1][shortestVertY] < 
						shortestDistance[shortestVertX + 1][shortestVertY]) {
					shortestDistance[shortestVertX + 1][shortestVertY] = 
							shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX + 1][shortestVertY];
					shortestPath[shortestVertX + 1][shortestVertY] = shortestPath[shortestVertX][shortestVertY] + 1;
					parentX[shortestVertX + 1][shortestVertY] = shortestVertX;
					parentY[shortestVertX + 1][shortestVertY] = shortestVertY;
				}

				if(shortestVertY < yVerticesN - 1 && height[shortestVertX][shortestVertY + 1] > 0 &&
						height[shortestVertX][shortestVertY + 1] <= height[shortestVertX][shortestVertY] &&
						shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX][shortestVertY + 1] < 
						shortestDistance[shortestVertX][shortestVertY + 1]) {
					shortestDistance[shortestVertX][shortestVertY + 1] = 
							shortestDistance[shortestVertX][shortestVertY] + height[shortestVertX][shortestVertY + 1];
					shortestPath[shortestVertX][shortestVertY + 1] = shortestPath[shortestVertX][shortestVertY] + 1;
					parentX[shortestVertX][shortestVertY + 1] = shortestVertX;
					parentY[shortestVertX][shortestVertY + 1] = shortestVertY;
				}
			}
		}
		
		printSolution(height, shortestDistance, shortestPath, parentX, parentY, longitude, latitude);
		
		if(shortestFlowY != 0 && shortestFlowY != yVerticesN - 1) {
			System.out.println("Water will not reach the shore");
		} else {
			System.out.println("Water will reach: " + shortestFlowX + "," + shortestFlowY);
			System.out.print("Path that will be taken is: ");
			printPath(parentX, parentY, shortestFlowX, shortestFlowY);
		}
		
	}
	
	public static void printSolution(int[][] height, int[][] shortestDistance, int[][] shortestPath, int[][] parentX, int[][] parentY, int longitude, int latitude) {
		for(int x = 0; x < height.length; ++x) {
			for(int y = 0; y < height[0].length; ++y) {
				if(x != longitude || y != latitude) {
					System.out.print(longitude + "," + latitude + " -> " + x + "," + y);
					System.out.print("             " + shortestDistance[x][y] + "                  ");
					System.out.print("Path length: " + shortestPath[x][y] + "             ");
					printPath(parentX, parentY, x, y);
					System.out.println("");
				}
			}
		}
	}
	
	public static void printPath(int[][] parentX, int[][] parentY, int x, int y) {
		if(parentX[x][y] == -2 && parentY[x][y] == -2) {
			System.out.print("No Path");
			return;
		} else if(parentX[x][y] == -1 && parentY[x][y] == -1) {
			System.out.print(x + "," + y + "  ");
			return;
		}
		
		printPath(parentX, parentY, parentX[x][y], parentY[x][y]);
		System.out.print(x + "," + y + "  ");
		
	}
	
	public static void main(String[] args) {
		int[][] height = {	{12,13,1,8},
							{3,20,10,11},
							{5,10,5,1}	};
		shortestFlowPath(height, 1, 2);
	}

}
