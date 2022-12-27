package controllers;

import java.io.File;
import java.util.List;

import models.Esporte;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Esportes extends Controller {
	public static void form() {
		render();
	}

	public static void salvar(Esporte p) {

		// foto.renameTo(new File("./uploads/" + foto.getName()));

		p.save();
		form();
	}

	public static void listar() {
		List<Esporte> lista = Esporte.findAll();
		render(lista);
	}

	public static void editar(Long id) {
		Esporte eve = Esporte.findById(id);
		renderTemplate("Esportes/form.html", eve);
	}

	public static void deletar(Long id) {
		Esporte eve = Esporte.findById(id);
		eve.delete();

		listar();
	}

	public static void mostrarImagem(Long id) {
		Esporte e = Esporte.findById(id);
		renderBinary(e.imagem.getFile());

	}
	
	
}
