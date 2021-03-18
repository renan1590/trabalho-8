package projeto.controller;

import java.util.ArrayList;
import java.util.List;

import projeto.enums.PersistenciaEnum;
import projeto.model.Venda;
import projeto.persistencia.PersistenciaVendas;
import projeto.persistencia.PersistenciaVendasJSON;

public class VendasRegistros {
	private static List<Venda> vendas;
	private static PersistenciaVendas persVendas;
	
	public static void init(PersistenciaEnum formaGravar) {
		ProdutosCadastro.init(formaGravar);
		
		switch (formaGravar) {
			case JSON:
				persVendas = new PersistenciaVendasJSON();
				break;
			default: break;
		}
		
		if (persVendas != null) {
			vendas = persVendas.buscar();
		}
		else {
			vendas = new ArrayList<>();
		}
	}
	
	public static void gravar() {
		persVendas.gravar(vendas);
	}
		
	public static void init() {
	vendas = new ArrayList<>();
	}
		
		public static List<Venda> getVendas() {
			return vendas;
		}
		
		public static void listarVendas() {
			for (int i = 0; i < vendas.size(); i ++) {
				if (i > 0) {
					System.out.println("----------");
				}
				
				System.out.println(vendas.get(i));
			}
		}

		public static void setVendas(List<Venda> vendas) {
			VendasRegistros.vendas = vendas;
		}

		public static void addVenda(Venda venda) {
			vendas.add(venda);
		}
}