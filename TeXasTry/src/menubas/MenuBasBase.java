package menubas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MenuBasBase extends JTabbedPane {
	
	public MenuBasBase(){
		super(JTabbedPane.BOTTOM);
		this.setBackground(Color.WHITE);
		MenuBasDesignOngletsUI menuBasDesign = new MenuBasDesignOngletsUI();
		this.setUI(menuBasDesign);
		menuBasDesign.overrideContentBorderInsetsOfUI();
		
	}
	
	
}
