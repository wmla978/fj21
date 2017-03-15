package br.com.caelum.agenda.filtro;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.caelum.agenda.ConnectionFactory;
import sun.rmi.transport.Connection;

@WebFilter ("/*")
public class FiltroConexao implements Filter{
	
	@Override 
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		try {
			Connection connection = (Connection) new ConnectionFactory().getConnection();
			
			// pendurando a connection na requisicao
			request.setAttribute("conexao", connection);
			
			chain.doFilter(request, response);
		
			connection.close();
		} catch (SQLException e) {
			throw new ServletException (e);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	


}
