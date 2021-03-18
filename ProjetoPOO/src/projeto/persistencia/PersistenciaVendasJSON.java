package projeto.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import projeto.controller.ProdutosCadastro;
import projeto.enums.PersistenciaEnum;
import projeto.model.ProdutoVenda;
import projeto.model.Venda;

public class PersistenciaVendasJSON implements PersistenciaVendas {
	private final String CAMINHO_ARQUIVO = "persistencia/vendas" + PersistenciaEnum.JSON.getExtensao();
	
	@Override
	public void gravar(List<Venda> vendas) {
		try {
			JSONArray array = new JSONArray();
			
			for (int i = 0; i < vendas.size(); i ++) {
				JSONObject venda = new JSONObject();
				
				venda.put("desconto", vendas.get(i).getDesconto());
				venda.put("forma_pagamento", vendas.get(i).getFormaPagamento().getNome());
				
				List<ProdutoVenda> produtos = vendas.get(i).getProdutos();
				List<Map<String, Object>> prods = new ArrayList<>();
				
				for (int j = 0; j < produtos.size(); j++) {
					Map<String, Object> map = new HashMap<>();
					
					map.put("id_produto", produtos.get(j).getId());
					map.put("qtd_venda", produtos.get(j).getQtdVenda());
					
					prods.add(map);
				}
				
				venda.put("produtos", prods);
				
				array.put(venda);
			}
	
			PersistenciaUtil.criarArquivo(CAMINHO_ARQUIVO, array.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Venda> buscar() {
		List<Venda> vendas = new ArrayList<>();
		
		try {
			File f = new File(CAMINHO_ARQUIVO);
			
			if (! f.exists()) {
				return vendas;
			}
			
			String s = PersistenciaUtil.buscarConteudoArquivo(CAMINHO_ARQUIVO);
			
			JSONArray array = new JSONArray(s);
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				
				Venda venda = new Venda();
				venda.setDesconto(obj.getDouble("desconto"));
				venda.setFormaPagamento(obj.getString("forma_pagamento"));
				
				List<ProdutoVenda> produtos = new ArrayList<>();
				JSONArray prods = obj.getJSONArray("produtos");
				
				for (int j = 0; j < prods.length(); j++) {
					JSONObject p = prods.getJSONObject(j);
					
					ProdutoVenda prodVenda = new ProdutoVenda(ProdutosCadastro.buscarPorId(p.getInt("id_produto")), p.getInt("qtd_venda"));
					
					produtos.add(prodVenda);
				}
				
				venda.setProdutos(produtos);
				
				vendas.add(venda);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return vendas;
	}
}