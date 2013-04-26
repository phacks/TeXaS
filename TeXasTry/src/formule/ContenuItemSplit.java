package formule;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class ContenuItemSplit extends JPanel{
	
	private BoutonsAction ba;
	private JPanel panelHaut = new JPanel();
	private Formule splitHaut;
	private JPanel panelBas = new JPanel();
	private Formule splitBas;
	private Item itemParent;
	private PanelLigneHorizontale split = new PanelLigneHorizontale();
	private String type = "split";
	private ItemLayeredPane layeredPane;
	
	public ContenuItemSplit(BoutonsAction ba, ItemLayeredPane layeredPane){
		
		this.layeredPane = layeredPane;
		
		this.setBounds(5, 2, this.layeredPane.getPreferredSize().width - 10, this.layeredPane.getPreferredSize().height-3);
		this.ba = ba;
		
	    
//		panelHaut.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, (this.layeredPane.getPreferredSize().height-2) /2 - 2 ));
//	    panelBas.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, (this.layeredPane.getPreferredSize().height-2) /2 - 2 ));
	    split.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, 5));

	    panelHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
	    panelBas.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
	    
	    
		this.splitHaut = new Formule(ba, this.panelHaut,this.layeredPane.getDepth() +1, this.layeredPane);
		
		Formule[] arraySplitHaut = new Formule[1];
		arraySplitHaut[0] = splitHaut;
		
		this.splitBas = new Formule(ba, this.panelBas,this.layeredPane.getDepth() +1, this.layeredPane);
		
		Formule[] arraySplitBas = new Formule[1];
		arraySplitBas[0] = splitBas;
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.panelHaut.setAlignmentX((float) 0.5);
		this.panelBas.setAlignmentX((float) 0.5);

		this.add(panelHaut);
		this.add(split);
		this.add(panelBas);
		
		Formule[] arraySplit = new Formule[2];
		arraySplit[0] = splitHaut;
		arraySplit[1] = splitBas;
		
		AdapteWidthItem adapteWidthItem = new AdapteWidthItem(arraySplit, this.layeredPane);

		
	}
	
	public void redefinirApparence(){
		
		this.setBounds(5, 2, this.layeredPane.getPreferredSize().width - 10, this.layeredPane.getPreferredSize().height-3);
//		panelHaut.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, (this.layeredPane.getPreferredSize().height-2) /2 - 2 ));
//	    panelBas.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, (this.layeredPane.getPreferredSize().height-2) /2 - 2 ));
//	    split.setPreferredSize(new Dimension(this.layeredPane.getPreferredSize().width - 10, 5));

	}
	
	public String getText(){
		return "";
	}
	
	public String getType(){
		return this.type;
	}
	
	public Formule getSplitHaut(){
		return this.splitHaut;
	}
	
	public Formule getSplitBas(){
		return this.splitBas;
	}
	
}
