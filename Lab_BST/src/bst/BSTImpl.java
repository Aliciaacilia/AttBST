package bst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BSTImpl implements BST_IF {
	
	private Node root; //nó raiz
	
	public BSTImpl() { //Utilizar o construtor padrão

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node search(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Integer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node maximum(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node minimum(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node predecessor(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node sucessor(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Integer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer[] preOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] order() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer[] postOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Método de brinde! Não modificar!
	 * Este método implementa uma busca em largura usando uma fila.
	 * @return
	 */
    public ArrayList<Integer> bfs() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Deque<Node> queue = new LinkedList<Node>();
        
        if (!isEmpty()) {
            queue.addLast(this.root);
            while (!queue.isEmpty()) {
                Node current = queue.removeFirst();
                
                list.add(current.getValue());
                
                if(current.getLeft() != null) 
                    queue.addLast(current.getLeft());
                if(current.getRight() != null) 
                    queue.addLast(current.getRight());   
            }
        }
        return list;
    }

}
