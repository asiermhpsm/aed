package es.upm.aedlib.tree;

import java.util.Arrays;
import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


public class DictImpl implements Dictionary {
	// A boolean because we need to know if a word ends in a node or not
	GeneralTree<Pair<Character,Boolean>> tree;

	public DictImpl() {
		tree = new LinkedGeneralTree<>();
		tree.addRoot(new Pair<Character,Boolean>(null,false));
	}

	public void add(String word) {
		if(word==null	||	word.isEmpty())	throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> pos=findPos(word);
		if(pos!=null)	pos.element().setRight(true);
		else {
			pos=tree.root();
			int i=0;
			while(i<word.length()) {
				Position<Pair<Character,Boolean>> hijo=searchChildLabelledBy(word.charAt(i), pos);
				pos=(hijo!=null)?hijo:addChildAlphabetically(new Pair<>(word.charAt(i),(i>=word.length()-1)), pos);
				i++;
			}
		}
	}
	
	public void delete(String word) {
		if(word==null	||	word.isEmpty())	throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> pos=findPos(word);
		if(pos!=null)	pos.element().setRight(false);
	}
	
	public boolean isIncluded(String word) {
		if(word==null	||	word.isEmpty())	throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> pos=findPos(word);
		return (pos!=null	&&	pos.element().getRight());
	}
	
	public PositionList<String> wordsBeginningWithPrefix(String prefix) {
		if(prefix==null)	throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> pos=findPos(prefix);
		if(pos==null)	return null;
		PositionList<Character> camino=new NodePositionList<Character>();
		for(int i=0;i<prefix.length()-1;i++)	camino.addLast(prefix.charAt(i));
		return preorder(pos, new NodePositionList<String>(), camino);
	}

	//METODOS PRIVADOS
	
	//Recorre en preorden el arbol y devuelve las palabras que encuentre.
	private PositionList<String> preorder(Position<Pair<Character,Boolean>> pos, PositionList<String> res, PositionList<Character> camino) {
		if(!pos.equals(tree.root())) camino.addLast(pos.element().getLeft());
		if(pos.element().getRight()) {
			String palabra=new String();
			for(Character c:camino) {
				palabra+=c;
			}
			res.addLast(palabra);
		}
		for(Position<Pair<Character,Boolean>> w:tree.children(pos)) res=preorder(w, res, camino);
		if(!pos.equals(tree.root())) camino.remove(camino.last());
		return res;
	}
	// Devuelve el nodo cuyo camino desde la raiz contiene
	// la palabra prefix. Si no existe el metodo devuelve null.
	private Position<Pair<Character,Boolean>> findPos(String prefix) {
		Position<Pair<Character,Boolean>> cursor=tree.root();
		int i=0;
		while(i<prefix.length()) {
			cursor=searchChildLabelledBy(prefix.charAt(i), cursor);
			if(cursor==null)	return null;
			i++;
		}
		return cursor;
	}
	// Devuelve el hijo del nodo pos que contiene el caracter ch, null si no existe
	private Position<Pair<Character,Boolean>>  searchChildLabelledBy(char ch,  Position<Pair<Character,Boolean>> pos) {
		Iterator<Position<Pair<Character, Boolean>>> it=tree.children(pos).iterator();
		while(it.hasNext()) {
			Position<Pair<Character, Boolean>> cursor=it.next();
			if(cursor.element().getLeft().equals(ch))	return cursor;
		}
		return null;
	}
	// Anade un hijo al nodo pos conteniendo el elemento pair,
	// respetando el orden alfabetico de los hijos.
	private Position<Pair<Character,Boolean>>  addChildAlphabetically(Pair<Character,Boolean> pair, Position<Pair<Character,Boolean>> pos) {
		Iterator<Position<Pair<Character, Boolean>>> it=tree.children(pos).iterator();
		while(it.hasNext()) {
			Position<Pair<Character,Boolean>> cursor=it.next();
			if(pair.getLeft()<cursor.element().getLeft())	return tree.insertSiblingBefore(cursor, pair);
		}
		return tree.addChildLast(pos, pair);
	}






















}
