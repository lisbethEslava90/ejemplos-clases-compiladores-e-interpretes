package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
	
	private List<Figura> listaFiguras;
        public String BaseD;

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

        public String toString(){

            return BaseD;
        }

        public void extraer(Modelo yy){
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
}
