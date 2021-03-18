package projeto.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import projeto.enums.PersistenciaEnum;
import projeto.model.Produto;

public class PersistenciaProdutosCSV extends PersistenciaProdutos {
	@Override
	public void gravar(List<Produto> produtos) {
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < produtos.size(); i++) {
			if (i > 0) {
				str.append("\n");
			}
			
			str.append(produtos.get(i).getId());
			str.append(";");
			str.append(produtos.get(i).getCodigo());
			str.append(";");
			str.append(produtos.get(i).getDescricao());
			str.append(";");
			str.append(produtos.get(i).getPreco());
			str.append(";");
			str.append(produtos.get(i).getQtdEstoque());
		}
		
		PersistenciaUtil.criarArquivo(CAMINHO_ARQUIVO + PersistenciaEnum.CSV.getExtensao(), str.toString());
	}

	@Override
	public List<Produto> buscar() {
		List<Produto> produtos = new ArrayList<>();

		try {
			File f = new File(CAMINHO_ARQUIVO + PersistenciaEnum.CSV.getExtensao());
			
			if (! f.exists()) {
				return produtos;
			}
			
	    	BufferedReader reader = new BufferedReader(new FileReader(f));
	    	
	    	String linha;
	    	
	    	while ((linha = reader.readLine()) != null) {
	    		String[] dados = linha.split(";");
	    		
	    		Produto prod = new Produto();
	    		
	    		prod.setId(Integer.parseInt(dados[0]));
	    		prod.setCodigo(Integer.parseInt(dados[1]));
	    		prod.setDescricao(dados[2]);
	    		prod.setPreco(Double.parseDouble(dados[3]));
	    		prod.setQtdEstoque(Integer.parseInt(dados[4]));
	    		
	    		produtos.add(prod);
	    	}
	    	
	    	reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produtos;
	}
}