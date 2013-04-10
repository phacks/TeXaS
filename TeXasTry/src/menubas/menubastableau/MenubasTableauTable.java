package menubas.menubastableau;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class MenubasTableauTable extends JTable {

	Object[][] contenu;
	String[] title;

	public MenubasTableauTable(int nbLigne, int nbColonne) {
		super();
		contenu = new Object[nbLigne][nbColonne];
		title = new String[nbColonne];
		for (int j = 0; j < nbColonne; j++) {
			title[j] = ""+j;
			for (int i = 0; i < nbLigne; i++) {
				contenu[i][j] = new Boolean(false);
			}
		}
		ModeleTableauCentre modeleTableauCentre = new ModeleTableauCentre(contenu,title);
		this.setModel(modeleTableauCentre);
		this.setTableHeader(null);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}


	//Classe modèle personnalisée
	class ModeleTableauCentre extends AbstractTableModel{
		private Object[][] data;
		private String[] title;

		public ModeleTableauCentre(Object[][] contenu, String[] title) {
			this.data = contenu;
			this.title = title;
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
		}

	}
	
	

}
