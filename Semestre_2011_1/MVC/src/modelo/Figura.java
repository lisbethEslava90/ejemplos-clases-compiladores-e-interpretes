package modelo;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Figura {

<<<<<<< HEAD
    protected Point posicion;
    protected boolean seleccionada;
    boolean semovio;
=======
<<<<<<< HEAD
    protected Point posicion;
    protected boolean seleccionada;
    boolean semovio;
    public boolean visible;
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e

    public abstract boolean dentroFigura(Point p);

    public abstract void dibujar(Graphics g);
    // 0 = arriba
//    1=abajo
//    2=derecha
//    3=izquierda
<<<<<<< HEAD
    public Figura vec[] = new Figura[4];
=======
    Figura vec[] = new Figura[4];
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e

    public void setPosicion(Point posicion) {
         yasemovieron();
        semovio=true;
        Point desplazamiento=new Point();

        desplazamiento.x = posicion.x-this.posicion.x;
        desplazamiento.y = posicion.y-this.posicion.y;
<<<<<<< HEAD
        for (Figura figura : vec) {
            if (figura != null && figura.semovio==false) {
=======
        System.out.println(desplazamiento);
        for (Figura figura : vec) {
            if (figura != null && figura.semovio==false) {
                System.out.println("entrooo");
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
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
<<<<<<< HEAD
=======
=======
>>>>>>> 4f0f8471e7efb538e56cd702ec37a77097327e55
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
}
