package menubas.menubasFigures;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.RadioButtonDefaut;
import menubas.TextAligned;
import menubas.TextFieldAutoSuppression;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;




/**Pannel du menu bas permettant la création de figure
 * @author Kilian
 *
 */
public class MenuBasFigures extends JPanelDef {
	// Les JPanelDefs
		private JPanelDef main = new JPanelDef();
		private JPanelDef main1 = new JPanelDef();	
		private JPanelDef partieEast = new JPanelDef();
	
		private JPanelDef partieGauche = new JPanelDef();
		private JPanelDef partieCentrale = new JPanelDef();
		private JPanelDef partieDroite = new JPanelDef();


		// Les éléments interagissant
		private BoutonMenuBasValidate boutInserer = new BoutonMenuBasValidate("Insérer");
		private BoutonMenuBasFigure boutImporter = new BoutonMenuBasFigure("Importer");

		private TextFieldAutoSuppression texteLegende = new TextFieldAutoSuppression("Votre légende ici");

		private RadioButtonDefaut checkNumerotation = new RadioButtonDefaut("Numérotation", true);
		private RadioButtonDefaut checkLegende = new RadioButtonDefaut("Légende", true);
		
		JSlider slideTaille = new JSlider(0, 100, 30);
			

		public MenuBasFigures(){
			super();
			
			this.setLayout(new BorderLayout(30,0));
			main1.setLayout(new BorderLayout());
			main.setLayout(new GridLayout(1, 3, 30, 0));
			
			

			// Traitement partie gauche

			partieGauche.setLayout(new GridLayout(2,1));
			partieGauche.add(boutImporter);
			partieGauche.add(new TextAligned("Formats supportés : \n JPG, PNG, BMP, GIF, EPS",true));
			
			
			
			// Traitement partie centrale
			partieCentrale.setLayout(new GridLayout(3,1));

			
			partieCentrale.add(checkNumerotation);
			partieCentrale.add(checkLegende);
			
			JPanelDef panSlide = new JPanelDef();
			panSlide.setLayout(new GridLayout(1,3));
			
			panSlide.add(new TextAligned("Taille :",false));
			
			slideTaille.setFont(Police.segoe);
			slideTaille.setBackground(Color.WHITE);
			slideTaille.setPaintTicks(true);
			slideTaille.setPaintLabels(true);
			slideTaille.setMinorTickSpacing(10);
			slideTaille.setMajorTickSpacing(20);
			panSlide.add(slideTaille);
			panSlide.add(new JPanelDef());
			partieCentrale.add(panSlide);

			// Traitement partie droite
			partieDroite.setLayout(new GridLayout(3,1));
			partieDroite.add(new JPanelDef());
			partieDroite.add(texteLegende);
			partieDroite.add(new JPanelDef());

			// Traitement partie East
			partieEast.setLayout(new GridLayout(3,1));
			partieEast.add(new PanelboutonStyle());
			partieEast.add(boutInserer);
			partieEast.add(new PanelboutonStyle());
			
			// Finalisation
			main.add(partieGauche);
			main.add(partieCentrale);
			main.add(partieDroite);
			main1.add(main, BorderLayout.CENTER);
			main1.add(new JPanelDef(), BorderLayout.NORTH);
			this.add(main1, BorderLayout.CENTER);
			this.add(partieEast, BorderLayout.EAST);
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
			slideTaille.setValue(50);
		}
}
