package cs577_project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class part1 {
	static int SIZE=100;
	static int HEAD = 0;
	static int TAIL = 1;
	static int TOTAL_RUNS = 1000000;
	Random rand;
	// one time result
	int time;
	Map<Integer, Integer> count_numCells;
	int visited[];
	// TOTAL_RUNS total result
	Map<Integer, Integer> time_count;
	Map<Integer, Integer> count_numCells_sum;
	int visited_sum[];
	
	public part1(){
		rand = new Random();
		visited = new int[SIZE];
		count_numCells = new HashMap<Integer, Integer>();
		time_count = new HashMap<Integer, Integer>();
		count_numCells_sum = new HashMap<Integer, Integer>();
		visited_sum = new int[SIZE];
		for(int i = 0; i<SIZE; i++){
			visited_sum[i]=0;
		}
	}
	public part1_result run(){
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
			// accumulate visited
			for(int j = 0; j<SIZE; j++){
				visited_sum[j]+=visited[j];
			}
			// accumulate count_numCells;
			for(Map.Entry<Integer, Integer> entry: count_numCells.entrySet()){
				int count = entry.getKey();
				int numCells = entry.getValue();
				if(count_numCells_sum.containsKey(count)){
					int existing_numCells = count_numCells_sum.get(count);
					existing_numCells+=numCells;
					count_numCells_sum.put(count, existing_numCells);
				}
				else{
					count_numCells_sum.put(count, numCells);
				}
			}
		}
		// 
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(time_count.keySet());
		Collections.sort(keys);
		// 1.1 compute probability		
		List<Double[]> time_probility = new ArrayList<Double[]>();
		for(int time: keys){
			int count = time_count.get(time);
			double probility = (double)count/(double)TOTAL_RUNS;
			time_probility.add(new Double[]{(double)time, probility});
		}
		// 1.22 compute average visited 
		double[] visited_average = new double[SIZE];
		for(int i = 0; i<SIZE; i++){
			visited_average[i] = (double)visited_sum[i]/(double)TOTAL_RUNS;
		}
		// 1.21
		Map<Integer, Double> count_numCells_avg = new HashMap<Integer, Double> ();
		for(Map.Entry<Integer, Integer> entry: count_numCells_sum.entrySet()){
			int count = entry.getKey();
			int numCells_sum = entry.getValue();
			double numCells_avg = (double)numCells_sum/(double)TOTAL_RUNS;
			count_numCells_avg.put(count, numCells_avg);
		}
		
		
		return new part1_result(time_probility, visited_average, count_numCells_avg);
	}
	public void oneRun(){
		// init 
		time = 0;
		int pos = 50;
		for(int i = 0; i<SIZE; i++){
			visited[i] = 0;
		}
		visited[pos] = 1;
		while(true){
			// if one end break
			if (pos==0 || pos == SIZE-1){
				break;
			}
			// else   flip and move
			else{
				int coin = flipCoin();
				if(coin == HEAD){
					pos++;
				}
				else{
					pos--;
				}
				visited[pos]++;
				time++;
			}
		}
		// 1.21 compute how many cells are visited x time
		count_numCells.clear();
		// 1.22 compute numVisit of cell 0, 1, 2, ... 99, stored in visted[]
		
		for(int i = 0; i<SIZE; i++){
			int count = visited[i];
			if(count_numCells.containsKey(count)){
				int numCells = count_numCells.get(count);
				numCells++;
				count_numCells.put(count, numCells);
			}
			else{
				count_numCells.put(count, 1);
			}
		}
	}
	public int flipCoin(){
		return rand.nextInt(2);
	}

}
