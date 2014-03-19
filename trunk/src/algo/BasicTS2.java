package algo;

import problem.NQueen2;

public class BasicTS2 {
	private int maxIter;
	private NQueen2 nqueen;
	private int dim;
	private int tailleTabu;
	
	// Test d'automatisation des tailles tabu 
	public BasicTS2(int n){
		maxIter = 10*n ;
		nqueen = new NQueen2(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	public BasicTS2(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueen2(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	int findbestfit(int[][] inV)
	{
		int best = 0 ;
		int bestFit = dim*dim*dim*dim ;
		
		for( int i = 0 ; i < dim*(dim-1)/2 ; ++i)
		{
			if( bestFit > inV[i][0])
			{
				bestFit = inV[i][0] ;
				best = i ;
			}
		}
		
		return best ;
	}
	
	public void addTabu(int[] sol, int[] curr, int[][] tabuL, int iterator)
	{
		int i = 1 ;
		int j = 1 ;
		
		while( i < dim+1 && sol[i] == curr[i]  )
		{
			i++ ;
		}
		
		j = i + 1 ;
		
		while( j < dim+1 && sol[j] == curr[j]	)
		{
			j++ ;
		}
		
		tabuL[iterator][0] = i ;
		tabuL[iterator][1] = j ;
	
	}
	
	public int[] start(){
		
		int[] s = nqueen.generate() ;	
		
		int[] best_s = s ;
		
		int[][] tabulist = new int[tailleTabu][2] ;
		
		for( int i = 0; i < tailleTabu ; i++)
		{
			tabulist[i][0] = 0 ;
			tabulist[i][1] = 0 ;
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
		
		while( k < maxIter && (best_s[0] != 0) ){
			k++;
			
			int[][] v = nqueen.voisinage(best_s[0], s, tabulist);
			
			int bestFit = findbestfit(v) ;
			
			int[] best_v = v[bestFit] ;
			
			addTabu(s, best_v, tabulist, tabu) ;
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

