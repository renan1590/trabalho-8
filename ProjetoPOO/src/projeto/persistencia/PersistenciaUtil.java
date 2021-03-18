package projeto.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class PersistenciaUtil {
	public static void criarArquivo(String caminho, String conteudo) {
		try {
			File f = new File(caminho);
			
			if (! f.getParentFile().exists())
				f.getParentFile().mkdirs();
			
			FileWriter write = new FileWriter(f);
			
			write.write(conteudo);
			
			write.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String buscarConteudoArquivo(String caminho) {
		String s = "";
		
		try {
			File f = new File(caminho);
			
			if (! f.exists()) {
				return s;
			}
			
			FileInputStream stream = new FileInputStream(f);
			
			s = new String(stream.readAllBytes());
			
			stream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}
}