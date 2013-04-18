import javax.swing.JFrame;
import javax.swing.UIManager;


public class Main {


	public static void main(String[] args) {
	
		 try {
		      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		Fenetre maFen = new Fenetre();

		maFen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}

}
