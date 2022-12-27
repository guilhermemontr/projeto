package controllers;


import java.io.File;

import java.util.List;


 

import models.Curso;
import models.Esporte;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;


@With(Seguranca.class)

		public class Cursos extends Controller{
	
			public static void form() {
				Curso eve = (Curso)Cache.get("eve");
				Cache.clear();
				render(eve);
			}
			
			public static void salvar(@Valid Curso p) {
				
				
				if(validation.hasErrors()) {
					validation.keep();
					
					flash.error("Falha ao salvar.");
					Cache.set("eve", p);
					
					form();
				}
				
				p.save();
				
				flash.success("Curso cadastrado com sucesso.");
				form();
			}
			
			public static void listar() {
				List<Curso> lista = Curso.findAll();
				render(lista);
			}
			
			public static void editar(Long id) {
				Curso eve = Curso.findById(id);
				renderTemplate("Cursos/form.html", eve);
			}
			
			public static void deletar(Long id) {
				Curso eve = Curso.findById(id);
				eve.delete();
				
				flash.success("Curso removido.");
				
				listar();
				}
			
			
			public static void mostrarImagem(Long id) {
				Curso c = Curso.findById(id);
				renderBinary(c.imagem.getFile());

			}
}
