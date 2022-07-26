package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class IdandFecha {
	private int idtransferencia;
	private Date date;
	
	public int getIdtransferencia() {
		return idtransferencia;
	}

	public void setIdtransferencia(int idtransferencia) {
		this.idtransferencia = idtransferencia;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Serializable string) {
		this.date = (Date) string;
	}

	public IdandFecha idandFecha(IdandFecha idandFecha) {
		int int_random = ThreadLocalRandom.current().nextInt(1 , 999999999) ;  
		idandFecha.setIdtransferencia(int_random);
		
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 	    LocalDateTime now = LocalDateTime.now();
	    System.out.println(fecha.format(now));
 	    idandFecha.setDate(fecha.format(now));

		return idandFecha;
	}
}