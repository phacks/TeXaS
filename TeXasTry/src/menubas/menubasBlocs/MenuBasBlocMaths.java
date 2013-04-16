package menubas.menubasBlocs;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.RadioButtonDefaut;
import menubas.TextFieldAutoSuppression;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class MenuBasBlocMaths extends JPanelDef {

	private JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieGauche = new JPanelDef();
	private JPanelDef partieCentrale = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();
	
	private RadioButtonDefaut checkTheoreme = new RadioButtonDefaut("Théorême", true);
	private RadioButtonDefaut checkProposition = new RadioButtonDefaut("Proposition", false);
	private RadioButtonDefaut checkLemme = new RadioButtonDefaut("Lemme", false);
	private RadioButtonDefaut checkPropriete = new RadioButtonDefaut("Propriété", false);
	private RadioButtonDefaut checkCorollaire = new RadioButtonDefaut("Corollaire", false);
	private RadioButtonDefaut checkDemonstration = new RadioButtonDefaut("Démonstration", false);
	private RadioButtonDefaut[] checkType={
			checkTheoreme,checkProposition,checkLemme,checkPropriete,checkCorollaire,checkDemonstration};
	private ButtonGroup groupeBoutonGauche = new ButtonGroup();

	private String titre = "Titre du bloc mathématique";
	private TextFieldAutoSuppression texteTitre = new TextFieldAutoSuppression(titre); 
	
	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Insérer");

	public MenuBasBlocMaths(){
		
		this.setLayout(new BorderLayout(30, 0));
		main.setLayout(new BorderLayout());
		main1.setLayout(new BorderLayout());
		
		// Traitement partie gauche
		partieGauche.setLayout(new GridLayout(6, 1));
		for (int i = 0; i < checkType.length ; i++) {
			groupeBoutonGauche.add(checkType[i]);
			partieGauche.add(checkType[i]);
		}
		main.add(partieGauche, BorderLayout.WEST);
		
		// Traitement partie droite
		partieCentrale.setLayout(new GridLayout(3,3,20,20));
		
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(new JPanelDef());
		
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(texteTitre);
		partieCentrale.add(new JPanelDef());
		
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(new JPanelDef());
		partieCentrale.add(new JPanelDef());
		
		main.add(partieCentrale, BorderLayout.CENTER);
		
		// Finalisation
		main1.setLayout(new BorderLayout());
		main1.add(main, BorderLayout.CENTER);
		main1.add(new JPanelDef(), BorderLayout.NORTH);
		this.add(main1, BorderLayout.CENTER);
		
		partieEast.setLayout(new GridLayout(3,1));
		partieEast.add(new PanelboutonStyle());
		partieEast.add(boutonInserer);
		partieEast.add(new PanelboutonStyle());
		
		this.add(partieEast, BorderLayout.EAST);
		this.revalidate();

	}

	public void reinstialisation() {
		this.checkTheoreme.setSelected(true);
		this.texteTitre.setText(titre);
	}
}
