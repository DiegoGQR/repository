package py.edu.ucom.is2.proyectocamel.routes.filter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FilterSimple extends RouteBuilder {
	@Autowired
	GeneradorNumero generador;

	@Override
	public void configure() throws Exception {
		from("timer:mytimer?period=1000")
		.bean(generador) // transformacion del Mensaje
		.log("${body}")
		.filter(simple("${body} == 1"))
		.to("log:mylogger");
	}
}