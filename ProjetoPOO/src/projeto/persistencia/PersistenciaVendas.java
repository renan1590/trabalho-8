package projeto.persistencia;

import java.util.List;

import projeto.model.Venda;

public interface PersistenciaVendas extends IPersistencia<List<Venda>> {
	public void gravar(List<Venda> gravar);
	
	public List<Venda> buscar();
}