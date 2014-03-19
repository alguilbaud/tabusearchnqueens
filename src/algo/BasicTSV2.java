package algo;
import problem.NQueenV2;


/* Version 1.2 du BasicTS
 * 
 * Version améliorée n'ayant plus de problème de dépassement mémoire.
 * Correspond à la recherche Tabu, cristallisation des Question 1) 3) et 4).
 * 
 * Auteur Delmée Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Algorithme de recherche tabu sur un nombre d'itération prédéfini.
 * La taille de la liste tabu est passée en paramètre.
 * 
 */


public class BasicTSV2 {
	private int maxIter;
	private NQueenV2 nqueen;
	private int dim;
	private int tailleTabu;
	
	// Test d'automatisation des tailles tabu 
	public BasicTSV2(int n){
		maxIter = 10*n ;
		nqueen = new NQueenV2(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	/* Constructeur de la classe BasicTS
	 * 
	 * n permet de définir le nombre de Queen et le problème NQueen associé
	 * tabu permet de définir la taille de la liste tabu
	 * 
	 */
	public BasicTSV2(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueenV2(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	
	/* Fonction permettant d'ajout à la Liste Tabu
	 * 
	 * Permet d'ajouter à la liste Tabu un couple i,j correspondant au dernier swap effectuée.
	 * 
	 */
	public void addTabu(int[] bestFit, int[][] tabuL, int iterator)
	{
		for( int i = 0; i < 3 ; ++i)
		{
			tabuL[iterator][i] = bestFit[i] ;
		}
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
		
		int[][] tabulist = new int[tailleTabu][3] ;
		
		for( int i = 0; i < tailleTabu ; i++)
		{
			tabulist[i][0] = 0 ;
			tabulist[i][1] = 0 ;
			tabulist[i][2] = 0 ;
		}
		
		int k = 0;
		int tabu = 0;
		
		System.out.println("Fitness Beggining = " + best_s[0]) ;
		
		/*
		for(int i = 1; i < dim+1 ; i++ )
		{
			System.out.println("Queen "+ i + " placed " + best_s[i]) ;
		}
		*/
		
		int[] bestFit = new int[4];
		int[] best_v = new int[dim+1];
		
		while( k < maxIter && (best_s[0] != 0) ){
			k++;
			
			bestFit = nqueen.findBestFit(best_s[0], s, tabulist) ;
			
			for(int in = 1; in <dim+1 ; ++in)
			{
				best_v[in] = s[in] ;
			}
			
			best_v[bestFit[0]] = bestFit[2] ;
			
			best_v[0] = bestFit[3] ;
			
			addTabu(bestFit, tabulist, tabu) ;
			tabu = (tabu+1) % tailleTabu ;
			
			s = best_v;
			
			if(best_v[0] < best_s[0])
			{
				best_s = best_v;
			}
			
		}
		
		System.out.println("Final k = "+k);
		
		return best_s;
	}
}
