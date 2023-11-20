package tiqueto.model;

import tiqueto.EjemploTicketMaster;

import java.util.concurrent.ThreadLocalRandom;

public class FanGrupo extends Thread {

	final WebCompraConciertos webCompra;
	int numeroFan;
	private String tabuladores = "\t\t\t\t";
	int entradasCompradas = 0;

	public FanGrupo(WebCompraConciertos web, int numeroFan) {
		super();
		this.numeroFan = numeroFan;
		this.webCompra = web;
	}

	/**
	 * Método fan que compra entradas mientras pueda por limite de entradas por usuario y disponibilidad
	 * Duerme entre 1 y 3 segundos por cada compra
	 */
	@Override
	public void run() {
		while (!webCompra.isVentaCerrada) {
			if (entradasCompradas != EjemploTicketMaster.MAX_ENTRADAS_POR_FAN){
				mensajeFan("Voy a intentar comprar una entrada... ¡Espero que no me la quiten!");
				if (webCompra.comprarEntrada()){
					entradasCompradas++;
					mensajeFan("¡He conseguido comprar: " + entradasCompradas + " que bien!");
					try {
						Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						System.out.println("Thread interrupted: " +e);
					}
				}
			}
		}
		mensajeFan("¡Como que la venta se ha cerrado solo he podido conseguir " + entradasCompradas + " !");
	}
	
	public void dimeEntradasCompradas() {
		mensajeFan("Sólo he conseguido: " + entradasCompradas + " entradas");
	}
	
	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajeFan(String mensaje) {
		System.out.println(System.currentTimeMillis() + "|" + tabuladores +" Fan "+this.numeroFan +": " + mensaje);
	}
}
