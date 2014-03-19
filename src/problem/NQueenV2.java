package problem;


/* Version 1.2 du NQueen
 * 
 * Version am�lior�e n'ayant plus de probl�me de d�passement m�moire.
 * Correspond � la recherche Tabu, cristallisation des Question 1) 3) et 4).
 * 
 * Auteur Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Classe Permettant la gestion du probl�me des NQueens, tel que le calcul de la Fitness,
 * la cr�ation d'une solution de d�part, la recherche de voisinage ...
 *
 * 
 */


public class NQueenV2 {

	private int dim;
	private int tailletabu;
	
	/* Constructeur de la classe NQueen
	 * 
	 * n correspond au nombre de Queen � placer
	 * m correspond � la taille de la liste Tabu
	 * 
	 */
	public NQueenV2(int n, int m)
	{
		dim = n ;
		tailletabu = m ;
	}
	
	/* 
	 * Fonction qui permet de r�cup�rer le nombre de Queen du probl�me
	 */
	public int getDim() {
		return dim;
	}

	/* Fonction permettant de g�n�rer une premi�re solution
	 * 
	 * Comme demand� dans la premi�re partie du TP, la solution ainsi g�n�r�e est totalement al�atoire.
	 * 
	 */
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
	
	/* Fonction v�rifiant si un swap se trouve dans la liste Tabu
	 * 
	 * Permet de v�rifier, lors de la recherche de voisinage, si un voisin se trouve ou non
	 * dans la Liste Tabu.
	 * 
	 */
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
	
	/* Fonction Calculant la fitness d'une solution
	 * 
	 * Fonction permettant de calculer la fitness d'une solution et de mettre celle-ci � jour.
	 * 
	 */
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
	
	/* Fonction trouvant le meilleur premier Voisin
	 * 
	 * Cette fonction, n'apparaissant pas dans les version 2 de l'algorithme permet la recherche du meilleur voisin
	 * Sans pour autant avoir besoin de la liste de ceux-ci. En effet, la fonction cr�e chaque voisin un � un
	 * et ne garde en m�moire que le swap permettant d'arriver au meilleur de celui-ci.
	 * On s'�vite ainsi une population dont la taille des donn�es atteint le millaird pour n = 1000.
	 * 
	 */
	public int[] findBestFit(int best_s, int[] currSol, int[][] tabuList )
	{
		int[] best = new int[4] ;
		int bestFit = dim*dim*dim*dim ;
		
		int[] tempSol = new int[dim+1] ;
		

		
		for(int i = 1; i<dim+1; ++i)
		{
			for(int j = 1; j<dim+1; ++j)
			{		
				
				if(j != currSol[i])
				{
					tempSol[0] = dim*dim*dim ;
					
					for(int k = 1; k <dim+1 ; ++k)
					{
						tempSol[k] = currSol[k] ;
					}
					
					tempSol[i] = j ; 
					
					fitness(tempSol) ;
					
					if( !isInTabu(i, currSol[i], j, tabuList)  || tempSol[0] < best_s )
					{				
						if( tempSol[0] < bestFit )
						{
							bestFit = tempSol[0] ;
							best[0] = i ;
							best[1] = currSol[i] ;
							best[2] = j ;
							best[3] = bestFit ;
						}
					}
						
				}
			}
		}	


		return best ;
	}
	
}
