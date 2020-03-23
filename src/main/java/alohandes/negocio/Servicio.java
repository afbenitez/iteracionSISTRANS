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
 * Clase para modelar el concepto servicio del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Servicio {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long servicoId;
	/**
	 * id (?)
	 */
	private String id;
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
	public Servicio()
	{
		this.servicoId = 0;
		this.id = "";
		this.nombre ="";
	}
	/**
	 * @param servicoId
	 * @param id
	 * @param nombre
	 */
	public Servicio(long servicoId, String id, String nombre) {

		this.servicoId = servicoId;
		this.id = id;
		this.nombre = nombre;
	}
	/**
	 * @return the servicoId
	 */
	public long getServicoId() {
		return servicoId;
	}
	/**
	 * @param servicoId the servicoId to set
	 */
	public void setServicoId(long servicoId) {
		this.servicoId = servicoId;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
		return "Servicio [servicoId=" + servicoId + ", id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
