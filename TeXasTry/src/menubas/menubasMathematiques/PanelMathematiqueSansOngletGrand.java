package menubas.menubasMathematiques;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import formule.ActionListenerFormule;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import EcranEditionCentral.ContenuEditable;
import briquesElementaires.JPanelDef;

public class PanelMathematiqueSansOngletGrand extends JPanelDef {

	//Images des symboles
	private String nomType;
	private String cheminFichier;
	private File emplacementImage;
	private int nbImage;
	private int indexRemplissage=0;
	private int nbLigne=2;
	private int nbColonnes;

	// Les JPanel
	private JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();

	// Les boutons
	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Insérer");
	private BoutonMenuMathematiqueSymbole[] boutonMenuTous;

	public PanelMathematiqueSansOngletGrand(String string) {
		super();

		this.nomType = string;
		this.cheminFichier = "SymbolesLaTex/SymbolesLaTeXSmall/"+nomType;
		this.emplacementImage = new File(this.cheminFichier);

		// Structure centrale
		creerTableauImage();
		double nbImageFloat = (double) this.nbImage;
		double nbLigneFloat = (double) this.nbLigne;
		double nbColonneFloat = Math.ceil(nbImageFloat/nbLigneFloat);
		this.nbColonnes = (int) nbColonneFloat;

		main.setLayout(new GridLayout(nbLigne,nbColonnes,3,3));
		for (int i = 0; i < nbImage; i++) {
			main.add(boutonMenuTous[i]);
		}

		for (int i = nbImage; i < nbColonnes*nbLigne; i++) {
			main.add(new JPanelDef());		
		}
		
		boutonInserer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			ContenuEditable.addEditeurFormule();
			}
		});
		
		for (int i = 0; i < boutonMenuTous.length; i++) {
			if (boutonMenuTous[i].getFichierBouton().getName().startsWith("structure")){
				boutonMenuTous[i].addActionListener(new ActionListenerFormule("structure"));
			}
			
			else if (boutonMenuTous[i].getFichierBouton().getName().startsWith("parentheses")){
				boutonMenuTous[i].addActionListener(new ActionListenerFormule("parentheses"));
			}
		}

		// Gestion favoris

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
		partieEast.add(new PanelboutonStyle());

		this.add(partieEast, BorderLayout.EAST);
		this.revalidate();

	}


	private void creerTableauImage() {
		DecompteImage(emplacementImage);
		boutonMenuTous = new BoutonMenuMathematiqueSymbole[this.nbImage];
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
					boutonMenuTous[indexRemplissage]=new BoutonMenuMathematiqueSymbole(sousFichier);
					indexRemplissage++;
				}
			}
		}
	}

}
