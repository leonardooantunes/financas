package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaGroupBy {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);
		
		MovimentacaoDao dao = new MovimentacaoDao(em);

		List<Double> total = dao.getMediaPorDia(conta);

		System.out.println("A media do dia 14 é " + total.get(0));
		System.out.println("A media do dia 15 é " + total.get(1));
		System.out.println("A media do dia 16 é " + total.get(2));
		
		em.close();

	}

}
