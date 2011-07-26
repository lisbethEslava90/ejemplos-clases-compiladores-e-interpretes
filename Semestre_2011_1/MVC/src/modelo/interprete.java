package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class interprete extends Figura
{

    private int ancho;
    public  String de="", para="";
        public interprete(){}
	public interprete(Point posicion, int ancho, String de, String para){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;
                this.de = de;
                this.para = para;
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
		g.setColor(Color.GRAY);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAncho());
                g.setColor(Color.BLACK);
                g.drawString(de, this.getX()+25, this.getY()+25);
                g.drawString(para, this.getX()+25, this.getY()+70);
                
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);  
		}
            }
	}
        public void eliminar(Graphics g)
        {
            g.dispose();
        }

    @Override
    public void pegar(Figura intersectada) {
    }

    @Override
    public void unir(Figura intersectada) {
    }
}
