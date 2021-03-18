package projeto.model;

import java.util.ArrayList;
import java.util.List;

import projeto.controller.ProdutosCadastro;
import projeto.enums.FormaPagamentoEnum;

public class Venda {
	private List<ProdutoVenda> produtos;
	private double desconto;
	private FormaPagamentoEnum formaPagamento;
	
	public Venda() {
		this.produtos = new ArrayList<>();
	}
	
	public List<ProdutoVenda> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoVenda> produtos) {
		this.produtos = produtos;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public void addProduto(int codigo, int quantidade) {
		Produto prod = ProdutosCadastro.buscarPorCodigo(codigo);
		
		if (prod != null) {
			boolean encontrou = false;
			
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getCodigo() == codigo) {
					if (produtos.get(i).getQtdVenda() + quantidade > produtos.get(i).getQtdEstoque()) {
						System.out.println("Quantidade de venda ultrapassou o total de estoque!");
					}
					else {
						produtos.get(i).addQtdVenda(quantidade);
					}
					
					encontrou = true;
					break;
				}
			}
			
			if (! encontrou) {
				produtos.add(new ProdutoVenda(prod, quantidade));
			}
		}
		else {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public void removeProduto(int codigo, int quantidade) {
		Produto prod = ProdutosCadastro.buscarPorCodigo(codigo);
		
		if (prod != null) {
			boolean encontrou = false;
			
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getCodigo() == codigo) {
					if (produtos.get(i).getQtdVenda() >= quantidade) {
						produtos.get(i).addQtdVenda(-quantidade);
						encontrou = true;
						break;
					}
				}
			}
			
			if (! encontrou) {
				System.out.println("Quantidade do produto na venda não pode ser menos que 0!");
			}
		}
		else {
			System.out.println("Produto não encontrado!");
		}
	}
	
	private boolean verificar() {
		if (getTotalVenda() <= 0) {
			System.out.println("Desconto não pode ser maior ou igual ao total da venda");
			return false;
		}
		
		return true;
	}
	
	public FormaPagamentoEnum getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void setFormaPagamento(String formaPagamento) {
		for (FormaPagamentoEnum e : FormaPagamentoEnum.values()) {
			if (e.getNome().equals(formaPagamento)) {
				this.formaPagamento = e;
			}
		}
	}
	
	public double getTotalVenda() {
		double totalVenda = 0;
		
		for (int i = 0; i < produtos.size(); i++) {
			totalVenda += produtos.get(i).getPreco() * produtos.get(i).getQtdVenda();
		}
		
		return totalVenda - desconto;
	}

	public boolean fecharVenda(FormaPagamentoEnum formaPagamento) {
		if (verificar()) {
			this.formaPagamento = formaPagamento;
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("***Venda***\n");
		
		for (int i = 0; i < produtos.size(); i++) {
			str.append(produtos.get(i) + "\n");
		}
		
		str.append("Forma de Pagamento: " + formaPagamento.getNome() + "\n");
		str.append("Desconto: R$ " + String.format("%.2f", desconto) + "\n");
		str.append("Total Venda: R$ " + String.format("%.2f", getTotalVenda()));
		
		return str.toString();
	}
}