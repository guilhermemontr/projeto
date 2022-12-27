package controllers;

import java.io.File;

import java.util.List;

import models.Curso;
import models.Evento;
import play.mvc.Controller;
import play.mvc.With;


@With(Seguranca.class)

public class Eventos extends Controller{
	
	
	public static void form() {
		render();
	}
	
	public static void salvar(Evento p) {
		
		
		
		p.save();
		form();
	}
	
	public static void listar() {
		String termo = params.get("termo");
		
		List<Evento> lista;
		if(termo == null) {
			lista = Evento.findAll();
		}else {
			lista = Evento.find("nome like ?1 or descriçao like ?1", "%"+termo+"%").fetch();
		}
		
		render(lista, termo);
	}
	
	public static void editar(Long id) {
		Evento eve = Evento.findById(id);
		renderTemplate("Eventos/form.html", eve);
	}
	
	public static void deletar(Long id) {
		Evento eve = Evento.findById(id);
		eve.delete();
		
		listar();
		}
	
	public static void mostrarImagem(Long id) {
		Evento eve = Evento.findById(id);
		renderBinary(eve.imagem.getFile());

	}
}
