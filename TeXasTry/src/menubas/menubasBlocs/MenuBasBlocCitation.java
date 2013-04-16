package menubas.menubasBlocs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.RadioButtonDefaut;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class MenuBasBlocCitation extends JPanelDef {

	private JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();

	private RadioButtonDefaut citationCourte = new RadioButtonDefaut("Citation courte", true);
	private RadioButtonDefaut citationLongue = new RadioButtonDefaut("Citation longue",false);
	private ButtonGroup groupeBoutonGauche = new ButtonGroup();
	
	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Insérer");

	public MenuBasBlocCitation(){

		this.setLayout(new BorderLayout(30, 0));
		main.setLayout(new GridLayout(2,3));
		
		groupeBoutonGauche.add(citationCourte);
		groupeBoutonGauche.add(citationLongue);
		
		main.add(citationCourte);
		main.add(new JPanelDef());
		main.add(new JPanelDef());
		

		main.add(citationLongue);
		main.add(new JPanelDef());
		main.add(new JPanelDef());

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
		this.citationCourte.setSelected(true);
	}

}
