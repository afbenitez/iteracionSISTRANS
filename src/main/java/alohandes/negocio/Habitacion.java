/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: alohandes
 * @version 1.0
 * @author MIGUEL PARRA , ANDRES BENITEZ
 * MARZO 2020
 * 
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package alohandes.negocio;
/**
 * Clase para modelar el concepto habitacion del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Habitacion {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long habitacionId;
	/**
	 * id del operador 
	 */
	private long operadorId;
	/*
	 * direccion de la habitacion
	 */
	private String direccion;
	/**
	 * capacidad de la habitacion
	 */
	private int capacidad;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * contructor
	 */
	public Habitacion()
	{
		this.habitacionId = 0;
		this.operadorId = 0; 
		this.direccion = "";
		this.capacidad =0;
	}
	/**
	 * @param habitacionId
	 * @param operadorId
	 * @param direccion
	 * @param capacidad
	 */
	public Habitacion(long habitacionId, long operadorId, String direccion, int capacidad) {

		this.habitacionId = habitacionId;
		this.operadorId = operadorId;
		this.direccion = direccion;
		this.capacidad = capacidad;
	}
	/**
	 * @return the habitacionId
	 */
	public long getHabitacionId() {
		return habitacionId;
	}
	/**
	 * @param habitacionId the habitacionId to set
	 */
	public void setHabitacionId(long habitacionId) {
		this.habitacionId = habitacionId;
	}
	/**
	 * @return the operadorId
	 */
	public long getOperadorId() {
		return operadorId;
	}
	/**
	 * @param operadorId the operadorId to set
	 */
	public void setOperadorId(long operadorId) {
		this.operadorId = operadorId;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	@Override
	public String toString() {
		return "Habitacion [habitacionId=" + habitacionId + ", operadorId=" + operadorId + ", direccion=" + direccion
				+ ", capacidad=" + capacidad + "]";
	}
	
	
}
