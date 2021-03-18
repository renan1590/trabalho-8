package projeto.persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import projeto.enums.PersistenciaEnum;
import projeto.model.Produto;

public class PersistenciaProdutosJSON extends PersistenciaProdutos {
	@Override
	public void gravar(List<Produto> produtos) {
		JSONArray array = new JSONArray(produtos);
		
		PersistenciaUtil.criarArquivo(CAMINHO_ARQUIVO + PersistenciaEnum.JSON.getExtensao(), array.toString());
	}

	@Override
	public List<Produto> buscar() {
		List<Produto> produtos = new ArrayList<>();
		
		try {
			File f = new File(CAMINHO_ARQUIVO + PersistenciaEnum.JSON.getExtensao());
			
			if (! f.exists()) {
				return produtos;
			}
			
			String s = PersistenciaUtil.buscarConteudoArquivo(CAMINHO_ARQUIVO + PersistenciaEnum.JSON.getExtensao());
			
			JSONArray array = new JSONArray(s);
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				
				Produto prod = new Produto();
				prod.setId(obj.getInt("id"));
				prod.setCodigo(obj.getInt("codigo"));
				prod.setDescricao(obj.getString("descricao"));
				prod.setPreco(obj.getDouble("preco"));
				prod.setQtdEstoque(obj.getInt("qtdEstoque"));
				
				produtos.add(prod);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return produtos;
	}
	
	
}