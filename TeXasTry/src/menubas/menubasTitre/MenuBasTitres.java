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
import menubas.RadioButtonMenuBas;
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

	private JTextField texteTitre = new JTextField("Votre titre ici");

	private RadioButtonMenuBas checkPartie = new RadioButtonMenuBas("Partie", true);
	private RadioButtonMenuBas checkChapitre = new RadioButtonMenuBas("Chapitre");
	private RadioButtonMenuBas checkSection = new RadioButtonMenuBas("Section");
	private RadioButtonMenuBas checkSSection = new RadioButtonMenuBas("Sous-section");
	private RadioButtonMenuBas checkSSSection = new RadioButtonMenuBas("Sous-sous-section");
	private RadioButtonMenuBas[] checkCategories = {
			checkPartie,checkChapitre,checkSection,checkSSection,checkSSSection	};
	private ButtonGroup groupeBoutonGauche = new ButtonGroup();
	
	private RadioButtonMenuBas checkNumerotation = new RadioButtonMenuBas("Numérotation", true);
	private RadioButtonMenuBas checkTdM = new RadioButtonMenuBas("Table des matières", true);
		

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
		texteTitre.setFont(Police.segoe);
		texteTitre.setHorizontalAlignment(JTextField.CENTER);
		partieDroite.add(texteTitre);
//		partieDroite.add(boutInserer);

		
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
