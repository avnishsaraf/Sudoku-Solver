

public class main {

	public static void main(String [] args){



	}

	public  SudokuCell[][] solve(SudokuCell[][] sudoku){
		int minI, minJ, minLen;
		minI = minJ = minLen = Integer.MAX_VALUE;
		for (int i = 0; i < 9; ++i){
			for (int j = 0; j < 9; ++j){
				if(sudoku[i][j].getNumPossibilities() < minLen & sudoku[i][j].getNumPossibilities() > 0){
					minI = i;
					minJ = j;
					minLen = sudoku[i][j].getNumPossibilities();
				}
			}
		}
		
		if (minLen == Integer.MAX_VALUE){
			return sudoku;
		}
		
		for(int i = 0; i < minLen; ++i){
			SudokuCell[][] temp = setNum(sudoku, sudoku[minI][minJ].getPossibility(),minI,minJ);
			if(null != temp){
				return solve(temp);
			}
		}
		return null;
	}
	
	public SudokuCell[][] setNum(SudokuCell[][] sudoku, int num, int i, int j){
		sudoku[i][j].setCellVal(num);
		return updateSudoku(sudoku, num, i, j);
	}
	
	public SudokuCell[][] updateSudoku(SudokuCell[][] sudoku, int num, int i, int j){
		for(int l = 0; l < 9; ++l){
			if(l != i && !sudoku[l][j].updateCellPossiblities(num)){
				return null;
			}
		}
		
		for(int m = 0; m < 9; ++m){
			if(m != j && !sudoku[i][m].updateCellPossiblities(num)){
				return null;
			}
		}
		
		for(int l = (i/3)*3; l < (i/3)*3 + 3; ++l){
			for(int m = (j/3)*3; m < (j/3)*3 + 3; ++m){
				if(l != i && m !=j && !sudoku[l][m].updateCellPossiblities(num)){
					return null;
				}
			}
		}
		
		return sudoku;
	}

}
