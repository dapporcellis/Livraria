/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Livro;

/**
 *
 * @author dappo
 */
public class LivroDAO extends GenericDAO<Livro,Long>{
    public LivroDAO(){
        super(Livro.class);
    }
    public List<Livro> listarClassificacao(Long id) {
      return em.createNamedQuery("Livro.findClassificacao").setParameter("classificacao", id).getResultList();
    }
    public List<Livro> listarGenero(Long id) {
      return em.createNamedQuery("Livro.findGenero").setParameter("genero", id).getResultList();
    }
    public List<Livro> listarAutor(Long id) {
      return em.createNamedQuery("Livro.findAutor").setParameter("autor", id).getResultList();
    }
    public List<Livro> listarEditora(Long id) {
      return em.createNamedQuery("Livro.findEditora").setParameter("editora", id).getResultList();
    }
}
