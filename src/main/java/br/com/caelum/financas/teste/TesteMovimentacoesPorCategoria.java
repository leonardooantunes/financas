package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.ContaComNumeroEAgencia;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Categoria categ = new Categoria();
		categ.setId(1);		

		MovimentacaoDao dao = new MovimentacaoDao(em);

		List<Movimentacao> resultados = dao.getMovimentacoesPorCategoria(categ);
	
		for (Movimentacao movimentacao : resultados) {
			System.out.println(movimentacao.getDescricao());
			System.out.println(movimentacao.getConta().getId());
		}
		
		List<ContaComNumeroEAgencia> contas = dao.getContaComNumeroEAgencia(); 
				
		for (ContaComNumeroEAgencia contaComNumeroEAgencia : contas) {
			System.out.println(contaComNumeroEAgencia.getAgencia());
			System.out.println(contaComNumeroEAgencia.getNumeroConta());
		}

		em.close();

	}

}
