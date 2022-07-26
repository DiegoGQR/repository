package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import java.util.Random;

import org.springframework.stereotype.Component;

//@Component
public class GeneradorId {
	public int generadorId() {
		Random rand = new Random();
		int max = 1000000;
		int min = 0;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}