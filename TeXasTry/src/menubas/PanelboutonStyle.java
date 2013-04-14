package menubas;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import briquesElementaires.Couleur;
import briquesElementaires.JPanelDef;

public class PanelboutonStyle extends JPanelDef {

	public PanelboutonStyle() {
		BoutonMenuBasValidate falseboute = new BoutonMenuBasValidate("Réinistialiser");
		falseboute.setEnabled(false);
		falseboute.setVisible(false);
		this.setLayout(new GridLayout(1,1));
		this.add(falseboute);
	}

	public PanelboutonStyle(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelboutonStyle(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public PanelboutonStyle(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Couleur.grisFonce);
		g.drawLine(0, 0, 0, this.getHeight());
	}

}
