package aed.delivery;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import java.util.Iterator;

public class Delivery<V> {

	DirectedGraph<V,Integer> graph;
	Map<V,Vertex <V>> map;

	// Construct a graph out of a series of vertices and an adjacency matrix.
	// There are 'len' vertices. A negative number means no connection. A non-negative
	// number represents distance between nodes.
	public Delivery(V[] places, Integer[][] gmat) {
		graph= new DirectedAdjacencyListGraph<>();
		map=new HashTableMap<>();
		for(int i=0;i<places.length;i++) {
			map.put(places[i], graph.insertVertex(places[i]));
		}
		for(int i=0;i<gmat.length;i++) {
			for(int j=0;j<gmat[0].length;j++) {
				if(gmat[i][j]!=null	&&	gmat[i][j]>=0) {
					graph.insertDirectedEdge(map.get(places[i]), map.get(places[j]), gmat[i][j]);
				}
			}
		}
	}

	// Just return the graph that was constructed
	public DirectedGraph<V, Integer> getGraph() {
		return graph;
	}

	// Return a Hamiltonian path for the stored graph, or null if there is noe.
	// The list containts a series of vertices, with no repetitions (even if the path
	// can be expanded to a cycle).
	public PositionList <Vertex<V>> tour() {
		PositionList<Vertex<V>> tour = new NodePositionList<>();
		int i = 0;
		while(tour.size() != sitios.size() && i < sitios.size()) {
			PositionList<Vertex<V>>  aux = new NodePositionList<>();
			Vertex<V> v0 = sitios.get(i);
			aux.addLast(v0);
			aux = recorreGrafo(v0,aux);
			tour = aux;
			i++;
		}
		if (tour.isEmpty()) return null;
		else return tour;
	}

	public int length(PositionList<Vertex<V>> path) {
		return (path.size()>0)?path.size():0;
	}

	public String toString() {
		return "Delivery";
	}

	private PositionList <Vertex<V>> recorreGrafo (Vertex<V> vertex,PositionList <Vertex<V>> memoria) {
		Iterator<Edge<Integer>> edges = mapa.outgoingEdges(vertex).iterator();
		while (edges.hasNext() && memoria.size() != sitios.size()) {
			Edge<Integer> edge = edges.next();
			Vertex<V> nextVertex = mapa.endVertex(edge);
			if (!member(memoria,nextVertex)) {
				PositionList<Vertex<V>> previousStep = copia(memoria);
				memoria.addLast(nextVertex);
				PositionList<Vertex<V>> nextStep = recorreGrafo(nextVertex,memoria);
				if (nextStep.size() == sitios.size()) {
					memoria = nextStep;
				}else memoria = previousStep;
			}
		}
		if (memoria.size() == sitios.size()) return memoria;
		else return new NodePositionList<>();
	}

	/**
	 * copy all the elements of iter in a new position list
	 * @param <E>
	 * @param iter
	 * @return A copy of iter 
	 */
	private <E> PositionList<E> copia(Iterable<E> iter){
		PositionList<E> copia = new NodePositionList<>();
		for (E elem : iter) {
			copia.addLast(elem);
		}
		return copia;
	}

	/**
	 * search elem in iter
	 * @param <E>
	 * @param iter
	 * @param elem
	 * @return true if elem is cotained in iter else return false
	 */
	private <E> boolean member(Iterable<E> iter, E elem){
		boolean member = false;
		for (E cursor : iter) {
			member |= cursor == elem;
		}
		return member;
	}
}
