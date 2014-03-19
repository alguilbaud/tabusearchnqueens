package algo;

import problem.NQueenV2;

public class BasicTSV2 {
	private int maxIter;
	private NQueenV2 nqueen;
	private int dim;
	private int tailleTabu;
	
	// Test d'automatisation des tailles tabu 
	public BasicTSV2(int n){
		maxIter = 10*n ;
		nqueen = new NQueenV2(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	public BasicTSV2(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueenV2(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	
	
	public void addTabu(int[] bestFit, int[][] tabuL, int iterator)
	{
		for( int i = 0; i < 3 ; ++i)
		{
			tabuL[iterator][i] = bestFit[i] ;
		}
	}
	
	public int[] start(){
		
		int[] s = nqueen.generate() ;	
		
		int[] best_s = s ;
		
		int[][] tabulist = new int[tailleTabu][3] ;
		
		for( int i = 0; i < tailleTabu ; i++)
		{
			tabulist[i][0] = 0 ;
			tabulist[i][1] = 0 ;
			tabulist[i][2] = 0 ;
		}
		
		int k = 0;
		int tabu = 0;
		
		System.out.println("Fitness Beggining = " + best_s[0]) ;
		
		/*
		for(int i = 1; i < dim+1 ; i++ )
		{
			System.out.println("Queen "+ i + " placed " + best_s[i]) ;
		}
		*/
		
		int[] bestFit = new int[3];
		int[] best_v = new int[dim+1];
		
		while( k < maxIter && (best_s[0] != 0) ){
			k++;
			
			bestFit = nqueen.findBestFit(best_s[0], s, tabulist) ;
			
			for(int in = 1; in <dim+1 ; ++in)
			{
				best_v[in] = s[in] ;
			}
			
			best_v[bestFit[0]] = bestFit[2] ;
			
			addTabu(bestFit, tabulist, tabu) ;
			tabu = (tabu+1) % tailleTabu ;
			
			s = best_v;
			
			if(best_v[0] < best_s[0])
			{
				best_s = best_v;
			}
			
		}
		
		System.out.println("Final k = "+k);
		
		return best_s;
	}
}
