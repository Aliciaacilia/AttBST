package bst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BSTImpl implements BST_IF {
	
	private Node root; //nó raiz
	
	public BSTImpl() { //Utilizar o construtor padrão

	}

	/**
	 * O método abaixo verifica se a BST está vazia. Para isso
	 * retorna true se a raiz (root) for igual a null, pois, se 
	 * não existe uma raiz, significa que não existe nenhum elemento na 
	 * árvore, já que ela é primeiro elemento a ser inserido.
	 */
	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * Seguindo o pseudocódigo, para calcularmos a altura, verificamos
	 * se o nó atual é igual a null, ou seja se ele não tem filhos e então
	 * retornamos -1. Caso contrário, calculamos recursivamente a altura
	 * das subárvores esquerda e direita, comparam-se esses dois valores e
	 * é retornado 1 + o valor deles para contar o nível atual do nó.
	 */
	@Override
	public int height() {
		return height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		} 
		else {
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());

			if (leftHeight > rightHeight) {
				return 1 + leftHeight;
			} 
			else {
				return 1 + rightHeight;
			}
		}
	}
	
	/**
	 * Para o método search, criei a variável current que aponta 
	 * para a raiz da árvore. E enquanto current for diferente de null (ou seja,
	 * enquanto existir um nó para verificar), continua... Se o valor do nó atual
	 * for igual ao valor que buscamos, então retorna esse nó e paramos
	 * a busca. Caso contrário, se o valor que buscamos for menor que o valor 
	 * do nó atual, então atualizamos current para ser o filho da esquerda. Se o valor
	 * for maior que o do nó atual, buscamos na subárvore da direita. Se o while terminou,
	 * significa que não encontramos o valor e retornamos null.
	 */
	@Override
	public Node search(Integer value) {
    Node current = this.root;
    while (current != null) {
        if (value.equals(current.getValue())) {
            return current;
        } else if (value < current.getValue()) {
            current = current.getLeft();
        } else {
            current = current.getRight();
        }
    }
    return null;
}

	/**
	 * Método de inserção: comecei criando o novo nó e, logo após, é
	 * realizada a verificação para saber se a árvore está vazia (root = null).
	 * Se estiver, então o novo nó será o primeiro nó da árvore. Seguindo...
	 * fazemos as verificações para saber se o valor que queremos inserir 
	 * é maior ou menor que o nó atual (menor: verificamos o filho da esquerda, maior:
	 * filho da direita). Ao final, o valor é inserido na posição correta dentro da árvore.
	 */
	@Override
	public void insert(Integer value) {
		Node newNode = new Node(value);
    if (root == null) {
        root = newNode;
        return;
    }

    Node current = root;
    while (true) {
        if (value < current.getValue()) {
            if (current.getLeft() == null) {
                current.setLeft(newNode);
                return;
            }
            current = current.getLeft();
        } else {
            if (current.getRight() == null) {
                current.setRight(newNode);
                return;
            }
            current = current.getRight();
        }
    }
		
	}

	/**
	 * Para achar o maior valor da árvore, seguimos pela direita até 
	 * encontrar o último nó. Primeiramente, verificamos se a árvore está
	 * vazia e retornamos null caso não exista nenhum valor. Caso contrário,
	 * seguimos enquanto houver um filho à direita (while). Quando chegarmos ao
	 * último nó, ele guardará esse valor e será retornado.
	 */
	@Override
	public Node maximum(Node node) {
	if (node == null) {
		return null;
	}
	while (node.getRight() != null) {
		node = node.getRight();
	}
	return node;
}

	/**
	 * Para encontrar o menor valor seguimos a mesma lógica, porém 
	 * percorremos agora pelo lado esquerdo da árvore até 
	 * encontrar o menor valor e retornamos ao final.
	 */
	@Override
	public Node minimum(Node node) {
	if (node == null) {
		return null;
	}
	while (node.getLeft() != null) {
		node = node.getLeft();
	}
	return node;
}

	/**
	 * Para o predecessor de um nó, primeiro verificamos se ele
	 * possui um filho à esquerda. Se tiver, o predecessor é o 
	 * maior nó dessa subárvore esquerda, então seguimos para o 
	 * filho da esquerda e, depois,vamos para a direita até o último nó. 
	 * Caso o nó não tenha filho à esquerda, subimos na árvore pelos pais até
	 *  encontrarmos um nó que seja pai de um filho direito,que será o predecessor. 
	 * Se não encontrarmos esse nó, significa que o nó não possui predecessor,
	 * e então retornamos null.
	 */
	@Override
	public Node predecessor(Node node) {
    if (node == null) {
        return null;
    }
    
    if (node.getLeft() != null) {
        Node current = node.getLeft();
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }
    
    Node current = node;
    Node parent = current.getParent();
    while (parent != null && current == parent.getLeft()) {
        current = parent;
        parent = parent.getParent();
    }
    return parent;
}
	/**
	 * Para o sucessor, primeiro verificamos se ele possui um filho à direita. 
	 * Se tiver, o sucessor é o menor nó dessa subárvore direita, 
	 * então seguimos para o filho da direita e, em seguida, vamos para a 
	 * esquerda até o último nó. Se o nó não tiver filho à direita, subimos na 
	 * árvore pelos pais até encontrarmos um nó que seja pai de um filho esquerdo,
	 * que será o sucessor. Se não encontrarmos esse nó, 
	 * significa que o nó não possui sucessor, e então retornamos null.
	 */
	@Override
	public Node successor(Node node) {
    if (node == null) {
        return null;
    }

    if (node.getRight() != null) {
        return minimum(node.getRight());
    }
    
    Node parent = node.getParent();
    while (parent != null && node == parent.getRight()) {
        node = parent;
        parent = parent.getParent();
    }
    	return parent;
	}

	/**
	 * Remoção- começamos usando duas variáveis: 
	 * current (nó atual) e parent (pai dele).
	 * Se não encontrar o valor, retorna, pois não tem o que remover.
	 * Caso 1: o nó a ser removido é folha (sem filhos), basta desconectar do pai.
	 * Caso 2: o nó tem só um filho, ligamos o pai direto ao filho do nó removido.
	 * Caso 3: o nó tem dois filhos, encontramos o sucessor, copiamos seu valor para o nó atual
	 * e removemos o sucessor, que terá no máximo um filho (removido pelo caso 1 ou 2).
	 * Se o nó removido for a raiz, atualizamos a raiz para o filho que o substituir.
	 */
		@Override
	public void remove(Integer value) {
		root = removeRec(root, value);
	}

		//Método aux
		private Node removeRec(Node node, Integer value) {
		if (node == null) {
			return null; 
		}

		if (value < node.getValue()) {
			node.setLeft(removeRec(node.getLeft(), value));
		} else if (value > node.getValue()) {
			node.setRight(removeRec(node.getRight(), value));
		} else {

        //Caso 1
        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        }

        //Caso 2
        if (node.getLeft() == null) {
            return node.getRight();
        }
        if (node.getRight() == null) {
            return node.getLeft();
        }

        //Caso 3
        Node successor = minimum(node.getRight()); 
        node.setValue(successor.getValue());
        node.setRight(removeRec(node.getRight(), successor.getValue()));
    }

    	return node;
	}

	/**
	 * O método preOrder percorre a árvore visitando primeiro o nó atual,
	 * depois recursivamente os filhos da esquerda e depois os da direita.
	 * Para isso, foi criado um método auxiliar que vai adicionando os valores
	 * dos nós em um array, seguindo essa ordem de visitação.
	 */
	@Override
	public Integer[] preOrder() {
    List<Integer> result = new ArrayList<>();
    preOrderTraversal(root, result);
    return result.toArray(new Integer[0]);
	}

	private void preOrderTraversal(Node node, List<Integer> result) {
    if (node == null) {
        return;
    }
    result.add(node.getValue());
    preOrderTraversal(node.getLeft(), result);
    preOrderTraversal(node.getRight(), result);
	}

	/**
	 * Para o "order", criamos uma lista chamada result que
	 * vai armazenar os valores na ordem correta. Em seguida, chamamos o método 
	 * traverseInOrder passando a raiz da árvore e a lista. Dentro desse método, 
	 * primeiro percorremos a subárvore da esquerda, depois adicionamos o valor 
	 * do nó atual na lista e, por fim, percorremos a subárvore da direita. No
	 * final do percurso, transformamos a lista em array e retornamos o resultado.
	 */
	@Override
	public Integer[] order() {
    List<Integer> result = new ArrayList<>();
    traverseInOrder(root, result);
    return result.toArray(new Integer[0]);
	}

	private void traverseInOrder(Node node, List<Integer> result) {
		if (node != null) {
			traverseInOrder(node.getLeft(), result);
			result.add(node.getValue());
			traverseInOrder(node.getRight(), result);
		}
	}

	/**
	 * PostOrder: foi criado uma lista (result) que
	 * vai guardar os valores. Em seguida chamamos o método auxiliar 
	 * passando a raiz e a lista. Nesse método, foi seguido a lógica de visitar
	 * primeiro a subárvore esquerda, depois a direita e só por último o nó atual. 
	 * Ao final, geramos o array e retornamos como resultado.
	 */
	@Override
	public Integer[] postOrder() {
	List<Integer> result = new ArrayList<>();
	postOrderTraversal(node, result);
	return result.toArray(new Integer[0]);
	}

	private void postOrderTraversal(Node node, List<Integer> result) {
	if (node != null) {
		postOrderTraversal(node.getLeft(), result);
		postOrderTraversal(node.getRight(), result);
		result.add(node.getValue());
		}
	}

	/**
	 * O "Size" serve para contar quantos nós existem na árvore inteira.
	 * Primeiro verificamos se a árvore está vazia (node == null), se sim, retornamos 0.
	 * Caso contrário, usamos uma função recursiva (countNodes) que 
	 * percorre todos os nós da árvore. A cada chamada ela soma 1 
	 * (representando o nó atual) com a quantidade de nós da subárvore esquerda 
	 * e da subárvore direita, retornando no final o total de nós da árvore.
	 */
	@Override
	public int size() {
		if (node == null) {
			return 0;
		}
		return countNodes(node);
	}

	private int countNodes(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}
	//finish!

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
