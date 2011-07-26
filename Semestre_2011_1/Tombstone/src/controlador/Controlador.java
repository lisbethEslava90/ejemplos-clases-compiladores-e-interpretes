package controlador;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import javax.swing.*;

import vista.Vista;
import modelo.Figura;
import modelo.Modelo;

public class Controlador {

    private Modelo modelo;
    private Vista vista;
<<<<<<< HEAD
    private Figura seleccionada, dere, izq, arriba, abajo, agregada;
    private Figura intersectada;
    Point punto;
    public boolean band = false;
=======
    private Figura seleccionada, agregada;
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e

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

    public void cambiarPosicion(Figura f, Point p) {
        f.setPosicion(p);
    }

    public Vista getVista() {
        return vista;
    }

    public void anyadirFigura(Figura f) {
        modelo.anyadirFigura(f);
        agregada = f;
    }

    public void eliminarFigura(Figura f) {
        modelo.eliminarFigura(f);
    }

    public Figura getFiguraEn(Point p) {
        return modelo.getFiguraEn(p);
    }

    public void eVmouseClicked(MouseEvent ev) {
        if (agregada != null) {
            agregada.setPosicion(ev.getPoint());
            agregada.visible = true;
            agregada = null;
        }
        vista.repaint();
    }

    public void eVmousePressed(MouseEvent ev) {
        if (SwingUtilities.isLeftMouseButton(ev)) { 			//Click boton izquierdo selecciona figura
            seleccionada = this.getFiguraEn(ev.getPoint());
        } else if (SwingUtilities.isRightMouseButton(ev)) {		//click boton derecho elimina la figura seleccionada
            seleccionada = this.getFiguraEn(ev.getPoint());
            this.eliminarFigura(seleccionada);
        } else if (SwingUtilities.isMiddleMouseButton(ev))//click boton medio a�ade figura tipo circulo
        {
<<<<<<< HEAD
            seleccionada = this.getFiguraEn(ev.getPoint());
            arriba = seleccionada.vec[0];
            abajo = seleccionada.vec[1];
            izq = seleccionada.vec[2];
            dere = seleccionada.vec[3];
            for (int i = 0; i < 4; i++) {
                seleccionada.vec[i] = null;
            }
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
                if (abajo instanceof T) {
                    System.out.println("derecha" + abajo.vec[3]);
                    System.out.println("izquierda" + abajo.vec[2]);
                    if (abajo.vec[2] != null && abajo.vec[2] == seleccionada) {
                        abajo.vec[2] = null;
                    }
                    else if(abajo.vec[3] != null && abajo.vec[3] == seleccionada) {
                        abajo.vec[3] = null;
                    }
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
=======
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
        }
        vista.repaint();
    }

    public void eVmouseDragged(MouseEvent ev) {
        if (seleccionada != null) {
            //El movimiento puede ser mas fluido recalculando el pto
            this.cambiarPosicion(seleccionada, ev.getPoint());
            vista.repaint();
        }
    }

    public void eVmouseReleased(MouseEvent ev) {
        vista.repaint();
        if (seleccionada != null) {
            seleccionada.setSeleccionada(false);
            seleccionada = null;
        }
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
    }

    public void guardar() {
        //System.out.println("si guarda");
        ObjectContainer base = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
        try {
            modelo.BaseD = JOptionPane.showInputDialog(vista, "Ingrese Nombre del Modelo:");
            base.store(modelo);

            modelo.limpiar(modelo);
            vista.repaint();

        } finally {
            base.close();
        }
    }

    public void cargar() {
        // System.out.println("si carga");
        ObjectContainer base1 = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
        try {


            ObjectSet<Modelo> lista = base1.query(Modelo.class);
            for (Modelo m : lista) {
                System.out.println("MODELO " + m);
            }

            Modelo dibu = (Modelo) JOptionPane.showInputDialog(vista, "Mensaje", "Titulo", JOptionPane.INFORMATION_MESSAGE, null, lista.toArray(), lista.get(0));
            if (dibu != null) {
                modelo.extraer(dibu);
            }
            vista.repaint();
        } finally {
            base1.close();
        }
    }
}
