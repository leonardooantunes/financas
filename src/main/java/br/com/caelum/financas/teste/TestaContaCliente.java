package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaContaCliente {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(1);

		Cliente cli = new Cliente();
		cli.setConta(conta);
		cli.setNome("José");
		cli.setProfissao("Engenheiro");
		cli.setEndereco("Faria lima numero 610");

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(cli);

		em.getTransaction().commit();
		em.close();

	}

}
