package GestionEcran;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JTextField;

import menubas.MenuBas;
import EcranEditionCentral.EcranEditionCentral;
import MenuHaut.MenuHaut;
import MenuNouveauDocument.ecranNouveauDocument;
import briquesElementaires.JPanelDef;

public class EcranCentral extends JPanelDef{

	// Instanciation des objets globaux
	static CardLayout containerCentreLayout = new CardLayout();
	static String[] listeDesContenus = {"EcranNormal","EcranNouveauDocument","EcranPreferences","EcranPersonnaliser","EcranEditerLeCodeLaTeX","EcranExporterEnPDF","EcranAide"};

	// Les Panels en fonctionnement "normal" (édition du document)
	static public JPanelDef ecranNormal = new JPanelDef();
	EcranEditionCentral centreEcranNormal = new EcranEditionCentral();
	MenuHaut menuHautPan = new MenuHaut();
	MenuBas menuBasPan = new MenuBas();

	// Les Panels du fonctionnement "dans le menu"

	static JPanelDef ecranNouveauDocument = new ecranNouveauDocument();

	public EcranCentral(){

		//Gestion globale
		this.setLayout(containerCentreLayout);

		// Gestion affichage normal

		ecranNormal.setLayout(new BorderLayout());

		ecranNormal.add(menuHautPan.getMenuHaut(),BorderLayout.NORTH);

		ecranNormal.add(menuBasPan,BorderLayout.SOUTH);

		ecranNormal.add(centreEcranNormal, BorderLayout.CENTER);

		this.add(ecranNormal, listeDesContenus[0]);

		// Gestion affichage menu nouveau document

		this.add(ecranNouveauDocument, listeDesContenus[1]);


		// Gestion affiche menu préférences
		/* 
		 * 
		 * --------------TEMPORAIRE--------------
		 * 
		 */
		for (int i = 2; i < listeDesContenus.length; i++) {
			this.add(new JPanelDef(), listeDesContenus[i]);
		}
		// Ajout du composant dans les actions listeners

		new boutonMenuGaucheListener(this);

	}

}
