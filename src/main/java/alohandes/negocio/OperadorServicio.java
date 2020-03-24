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
 * Clase para modelar el concepto operador-servicio del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class OperadorServicio {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id de servicio
	 */
	private long servicioId;
	/**
	 * id de operador
	 */
	private long operadorId;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor
	 */
	public OperadorServicio()
	{
		this.servicioId = 0;
		this.operadorId =0;
	}
	/**
	 * @param servicioId
	 * @param operadorId
	 */
	public OperadorServicio(long servicioId, long operadorId) {
	
		this.servicioId = servicioId;
		this.operadorId = operadorId;
	}
	/**
	 * @return the servicioId
	 */
	public long getServicioId() {
		return servicioId;
	}
	/**
	 * @param servicioId the servicioId to set
	 */
	public void setServicioId(long servicioId) {
		this.servicioId = servicioId;
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
		return "OperadorServicio [servicioId=" + servicioId + ", operadorId=" + operadorId + "]";
	}
	
}
