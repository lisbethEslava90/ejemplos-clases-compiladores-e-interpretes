import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import vista.Vista;
import modelo.Modelo;
import controlador.Controlador;


<<<<<<< HEAD
    static JFrame frame;

    public Figuras() {

        frame = new JFrame();
        frame.setTitle("Modelador Diagramas de Tombstone");
        //Set the window initial Size & default close operation

        frame.setVisible(true);
        Dimension fullscreen = Toolkit.getDefaultToolkit().getScreenSize();
        fullscreen.width = fullscreen.width - 400;
        fullscreen.height = fullscreen.height - 400;
        frame.setBounds(50, 50, fullscreen.width, fullscreen.height);
        frame.getContentPane().setPreferredSize(fullscreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container guiobjects = frame.getContentPane();
        guiobjects.setLayout(new BorderLayout());
        Modelo modelo = new Modelo();
        Vista vista = new Vista(new Dimension(1000, 800), modelo);
        final Controlador controlador = new Controlador(modelo, vista);
        vista.controlador = controlador; //Lo registro para su uso, deberia ser un metodo pero por simplificacion
        frame.setJMenuBar(vista.barra_menu());
        JScrollPane ModelScroll = new JScrollPane(controlador.getVista(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        guiobjects.add(ModelScroll);
        /*ModelScroll.repaint();
        frame.repaint();*/
        frame.pack();
=======
public class Figuras {

    static  JFrame frame;

    public Figuras (){

                        frame = new JFrame();
			frame.setTitle("Modelador Diagramas de Tombstone");
			//Set the window initial Size & default close operation
                        
			frame.setVisible(true);
			Dimension fullscreen = Toolkit.getDefaultToolkit().getScreenSize();
			fullscreen.width=fullscreen.width-400;
			fullscreen.height=fullscreen.height-400;
			frame.setBounds(50,50,fullscreen.width,fullscreen.height);
			frame.getContentPane().setPreferredSize(fullscreen);
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                        Container guiobjects = frame.getContentPane();
                        guiobjects.setLayout(new BorderLayout());
			Modelo modelo = new Modelo();
			Vista vista = new Vista(new Dimension(1000,800),modelo);
			final Controlador controlador = new Controlador(modelo,vista);
			vista.controlador=controlador; //Lo registro para su uso, deberia ser un metodo pero por simplificacion
                        frame.setJMenuBar(vista.barra_menu());
			JScrollPane ModelScroll = new JScrollPane(controlador.getVista(), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			guiobjects.add(ModelScroll);
			/*ModelScroll.repaint();
			frame.repaint();*/
			frame.pack();
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
    }

    public static void main(String[] args) {
		try{
			new Figuras();
		}catch (RuntimeException e){
			exitApplication();
		}

	}

	public static void exitApplication() {
		   System.out.println("Saliendo Adios...");
		   System.exit(0);
        }



}
