import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Puzzle {
	ArrayList<int[]> rows = new ArrayList<int[]>();
	
	// The sudoku puzzle has 9 internal grids, number 1, 2 & 3 on the top row, number 4, 5 & 6 middle row and 7, 8 & 9 top row. 
	// Create hashmap with Key as the grid number, pointing to an array of lowestColumnNum & lowestRowNum
	// 1 => [0, 0]; 2 => [3, 0]
	Map<Integer, Integer[]> gridCoordinates = new HashMap<Integer, Integer[]>();
	
	Puzzle(){
		// Create 9 arrays (rows) of size 9 (columns).
		for (int i = 0; i < 9; i++ )
			rows.add(new int[9]);
		
		gridCoordinates.put(1, new Integer[] {0, 0});	// Internal grid 1
		gridCoordinates.put(2, new Integer[] {3, 0});	// Internal grid 2
		gridCoordinates.put(3, new Integer[] {6, 0});	// Internal grid 3
		gridCoordinates.put(4, new Integer[] {0, 3});	// Internal grid 4
		gridCoordinates.put(5, new Integer[] {3, 3});	// Internal grid 5
		gridCoordinates.put(6, new Integer[] {6, 3});	// Internal grid 6
		gridCoordinates.put(7, new Integer[] {0, 6});	// Internal grid 7
		gridCoordinates.put(8, new Integer[] {3, 6});	// Internal grid 8
		gridCoordinates.put(9, new Integer[] {6, 6});	// Internal grid 9
		
	}
	
	public int getCell(int columnNum, int rowNum){
		int[] row = rows.get(rowNum);	// Get the row array in question
		return row[columnNum]; 
	}
	
	public void setCell(int columnNum, int rowNum, int value) throws Exception
	{
		// Check if the value is between 1 and 9.
		if(value < 1 || value > 9)
			throw new Exception("Rule violation: Value being added to cell is not in the range of 1-9.");
		
		int[] row = rows.get(rowNum);	// Get the row array in question
		
		if(row[columnNum] != 0)
			throw new Exception("Rule violation: Cell already contains a value. Column ("+ columnNum +"), Row ("+ rowNum +").");
		
		if(checkRow(rowNum, value) == false)
			throw new Exception("Rule violation: Row already contains a value. Column ("+ columnNum +"), Row ("+ rowNum +").");
		
		if(checkColumn(columnNum, value) == false)
			throw new Exception("Rule violation: Column already contains a value. Column ("+ columnNum +"), Row ("+ rowNum +").");
		
		if(checkGrid(columnNum, rowNum, value) == false)
			throw new Exception("Rule violation: Grid already contains a value. Column ("+ columnNum +"), Row ("+ rowNum +").");
		
		row[columnNum] = value;
	}
	
	public boolean checkRow(int rowNum, int value)
	{
		// Checks that the value does not exist in the row.  False means it is already present.
		int[] row = rows.get(rowNum);
		
		for(int i = 0; i < 9; i++)
			if(row[i] == value)
				return false;
		
		return true;
	}
	
	public boolean checkColumn(int columnNum, int value)
	{
		// Checks that the value does not exist in the column.  False means it is already present.
		for(int i = 0; i < 9; i++)
		{
			int[] row = rows.get(i);
			
			if(row[columnNum] == value)
				return false;
		}
		
		return true;
	}
	
	public Integer[] getGridCoordinates(int gridNumber){
		Integer[] gridCoordinate = gridCoordinates.get(gridNumber);
		return gridCoordinate;
	}
	
	public int getGridNum(int columnNum, int rowNum){
		int gridNum = 0;
		
		if(columnNum <= 2) {
			if(rowNum <=2) {
				gridNum = 1;
			} else if(rowNum >= 3 & rowNum <= 5) {
				gridNum = 4;
			} else {
				gridNum = 7;
			}
		} else if(columnNum >= 3 & columnNum <= 5) {
			if(rowNum <=2) {
				gridNum = 2;
			} else if(rowNum >= 3 & rowNum <= 5) {
				gridNum = 5;
			} else {
				gridNum = 8;
			}
		} else {
			if(rowNum <=2) {
				gridNum = 3;
			} else if(rowNum >= 3 & rowNum <= 5) {
				gridNum = 6;
			} else {
				gridNum = 9;
			}
		}
		
		return gridNum;
	}
	
	public boolean checkGrid(int columnNum, int rowNum, int value)
	{
		// Checks whether the grid number entered contains the value.	
		Integer[] gridCorner = gridCoordinates.get(getGridNum(columnNum, rowNum));
				
		for(int i = gridCorner[1]; i < gridCorner[1] + 3; i++) {
			int[] row = rows.get(i);
			for(int j = gridCorner[0]; j < gridCorner[0] + 3; j++) {
				if(row[j] == value)
					return false;
			}
		}
		
		return true;
	}
	
	public void printPuzzle(){
		for (int i = 0; i < 9; i++ )
			System.out.println(Arrays.toString(rows.get(i)));
	}

}
