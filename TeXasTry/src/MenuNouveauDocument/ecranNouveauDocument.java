package MenuNouveauDocument;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import briquesElementaires.JPanelDef;

public class ecranNouveauDocument extends JPanelDef {

	// Panels
	JPanelDef north = new JPanelDef();
	JPanelDef centre = new JPanelDef();

	public ecranNouveauDocument(){
		this.setLayout(new BorderLayout());

		// Gestion du mot "nouveau"
		north.setLayout(new GridLayout(3, 1));
		north.add(new JPanelDef());
		north.add(new TextAlignedHuge("Nouveau Projet"));
		north.add(new JPanelDef());
		
		this.add(north, BorderLayout.NORTH);


		// Gestion des boutons de projet
		centre.setLayout(new GridLayout(2, 3, 100, 0));

		// Ligne 1
//		centre.add(new JPanelDef());
		for (int i = 0; i < 3; i++) {
			centre.add(new BoutonTemplate(i));
		}
//		centre.add(new JPanelDef());
		// Ligne 2
//		centre.add(new JPanelDef());
		for (int i = 3; i < 6; i++) {
			centre.add(new BoutonTemplate(i));
		}
//		centre.add(new JPanelDef());
		

		this.add(centre, BorderLayout.CENTER);
		
		this.add(new JPanelDef(), BorderLayout.SOUTH);

	}
}
