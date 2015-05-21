package cs577_project1;

import java.util.List;
import java.util.Map;

public class part1_result {
	public Map<Integer, Double> count_numCells;
	public double[] visited;
	public List<Double[]> time_probility;
	public part1_result(List<Double[]> t_p, double[] v, Map<Integer, Double> c_nCells){
		time_probility = t_p;
		visited= v;
		count_numCells = c_nCells;
	}
}
