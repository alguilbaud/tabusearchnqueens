package algo;
import java.util.Scanner; 


/* Version 1 du Maintest
 * 
 * Version de base avec le voisinage complet ayant des problème de dépassement mémoire lorsque n devient trop grand.
 * En effet, pour n = 1000 le nombre de voisin est approximativement de taill n² = 1 000 000. 
 * Le stockage nécessaire de chacun de ces voisins est donc de taille 1 000 000 000.
 * Correspond à la recherche Tabu, cristallisation des Question 1) 3) et 4).
 * 
 * Auteur Delmée Quentin, Grouhan Benjamin, Guilbaud Alexi
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
		
		while( sol[j][0] != 0 && j < t )
		{
			++j ;
			sol[j] = TabuTest.start() ;
	
			System.out.println("Fitness Best Solution of Try " + j +" = "+ sol[j][0] ) ;
			
		}
		
		if(sol[j][0] == 0)
		{
			System.out.println("Solution Found Iteration " + j ) ;
			
			// pour afficher les placement
			for( int i = 1; i < n+1 ; i++ )
			{
				System.out.println("Queen "+ i + " placed " + sol[j][i]) ;
			}
		}
		
		keyboard.close() ;
		
	}

}
