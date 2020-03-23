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
 * Clase para modelar el concepto apartamento del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Apartamento {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long apartamentoId;
	/**
	 * id del operador
	 */
	private long operadorId;
	/**
	 * capacidad 
	 */
	private int capcidad;
	/**
	 * Direccion del apto
	 */
	private String direccion;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor 
	 */
	public Apartamento()
	{
		this.apartamentoId = 0;
		this.operadorId = 0 ;
		this.capcidad =0;
		this.direccion = "";
		
	}
	/**
	 * @param apartamentoId
	 * @param operadorId
	 * @param capcidad
	 * @param direccion
	 */
	public Apartamento(long apartamentoId, long operadorId, int capcidad, String direccion) {
	
		this.apartamentoId = apartamentoId;
		this.operadorId = operadorId;
		this.capcidad = capcidad;
		this.direccion = direccion;
	}
	/**
	 * @return the apartamentoId
	 */
	public long getApartamentoId() {
		return apartamentoId;
	}
	/**
	 * @param apartamentoId the apartamentoId to set
	 */
	public void setApartamentoId(long apartamentoId) {
		this.apartamentoId = apartamentoId;
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
	 * @return the capcidad
	 */
	public int getCapcidad() {
		return capcidad;
	}
	/**
	 * @param capcidad the capcidad to set
	 */
	public void setCapcidad(int capcidad) {
		this.capcidad = capcidad;
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
	@Override
	public String toString() {
		return "Apartamento [apartamentoId=" + apartamentoId + ", operadorId=" + operadorId + ", capcidad=" + capcidad
				+ ", direccion=" + direccion + "]";
	}
	
	
}
