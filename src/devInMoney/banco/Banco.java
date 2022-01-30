package devInMoney.banco;

import java.util.ArrayList;

import devInMoney.conta.Conta;
import devInMoney.conta.ContaCorrente;
import devInMoney.conta.ContaInvestimento;
import devInMoney.conta.ContaPoupanca;
import devInMoney.conta.TiposDeConta;

public class Banco {

	int sequencial = 0;

	ArrayList<Transacao> transacoes = new ArrayList<Transacao>();

	ArrayList<Conta> listaDeContas = new ArrayList<Conta>();

	public Banco() {
		iniciaConta();
	}

	public int getSequencial() {
		return sequencial;
	}

	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}

	public ArrayList<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(ArrayList<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public ArrayList<Conta> getListaDeContas() {
		return listaDeContas;
	}

	public void setListaDeContas(ArrayList<Conta> listaDeContas) {
		this.listaDeContas = listaDeContas;
	}

	public void iniciaConta() {
		/*O numero das contas possuem 8 dígidos, sendo eles:
		 * ### -> referente a agencia 
		 * ### -> referente ao id pessoal
		 * ## -> referente ao tipo de conta
		 */
		criaConta("Rafael Chaves", "10592252639", 1000.00, 00.00, Agencia.FLORIANOPOLIS, TiposDeConta.CONTA_CORRENTE);
		criaConta("Mariana Cortes", "11111111111", 8000.00, 4500.00, Agencia.FLORIANOPOLIS,
				TiposDeConta.CONTA_CORRENTE);
		criaConta("Monalisa Motoca", "2222222222", 1000.00, 1000.00, Agencia.SAO_JOSE, TiposDeConta.CONTA_POUPANCA);
		criaConta("Rafael Chaves", "10592252639", 1000.00, 00.00, Agencia.FLORIANOPOLIS, TiposDeConta.CONTA_POUPANCA);
		criaConta("Mariana Cortes", "11111111111", 8000.00, 4500.00, Agencia.FLORIANOPOLIS,
				TiposDeConta.CONTA_POUPANCA);
		criaConta("Monalisa Motoca", "2222222222", 1000.00, 1000.00, Agencia.SAO_JOSE, TiposDeConta.CONTA_CORRENTE);
		criaConta("Rafael Chaves", "10592252639", 1000.00, 00.00, Agencia.FLORIANOPOLIS,
				TiposDeConta.CONTA_INVESTIMENTO, PlanosDeInvestimento.INVESTIDOR_BAIXA_RENDA);
		criaConta("Mariana Cortes", "11111111111", 8000.00, 4500.00, Agencia.FLORIANOPOLIS,
				TiposDeConta.CONTA_INVESTIMENTO, PlanosDeInvestimento.INVESTIDOR_PREMIUM);
		
	}

	public Conta criaConta(String nome, String cpf, double rendaMensal, double saldo, Agencia agencia,
			TiposDeConta tipo, PlanosDeInvestimento plano) {
		String sequencialUtilizado = getSequencialUtilizado(cpf);
		Conta contaInvestimento = new ContaInvestimento(nome, cpf, rendaMensal, saldo, agencia, sequencialUtilizado,
				plano);
		listaDeContas.add(contaInvestimento);
		return contaInvestimento;
	}

	public Conta criaConta(String nome, String cpf, double rendaMensal, double saldo, Agencia agencia,
			TiposDeConta tipo) {
		String sequencialUtilizado = getSequencialUtilizado(cpf);

		switch (tipo) {
		case CONTA_CORRENTE:
			Conta contaCorrente = new ContaCorrente(nome, cpf, rendaMensal, saldo, agencia, sequencialUtilizado);
			listaDeContas.add(contaCorrente);
			return contaCorrente;
		case CONTA_POUPANCA:
			Conta contaPoupanca = new ContaPoupanca(nome, cpf, rendaMensal, saldo, agencia, sequencialUtilizado);
			listaDeContas.add(contaPoupanca);
			return contaPoupanca;
		case CONTA_INVESTIMENTO:
			Conta contaInvestimento = new ContaInvestimento(nome, cpf, rendaMensal, saldo, agencia, sequencialUtilizado,
					PlanosDeInvestimento.INVESTIDOR_BAIXA_RENDA);
			listaDeContas.add(contaInvestimento);
			return contaInvestimento;
		default:
			return new ContaCorrente(nome, cpf, rendaMensal, saldo, agencia, sequencialUtilizado);
		}
	}

	public void mostraContas(Conta usuario) {
		for (Conta conta : listaDeContas) {
			System.out.println("\nCliente: " + conta.getCpf() + " ---- CPF: " + conta.getCpf()
					+ " ---- Número da conta: " + conta.getNumeroConta());
		}
	}

	public void mostraContasNegativas(Conta usuario) {
		for (Conta conta : listaDeContas) {
			if (conta.getSaldo() < 0) {
				System.out.println("\nConta número: " + conta.getNumeroConta() + " Saldo: " + conta.getSaldo());
			}
		}
	}

	public void mostraValorTotal(Conta usuario) {
		for (Conta conta : listaDeContas) {
			if (conta != null) {
				double valorInvestido = conta.getSaldo();

				valorInvestido += conta.getSaldo();
				System.out.println("\n Valor total investido no DEVInMoney: " + valorInvestido);
			}
		}

	}

	public void mostraTransacoes(Conta usuario) {
		ArrayList<Transacao> transacoesRelatorio = new ArrayList<Transacao>();
		for (Transacao transacaoUsuario : transacoes) {
			if (transacaoUsuario.contaOrigem != null
					&& transacaoUsuario.contaOrigem.getCpf().equals(usuario.getCpf())) {
				transacoesRelatorio.add(transacaoUsuario);
			}
			if (transacaoUsuario.contaDestino != null
					&& transacaoUsuario.contaDestino.getCpf().equals(usuario.getCpf())) {
				transacoesRelatorio.add(transacaoUsuario);
			}
		}
		for (Transacao transacao : transacoesRelatorio) {

			if (transacao.getContaOrigem() == null) {
				System.out.println("Operaçao: depósito " + "Destino: " + transacao.getContaDestino().getNome()
						+ "Valor: " + transacao.getValor() + "Hora: " + transacao.getHoraTransacao());
			} else if (transacao.getContaDestino() == null) {
				System.out.println("Operaçao: saque " + "Origem: " + transacao.getContaOrigem().getNome() + "Valor: "
						+ transacao.getValor() + "Hora: " + transacao.getHoraTransacao());
			} else {
				System.out.println("Origem: " + transacao.getContaOrigem().getNome() + "Destino: "
						+ transacao.getContaDestino().getNome() + "Valor: " + transacao.getValor() + "Hora: "
						+ transacao.getHoraTransacao());
			}
		}
	}

	private String getSequencialUtilizado(String cpf) {
		boolean usuarioExiste = false;
		String sequencialUsuario = "";
		String sequencialUtilizado;
		for (Conta conta : listaDeContas) {
			if (conta.getCpf().equals(cpf)) {
				usuarioExiste = true;
				sequencialUsuario = conta.getNumeroConta().substring(3, 6);
			}
		}
		if (usuarioExiste) {
			sequencialUtilizado = sequencialUsuario;
		} else {
			sequencial = sequencial + 1;
			sequencialUtilizado = String.format("%03d", sequencial);
		}
		return sequencialUtilizado;
	}

}
