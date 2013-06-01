package EcranEditionCentral;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import formule.Formule;

public class EditeurFormule extends Editeur implements KeyListener {

	private boolean selected;
	private Formule formule;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public EditeurFormule() {
		super(new BorderLayout());
		
		
		
		this.formule = new Formule((EditeurFormule) this, this, 1, null);
		
		this.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Caca");
		if(e.getKeyCode() == 0){
			
			ContenuEditable.addEditeurParagraphe(this);
			e.consume();
		}
	}
	
	public Formule getFormule(){
		return this.formule;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void deleteFormule() {
		// TODO Auto-generated method stub
		ContenuEditable.detruire(this);
	}

	public void addEditeurParagraphe() {
		// TODO Auto-generated method stub
		ContenuEditable.addEditeurParagraphe(this);
	}
	
	
	
}
