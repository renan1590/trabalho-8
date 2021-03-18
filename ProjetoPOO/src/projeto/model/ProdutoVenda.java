package projeto.model;

public class ProdutoVenda extends Produto {
	private int qtdVenda;

	public ProdutoVenda(Produto prod, int qtdVenda) {
		this.id = prod.getId();
		this.codigo = prod.getCodigo();
		this.descricao = prod.getDescricao();
		this.preco = prod.getPreco();
		this.qtdEstoque = prod.getQtdEstoque();
		this.qtdVenda = qtdVenda;
	}
	
	public int getQtdVenda() {
		return qtdVenda;
	}

	public void setQtdVenda(int qtdVenda) {
		this.qtdVenda = qtdVenda;
	}
	
	public void addQtdVenda(int qtdVenda) {
		this.qtdVenda += qtdVenda;
	}
	
	@Override
	public String toString() {
		return "Produto Venda | Código: " + codigo + " | Descrição: " + descricao + " | " + "Preço: R$ " + String.format("%.2f", preco) + " | Quantidade Vendida: " + qtdVenda;
	}
}