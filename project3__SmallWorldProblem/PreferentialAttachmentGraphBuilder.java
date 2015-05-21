import java.util.Random;


public class PreferentialAttachmentGraphBuilder extends GraphBuilder {
	int m; 
	int n0;
	int n;
	public PreferentialAttachmentGraphBuilder(int _m, int _n0, int _n){
		m = _m;
		n0=_n0;
		n = _n;
	}
	
	@Override
	public Graph getGraph() {
		Random r = new Random();
		
		Graph g = new Graph();		
		g.setNumNodes(n);
		// init  n0/2 pairs
		for(int i = 0; i< n0; i+=2){
			g.AddEdge(i, i+1);
		}	
		
		// iterate n0+1 to n
		for(int i = n0; i<n; i++){
			int currentNumNodes = i;
			int d[] = new int[currentNumNodes];
			int sum = 0;
			for(int j = 0; j< currentNumNodes; j++){
				d[j] = g.GetDegree(j);
				sum+= d[j];
			}
			double p[] = new double[currentNumNodes];
			for(int j = 0; j< currentNumNodes; j++){
				p[j] = (double)d[j] / (double)sum;
			}
			// get m vertex
			int nodes[] = new int[currentNumNodes];
			for(int j = 0; j< m; j++){
				double prob = r.nextDouble();
				int index = 0;
				while(true){
					if(prob < p[index]) 
						break;
					else{
						prob -= p[index];
						index ++;
					}
				}
				// index
				nodes[index] = 1;
			}
			for(int j = 0; j<currentNumNodes; j++){
				if(nodes[j] == 1){
					g.AddEdge(i, j);
				}
			}			
		}
		return g;
	}

}
