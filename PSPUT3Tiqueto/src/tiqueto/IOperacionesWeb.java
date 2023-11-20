package tiqueto;

public interface IOperacionesWeb {

	/**
	 * Realiza una compra de una sola entrada. Es decir, decrementa el número de entradas disponibles.
	 * IMPORTANTE: La implementacion de este metodo deberá ser marcado como synchronized.
	 * 
	 * @return true si se ha podido comprar una entrada (es decir, si todavía estaba activa la venta y había entradas disponibles)
	 * 		   false en caso contrario (es decir, no se ha podido comprar una entrada porque no haya más o se haya cerrado la venta)
	 */
	boolean comprarEntrada();
	
	/**
	 * Realiza una reposición de entradas. Es decir, incrementará el número de entradas disponibles.
	 * IMPORTANTE: La implementación de este método deberá ser marcado como synchronized.
	 *  
	 * @param numeroEntradas Número de entradas a reponer
	 * @return Número de entradas disponibles tras la reposición
	 */
	int reponerEntradas(int numeroEntradas);
	
	/**
	 * Cierra la venta de entradas. Por lo tanto, quien quiera comprar entradas no podrá hacerlo.
	 * PISTA: esto deberá modificar una condición general.
	 * IMPORTANTE: La implementación de este método deberá ser marcado como synchronized.
	 */
	void cerrarVenta();
	
	/**
	 * Informa si todavía hay entradas disponibles
	 * IMPORTANTE: La implementación de este método deberá ser marcado como synchronized.
	 * 
	 * @return El número de entradas disponibles en la web de compra de conciertos
	 */
	boolean hayEntradas();
	
	/**
	 * Informa de las entradas restantes.
	 * IMPORTANTE: La implementación de este método deberá ser marcado como synchronized.
	 * @return Número de entradas restantes en el momento de la solicitud
	 */
	int entradasRestantes();
}
