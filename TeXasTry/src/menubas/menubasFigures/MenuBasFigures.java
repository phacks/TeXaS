package menubas.menubasFigures;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JTextField;

import menubas.BoutonMenuBasValidate;
import menubas.RadioButtonMenuBas;
import menubas.TextAligned;


import briquesElementaires.JPanelDef;
import briquesElementaires.Police;




public class MenuBasFigures extends JPanelDef {
	// Les JPanelDefs
		private JPanelDef partieGauche = new JPanelDef();
		private JPanelDef partieCentrale = new JPanelDef();
		private JPanelDef partieDroite = new JPanelDef();


		// Les éléments interagissant
		private BoutonMenuBasValidate boutInserer = new BoutonMenuBasValidate("Insérer");
		private BoutonMenuBasValidate boutImporter = new BoutonMenuBasValidate("Importer");

		private JTextField texteLegende = new JTextField("Votre légende ici");

		private RadioButtonMenuBas checkNumerotation = new RadioButtonMenuBas("Numérotation", true);
		private RadioButtonMenuBas checkLegende = new RadioButtonMenuBas("Légende", true);
			

		public MenuBasFigures(){
			super();
			this.setLayout(new GridLayout(1, 3, 30, 0));


			// Traitement partie gauche

			partieGauche.setLayout(new GridLayout(2,1));
			partieGauche.add(boutImporter);
			partieGauche.add(new TextAligned("Formats supportés : \n JPG, PNG, BMP, GIF, EPS",true));
			
			
			
			// Traitement partie centrale
			partieCentrale.setLayout(new GridLayout(3,1));

			
			partieCentrale.add(checkNumerotation);
			partieCentrale.add(checkLegende);
			
			JPanelDef panSlide = new JPanelDef();
			panSlide.setLayout(new GridLayout(1,2));
			
			panSlide.add(new TextAligned("Taille :",false));
			
			JSlider slideTaille = new JSlider(0, 100, 30);
			slideTaille.setFont(Police.segoe);
			slideTaille.setBackground(Color.WHITE);
			slideTaille.setPaintTicks(true);
			slideTaille.setPaintLabels(true);
			slideTaille.setMinorTickSpacing(10);
			slideTaille.setMajorTickSpacing(20);
			panSlide.add(slideTaille);
			partieCentrale.add(panSlide);

			// Traitement partie droite
			partieDroite.setLayout(new GridLayout(2,1));
			texteLegende.setFont(Police.segoe);
			texteLegende.setHorizontalAlignment(JTextField.CENTER);
			partieDroite.add(texteLegende);
			partieDroite.add(boutInserer);

			this.add(partieGauche);
			this.add(partieCentrale);
			this.add(partieDroite);
			this.revalidate();

			boutInserer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					checkNumerotation.setSelected(true);
					checkLegende.setSelected(true);
					texteLegende.setText("Votre légende ici");
				}
			});

		}


		public void reinistialisation() {
			
			checkNumerotation.setSelected(true);
			checkLegende.setSelected(true);
			texteLegende.setText("Votre légende ici");
		}
}
