package projeto.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import projeto.enums.PersistenciaEnum;
import projeto.model.Produto;

public class PersistenciaProdutosXML extends PersistenciaProdutos {
	@Override
	public void gravar(List<Produto> produtos) {
		Element el = new Element("estoque");
		
		for (int i = 0; i < produtos.size(); i++) {
			Element prod = new Element("produto");
			
			prod.setAttribute("id", String.valueOf(produtos.get(i).getId()));
			prod.setAttribute("codigo", String.valueOf(produtos.get(i).getCodigo()));
			prod.setAttribute("descricao", produtos.get(i).getDescricao());
			prod.setAttribute("preco", String.valueOf(produtos.get(i).getPreco()));
			prod.setAttribute("qtdEstoque", String.valueOf(produtos.get(i).getQtdEstoque()));
			
			el.addContent(prod);
		}
		
		Document doc = new Document(el);
		
		XMLOutputter xout = new XMLOutputter();

		try {
			FileWriter arquivo = new FileWriter(CAMINHO_ARQUIVO + PersistenciaEnum.CSV.getExtensao());
			
			xout.output(doc, arquivo);
			
			arquivo.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Produto> buscar() {
		List<Produto> produtos = new ArrayList<>();
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();

		try {
			File f = new File(CAMINHO_ARQUIVO + PersistenciaEnum.CSV.getExtensao());
			
			if (! f.exists()) {
				return produtos;
			}
			
	    	doc = builder.build(f);
	    	
	    	List<Element> list = doc.getRootElement().getChildren();
	    	
	    	for (int i = 0; i < list.size(); i++) {
	    		Produto prod = new Produto();
	    		
	    		prod.setId(Integer.parseInt(list.get(i).getAttributeValue("id")));
	    		prod.setCodigo(Integer.parseInt(list.get(i).getAttributeValue("codigo")));
	    		prod.setDescricao(list.get(i).getAttributeValue("descricao"));
	    		prod.setPreco(Double.parseDouble(list.get(i).getAttributeValue("preco")));
	    		prod.setQtdEstoque(Integer.parseInt(list.get(i).getAttributeValue("qtdEstoque")));
	    		
	    		produtos.add(prod);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produtos;
	}
}