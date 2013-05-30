package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import briquesElementaires.JPanelDef;

public class EcranEditionCentral extends JPanelDef {

	private static ContenuEditable contenuEditable = new ContenuEditable(); 

	public static ContenuEditable getContenuEditable() {
		return contenuEditable;
	}

	public static void setContenuEditable(ContenuEditable contenuEditable) {
		EcranEditionCentral.contenuEditable = contenuEditable;
	}
	
	private JPanelDef center = new JPanelDef();

	public EcranEditionCentral(){
		super(new BorderLayout());
		
		center.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(contenuEditable);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		center.add(new JPanelDef(),BorderLayout.EAST);
		center.add(new JPanelDef(), BorderLayout.NORTH);
		center.add(new JPanelDef(), BorderLayout.SOUTH);
		center.add(new JPanelDef(), BorderLayout.WEST);
		center.add(scrollPane,BorderLayout.CENTER);
		
		this.add(center, BorderLayout.CENTER);
		this.add(scrollPane.getVerticalScrollBar(), BorderLayout.EAST);
		this.revalidate();
	}

}
