package py.edu.ucom.is2.proyectocamel.tarea2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;

@Component
public class FiltroDeFecha {

	public boolean ValidarFecha(BancoRequest bancoRequest) {
		boolean esFechaValidada;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		String  requestDate = bancoRequest.getFecha();
		
		System.out.println("Fecha de banco Request : "+requestDate);
		
		LocalDate myDate = LocalDate.parse(requestDate,dtf);

	    LocalDate currentDate = LocalDate.now();
		
		long numeroOFDays = ChronoUnit.DAYS.between(myDate,currentDate);
		
		if (dtf.format(now).equals(bancoRequest.getFecha())) {
			esFechaValidada = true;
		}else if(numeroOFDays < 2){
			esFechaValidada = true;
		}else {
			esFechaValidada = false;
		}
		return esFechaValidada; 
	}
}
