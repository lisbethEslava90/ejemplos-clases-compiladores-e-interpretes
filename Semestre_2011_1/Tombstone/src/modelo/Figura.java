package modelo;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Figura {
	protected Point posicion;
	protected boolean seleccionada;
        public boolean visible;
	public abstract boolean dentroFigura(Point p);
	public abstract void dibujar(Graphics g);
	
	public void setPosicion(Point posicion)
	{
		this.posicion=posicion;
	}
	
	public int getX(){
		return posicion.x;
	}
	
	public int getY(){
		return posicion.y;
	}

	Point getPosicion(){
		return posicion;
	}
	
	public boolean getSeleccionada(){
		return seleccionada;
	}

	public void setSeleccionada(boolean sel){
		seleccionada=sel;
	}

<<<<<<< HEAD
    protected Point posicion;
    protected boolean seleccionada;
    boolean semovio;
    public boolean visible;

    public abstract boolean dentroFigura(Point p);

    public abstract void dibujar(Graphics g);
    // 0 = arriba
//    1=abajo
//    2=derecha
//    3=izquierda
    Figura vec[] = new Figura[4];

    public void setPosicion(Point posicion) {
         yasemovieron();
        semovio=true;
        Point desplazamiento=new Point();

        desplazamiento.x = posicion.x-this.posicion.x;
        desplazamiento.y = posicion.y-this.posicion.y;
        System.out.println(desplazamiento);
        for (Figura figura : vec) {
            if (figura != null && figura.semovio==false) {
                System.out.println("entrooo");
                figura.desplazar(desplazamiento);

            }
        }
        this.posicion = posicion;
        yasemovieron();
    }

    public void desplazar(Point desplazamiento) {
        semovio=true;
        this.posicion.x += desplazamiento.x;
        this.posicion.y += desplazamiento.y;
        for (Figura figura : vec) {
            if (figura != null && figura.semovio==false) {
                figura.desplazar(desplazamiento);

            }
        }
    }
    public void yasemovieron(){
    semovio=false;
    for (Figura figura : vec) {
            if (figura != null && figura.semovio==true) {
                figura.yasemovieron();

            }
        }
    }


    public int getX() {
        return posicion.x;
    }

    public int getY() {
        return posicion.y;
    }

    Point getPosicion() {
        return posicion;
    }

    public boolean getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean sel) {
        seleccionada = sel;
    }

    public abstract void pegar(Figura intersectada);

    public abstract void unir(Figura intersectada);
=======
>>>>>>> 4f0f8471e7efb538e56cd702ec37a77097327e55
}
