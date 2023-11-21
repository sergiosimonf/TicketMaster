package tiqueto;

import java.util.ArrayList;
import java.util.List;

import tiqueto.model.FanGrupo;
import tiqueto.model.PromotoraConciertos;
import tiqueto.model.WebCompraConciertos;

public class EjemploTicketMaster {

	// Total de entradas que se venderán
	public static int TOTAL_ENTRADAS = 10;

	// El número de entradas que reponerá cada vez el promotor
	public static int REPOSICION_ENTRADAS = 2;

	// El número máximo de entradas por fan
	public static int MAX_ENTRADAS_POR_FAN = 10;

	// El número total de fans
	public static int NUM_FANS = 5;

	public static void main(String[] args) throws InterruptedException {

		String mensajeInicial = "[ Empieza la venta de tickets. Se esperan %d fans, y un total de %d entradas ]";
		System.out.println(String.format(mensajeInicial, NUM_FANS, TOTAL_ENTRADAS));
		WebCompraConciertos webCompra = new WebCompraConciertos();

		PromotoraConciertos liveNacion = new PromotoraConciertos(webCompra);
		List<FanGrupo> fans = new ArrayList<>();

		// Creamos todos los fans
		for (int numFan = 1; numFan <= NUM_FANS; numFan++) {
			FanGrupo fan = new FanGrupo(webCompra, numFan);
			fans.add(fan);
			fan.start();
		}

		//Lanzamos al promotor para que empiece a reponer entradas
		liveNacion.start();

		//Esperamos a que el promotor termine, para preguntar a los fans cuántas entradas tienen compradas
		liveNacion.join();

		System.out.println("\n[ Terminada la fase de venta - Sondeamos a pie de calle a los compradores ] \n");
		System.out.println("\nTotal entradas ofertadas: " + TOTAL_ENTRADAS);
		System.out.println("Total entradas disponibles en la web: " + webCompra.entradasRestantes() + "\n");

		// Les preguntamos a cada uno
		for (FanGrupo fan : fans) {
			fan.dimeEntradasCompradas();
		}

		// Obligamos a terminar la ejecución porque si no se queda eternamente ejecutando aun que no tenga nada que hacer
		System.exit(0);
	}

}
