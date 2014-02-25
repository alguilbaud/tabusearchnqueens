package problem;


public class NQueen {

	private int dim;
	
	public NQueen(int n){
		dim = n;
	}
	
	public int getDim(){
		return dim;
	}

	public int[] generate(){
		int[] newBirth = new int[dim+1];
		
		for(int i=1; i<=dim; ++i){
			newBirth[i] = (int)Math.floor(Math.random()*dim+1);
		}
		return newBirth;
	}
	
	public int fitness(int[] inSol){ //ça prend un tableau à une dimension alors que le voisinnage renvoie un tableau à 2 dimensions, c'est pas pratique, faut uniformiser
		int fit = 0 ;
		
		for(int i=0; i<dim; ++i){
			for(int j=i+1; j<dim; ++j){
				if(inSol[i]==inSol[j]){
					++fit;
				}
				if(Math.abs(inSol[i]-inSol[j])==(j-i)){
					++fit;
				}
			}
		}
		
		return fit;
	}
	
	public int[][] voisinage( int[] inSol, int[][] tabu){ //voir commentaire de fitness
		int[][] voisin = new int[dim][dim+1];
		int[] courant = new int[dim+1];
		
		for(int i=1; i<=dim; ++i){
			for(int j=1; j<dim; ++j){
				if(j==inSol[i]){
					
				}
			}
		}
		
		return voisin;
	}
	
}
