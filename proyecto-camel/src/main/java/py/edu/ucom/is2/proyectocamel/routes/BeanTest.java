package py.edu.ucom.is2.proyectocamel.routes;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class BeanTest extends RouteBuilder {

	@Autowired
	Reloj relojInyectado;

	@Override
	public void configure() throws Exception {
		// timer (componente endpoint)
		// transformaciones
		// logger
		from("timer:mytimer?period=5000")
		.log("${body}")
		.bean(relojInyectado,"getSalida") //transformacion del Mensaje
		.log("${body}")
		.to("log:mylogger");
	}
}

@Component
class Reloj {
	public String getHora() {
		return ("Hora actual es " + LocalDateTime.now());
	}

	public String getSalida() {
		return ("Hora salida es " + LocalDateTime.now());
	}

}
