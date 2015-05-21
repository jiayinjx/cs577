import java.util.Random;


public class IndependentEdgeGraphBuilder extends GraphBuilder {

	private double p;
	private int n;
	public IndependentEdgeGraphBuilder(double _p, int _n){
		p= _p;
		n=_n;
	}
	@Override
	public Graph getGraph() {
		Random r = new Random();
		Graph g = new Graph();
		g.setNumNodes(n);
		for(int i =0; i< n; i++){
			for(int j = i+1; j<n; j++){
				// (i, j) is  combination
				double rand = r.nextDouble();
				if(rand < p){
					g.AddEdge(i, j);
				}
			}
		}
		return g;
	}

}
