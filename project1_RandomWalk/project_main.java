package cs577_project1;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class project_main {
	public static void main(String[] args) {
/*
		part1 p1 = new part1();
		part1_result r1 = p1.run();
		List<Double[]> time_probility = r1.time_probility;
		double visited[] = r1.visited;
		Map<Integer, Double> count_numCells = r1.count_numCells;
		// write result to file		
		WriteResultToFile(time_probility, "part1_1.csv");
		WriteResultToFile(count_numCells, "part1_21.csv");
		WriteResultToFile(visited, "part1_22.csv");
	*/	
		
		// ========================================
		// part 2
		/*
		part2 p2 = new part2();
		part2_result1 r21 = p2.run();
		List<Double[]> time_probility2 = r21.time_probility;
		List<Double[]>	numCellsVisited_probility2 = r21.numCellsVisited_probility;
		double visited2[][] = r21.visited;
		WriteResultToFile(time_probility2, "part2_1.csv");
		WriteResultToFile(numCellsVisited_probility2, "part2_2.csv");
		WriteResultToFile(visited2, "part2_3.csv");
		*/
		// part 2.4
		part2 p24 = new part2();
		part2_result1 r24 = p24.run();
		List<Double[]> time_probility24 = r24.time_probility;
		double visited24[][] = r24.visited;
		WriteResultToFile(time_probility24, "part2_41.csv");
		WriteResultToFile(visited24, "part2_42.csv");
		
	}

	private static void WriteResultToFile(double[][] visited2, String fileName) {
		// TODO Auto-generated method stub

		String dataToWrite = "";
		for(int i = 0; i< visited2.length; i++){
			for(int j = 0; j<visited2[i].length; j++){
				double p = visited2[i][j];
				dataToWrite += i+","+j+","+p+"\n";
			}
		}
		
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(fileName)));
		    writer.write(dataToWrite);
		} catch (IOException ex) {
			System.out.println(ex);
		  System.out.println("Fail to write to file "+fileName);
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}

	private static void WriteResultToFile(Map<Integer, Double> count_numCells,
			String fileName) {

		String dataToWrite = "";
		for(Map.Entry<Integer, Double> entry: count_numCells.entrySet()){
			dataToWrite+= entry.getKey()+","+entry.getValue()+"\n";
		}
		
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(fileName)));
		    writer.write(dataToWrite);
		} catch (IOException ex) {
		  System.out.println("Fail to write to file "+fileName);
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}

	private static void WriteResultToFile(double[] visited, String fileName) {
		String dataToWrite = "";
		for(int i = 0; i<visited.length; i++){
			dataToWrite+=i+","+visited[i]+"\n";
		}

		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(fileName)));
		    writer.write(dataToWrite);
		} catch (IOException ex) {
		  System.out.println("Fail to write to file "+fileName);
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}

	private static void WriteResultToFile(List<Double[]> results, String fileName){
		String dataToWrite = "";
		for(Double[] i : results){
			dataToWrite+=(int)(double)i[0]+","+i[1]+"\n";
		}
		
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(fileName)));
		    writer.write(dataToWrite);
		} catch (IOException ex) {
		  System.out.println("Fail to write to file "+fileName);
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
}
