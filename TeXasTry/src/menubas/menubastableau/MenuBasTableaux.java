package menubas.menubastableau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import menubas.BoutonMenuBasValidate;
import menubas.RadioButtonDefaut;
import menubas.TextAligned;


import briquesElementaires.JPanelDef;
import briquesElementaires.Police;




public class MenuBasTableaux extends JPanelDef  {

	// Les JPanels
	private JPanelDef main = new JPanelDef();
	private JPanelDef partieGauche = new JPanelDef();
	private MenuBasTableauCentre partieCentrale = new MenuBasTableauCentre();
	private JPanelDef partieDroite = new JPanelDef();

	// Les éléments interagissant
	private BoutonMenuBasValidate boutInserer = new BoutonMenuBasValidate("Insérer");
	private BoutonMenuBasValidate boutFusionner = new BoutonMenuBasValidate("Fusionner");
	private BoutonMenuBasValidate boutReinistialiser = new BoutonMenuBasValidate("Réinistialiser");

	private RadioButtonDefaut checkBordures = new RadioButtonDefaut("Bordure", true);
	private RadioButtonDefaut checkLegende = new RadioButtonDefaut("Légende", true);
	private RadioButtonDefaut checkNumerotation = new RadioButtonDefaut("Numérotation", true);

	private BoutonIconeUpAndDown up1 = new BoutonIconeUpAndDown("up");
	private BoutonIconeUpAndDown down1 = new BoutonIconeUpAndDown("down");
	private BoutonIconeUpAndDown up2 = new BoutonIconeUpAndDown("up");
	private BoutonIconeUpAndDown down2 = new BoutonIconeUpAndDown("down");

	private FormattedTextPositiveInteger fieldLignes = new FormattedTextPositiveInteger(NumberFormat.getIntegerInstance());
	private FormattedTextPositiveInteger fieldColonnes = new FormattedTextPositiveInteger(NumberFormat.getIntegerInstance());

	public MenuBasTableaux() throws ParseException{
		super();
		this.setLayout(new BorderLayout());
		main.setFont(Police.segoe);
		main.setBackground(Color.WHITE);
		main.setLayout(new BorderLayout());


		// Traitement partie gauche

		partieGauche.setBackground(Color.WHITE);
		partieGauche.setLayout(new GridLayout(5, 1, 0, 3));

		// Partie edit du nombre de ligne
		JPanelDef partieGaucheLigne = new JPanelDef(new GridLayout(1,2));
		JPanelDef partieGaucheLigneChoix = new JPanelDef(new GridLayout(1,2));
		JPanelDef partieGaucheLigneBout = new JPanelDef(new GridLayout(2,1));

		partieGaucheLigneBout.add(up1);
		partieGaucheLigneBout.add(down1);

		fieldLignes.setText("2");
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

		fieldColonnes.setText("3");
		partieGaucheColonneChoix.add(fieldColonnes);
		partieGaucheColonneChoix.add(partieGaucheColonneBout);

		TextAligned textColonnes = new TextAligned("Colonnes :",false);
		partieGaucheColonne.add(textColonnes);
		partieGaucheColonne.add(partieGaucheColonneChoix);

		partieGauche.add(partieGaucheColonne);

		partieGauche.add(checkBordures);
		partieGauche.add(checkLegende);
		partieGauche.add(checkNumerotation);


		main.add(partieGauche,BorderLayout.WEST);

		fieldColonnes.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				partieCentrale.setNbColonne(Integer.parseInt(fieldColonnes.getText()));
				partieCentrale.redessiner();
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

		});

		fieldLignes.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				partieCentrale.setNbLigne(Integer.parseInt(fieldLignes.getText()));
				partieCentrale.redessiner();
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});

		fieldColonnes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				partieCentrale.setNbColonne(Integer.parseInt(fieldColonnes.getText()));
				partieCentrale.redessiner();
			}
		});

		fieldLignes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				partieCentrale.setNbLigne(Integer.parseInt(fieldLignes.getText()));
				partieCentrale.redessiner();
			}
		});

		up1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldLignes.up();
				partieCentrale.setNbLigne(partieCentrale.getNbLigne()+1);
				partieCentrale.redessiner();
			}
		});

		up2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldColonnes.up();	
				partieCentrale.setNbColonne(partieCentrale.getNbColonne()+1);
				partieCentrale.redessiner();
			}
		});

		down1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldLignes.down();
				if(partieCentrale.getNbLigne()>1){
					partieCentrale.setNbLigne(partieCentrale.getNbLigne()-1);
					partieCentrale.redessiner();
				}
			}
		});

		down2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fieldColonnes.down();
				if(partieCentrale.getNbColonne()>1){
					partieCentrale.setNbColonne(partieCentrale.getNbColonne()-1);
					partieCentrale.redessiner();
				}
			}
		});
		// Traitement partie centrale

		main.add(partieCentrale,BorderLayout.CENTER);
		checkBordures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuBasTableUI.setBordure(checkBordures.isSelected());
				partieCentrale.repaint();
			}
		});

		// Traitement partie droite

		partieDroite.setLayout(new GridLayout(3,1));
		partieDroite.add(boutFusionner);
		partieDroite.add(boutInserer);
		partieDroite.add(boutReinistialiser);
		main.add(partieDroite, BorderLayout.EAST);

		this.add(new JPanelDef(), BorderLayout.WEST);
		this.add(main, BorderLayout.CENTER);

		boutInserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkNumerotation.setSelected(true);
				checkLegende.setSelected(true);
				//					texteLegende.setText("Votre légende ici");
			}
		});

		boutFusionner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				partieCentrale.fusionner();
			}
		});
		
		boutReinistialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				partieCentrale.redessiner();
			}
		});

	}


	public void reinistialisation() {
		checkBordures.setSelected(true);
		checkNumerotation.setSelected(true);
		checkLegende.setSelected(true);
		fieldLignes.setText("2");
		partieCentrale.setNbLigne(2);
		fieldColonnes.setText("3");
		partieCentrale.setNbColonne(3);
		partieCentrale.redessiner();
		
	}


}

