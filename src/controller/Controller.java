package controller;

import java.util.Scanner;


import model.data_structures.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		Integer respuesta = 0;

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				view.printMessage("------------------------------------------------------------------\n Ingrese el numero de comparendos que desea agregar a la MaxCola y MaxHeap \n------------------------------------------------------------------------");
				int num = lector.nextInt();
				int time = modelo.cargarInfo(num);

				view.printMessage("Total de comparendos leidos: "+modelo.darArreglo().darTamano()+"");
				view.printMessage("Tiempo de carga datos alaeatorios en MaxCola y MaxHeap: "+time+" milisegundos.");

				view.printMessage("------------------------------------------------------------------------");


				break;

			case 2:
				view.printMessage("------------------------------------------------------------------------\n Digite el numero de comparendos que desea conocer: \n------------------------------------------------------------------------");
				int num1 = lector.nextInt();
				view.printMessage("------------------------------------------------------------------------\n Digite el primer vehiculo que desea conocer los comparendos: \n------------------------------------------------------------------------");
				String vehiculo1Cola = lector.nextLine();
				view.printMessage("------------------------------------------------------------------------\n Digite el segundo vehiculo que desea conocer los comparendos: \n------------------------------------------------------------------------");
				String vehiculo1Cola1 = lector.nextLine();
				view.printMessage("------------------------------------------------------------------------\n Digite el tercer vehiculo que desea conocer los comparendos: \n------------------------------------------------------------------------");
				String vehiculo1Cola2 = lector.nextLine();
				int index2=0;
				Long start = System.currentTimeMillis();

				while(index2<num1)
				{
					Comparendo datogg= modelo.eliminarMaxCola();
					if(datogg!=null)
					{
						if(datogg.darClaseVehiculo().equals(vehiculo1Cola)||datogg.darClaseVehiculo().equals(vehiculo1Cola1)||datogg.darClaseVehiculo().equals(vehiculo1Cola2)){
							view.printMessage("El ID del vehiculo es: "+ datogg.darID()+ ", el tipo de vehiculo es: "+ datogg.darClaseVehiculo()+ ", la latitud y longitud son: "+ datogg.darLatitud()+","+datogg.darLongitud());
						}
					}
					index2++;
				}
				Long finish = System.currentTimeMillis();
				view.printMessage("El requerimiento duro: "+ (int)(finish-start)+" milisegundos");


				break;
			case 3:
				view.printMessage("------------------------------------------------------------------------\n Digite el numero de comparendos que desea conocer: \n------------------------------------------------------------------------");
				int num2 = lector.nextInt();
				view.printMessage("------------------------------------------------------------------------\n Digite el primer vehiculo que desea conocer los comparendos: \n------------------------------------------------------------------------");
				String vehiculo2heap = lector.nextLine();
				view.printMessage("------------------------------------------------------------------------\n Digite el segundo vehiculo que desea conocer los comparendos: \n------------------------------------------------------------------------");
				String vehiculo2heap1 = lector.nextLine();
				view.printMessage("------------------------------------------------------------------------\n Digite el tercer vehiculo que desea conocer los comparendos: \n------------------------------------------------------------------------");
				String vehiculo2heap2 = lector.nextLine();
				int index3=0;
				Long start1 = System.currentTimeMillis();

				while(index3<num2)
				{
					Comparendo datogg= modelo.eliminarMaxCola();
					if(datogg!=null)
					{
						if(datogg.darClaseVehiculo().equals(vehiculo2heap)||datogg.darClaseVehiculo().equals(vehiculo2heap1)||datogg.darClaseVehiculo().equals(vehiculo2heap2)){
							view.printMessage("El ID del vehiculo es: "+ datogg.darID()+ ", el tipo de vehiculo es: "+ datogg.darClaseVehiculo()+ ", la latitud y longitud son: "+ datogg.darLatitud()+","+datogg.darLongitud());
						}
					}
					index3++;
				}
				Long finish2 = System.currentTimeMillis();
				view.printMessage("El requerimiento duro: "+ (int)(finish2-start1)+" milisegundos");


				break;

			default: 
				view.printMessage("--------------------------------------------------------------- \n Opcion Invalida !! \n---------------------------------------------------------------");
				break;
			}
		}




	}	
}
