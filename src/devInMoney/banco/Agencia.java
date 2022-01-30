package devInMoney.banco;

public enum Agencia {

	FLORIANOPOLIS("Florianópolis", "001"), SAO_JOSE("São José", "002");

	private String agencia;
	private String numAgencia;

	Agencia(String agencia, String numAgencia) {
		this.agencia = agencia;
		this.numAgencia = numAgencia;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumAgencia() {
		return numAgencia;
	}

	public void setNumAgencia(String numAgencia) {
		this.numAgencia = numAgencia;
	}

}
