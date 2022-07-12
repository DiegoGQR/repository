package py.edu.ucom.is2.proyectocamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FileRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		//copia archivos de la carpeta in a la carpeta output
		from("file:archivos/input/")
		.log("${body}")
		.to("file:archivos/ouput/");
	}

}
