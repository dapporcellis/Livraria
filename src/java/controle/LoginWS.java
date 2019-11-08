/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LoginWS", urlPatterns = {"/admin/login/LoginWS"})
public class LoginWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Usar para sair do sistema
        request.getSession().invalidate();
        response.sendRedirect("admin.jsp?erro=logout");
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Usar para logar no sistema
        String login = request.getParameter("txtEmail");
        //criptografar
        String senha = null;
        
        try {
            senha = Criptografia.convertPasswordToMD5(request.getParameter("txtSenha"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.logar(login,senha);
        if(u!=null){
            //logar
            request.getSession().setAttribute("admin", u);
            response.sendRedirect("../usuario/UsuarioWS");
        }else{
            //deu erro na senha
            response.sendRedirect("admin.jsp?erro=senha");
        }
    }

}
