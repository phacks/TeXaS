import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import EcranEditionCentral.EditeurTitre;
import SlpashScreen.OpenWindow;
import XML.ConversionXMLversInterface;


public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}


		// Decommenter la ligne du dessous pour ajouter la gestion des partie masquable
		EditeurTitre.masquable = true;

		// Ecran de chargement
		OpenWindow wind = new OpenWindow();

		// Creation de l'interface principale
		Fenetre maFen = new Fenetre();

		// Lecture du fichier enregistré
		OpenWindow.setText("Lecture du fichier enregistré");
		ConversionXMLversInterface.fromXML();

		// Affichage du programme
		OpenWindow.setText("Affichage");
		wind.setVisible(false);
		maFen.setVisible(true);

		maFen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}

}
