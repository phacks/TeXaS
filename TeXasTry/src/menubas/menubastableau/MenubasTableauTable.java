package menubas.menubastableau;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


public class MenubasTableauTable extends JTable {

	private int nbLigne;
	private int nbColonne;
	private Object[][] contenu;
	/* tableau de couple d'entier (i,j), 
	i indiquant si la ligne est fusionée (après la celulle) 
	et j si la colonne est fusionnée (après la cellule)*/
	private Boolean[][][] fusion; 
	private String[] title;
	private ModeleTableauCentre modeleTableauCentre;
	private MenuBasTableUI menuBasTableUI;

	public MenubasTableauTable(int nbLigne, int nbColonne) {
		super();
		this.nbLigne = nbLigne;
		this.nbColonne = nbColonne;
		this.contenu = new Object[nbLigne][nbColonne];
		this.fusion = new Boolean[nbLigne][nbColonne][2];
		this.title = new String[nbColonne];
		Boolean[] strucBase = {false,false};
		for (int j = 0; j < nbColonne; j++) {
			title[j] = ""+j;
			for (int i = 0; i < nbLigne; i++) {
				contenu[i][j] = new Boolean(false);
				fusion[i][j] =  strucBase;
			}
		}
		modeleTableauCentre = new ModeleTableauCentre(contenu,title,fusion);
		this.setModel(modeleTableauCentre);
		this.setTableHeader(null);
		menuBasTableUI = new MenuBasTableUI(contenu,fusion);
		this.setUI(menuBasTableUI);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	public void fusionner() {
		// Cette fonction fusionne les cellules du tableau sélectionnée (une seule selection - valide - possible)

		int nbColFus = 0;
		int nbLigFus = 0;
		boolean selectionCorrecte = true;
		boolean selectionExiste = false;

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				if(!selectionExiste){
					selectionExiste = (Boolean)contenu[i][j];
				}
			}
		}

		if(selectionExiste){
			// On cherche la première cellule sélectionnée
			int iHG = 0, jHG =0;
			while(!(Boolean)contenu[iHG][jHG] || (iHG==(nbLigne-1) && jHG==(nbColonne-1))){
				if (jHG==(nbColonne-1)){
					jHG=0;
					iHG++;
				}
				else{
					jHG++;
				}
			}

			// On décompte le nombre de colonne fusionnée
			while((jHG+nbColFus)<nbColonne && (Boolean)contenu[iHG][jHG+nbColFus]){
				nbColFus++;
			}

			// On décompte le nombre de ligne fusionnée
			while((iHG+nbLigFus)<nbLigne && (Boolean)contenu[iHG+nbLigFus][jHG]){
				nbLigFus++;	
			}

			/* On vérifie l'intégrité de la fusion
		On cherche la dernière cellule selectionnée et on vérifie qu'on a bien un "carré" de selection */
			int iBD = nbLigne-1, jBD = nbColonne-1;
			while(!(Boolean)contenu[iBD][jBD] || (iBD==0 && jBD==0)){
				if (jBD==0){
					jBD=nbColonne-1;
					iBD--;
				}
				else{
					jBD--;
				}
			}

			int iBG = nbLigne-1, jBG = 0;
			while(!(Boolean)contenu[iBG][jBG] || (iBG==0 && jBG==nbColonne-1)){
				if (jBG==nbColonne-1){
					jBG=0;
					iBG--;
				}
				else{
					jBG++;
				}
			}

			int iHD = 0, jHD = nbColonne-1;
			while(!(Boolean)contenu[iHD][jHD] || (iHD==nbLigne-1 && jHD==0)){
				if (jHD==0){
					jHD=nbColonne-1;
					iHD++;
				}
				else{
					jHD--;
				}
			}

			if(!((iHG==iHD) && (iBG == iBD) && (jHG == jBG) && (jHD == jBD))){
				selectionCorrecte = false;
			}

			if(selectionCorrecte){
				for (int i2 = iHG; i2 <= iBD; i2++) {
					for (int j2 = jHG; j2 <= jBD; j2++) {
						if(!(Boolean)contenu[i2][j2]){
							selectionCorrecte = false;
						}
					}
				}
			}

			// Lorsque la fusion est correcte, on l'effectue
			if(selectionCorrecte){
				for (int i2 = iHG; i2 < iHG+nbLigFus; i2++) {
					for (int j2 = jHG; j2 < jHG+nbColFus; j2++) {
						boolean b1 = false, b2 = false;
						if(i2<(iHG+nbLigFus-1)){
							b1 = true;
						}
						if(j2<(jHG+nbColFus-1)){
							b2 = true;
						}
						Boolean[] strucBase = {b1,b2};
						this.fusion[i2][j2] = strucBase;
					}
				}
			}


			// Deselection des cases
			for (int i1 = 0; i1 < nbLigne; i1++) {
				for (int j1 = 0; j1 < nbColonne; j1++) {
					this.modeleTableauCentre.setValueAt(false, i1, j1);
				}
			}
			this.modeleTableauCentre.setFusion(this.fusion);
			repaint();
		}
	}





	//Classe modèle personnalisée
	class ModeleTableauCentre extends AbstractTableModel {
		private Object[][] data;
		private Boolean[][][] fusion;
		private String[] title;

		public Boolean[][][] getFusion() {
			return fusion;
		}

		public void setFusion(Boolean[][][] fusion) {
			this.fusion = fusion;
		}

		public ModeleTableauCentre(Object[][] contenu, String[] title, Boolean[][][] fusion) {
			this.data = contenu;
			this.title = title;
			this.fusion = fusion;
		}

		public int getColumnCount() {
			return this.title.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.data.length;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return this.data[arg0][arg1];
		}

		public String getColumnName(int col) {
			return this.title[col];
		}

		public Class getColumnClass(int col){
			return this.data[0][col].getClass();
		}

		public boolean isCellEditable(int row, int col){
			return true;
		}

		public void setValueAt(Object value, int row, int col) {
			this.data[row][col] = value;
			if (this.fusion[row][col][0] && this.data[row+1][col]!=value){
				this.setValueAt(value, row+1, col);
			}
			if((row-1)>=0 && this.fusion[row-1][col][0] && this.data[row-1][col]!=value){
				this.setValueAt(value, row-1, col);
			}
			if (this.fusion[row][col][1] && this.data[row][col+1]!=value){
				this.setValueAt(value, row, col+1);
			}
			if((col-1)>=0 && this.fusion[row][col-1][1] && this.data[row][col-1]!=value){
				this.setValueAt(value, row, col-1);
			}

			repaint();
		}

	}

}
