package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
	
	private List<Figura> listaFiguras;
        public String BaseD;
<<<<<<< HEAD
=======

>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
	public Modelo(){
		listaFiguras = new ArrayList<Figura>();
	}
	
	public void AnyadirFigura(Figura f){
		listaFiguras.add(f);
	}
	
	public List<Figura> getListado(){
		return listaFiguras;
	}
	
	public void anyadirFigura(Figura f){
		listaFiguras.add(f);
	}

        public void eliminarFigura(Figura f){
                listaFiguras.remove(f);
        }
	
	public Figura getFiguraEn(Point p){
		for (Figura elemento : getListado()) {
			if(elemento.dentroFigura(p)){
				elemento.seleccionada=true;
				return elemento;				
			}
		}
		return null;
	}
<<<<<<< HEAD
         public String toString(){

            return BaseD;
        }
 public void extraer(Modelo yy){
=======

        public String toString(){

            return BaseD;
        }

        public void extraer(Modelo yy){
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
            for(int k=0;k<listaFiguras.size();k++)
            {
                listaFiguras.remove(0);
            }
            System.out.println("Nuevo tamaño"+ listaFiguras.size());
            for(Figura d:yy.listaFiguras)
            {
               listaFiguras.add(d);
            }
            listaFiguras=yy.listaFiguras;
            System.out.println(listaFiguras);

        }
<<<<<<< HEAD
        public void limpiar(Modelo yy){            
               listaFiguras.clear();           
        }

=======
<<<<<<< HEAD

        public void limpiar(Modelo yy){            
               listaFiguras.clear();           
        }
=======
>>>>>>> 4f0f8471e7efb538e56cd702ec37a77097327e55
>>>>>>> d9343a4cdba3aad1b32abc5389acd6641dd23c0e
}
