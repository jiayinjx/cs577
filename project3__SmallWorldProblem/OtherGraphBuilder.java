
public class OtherGraphBuilder extends GraphBuilder {
	int n; // 
	int k; // dimension
	public OtherGraphBuilder(int _n, int _k){
		n = _n;
		k = _k;
	}
	@Override
	public Graph getGraph() {
		Graph g = new Graph();
		int numNode = (int)Math.pow(n, k);
		g.setNumNodes(numNode);
		for(int i = 0; i< k; i++){
			// left of i:  k-1-i bits;    right of : i bits
			int possible_left = (int)Math.pow(n, k-1-i);
			int possible_right = (int)Math.pow(n, i);
			//System.out.println("possible_left: "+possible_left+", possible_right: "+possible_right);
			for(int left = 0; left< possible_left; left++){
				for(int right = 0; right < possible_right; right++){
					for(int j1 = 0; j1< n; j1++){
						for(int j2 = j1+1; j2<n ;j2++){
							int num1 = left * (int)Math.pow(n, i+1) + j1 * (int)Math.pow(n, i) + right;
							int num2 = left * (int)Math.pow(n, i+1) + j2 * (int)Math.pow(n, i) + right;
							g.AddEdge(num1, num2);
						//	System.out.println("Add edge: "+num1+", "+num2);
						}
					}
				}
			}
		}
		return g;
	}

}
