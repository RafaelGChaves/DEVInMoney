package devInMoney.banco;

public enum PlanosDeInvestimento {

	INVESTIDOR_BAIXA_RENDA("Baixa renda", "Sua conta rende 1% ao mês", 0.01),
	INVESTIDOR_PREMIUM("Premium", "Sua conta rende 5% ao mês", 0.05);

	public String nomeInvestimento;
	public String descricaoInvestimento;
	public double rendimentoInvestimento;

	private PlanosDeInvestimento(String nomeInvestimento, String descricaoInvestimento, double rendimentoInvestimento) {
		this.nomeInvestimento = nomeInvestimento;
		this.descricaoInvestimento = descricaoInvestimento;
		this.rendimentoInvestimento = rendimentoInvestimento;
	}

	public String toString() {
		return "Plano: " + nomeInvestimento + " - " + descricaoInvestimento;
	}

	public String getNomeInvestimento() {
		return nomeInvestimento;
	}

	public void setNomeInvestimento(String nomeInvestimento) {
		this.nomeInvestimento = nomeInvestimento;
	}

	public String getDescricaoInvestimento() {
		return descricaoInvestimento;
	}

	public void setDescricaoInvestimento(String descricaoInvestimento) {
		this.descricaoInvestimento = descricaoInvestimento;
	}

	public double getRendimentoInvestimento() {
		return rendimentoInvestimento;
	}

	public void setRendimentoInvestimento(double rendimentoInvestimento) {
		this.rendimentoInvestimento = rendimentoInvestimento;
	}

}
