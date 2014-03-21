package algo;
import java.util.Scanner; 


/* Version 1 du Maintest
 * 
 * Version de base avec le voisinage complet provoquant des probl�mes de d�passement m�moire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisins est approximativement de taille n� = 1 000 000. 
 * Le stockage n�cessaire de chacun de ces voisins est donc de taille 1 000 000 000.
 * Correspond � la recherche Tabu, cristallisation des Questions 1) 3) et 4).
 * 
 * Auteur Delm�e Quentin, Grouhan Benjamin, Guilbaud Alexis
 * 
 * Main de notre Tabu Search
 * 
 */


public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner( System.in );
		
		int n = 5;
		int m = 5;
		int t = 5;
		
		System.out.println("Number of Queen ? Give an int please ");
		
		n = keyboard.nextInt() ;
		
		System.out.println("Tabu List Size ? Give an int please");
		
		m = keyboard.nextInt() ;
		
		System.out.println("Number of try ? Give an int please");
		
		t = keyboard.nextInt() ;
		
		
		
		BasicTS TabuTest = new BasicTS(n,m) ;
		
		int[][] sol = new int[t][n+1] ;
		
		sol[0] =  TabuTest.start() ;
		
		int j = 0 ;
		
		while( sol[j][0] != 0 && j+1 < t )
		{
			++j ;
			sol[j] = TabuTest.start() ;
	
			System.out.println("Fitness Best Solution of Try " + j +" = "+ sol[j][0] ) ;
			
		}
		
		if(sol[j][0] == 0)
		{
			System.out.println("Solution Found Try " + j ) ;
			
			// pour afficher les placement
			for( int i = 1; i < n+1 ; i++ )
			{
				System.out.println("Queen "+ i + " placed " + sol[j][i]) ;
			}
		}
		
		keyboard.close() ;
		
	}

}
