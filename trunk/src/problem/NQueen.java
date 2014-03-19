package problem;

public class NQueen {

	private int dim;
	private int tailletabu;
	
	public NQueen(int n, int m)
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
			newBirth[i] = (int)Math.floor( (Math.random()*dim) +1) ;
		}
		
		fitness(newBirth) ;
		
		return newBirth ;
	}
	
	public boolean isInTabu(int courant, int i, int j, int[][] tabu)
	{
		
			for( int k = 0; k < tailletabu ; ++k )
			{
				if( tabu[k][0] == courant && tabu[k][1] == i && tabu[k][2] == j )
				{
					return true ;
				}
			}
		
		return false;
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
		int[][] voisin = new int[dim*(dim-1)][dim+1] ;
		int courant = 0 ;
		
		for(int i = 1; i<dim+1; ++i)
		{
			for(int j = 1; j<dim+1; ++j)
			{		
				
				if(j != inSol[i])
				{
					voisin[courant][0] = dim*dim*dim ;
					
					for(int k = 1; k <dim+1 ; ++k)
					{
						voisin[courant][k] = inSol[k] ;
					}
					
					voisin[courant][i] = j ; 
					
					fitness(voisin[courant]) ;
					
					if( !isInTabu(i, inSol[i], j, tabu)  || voisin[courant][0] < best_s )
					{				
						courant ++ ;
					}
						
				}
			}
		}
		
		while( courant < dim*(dim-1) )
		{
			voisin[courant][0] = dim*dim*dim ;
			courant ++ ;
		}
		
		return voisin;
	}
	
}
