package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		MovimentacaoDao dao = new MovimentacaoDao(em);

		Double media = dao.getMediaPorContaETipoSaida(conta, TipoMovimentacao.SAIDA);
		
		System.out.println("A media é: " +  media);

		em.getTransaction().commit();
		em.close();

	}

}
