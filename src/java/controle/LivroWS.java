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
import java.util.ArrayList;
import java.util.Date;
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
import util.FormataData;

/**
 *
 * @author dappo
 */
@WebServlet(name = "LivroWS", urlPatterns = {"/admin/livro/LivroWS"})
public class LivroWS extends HttpServlet {
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("txtAcao");
        RequestDispatcher destino;
        String pagina;
        LivroDAO dao = new LivroDAO();
        Livro obj;
        List<Livro> livros;
        Boolean deucerto;
        String msg;
        
        //variaveis de lista
        GeneroDAO gdao;
        List<Genero> generos;
        EditoraDAO edao;
        List<Editora> editoras;
        AutorDAO adao;
        List<Autor> autores;
        
        
        switch(String.valueOf(acao)){
            case "add":
                //buscar dados
                gdao = new GeneroDAO();
                edao = new EditoraDAO();
                adao = new AutorDAO();
                generos = gdao.listar();
                editoras = edao.listar();
                autores = adao.listar();
                //enviar dados
                request.setAttribute("generos", generos);
                request.setAttribute("editoras", editoras);
                request.setAttribute("autores", autores);                
                //chamar pagina
                pagina = "add.jsp";
                break;
            case "edit":
                //buscar dados
                gdao = new GeneroDAO();
                edao = new EditoraDAO();
                adao = new AutorDAO();
                generos = gdao.listar();
                editoras = edao.listar();
                autores = adao.listar();
                //enviar dados
                request.setAttribute("generos", generos);
                request.setAttribute("editoras", editoras);
                request.setAttribute("autores", autores);    
                //Abrir tela e buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId")));
                request.setAttribute("obj", obj);
                pagina = "edit.jsp";
                break;
            case "del":
                //excluir dados e buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId")));
                deucerto = dao.excluir(obj);
                if(deucerto){
                    msg = obj.getNome() + " deletado com sucesso!";
                }else{
                    msg = "Problema ao excluir o livro " + obj.getNome();
                }
                livros = dao.listar();
                request.setAttribute("msg", msg);
                request.setAttribute("lista", livros);
                pagina = "list.jsp";
                break;
            default:
                //listar ou listar com filtro
                String filtro = request.getParameter("txtFiltro");
                if(filtro == null){
                    //lista todos
                    livros = dao.listar();
                }else{
                    //lista com filtro
                    try {                
                        livros = dao.listar(filtro);
                    } catch (Exception ex) {
                        livros = dao.listar();
                        msg = "Problema ao filtrar";
                        request.setAttribute("msg", msg);
                    }
                }
                request.setAttribute("lista", livros);
                pagina = "list.jsp";
                break;
        }
        
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //criar variaveis
        Livro obj;
        LivroDAO dao = new LivroDAO();
        Boolean deucerto;
        String msg;
        String pagina;
        RequestDispatcher destino;
        List<Livro> livros;
        
        
        //Receber dados
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("txtId");
        String titulo = request.getParameter("txtTitulo");
        String id_genero = request.getParameter("txtGenero");
        String id_editora = request.getParameter("txtEditora");
        //usado para receber select multiplo
        //String[] ids_autores = request.getParameterValues("txtAutores");
        //usado para receber checkbox
        String[] ids_autores = request.getParameter("txtAutores").split(";");
        String paginas = request.getParameter("txtPaginas");
        String isbn = request.getParameter("txtIsbn");
        String idioma = request.getParameter("txtIdioma");
        String data = request.getParameter("txtData");
        String sinopse = request.getParameter("txtSinopse");
        String foto1 = request.getParameter("txtFoto1");
        String foto2 = request.getParameter("txtFoto2");
        String foto3 = request.getParameter("txtFoto3");
        
        //Tratar os dados (transformar os dados no formato solicitado)
        Integer pg = Integer.parseInt(paginas);
        Date lancamento = FormataData.formata(data, "yyyy-MM-dd");
        GeneroDAO gdao = new GeneroDAO();
        Genero genero = gdao.buscarPorChavePrimaria(Long.parseLong(id_genero));
        EditoraDAO edao = new EditoraDAO();
        Editora editora = edao.buscarPorChavePrimaria(Long.parseLong(id_editora));
        AutorDAO adao = new AutorDAO();
        List<Autor> autores = new ArrayList<>();
        for(String id_autor:ids_autores){
            Autor autor = adao.buscarPorChavePrimaria(Long.parseLong(id_autor));
            autores.add(autor);
        }
        
        
        if(id != null){
            //busca o que existe
           obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
        }else{
            //cria um novo
           obj = new Livro(); 
        }
        
        //adicionar os dados recebidos
        obj.setNome(titulo);
        obj.setPaginas(pg);
        obj.setIsbn(isbn);
        obj.setIdioma(idioma);
        obj.setLancamento(lancamento);
        obj.setSinopse(sinopse);
        obj.setGenero(genero);
        obj.setEditora(editora);
        obj.setAutores(autores);
       
        obj.setFoto1(foto1);
        obj.setFoto2(foto2);
        obj.setFoto3(foto3);
        
        if(id != null){
            deucerto = dao.alterar(obj);
            pagina = "list.jsp";
            livros = dao.listar();
            request.setAttribute("lista", livros);
            if(deucerto){
                msg = obj.getNome() + " alterado com sucesso!";
            }else{
                msg = "Problema ao editar o livro " + obj.getNome();
            }
        }else{
            deucerto = dao.incluir(obj);
            //buscar dados
            gdao = new GeneroDAO();
            edao = new EditoraDAO();
            adao = new AutorDAO();
            List<Genero> generos = gdao.listar();
            List<Editora> editoras = edao.listar();
            autores = adao.listar();
            //enviar dados
            request.setAttribute("generos", generos);
            request.setAttribute("editoras", editoras);
            request.setAttribute("autores", autores);    
            pagina = "add.jsp";
            if(deucerto){
                msg = obj.getNome() + " adicionado com sucesso!";
            }else{
                msg = "Problema ao adicionar o livro " + obj.getNome();
            }
        }
        request.setAttribute("msg", msg);
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);       
        
    }

}
