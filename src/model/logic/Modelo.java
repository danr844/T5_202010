package model.logic;



import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Comparendo;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{
	/**
	 * Atributos del modelo del mundo
	 */
	private MaxColaCP<Comparendo> maxCola;
	private MaxHeapCP<Comparendo> heap;
	private ArregloDinamico<Comparendo>array;


	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo()
	{
		maxCola = new MaxColaCP<Comparendo>();
		heap= new MaxHeapCP<Comparendo>();
		array = new ArregloDinamico<Comparendo>(10000);
	}



	public int cargarInfo(int NumeroDatos){
		int res =0;
		try {

			Gson gson = new Gson();

			String path = "./data/comparendos_dei_2018_small.geojson";
			JsonReader reader;

			List<String> lista = new ArrayList<String>();

			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray ja = elem.getAsJsonObject().get("features").getAsJsonArray();
			for(JsonElement e: ja) {
				int id = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				String fecha = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				String medio = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String Clasevehi= e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String tipoServicio = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String Infraccion =e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DescInfra=e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();
				String Localidad = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				Comparendo user = new Comparendo(0,null, null, null, null, null, null, null, 0, 0);

				if(e.getAsJsonObject().has("geometry") && !e.getAsJsonObject().get("geometry").isJsonNull())
				{
					String[] coordenadas  = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsString().split(",");
					double Latitud =Double.parseDouble(coordenadas[0]);
					double longitud =Double.parseDouble(coordenadas[1]);

					user = new Comparendo(id,fecha, medio, Clasevehi, tipoServicio, Infraccion, DescInfra, Localidad, Latitud, longitud);
				}
				else {
					user = new Comparendo(id,fecha, medio, Clasevehi, tipoServicio, Infraccion, DescInfra, Localidad, 0, 0);
				}
				array.agregar(user);

			}

			System.out.println(Arrays.toString(lista.toArray()));
			int k=0;
			Long start = System.currentTimeMillis();
			while(k<20000){
				int index = (int) Math.random();
				Comparendo user = array.darElemento(index);
				agregarMaxCola(user);
				agregarMaxHeap(user);
				k++;
			}
			Long finish = System.currentTimeMillis();
			res = (int) (finish - start);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param object Dato a eliminar
	 * @return dato eliminado
	 */
	public Comparendo eliminarMaxCola()
	{
		return  (Comparendo) maxCola.deleteMax(darComparadorLatitud());
	}
	public Comparendo eliminarMaxHeap(Comparendo comparendo)
	{
		return  (Comparendo) heap.deleteMax(comparendo, darComparadorLatitud());
	}

	public void agregarMaxCola(Comparendo comparendo){
		maxCola.agregar(comparendo);
	}
	public void agregarMaxHeap(Comparendo comparendo){
		heap.agregar(comparendo, darComparadorLatitud());
	}




	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoCola()
	{
		return maxCola.darNumElementos();
	}
	public int darTamanoHeap()
	{
		return heap.darNumElementos();
	}
	public MaxColaCP<Comparendo> darMaxCola(){
		return maxCola;
	}
	public MaxHeapCP<Comparendo> darMaxHeap(){
		return heap;
	}
	public ArregloDinamico<Comparendo> darArreglo(){
		return array;
	}



	public Comparator<Comparendo> darComparadorLatitud(){


		Comparator<Comparendo> ID = new Comparator<Comparendo>()
		{
			@Override
			public int compare(Comparendo o1, Comparendo o2) 
			{
				if(o1.darLatitud()<o2.darLatitud())return -1;
				else if (o1.darID()>o2.darID())
					return 1;
				return 0;	
			}
		};
		return ID;
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
}
















