package projeto.enums;

public enum PersistenciaEnum {
	CSV(".csv"), JSON(".json"), XML(".xml"), TXT(".txt");
	
	private String extensao;
	
	private PersistenciaEnum(String extensao) {
		this.setExtensao(extensao);
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
}