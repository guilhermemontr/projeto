package controllers;

import play.mvc.Controller;
import play.mvc.Before;

public class Seguranca extends Controller{
	
	@Before
	static void verificar() {
		
		
		if(session.contains("usuario.email") == false) {
			Login.form();
		}
	}
	
	@Before(unless={"Usuarios.listar"})
	static void permissoes() {
		
		verificar();
		
		if(session.get("usuario.nivel").equals("1") == false) {
			renderText("Acesso negado");
		}
	}
}
