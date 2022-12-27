package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Curso extends Model{
	
	@Required
	public String nome;
	
	@Required
	public String descriçao;
	
	public String insta;
	
	public String modalidade;
	
	public Blob imagem;

}
