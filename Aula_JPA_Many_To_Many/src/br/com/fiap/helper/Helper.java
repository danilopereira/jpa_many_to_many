package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Funcionario;

public class Helper {
	EntityManager em;
	
	public Helper(EntityManager em) {
		this.em = em;
	}
	
	public void salvar(Funcionario funcionario) throws Exception{
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}
	
	//JPQL: using Query
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFuncionarios(){
		Query query = em.createQuery("select f from Funcionario f");
		return query.getResultList();
	}
	
	public Funcionario buscarFuncionario(String matricula){
		Query query = em.createQuery("select f from Funcionario f where matricula = :matricula");
		query.setParameter("matricula", matricula);
		
		return (Funcionario) query.getSingleResult();
	}
	
	//JPQL: using NamedQuery
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodos(){
		Query query = em.createNamedQuery("Funcionario.findAll");
		return query.getResultList();
	}

}
