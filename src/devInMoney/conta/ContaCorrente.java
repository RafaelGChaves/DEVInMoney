package devInMoney.conta;

import java.time.LocalDateTime;
import java.util.Scanner;

import devInMoney.banco.Agencia;
import devInMoney.banco.Transacao;

public class ContaCorrente extends Conta {

	Scanner scanner = new Scanner(System.in);

	double valorCheque;

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(String nome, String cpf, double rendaMensal, double saldo, Agencia agencia,
			String sequencial) {
		super(nome, cpf, rendaMensal, saldo, agencia, sequencial, TiposDeConta.CONTA_CORRENTE);
	}

	public boolean direitoChequeEspecial() {
		if (this.getRendaMensal() >= 500 && this.getRendaMensal() < 2000) {
			System.out.println("Você tem direito a um cheque especial no valor de R$ 200,00.");
			return true;
		} else if (this.getRendaMensal() >= 2000) {
			System.out.println("Você tem direito a um cheque especial no valor de R$ 1000,00.");
			return true;
		} else {
			System.out.println("Sua renda não permite cheque especial");
			return false;
		}
	}

	@Override
	public Transacao saque(double valor) {
		if (getSaldo() - valor >= 0) {
			System.out.println("Saldo anterior: " + getSaldo());
			this.setSaldo(getSaldo() - valor);

			return new Transacao(this, valor, LocalDateTime.now(), true);
		} else if (getSaldo() - valor + getSaldoChequeEspecial() < (getSaldoChequeEspecial() * -1)) {
			System.out.println("Você não pode ultrapassar o limite do cheque especial");

		} else {

			
			System.out.println("Você tem direito a um cheque especial no valor de R$ " + getSaldoChequeEspecial());
			System.out.println("Deseja utilizar o cheque especial? \n1 -> Sim \n2 ->Não");
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				if (getSaldo() - valor + getSaldoChequeEspecial() > (getSaldoChequeEspecial() * -1)) {
					System.out.println("Saldo anterior: " + getSaldo());
					setSaldo(getSaldo() - valor);
					System.out.println("Saldo atual: " + getSaldo());
					return new Transacao(this, valor, LocalDateTime.now(), true);
				} else {
					System.out.println("Você não pode ultrapassar o limite do cheque especial");
				}
				break;

			default:
				System.out.println("Operação cancelada");
				break;
			}

		}
		return null;
	}

	
	
	@Override
	public Transacao transferencia(Conta destino, double valor) {
		if(super.transferencia(destino, valor) == null) {
			if (this.numeroConta.equals(destino.getNumeroConta())) {
				return null;
			}
			System.out.println("Você tem direito a um cheque especial no valor de R$ " + getSaldoChequeEspecial());
			System.out.println("Deseja utilizar o cheque especial? \n1 -> Sim \n2 ->Não");
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				if (getSaldo() - valor + getSaldoChequeEspecial() > (getSaldoChequeEspecial() * -1)) {
					System.out.println("Saldo anterior: " + getSaldo());
					setSaldo(getSaldo() - valor);
					System.out.println("Saldo atual: " + getSaldo());
					return new Transacao(this, destino, valor, LocalDateTime.now());
				} else {
					System.out.println("Você não pode ultrapassar o limite do cheque especial");
				}
				break;

			default:
				System.out.println("Operação cancelada");
				break;
			}
			
		}
		return super.transferencia(destino, valor);
		
	}
	@Override
	public double getSaldoChequeEspecial() {
		double saldoConta = getSaldo();
		switch (getTipoChequeEspecial()) {
		case CHEQUE_ESPECIAL_PADRAO:
			if (saldoConta < 0) {
				return saldoConta + 200;

			} else {
				return 200;
			}

		case CHEQUE_ESPECIAL_PLUS:
			if (saldoConta < 0) {
				return saldoConta + 1000;

			} else {
				return 1000;
			}

		default:
			return 0;

		}
	}

	private TipoChequeEspecial getTipoChequeEspecial() {
		if (this.getRendaMensal() >= 500 && this.getRendaMensal() < 2000) {
			return TipoChequeEspecial.CHEQUE_ESPECIAL_PADRAO;
		} else if (this.getRendaMensal() >= 2000) {
			return TipoChequeEspecial.CHEQUE_ESPECIAL_PLUS;
		} else {
			return TipoChequeEspecial.NAO_PERMITE_CHEQUE_ESPECIAL;
		}
	}

}
