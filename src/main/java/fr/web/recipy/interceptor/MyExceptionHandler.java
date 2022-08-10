package fr.web.recipy.interceptor;

import fr.dawan.AppliCFABack.dto.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { TokenException.class })
	protected ResponseEntity<?> handleTokenException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		// .. ajouter des entêtes dans la rponse si besoin

		// On crée notre propre objet de réponse
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		pw.close();
		Logger.getAnonymousLogger().log(Level.SEVERE, sw.toString());
		
		APIError myError = new APIError(HttpStatus.UNAUTHORIZED, ex.getMessage());
		//On peut remplacer l'objet myError par une Map<String,Object>
		return handleExceptionInternal(ex, myError, headers, HttpStatus.UNAUTHORIZED, request);
	}
	
	@ExceptionHandler(value = { IOException.class })
	protected ResponseEntity<?> handleIOException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		// .. ajouter des entêtes dans la rponse si besoin

		// On crée notre propre objet de réponse
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		pw.close();
		Logger.getAnonymousLogger().log(Level.SEVERE, sw.toString());
		
		APIError myError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		//On peut remplacer l'objet myError par une Map<String,Object>
		return handleExceptionInternal(ex, myError, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		// .. ajouter des entêtes dans la rponse si besoin

		// On crée notre propre objet de réponse
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		pw.close();
		Logger.getAnonymousLogger().log(Level.SEVERE, sw.toString());
		
		APIError myError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		//On peut remplacer l'objet myError par une Map<String,Object>
		return handleExceptionInternal(ex, myError, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}

