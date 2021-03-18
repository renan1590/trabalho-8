package projeto.teste;

import projeto.controller.VendasRegistros;
import projeto.enums.FormaPagamentoEnum;
import projeto.enums.PersistenciaEnum;
import projeto.model.Venda;
import projeto.persistencia.PersistenciaVendasJSON;

public class VendasTeste {
	public static void main(String[] args) {
		VendasRegistros.init(PersistenciaEnum.JSON);
		
		Venda venda = new Venda();
		
		venda.setDesconto(2);
		
		venda.addProduto(6, 3);
		
		if (venda.fecharVenda(FormaPagamentoEnum.PIX)) {
			VendasRegistros.addVenda(venda);
		}
		
		PersistenciaVendasJSON vendasJson = new PersistenciaVendasJSON();
		
		System.out.println(vendasJson.buscar());
		
		VendasRegistros.gravar();
	}
}