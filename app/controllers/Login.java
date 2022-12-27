 package controllers;

import java.util.Random;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import models.Usuario;
import play.libs.Crypto;
import play.libs.Mail;
import play.mvc.Controller;

public class Login extends Controller {
	
	
	public static void salvarNovaSenha(String hash, String senha, String senhaConfirmacao) {
		
		Usuario user = Usuario.find("hash = ?1", hash).first();
		
		if(user != null) {
			
			if(senha.equals(senhaConfirmacao)) {
				
				user.senha = senha;
				user.save();
				user.hash = null;
				flash.success("Senha Atualizada.");
				form();
				
			}else {
				flash.error("A senha de confirmação está diferente.", senha);
				digitarNovaSenha(hash);
			}
			
		}else {
			flash.error("Hash inválido.");
			esqueciMinhaSenha();
		}
		
	}
	
	
	public static void digitarNovaSenha(String hash) {
		render();
	}
	
	
	public static void solicitarNovaSenha(String email) {
		
		
		Usuario user = Usuario.find("email = ?1", email).first();
		
		if(user != null) {
			
			Random rand = new Random();
			String hash = Crypto.passwordHash(rand.nextInt(1000)+"");
			
			hash = hash.replace("+", "");
			hash = hash.replace("-", "");
			hash = hash.replace("&", "");
			
			user.hash = hash;
			user.save();
			
			HtmlEmail mail = new HtmlEmail();
			try {
				mail.addTo(email);
				mail.setFrom("guilherm.montr@gmail.com", "Sistema X");
				mail.setSubject("Você solicitou uma nova senha?");
				
				String msg = "<h4>Você solicitou uma nova senha?</h4>";
				msg += "Entre no link abaixo e digite sua nova senha:<br/>";
				msg += "<a href='http://localhost:9000/login/digitarNovaSenha?hash="+hash+"'>http:localhost://9000/login/digitarNovaSenha?hash="+hash+" </a> <br/>";
				msg += "Caso você não tenha pedido uma nova senha, desconsidere este e-mail";
				
				// set the html message
				mail.setHtmlMsg(msg);
				
				Mail.send(mail); 
				
				
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flash.error("Falha ao enviar o e-mail.");
			}
			
		}else {
			flash.error("E-mail não encontrado.");
			esqueciMinhaSenha();
		}
		
		render();
		
		
	}
	
	
	public static void esqueciMinhaSenha() {
		render();
	}
	
	
	public static void form(){
		
		if(Usuario.count() == 0) {
			Usuario u = new Usuario();
			u.email = "admin@admin.com";
			u.senha = "admin";
			u.nivel = 1;
			u.save();
		}
		render();
	}
	
	public static void logar(String email, String senha) {
		
		Usuario usu = Usuario.find("email = ?1 and senha = ?2", 
				 					email, Crypto.passwordHash(senha)).first();
		
		if(usu == null) {
			
			flash.error("Login ou senha inválidos.");
			form();
		}else {
			session.put("usuario.email", usu.email);
			session.put("usuario.nivel", usu.nivel);
			
			Usuarios.listar();
		}
	}
	
	public static void sair() {
		session.clear();
		Login.form();
	}
	
	
}
