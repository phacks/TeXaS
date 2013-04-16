package menubas.menubasBlocs;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.TextFieldAutoSuppression;
import briquesElementaires.JPanelDef;


public class MenuBasBlocNoteBasPage extends JPanelDef {
	
	private JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();

	private TextFieldAutoSuppression texteTitre = new TextFieldAutoSuppression("Texte de la note de bas de page"); 
	
	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Ins�rer");

	public MenuBasBlocNoteBasPage(){

		this.setLayout(new BorderLayout(30, 0));
		main.setLayout(new GridLayout(3,3));
		
		main.add(new JPanelDef());
		main.add(new JPanelDef());
		main.add(new JPanelDef());
		
		main.add(new JPanelDef());
		main.add(texteTitre);
		main.add(new JPanelDef());
		
		main.add(new JPanelDef());
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

}
