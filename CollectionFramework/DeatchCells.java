class DeatchCells {

		/**	ASSESSMENT **/
		/** 
		 * Game of Death: 
		 * 
		 * Implement an algorithm that produces the next move in the game of death.
			Basically given a two dimensional array it will have either values 1 (live cell) or 0 (dead cell) 

			1. A Live cell will live only if it has two or three live neighbors All other cases die. 

			2. A dead cell with exactly three live neighbors will live otherwise no change, dead 

			Transform the array by using above two rules 		
			
			(implement rules, and change array according to them)		
		*/
	
	
		/** Data Example **/
	
		//   	1 1 1 0	1 1 1
		//		1 0 0 0	1 1 1
		//		1 0 0 0 1 1 1
		//		1 0 0 0 1 1 1
		//		0 1 1 1 0 1 0
		//		1 1 0 1 0 1 0
	
	
		/** 	DESIGN TO IMPL
	
			//processData(cellsData)
				//loop in every cell and
					//if alive  ->willSurvive
					// else		-> willResurrect
		
			// willSurvive(x,y, cellsData)
				//calculateLivingNb(x,y, cellsData)
					// previousLineLiving(cell) ->sum living NB 
					// currentLineLiving(cell) ->sum living NB 
					// nextLineLiving(cell) ->sum living NB 
		
			// willResurrect(x,y, cellsData)
				//calculateLivingNb(x,y, cellsData)
					// previousLineLiving(cell) ->sum living NB 
					// currentLineLiving(cell) ->sum living NB 
					// nextLineLiving(cell) ->sum living NB 
		
		*/
	
	
	public static void main(String[] args) {
		
		int[][] cellsData={
				{1, 1, 1, 0, 1, 0, 1},	// expected {1, 0, 1, 1, 1, 1, 1} // because of updates
				{0, 1, 1, 0, 0, 0, 1},
				{0, 1, 0, 0, 1, 1, 1},
				{0, 1, 0, 0, 0, 0, 1}, 	// expected {1, 1, 1, 0, 0, 1, 1}
				{0, 1, 0, 0, 0, 0, 1},
				{0, 0, 1, 0, 1, 0, 1},
				{0, 0, 0, 0, 1, 0, 1}	// expected {0, 0, 0, 1, 0, 1, 0}			
		};
	
		System.out.println("Before");
		printData(cellsData);
		processData(cellsData);
		System.out.println("\n\nAfter");
		printData(cellsData);
	}
	
	
	private static void processData(int[][] cellsData){
		
		for (int i=0; i<cellsData.length; i++ ){
			for (int j=0; j<cellsData[i].length; j++ ){
				
				if(cellsData[i][j]==1){
					updateLivingCell(i,j,cellsData);
				}else{
					updateDeadCell(i,j,cellsData);
				}				
			}			
		}		
	}
	
	

	
	private static void updateLivingCell(int x, int y, int[][] cellsData){
		
		if(willSurvive(x, y, cellsData)){
			cellsData[x][y]=1;
		}else{
			cellsData[x][y]=0;
		}
		
	}
	
	private static void updateDeadCell(int x, int y, int[][] cellsData){
		
		if(willResurrect(x, y, cellsData)){
			cellsData[x][y]=1;
		}else{
			cellsData[x][y]=0;
		}
	}
	
	private static boolean willSurvive(int x, int y, int[][] cellsData){
	
		int livingCells=sumNeighborsPreviousLine(x, y, cellsData);		
		livingCells+=sumNeighborsSameLine(x, y, cellsData);		
		livingCells+=sumNeighborsNextLine(x, y, cellsData);
		
		return (livingCells==2 || livingCells==3);
	
	}
	
	
	private static int sumNeighborsNextLine(int x, int y, int[][] cellsData) {
		int livingCellsSum=0;
		
		if (x<cellsData.length-1){
			int previous= (isValidIndex(y-1, cellsData[x+1]))? cellsData[x+1][y-1] : 0;
			int same= (isValidIndex(y, cellsData[x+1]))? cellsData[x+1][y] : 0;
			int next= (isValidIndex(y+1, cellsData[x+1]))? cellsData[x+1][y+1] : 0;
			livingCellsSum=previous+same+next;
		}
		return livingCellsSum;
	}


	private static int sumNeighborsSameLine(int x, int y, int[][] cellsData) {
	
		int previous= (isValidIndex(y-1, cellsData[x]))? cellsData[x][y-1] : 0;
		int same= (isValidIndex(y, cellsData[x]))? cellsData[x][y] : 0;
		int next= (isValidIndex(y+1, cellsData[x]))? cellsData[x][y+1] : 0;
		return previous+same+next;		
	}


	private static int sumNeighborsPreviousLine(int x, int y, int[][] cellsData){
		int livingCellsSum=0;
		
		if (x>0){
			int previous= (isValidIndex(y-1, cellsData[x-1]))? cellsData[x-1][y-1] : 0;
			int same= (isValidIndex(y, cellsData[x-1]))? cellsData[x-1][y] : 0;
			int next= (isValidIndex(y+1, cellsData[x-1]))? cellsData[x-1][y+1] : 0;
			livingCellsSum=previous+same+next;
		}
		return livingCellsSum;
	}
	
	
	private static boolean willResurrect(int x, int y, int[][] cellsData){
		
		int livingCells=sumNeighborsPreviousLine(x, y, cellsData);		
		livingCells+=sumNeighborsSameLine(x, y, cellsData);		
		livingCells+=sumNeighborsNextLine(x, y, cellsData);		
		return livingCells==3;
	}
	
	
	
	private static boolean isValidIndex(int index, int [] line){
		return index>0 && index<line.length;
	}

	
	
	
	private static void printData(int[][] cellsData){
		
		for (int i=0; i<cellsData.length; i++ ){
			System.out.println("");
			for (int j=0; j<cellsData[i].length; j++ ){
				System.out.print(cellsData[i][j]+" ");
			}
		}
		
	}
}