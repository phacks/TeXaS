package MenuGauche;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MenuGauche {

	// Les JPanels

	private JPanel menuGauche = new JPanel();

	private MenuGaucheBase base = new MenuGaucheBase();
	private MenuGaucheDroite monProjetMenuDroite = new MenuGaucheDroite();
	private MenuGaucheDroite preferencesMenuDroite = new MenuGaucheDroite();
	private MenuGaucheDroite personnaliserMenuDroite = new MenuGaucheDroite();
	private MenuGaucheDroite editerMenuDroite = new MenuGaucheDroite();
	private MenuGaucheDroite exporterMenuDroite = new MenuGaucheDroite();
	private MenuGaucheDroite aideMenuDroite = new MenuGaucheDroite();
	
	private MenuGaucheDroite[] mesMenusDroite = {
			monProjetMenuDroite,preferencesMenuDroite,personnaliserMenuDroite,editerMenuDroite,exporterMenuDroite,aideMenuDroite};

	// Les boutons

	// de base
	private BoutonMenuGaucheBase boutMonProjet = new BoutonMenuGaucheBase("Mon Projet");
	private BoutonMenuGaucheBase boutPref = new BoutonMenuGaucheBase("Pr�f�rences");
	private BoutonMenuGaucheBase boutPerso = new BoutonMenuGaucheBase("Personnaliser");
	private BoutonMenuGaucheBase boutEditer = new BoutonMenuGaucheBase("Editer le code LaTeX");
	private BoutonMenuGaucheBase boutExport = new BoutonMenuGaucheBase("Exporter en PDF");
	private BoutonMenuGaucheBase boutAide = new BoutonMenuGaucheBase("Aide");

	private BoutonMenuGaucheBase[] mesBoutonsBase = {
			boutMonProjet,boutPref,boutPerso,boutEditer,boutExport,boutAide};

	// du menu Projet
	private BoutonMenuGaucheDroite boutNewProj = new BoutonMenuGaucheDroite("Nouveau Projet");
	private BoutonMenuGaucheDroite boutSave = new BoutonMenuGaucheDroite("Enregistrer");
	private BoutonMenuGaucheDroite boutSaveAs = new BoutonMenuGaucheDroite("Enregistrer Sous");
	private BoutonMenuGaucheDroite boutOpen = new BoutonMenuGaucheDroite("Ouvrir");
	private BoutonMenuGaucheDroite boutFermer = new BoutonMenuGaucheDroite("Fermer");
	
	private BoutonMenuGaucheDroite[] mesBoutonsProjet = {
			boutNewProj,boutSave,boutSaveAs,boutOpen,boutFermer};
	
	//du menu Pr�f�rences
	private BoutonMenuGaucheDroite boutGeneral = new BoutonMenuGaucheDroite("General");
	private BoutonMenuGaucheDroite boutAffichage = new BoutonMenuGaucheDroite("Affichage");
	private BoutonMenuGaucheDroite boutEnregistrement = new BoutonMenuGaucheDroite("Enregistrement");
	private BoutonMenuGaucheDroite boutOptAvancees = new BoutonMenuGaucheDroite("Options Avanc�es");

	private BoutonMenuGaucheDroite[] mesBoutonsPreferences = {
			boutGeneral,boutAffichage,boutEnregistrement,boutOptAvancees};

	//du menu Personnaliser
	private BoutonMenuGaucheDroite boutMiseEnPage = new BoutonMenuGaucheDroite("Mise En Page");
	private BoutonMenuGaucheDroite boutPolice = new BoutonMenuGaucheDroite("Police");
	private BoutonMenuGaucheDroite boutEnTete = new BoutonMenuGaucheDroite("En T�te et Pied de Page");
	private BoutonMenuGaucheDroite boutPageDeGarde = new BoutonMenuGaucheDroite("Page de Garde");
	
	private BoutonMenuGaucheDroite[] mesBoutonsPersonnaliser = {
			boutMiseEnPage,boutPolice,boutEnTete,boutPageDeGarde};
	
	//du menu editer le code
	private BoutonMenuGaucheDroite[] mesBoutonsEditer = {};
	
	// du menu exporter en PDF 
	private BoutonMenuGaucheDroite[] mesBoutonsExporter = {};
	
	// du menu Aide
	private BoutonMenuGaucheDroite boutBase = new BoutonMenuGaucheDroite("Bases");
	private BoutonMenuGaucheDroite boutMath = new BoutonMenuGaucheDroite("Hi�rarchie");
	private BoutonMenuGaucheDroite boutTable = new BoutonMenuGaucheDroite("Tables");
	private BoutonMenuGaucheDroite boutFigure = new BoutonMenuGaucheDroite("Figure");
	private BoutonMenuGaucheDroite boutAPropos = new BoutonMenuGaucheDroite("A propos");
	
	private BoutonMenuGaucheDroite[] mesBoutonsAide = {
			boutBase,boutMath,boutTable,boutFigure,boutAPropos	};
	
	// Ensemble boutons droite
	private BoutonMenuGaucheDroite[][] ensembleBoutonsDroites = {
			mesBoutonsProjet,mesBoutonsPreferences,mesBoutonsPersonnaliser,mesBoutonsEditer,mesBoutonsExporter,mesBoutonsAide};
	
	// Getters & Setters
	
	public JPanel getMenuGauche() {
		return menuGauche;
	}
	
	// Constructeur de classe
	public MenuGauche(){

		menuGauche.setLayout(new BorderLayout());
		
		// Menu de base

		for (int i = 0; i < mesBoutonsBase.length; i++) {
			base.add(mesBoutonsBase[i]);
		}
		
		// Sous-menus droites
		
		for (int i = 0; i < mesMenusDroite.length; i++) {
			for (int j = 0; j < ensembleBoutonsDroites[i].length; j++) {
				mesMenusDroite[i].add(ensembleBoutonsDroites[i][j]);
			}
		}

		// Finalisation du menu

		menuGauche.add(base);
		menuGauche.revalidate();

		// Gestion des boutons

		boutMonProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ouvrirMenu(boutMonProjet);
			}
		});
		
		 boutPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ouvrirMenu(boutPref);	
			}
		});
		 
		 boutPerso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ouvrirMenu(boutPerso);	
			}
		});
		 
		 boutAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ouvrirMenu(boutAide);	
			}
		});

	}

	
	// Methode de gestion de l'ouverture d'un menu lors d'un clic sur un bouton du menu de base
	protected void ouvrirMenu(BoutonMenuGaucheBase bouton) {
		if (!bouton.isCheck()){
			uncheck(bouton);
			menuGauche.removeAll();
			menuGauche.add(base, BorderLayout.WEST);
			int i = indice(bouton);
			menuGauche.add(mesMenusDroite[i], BorderLayout.CENTER);
			bouton.check();
			menuGauche.revalidate();
		}
		else{
			menuGauche.removeAll();
			menuGauche.add(base, BorderLayout.WEST);
			menuGauche.revalidate();
			bouton.uncheck();
		}
	}

	// Methode pour trouver l'indice d'un bouton dans le tableau des boutons du menu de base
	private int indice(BoutonMenuGaucheBase bouton) {
		for (int i = 0; i < mesBoutonsBase.length; i++) {
			if (mesBoutonsBase[i].equals(bouton)){
				return i;
			}
		}
		return 0;
	}
	
	// M�thode pour d�checker les boutons lors du clic
	private void uncheck(BoutonMenuGaucheBase bouton) {
		for (int i = 0; i < mesBoutonsBase.length; i++) {
			if (!mesBoutonsBase[i].equals(bouton)){
				mesBoutonsBase[i].uncheck();
			}
		}
	}

}