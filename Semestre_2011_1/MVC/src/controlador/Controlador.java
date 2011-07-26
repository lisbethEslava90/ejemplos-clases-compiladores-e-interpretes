package controlador;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import javax.swing.*;

import vista.Vista;
import modelo.Figura;
import modelo.Modelo;
import modelo.T;
import modelo.programa;
import modelo.maquina;
import modelo.interprete;

public class Controlador {

    private Modelo modelo;
    private Vista vista;
    private Figura seleccionada, dere, izq, arriba, abajo;
    private Figura intersectada;
    Point punto;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        seleccionada = null;
    }

    public Figura obtenerFigura(Point posicion) {
        ListIterator<Figura> it = modelo.getListado().listIterator();
        while (it.hasNext()) {
            Figura tmp = it.next();
            if (tmp.dentroFigura(posicion)) {
                tmp.setSeleccionada(true);
                return tmp;
            }
        }
        return null;
    }

    private Figura getFiguraEn2(Point posicion, Figura yo) {
        ListIterator<Figura> it = modelo.getListado().listIterator();
        while (it.hasNext()) {
            Figura tmp = it.next();
            if (!tmp.equals(yo) && tmp.dentroFigura(posicion)) {
                tmp.setSeleccionada(true);
                return tmp;
            }
        }
        return null;
    }

//	public void cambiarPosicion(Figura f, Point p){
//		f.setPosicion(p);
//	}
    public void desplazar(Figura f, Point p) {
        Point desplazamiento = new Point();
        desplazamiento.x = p.x - punto.x;
        desplazamiento.y = p.y - punto.y;
        punto = new Point();
        punto.x = p.x;
        punto.y = p.y;
        f.desplazar(desplazamiento);
        f.yasemovieron();
    }

    public Vista getVista() {
        return vista;
    }

    public void anyadirFigura(Figura f) {
        modelo.anyadirFigura(f);
    }

    public void eliminarFigura(Figura f) {
        modelo.eliminarFigura(f);
    }

    public Figura getFiguraEn(Point p) {
        return modelo.getFiguraEn(p);
    }

    public void eVmousePressed(MouseEvent ev) {
        if (SwingUtilities.isLeftMouseButton(ev)) { 			//Click boton izquierdo selecciona figura
            seleccionada = this.getFiguraEn(ev.getPoint());
        } else if (SwingUtilities.isRightMouseButton(ev)) {		//click boton derecho elimina la figura seleccionada
            seleccionada = this.getFiguraEn(ev.getPoint());
            this.eliminarFigura(seleccionada);
        } else if (SwingUtilities.isMiddleMouseButton(ev))//click boton medio a�ade figura tipo circulo
        {
            seleccionada = this.getFiguraEn(ev.getPoint());
            arriba = seleccionada.vec[0];
            abajo = seleccionada.vec[1];
            izq = seleccionada.vec[2];
            dere = seleccionada.vec[3];
            for (int i = 0; i < 4; i++) {
                seleccionada.vec[i] = null;
            }
//            System.out.println("dere "+dere);
//            System.out.println("izq "+izq);
//            System.out.println("arriba "+arriba);
//            System.out.println("abajo "+abajo);
            if (seleccionada instanceof T) {
                if (dere instanceof programa && dere.vec[2] != null) {
                    dere.vec[2] = null;
                }
                if (izq instanceof programa && izq.vec[3] != null) {
                    izq.vec[3] = null;
                }
                if (dere instanceof T && dere.vec[1] != null) {
                    dere.vec[1] = null;
                }
                if (izq instanceof T && izq.vec[1] != null) {
                    izq.vec[1] = null;
                }
                if (abajo instanceof T && abajo.vec[2] != null) {
                    abajo.vec[2] = null;
                }
                if (abajo instanceof T && abajo.vec[3] != null) {
                    abajo.vec[3] = null;
                }
                if (abajo instanceof maquina && abajo.vec[0] != null) {
                    abajo.vec[0] = null;
                }
                if (arriba instanceof T && arriba.vec[0] != null) {
                    arriba.vec[0] = null;
                }
                if (abajo instanceof interprete && abajo.vec[0] != null) {
                    abajo.vec[0] = null;
                }
            }
            if (seleccionada instanceof programa) {
                if (dere instanceof T && dere.vec[2] != null) {
                    dere.vec[2] = null;
                }
                if (izq instanceof T && izq.vec[3] != null) {
                    izq.vec[3] = null;
                }
                if (abajo instanceof maquina && abajo.vec[0] != null) {
                    abajo.vec[0] = null;
                }
            }
            if (seleccionada instanceof interprete) {
                if (abajo instanceof maquina && abajo.vec[0] != null) {
                    abajo.vec[0] = null;
                }
                if (arriba instanceof T && arriba.vec[1] != null) {
                    arriba.vec[1] = null;
                }
            }
            if (seleccionada instanceof maquina) {
                if (arriba instanceof T && arriba.vec[1] != null) {
                    arriba.vec[1] = null;
                }
                 if (arriba instanceof programa && arriba.vec[1] != null) {
                    arriba.vec[1] = null;
                }
            }
        }
        punto = ev.getPoint();
        vista.repaint();
    }

    public void eVmouseDragged(MouseEvent ev) {
        if (seleccionada != null) {
            //El movimiento puede ser mas fluido recalculando el pto
            //this.cambiarPosicion(seleccionada, ev.getPoint());
            this.desplazar(seleccionada, ev.getPoint());

            vista.repaint();
        }
        if (seleccionada != null) {
            intersectada = this.getFiguraEn2(ev.getPoint(), seleccionada);
            if (intersectada != null) {
                seleccionada.pegar(intersectada);
                vista.repaint();
            }

        }
    }

    public void eVmouseReleased(MouseEvent ev) {
        vista.repaint();
        if (intersectada != null && seleccionada != null) {
            seleccionada.unir(intersectada);
        }
        if (seleccionada != null) {
            seleccionada.setSeleccionada(false);
            seleccionada = null;
        }
<<<<<<< HEAD

=======
    }

    public void guardar() {
        //System.out.println("si guarda");
        ObjectContainer base = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
        try {
            modelo.BaseD = JOptionPane.showInputDialog(vista, "Ingrese Nombre del Modelo:");
            base.store(modelo);
<<<<<<< HEAD
            modelo.limpiar(modelo);
            vista.repaint();
=======
>>>>>>> 4f0f8471e7efb538e56cd702ec37a77097327e55
        } finally {
            base.close();
        }
    }

    public void cargar() {
        // System.out.println("si carga");
        ObjectContainer  base1= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
        try {

<<<<<<< HEAD
            ObjectSet<Modelo> lista = base1.query(Modelo.class);            
=======
            ObjectSet<Modelo> lista = base1.query(Modelo.class);
            for (Modelo m : lista) {
                System.out.println("MODELO " + m);
            }
>>>>>>> 4f0f8471e7efb538e56cd702ec37a77097327e55
            Modelo dibu = (Modelo) JOptionPane.showInputDialog(vista, "Mensaje", "Titulo", JOptionPane.INFORMATION_MESSAGE, null, lista.toArray(), lista.get(0));
            if (dibu != null) {
                modelo.extraer(dibu);
            }
            vista.repaint();
        } finally {
            base1.close();
        }
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
    }
}
