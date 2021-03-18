package projeto.model;

public class Produto {
	protected String descricao;
	protected double preco;
	protected int id, codigo, qtdEstoque;
	
	public Produto() {}
	
	public Produto(int id, int codigo, String descricao, double preco, int qtdEstoque) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	@Override
	public String toString() {
		return "Produto | Código: " + codigo + " | Descrição: " + descricao + " | " + "Preço: R$ " + String.format("%.2f", preco) + " | Quantidade Estoque: " + qtdEstoque;
	}
}