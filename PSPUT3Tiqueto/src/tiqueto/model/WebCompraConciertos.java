package tiqueto.model;

import tiqueto.EjemploTicketMaster;
import tiqueto.IOperacionesWeb;

import java.util.concurrent.atomic.AtomicInteger;

public class WebCompraConciertos implements IOperacionesWeb{
	public WebCompraConciertos() {
		super();
	}
	private int entradasVendidas = 0;
	public int entradasDisponibles = 0;
	public boolean isVentaCerrada = false;

	/**
	 * Metodo que intenta comprar entradas si hay disponibles. Si no es asi duerme con .wait()
	 * @return Boolean que indica si ha comprado la entrada
	 */
	@Override
	public synchronized boolean comprarEntrada() {
		if (hayEntradas()) {
			entradasVendidas++;
			entradasDisponibles--;
            mensajeWeb("Entrada comprada: " + entradasRestantes() + " quedan entradas restantes.");
			return true;
		}else {
			try {
				mensajeWeb("No hay entradas disponibles.");
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " +e);
			}
			return false;
		}
	}

	/**
	 * Método para reponer las entradas disponibles
	 * @param numeroEntradas Número de entradas a reponer
	 * @return Número de entradas disponibles depues de ser repuestas
	 */
	@Override
	public synchronized int reponerEntradas(int numeroEntradas) {
		notifyAll();
//		return this.entradasDisponibles += numeroEntradas;

		if (entradasVendidas + entradasDisponibles + numeroEntradas > EjemploTicketMaster.TOTAL_ENTRADAS ){
			return entradasDisponibles += EjemploTicketMaster.TOTAL_ENTRADAS - (entradasDisponibles + entradasVendidas);
		}else {
			return this.entradasDisponibles += numeroEntradas;
		}
	}

	/**
	 * Método para impedir que los fans sigan comprado entradas
	 */
	@Override
	public synchronized void cerrarVenta() {
		this.isVentaCerrada = true;
		notifyAll();
	}

	/**
	 * Método para conocer si hay entradas disponibles para ser compradas
	 * @return Boolean para saber si hay entradas disponibles
	 */
	@Override
	public synchronized boolean hayEntradas() {
        return entradasDisponibles > 0;
	}


	/**
	 * Método para conocer las entradas restantes que se pueden vender
	 * @return Número de entradas restantes
	 */
	@Override
	public synchronized int entradasRestantes() {
        return EjemploTicketMaster.TOTAL_ENTRADAS - entradasVendidas;
	}


	/**
	 * Método a usar para cada impresión por pantalla
	 * @param mensaje Mensaje que se quiere lanzar por pantalla
	 */
	private void mensajeWeb(String mensaje) {
		System.out.println(System.currentTimeMillis() + "| WebCompra: " + mensaje);
	}
}
