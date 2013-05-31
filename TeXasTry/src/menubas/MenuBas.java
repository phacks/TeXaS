package menubas;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JPanel;

import menubas.menubasBlocs.MenuBasBlocs;
import menubas.menubasFigures.MenuBasFigures;
import menubas.menubasMathematiques.MenuBasMathematiques;
import menubas.menubasTitre.MenuBasTitres;
import menubas.menubastableau.MenuBasTableaux;
import EcranEditionCentral.ContenuEditable;
import EcranEditionCentral.EcranEditionCentral;
import briquesElementaires.JPanelDef;



public class MenuBas extends JPanel{

	// Les JPanels

	// Menu Bas partie basse

	private MenuBasBase menuBasChoix = new MenuBasBase();
	private JPanelDef menuBasNorth = new JPanelDef();

	// Menu Bas onglets

	private MenuBasTitres menuBasTitres = new MenuBasTitres();
	private MenuBasFigures menuBasFigures = new MenuBasFigures();
	private MenuBasTableaux menuBasTableaux;
	private MenuBasBlocs menuBasBlocs = new MenuBasBlocs();
	private MenuBasMathematiques menuBasMathematiques = new MenuBasMathematiques();

	//Les boutons

	private BoutonMenuBas boutInserer = new BoutonMenuBas("Insérer");
	private RadioButtonReinit boutReinistialisation = new RadioButtonReinit();

	// Le statut du ruban

	private boolean rubanOuvert = false;

	public MenuBas() {

		// Instanciation risquée
		try {
			menuBasTableaux= new MenuBasTableaux();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Gestion des onglets
		menuBasChoix.add("Titres", menuBasTitres);
		menuBasChoix.add("Mathématiques", menuBasMathematiques);
		menuBasChoix.add("Figures", menuBasFigures);
		menuBasChoix.add("Tableaux", menuBasTableaux);
		menuBasChoix.add("Blocs", menuBasBlocs);



		// Gestion du menu de base
		this.setLayout(new BorderLayout());

		menuBasNorth.setLayout(new BorderLayout());
		menuBasNorth.add(boutInserer,BorderLayout.CENTER);
		PanelDesignNorth panelReinit = new PanelDesignNorth();
		panelReinit.add(boutReinistialisation);
		menuBasNorth.add(panelReinit,BorderLayout.EAST);
		this.add(menuBasNorth,BorderLayout.NORTH);
		this.revalidate();

		boutInserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(rubanOuvert){
//					ContenuEditable.revalider();
//					ContenuEditable.setScrollPanePosition(ContenuEditable.getScrollPanePosition()+260);
//					EcranEditionCentral.getScrollPane().getVerticalScrollBar().setValue(ContenuEditable.getScrollPanePosition()+260);
//					ContenuEditable.revalider();
//				}
//				else{
//					ContenuEditable.revalider();
//					ContenuEditable.setScrollPanePosition(ContenuEditable.getScrollPanePosition()-260);
//					EcranEditionCentral.getScrollPane().getVerticalScrollBar().setValue(ContenuEditable.getScrollPanePosition()-260);
//
//					ContenuEditable.revalider();	
//				}
				ouvrirMenuBas();
			}
		});
	}

	private void ouvrirMenuBas() {
		if (!boutInserer.isCheck()){
			this.removeAll();

			if(boutReinistialisation.isSelected()){
				menuBasTitres.reinistialisation();
				menuBasFigures.reinistialisation();
				menuBasTableaux.reinistialisation();
				menuBasBlocs.reinistialisation();
				menuBasMathematiques.reinistialisation();
			}


			this.add(menuBasNorth,BorderLayout.NORTH);
			this.add(menuBasChoix, BorderLayout.SOUTH);
			this.revalidate();
			boutInserer.check();
		}
		else{
			this.removeAll();
			this.add(menuBasNorth,BorderLayout.NORTH);
			this.revalidate();
			boutInserer.uncheck();
		}
	}

}
