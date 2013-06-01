package menubas.menubasMathematiques;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import formule.ActionListenerFormule;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import EcranEditionCentral.ContenuEditable;
import briquesElementaires.JPanelDef;

public class PanelMathematiquesFavoris extends JPanelDef {

	//Images des symboles
	private String cheminFichier;
	private File emplacementImage;
	private static int nbImage;
	private int indexRemplissage=0;
	private static int nbLignes=5;
	private static int nbColonnes=18;

	// Les JPanel
	private static JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();

	// Les boutons
	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Insérer");
	private BoutonMenuBasValidate boutonReinistialiser = new BoutonMenuBasValidate("Réinistialiser");
	private static BoutonMenuMathematiqueSymbole[] boutonMenu;

	public PanelMathematiquesFavoris() {
		super();

		this.cheminFichier = "SymbolesLaTex/SymbolesLaTeXUsuels";
		this.emplacementImage = new File(this.cheminFichier);

		// Structure centrale
		creerTableauImage();
		main.setLayout(new GridLayout(nbLignes,nbColonnes,3,3));
		for (int i = 0; i < Math.min(nbImage,nbColonnes*nbLignes); i++) {
			main.add(boutonMenu[i]);
		}

		for (int i = Math.min(nbImage,nbColonnes*nbLignes); i < nbColonnes*nbLignes; i++) {
			main.add(new JPanelDef());		
		}

		// Structure de base (marge en haut et bouton insérer à droite)
		this.setLayout(new BorderLayout());

		main1.setLayout(new BorderLayout());
		main1.add(main, BorderLayout.CENTER);
		main1.add(new JPanelDef(), BorderLayout.NORTH);
		main1.add(new JPanelDef(), BorderLayout.SOUTH);
		main1.add(new JPanelDef(), BorderLayout.EAST);
		main1.add(new JPanelDef(), BorderLayout.WEST);
		this.add(main1, BorderLayout.CENTER);

		partieEast.setLayout(new GridLayout(3,1));
		partieEast.add(new PanelboutonStyle());
		partieEast.add(boutonInserer);
		partieEast.add(boutonReinistialiser);

		this.add(partieEast, BorderLayout.EAST);
		this.revalidate();

		// Gestion des actions sur les boutons
		
		boutonInserer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			ContenuEditable.addEditeurFormule();
			}
		});
		
		
		boutonReinistialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creerTableauImage();
				main.removeAll();
				for (int i = 0; i < Math.min(nbImage,nbColonnes*nbLignes); i++) {
					main.add(boutonMenu[i]);
				}

				for (int i = Math.min(nbImage,nbColonnes*nbLignes); i < nbColonnes*nbLignes; i++) {
					main.add(new JPanelDef());		
				}
				main.revalidate();
				main.repaint();
			}
		});
		
		for (int i = 0; i < boutonMenu.length; i++) {
			boutonMenu[i].addActionListener(new ActionListenerFormule("symbole"));
		}

	}


	private void creerTableauImage() {
		nbImage=0;
		indexRemplissage=0;
		DecompteImage(emplacementImage);
		boutonMenu = new BoutonMenuMathematiqueSymbole[this.nbImage];
		remplirTableau(emplacementImage);
	}

	public void DecompteImage(File file){

		for(File sousFichier : file.listFiles()){
			if(sousFichier.isDirectory()){
				DecompteImage(sousFichier);
			}
			else{
				if(sousFichier.getName().substring(sousFichier.getName().length()-3, sousFichier.getName().length()).equals("png")){
					this.nbImage++;
				}
			}
		}
	}

	public void remplirTableau(File file){
		for(File sousFichier : file.listFiles()){
			if(sousFichier.isDirectory()){
				remplirTableau(sousFichier);
			}
			else{
				if(sousFichier.getName().substring(sousFichier.getName().length()-3, sousFichier.getName().length()).equals("png")){
					boutonMenu[indexRemplissage]=new BoutonMenuMathematiqueSymbole(sousFichier);
					indexRemplissage++;
				}
			}
		}
	}


	public static void ajouter(File file) {
		int existeDeja = existeDansListe(file);
		if(existeDeja>=0){
			for (int i = existeDeja; i > 0; i--) {
				boutonMenu[i]=boutonMenu[i-1];
			}
			boutonMenu[0] = new BoutonMenuMathematiqueSymbole(file);
			main.removeAll();
			for (int i = 0; i < Math.min(nbImage,nbColonnes*nbLignes); i++) {
				main.add(boutonMenu[i]);
			}

			for (int i = Math.min(nbImage,nbColonnes*nbLignes); i < nbColonnes*nbLignes; i++) {
				main.add(new JPanelDef());		
			}
			main.revalidate();
		}
		else{
			main.removeAll();
			if(nbImage < nbColonnes*nbLignes){
				BoutonMenuMathematiqueSymbole[] temp = boutonMenu;
				nbImage++;
				boutonMenu = new BoutonMenuMathematiqueSymbole[nbImage];
				for (int i = boutonMenu.length-1; i > 0; i--) {
					boutonMenu[i]=temp[i-1];
				}
				boutonMenu[0] = new BoutonMenuMathematiqueSymbole(file);
			}
			else{
				for (int i = nbColonnes*nbLignes-1; i > 0; i--) {
					boutonMenu[i]=boutonMenu[i-1];
				}
				boutonMenu[0]=new BoutonMenuMathematiqueSymbole(file);
			}
			for (int i = 0; i < Math.min(nbImage,nbColonnes*nbLignes); i++) {
				main.add(boutonMenu[i]);
			}

			for (int i = Math.min(nbImage,nbColonnes*nbLignes); i < nbColonnes*nbLignes; i++) {
				main.add(new JPanelDef());		
			}
			main.revalidate();
		}
	}


	private static int existeDansListe(File file) {
		int existeDeja = -1;
		for (int i = 0; i < boutonMenu.length; i++) {
			if(boutonMenu[i].getFichierBouton().getName().equals(file.getName())){
				existeDeja=i;
			}
		}
		return existeDeja;
	}

}
