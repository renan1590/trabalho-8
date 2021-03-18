package projeto.controller;

import java.util.ArrayList;
import java.util.List;

import projeto.enums.PersistenciaEnum;
import projeto.model.Produto;
import projeto.persistencia.PersistenciaIdProdutos;
import projeto.persistencia.PersistenciaProdutos;
import projeto.persistencia.PersistenciaProdutosCSV;
import projeto.persistencia.PersistenciaProdutosJSON;
import projeto.persistencia.PersistenciaProdutosXML;

public final class ProdutosCadastro {
	private static List<Produto> lista;
	private static int sequenciaId;
	private static PersistenciaProdutos persProdutos;
	private static PersistenciaIdProdutos persIdProdutos;
	
	public static void init(PersistenciaEnum formaGravar) {
		switch (formaGravar) {
			case CSV:
				persProdutos = new PersistenciaProdutosCSV();
				break;
			case JSON:
				persProdutos = new PersistenciaProdutosJSON();
				break;
			case XML:
				persProdutos = new PersistenciaProdutosXML();
				break;
			default: break;
		}
		
		if (persProdutos != null) {
			lista = persProdutos.buscar();
		}
		else {
			lista = new ArrayList<>();
		}
		
		persIdProdutos = new PersistenciaIdProdutos();
		sequenciaId = persIdProdutos.buscar();
	}
	
	public static void gravar() {
		persIdProdutos.gravar(sequenciaId);
		persProdutos.gravar(lista);
	}
	
	public static void inserirProduto(int codigo, String descricao, double preco, int qtdEstoque) {
		if (verificarCodigo(codigo) && verificarDescricao(descricao) && verificarPreco(preco) && verificarQtdEstoque(qtdEstoque)) {
			sequenciaId++;
			
			Produto prod = new Produto(sequenciaId, codigo, descricao, preco, qtdEstoque);
			
			lista.add(prod);
		}
	}
	
	public static void excluirPorCodigo(int codigo) {
		boolean encontrou = false;
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == codigo) {
				lista.remove(i);
				encontrou = true;
				break;
			}
		}

		if (! encontrou) {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public static void excluirPorDescricao(String descricao) {
		boolean encontrou = false;
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDescricao().equalsIgnoreCase(descricao)) {
				lista.remove(i);
				encontrou = true;
				break;
			}
		}
		
		if (! encontrou) {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public static void alterarCodigoPorCodigo(int codigoAntes, int codigoNovo) {
		if (verificarCodigo(codigoNovo)) {
			Produto prod = buscarPorCodigo(codigoAntes);
			
			if (prod != null) {
				prod.setCodigo(codigoNovo);
			}
			else {
				System.out.println("Produto não encontrado!");
			}
		}
	}
	
	public static void alterarCodigoPorDescricao(String descricao, int codigoNovo) {
		if (verificarCodigo(codigoNovo)) {
			Produto prod = buscarPorDescricao(descricao);
			
			if (prod != null) {
				prod.setCodigo(codigoNovo);
			}
			else {
				System.out.println("Produto não encontrado!");
			}
		}
	}
	
	public static void alterarDescricaoPorDescricao(String descricaoAntes, String descricaoNovo) {
		if (verificarDescricao(descricaoNovo)) {
			Produto prod = buscarPorDescricao(descricaoAntes);
			
			if (prod != null) {
				prod.setDescricao(descricaoNovo);
			}
			else {
				System.out.println("Produto não encontrado!");
			}
		}
	}
	
	public static void alterarDescricaoPorCodigo(int codigo, String descricaoNovo) {
		if (verificarDescricao(descricaoNovo)) {
			Produto prod = buscarPorCodigo(codigo);
			
			if (prod != null) {
				prod.setDescricao(descricaoNovo);
			}
			else {
				System.out.println("Produto não encontrado!");
			}
		}
	}
	
	public static void alterarPrecoPorCodigo(int codigo, double preco) {
		if (verificarPreco(preco)) {
			Produto prod = buscarPorCodigo(codigo);
			
			if (prod != null) {
				prod.setPreco(preco);
			}
			else {
				System.out.println("Produto não encontrado!");
			}
		}
	}
	
	public static void alterarPrecoPorDescricao(String descricao, double preco) {
		if (verificarPreco(preco)) {
			Produto prod = buscarPorDescricao(descricao);
			
			if (prod != null) {
				prod.setPreco(preco);
			}
			else {
				System.out.println("Produto não encontrado!");
			}
		}
	}
	
	public static void inserirEstoquePorCodigo(int codigo, int qtdEstoque) {
		Produto prod = buscarPorCodigo(codigo);
		
		if (prod != null) {
			prod.setQtdEstoque(prod.getQtdEstoque() + qtdEstoque);
		}
		else {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public static void inserirEstoquePorDescricao(String descricao, int qtdEstoque) {
		Produto prod = buscarPorDescricao(descricao);
		
		if (prod != null) {
			prod.setQtdEstoque(prod.getQtdEstoque() + qtdEstoque);
		}
		else {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public static void retirarEstoquePorCodigo(int codigo, int qtdEstoque) {
		Produto prod = buscarPorCodigo(codigo);
		
		if (prod != null) {
			if (verificarQtdEstoque(prod.getQtdEstoque() - qtdEstoque)) {
				prod.setQtdEstoque(prod.getQtdEstoque() - qtdEstoque);
			}
		}
		else {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public static void retirarEstoquePorDescricao(String descricao, int qtdEstoque) {
		Produto prod = buscarPorDescricao(descricao);
		
		if (prod != null) {
			if (verificarQtdEstoque(prod.getQtdEstoque() - qtdEstoque)) {
				prod.setQtdEstoque(prod.getQtdEstoque() - qtdEstoque);
			}
		}
		else {
			System.out.println("Produto não encontrado!");
		}
	}
	
	public static Produto buscarPorId(int id) {
		Produto prod = null;
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId() == id) {
				prod = lista.get(i);
				break;
			}
		}
		
		return prod;
	}
	
	public static Produto buscarPorCodigo(int codigo) {
		Produto prod = null;
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == codigo) {
				prod = lista.get(i);
				break;
			}
		}
		
		return prod;
	}
	
	public static Produto buscarPorDescricao(String descricao) {
		Produto prod = null;
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDescricao().equalsIgnoreCase(descricao)) {
				prod = lista.get(i);
				break;
			}
		}
		
		return prod;
	}
	
	public static List<Produto> buscarTodos() {
		return lista;
	}
	
	public static void listarTodos() {
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	}
	
	private static boolean verificarCodigo(int codigo) {
		if (codigo <= 0) {
			System.out.println("Código deve ser maior que 0!");
			return false;
		}
		else if (codigoRepetido(codigo)) {
			System.out.println("Código repetido!");
			return false;
		}
		else {
			return true;
		}
	}
	
	private static boolean codigoRepetido(int codigo) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCodigo() == codigo) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean verificarDescricao(String descricao) {
		if (descricao.equals("")) {
			System.out.println("Descrição não pode passar em branco!");
			return false;
		}
		else if (descricaoRepetida(descricao)) {
			System.out.println("Descrição repetida!");
			return false;
		}
		else {
			return true;
		}
	}
	
	private static boolean descricaoRepetida(String descricao) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDescricao().equalsIgnoreCase(descricao)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean verificarPreco(double preco) {
		if (preco <= 0d) {
			System.out.println("Preço deve ser maior que 0!");
			return false;
		}
		
		return true;
	}
	
	private static boolean verificarQtdEstoque(int qtdEstoque) {
		if (qtdEstoque < 0) {
			System.out.println("Quantidade de estoque não pode ser menor que 0!");
			return false;
		}
		
		return true;
	}
}