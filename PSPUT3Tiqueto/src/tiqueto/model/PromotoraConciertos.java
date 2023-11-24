package tiqueto.model;

import tiqueto.EjemploTicketMaster;

import java.util.concurrent.ThreadLocalRandom;

public class PromotoraConciertos extends Thread {

	final WebCompraConciertos webCompra;

	private int entradasRepuestas = 0;

	public PromotoraConciertos(WebCompraConciertos webCompra) {
		super();
		this.webCompra = webCompra;
	}

	/**
	 * Método que repone entradas según se vayan agotado
	 * Duerme por cada vez que repone entradas entre 3 y 8 segundos
	 */
	@Override
	public void run() {
		while (entradasRepuestas != EjemploTicketMaster.TOTAL_ENTRADAS && !webCompra.hayEntradas()) {
			if (!webCompra.hayEntradas()){
				mensajePromotor("Vaya... se han acabado las entradas, tendré que reponerlas");
				entradasRepuestas += webCompra.reponerEntradas(EjemploTicketMaster.REPOSICION_ENTRADAS);
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 8000));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Thread interrupted: " +e);
				}
			}
		}
		webCompra.cerrarVenta();
	}
	
	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajePromotor(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| Promotora: " + mensaje);
		
	}
}
