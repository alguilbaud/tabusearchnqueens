package problem;

import java.util.Vector;

public class NQueen2 {

	private int dim;
	private int tailletabu;
	
	public NQueen2(int n, int m)
	{
		dim = n ;
		tailletabu = m ;
	}
	
	public int getDim() {
		return dim;
	}

	public int[] generate()
	{
		int[] newBirth = new int[dim+1] ;
		
		newBirth[0] = 0 ;
		
		for(int i=1; i<dim+1; ++i)
		{
			boolean ok = false ;
			
			Vector<Integer> Random = new Vector<Integer>() ;
			
			for( i = 0 ; i < dim ; ++i)
			{
				Random.add(i+1) ;
			}
			
			while( !ok )
			{
				int k = (int)Math.floor( Math.random()*Random.size() ) ;
				
				newBirth[i] = Random.get(k) ; 
				Random.remove(k) ;
				
				for(int j=1; j<i && !ok ; ++j)
				{
					ok = true ;
					
					if( newBirth[j] == newBirth[i] )
					{
						ok = false ;
					}
				}
			}
		}
		
		System.out.println("End Generate Random");
		
		fitness(newBirth) ;
		
		return newBirth ;
	}
	
	public boolean isInTabu(int courant, int i, int j, int[][] tabu)
	{
		boolean toReturn = false ;
			
			if( i > j )
			{
				int k = j ;
				j = i ;
				i = k ;
			}
		
			for( int k = 0; k < tailletabu ; ++k )
			{
				if( tabu[k][0] == courant && tabu[k][1] == i && tabu[k][2] == j )
				{
					toReturn = true ;
				}
			}
		
		return toReturn ;
	}
	
	public void fitness(int[] inSol)
	{
		int fit = 0 ;
		
		for(int i = 1; i < dim+1; ++i )
		{
			for(int j = i+1; j < dim+1; ++j)
			{
				if(inSol[i]==inSol[j])
				{
					++fit;
				}
				if( ((int)Math.abs(inSol[i]-inSol[j])) == (j-i))
				{
					++fit;
				}
			}
		}
		
		inSol[0] = fit;
	}
	
	public int[][] voisinage(int best_s, int[] inSol, int[][] tabu)
	{
		int[][] voisin = new int[dim*(dim-1)/2][dim+1] ;
		int courant = 0 ;
		
		for(int i = 1; i<dim+1; ++i)
		{
			for(int j = i+1 ; j<dim+1; ++j)
			{		
				
					voisin[courant][0] = dim*dim*dim ;
					
					for(int k = 1; k <dim+1 ; ++k)
					{
						voisin[courant][k] = inSol[k] ;
					}
					
					voisin[courant][i] = inSol[j] ; 
					voisin[courant][j] = inSol[i] ;
					
					fitness(voisin[courant]) ;
					
					if( !isInTabu(i, inSol[i], j, tabu)  || voisin[courant][0] < best_s )
					{				
						courant ++ ;
					}
						
			}
		}
		
		while( courant < dim*(dim-1)/2 )
		{
			voisin[courant][0] = dim*dim*dim ;
			courant ++ ;
		}
		
		return voisin;
	}
	
}
