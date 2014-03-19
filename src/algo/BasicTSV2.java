package algo;
import problem.NQueenV2;


/* Version 1.2 du BasicTS
 * 
 * Version am�lior�e n'ayant plus de probl�me de d�passement m�moire.
 * Correspond � la recherche Tabu, cristallisation des Question 1) 3) et 4).
 * 
 * Auteur Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Algorithme de recherche tabu sur un nombre d'it�ration pr�d�fini.
 * La taille de la liste tabu est pass�e en param�tre.
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
	 * n permet de d�finir le nombre de Queen et le probl�me NQueen associ�
	 * tabu permet de d�finir la taille de la liste tabu
	 * 
	 */
	public BasicTSV2(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueenV2(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	
	/* Fonction permettant d'ajout � la Liste Tabu
	 * 
	 * Permet d'ajouter � la liste Tabu un couple i,j correspondant au dernier swap effectu�e.
	 * 
	 */
	public void addTabu(int[] bestFit, int[][] tabuL, int iterator)
	{
		for( int i = 0; i < 3 ; ++i)
		{
			tabuL[iterator][i] = bestFit[i] ;
		}
	}
	
	/* Fonction d�marrant la recherche Tabu
	 * 
	 * Fonction utilis�e dans le MainTest pour d�marrer la recherche Tabu.
	 * Celle-ci s'arr�te au bout de 10*n it�ration o� d�s qu'une solution exacte est trouv�e.
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
