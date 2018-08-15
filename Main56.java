
import java.util.Random;
public class Main56 {
	public static void main(String[] args) {
		int m=20, n = 30;
		int[][] huanjing = new int[m][n];
		int[][] zhangai = new int[][] {
			{2,2},{3,2},{4,2},{3,3},{2,4},{3,4},{4,4},{2,6},{3,6},{4,6},{4,7},{4,8},{3,8},{2,8},{4,10},{3,10},{2,10},{4,11},{4,12},{4,14},{4,15},{4,16},{3,14},{2,14},{3,16},{2,16}
		};
		int[] start = new int[] {1,1};
		int[] target = new int[] {7,6}; 
		run1(huanjing,zhangai,start,target);
		//run2(huanjing,zhangai,start,target);
	}
	public static void inithuanjing(int[][] huanjing,int[][] zhangai) {
		int m = huanjing.length, n = huanjing[0].length;
		for(int i=0;i<m;i++) {
			huanjing[i][0] = 1;
			huanjing[i][n-1] = 1;
		}
		for(int i=0;i<n;i++) {
			huanjing[0][i] = 1;
			huanjing[m-1][i] = 1;
		}
		for(int[] p:zhangai) huanjing[p[0]][p[1]] = 1;
	}
	public static void run1(int[][] huanjing,int[][] zhangai, int[] start, int target[]) {
		inithuanjing(huanjing, zhangai);
		printhuanjing(huanjing, start, target);
		int m = huanjing.length, n = huanjing[0].length;
		int x = start[0], y = start[1];
		while(true) {
			while(y<n-2&&huanjing[x][y+1]==0) {
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				y++;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
				if(start[0]==target[0]&&start[1]==target[1]) {
					return;
				}
			}
			if(y<n-2&&huanjing[x][y+1]==1) {
				if(y>1&&huanjing[x][y-1]==0) y-=1;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
			}
			while(x<m-2&&huanjing[x+1][y]==0) {
//				try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				x++;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
				if(start[0]==target[0]&&start[1]==target[1]) {
					return;
				}
			}
			if(x<m-2&&huanjing[x+1][y]==1) {
				if(x>1&&huanjing[x-1][y]==0)  x-=2;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
			}
			while(y>1&&huanjing[x][y-1]==0) {
//				try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				y--;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
				if(start[0]==target[0]&&start[1]==target[1]) {
					return;
				}
			}
			if(y>1&&huanjing[x][y-1]==1) {
				if(y<n-2&&huanjing[x][y+1]==0) y+=1;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
			}
			while(x>1&&huanjing[x-1][y]==0){
//				try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				x--;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
				if(start[0]==target[0]&&start[1]==target[1]) {
					return;
				}
			}
			if(x>1&&huanjing[x-1][y]==1) {
				if(x<m-2&&huanjing[x+1][y]==0) x+=2;
				start[0] = x;
				start[1] = y;
				printhuanjing(huanjing, start, target);
			}
		}
	}
	public static void run2(int[][] huanjing,int[][] zhangai, int[] start, int target[]) {
		inithuanjing(huanjing, zhangai);
		printhuanjing(huanjing, start, target);
		int m = huanjing.length;
		int n = huanjing[0].length;
		float[][][] Q = new float[m][n][4];
		float ep = 0.1f;
		while(true) {
			int x = start[0];
			int y = start[1];
			int t = -1;
			int r = 0;
			for(int i=0;i<4;i++) {
				if(t==-1||Q[x][y][i]>Q[x][y][t]) {
					t=i;
				}
			}
			if(Math.random()<ep) t = (int) (Math.random()*4);
			if(t==0&&x<m-2) {
				x++;
				if(huanjing[x][y]==1) r=-1;
				else if(Math.abs(x-target[0])<Math.abs(x-1-target[0])) r = 1;
			}
			if(t==1&&x>1) {
				x--;
				if(huanjing[x][y]==1) r=-1;
				else if(Math.abs(x-target[0])<Math.abs(x+1-target[0])) r = 1;
			}
			if(t==2&&y<n-2) {
				y++;
				if(huanjing[x][y]==1) r=-1;
				else if(Math.abs(y-target[1])<Math.abs(y-1-target[1])) r = 1;
			}
			if(t==0&&y>1) {
				y--;
				if(huanjing[x][y]==1) r=-1;
				else if(Math.abs(y-target[1])<Math.abs(y+1-target[1])) r = 1;
			}
			
			Q[x][y][t] = (float) (Q[x][y][t]+0.5*(r+Q[x][y][0]-Q[x][y][t]));
			start[0] = x;
			start[1] = y;
			printhuanjing(huanjing, start, target);
			if(start[0]==target[0]&&start[1]==target[1]) {
				return;
			}
		}
		
	}
	public static void printhuanjing(int[][] huanjing,int[] pos, int[] target) {
		for(int i=0;i<huanjing.length;i++) {
			for(int j=0;j<huanjing[0].length;j++) {
				if(i==pos[0]&&j==pos[1]) {
					System.out.print("*");
				}
				else if(i==target[0]&&j==target[1]) {
					System.out.print("#");
				}
				else if(huanjing[i][j]==1) System.out.print(huanjing[i][j]);
				else System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
