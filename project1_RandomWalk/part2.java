package cs577_project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class part2 {

	final static int SIZE=50;
	final static int LEFT = 0;
	final static int RIGHT = 1;
	final static int UP = 2;
	final static int DOWN = 3;
	final static int TOTAL_RUNS = 1000000;
	Random rand;
	// one time result
	int time;
	int numCellsVisted;
	int visited[][];
	// TOTAL_RUNS total result
	Map<Integer, Integer> time_count;
	Map<Integer, Integer> numCellsVisited_count;
	int visited_sum[][];
	
	public part2(){
		rand = new Random();
		visited = new int[SIZE][SIZE];
		
		time_count = new HashMap<Integer, Integer>();
		numCellsVisited_count = new HashMap<Integer, Integer>();
		visited_sum = new int[SIZE][SIZE];
		for(int i = 0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++)
				visited_sum[i][j]=0;
		}
	}
	public part2_result1 run(){
		// run exp several times
		for(int i = 0; i<TOTAL_RUNS; i++){
			// run once
			oneRun();
			// accumulate time
			if(time_count.containsKey(time)){
				int count = time_count.get(time);
				count++;
				time_count.put(time, count);
			}
			else{
				time_count.put(time, 1);
			}
			// accumulate numCellsVisited (2.2)
			if(numCellsVisited_count.containsKey(numCellsVisted)){
				int count = numCellsVisited_count.get(numCellsVisted);
				count++;
				numCellsVisited_count.put(numCellsVisted, count);
			}
			else{
				numCellsVisited_count.put(numCellsVisted, 1);
			}
			// accumulate visited (2.3)
			for(int j = 0; j<SIZE; j++){
				for(int k = 0; k<SIZE; k++)
					visited_sum[j][k] +=visited[j][k];
			}
		}
		// 2.1 compute probability	
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(time_count.keySet());
		Collections.sort(keys);	
		List<Double[]> time_probility = new ArrayList<Double[]>();
		for(int time: keys){
			int count = time_count.get(time);
			double probility = (double)count/(double)TOTAL_RUNS;
			time_probility.add(new Double[]{(double)time, probility});
		}
		// 2.2 compute average visited 

		List<Integer> keys_visited = new ArrayList<Integer>();
		keys_visited.addAll(numCellsVisited_count.keySet());
		Collections.sort(keys_visited);	
		List<Double[]> numCellsVisited_probility = new ArrayList<Double[]>();
		for(int numCellsVisited: keys_visited){
			int count = numCellsVisited_count.get(numCellsVisited);
			double probility = (double)count/(double)TOTAL_RUNS;
			numCellsVisited_probility.add(new Double[]{(double)numCellsVisited, probility});
		}
		// 2.3
		
		double[][] visited_average = new double[SIZE][SIZE];
		for(int i = 0; i<SIZE; i++){
			for(int j =0; j<SIZE; j++)
			visited_average[i][j] = (double)visited_sum[i][j]/(double)TOTAL_RUNS;
		}		
		
		return new part2_result1(time_probility, numCellsVisited_probility, visited_average);
	}
	public void oneRun(){
		// init 
		time = 0;
		int posX = 25;
		int posY = 25;
		for(int i = 0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++)
				visited[i][j] = 0;
		}
		visited[posX][posY] = 1;
		
		while(true){
			// if one end break
			if (posX==0 || posX == SIZE-1 || posY==0 || posY == SIZE-1){
				break;
			}
			// else   flip and move
			else{
				int coin = flipCoin();
				switch(coin){
				case LEFT:
					posX--;
					break;
				case RIGHT:
					posX++;
					break;
				case UP:
					posY++;
					break;
				case DOWN:
					posY--;
					break;
				default:	
					System.out.println("ERROR");
					System.exit(1);
				}
				visited[posX][posY]++;
				time++;
			}
		}
		// 2.2 how many cells are visit?
		numCellsVisted =0 ;
		// 2.3 compute numVisit of cell 0, 1, 2, ... 99, stored in visited[][]
		
		for(int i = 0; i<SIZE; i++){
			for(int j=0;j<SIZE; j++){
				if(visited[i][j]!=0){
					numCellsVisted++;
				}
			}
		}
	}
	
	public part2_result1 run2(){
		// run exp several times
		for(int i = 0; i<TOTAL_RUNS; i++){
			// run once
			oneRun();
			// accumulate time. 2.41
			if(time_count.containsKey(time)){
				int count = time_count.get(time);
				count++;
				time_count.put(time, count);
			}
			else{
				time_count.put(time, 1);
			}
			// accumulate visited (2.42)
			for(int j = 0; j<SIZE; j++){
				for(int k = 0; k<SIZE; k++)
					visited_sum[j][k] +=visited[j][k];
			}
		}

		// 2.41 compute time probability	
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(time_count.keySet());
		Collections.sort(keys);	
		List<Double[]> time_probility = new ArrayList<Double[]>();
		for(int time: keys){
			int count = time_count.get(time);
			double probility = (double)count/(double)TOTAL_RUNS;
			time_probility.add(new Double[]{(double)time, probility});
		}
		// 2.42		
		double[][] visited_average = new double[SIZE][SIZE];
		for(int i = 0; i<SIZE; i++){
			for(int j =0; j<SIZE; j++)
			visited_average[i][j] = (double)visited_sum[i][j]/(double)TOTAL_RUNS;
		}		
		
		return new part2_result1(time_probility, null, visited_average);
		
	}
	public void oneRun2(){
		// init 
		time = 0;
		int posX = 25;
		int posY = 25;
		for(int i = 0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++)
				visited[i][j] = 0;
		}
		visited[posX][posY] = 1;
		
		while(true){
			// if one end break
			boolean allVisited = true;
			for(int i = 0; i<SIZE; i++){
				for(int j = 0 ; j<SIZE; j++){
					if(visited[i][j]==0){
						allVisited = false;
						break;
					}
				}
				if(!allVisited){
					break;
				}
			}
			if(allVisited){
				break;
			}
			// else   flip and move
			else{
				int coin = flipCoin();
				switch(coin){
				case LEFT:
					posX--;
					break;
				case RIGHT:
					posX++;
					break;
				case UP:
					posY++;
					break;
				case DOWN:
					posY--;
					break;
				default:	
					System.out.println("ERROR");
					System.exit(1);
				}
				if(posX<0){
					posX = 1;
				}
				if(posX>=SIZE){
					posX = SIZE-2;
				}
				if(posY<0){
					posY = 1;
				}
				if(posY >=SIZE){
					posY = SIZE-2;
				}
				visited[posX][posY]++;
				time++;
			}
		}
		// 2.2 how many cells are visit?
		numCellsVisted =0 ;
		// 2.3 compute numVisit of cell 0, 1, 2, ... 99, stored in visited[][]
		
		for(int i = 0; i<SIZE; i++){
			for(int j=0;j<SIZE; j++){
				if(visited[i][j]!=0){
					numCellsVisted++;
				}
			}
		}
	}
	
	public int flipCoin(){
		return rand.nextInt(4);
	}
}
