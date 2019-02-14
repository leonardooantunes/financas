package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultasJPA {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		Movimentacao mov1 = em.find(Movimentacao.class, 2);
		
		System.out.println(mov1.getCategoria().size());
		
//		em.getTransaction().begin();		
//		em.getTransaction().commit();
		em.close();

	}

}
