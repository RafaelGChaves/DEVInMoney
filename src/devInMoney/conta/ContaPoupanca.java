package devInMoney.conta;

import devInMoney.banco.Agencia;

public class ContaPoupanca extends Conta {

	int mesesRendimento;
	double rentabilidadeAnual;

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String nome, String cpf, double rendaMensal, double saldo, Agencia agencia,
			String sequencial) {
		super(nome, cpf, rendaMensal, saldo, agencia, sequencial, TiposDeConta.CONTA_POUPANCA);
	}

	private void simulacaoRendimento(int mesesRendimento, double rentabilidadeAnual) {
		double valorSimulado = this.getSaldo();
		valorSimulado += (this.getSaldo() * (rentabilidadeAnual / 0.12) * mesesRendimento);

	}

}
