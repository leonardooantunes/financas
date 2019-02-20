package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.ContaComNumeroEAgencia;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {
	EntityManager em;

	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<Double> getMediaPorDia(Conta conta) {

		String jpql = "select avg(valor) from Movimentacao m where m.conta = :pConta group by day(data), month(data), year(data)";
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		return query.getResultList();

	}

	public Long getTotalMovimentacao(Conta conta, TipoMovimentacao saida) {
		TypedQuery<Long> query = em.createNamedQuery("TotalMovimentacao", Long.class);		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);
		return (Long) query.getSingleResult();
	}

	public Double getMediaPorContaETipoSaida(Conta conta, TipoMovimentacao saida) {
		Query query = em.createQuery(
				"select avg(valor) from Movimentacao m where m.conta = :pConta" + " and m.tipoMovimentacao = :pTipo");
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);
		return (Double) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Conta> getContasEMovimentacoes() {

		String sql = "select distinct c from Conta c left join fetch c.movimentacoes";

		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Movimentacao> getMovimentacoes(Conta conta, TipoMovimentacao entrada) {

		Query query = em.createQuery(
				"select m from Movimentacao m where m.conta = :pConta" + " and m.tipoMovimentacao = :pTipo");
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", entrada);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Movimentacao> getMovimentacoesPorCategoria(Categoria categ) {
		Query query = em.createQuery("select m from Movimentacao m join m.categoria c where c = :pCategoria");
		query.setParameter("pCategoria", categ);
		return query.getResultList();
	}

	public List<ContaComNumeroEAgencia> getContaComNumeroEAgencia() {
		String query = "select new br.com.caelum.financas.modelo.ContaComNumeroEAgencia(c.numero, c.agencia) from Conta c";
		return em.createQuery(query, ContaComNumeroEAgencia.class).getResultList();
	}

}
