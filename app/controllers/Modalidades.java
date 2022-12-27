package controllers;

import java.util.List;

import models.Modalidade;
import play.mvc.Controller;

public class Modalidades extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void salvar(Modalidade mod) {
		mod.save();
		editar(mod.id);
	}
	
	public static void listar() {
		List<Modalidade> lista = Modalidade.findAll();
		
		render(lista);
	}
	
	public static void editar(long id) {
		Modalidade m = Modalidade.findById(id);
		
		List<Modalidade> modalidades = Modalidade.findAll();
		
		renderTemplate("Modalidades/form.html", m, modalidades);
	}
	
	public static void deletar(long id) {
		Modalidade mod = Modalidade.findById(id);
		mod.delete();
		
		listar();
	}
}
