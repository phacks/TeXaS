package briquesElementaires;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class JPanelDef extends JPanel {

	public JPanelDef(){
		super();
		this.setBackground(Couleur.white);
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
	}

	public JPanelDef(boolean arg0) {
		super(arg0);
		this.setBackground(Couleur.white);
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
	}

	public JPanelDef(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		this.setBackground(Couleur.white);
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
	}

	public JPanelDef(LayoutManager arg0) {
		super(arg0);
		this.setBackground(Couleur.white);
		this.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
	}
}
