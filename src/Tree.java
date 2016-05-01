
public class Tree implements Comparable<Tree>{

	public int frequency;
	
	public Tree(int f){
		frequency = f;
	}

	public int compareTo(Tree tree){
		return frequency - tree.frequency;
	}
		
}
