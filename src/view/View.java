package view;

import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}
	public void printMenu()
	{
		System.out.println("1. Opcion 1. cargar Informacion");
		System.out.println("2. Opcion 2: Dar los N comparendos ocurridos mas al norte con un vehiculo dado(Max Cola)");
		System.out.println("3. Opcion 3: Dar los N comparendos ocurridos mas al norte con un vehiculo dado(Max Cola)");
		System.out.println("8. Quit");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		System.out.println("------------------------------------------------------------------------");

	}
	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	public void printModelo(Modelo modelo)
	{
		// TODO implementar
		for( int i = 0; i<modelo.darTamanoCola(); i++){
			System.out.println(modelo.darMaxCola().darElementos().darElemento(i));

		}
	}
}
