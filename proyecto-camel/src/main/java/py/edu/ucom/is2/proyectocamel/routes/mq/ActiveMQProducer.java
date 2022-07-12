package py.edu.ucom.is2.proyectocamel.routes.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.helper.MessageGenerator;

//@Component
public class ActiveMQProducer extends RouteBuilder{

	@Autowired
	MessageGenerator gen;
	
	@Override
	public void configure() throws Exception {
		from("timer:active-mq-timer?period=2000") //generamos un mensaje nulo cada 2 segundos
		.process(gen) //modificamos el mensaje con el ID generado
		.log("Mensaje enviado> ${body}") //logueamos el body del mensaje
		.to("activemq:IS2"); //encolamos el mensaje a la cola del Apache ActiqveMQ IS2
		
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
