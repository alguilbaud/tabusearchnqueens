package algo;

import problem.NQueen;

public class BasicTS {
	private int maxIter;
	private NQueen nqueen;
	private int dim;
	private int tailleTabu;
	
	public BasicTS(int n){
		maxIter = 10*n ;
		nqueen = new NQueen(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	public BasicTS(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueen(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	int findbestfit(int[][] inV)
	{
		int best = 0 ;
		int bestFit = dim*dim*dim*dim ;
		
		for( int i = 0 ; i < dim*(dim-1) ; ++i)
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
		
		while( i < dim && sol[i] == curr[i]  )
		{
			i++ ;
		}
		
		tabuL[iterator][0] = i ;
		tabuL[iterator][1] = sol[i] ;
		tabuL[iterator][2] = curr[i] ;		
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
