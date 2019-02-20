package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteTodasAsMovimentacoesDasContas {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		MovimentacaoDao dao = new MovimentacaoDao(em);

		List<Conta> contas = dao.getContasEMovimentacoes();

		for (Conta conta : contas) {
			System.out.println(conta.getTitular());
			System.out.println(conta.getMovimentacoes());
		}

	}

}
