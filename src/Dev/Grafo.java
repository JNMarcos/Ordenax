/**
 * 
 */
package Dev;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author JN
 *
 */
public class Grafo {
	
	private List<No> vertices;
	private Map<Integer, List<Integer>> arestas;
	
	public Grafo(){
		setVertices(new ArrayList<>());
		arestas = new Hashtable<>();
	}

	public List<No> getVertices() {
		return vertices;
	}
	
	public void setVertice(No vertice) {
		this.vertices.add(vertice);
	}

	public void setVertices(List<No> vertices) {
		this.vertices = vertices;
	}

	public Map<Integer, List<Integer>> getArestas() {
		return arestas;
	}
	
	public void setArestaAdj(Integer vertice, Integer adjacente) {
		arestas.get(vertice).add(adjacente);
	}

	public void setArestas(Integer chave, List<Integer> arestas) {
		this.arestas.put(chave, arestas);
	}

}
