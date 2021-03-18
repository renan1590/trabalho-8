package projeto.persistencia;

import projeto.enums.PersistenciaEnum;

public class PersistenciaIdProdutos implements IPersistencia<Integer> {
	private String CAMINHO_ARQUIVO = "persistencia/id_produtos" + PersistenciaEnum.TXT.getExtensao();
	
	@Override
	public void gravar(Integer id) {
		PersistenciaUtil.criarArquivo(CAMINHO_ARQUIVO, id.toString());
	}

	@Override
	public Integer buscar() {
		String retorno = PersistenciaUtil.buscarConteudoArquivo(CAMINHO_ARQUIVO);
		
		return retorno.equals("") ? 0 : Integer.parseInt(retorno);
	}
}