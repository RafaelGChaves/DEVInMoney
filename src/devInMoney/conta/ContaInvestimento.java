package devInMoney.conta;

import java.text.DecimalFormat;

import devInMoney.banco.Agencia;
import devInMoney.banco.PlanosDeInvestimento;

public class ContaInvestimento extends Conta {

	PlanosDeInvestimento plano;

	public ContaInvestimento() {
		super();
	}

	public ContaInvestimento(String nome, String cpf, double rendaMensal, double saldo, Agencia agencia,
			String sequencial, PlanosDeInvestimento plano) {
		super(nome, cpf, rendaMensal, saldo, agencia, sequencial, TiposDeConta.CONTA_INVESTIMENTO);
		this.plano = plano;
	}

	@Override
	public void simulaRendimento(double valor, int meses) {
		DecimalFormat formato = new DecimalFormat("#,##0,00");
		for (int i = 0; i < meses; i++) {
			valor += valor * plano.rendimentoInvestimento;
			System.out.println("\n Rendimento no " + (i+1) + "º mês: R$ " + formato.format(valor));
		}

	}

}
