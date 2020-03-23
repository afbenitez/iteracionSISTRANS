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
 * Clase para modelar el concepto AGENDA del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Alohandes {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 *id unico de  alohandes 
	 */
	private long idAlohandes;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor
	 */
	public Alohandes()
	{
		this.idAlohandes = 0;
	}
	/**
	 * constructor con valores
	 * @param idAlohandes
	 */
	public Alohandes(long idAlohandes) {

		this.idAlohandes = idAlohandes;
	}
	/**
	 * @return the idAlohandes
	 */
	public long getIdAlohandes() {
		return idAlohandes;
	}
	/**
	 * @param idAlohandes the idAlohandes to set
	 */
	public void setIdAlohandes(long idAlohandes) {
		this.idAlohandes = idAlohandes;
	}
	@Override
	public String toString() {
		return "Alohandes [idAlohandes=" + idAlohandes + "]";
	}
	

}
