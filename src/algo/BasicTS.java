package algo;

import java.util.ArrayList;

public class BasicTS {
	private int maxIter;
	private NQueen nqueen;
	private int tailleTabu;
	
	public BasicTS(int n, int tabu){
		maxIter = 100;
		nqueen = new NQueen(n);
		tailleTabu = tabu;
	}
	
	public ArrayList<Integer> start(){
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<Integer> best_s = new ArrayList<Integer>();
		int k = 0;
		while(k<maxIter){
			k++;
			ArrayList<Integer> v = nqueen.voisinage(s);
			ArrayList<Integer> best_v = nqueen.fitness(v);
			s = best_v;
			if(nqueen.fitness(best_v) < nqueen.fitness(best_s)){
				best_s = best_v;
			}
		}
		return best_s;
	}
}
