package projeto.persistencia;

import java.util.List;

import projeto.model.Produto;

public abstract class PersistenciaProdutos implements IPersistencia<List<Produto>> {
	protected final String CAMINHO_ARQUIVO = "persistencia/produtos";
	
	public abstract void gravar(List<Produto> gravar);
	
	public abstract List<Produto> buscar();
}