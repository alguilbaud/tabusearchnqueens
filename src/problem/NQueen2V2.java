package problem;

import java.util.Vector;

public class NQueen2V2 {

	private int dim;
	private int tailletabu;
	
	public NQueen2V2(int n, int m)
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
		
			Vector<Integer> Random = new Vector<Integer>() ;
			
			for(int j = 0 ; j < dim ; ++j)
			{
				Random.add(j+1) ;
			}
			
			int k = (int)Math.floor( Math.random()*Random.size() ) ;
			
			newBirth[i] = Random.get(k) ; 
			Random.remove(k) ;
			
		}
		
		System.out.println("End Generate Random");
		
		fitness(newBirth) ;
		
		return newBirth ;
	}
	
	public boolean isInTabu( int i, int fromI, int j, int fromJ, int[][] tabu)
	{	
			if( i > j )
			{
				int k = j ;
				j = i ;
				i = k ;
			}
		
			for( int k = 0; k < tailletabu ; ++k )
			{
				if( tabu[k][0] == i && tabu[k][2] == j && tabu[k][1] == fromI && tabu[k][2] == fromJ )
				{
					return true ;
				}
			}
		
		return false ;
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
	
	
	public int[] findBestFit(int best_s, int[] currSol, int[][] tabuList )
	{
		int[] best = new int[5] ;
		int bestFit = dim*dim*dim*dim ;
		
		int[] tempSol = new int[dim+1] ;
		
		for(int i = 1; i<dim+1; ++i)
		{
			for(int j = i+1 ; j<dim+1; ++j)
			{		
				
					tempSol[0] = dim*dim*dim ;
					
					for(int k = 1; k <dim+1 ; ++k)
					{
						tempSol[k] = currSol[k] ;
					}
					
					tempSol[i] = currSol[j] ; 
					tempSol[j] = currSol[i] ;
					
					fitness(tempSol) ;
					
					if( !isInTabu( i, j, currSol[i], currSol[j], tabuList)  || tempSol[0] < best_s )
					{	
						if( tempSol[0] < bestFit)
						{
							bestFit = tempSol[0] ;
							best[0] = i ;
							best[1] = j ;
							best[2] = currSol[i] ;
							best[3] = currSol[j] ;
							best[4] = bestFit ;
						}
					}
						
			}
		}


		return best ;
	}
	
}
