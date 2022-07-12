package py.edu.ucom.is2.proyectocamel.routes.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQTopicConsumer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:topic:whatsappMQ")
		.transform().simple("consumidor ID 1> ${body}")
		.to("log:is2log");
		
	}
}