package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Esporte extends Model{
	
	public String nome;
	public String descriçao;
	public String programas;
	
	public Blob imagem;

}
