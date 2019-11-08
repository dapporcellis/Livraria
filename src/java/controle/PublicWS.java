/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autor;
import modelo.Editora;
import modelo.Genero;
import modelo.Livro;

/**
 *
 * @author dappo
 */
@WebServlet(name = "PublicWS", urlPatterns = {"/public/PublicWS"})
public class PublicWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina = "index.jsp";
        GeneroDAO gdao = new GeneroDAO();
        List<Genero> generos = gdao.listar();
        request.setAttribute("generos", generos);

        AutorDAO adao = new AutorDAO();
        List<Autor> autores = adao.listar();
        request.setAttribute("autores", autores);

        EditoraDAO edao = new EditoraDAO();
        List<Editora> editoras = edao.listar();
        request.setAttribute("editoras", editoras);

        LivroDAO ldao = new LivroDAO();
        String acao = request.getParameter("txtAcao");
        Long id;
        List<Livro> livros;
        switch (String.valueOf(acao)) {
            case "listGenero":
                id = Long.parseLong(request.getParameter("id"));
                livros = ldao.listarGenero(id);
                request.setAttribute("livros", livros);
                break;
            case "listAutor":
                id = Long.parseLong(request.getParameter("id"));
                livros = ldao.listarAutor(id);
                request.setAttribute("livros", livros);
                break;
            case "listEditora":
                id = Long.parseLong(request.getParameter("id"));
                livros = ldao.listarEditora(id);
                request.setAttribute("livros", livros);
                break;
            default:
                livros = ldao.listar();
                request.setAttribute("livros", livros);
        }
        
       
        RequestDispatcher destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
