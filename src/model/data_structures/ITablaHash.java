package model.data_structures;

import java.util.Iterator;

public interface ITablaHash <Key extends Comparable<Key>, V extends Comparable<V>> {
	
	public void put(Key llave, V valor);
	public V get (Key llave);
	public V delete (Key llave);
	public Iterator<Key> keys();

}
