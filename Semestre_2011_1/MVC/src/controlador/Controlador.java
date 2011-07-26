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
    private Figura seleccionada;
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
            intersectada = this.getFiguraEn2(ev.getPoint(),seleccionada);
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
        ObjectContainer  base1= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
        try {

            ObjectSet<Modelo> lista = base1.query(Modelo.class);            
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
