package algo;
import problem.NQueen_Swap;


/* Version Swap du BasicTS
 * 
 * Version de base avec le voisinage complet ayant des problème de dépassement mémoire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisin est approximativement de taill n² = 1 000 000. 
 * Le stockage nécessaire de chacun de ces voisins est donc de taille 1 000 000 000. 
 * Correspond à la recherche Tabu de la question 5)
 * 
 * Auteur Delmée Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Algorithme de recherche tabou sur un nombre d'itération prédéfini.
 * La taille de la liste tabu est passée en paramètre.
 * 
 */



public class TS_Swap {
	private int maxIter;
	private NQueen_Swap nqueen;
	private int dim;
	private int tailleTabu;
	
	// Test d'automatisation des tailles tabu 
	public TS_Swap(int n){
		maxIter = 10*n ;
		nqueen = new NQueen_Swap(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	/* Constructeur de la classe BasicTS
	 * 
	 * n permet de définir le nombre de Queen et le problème NQueen associé
	 * tabu permet de définir la taille de la liste tabu
	 * 
	 */	
	public TS_Swap(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueen_Swap(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	/* Fonction renvoyant le premier meilleur voisin
	 * 
	 * Fonction trouvant la première fitness minimale parmi une population de solution
	 */
	int findbestfit(int[][] inV)
	{
		int best = 0 ;
		int bestFit = dim*dim*dim*dim ;
		
		for( int i = 0 ; i < dim*(dim-1)/2 ; ++i)
		{
			if( bestFit > inV[i][0])
			{
				bestFit = inV[i][0] ;
				best = i ;
			}
		}
		
		return best ;
	}
	
	/* Fonction permettant d'ajout à la Liste Tabu
	 * 
	 * Permet d'ajouter à la liste Tabu un couple i,j correspondant au dernier swap effectuée.
	 * 
	 */
	public void addTabu(int[] sol, int[] curr, int[][] tabuL, int iterator)
	{
		int i = 1 ;
		int j = 1 ;
		
		while( i < dim+1 && sol[i] == curr[i]  )
		{
			i++ ;
		}
		
		j = i + 1 ;
		
		while( j < dim+1 && sol[j] == curr[j]	)
		{
			j++ ;
		}
		
		tabuL[iterator][0] = i ;
		tabuL[iterator][1] = j ;
	
	}
	
	/* Fonction démarrant la recherche Tabu
	 * 
	 * Fonction utilisée dans le MainTest pour démarrer la recherche Tabu.
	 * Celle-ci s'arrête au bout de 10*n itération où dès qu'une solution exacte est trouvée.
	 * 
	 */
	public int[] start(){
		
		int[] s = nqueen.generate() ;	
		
		int[] best_s = s ;
		
		int[][] tabulist = new int[tailleTabu][2] ;
		
		for( int i = 0; i < tailleTabu ; i++)
		{
			tabulist[i][0] = 0 ;
			tabulist[i][1] = 0 ;
		}
		
		int k = 0;
		int k2 = 0 ;
		int tabu = 0;
		
		System.out.println("Fitness Beggining = " + best_s[0]) ;
		
		/*
		for(int i = 1; i < dim+1 ; i++ )
		{
			System.out.println("Queen "+ i + " placed " + best_s[i]) ;
		}
		*/
		
		while( k < maxIter && (best_s[0] != 0) && k2 < dim){
			k++;
			
			int[][] v = nqueen.voisinage(best_s[0], s, tabulist);
			
			int bestFit = findbestfit(v) ;
			
			int[] best_v = v[bestFit] ;
			
			addTabu(s, best_v, tabulist, tabu) ;
			tabu = (tabu+1) % tailleTabu ;
			
			s = best_v;
			
			if(best_v[0] < best_s[0])
			{
				best_s = best_v;
				k2 = 0 ;
			}
			else
			{
				k2++ ;
			}
			
		}
		
		System.out.println("Final k = "+k);
		
		return best_s;
	}
}

