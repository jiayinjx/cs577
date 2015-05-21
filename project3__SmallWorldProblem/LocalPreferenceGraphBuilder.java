import java.util.Random;


public class LocalPreferenceGraphBuilder extends GraphBuilder {
	double alpha, beta;
	int n;
	public LocalPreferenceGraphBuilder(double a, double b, int _n){
		alpha = a;
		beta = b;
		n = _n;
	}
	@Override
	public Graph getGraph() {
		double x[] = new double[n];
		double y[] = new double[n];
		Graph g = new Graph();
		g.setNumNodes(n);
		Random r = new Random();
		for(int i=0; i<n; i++){
			double x_coordinate = r.nextDouble();
			double y_coordinate = r.nextDouble();
			x[i] = x_coordinate;
			y[i] = y_coordinate;
		}
		for(int i = 0; i< n; i++){
			for(int j =i+1; j<n; j++){
				double d = Math.sqrt( (x[i]-x[j])* (x[i]-x[j]) + (y[i]-y[j])* (y[i]-y[j]) );
				double threshold = beta * Math.exp(-d / ( Math.sqrt(2) * alpha));
				//System.out.println(threshold);
				double p = r.nextDouble();
				if(p < threshold){
					g.AddEdge(i, j);
				}
			}
		}
		
		return g;
	}

}
