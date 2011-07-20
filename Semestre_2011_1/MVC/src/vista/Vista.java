package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import modelo.Modelo;
import modelo.Figura;
import controlador.Controlador;
import java.awt.Point;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.T;
import modelo.interprete;
import modelo.maquina;
import modelo.programa;



public class Vista extends JPanel{
	static final long serialVersionUID = 0L;
	private Modelo modelo;
	public Controlador controlador;  //IMPORTANTE DEBE SER REGISTRADO O TODO FALLA
        private JMenuBar barra;
        private JMenu agregar, diagrama;
        private JMenuItem item1, item2, item3, item4, item5, item6, item7;
        private Point lugar;
        private JFrame datos;
        private JLabel lfuente, lobjeto, lescribe;
        private JTextField fuente, objeto, escrito;
        private JButton aceptar;
        private String de="", para="", en="";
        private int opcion = 0, posx = 0, posy = 0;
        private boolean band = false, bcomp = false, bpro = false, bmaq = false, binter = false, bvir =false;
        private Timer timer;
       
	
	public Vista(Dimension size, Modelo modelo){
		super();
		this.modelo=modelo;
		
		setPreferredSize(size);
		setBackground(Color.white);
		setFocusable(true);

                lugar = new Point(100,100);
                timer = new Timer (1, new ActionListener ()
                {
                    public void actionPerformed(ActionEvent e)
                    {

                      if(band == true && bcomp == true){
                        
                        controlador.anyadirFigura(new T(lugar,200,de,para,en));
                        band = false;
                        bcomp = false;
                        datos.setVisible(false);
                        fuente.setText("");
                        objeto.setText("");
                        escrito.setText("");
                      }
                      else if(band == true && bpro == true)
                      {
                        controlador.anyadirFigura(new programa(lugar,70,de,para));
                        band = false;
                        bpro = false;
                        datos.setVisible(false);
                        fuente.setText("");
                        objeto.setText("");
                        escrito.setText("");
                      }
                      else if(band == true && bmaq == true)
                      {
                        controlador.anyadirFigura(new maquina(lugar,80,de,para,false));
                        band = false;
                        bmaq = false;
                        datos.setVisible(false);
                        fuente.setText("");
                        objeto.setText("");
                        escrito.setText("");
                      }
                      else if(band == true && binter == true)
                      {
                        controlador.anyadirFigura(new interprete(lugar, 80, de, para));
                        band = false;
                        binter = false;
                        datos.setVisible(false);
                        fuente.setText("");
                        objeto.setText("");
                        escrito.setText("");
                      }
                      else if(band == true && bvir == true)
                      {
                        controlador.anyadirFigura(new maquina(lugar, 80, de, para,true));
                        band = false;
                        bvir = false;
                        datos.setVisible(false);
                        fuente.setText("");
                        objeto.setText("");
                        escrito.setText("");
                      }

                     }
                });
                lfuente = new JLabel("Lenguaje Fuente:");
                lobjeto = new JLabel("Lenguaje Objeto:");
                lescribe = new JLabel("Escrito en:");
                fuente = new JTextField();
                objeto = new JTextField();
                escrito = new JTextField();
                aceptar = new JButton("Aceptar");
                timer.start();

		//Mejorable al 1000% solo por simplificacion realizado de esta forma
		MouseController mouseControl = new MouseController() {
			public void mouseClicked(MouseEvent event) {
                            eVmouseClicked(event); }
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
			public void mouseMoved(MouseEvent event) {}
			public void mousePressed(MouseEvent event) {
			    eVmousePressed(event);	}
			public void mouseReleased(MouseEvent event) {
				eVmouseReleased(event);	}
			public void mouseDragged(MouseEvent event) {
				eVmouseDragged(event);	}
		};
		this.addMouseListener(mouseControl);
		this.addMouseMotionListener(mouseControl);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		pintarTodo(g2);
	}
	
	private void pintarTodo(Graphics2D g){
		for (Figura elemento : modelo.getListado()) {
			elemento.dibujar(g);
		}
	}

        public void eVmouseClicked(MouseEvent ev) {
		if(controlador!=null)
		{
			lugar=ev.getPoint();
                        controlador.eVmouseClicked(ev);
		}
	}

	public void eVmousePressed(MouseEvent ev) {
		if(controlador!=null)
		{
			controlador.eVmousePressed(ev);
		}
	}
	
	public void eVmouseDragged(MouseEvent ev) {
		if(controlador!=null)
		{
			controlador.eVmouseDragged(ev);
		}
	}
	
	public void eVmouseReleased (MouseEvent ev) {
		if(controlador!=null)
		{
			controlador.eVmouseReleased(ev);
		}
	}

        public void datos(){

            datos = new JFrame("Datos");

            datos.setBounds(100, 100, 300, 200);
            datos.setLayout(null);
            datos.setResizable(false);
            datos.setDefaultCloseOperation(datos.getDefaultCloseOperation());
            datos.add(lfuente);
            datos.add(lobjeto);
            datos.add(lescribe);
            datos.add(fuente);
            datos.add(objeto);
            datos.add(escrito);
            datos.add(aceptar);


            switch (opcion)
            {
                case 1:
                        lfuente.setText("Lenguaje Fuente:");
                        lobjeto.setText("Lenguaje Objeto");
                        lescribe.setText("Escrito en:");
                        lobjeto.setVisible(true);
                        objeto.setVisible(true);
                        lescribe.setVisible(true);
                        escrito.setVisible(true);
                        opcion = 0;
                        break;
                case 2:
                        lfuente.setText("Programa: ");
                        lobjeto.setText("Escrito en: ");
                        lescribe.setVisible(false);
                        escrito.setVisible(false);
                        opcion = 0;
                        break;

                case 3:
                        lfuente.setText("Plataforma:");
                        lobjeto.setVisible(false);
                        lescribe.setVisible(false);
                        escrito.setVisible(false);
                        objeto.setVisible(false);
                        opcion = 0;
                        break;
                case 4:
                        lfuente.setText("Interprete de:");
                        lobjeto.setText("Escrito en:");
                        lobjeto.setVisible(true);
                        lescribe.setVisible(false);
                        objeto.setVisible(true);
                        escrito.setVisible(false);
                        opcion = 0;
                        break;
                case 5:
                        lfuente.setText("Interprete de:");
                        lobjeto.setText("Ejecutado en:");
                        lobjeto.setVisible(true);
                        lescribe.setVisible(false);
                        escrito.setVisible(false);
                        objeto.setVisible(true);
                        opcion = 0;
                        break;

            }

            datos.setVisible(true);
            lfuente.setBounds(5, 5, 120, 30);
            lobjeto.setBounds(5, 50, 120, 30);
            lescribe.setBounds(5, 100, 80, 30);
            fuente.setBounds(115, 10, 100, 30);
            objeto.setBounds(115, 50, 100, 30);
            escrito.setBounds(115, 90, 100, 30);
            aceptar.setBounds(120, 130, 80, 30);

            aceptar.addMouseListener( new MouseAdapter()
        {
            @Override
          public void mouseClicked(MouseEvent e)
          {
              de = fuente.getText();
              para = objeto.getText();
              en = escrito.getText();
              band = true;
              repaint();
          }

        });

        }

        public JMenuBar barra_menu()
        {
            barra = new JMenuBar();
            agregar = new JMenu("Agregar");
            diagrama = new JMenu("Diagrama");
            item1 = new JMenuItem("Compilador");
            item1.addActionListener(new EventoMenu());
            item2 = new JMenuItem("Programa");
            item2.addActionListener(new EventoMenu());
            item3 = new JMenuItem("Maquina");
            item3.addActionListener(new EventoMenu());
            item4 = new JMenuItem("Interprete");
            item4.addActionListener(new EventoMenu());
            item5 = new JMenuItem("Maquina Virtual");
            item5.addActionListener(new EventoMenu());
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
           opcion = 1;
           datos();
           bcomp = true;
        }
        if( evt.getSource()==item2)
        {
            opcion = 2;
            datos();
            bpro = true;
        }
        if( evt.getSource()==item3)
        {
            opcion = 3;
            datos();
            bmaq = true;
        }
        if( evt.getSource()==item4)
        {
           opcion = 4;
           datos();
           binter = true;

        }
        if( evt.getSource()==item5)
        {
            opcion = 5;
            datos();
            bvir = true;
        }

        }
     }

}


/**************************************************
* SOLO para ahorrar espacio y simplificar cosas
**************************************************/
class MouseController implements MouseListener, MouseMotionListener {
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseDragged(MouseEvent event) {}
	public void mouseMoved(MouseEvent event) {}
}

