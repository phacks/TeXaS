package menubas.menubasBlocs;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import menubas.BoutonMenuBasValidate;
import menubas.PanelboutonStyle;
import menubas.RadioButtonMenuBas;
import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;
import briquesElementaires.Police;

public class MenuBasBlocCode extends JPanelDef {

	private JPanelDef main = new JPanelDef();
	private JPanelDef main1 = new JPanelDef();
	private JPanelDef partieGauche = new JPanelDef();
	private JPanelDef partieCentrale = new JPanelDef();
	private JPanelDef partieEast = new JPanelDef();
	
	private RadioButtonMenuBas ligneNumerotees = new RadioButtonMenuBas("Lignes numérotées", true);
	private RadioButtonMenuBas legende = new RadioButtonMenuBas("Légende",true);
	private RadioButtonMenuBas numerotation = new RadioButtonMenuBas("Numérotation",true);

//	private String[] contenuCombo = {"Java", "C", "C++", "Matlab"};
	private String[] contenuCombo = {"ABAP","IDL","PL/I","ACSL","inform","Plasm","Ada","Java","POV","Algol","JVMIS","Prolog","Ant",
			"ksh","Promela","Assembler2","Lisp","Python","Awk","Logo","R","bash","make","Reduce","Basic2",
			"Mathematica1","Rexx","C4","Matlab","RSL","C++","Mercury","Ruby","Caml","MetaPost","S","Clean",
			"Miranda","SAS","Cobol","Mizar","Scilab","Comal","ML","sh","csh","Modelica3","SHELXL","Delphi",
			"Modula-2","Simula","Eiffel","MuPAD","SQL","Elan","NASTRAN","tcl","erlang","Oberon-2","TeX","Euphoria",
			"OCL","VBScript","Fortran","Octave","Verilog","GCL","Oz","VHDL","Gnuplot","Pascal","VRML","Haskell",
			"Perl","XML","HTML","PHP","XSLT"};
	
	private JComboBox comboBox = new JComboBox(contenuCombo);

	private BoutonMenuBasValidate boutonInserer = new BoutonMenuBasValidate("Insérer");

	public MenuBasBlocCode(){
		
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
		for (int i = 0; i < 12; i++) {
			partieCentrale.add(new JPanelDef());
		}
		
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
}
