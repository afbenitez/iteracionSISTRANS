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
 * Clase para modelar el concepto hostal del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Hostal {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long hostalId;
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
	public Hostal()
	{
		this.hostalId = 0;
		this.operadorId =0;
		this.capacidad =0;
		this.direccion = "";
		this.nombre = "";
	}
	/**
	 * @param hostalId
	 * @param operadorId
	 * @param capacidad
	 * @param direccion
	 * @param nombre
	 */
	public Hostal(long hostalId, long operadorId, int capacidad, String direccion, String nombre) {

		this.hostalId = hostalId;
		this.operadorId = operadorId;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.nombre = nombre;
	}
	/**
	 * @return the hostalId
	 */
	public long getHostalId() {
		return hostalId;
	}
	/**
	 * @param hostalId the hostalId to set
	 */
	public void setHostalId(long hostalId) {
		this.hostalId = hostalId;
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
		return "Hostal [hostalId=" + hostalId + ", operadorId=" + operadorId + ", capacidad=" + capacidad
				+ ", direccion=" + direccion + ", nombre=" + nombre + "]";
	}
	
}
