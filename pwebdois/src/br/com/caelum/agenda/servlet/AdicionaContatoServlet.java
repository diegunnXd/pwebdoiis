package br.com.caelum.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

/**
 * Servlet implementation class AdicionaContatoServlet
 */
@WebServlet("/AdicionaContatoServlet")

public class AdicionaContatoServlet extends HttpServlet {
	protected void service(HttpServletRequest request,
            	HttpServletResponse response)
            	throws IOException, ServletException {
		// busca o writer
		PrintWriter out = response.getWriter();

	// buscando os parâmetros no request
	String nome = request.getParameter("nome");
	String endereco = request.getParameter("endereco");
	String email = request.getParameter("email");
	String dataEmTexto = request
    	.getParameter("dataNascimento");
	Calendar dataNascimento = null;

	// fazendo a conversão da data
	try {
		Date date =
        	ew SimpleDateFormat("dd/MM/yyyy")
        	.parse(dataEmTexto);
		dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(date);
	} catch (ParseException e) {
		out.println("Erro de conversão da data");
		return; //para a execução do método
	}

		// monta um objeto contato
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);

		// salva o contato
		ContatoDao dao = new ContatoDao();
		dao.adiciona(contato);

		// imprime o nome do contato que foi adicionado
		out.println("<html>");
		out.println("<body>");
		out.println("Contato " + contato.getNome() +
		" adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
}
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdicionaContatoServlet() {
    	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
