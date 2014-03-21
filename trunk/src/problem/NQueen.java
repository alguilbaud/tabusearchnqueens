package problem;


/* Version 1 du NQueen
 * 
 * Version de base avec le voisinage complet provoquant des probl�mes de d�passement m�moire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisins est approximativement de taille n� = 1 000 000. 
 * Le stockage n�cessaire de chacun de ces voisins est donc de taille 1 000 000 000. 
 * Correspond � la recherche Tabu, cristallisation des Questions 1) 3) et 4).
 * 
 * Auteurs : Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexis
 * 
 * Classe permettant la gestion du probl�me des NQueens, tel que le calcul de la Fitness,
 * la cr�ation d'une solution de d�part, la recherche de voisinage ...
 *
 * 
 */


public class NQueen {

	private int dim;
	private int tailletabu;
	
	/* Constructeur de la classe NQueen
	 * 
	 * n correspond au nombre de Queens � placer
	 * m correspond � la taille de la liste Tabu
	 * 
	 */
	public NQueen(int n, int m)
	{
		dim = n ;
		tailletabu = m ;
	}
	
	/* 
	 * Fonction qui permet de r�cup�rer le nombre de Queens du probl�me
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
	
	/* Fonction v�rifiant si un mouvement se trouve dans la liste Tabu
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
	
	/* Fonction calculant la fitness d'une solution
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
	
	/* Fonction permettant de cr�er le voisinage d'une solution donn�e
	 * 
	 * Fonction rendant un tableau de solution correspondant � tout changement de reine possible 
	 * de la solution actuelle Minus les solutions se trouvant dans la liste Tabu 
	 * ( et modulo le crit�re d'aspiration ).
	 * 
	 * Cette version connait h�las un d�passement m�moire lorsque n devient trop grand.
	 */
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
					
					if( voisin[courant][0] == 0 )
					{
						i = dim + 1 ;
						j = dim + 1 ;
						courant ++ ;
					}
					else if( !isInTabu(i, inSol[i], j, tabu)  || voisin[courant][0] < best_s )
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
