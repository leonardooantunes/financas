package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPARelacionamento {

	public static void main(String[] args) {

        Conta conta = new Conta();
        conta.setTitular("Leonardo");
        conta.setBanco("102");
        conta.setAgencia("200");
        conta.setNumero("300");
        
        Movimentacao mov = new Movimentacao();
        mov.setConta(conta);
        mov.setData(Calendar.getInstance());
        mov.setDescricao("Churrasco");
        mov.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        mov.setValor(new BigDecimal("200.0"));
		
		EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        em.persist(conta);
        em.persist(mov);
        em.getTransaction().commit();

	}

}
