package problem;
import java.util.Vector;


/* Version 2 du NQueen
 * 
 * Version de base avec le voisinage complet ayant des probl�me de d�passement m�moire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisin est approximativement de taill n� = 1 000 000. 
 * Le stockage n�cessaire de chacun de ces voisins est donc de taille 1 000 000 000. 
 * Correspond � la recherche Tabu de la question 5)
 * 
 * Auteur Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Classe Permettant la gestion du probl�me des NQueens, tel que le calcul de la Fitness,
 * la cr�ation d'une solution de d�part, la recherche de voisinage ...
 *
 * 
 */


public class NQueen2 {

	private int dim;
	private int tailletabu;
	
	/* Constructeur de la classe NQueen
	 * 
	 * n correspond au nombre de Queen � placer
	 * m correspond � la taille de la liste Tabu
	 * 
	 */
	public NQueen2(int n, int m)
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
	 * Comme demand� dans l'exercice 5, la solution ainsi g�n�r�e assure qu'aucune reine
	 * ne se trouve dans la colonne d'une autre.
	 * 
	 */
	public int[] generate()
	{
		int[] newBirth = new int[dim+1] ;
		
		Vector<Integer> Random = new Vector<Integer>() ;
		
		for(int j = 0 ; j < dim ; ++j)
		{
			Random.add(j+1) ;
		}
		
		for(int i=1; i<dim+1; ++i)
		{		
	
			int k = (int)Math.floor( Math.random()*Random.size() ) ;
			
			newBirth[i] = Random.get(k) ; 
			Random.remove(k) ;

		}
		
		System.out.println("End Generate Random");
		
		fitness(newBirth) ;
		
		return newBirth ;
	}
	
	/* Fonction v�rifiant si un swap se trouve dans la liste Tabu
	 * 
	 * Permet de v�rifier, lors de la recherche de voisinage, si un voisin se trouve ou non
	 * dans la Liste Tabu.
	 * 
	 */
	public boolean isInTabu(int i, int j, int[][] tabu)
	{
			
			if( i > j )
			{
				int k = j ;
				j = i ;
				i = k ;
			}
		
			for( int k = 0; k < tailletabu ; ++k )
			{
				if( tabu[k][0] == i && tabu[k][1] == j )
				{
					return  true ;
				}
			}
		
		return false ;
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
	
	/* Fonction permettant de cr�er le voisinage d'une solution donn�e
	 * 
	 * Fonction rendant un tableau de solution correspondant toute � un swap possible  de la solution actuelle
	 * Minus les solutions se trouvant dans la liste Tabu ( modulo le crit�re d'aspiration ).
	 * 
	 * Cette version connait h�las un d�passement m�moire lorsque n devient trop grand.
	 */
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
					
					if( !isInTabu(i, j, tabu)  || voisin[courant][0] < best_s )
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
