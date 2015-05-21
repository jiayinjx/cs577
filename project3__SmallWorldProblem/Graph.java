import sun.misc.Queue;




public class Graph {
	int numNode;
	int edges[][];
	public void setNumNodes(int n){
		numNode = n;
		edges = new int[n][n];
		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				edges[i][j]=0;
			}
		}
	}
	
	public void AddEdge(int i, int j){
		edges[i][j] = edges[j][i] = 1;
	}

	public int GetDegree(int i){
		int d = 0;
		for(int j =0; j< numNode; j++)
		{
			d+= edges[i][j];
		}
		return d;
	}
	
	public double Density(){
		int numEdge  = 0;
		for(int i = 0; i< numNode; i++){
			numEdge += GetDegree(i);
		}
		numEdge/=2;
		return (double) numEdge / (double)(numNode * (numNode-1) /2 );
	}
	public boolean Connectivity(){
		// BFS
		if(numNode==0) return false;
		boolean visited[] = new boolean[numNode];
		for(int i = 0; i< numNode; i++){
			visited[i] = false;
		}
		int start = 0;
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(start);
		while(!queue.isEmpty()){
			int aNode =0 ;
			try {
				aNode = queue.dequeue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			visited[aNode] = true;
			for(int i =0; i< numNode; i++){
				if(edges[aNode][i]==1 && !visited[i]){
					queue.enqueue(i);
				}
			}
		}
		// all nodes visited?
		for(int i = 0; i< numNode; i++){
			if(!visited[i]) 
				return false;
		}
		return true;
	}
	public double[] PathLength(){
		int dist[][] = new int[numNode][numNode];
		int infinity = numNode;
		for(int i =0; i< numNode; i++){
		}
		
		for(int i = 0; i< numNode; i++){
			int start = i;
			boolean visited[]=new boolean[numNode];
			for(int j = 0; j<numNode; j++){
				visited[j] = false;
			}
			for(int j = 0; j< numNode; j++){
				dist[i][j] = infinity;
			}
			Queue<Integer> queue = new Queue<Integer>();
			queue.enqueue(start);
			dist[start][start] = 0;
			while(true){
				// find a node : minimum dist to start, unvisited
				int aNode = -1;
				int min_dist = infinity;
				for(int j = 0; j<numNode; j++){
					if( visited[j]) 
						continue;
					int d = dist[start][j];
					if( d < min_dist){
						min_dist = d;
						aNode = j;
					}
				}
				if(aNode == -1 ){
					break;
				}
				visited[aNode] = true;
				// update neighbor
				for(int j =0; j< numNode; j++){
					if( edges[aNode][j] == 1 && ! visited[j]){
						dist[start][j] = Math.min( dist[start][j], dist[start][aNode]+1);
					}
				}
			}
		}
		int non_inf_count = 0;
		int inf_count = 0;
		int total_path_length =0;
		for(int i = 0; i< numNode; i++){
			for(int j = i+1; j<numNode; j++){
				if(dist[i][j] == infinity){
					inf_count++;
			//		System.out.println("find unconnected pairs "+i + " "+j);
				}
				else{
					total_path_length += dist[i][j];
					non_inf_count ++;
				}
			}
			
		}
//		System.out.println(non_inf_count +" "+inf_count+" "+(double)non_inf_count / (double)(non_inf_count+inf_count));
		return new double[]{ (double)non_inf_count / (double)(non_inf_count+inf_count), (double)total_path_length/(double)non_inf_count  };
	}
	
	public String toString(){
		String output = "";
		output+= "numNode: "+ numNode+"\n";
		for(int i = 0; i< numNode; i++){
			for(int j = 0; j<numNode; j++){
				if(edges[i][j]==1){
					output+="("+i+","+j+"), ";
				}
			}
		}
		return output;
	}
}
