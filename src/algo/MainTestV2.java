package algo;
import java.util.Scanner; 


/* Version 1.2 du MainTest
 * 
 * Version améliorée n'ayant plus de problème de dépassement mémoire.
 * Correspond à la recherche Tabu, cristallisation des Question 1) 3) et 4).
 * 
 * Auteur Delmée Quentin, Grouhan Benjamin, Guilbaud Alexi
 * 
 * Main de notre Tabu Search
 * 
 */


public class MainTestV2 {

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
		
		
		
		BasicTSV2 TabuTest = new BasicTSV2(n,m) ;
		
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
