package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		MovimentacaoDao dao = new MovimentacaoDao(em);

		List<Movimentacao> resultado = dao.getMovimentacoes(conta, TipoMovimentacao.ENTRADA);
		
		for (Movimentacao movimentacao : resultado) {
			System.out.println(movimentacao.getDescricao());
			System.out.println(movimentacao.getConta().getTitular());
		}

		em.getTransaction().commit();
		em.close();

	}

}
