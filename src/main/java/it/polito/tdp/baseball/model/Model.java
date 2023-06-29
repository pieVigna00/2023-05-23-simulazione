package it.polito.tdp.baseball.model;
//MOLTO IMPORTANTE PER QUERY CHE FA VEDERE CORRELAZIONE TRA QUERY VERTICI E QUERY ARCHI 
//da guardare per capire database
//da guardare per le query e gestione
//da guardare per ispettore comp connesse
//da guardare per cercre grado massimo 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.baseball.db.BaseballDAO;

public class Model {
	BaseballDAO dao;
	Graph<People, DefaultEdge> grafo;
	Map<String, People> mappaPeople;
	public Model() {
		this.dao= new BaseballDAO();
		this.mappaPeople= new HashMap<>();
		this.dao.readAllPlayers(mappaPeople);
	}
	public void buildGraph(int anno, int salario) {
		System.out.println(salario+ " "+anno);
		grafo= new SimpleGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(grafo, this.dao.getVertici(anno, salario));
		List<ArcoMomentaneo> result= this.dao.getCorrelazionePeople(mappaPeople, anno);
		for(ArcoMomentaneo a: result) {
			if(grafo.containsVertex(a.getP1()) && grafo.containsVertex(a.getP2())) {
				grafo.addEdge(a.getP1(), a.getP2());
			}
		}
	}
	public int getNumVertici() {
		return grafo.vertexSet().size();
	}
	public int getNumArchi() {
		return grafo.edgeSet().size();
	}
	public People getGradoMax() {
		int max=0;
		People result=null;
		for(People p: grafo.vertexSet()) {
			int grado=Graphs.neighborListOf(grafo, p).size();
			if(grado>max) {
				max=grado;
			}
		}
		for(People p: grafo.vertexSet()) {
			int grado=Graphs.neighborListOf(grafo, p).size();
			if(grado==max) {
				result=p;
			}
		}
		return result;
	}
	public int getValoreGradoMax() {
		int max=0;
		for(People p: grafo.vertexSet()) {
			int grado=Graphs.neighborListOf(grafo, p).size();
			if(grado>max) {
				max=grado;
			}
		}
		return max;
	}
	public int getConnesse() {
		ConnectivityInspector<People, DefaultEdge> ispettore=new ConnectivityInspector<>(grafo);
		return ispettore.connectedSets().size();
	}
}