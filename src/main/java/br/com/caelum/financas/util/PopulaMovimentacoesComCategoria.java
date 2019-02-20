package br.com.caelum.financas.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class PopulaMovimentacoesComCategoria {


	public static void main(String[] args) {

		Categoria cat1 = new Categoria("Viagem");		
		Categoria cat2 = new Categoria("Negocios");
		
		Conta conta = new Conta();
		conta.setId(2);

		Movimentacao mov1 = new Movimentacao();
		mov1.setData(Calendar.getInstance());
		mov1.setDescricao("Viagem ao Rio");
		mov1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		mov1.setValor(new BigDecimal("200.0"));
		mov1.setCategoria(Arrays.asList(cat1, cat2));
		mov1.setConta(conta);

		
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Movimentacao mov2 = new Movimentacao();
		mov2.setData(amanha);
		mov2.setDescricao("Viagem ao Mato Grosso");
		mov2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		mov2.setValor(new BigDecimal("300.0"));
		mov2.setCategoria(Arrays.asList(cat1, cat2));
		mov2.setConta(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(cat1);
		em.persist(cat2);
		
		em.persist(mov1);
		em.persist(mov2);
		
		em.getTransaction().commit();

	}

}
