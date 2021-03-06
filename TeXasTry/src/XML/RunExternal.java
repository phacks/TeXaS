package XML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Lancer un ex�cutable ou une commande externe
 */
public class RunExternal {

	public static void launch(String[] command) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command);			
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = input.readLine()) != null) {
				if(line.contains("I can't write on file")){
					process.destroy();
					JOptionPane.showMessageDialog(null, "La compilation a rencontr�e un probl�me : \n" +
							"Essayez ce qui suit : \n" +
							"- Fermer le lecteur PDF. \n",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					ConversionXMLversLaTeX.setProblemeCompilation(true);
				}
			}
			//Attendre la fin de l'execution
			if (process.waitFor() != 0) {
				System.out.println("Une erreur est survenue ");

			}
		} catch (InterruptedException ex) {
			Logger.getLogger(RunExternal.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(RunExternal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}

