package py.edu.ucom.is2.proyectocamel.routes;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class TimerTest extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// Timer Componente endpoint
		// transformaciones 
		// logger
		from("timer:mytimer")
		.bean("reloj","getSalida")
		.log("${body}")
		.to("log:mylogger");
	}
}

@Component
class Relojss {
	
	public String getHora() {
		return ("Hora actual es :"+LocalDateTime.now());	
	}
	public String getSalida() {
		return ("Hora salida es :"+LocalDateTime.now());	
	}

}
//Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]

