package menubas;

import javax.swing.JTabbedPane;

import briquesElementaires.JPanelDef;

public class MenuBasBlocs extends JTabbedPane {
	
	public MenuBasBlocs(){
		super(JTabbedPane.LEFT);
		
		this.add("Code", new JPanelDef());
		this.add("Citation", new JPanelDef());
		this.add("Math�matiques", new JPanelDef());
		this.add("Note de bas de page", new JPanelDef());
	}
}
