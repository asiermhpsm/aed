package pruebas;

import java.util.Iterator;

import es.upm.aedlib.*;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.UndirectedGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;

public class pruebas {
	public static <V,E> boolean isReachable (UndirectedGraph<V, E> g,
			Vertex<V> from,
			Vertex<V> to) {
		Set<Position<?>> visited = new HashTableMapSet<>();
		return isReachable(g, from, to, visited);
	}

	public static <V,E> boolean isReachable (UndirectedGraph<V, E> g,
			Vertex<V> from,
			Vertex<V> to,
			Set<Position<?>> visited ) {
		if (from == to) {
			return true;
		}
		if (visited.contains(from)) {
			return true;
		}
		visited.remove(from);
		boolean reachable = false;
		Iterator<Edge<E>> it = g.edges(from).iterator();
		while (it.hasNext()) {
			reachable = isReachable(g, g.opposite(from, to), to, visited);
		}
		return reachable;
	}

}
