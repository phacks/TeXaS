package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import briquesElementaires.JPanelDef;


/** Classe de gestion de l'écran d'édition central du logiciel
 * @author Kilian
 *
 */
public class EcranEditionCentral extends JPanelDef {

	/** Le contenu de l'écran
	 * 
	 */
	private static ContenuEditable contenuEditable = new ContenuEditable(); 

	/** Le panel d'affichage
	 * 
	 */
	private JPanelDef center = new JPanelDef();

	/** Le panel scrollable contenant l'affichage
	 * 
	 */
	private static JScrollPane scrollPane = new JScrollPane(contenuEditable);
	
	
	// >>>>>>>>>>> Getters and setters
	/** Renvoie la liste des conteneurs affichés dans l'écran central
	 * @return contenuEditable
	 * 			La liste des contenus editable
	 */
	public static ContenuEditable getContenuEditable() {
		return contenuEditable;
	}

	/** Edite la liste des contenus affichés dans l'écran central
	 * @param contenuEditable
	 * 			La liste des contenus editable
	 */
	public static void setContenuEditable(ContenuEditable contenuEditable) {
		EcranEditionCentral.contenuEditable = contenuEditable;
	}
	
	// >>>>>>>>>>> Fin getters and setters
	
	public EcranEditionCentral(){
		super(new BorderLayout());
		
		center.setLayout(new BorderLayout());
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		center.add(new PannelBordure(),BorderLayout.EAST);
		center.add(new JPanelDef(), BorderLayout.NORTH);
		center.add(new JPanelDef(), BorderLayout.SOUTH);
		center.add(new PannelBordure(), BorderLayout.WEST);
		center.add(scrollPane,BorderLayout.CENTER);
		
		this.add(center, BorderLayout.CENTER);
		this.add(scrollPane.getVerticalScrollBar(), BorderLayout.EAST);
		this.revalidate();
	}

}
