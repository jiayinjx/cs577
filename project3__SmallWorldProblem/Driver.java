import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;


public class Driver {
	public static void main(String[] args){
		Driver d = new Driver();
		Graph g;
		GraphBuilder gb;

		String content;
		double graphProperties[] = new double[3];
		int exp = 10;
		int numNodes = 100;
		// independent edge


		content = "";
		for(double n0 = 0.1*numNodes; n0 <= numNodes; n0 += 0.1*numNodes){
			for(double m = 0.1*n0; m <=n0; m += 0.1*n0){

				graphProperties[0] = graphProperties[1] = graphProperties[2] = 0;
				for(int i = 0; i<exp*10; i++){			
					gb = new PreferentialAttachmentGraphBuilder((int)m, (int)n0, numNodes);
					g = gb.getGraph();
				//	d.PrintGraph(g);
					double result[] = d.GraphProperties(g);
					graphProperties[0]+=result[0];
					graphProperties[1]+=result[1];
					graphProperties[2]+=result[2];				
				}
				graphProperties[0]/=exp*10;
				graphProperties[1]/=exp*10;
				graphProperties[2]/=exp*10;
				content+=n0+","+(m/n0)+","+graphProperties[0]+","+graphProperties[1]+","+graphProperties[2]+"\n";
			}
		}
		d.WriteToFile("PreferentialAttachment.csv", content);
		
		System.exit(0);

		System.out.println("============================");
	//	System.exit(0);
		content = "";
		for(double p = 0.01; p<=0.1; p+=0.01){
			graphProperties[0] = graphProperties[1] = graphProperties[2] = 0;
			for(int i = 0; i<exp; i++){			
				gb = new IndependentEdgeGraphBuilder(p, numNodes);
				g = gb.getGraph();	
				double result[] = d.GraphProperties(g);
				graphProperties[0]+=result[0];
				graphProperties[1]+=result[1];
				graphProperties[2]+=result[2];				
			}
			graphProperties[0]/=exp;
			graphProperties[1]/=exp;
			graphProperties[2]/=exp;
	//		d.PrintGraph(g);
			content+=p+","+graphProperties[0]+","+graphProperties[1]+","+graphProperties[2]+"\n";
		}
		d.WriteToFile("IndependentEdge_0.1.csv", content);

		System.out.println("============================");
		

		content = "";
		System.out.println("============================");
		for(double a = 0.1*numNodes; a <= numNodes; a+=0.1*numNodes){
			for (double b = 0.01; b <= 0.1; b+=0.01){
				graphProperties[0] = graphProperties[1] = graphProperties[2] = 0;
				for(int i = 0; i<exp; i++){
					gb = new LocalPreferenceGraphBuilder(a, b, numNodes);
					g = gb.getGraph();
					double result[] = d.GraphProperties(g);
					graphProperties[0]+=result[0];
					graphProperties[1]+=result[1];
					graphProperties[2]+=result[2];				
				}
				graphProperties[0]/=exp;
				graphProperties[1]/=exp;
				graphProperties[2]/=exp;
				content+=a+","+b+","+graphProperties[0]+","+graphProperties[1]+","+graphProperties[2]+"\n";
		//		System.out.println(content);
			}
		}
		//System.out.println(content);
		d.WriteToFile("LocalPreference_0.1.csv", content);
		
	//	System.exit(0);

		System.out.println("============================");
		 
		content = "";
		for(double p = 0.1; p<1; p+=0.1){
			graphProperties[0] = graphProperties[1] = graphProperties[2] = 0;
			for(int i = 0; i<exp; i++){			
				gb = new IndependentEdgeGraphBuilder(p, numNodes);
				g = gb.getGraph();	
				double result[] = d.GraphProperties(g);
				graphProperties[0]+=result[0];
				graphProperties[1]+=result[1];
				graphProperties[2]+=result[2];				
			}
			graphProperties[0]/=exp;
			graphProperties[1]/=exp;
			graphProperties[2]/=exp;
	//		d.PrintGraph(g);
			content+=p+","+graphProperties[0]+","+graphProperties[1]+","+graphProperties[2]+"\n";
		}
		d.WriteToFile("IndependentEdge.csv", content);
		
		System.out.println("============================");


		content = "";
		for(double a = 0.1*numNodes; a <= numNodes; a+=0.1*numNodes){
			for (double b = 0.1; b <= 1; b+=0.1){
				graphProperties[0] = graphProperties[1] = graphProperties[2] = 0;
				for(int i = 0; i<exp; i++){
					gb = new LocalPreferenceGraphBuilder(a, b, numNodes);
					g = gb.getGraph();
					double result[] = d.GraphProperties(g);
					graphProperties[0]+=result[0];
					graphProperties[1]+=result[1];
					graphProperties[2]+=result[2];				
				}
				graphProperties[0]/=exp;
				graphProperties[1]/=exp;
				graphProperties[2]/=exp;
				content+=a+","+b+","+graphProperties[0]+","+graphProperties[1]+","+graphProperties[2]+"\n";
			}
		}
		d.WriteToFile("LocalPreference.csv", content);
		System.out.println("============================");


	
		
		
		System.out.println("============================");
		content = "";
		for(int n = 5; n <=15; n+=5){
			for(int k = 1; k<=3; k++){
				graphProperties[0] = graphProperties[1] = graphProperties[2] = 0;
				for(int i = 0; i<1; i++){			
				//	System.out.println(n+","+k+","+i);	
					gb = new OtherGraphBuilder(n, k);
					g = gb.getGraph();
				//	d.PrintGraph(g);
					double result[] = d.GraphProperties(g);
					graphProperties[0]+=result[0];
					graphProperties[1]+=result[1];
					graphProperties[2]+=result[2];		
				}
				graphProperties[0]/=exp;
				graphProperties[1]/=exp;
				graphProperties[2]/=exp;
				content+=n+","+k+","+graphProperties[0]+","+graphProperties[1]+","+graphProperties[2]+"\n";
			}
		}
		d.WriteToFile("Other.csv", content);
	}

	public void PrintGraph(Graph g){
		double density = g.Density();
		boolean connectivity = g.Connectivity();
		double path_length[] = g.PathLength();
		String result = "Graph density: "+ density+"; ";
		result += "Graph Connectivity: "+connectivity+"; ";
		result+= "Connected Pairs: "+path_length[0]+"; average path length: "+path_length[1];
		System.out.println(result);
	}
	
	public double[] GraphProperties(Graph g){
		double result [] = new double[3];
		result[0] = g.Density();
		double path_length[] = g.PathLength();
		result[1] = path_length[0];
		result[2] = path_length[1];
		return result;
	}

	public void WriteToFile(String filename, String content){

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
