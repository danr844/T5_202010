package model.data_structures;

import java.util.Iterator;


public class TablaHashSeparateChaining <Key extends Comparable<Key>, V extends Comparable<V>>implements ITablaHash<Key, V> 
{
	private int M = 97;
	private Node[] st = new Node[M]; 
	// array of linked lists (chains)

	private static class Node
	{
		private Object key;
		private Object val;
		private Node next;
		private Node(Object k, Object pval, Node pNext){key = k; val = pval; next = pNext;}
		public Node darSiguiente(){return next;}
		public void agregar(Node nuevo){if(next==null)next = nuevo;else next.agregar(nuevo);}
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
		return (x.hashCode() & 0x7fffffff) % M; 
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
	return null;
}


public Iterator<Key> keys() {
	// TODO Auto-generated method stub
	return null;
}
@Override


}
