import javax.swing.JFrame;
import javax.swing.UIManager;


public class Main {


	public static void main(String[] args) {
	
		 try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		Fenetre maFen = new Fenetre();

		maFen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}

}
