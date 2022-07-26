package py.edu.ucom.is2.proyectocamel.tarea2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

//@Component
public class SetearIdandFecha {

	public BancoRequest genId(BancoRequest setearIdandFecha) {
		int int_random = ThreadLocalRandom.current().nextInt(1 , 1000000) ;  
		setearIdandFecha.setId_transaccion(int_random);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   LocalDateTime now = LocalDateTime.now();
		   setearIdandFecha.setFecha(dtf.format(now));
		return setearIdandFecha;
	}
}