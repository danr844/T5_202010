package model.data_structures;

import java.util.Iterator;


public class TablaHashSeparateChaining <Key extends Comparable<Key>, V extends Comparable<V>>implements ITablaHash<Key, V> 
{
	private int M = 97;
	private Node<Key, V>[] st = new Node[M];  	// array of linked lists (chains)
	private static final int[] PRIMES ={53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613, 393241,786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319};

	private static class Node <Key extends Comparable<Key>, V extends Comparable<V>>
	{
		private Key key;
		private V val;
		private Node<Key, V> next;
		private Node(Key k, V pval, Node<Key,V> pNext){key = k; val = pval; next = pNext;}
		public Node<Key, V> darSiguiente(){return next;}
		public void agregar(Node nuevo){if(next==null)next = nuevo;else next.agregar(nuevo);}


		public void cambiarSiguiente(Node<Key, V> pSiguiente)
		{
			next = pSiguiente;
		}
		public void cambiarValor(V valor)
		{
			val= valor;
		}
		public V darTvalor(){
			return val;
		}
	}
	public void put(Key pkey, V val) 
	{
		int i = hash(pkey);
		for (Node x = st[i]; x != null; x = x.next) 
			if (pkey ==x.key) 
			{ x.val = val;
			return; } 
		st[i] = new Node(pkey, val, st[i]); 
	}
	private int hash(Key x) 
	{
		return ((31*x.hashCode()) & 0x7fffffff) % M; 
	} 



	public V get(Key key)
	{
		int i = hash(key);
		//calcula el índice i a partir de la llave
		for (Node x = st[i]; x != null; x = x.next) 
			//recorre la lista en i 
			if (key.equals(x.key)) 
				return (V) x.val; 
		//si encuentra la clave, retorna valor 
		return null; //clave no encontrada 

	}


	public V delete(Key llave) {
		// TODO Auto-generated method stub
		int i = hash(llave);
		Node<Key,V> aEliminar = null;
		V dato = null;

		//calcula el índice i a partir de la llave
		for (Node<Key, V> x = st[i]; x != null; x = x.next) 
			//recorre la lista en i 
			if (llave.equals(x.next.key)) 
				aEliminar=x; 
		if(aEliminar!= null){
			dato = aEliminar.next.val;
			aEliminar.cambiarSiguiente(aEliminar.next.next);
		}
		return dato;
	}

	public Iterable<Key> keys1()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }


	public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys1())
                queue.enqueue(key);
        }
        return queue;
    } 


}
