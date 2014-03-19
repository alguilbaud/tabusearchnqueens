package problem;
import java.util.Vector;


/* Version 2.2 du NQueen
 * 
 * Version améliorée n'ayant plus de problème de dépassement mémoire.
 * Correspond à la recherche Tabu de la question 5)
 * 
 * Auteur Delmée Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Classe Permettant la gestion du problème des NQueens, tel que le calcul de la Fitness,
 * la création d'une solution de départ, la recherche de voisinage ...
 *
 * 
 */


public class NQueen2V2 {

	private int dim;
	private int tailletabu;
	
	/* Constructeur de la classe NQueen
	 * 
	 * n correspond au nombre de Queen à placer
	 * m correspond à la taille de la liste Tabu
	 * 
	 */
	public NQueen2V2(int n, int m)
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
	 * Comme demandé dans l'exercice 5, la solution ainsi générée assure qu'aucune reine
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
	
	/* Fonction vérifiant si un swap se trouve dans la liste Tabu
	 * 
	 * Permet de vérifier, lors de la recherche de voisinage, si un voisin se trouve ou non
	 * dans la Liste Tabu.
	 * 
	 */
	public boolean isInTabu( int i, int j, int[][] tabu)
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
					return true ;
				}
			}
		
		return false ;
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
	
	/* Fonction trouvant le meilleur premier Voisin
	 * 
	 * Cette fonction, n'apparaissant pas dans la version 2 de l'algorithme permet la recherche du meilleur voisin
	 * Sans pour autant avoir besoin de la liste de ceux-ci. En effet, la fonction crée chaque voisin un à un
	 * et ne garde en mémoire que le swap permettant d'arriver au meilleur de celui-ci.
	 * On s'évite ainsi une population dont la taille des données atteint le millaird pour n = 1000.
	 * 
	 */
	public int[] findBestFit(int best_s, int[] currSol, int[][] tabuList )
	{
		int[] best = new int[3] ;
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
					
					if( !isInTabu( i, j, tabuList)  || tempSol[0] < best_s )
					{	
						if( tempSol[0] < bestFit)
						{
							bestFit = tempSol[0] ;
							best[0] = i ;
							best[1] = j ;
							best[2] = bestFit ;
						}
					}
						
			}
		}


		return best ;
	}
	
}
