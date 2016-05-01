import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

	public static Map<Character, String> huffman = new HashMap<Character, String>(); 
	public static Map<Character, Integer> rle = new HashMap<Character, Integer>(); 
	
	public static void main(String[] args) throws IOException {
		
		// DEFINE COLUNAS E LINHAS
		Scanner scanner = new Scanner(System.in);
		int columns, rows;
		System.out.print("Defina a quantidade de colunas: ");
		columns = scanner.nextInt();
		System.out.print("Defina a quantidade de linhas: ");
		rows = scanner.nextInt();
		System.out.println("Imagem " + columns + "x" + rows);
		scanner.close();
		
		FileWriter f = new FileWriter("image.txt"); // CRIA ARQUIVO QUE SIMULA IMAGEM
		PrintWriter out = new PrintWriter(f);
		// PERCORRE COLUNAS E LINHAS PARA PREENCHER IMAGEM
		Random random = new Random();
		String chars = "";
		char c = 'z';
		int repeat = 1, count = 0;
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				count++;
				if(count == repeat){
					// GRAVA RLE
					rle.put(c, repeat);
					// GERA NOVA SEQUENCIA DE CARACTERES
					repeat = 20 + (int)(Math.random() * 21);
					c = (char)(random.nextInt(26) + 'a');
					count = 0;
				}
				System.out.print(c);
				out.print(c);
			}
			System.out.println("");
			out.println("");
		}
		out.close();
		
		// GERA HUFFMAN
		Tree tree = buildTree();
		getHuffman(tree, new StringBuffer());
		// EXIBE HUFFMAN
		for(Character character : huffman.keySet()){
			System.out.print(character + ":" + huffman.get(character) + ";");
		}
	}

	public static void generateImage(){}
	
	public static Tree buildTree(){
		PriorityQueue<Tree> tree = new PriorityQueue<Tree>();
		for(Character character : rle.keySet()){
			tree.offer(new Leaf(rle.get(character), character));
		}
		while(tree.size() > 1){
			Tree a = tree.poll();
			Tree b = tree.poll();
			tree.offer(new Node(a, b));
		}
		return tree.poll();
	}

	public static void getHuffman(Tree tree, StringBuffer code){
		if(tree instanceof Leaf){
			Leaf leaf = (Leaf) tree;
			huffman.put(leaf.value, code.toString());
		}else if(tree instanceof Node){
			Node node = (Node) tree;
			// LEFT
			code.append("0");
			getHuffman(node.left, code);
			code.deleteCharAt(code.length() - 1);
			// RIGHT
			code.append("1");
			getHuffman(node.right, code);
			code.deleteCharAt(code.length() - 1);
		}
	}

}
