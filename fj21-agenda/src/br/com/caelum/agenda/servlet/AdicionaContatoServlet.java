package br.com.caelum.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

@WebServlet("/adicionaContato")


public class AdicionaContatoServlet extends HttpServlet {


	protected void service (HttpServletRequest request,
			              HttpServletResponse response) 
	throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		
		// buscando os parametros no request 
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto= request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		
		// fazendo a conversao da data
		try { 
			Date date = 
					(Date) new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			out.println("Erro de conversáo da data");
			return; //para a execucao do metodo
		}
		//monta um obj contato
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);
		
		//salva contato
		ContatoDao dao = new ContatoDao();
		dao.adiciona(contato);
		
	
		// Imprime o nome do contato que foi adicionado
	/*
		out.println("<html>");
		out.println("<body>");
		out.println("Contato " + contato.getNome() + "adicionado com sucesso");
		out.println("</body>");
		out.println("</html>"); 
	 */ 
		
	RequestDispatcher rd = request.getRequestDispatcher("/contato-adicionado.jsp");
	rd.forward(request, response);
		
	}

	
}
