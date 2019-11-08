/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import util.Criptografia;


/**
 *
 * @author dappo
 */
@WebServlet(name = "UsuarioWS", urlPatterns = {"/admin/usuario/UsuarioWS"})
public class UsuarioWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("txtAcao");
        Long id;
        UsuarioDAO dao = new UsuarioDAO();
        RequestDispatcher destino;
        String pagina;
        List<Usuario> usuarios = null;
        Usuario obj;
        boolean deucerto;        
        switch(String.valueOf(acao)){
            case "del":
                //comandos para deletar
                Usuario logado = (Usuario) request.getSession().getAttribute("admin");
                id = Long.parseLong(request.getParameter("txtId"));
                obj = dao.buscarPorChavePrimaria(id);
                if(!Objects.equals(obj.getId(), logado.getId())){
                    deucerto = dao.excluir(obj);
                }else{
                    deucerto = false;
                }
                if(deucerto){
                    request.setAttribute("msg", "Excluído com sucesso!");
                }else{
                    request.setAttribute("msg", "Problemas ao excluir!");
                }
                usuarios = dao.listar();
                pagina = "list.jsp";
                request.setAttribute("usuarios", usuarios);
                break;
            case "edit":
                //comandos para editar
                id = Long.parseLong(request.getParameter("txtId"));
                obj = dao.buscarPorChavePrimaria(id);
                request.setAttribute("usuario", obj);
                pagina = "edit.jsp";
                break;
            case "sair":
                request.getSession().invalidate();
                //response.sendRedirect("../login/admin.jsp?erro=permissao");  
                pagina = "../login/admin.jsp?erro=permissao";
            default:
                String filtro = request.getParameter("txtFiltro");
                if (filtro == null) {
                    usuarios = dao.listar();
                } else {
                    try {
                        usuarios = dao.listar(filtro);
                    } catch (Exception ex) {
                        Logger.getLogger(AutorWS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                pagina = "list.jsp";
                request.setAttribute("usuarios", usuarios);
                break;                    
        }
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean deucerto;
        
        UsuarioDAO dao = new UsuarioDAO();
        String msg;
        String pagina;
        RequestDispatcher destino;
        
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String foto = request.getParameter("txtFoto");
        //criptografia
        String senha = request.getParameter("txtSenha");
        String senhacript=null;
        try {
            senhacript = Criptografia.convertPasswordToMD5(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String id = request.getParameter("txtId");
        
        Usuario obj;
        if(id==null){
            //adicionando
            obj = new Usuario();
        }else{
            //editando
            obj = dao.buscarPorChavePrimaria(Long.parseLong(id));
        }
        
        obj.setNome(nome);
        obj.setEmail(email);
        obj.setFoto(foto);
        obj.setSenha(senhacript);
           
        if(id==null){
            deucerto = dao.incluir(obj);
            if (deucerto) {
                msg = "Adicionado com sucesso!";
            } else {
                msg = "Problemas ao adicionar autor!";
            }
            pagina = "add.jsp";
        }else{
            deucerto = dao.alterar(obj);
            if (deucerto) {
                msg = "Editado com sucesso!";
            } else {
                msg = "Problemas ao editar autor!";
            }
            List<Usuario> usuarios = dao.listar();
            pagina = "list.jsp";
            request.setAttribute("usuarios", usuarios);
        }       
        
        request.setAttribute("msg", msg);
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
    
    }

 

}
