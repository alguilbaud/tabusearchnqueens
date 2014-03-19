package algo;

import problem.NQueen2V2;

public class BasicTS2V2 {
	private int maxIter;
	private NQueen2V2 nqueen;
	private int dim;
	private int tailleTabu;
	
	// Test d'automatisation des tailles tabu 
	public BasicTS2V2(int n){
		maxIter = 10*n ;
		nqueen = new NQueen2V2(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	public BasicTS2V2(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueen2V2(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	public void addTabu(int[] bestFit, int[][] tabuL, int iterator)
	{
		tabuL[iterator][0] = bestFit[0] ;
		tabuL[iterator][1] = bestFit[1] ;
	}
	
	public int[] start(){
		
		int[] s = nqueen.generate() ;	
		
		int[] best_s = s ;
		
		int[][] tabulist = new int[tailleTabu][4] ;
		
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
		
		int[] bestFit = new int[3] ;
		int[] best_v = new int[dim+1] ;
		
		while( k < maxIter && (best_s[0] != 0) )
		{
			k++ ;
			
			bestFit = nqueen.findBestFit(best_s[0], s, tabulist) ;
			
			for(int i = 1; i <dim+1 ; ++i)
			{
				best_v[i] = s[i] ;
			}
			
			best_v[bestFit[0]] = s[bestFit[1]] ;
			best_v[bestFit[1]] = s[bestFit[0]] ;
			best_v[0] = bestFit[2] ;
			
			addTabu(bestFit, tabulist, tabu) ;
			tabu = (tabu+1) % tailleTabu ;
			
			//s = best_v ;
			
			
			for(int i = 0; i <dim+1 ; ++i)
			{
				s[i] = best_v[i] ;
			}
			
			
			if(best_v[0] < best_s[0])
			{
				best_s = best_v;
			}
			
		}
		
		//System.out.println("Final k = "+k);
		
		return best_s;
	}
}

