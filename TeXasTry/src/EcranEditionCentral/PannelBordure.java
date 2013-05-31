package EcranEditionCentral;

import java.awt.BorderLayout;

import javax.swing.JButton;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;

public class PannelBordure extends JPanelDef {

	JButton monBoutonInvisible = new JButton("                 ");

	public PannelBordure(){
		super(new BorderLayout());
		monBoutonInvisible.setEnabled(false);
		monBoutonInvisible.setBackground(Couleur.white);
		monBoutonInvisible.setBorder(null);
		monBoutonInvisible.setFocusable(false);
		this.add(monBoutonInvisible, BorderLayout.CENTER);
	}
}
