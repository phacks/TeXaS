package menubas.menubasTitre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.RadioButtonDefaut;
import menubas.TextFieldAutoSuppression;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;






public class MenuBasTitres extends JPanelDef{

	// Les JPanels
	private JPanel main = new JPanelDef();
	private JPanelDef partieGauche = new JPanelDef();
	private JPanelDef partieCentrale = new JPanelDef();
	private JPanelDef partieDroite = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();


	// Les éléments interagissant
	private BoutonMenuBasValidate boutInserer = new BoutonMenuBasValidate("Insérer");

	private TextFieldAutoSuppression texteTitre = new TextFieldAutoSuppression("Votre titre ici");

	private RadioButtonDefaut checkPartie = new RadioButtonDefaut("Partie", true);
	private RadioButtonDefaut checkChapitre = new RadioButtonDefaut("Chapitre");
	private RadioButtonDefaut checkSection = new RadioButtonDefaut("Section");
	private RadioButtonDefaut checkSSection = new RadioButtonDefaut("Sous-section");
	private RadioButtonDefaut checkSSSection = new RadioButtonDefaut("Sous-sous-section");
	private RadioButtonDefaut[] checkCategories = {
			checkPartie,checkChapitre,checkSection,checkSSection,checkSSSection	};
	private ButtonGroup groupeBoutonGauche = new ButtonGroup();
	
	private RadioButtonDefaut checkNumerotation = new RadioButtonDefaut("Numérotation", true);
	private RadioButtonDefaut checkTdM = new RadioButtonDefaut("Table des matières", true);
		

	public MenuBasTitres(){
		super();
		this.setLayout(new BorderLayout(30, 0));
		main.setLayout(new GridLayout(1, 3));


		// Traitement partie gauche

		partieGauche.setLayout(new GridLayout(5,1));
		for (int i = 0; i < checkCategories.length; i++) {
			groupeBoutonGauche.add(checkCategories[i]);
		}
		for (int i = 0; i < checkCategories.length; i++) {
			partieGauche.add(checkCategories[i]);
		}
		
		// Traitement partie centrale
		partieCentrale.setLayout(new GridLayout(5,1));
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(checkNumerotation);
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(checkTdM);

		// Traitement partie droite
		partieDroite.setLayout(new GridLayout(3,1,20,20));
		partieDroite.add(new JPanelDef());
		partieDroite.add(texteTitre);

		
		// Traitement partie basse
		partieEast.setLayout(new GridLayout(3,1));
		partieEast.add(new PanelboutonStyle());
		partieEast.add(boutInserer);
		partieEast.add(new PanelboutonStyle());
		
		main.add(partieGauche);
		main.add(partieCentrale);
		main.add(partieDroite);
		
		this.add(main, BorderLayout.CENTER);
		this.add(partieEast, BorderLayout.EAST);
		this.revalidate();

		boutInserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < checkCategories.length; i++) {
					if (checkCategories[i].isSelected()){
						System.out.println(checkCategories[i].getText() + " - " + texteTitre.getText()); 
					}
				}
				checkCategories[0].setSelected(true);
				for (int i = 1; i < checkCategories.length; i++) {
					checkCategories[i].setSelected(false);
				}
				checkNumerotation.setSelected(true);
				checkTdM.setSelected(true);
				texteTitre.setText("Votre titre ici");
				
			}
		});

	}


	public void reinistialisation() {
		checkCategories[0].setSelected(true);
		for (int i = 1; i < checkCategories.length; i++) {
			checkCategories[i].setSelected(false);
		}
		checkNumerotation.setSelected(true);
		checkTdM.setSelected(true);
		texteTitre.setText("Votre titre ici");
	}



}
