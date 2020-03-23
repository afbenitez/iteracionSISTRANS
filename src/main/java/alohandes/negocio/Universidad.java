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
 * Clase para modelar el concepto universidad del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Universidad {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long universidadId;
	/**
	 * direccion
	 */
	private String direccion;
	/**
	 * nombre
	 */
	private String nombre;
	/**
	 * nit
	 */
	private long nit;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * contructor 
	 */
	public Universidad()
	{
		this.universidadId =0;
		this.direccion = "";
		this.nombre = "";
		this.nit =0;
	}
	/**
	 * @param universidadId
	 * @param direccion
	 * @param nombre
	 * @param nit
	 */
	public Universidad(long universidadId, String direccion, String nombre, long nit) {

		this.universidadId = universidadId;
		this.direccion = direccion;
		this.nombre = nombre;
		this.nit = nit;
	}
	/**
	 * @return the universidadId
	 */
	public long getUniversidadId() {
		return universidadId;
	}
	/**
	 * @param universidadId the universidadId to set
	 */
	public void setUniversidadId(long universidadId) {
		this.universidadId = universidadId;
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
	/**
	 * @return the nit
	 */
	public long getNit() {
		return nit;
	}
	/**
	 * @param nit the nit to set
	 */
	public void setNit(long nit) {
		this.nit = nit;
	}
	@Override
	public String toString() {
		return "Universidad [universidadId=" + universidadId + ", direccion=" + direccion + ", nombre=" + nombre
				+ ", nit=" + nit + "]";
	}
	
	
	
}
