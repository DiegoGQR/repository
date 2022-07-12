package py.edu.ucom.is2.proyectocamel.routes.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.helper.MessageGenerator;

//@Component
public class ActiveMQTopicProducer extends RouteBuilder{

	@Autowired
	MessageGenerator gen;
	@Override
	public void configure() throws Exception {
		from("timer:active-mq-timer?period=1000").id("Route consumidor")
		.process(gen)
		.to("activemq:topic:whatsappMQ");
		
	}

}

//
//from("timer:simple?period=1000")
//.process()
//    .message(m -> m.setHeader("index", index++ % 3))
//.transform()
//    .message(this::randomBody)
//.choice()
//    .when()
//        .body(String.class::isInstance)
//        .log("Got a String body")
//    .when()
//        .body(Integer.class::isInstance)
//        .log("Got an Integer body")
//    .when()
//        .body(Double.class::isInstance)
//        .log("Got a Double body")
//    .otherwise()
//        .log("Other type message");
