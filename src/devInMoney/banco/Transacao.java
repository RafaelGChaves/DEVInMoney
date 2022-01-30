package devInMoney.banco;

import java.time.LocalDateTime;

import devInMoney.conta.Conta;

public class Transacao {
	Conta contaOrigem;
	Conta contaDestino;
	double valor;
	LocalDateTime horaTransacao;

	public Transacao() {

	}

	public Transacao(Conta conta, double valor, LocalDateTime horaTransacao, boolean saque) {
		if (saque) {
			this.contaOrigem = conta;
		} else {
			this.contaDestino = conta;
		}
		this.valor = valor;
		this.horaTransacao = horaTransacao;
	}

	public Transacao(Conta contaOrigem, Conta contaDestino, double valor, LocalDateTime horaTransacao) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.horaTransacao = horaTransacao;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDateTime getHoraTransacao() {
		return horaTransacao;
	}

	public void setHoraTransacao(LocalDateTime horaTransacao) {
		this.horaTransacao = horaTransacao;
	}

}
