package aed.recursion;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.positionlist.*;


public class Explorador {

	public static Pair<Object,PositionList<Lugar>> explora(Lugar inicialLugar) {
		return exploraAux(inicialLugar, null, null);
	}

	public static Pair<Object,PositionList<Lugar>> exploraAux(Lugar lugarActual,
			Object tesoro, PositionList<Lugar> camino) {
		Pair<Object,PositionList<Lugar>> res;
		if(lugarActual.tieneTesoro()) {
			tesoro=lugarActual.getTesoro();
			camino.addLast(lugarActual);
		}
		else {
			lugarActual.marcaSueloConTiza();
			Iterator<Lugar> it = lugarActual.caminos().iterator();
			while(tesoro==null	&&	it.hasNext()) {
				Lugar next=it.next();
				if(!next.sueloMarcadoConTiza()) {
					camino.addLast(next);
					res=exploraAux(next, tesoro, camino);
					if(tesoro==null) {
						camino.remove(camino.last());
					}
				}

			}
		}
		res = new Pair(tesoro, camino);
		return res;
	}

}
