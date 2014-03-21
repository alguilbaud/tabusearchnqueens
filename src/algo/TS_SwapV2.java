package algo;
import problem.NQueen_SwapV2;


/* 2�me version Swap du BasicTS
 * 
 * Version am�lior�e n'ayant plus de probl�me de d�passement m�moire.
 * Correspond � la recherche Tabu de la question 5)
 * 
 * Auteurs : Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexis
 * 
 * Algorithme de recherche tabu sur un nombre d'it�rations pr�d�fini.
 * La taille de la liste tabu est pass�e en param�tre.
 * 
 */


public class TS_SwapV2 {
	private int maxIter;
	private NQueen_SwapV2 nqueen;
	private int dim;
	private int tailleTabu;
	
	// Test d'automatisation des tailles tabu 
	public TS_SwapV2(int n){
		maxIter = 10*n ;
		nqueen = new NQueen_SwapV2(n, (int) n/10 );
		tailleTabu = (int) n/10;
		dim = n ;
	}
	
	
	/* Constructeur de la classe Ts_SwapV2
	 * 
	 * n permet de d�finir le nombre de Queens et le probl�me NQueen associ�
	 * tabu permet de d�finir la taille de la liste tabu
	 * 
	 */
	public TS_SwapV2(int n, int tabu){
		maxIter = 10*n;
		nqueen = new NQueen_SwapV2(n, tabu);
		tailleTabu = tabu;
		dim = n ;
	}
	
	/* Fonction permettant d'ajouter � la Liste Tabu
	 * 
	 * Permet d'ajouter � la liste Tabu un couple i,j correspondant au dernier swap effectu�.
	 * 
	 */
	public void addTabu(int[] bestFit, int[][] tabuL, int iterator)
	{
		tabuL[iterator][0] = bestFit[0] ;
		tabuL[iterator][1] = bestFit[1] ;
	}
	
	
	/* Fonction d�marrant la recherche Tabu
	 * 
	 * Fonction utilis�e dans le Main_SwapV2 pour d�marrer la recherche Tabu.
	 * Celle-ci s'arr�te au bout de 10*n it�rations ou d�s qu'une solution exacte est trouv�e.
	 * 
	 */
	public int[] start(){
		
		int[] s = nqueen.generate() ;	
		
		int[] best_s = s ;
		
		int[][] tabulist = new int[tailleTabu][4] ;
		
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
		
		int[] bestFit = new int[3] ;
		int[] best_v = new int[dim+1] ;
		
		while( k < maxIter && (best_s[0] != 0) && k2 < dim )
		{
			k++ ;
			
			bestFit = nqueen.findBestFit(best_s[0], s, tabulist) ;
			
			for(int i = 1; i <dim+1 ; ++i)
			{
				best_v[i] = s[i] ;
			}
			
			best_v[bestFit[0]] = s[bestFit[1]] ;
			best_v[bestFit[1]] = s[bestFit[0]] ;
			best_v[0] = bestFit[2] ;
			
			addTabu(bestFit, tabulist, tabu) ;
			tabu = (tabu+1) % tailleTabu ;
			
			//s = best_v ;
			
			
			for(int i = 0; i <dim+1 ; ++i)
			{
				s[i] = best_v[i] ;
			}
			
			
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

