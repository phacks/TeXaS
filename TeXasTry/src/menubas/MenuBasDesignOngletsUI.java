package menubas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

import briquesElementaires.Couleur;
import briquesElementaires.Police;




public class MenuBasDesignOngletsUI extends MetalTabbedPaneUI {

	private int maxWidth = 0;
	private int maxHeight = 0;
	
	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	Font segoe = new Font("Segoe UI Light", Font.PLAIN, 15);
	Font segoeBold = new Font("Segoe UI Light", Font.BOLD,15);


	public MenuBasDesignOngletsUI(){
		super();
	}



	protected int calculateTabWidth(int tabPlacement, int tabIndex,FontMetrics metrics) {
		maxWidth = Math.max(maxWidth, super.calculateTabWidth(tabPlacement, tabIndex, metrics));
		return maxWidth+20;
	}

	protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
	{
		int vHeight = fontHeight;
		if (vHeight % 2 > 0)
		{
			vHeight += 1;
		}
		maxHeight = vHeight;
		return vHeight+10;
	}

	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{

//		Graphics2D surface = (Graphics2D) g;
//		surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//		surface.setColor(Couleur.bleuMedianMenuGauche);
//		Area bordure = new Area();
//		bordure.add(new Area(new Rectangle2D.Double(x,y,w,h)));
//		surface.draw(bordure);	
//		surface.setColor(Color.WHITE);

	}

	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
		Graphics2D surface = (Graphics2D) g;
		surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Area bordure = new Area();
		bordure.add(new Area(new Rectangle2D.Double(x,y,w,h)));
		if (!isSelected){
			GradientPaint gp = new GradientPaint(x, y+3, Couleur.grisClair, x, y, Couleur.grisFonce);
			surface.setPaint(gp);
			surface.fill(bordure);
		}
		else{
			surface.setColor(Couleur.bleuClairMenuGauche);
			surface.fill(bordure);	
		}
		surface.fill(bordure);
	}

	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
	{
		// Do nothing
	}


	protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
		Graphics2D shape = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, tabPane.getBounds().height-this.maxTabHeight+1, Couleur.grisClair, 0, tabPane.getBounds().height-this.maxTabHeight-2, Couleur.grisFonce);
		shape.setPaint(gp);
		shape.fillRect(0, 0, tabPane.getBounds().width, tabPane.getBounds().height);
		
		super.paintTabArea(g, tabPlacement, selectedIndex);
		
	}

	public void overrideContentBorderInsetsOfUI(){
		this.contentBorderInsets.top = 0;
		this.contentBorderInsets.left = 0;
		this.contentBorderInsets.right = 0;
		this.contentBorderInsets.bottom = 0;    
		highlight = Color.LIGHT_GRAY;
		lightHighlight = Color.LIGHT_GRAY;
		shadow = Color.LIGHT_GRAY;
		darkShadow = Color.LIGHT_GRAY;
		focus = Color.LIGHT_GRAY;
	}




	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,int selectedIndex, int x, int y, int w, int h) {
		
	}



	protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected){
		 if (isSelected) {
	            g.setColor(Color.WHITE);
	        } else {
	            g.setColor(Color.BLACK);
	        }
	         
	        Font tabFont = Police.segoe;
	             
	        g.setFont(tabFont);
	        g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
	    }



	

}
//
//protected int calculateTabWidth(int tabPlacement, int tabIndex,FontMetrics metrics) {
//	maxWidth = Math.max(maxWidth, super.calculateTabWidth(tabPlacement, tabIndex, metrics));
//	return maxWidth+20;
//}
//
//protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
//{
//	int vHeight = fontHeight;
//	if (vHeight % 2 > 0)
//	{
//		vHeight += 1;
//	}
//	return vHeight+10;
//}
//
//protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
//{
//	Graphics2D surface = (Graphics2D) g;
//	surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//	surface.setColor(Color.WHITE.darker().darker());
//	Area bordure = new Area();
//	bordure.add(new Area(new Rectangle2D.Double(x-h/2,y,w+h,h/2)));
//	bordure.subtract(new Area(new RoundRectangle2D.Double(x-h,y,h,h,h,h)));
//	bordure.subtract(new Area(new RoundRectangle2D.Double(x+w, y, h, h,h,h)));
//	bordure.add(new Area(new RoundRectangle2D.Double(x, y-h, w, 2*h, h, h)));
//	surface.draw(bordure);	
//	surface.setColor(Color.WHITE);
//	surface.drawLine(x-h/2+4, y, x+w+h/2-4, y);
//
//}
//
//protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
//{
//	Graphics2D surface = (Graphics2D) g;
//	surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//	Area bordure = new Area();
//	bordure.add(new Area(new Rectangle2D.Double(x-h/2,y-h,w+h,h+h/2)));
//	bordure.subtract(new Area(new RoundRectangle2D.Double(x-h,y,h,h,h,h)));
//	bordure.subtract(new Area(new RoundRectangle2D.Double(x+w, y, h, h,h,h)));
//	bordure.add(new Area(new RoundRectangle2D.Double(x, y-h, w, 2*h, h, h)));
//
//	if (isSelected){
//		surface.setColor(Couleur.white);
//
//	}
//	else{
//		surface.setColor(Couleur.lightgray);
//		surface.fill(bordure);	
//	}
//	surface.fill(bordure);
//}
//
//protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
//{
//	// Do nothing
//}
//
//
//protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
//	super.paintTabArea(g, tabPlacement, selectedIndex);
//	int tw = tabPane.getBounds().width;
//	g.setColor(Color.BLACK);
//	g.fillRect(0, 0, tw, rects[0].height + 3);
//
//}
//
//public void overrideContentBorderInsetsOfUI(){
//	this.contentBorderInsets.top = 0;
//	this.contentBorderInsets.left = 0;
//	this.contentBorderInsets.right = 0;
//	this.contentBorderInsets.bottom = 0;    
//	highlight = Color.LIGHT_GRAY;
//	lightHighlight = Color.LIGHT_GRAY;
//	shadow = Color.LIGHT_GRAY;
//	darkShadow = Color.LIGHT_GRAY;
//	focus = Color.LIGHT_GRAY;
//}
//
//
//
//
//protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,int selectedIndex, int x, int y, int w, int h) {
//	Graphics2D surface = (Graphics2D) g;
//	surface.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//	Rectangle selectedRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, calcRect);
//	int x1 = selectedRect.x - (selectedRect.height / 2) + 7 ;
//	int wRect = selectedRect.width + selectedRect.height; 
//	int x2 = x1 + wRect - 14;
//
//	g.setColor(Color.LIGHT_GRAY);
//
//	g.fillRect(x, y+h, x1-x, 1);
//	g.fillRect(x2, y+h, x+w-x2, 1);
//
//	g.setColor(Color.WHITE);
//
//	g.fillRect(x1-1, y+h, x2-x1+4, 1);
//}
//
//
//
//protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected){
//	super.paintText(g, tabPlacement, segoe, metrics, tabIndex, title, textRect, isSelected);
//}
//
//}

