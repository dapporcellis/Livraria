/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author dappo
 */
@WebFilter(filterName = "Admin", urlPatterns = {"/admin/*"})
public class Admin implements Filter {
        
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String path = ((HttpServletRequest)request).getRequestURI();
        Usuario u = (Usuario) ((HttpServletRequest)request).getSession().getAttribute("admin");
        
        if(u!=null || path.startsWith("/Livraria2019/admin/login/")){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect("../login/admin.jsp?erro=logout");
            //chain.doFilter(request, response);
        }
    }
        
    @Override
    public void init(FilterConfig fc) {
        
    }
    @Override
    public void destroy(){
        
    }
    
}
