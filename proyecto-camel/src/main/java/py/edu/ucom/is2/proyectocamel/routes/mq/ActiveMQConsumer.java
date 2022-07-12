package py.edu.ucom.is2.proyectocamel.routes.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQConsumer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:is2")
		.log("Mensaje recibido ${body}")
		.to("log:is2log");
	}
}
