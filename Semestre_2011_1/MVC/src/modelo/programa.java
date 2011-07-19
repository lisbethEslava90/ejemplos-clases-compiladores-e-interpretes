package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class programa extends Figura {

	private int radio;
        private boolean circulo=false;
        private String de = " ", para = " ";

	public void setRadio(int ancho){
		this.radio=ancho;
	}

	public int getRadio(){
		return radio;
	}

	public programa(Point posicion, int radio, String de, String para){
		this.posicion=posicion;
		this.radio=radio;
		this.seleccionada=false;  //Deberia estar en el constructor de figura pero por simplicidad
                this.de = de;
                this.para = para;
	}

	@Override
	public boolean dentroFigura(Point p) {
		if ( radio >= Math.sqrt( Math.pow( p.x - posicion.x, 2 ) + Math.pow(p.y - posicion.y, 2 )))
                    circulo = true;
                else
                    circulo = false;

                return (circulo == true);
	}

	@Override
	public void dibujar(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(this.getX(), this.getY(), this.getRadio(), this.getRadio());
                g.fillRect(this.getX()+10, this.getY()+50, 50, 50);
                g.setColor(Color.black);
                g.drawString(this.de, this.getX()+25, this.getY()+40);
                g.drawString(this.para, this.getX()+25, this.getY()+90);
		if(this.getSeleccionada()){
			g.setColor(Color.ORANGE);
			g.drawOval(this.getX()+7, this.getY()+7, this.getRadio()-15, this.getRadio()-15);
                        g.drawRect(this.getX()+17, this.getY()+57, 40, 40);
		}
	}
}