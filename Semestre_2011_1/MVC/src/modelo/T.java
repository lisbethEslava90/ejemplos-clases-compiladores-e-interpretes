package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import modelo.Modelo;

public class T extends Figura {

    private int ancho;
    public String de = "", para = "", en = "";

    public T() {
    }

    public T(Point posicion, int ancho, String de, String para, String en) {
        this.posicion = new Point(0, 0);
        this.ancho = ancho;
        this.seleccionada = false;
        this.de = de;
        this.para = para;
        this.en = en;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAncho() {
        return ancho;
    }

    public boolean dentroFigura(Point p) {
        int difX = Math.abs(p.x - (posicion.x + (ancho / 2)));
        int difY = Math.abs(p.y - (posicion.y + (ancho / 2)));
        return ((difX < ancho / 2) && (difY < ancho / 2));
    }

    public void dibujar(Graphics g) {
            if(visible){

        g.setColor(Color.BLUE);
        g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAncho() / 4 - 10);
        g.fillRect(this.getX() + 60, this.getY(), this.getAncho() - 120, this.getAncho() - 120);
        g.setColor(Color.BLACK);
        g.drawString(de, this.getX() + 20, this.getY() + 25);
        g.drawString(para, this.getX() + 150, this.getY() + 25);
        g.drawString(en, this.getX() + 85, this.getY() + 70);
        if (this.getSeleccionada()) {
            g.setColor(Color.RED);
            g.drawRect(this.getX() + 7, this.getY() + 7, this.getAncho() - 20, this.getAncho() / 4 - 20);
            g.drawRect(this.getX() + 67, this.getY() + 7, this.getAncho() - 135, this.getAncho() - 135);
        }
            }
    }

    @Override
    public void pegar(Figura intersectada) {
        if (intersectada instanceof interprete
                && this.vec[1] == null
                && intersectada.vec[0] == null
                && this.en.equals(((interprete) intersectada).de)) //maquina
        {
            Point p = new Point(intersectada.posicion);
            p.x -= 60;
            p.y -= 80;
            this.setPosicion(p);
        }
        if (intersectada instanceof maquina
                && this.vec[1] == null
                && intersectada.vec[0] == null
                && this.en.equals(((maquina) intersectada).de)) //maquina
        {
            Point p = new Point(intersectada.posicion);
            p.x -= 60;
            p.y -= 80;
            this.setPosicion(p);
        }
        if (intersectada instanceof programa
                && this.vec[3] == null
                && intersectada.vec[2] == null
                && this.de.equals(((programa) intersectada).para)) //programa
        {
            Point p = new Point(intersectada.posicion);
            p.x += 60;
            p.y += 60;
            this.setPosicion(p);
        }
        if (intersectada instanceof T
                && this.vec[1] == null
                && intersectada.vec[2] == null
                && this.en.equals(((T) intersectada).de))// tombstom izquierda
        {
            Point p = new Point(intersectada.posicion);
            p.x -= 130;
            p.y -= 40;
            this.setPosicion(p);
        } else if (intersectada instanceof T
                && this.vec[1] == null
                && intersectada.vec[3] == null
                && this.en.equals(((T) intersectada).para)) //tombston derecho
        {
            Point p = new Point(intersectada.posicion);
            p.x += 120;
            p.y -= 40;
            this.setPosicion(p);
        } else if (intersectada instanceof programa
                && this.vec[2] == null
                && intersectada.vec[3] == null
                && this.de.equals(((programa) intersectada).para)) //programa
        {
            Point p = new Point(intersectada.posicion);
            p.x -= 190;
            p.y += 60;
            this.setPosicion(p);
        }
    }

    @Override
    public void unir(Figura intersectada) {
        if (intersectada instanceof programa
                && this.vec[3] == null
                && intersectada.vec[2] == null
                && this.de.equals(((programa) intersectada).para)) //programa
        {
            this.vec[3] = intersectada;
            intersectada.vec[2] = this;
        }
        if (intersectada instanceof interprete
                && this.vec[1] == null
                && intersectada.vec[0] == null
                && this.en.equals(((interprete) intersectada).de)) //maquina
        {
            this.vec[1] = intersectada;
            intersectada.vec[0] = this;
        }
        if (intersectada instanceof maquina
                && this.vec[1] == null
                && intersectada.vec[0] == null
                && this.en.equals(((maquina) intersectada).de)) //maquina
        {
            this.vec[1] = intersectada;
            intersectada.vec[0] = this;
        }
        if (intersectada instanceof T
                && this.vec[1] == null
                && intersectada.vec[2] == null
                && this.en.equals(((T) intersectada).de))// tombstom izquierda
        {
            this.vec[1] = intersectada;
            intersectada.vec[2] = this;
        } else if (intersectada instanceof T
                && this.vec[1] == null
                && intersectada.vec[3] == null
                && this.en.equals(((T) intersectada).para)) //tombston derecho
        {
            this.vec[1] = intersectada;
            intersectada.vec[3] = this;
        } else if (intersectada instanceof programa
                && this.vec[2] == null
                && intersectada.vec[3] == null
                && this.de.equals(((programa) intersectada).para)) {
            this.vec[2] = intersectada;
            intersectada.vec[3] = this;
        }
    }
}
