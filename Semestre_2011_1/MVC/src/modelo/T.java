
package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class T extends Figura
{
    private int ancho;
    private String de="", para="", en="";
	public T(Point posicion, int ancho, String de, String para, String en){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;
                this.de = de;
                this.para = para;
                this.en = en;
	}

	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
	}
	
	public boolean dentroFigura(Point p) {
		int difX=Math.abs(p.x-(posicion.x+(ancho/2)));
		int difY=Math.abs(p.y-(posicion.y+(ancho/2)));
		return ( (difX<ancho/2) && (difY<ancho/2));
	}
	
	public void dibujar(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAncho()/4-10);
                g.fillRect(this.getX()+60, this.getY(), this.getAncho()-120, this.getAncho()-120);
                g.setColor(Color.BLACK);
                g.drawString(de, this.getX()+20, this.getY()+25);
                g.drawString(para, this.getX()+150, this.getY()+25);
                g.drawString(en, this.getX()+85, this.getY()+70);
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()/4-20);
                        g.drawRect(this.getX()+67, this.getY()+7, this.getAncho()-135, this.getAncho()-135);
		}
	}

}
