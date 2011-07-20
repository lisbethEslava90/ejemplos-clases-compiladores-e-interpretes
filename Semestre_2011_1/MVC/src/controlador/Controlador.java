package controlador;

<<<<<<< HEAD
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
=======
>>>>>>> 5f1365bd2f6537184919635dfeea5f3ac9635b3c

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import javax.swing.*;

import vista.Vista;
import modelo.Figura;
import modelo.Modelo;

public class Controlador {
<<<<<<< HEAD

    private Modelo modelo;
    private Vista vista;
    private Figura seleccionada, agregada;

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
        } finally {
            base.close();
        }
    }

    public void cargar() {
        // System.out.println("si carga");
        ObjectContainer  base1= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "BaseDatos");
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
=======
	
	private Modelo modelo;
	private Vista vista;
	private Figura seleccionada, agregada;
        
	
	public Controlador(Modelo modelo, Vista vista){
		this.modelo=modelo;
		this.vista=vista;
		seleccionada=null;
        }
	
	public Figura obtenerFigura(Point posicion){
		ListIterator<Figura> it=modelo.getListado().listIterator();
	    while (it.hasNext()) {
	    	Figura tmp=it.next();
	    		if(tmp.dentroFigura(posicion)){
	    			tmp.setSeleccionada(true);
	    			return tmp;
	    		}
		    }
	    return null;
	}

	public void cambiarPosicion(Figura f, Point p){
            f.setPosicion(p);
	}
	
	public Vista getVista(){
		return vista;
	}
	
	public void anyadirFigura(Figura f){
		modelo.anyadirFigura(f);
                agregada=f;
	}
        
        public void eliminarFigura(Figura f){
                modelo.eliminarFigura(f);
        }
	
	public Figura getFiguraEn(Point p){
		return modelo.getFiguraEn(p);
	}

        public void eVmouseClicked(MouseEvent ev) {
            if(agregada!=null)
            {
                agregada.setPosicion(ev.getPoint());
                agregada.visible=true;
                agregada=null;
            }
            vista.repaint();
	}

	public void eVmousePressed(MouseEvent ev) {
		if(SwingUtilities.isLeftMouseButton(ev)){ 			//Click boton izquierdo selecciona figura
			seleccionada=this.getFiguraEn(ev.getPoint());
		}else if(SwingUtilities.isRightMouseButton(ev)){		//click boton derecho elimina la figura seleccionada
			seleccionada = this.getFiguraEn(ev.getPoint());
                        this.eliminarFigura(seleccionada);
		}else if(SwingUtilities.isMiddleMouseButton(ev))//click boton medio a�ade figura tipo circulo
		{
			
		}
		vista.repaint();		
	}
	
	public void eVmouseDragged(MouseEvent ev) {
		if(seleccionada!=null){
			//El movimiento puede ser mas fluido recalculando el pto
			this.cambiarPosicion(seleccionada, ev.getPoint());
			vista.repaint();
		}
	}

	public void eVmouseReleased (MouseEvent ev) {
		vista.repaint();
		if(seleccionada!=null){
			seleccionada.setSeleccionada(false);
			seleccionada=null;
		}
	}
//        Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba");
//	        try {
//
//	            db.store(modelo);
//
//	        } finally {
//	            db.close();
//	        }
//
//	        // accessDb4o
//
//
>>>>>>> 5f1365bd2f6537184919635dfeea5f3ac9635b3c
}
