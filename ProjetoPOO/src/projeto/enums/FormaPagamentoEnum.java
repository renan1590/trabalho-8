package projeto.enums;

public enum FormaPagamentoEnum {
	DINHEIRO("dinheiro"), CARTAO_CREDITO("cartao_credito"), CARTAO_DEBITO("cartao_debito"), PIX("pix");
	
	private String nome;
	
	private FormaPagamentoEnum(String nome) {
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}