package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;


@Entity
public class Evento extends Model {
	
	public String nome;
	public String descriçao;
	public String link;
	
	public Blob imagem;
	
	public String dataIni;
	public String dataFim;

}
