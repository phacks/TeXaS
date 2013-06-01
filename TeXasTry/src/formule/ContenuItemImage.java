package formule;

//import java.awt.Dimension;
//
//import javax.swing.JPanel;
//
//
//public class ContenuItemImage extends JPanel{
//	private int width = 100;
//	private int height = 100;
//	private String text = "";
//
//	public ContenuItemImage(){
//		this.setPreferredSize(new Dimension(this.width, this.height));
//		this.setSize(new Dimension(this.width, this.height));
//	}
//	
//	public void setWidth(int w){
//		this.width = w;
//	}
//	
//	public void setHeight(int h){
//		this.width = h;
//	}
//	
//	public String getText(){
//		return this.text;
//	}
//	
//	public void setText(String s){
//		this.text = s;
//	}
//}


import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.JPanel;

import briquesElementaires.Couleur;
public class ContenuItemImage extends JPanel implements Serializable {
    private Image image = null;
    private String text = "";
    private String type = "image";
    private ItemLayeredPane layeredPane;
    
    public ContenuItemImage(Image image, ItemLayeredPane layeredPane) {
        this.image = image;
        this.layeredPane = layeredPane;
    }
    public ContenuItemImage(ItemLayeredPane layeredPane) {
    	
    	this.layeredPane = layeredPane;
    	
    	this.setBounds(1, 1, this.layeredPane.getPreferredSize().width - 2, this.layeredPane.getPreferredSize().height - 2);

    
    }
    public void setImage(Image image){
        this.image = image;
        
        this.setBounds(1, (this.layeredPane.getPreferredSize().height - 2 - image.getHeight(null)) / 2 + 1, image.getWidth(null) + 10, image.getHeight(null));
        
		this.layeredPane.setWidth(image.getWidth(null) + 12);
		
		this.layeredPane.redefinirApparence();
		((Item) this.layeredPane.getComponentsInLayer(0)[0]).redefinirApparence();
		((BoutonInvisible) this.layeredPane.getComponentsInLayer(1)[0]).redefinirApparence();
    }
    public Image getImage(Image image){
        return image;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint background
        g.setColor(Couleur.white);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
        if (image != null) { //there is a picture: draw it
//            int height = image.getHeight(null);;
//            int width = image.getWidth(null);;
              g.drawImage(image, 5, 0, this); //use image size          
//            g.drawImage(image,0,0, width, height, this);
        }
    }
	public String getText(){
		return this.text;
	}

	public void setText(String s){
		this.text = s;
	}
	
	public String getType(){
		return this.type;
	}
}