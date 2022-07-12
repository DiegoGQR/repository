package py.edu.ucom.is2.proyectocamel.routes.multicast;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.helper.Bean1;
import py.edu.ucom.is2.proyectocamel.helper.Bean2;

//@Component
public class MultiCastTest extends RouteBuilder {

	@Autowired
	Bean1 bean1;
	
	@Autowired
	Bean2 bean2;

	@Override
	public void configure() throws Exception {
		// timer (componente endpoint)
		// transformaciones
		// logger
		from("timer:mytimer?period=2000")
		.transform().simple("Mensaje original")
		.multicast().parallelProcessing()
			.pipeline()
				.to("bean:bean1")
				.to("log:logbean1")
			.end()
			.pipeline()
				.to("bean:bean2")
				.to("log:logbean2")
			.end()
			.to("log:mylogger")
			.to("activemq:is2")
			.to("file:")
		.end();
	}
}
