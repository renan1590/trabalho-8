package projeto.persistencia;

public interface IPersistencia<E> {
	public void gravar(E gravar);
	
	public E buscar();
}