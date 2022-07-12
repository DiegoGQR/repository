package py.edu.ucom.is2.proyectocamel.routes.rest.bean;

import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.rest.tipos.AlumnoRequest;
import py.edu.ucom.is2.proyectocamel.routes.rest.tipos.AlumnoResponse;

//@Component
public class AlumnoService {
	public AlumnoResponse insertarAlumno(AlumnoRequest alumnoRequest) {
			AlumnoResponse respuesta = new AlumnoResponse();
			//CONECTARSE A LA BASE DE DATOS
			respuesta.setMensaje("Alumno "+alumnoRequest.getNombre() + " "+ alumnoRequest.getApellido()+ " insertado con exito");
			return respuesta;
	}
}