package algo;
import java.util.Scanner; ;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner( System.in );
		
		System.out.println("Number of Queen ? Give an int please ");
		
		int n = 5;
		int m = 5;
		
		n = keyboard.nextInt() ;
		
		System.out.println("Tabu List Size ? Give an int please");
		
		m = keyboard.nextInt() ;
		
		BasicTS TabuTest = new BasicTS(n,m) ;
		
		int[] sol = TabuTest.start() ;

		System.out.println("Fitness Best Solution = " + sol[0] ) ;
		
		/*
		TabuTest = new BasicTS(n) ;
		
		sol = TabuTest.start() ;
		
		System.out.println("Fitness Best Solution (Auto Size 10%) = " + sol[0] ) ;
		
		*/
		if(sol[0] == 0)
		{
			for(int i = 1; i < n+1 ; i++ )
			{
				System.out.println("Queen "+ i + " placed " + sol[i]) ;
			}
		}
		
		keyboard.close() ;
		
	}

}
