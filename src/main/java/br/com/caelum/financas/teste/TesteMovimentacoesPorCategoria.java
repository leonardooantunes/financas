package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.ContaComNumeroEAgencia;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categ = new Categoria();
		categ.setId(1);

		Query query = em.createQuery("select m from Movimentacao m join m.categoria c where c = :pCategoria");
		query.setParameter("pCategoria", categ);

		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println(movimentacao.getDescricao());
			System.out.println(movimentacao.getConta().getId());
		}
		
		List<ContaComNumeroEAgencia> contas = em
				.createQuery("select new br.com.caelum.financas.modelo.ContaComNumeroEAgencia(c.numero, c.agencia) from Conta c", ContaComNumeroEAgencia.class)
				.getResultList();
		
		for (ContaComNumeroEAgencia contaComNumeroEAgencia : contas) {
			System.out.println(contaComNumeroEAgencia.getAgencia());
			System.out.println(contaComNumeroEAgencia.getNumeroConta());
		}

		em.getTransaction().commit();
		em.close();

	}

}
