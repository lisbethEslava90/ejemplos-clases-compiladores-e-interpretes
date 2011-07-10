package controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import javax.swing.*;

import vista.Vista;
import modelo.Circulo;
import modelo.Cuadrado;
import modelo.Figura;
import modelo.Modelo;
import modelo.T;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private Figura seleccionada;
        private JMenuBar barra;
        private JMenu agregar, diagrama;
        private JMenuItem item1, item2, item3, item4, item5, item6, item7;
        private Point lugar;
	
	public Controlador(Modelo modelo, Vista vista){
		this.modelo=modelo;
		this.vista=vista;
		seleccionada=null;
                lugar = new Point(50, 50);
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
	}
	
	public Figura getFiguraEn(Point p){
		return modelo.getFiguraEn(p);
	}

        public JMenuBar barra_menu()
        {
            barra = new JMenuBar();
            agregar = new JMenu("Agregar");
            diagrama = new JMenu("Diagrama");
            item1 = new JMenuItem("Compilador");
            item1.addActionListener(new EventoMenu());
            item2 = new JMenuItem("Programa");
            item3 = new JMenuItem("Maquina");
            item4 = new JMenuItem("Interprete");
            item5 = new JMenuItem("Maquina Virtual");
            item6 = new JMenuItem("Guardar Modelo");
            item7 = new JMenuItem("Cargar Modelo");
            agregar.add(item1);
            agregar.add(item2);
            agregar.add(item3);
            agregar.add(item4);
            agregar.add(item5);
            diagrama.add(item6);
            diagrama.add(item7);
            barra.add(agregar);
            barra.add(diagrama);
         return barra;

        }

        class EventoMenu implements ActionListener{

       public void actionPerformed(ActionEvent evt) {

        if( evt.getSource()==item1)
        {
           controlador.Controlador.this.anyadirFigura(new T(lugar, 200));
        }

        }
     }

	public void eVmousePressed(MouseEvent ev) {
          //  lugar = ev.getPoint();
		if(SwingUtilities.isLeftMouseButton(ev)){ 			//Click boton izquierdo selecciona figura
			seleccionada=this.getFiguraEn(ev.getPoint());
		}else if(SwingUtilities.isRightMouseButton(ev)){		//click boton izquierdo a�ade figura tipo cuadrado
			this.anyadirFigura(new Cuadrado(ev.getPoint(),40));			
		}else if(SwingUtilities.isMiddleMouseButton(ev))//click boton medio a�ade figura tipo circulo
		{
			this.anyadirFigura(new Circulo(ev.getPoint(),40));
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

  

}
