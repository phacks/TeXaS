package menubas.menubasBlocs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.RadioButtonDefaut;
import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

/**Pannel du menu bas permettant la création de bloc de code
 * @author Kilian
 *
 */
public class MenuBasBlocCode extends JPanelDef {

	private JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieGauche = new JPanelDef();
	private JPanelDef partieCentrale = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();

	private RadioButtonDefaut ligneNumerotees = new RadioButtonDefaut("Lignes numérotées", true);
	private RadioButtonDefaut legende = new RadioButtonDefaut("Légende",true);
	private RadioButtonDefaut numerotation = new RadioButtonDefaut("Numérotation",true);

	/* tableau contenu de la jcomboBox, 
	 * trié automatiquement par ordre alphabétique (pour éventuelle modif / ajout / suppression) */
	private String[] contenuComboSimple = {"Java", "C", "C++", "Matlab", "HTML", "PHP", "SQL"};
	private String[] contenuComboComplet = {"ABAP","IDL","PL/I","ACSL","inform","Plasm","Ada","Java","POV","Algol","JVMIS","Prolog","Ant",
			"ksh","Promela","Assembler2","Lisp","Python","Awk","Logo","R","bash","make","Reduce","Basic2",
			"Mathematica1","Rexx","C4","Matlab","RSL","C++","Mercury","Ruby","Caml","MetaPost","S","Clean",
			"Miranda","SAS","Cobol","Mizar","Scilab","Comal","ML","sh","csh","Modelica3","SHELXL","Delphi",
			"Modula-2","Simula","Eiffel","MuPAD","SQL","Elan","NASTRAN","tcl","erlang","Oberon-2","TeX","Euphoria",
			"OCL","VBScript","Fortran","Octave","Verilog","GCL","Oz","VHDL","Gnuplot","Pascal","VRML","Haskell",
			"Perl","XML","HTML","PHP","XSLT"};

	private RadioButtonDefaut listeSimple = new RadioButtonDefaut("Liste simple",true);
	private RadioButtonDefaut listeComplete = new RadioButtonDefaut("Liste complète", false);
	private ButtonGroup groupeListe = new ButtonGroup();

	private JComboBox comboBox = new JComboBox();

	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Insérer");

	public MenuBasBlocCode(){

		sortByAlphabeticalOrder(contenuComboComplet);
		sortByAlphabeticalOrder(contenuComboSimple);
		
		for (int i = 0; i < contenuComboSimple.length; i++) {
			comboBox.addItem(contenuComboSimple[i]);
		}
		
		this.setLayout(new BorderLayout(30, 0));
		main.setLayout(new BorderLayout());

		// Traitement partie gauche
		partieGauche.setLayout(new GridLayout(3,1));
		partieGauche.add(ligneNumerotees);
		partieGauche.add(legende);
		partieGauche.add(numerotation);

		main.add(partieGauche, BorderLayout.WEST);

		// Traitement partie centrale
		partieCentrale.setLayout(new GridLayout(5,5));

		comboBox.setFont(Police.segoe);
		comboBox.setBackground(Couleur.white);
		((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < 12; i++) {
			partieCentrale.add(new JPanelDef());
		}
		partieCentrale.add(comboBox);
		for (int i = 0; i < 10; i++) {
			partieCentrale.add(new JPanelDef());
		}

		groupeListe.add(listeComplete);
		groupeListe.add(listeSimple);

		partieCentrale.add(listeSimple);
		partieCentrale.add(listeComplete);

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

		listeSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listeSimple.isSelected()){
					comboBox.removeAllItems();
					for (int i = 0; i < contenuComboSimple.length; i++) {
						comboBox.addItem(contenuComboSimple[i]);
					}
				}
			}
		});

		listeComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listeComplete.isSelected()){
					comboBox.removeAllItems();
					for (int i = 0; i < contenuComboComplet.length; i++) {
						comboBox.addItem(contenuComboComplet[i]);
					}
				}
			}
		});


	}

	private void sortByAlphabeticalOrder(String[] tab){
		int min;
		String temp;
		for(int i = 0; i < tab.length; i++)
		{
			min = i;
			for(int j = i; j < tab.length; j++)
			{				
				if(tab[min].compareToIgnoreCase(tab[j]) > 0)
				{					
					min = j;
				}
			}
			temp = tab[i];
			tab[i] = tab[min];
			tab[min] = temp;
		}
	}

	public void reinstialisation() {
		this.ligneNumerotees.setSelected(true);
		this.legende.setSelected(true);
		this.numerotation.setSelected(true);
		this.comboBox.removeAllItems();
		for (int i = 0; i < this.contenuComboSimple.length; i++) {
			this.comboBox.addItem(contenuComboSimple[i]);
		}
		this.listeSimple.setSelected(true);
	}

}
