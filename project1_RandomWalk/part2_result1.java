package cs577_project1;

import java.util.ArrayList;
import java.util.List;

public class part2_result1 {
	public double[][] visited;
	public List<Double[]> numCellsVisited_probility;
	public List<Double[]> time_probility;
	public part2_result1( List<Double[]> t_p, List<Double[]> nCV_p, double[][] v_a){
		visited = v_a;
		numCellsVisited_probility = nCV_p;
		time_probility = t_p;
	}
}
