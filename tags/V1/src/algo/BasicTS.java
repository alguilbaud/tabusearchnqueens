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
	
	public void tocopyto(int[] to, int[] ex)
	{
		int[] temp = new int[dim+1] ;
		
		for( int i = 0; i < dim+1 ; ++i )
		{
			temp[i] = to[i] ;
			to[i] = ex[i] ;
			ex[i] = temp[i] ;
		}
	}
	
	public void quickSort(int[][] v, int begin, int end)
	{	
		if( end - begin > 0 )
		{
			int pointeur = begin ;

			for(int i = begin; i < end ; ++i)
			{
				if( v[i][0] < v[end][0] )
				{
					
					int[] temp = new int[dim+1] ;
					temp = v[pointeur] ;
					v[pointeur] = v[i] ;
					v[i] = temp ;
					pointeur ++ ;
					
					/*
					int[] temp = new int[dim+1] ;
					
					tocopyto(temp,v[pointeur]) ;
					tocopyto(v[pointeur],v[i]) ;
					tocopyto(v[i],temp);
					pointeur ++ ;
					*/
				}
			}

			int[] temp = new int[dim+1] ;
			temp = v[pointeur] ;
			v[pointeur] = v[end] ;
			v[end] = temp ;

			/*
			int[] temp = new int[dim+1] ;
			
			tocopyto(temp,v[pointeur]) ;
			tocopyto(v[pointeur],v[end]) ;
			tocopyto(v[end],temp);
			*/
			
			quickSort(v, begin, pointeur-1) ;
			quickSort(v, pointeur+1, end) ;
		}
		
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
			
			//quickSort(v, 0, (dim*dim)-1 ) ;	
			/*
			for(int j = 0; j < dim*(dim-1) ; ++j )
			{
				System.out.println("Fitness V " + j + " = " + v[j][0]) ;
				if( v[j][0] != dim*dim*dim )
				{
						System.out.println("Queen 1 placed " +v[j][1]) ;
				}
			}
			*/
			
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
