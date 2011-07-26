package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class maquina extends Figura
{

    private int ancho;
    private int[] xs, ys;
    private String de="", para="";
    private boolean band=false;
	public maquina(Point posicion, int ancho, String de, String para, boolean band){
		this.posicion=posicion;
		this.ancho=ancho;
                this.para = para;
		this.seleccionada=false;
                this.band = band;
                this.de = de;
                this.xs = new int [5];
                this.ys = new int [5];
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
            if(visible)
            {
                this.xs[0] = posicion.x;
                this.xs[1] = posicion.x;
                this.xs[2] = posicion.x + (this.ancho/2);
                this.xs[3] = posicion.x + this.ancho;
                this.xs[4] = posicion.x + this.ancho;
                this.ys[0] = posicion.y;
                this.ys[1] = posicion.y + 30;
                this.ys[2] = posicion.y + 50;
                this.ys[3] = posicion.y + 30;
                this.ys[4] = posicion.y;              
                
                if(this.band == false)
                {
                    g.setColor(Color.MAGENTA);
                    g.fillPolygon(this.xs, this.ys, 5);
                    g.setColor(Color.BLACK);
                    g.drawString(de, this.getX()+25, this.getY()+20);
                }
                else if(this.band == true)
                {
                    g.setColor(Color.GREEN);
                    g.fillPolygon(this.xs, this.ys, 5);
                    g.setColor(Color.BLACK);
                    g.drawString(de, this.getX()+25, this.getY()+15);
                    g.drawString(para, this.getX()+25, this.getY()+35);
                }
		/*if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);  
		}*/
            }
	}
}

