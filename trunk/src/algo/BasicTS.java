package algo;

import problem.NQueen;

public class BasicTS {
	private int maxIter;
	private NQueen nqueen;
	private int tailleTabu;
	private int tailleNQueen;
	
	public BasicTS(int n, int tabu){
		maxIter = 100;
		nqueen = new NQueen(n);
		tailleTabu = tabu;
		tailleNQueen = n;
	}
	
	public int[] start(){
		int[] s = nqueen.generate();
		int[] best_s = new int[tailleNQueen];
		int k = 0;
		int[][] tabu = new int[tailleTabu][tailleNQueen];
		int fit;
		boolean trouve = false;
		while(k<maxIter && !trouve){
			k++;
			fit = nqueen.fitness(s);
			if(fit==0){
				trouve = true;
			}
			else{
				int[][] voisins = nqueen.voisinage(s,tabu);
				
				/*s = best_v;
				if(nqueen.fitness(best_v) < nqueen.fitness(best_s)){
					best_s = best_v;
				}*/
			}
		}
		return best_s;
	}
}
