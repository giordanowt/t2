
public class Node extends Tree{

	public Tree left, right;
	
	public Node(Tree l, Tree r){
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
	
}
