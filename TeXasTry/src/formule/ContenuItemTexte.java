package formule;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class ContenuItemTexte extends JTextField{
	
	private ItemLayeredPane layeredPane;
	private String type = "texte";
	private Document document;
	private int nombreAgrandissements = 0;
	private Font font = UIManager.getDefaults().getFont("TextField.font");
	@SuppressWarnings("deprecation")
	private FontMetrics fm;
	private int nbLettres = 0;
	
	int largeurTexte = 0;

	public ContenuItemTexte(final ItemLayeredPane layeredPane){
		
		this.setHorizontalAlignment(JTextField.CENTER);
		
		fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
		
		this.layeredPane = layeredPane;
		this.document = this.getDocument();
		this.setBounds(1, 1, this.layeredPane.getWidth() - 2, this.layeredPane.getHeight() - 2);
		this.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				nbLettres = document.getLength();
				
				try {
					largeurTexte = fm.stringWidth(document.getText(0, nbLettres));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//if (largeurTexte > layeredPane.getPreferredSize().width - 20){
					agrandirTextField();
				//}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	protected void agrandirTextField() {
				
		//layeredPane.setWidth(layeredPane.getPreferredSize().width + 30);
		layeredPane.setWidth(largeurTexte + 30);
		
		
		this.setBounds(1, 1, largeurTexte + 28, this.layeredPane.getHeight() - 2);
		
		layeredPane.redefinirApparence();
		((Item) this.layeredPane.getComponentsInLayer(0)[0]).redefinirApparence();
		((BoutonInvisible) this.layeredPane.getComponentsInLayer(1)[0]).redefinirApparence();
		layeredPane.getFormuleMere().repaintRevalidate();
		
	}

	public String getContent(){
		return this.getText();
	}
	
	public String getType(){
		return this.type;
	}
	
	
}
