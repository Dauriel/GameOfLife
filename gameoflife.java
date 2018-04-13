import java.util.*;
public class GameOfLife {
	
	private int genCount;
	private int table[][];
	private Scanner sc;
	
	public static void main(String[] args) {
		GameOfLife start = new GameOfLife();
		start.start();
	}
	
	public void start() {
		sc = new Scanner(System.in);
		genCount = 1;
		table = new int[12][12];
		dataInput();
		showTable();
		calc();
		System.out.println("\nNext Gen? (1/0)");
		int input = sc.nextInt();
		while(input == 1) {
			showTable();
			calc();
			System.out.println("\nNext Gen? (1/0)");
			input = sc.nextInt();
		}
	}

	private void showTable() {
		String row;
		System.out.println("\nGen: " + genCount + "\n");
		for(int x=1; x<11;x++) {
			row = "|";
			for(int y=1; y<11;y++) {
				row+= table[x][y] + "|";
			}
			System.out.println(row);
		}
	}

	private void dataInput() {
		Coord input = position();
		while((input.row != -1) && (input.column != -1)) {
			table[input.row][input.column] = 1;
			input = position();
		}
	}

	private Coord position() {
		Coord value = new Coord();
		System.out.println("Row: ");
		value.row = sc.nextInt();
		System.out.println("Column: ");
		value.column = sc.nextInt();
		while(!((value.row <=10 && value.row >= 1) && (value.column <= 10 && value.column >= 1))) {
			if(!((value.row == -1) && (value.column == -1))) {
				System.out.println("Out of bound coord");
				System.out.println("Row: ");
				value.row = sc.nextInt();
				System.out.println("Column: ");
				value.column = sc.nextInt();
			}else {
				break;
			}
		}
		return value;
	}

	class Coord{
		public int row;
		public int column;
	}
	
	private void calc() {
		int aux[][]=new int[12][12];		
		for(int x=1; x<11;x++) {
			for(int y=1; y<11;y++) {
				int sum = neighbourhood(x,y);

				if(sum < 2 || sum > 3) {
					aux[x][y]=0;
				}else if(sum == 3) {
					aux[x][y] = 1;
				}else if(table[x][y] == 1){
					aux[x][y]=1;
				}
			}
		}
		table = aux;
		genCount++;
	}

	private int neighbourhood(int x, int y){
		return table[x-1][y-1]+table[x][y-1]+table[x+1][y-1]+table[x-1][y]+table[x+1][y]+table[x+1][y+1]+table[x-1][y+1]+table[x][y+1];
	}

}
