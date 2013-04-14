package menubas.menubasBlocs;

import javax.swing.JTabbedPane;

import menubas.MenuBasDesignOngletsUI;

import briquesElementaires.JPanelDef;

public class MenuBasBlocs extends JTabbedPane {
	
	public MenuBasBlocs(){
		super(JTabbedPane.LEFT);
		this.setUI(new MenuBlocsDesignOngletsUI());
		this.add("Code", new JPanelDef());
		this.add("Citation", new JPanelDef());
		this.add("Mathématiques", new JPanelDef());
		this.add("Note de bas de page", new JPanelDef());
	}
}
