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
 * Clase para modelar el concepto hotel del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Hotel {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long hotelId;
	/**
	 * id del operador 
	 */
	private long operadorId;
	/**
	 * capacidad
	 */
	private int capacidad;
	/**
	 * direccion del hostal
	 */
	private String direccion;
	/**
	 * nombre
	 */
	private String nombre;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor
	 */
	public Hotel()
	{
		this.hotelId = 0;
		this.operadorId =0;
		this.capacidad =0;
		this.direccion = "";
		this.nombre = "";
	}
	/**
	 * @param hotelId
	 * @param operadorId
	 * @param capacidad
	 * @param direccion
	 * @param nombre
	 */
	public Hotel(long hotelId, long operadorId, int capacidad, String direccion, String nombre) {
	
		this.hotelId = hotelId;
		this.operadorId = operadorId;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.nombre = nombre;
	}
	/**
	 * @return the hotelId
	 */
	public long getHotelId() {
		return hotelId;
	}
	/**
	 * @param hotelId the hotelId to set
	 */
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", operadorId=" + operadorId + ", capacidad=" + capacidad + ", direccion="
				+ direccion + ", nombre=" + nombre + "]";
	}
	
	
}
