package py.edu.ucom.is2.proyectocamel.routes.filter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FilterRouter extends RouteBuilder {
	@Autowired
	GeneradorNumero generador;

	@Override
	public void configure() throws Exception {
		from("timer:mytimer?period=1000")
		.bean(generador) // transformacion del Mensaje
		.log("${body}")
		.filter().method(Filtro5.class, "divisible")
			.to("log:logger5")
		.end()
		.to("log:loggerotros");
		
	}
}
