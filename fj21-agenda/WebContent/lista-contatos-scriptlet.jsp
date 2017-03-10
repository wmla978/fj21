<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page import="java.util.*,
   java.text.SimpleDateFormat,
   br.com.caelum.agenda.dao.*,
   br.com.caelum.agenda.modelo.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
  <table>
       <%
          ContatoDao dao = new ContatoDao();
          List<Contato> contatos = dao.getLista();
          
           for (Contato contato : contatos) {
        	   
          %>
           <tr>
              <td><%=contato.getNome() %></td>
              <td><%=contato.getEmail() %></td>
              <td><%=contato.getEndereco() %> </td>
              <td><%=contato.getDataNascimento().getTime() %></td>
           </tr>
          <%
          }
          %>
          
  </table>
  
   
</body>
</html>