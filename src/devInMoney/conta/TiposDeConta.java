package devInMoney.conta;

public enum TiposDeConta {
	
	CONTA_CORRENTE("Conta Corrente", "01"), CONTA_POUPANCA ("Conta Poupança", "02") , CONTA_INVESTIMENTO ("Conta Investimento", "03");
	
	private String tipoConta;
	private String idTipoConta;
	
	TiposDeConta (String tipoConta, String idTipoConta) {
		this.tipoConta = tipoConta;
		this.idTipoConta = idTipoConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(String idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

}
