package devInMoney.conta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import devInMoney.banco.Agencia;
import devInMoney.banco.Transacao;

public abstract class Conta {

	private String nome;
	private String cpf;
	private double rendaMensal;
	private Agencia agencia;
	protected String numeroConta;
	private double saldo;

	public Conta() {

	};

	public Conta(String nome, String cpf, double rendaMensal, double saldo, Agencia agencia, String sequencial,
			TiposDeConta tipo) {
		this.nome = nome;
		this.cpf = cpf;
		this.rendaMensal = rendaMensal;
		this.saldo = saldo;
		this.agencia = agencia;
		this.numeroConta = agencia.getNumAgencia() + sequencial + tipo.getIdTipoConta();
	}

	public Transacao saque(double valor) {
		if (getSaldo() - valor >= 0) {
			System.out.println("Saldo anterior: " + getSaldo());
			this.setSaldo(getSaldo() - valor);
			System.out.println("Saldo atual: " + getSaldo());
		} else {
			System.out.println("Saldo insuficiente");
		}
		return new Transacao();
	}

	public Transacao deposito(double valor) {
		System.out.println("Saldo anterior: " + getSaldo());
		this.setSaldo(getSaldo() + valor);
		System.out.println("Saldo atual: " + getSaldo());
		return new Transacao(this, valor, LocalDateTime.now(), false);
	}

	public void extrato(ArrayList<Transacao> transacoes) {

		for (Transacao transacao : transacoes) {
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

	public Transacao transferencia(Conta destino, double valor) {
		if (this.numeroConta.equals(destino.getNumeroConta())) {
			System.out.println("Não é possível fazer um transferência para sí mesmo");
		} else if (getSaldo() - valor >= 0) {
			System.out.println("Saldo anterior: " + getSaldo());
			this.setSaldo(getSaldo() - valor);
			destino.setSaldo(destino.getSaldo() + valor);
			System.out.println("Saldo atual: " + getSaldo());
			return new Transacao(this, destino, valor, LocalDateTime.now());
		} else {
			System.out.println("");
		}
		return null;

	}

	public void alteraDados(Conta nome, double rendaMensal) {
		System.out.println("Digite seu novo nome: ");
		this.getNome();
		System.out.println("Atualize sua renda mensal: ");
		this.setRendaMensal(rendaMensal);
	}
	
	public void simulaRendimento(double valor, int meses) {
		
	}
	
	public double getSaldoChequeEspecial() {
		return 0;
	}	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, cpf, nome, numeroConta, rendaMensal, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return agencia == other.agencia && Objects.equals(cpf, other.cpf) && Objects.equals(nome, other.nome)
				&& Objects.equals(numeroConta, other.numeroConta)
				&& Double.doubleToLongBits(rendaMensal) == Double.doubleToLongBits(other.rendaMensal)
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo);
	}

}
