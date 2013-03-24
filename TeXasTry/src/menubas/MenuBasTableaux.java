package menubas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import briquesElementaires.JPanelDef;
import briquesElementaires.Police;




public class MenuBasTableaux extends JPanelDef {

	// Les JPanels
	private JPanelDef partieGauche = new JPanelDef();
	private MenuBasTableauCentre partieCentrale = new MenuBasTableauCentre();
	private JPanelDef partieDroite = new JPanelDef();

	// Les éléments interagissant
	private BoutonMenuBasValidate boutInserer = new BoutonMenuBasValidate("Insérer");

	private RadioButtonMenuBas checkBordures = new RadioButtonMenuBas("Bordure", true);
	private RadioButtonMenuBas checkLegende = new RadioButtonMenuBas("Légende", true);
	private RadioButtonMenuBas checkNumerotation = new RadioButtonMenuBas("Numérotation", true);

	private BoutonIcone up1 = new BoutonIcone("up");
	private BoutonIcone down1 = new BoutonIcone("down");
	private BoutonIcone up2 = new BoutonIcone("up");
	private BoutonIcone down2 = new BoutonIcone("down");

	private FormattedTextPositiveInteger fieldLignes = new FormattedTextPositiveInteger(NumberFormat.getIntegerInstance());
	private FormattedTextPositiveInteger fieldColonnes = new FormattedTextPositiveInteger(NumberFormat.getIntegerInstance());

	public MenuBasTableaux() throws ParseException{
		super();
		this.setFont(Police.segoe);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());


		// Traitement partie gauche

		partieGauche.setBackground(Color.WHITE);
		partieGauche.setLayout(new GridLayout(5, 1, 0, 3));

		// Partie edit du nombre de ligne
		JPanelDef partieGaucheLigne = new JPanelDef(new GridLayout(1,2));
		JPanelDef partieGaucheLigneChoix = new JPanelDef(new GridLayout(1,2));
		JPanelDef partieGaucheLigneBout = new JPanelDef(new GridLayout(2,1));

		partieGaucheLigneBout.add(up1);
		partieGaucheLigneBout.add(down1);

		partieGaucheLigneChoix.add(fieldLignes);
		partieGaucheLigneChoix.add(partieGaucheLigneBout);

		TextAligned textLignes = new TextAligned("Lignes :",false);
		partieGaucheLigne.add(textLignes);
		partieGaucheLigne.add(partieGaucheLigneChoix);

		partieGauche.add(partieGaucheLigne);


		// Partie edit du nombre de colonnes
		JPanelDef partieGaucheColonne = new JPanelDef(new GridLayout(1,2));
		JPanelDef partieGaucheColonneChoix = new JPanelDef(new GridLayout(1,2));
		JPanelDef partieGaucheColonneBout = new JPanelDef(new GridLayout(2,1));

		partieGaucheColonneBout.add(up2);
		partieGaucheColonneBout.add(down2);

		partieGaucheColonneChoix.add(fieldColonnes);
		partieGaucheColonneChoix.add(partieGaucheColonneBout);

		TextAligned textColonnes = new TextAligned("Colonnes :",false);
		partieGaucheColonne.add(textColonnes);
		partieGaucheColonne.add(partieGaucheColonneChoix);

		partieGauche.add(partieGaucheColonne);

		partieGauche.add(checkBordures);
		partieGauche.add(checkLegende);
		partieGauche.add(checkNumerotation);


		this.add(partieGauche,BorderLayout.WEST);

		up1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldLignes.up();
			}
		});

		up2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldColonnes.up();	
			}
		});

		down1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldLignes.down();
			}
		});

		down2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldColonnes.down();
			}
		});
		// Traitement partie centrale
		
		

		this.add(partieCentrale,BorderLayout.CENTER);

		// Traitement partie droite

		partieDroite.setLayout(new GridLayout(2,1));
		partieDroite.add(new JPanelDef());
		partieDroite.add(boutInserer);
		this.add(partieDroite, BorderLayout.EAST);
		
		this.revalidate();
		
		boutInserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkNumerotation.setSelected(true);
				checkLegende.setSelected(true);
				//					texteLegende.setText("Votre légende ici");
			}
		});

	}


	public void reinistialisation() {

		checkNumerotation.setSelected(true);
		checkLegende.setSelected(true);

	}

	
}

