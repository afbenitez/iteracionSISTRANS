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
 * Clase para modelar el concepto viviendas Universitaria del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class ViviendaUniveristaria {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long viviendaUniversitariaId;
	/*
	 * direccion
	 */
	private String direccion;
	/**
	 * nombre
	 */
	private String nombre;
	/**
	 * capacidad
	 */
	private int capacidad;
	/*
	 * id del  operador
	 */
	private long operadorId;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * contructor 
	 */
	public ViviendaUniveristaria()
	
	{
		this.viviendaUniversitariaId =0;
		this.direccion ="";
		this.nombre ="";
		this.capacidad =0;
		this.operadorId =0;
	}
	/**
	 * @param viviendaUniversitariaId
	 * @param direccion
	 * @param nombre
	 * @param capacidad
	 * @param operadorId
	 */
	public ViviendaUniveristaria(long viviendaUniversitariaId, String direccion, String nombre, int capacidad,
			long operadorId) {
	
		this.viviendaUniversitariaId = viviendaUniversitariaId;
		this.direccion = direccion;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.operadorId = operadorId;
	}
	/**
	 * @return the viviendaUniversitariaId
	 */
	public long getViviendaUniversitariaId() {
		return viviendaUniversitariaId;
	}
	/**
	 * @param viviendaUniversitariaId the viviendaUniversitariaId to set
	 */
	public void setViviendaUniversitariaId(long viviendaUniversitariaId) {
		this.viviendaUniversitariaId = viviendaUniversitariaId;
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
	@Override
	public String toString() {
		return "ViviendaUniveristaria [viviendaUniversitariaId=" + viviendaUniversitariaId + ", direccion=" + direccion
				+ ", nombre=" + nombre + ", capacidad=" + capacidad + ", operadorId=" + operadorId + "]";
	}
	
}
