package problem;


/* Version 1 du NQueen
 * 
 * Version de base avec le voisinage complet ayant des problème de dépassement mémoire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisin est approximativement de taill n² = 1 000 000. 
 * Le stockage nécessaire de chacun de ces voisins est donc de taille 1 000 000 000. 
 * Correspond à la recherche Tabu, cristallisation des Question 1) 3) et 4).
 * 
 * Auteur Delmée Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Classe Permettant la gestion du problème des NQueens, tel que le calcul de la Fitness,
 * la création d'une solution de départ, la recherche de voisinage ...
 *
 * 
 */


public class NQueen {

	private int dim;
	private int tailletabu;
	
	/* Constructeur de la classe NQueen
	 * 
	 * n correspond au nombre de Queen à placer
	 * m correspond à la taille de la liste Tabu
	 * 
	 */
	public NQueen(int n, int m)
	{
		dim = n ;
		tailletabu = m ;
	}
	
	/* 
	 * Fonction qui permet de récupérer le nombre de Queen du problème
	 */
	public int getDim() {
		return dim;
	}

	/* Fonction permettant de générer une première solution
	 * 
	 * Comme demandé dans la première partie du TP, la solution ainsi générée est totalement aléatoire.
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
	
	/* Fonction vérifiant si un swap se trouve dans la liste Tabu
	 * 
	 * Permet de vérifier, lors de la recherche de voisinage, si un voisin se trouve ou non
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
	 * Fonction permettant de calculer la fitness d'une solution et de mettre celle-ci à jour.
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
	
	/* Fonction permettant de créer le voisinage d'une solution donnée
	 * 
	 * Fonction rendant un tableau de solution correspondant à tout changement de reine possible 
	 * de la solution actuelle Minus les solutions se trouvant dans la liste Tabu 
	 * ( et modulo le critère d'aspiration ).
	 * 
	 * Cette version connait hélas un dépassement mémoire lorsque n devient trop grand.
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
