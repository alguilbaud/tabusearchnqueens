package algo;
import problem.NQueen_Swap;


/* Version Swap du BasicTS
 * 
 * Version de base avec le voisinage complet provoquant des probl�mes de d�passement m�moire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisins est approximativement de taille n� = 1 000 000. 
 * Le stockage n�cessaire de chacun de ces voisins est donc de taille 1 000 000 000. 
 * Correspond � la recherche Tabu de la question 5)
 * 
 * Auteurs : Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexis
 * 
 * Algorithme de recherche tabu sur un nombre d'it�rations pr�d�fini.
 * La taille de la liste tabu est pass�e en param�tre.
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
	
	/* Constructeur de la classe TS_Swap
	 * 
	 * n permet de d�finir le nombre de Queens et le probl�me NQueen associ�
	 * tabu permet de d�finir la taille de la liste tabu
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
	 * Fonction trouvant la premi�re fitness minimale parmi une population de solutions
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
	
	/* Fonction permettant d'ajouter � la Liste Tabu
	 * 
	 * Permet d'ajouter � la liste Tabu un couple i,j correspondant au dernier swap effectu�.
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
	
	/* Fonction d�marrant la recherche Tabu
	 * 
	 * Fonction utilis�e dans le Main_Swap pour d�marrer la recherche Tabu.
	 * Celle-ci s'arr�te au bout de 10*n it�rations ou d�s qu'une solution exacte est trouv�e.
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
		
		System.out.println("Fitness Beginning = " + best_s[0]) ;
		
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

