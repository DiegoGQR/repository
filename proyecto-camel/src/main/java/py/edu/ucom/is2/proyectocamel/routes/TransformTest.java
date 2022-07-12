package py.edu.ucom.is2.proyectocamel.routes;

import org.apache.camel.builder.RouteBuilder;

//@Component
public class TransformTest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// timer (componente endpoint)
		// transformaciones
		// logger
		from("timer:mytimer?period=5000")
		.log("${body}")
		.transform().constant("Mensaje constante") //Transformación del mensaje
		.log("${body}")
		.to("log:mylogger");
	}
}
