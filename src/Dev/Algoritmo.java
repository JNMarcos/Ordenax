/**
 * 
 */
package Dev;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JN
 *
 */
public class Algoritmo {
	private Grafo grafo;
	public boolean isCiclico = false;
	public List<Integer> listaEntrada = new ArrayList<>();
	private List<Integer> arestasChave;

	public Algoritmo(Grafo grafo){
		this.grafo = grafo;
	}

	public String isCiclico(){
		//obt�m uma lista de todas as chaves

		String mensagem = "\nO grafo n�o � c�clico. N�o fez mais que sua obriga��o!";
		List<Integer> arestasChave = new ArrayList<>(grafo.getArestas().keySet());
		this.arestasChave = arestasChave;

		List<Integer> listaTemporaria = null;
		int nVertices = arestasChave.size();

		int somaArestas = 0;
		int somaContrario = 0;
		int difArestas;

		boolean isEntrada;

		for (int i = 0; i < nVertices; i++){
			isEntrada = true;
			for (int j = 0; j < nVertices; j++){
				if (grafo.getArestas().get(arestasChave.get(j)).contains(arestasChave.get(i))){
					isEntrada = false; //se os v�rtices forem dif ent�o n�o � de entrada
					if (grafo.getArestas().get(arestasChave.get(i)).contains(arestasChave.get(j))){
						somaContrario = somaContrario + 1;
					}
				}
			}

			if (isEntrada == true){
				listaEntrada.add(arestasChave.get(i));
			} 

			listaTemporaria = grafo.getArestas().get(arestasChave.get(i));
			if (listaTemporaria != null){
				somaArestas = somaArestas + listaTemporaria.size();
			}
		}

		somaContrario = somaContrario/2;
		difArestas = somaArestas - somaContrario;

		System.out.println("\nN� de V�rtices " + arestasChave.size());
		System.out.println("N� de Arestas: " + somaArestas);
		System.out.println("N� de Arestas Duplas: " + somaContrario);
		System.out.println("Diferen�a entre Arestas: " +difArestas);

		if (difArestas >= nVertices){
			isCiclico = true;
			mensagem = "\nO grafo inserido � c�clico. Ent�o n�o podemos fazer uma ordem "
					+ "topol�gica dele.\nModifique o arquivo para que o grafo seja n�o c�clico."
					+ "\nTente, n�o � dif�cil.";
		} 

		for (int k = 0; k < listaEntrada.size(); k++){
			System.out.println(listaEntrada.get(k));
		}

		return mensagem;
	}

	public  List<Integer> ordenacaoTopologica(){
		List<Integer> elOrdenados = new ArrayList<>();
		List<Integer> temporario = new ArrayList<>();

		int valor;
		boolean aindaTem;

		while (listaEntrada.size() >= 1){
			aindaTem = false;
			System.out.println("\nH� elementos na lista");
			//elOrdenados.add(listaEntrada.get(0));
			
		
				valor = listaEntrada.get(0);
				System.out.println("Chave do V�rtice " + valor);

				for (int i = 0; i < grafo.getArestas().get(valor).size(); i++){
					temporario.add(grafo.getArestas().get(valor).get(i));
				}

				System.out.println("Lista de V�rtices Conectados � Chave" + temporario);
				elOrdenados.add(valor);
				System.out.println("Elementos Parcialmente Ordenados " + elOrdenados);
				System.out.println("Lista Entrada" + listaEntrada);
				listaEntrada.remove(0);
				System.out.println("Removeu da lista de entrada o valor");
				System.out.println("Lista Entrada" + listaEntrada);
		

			for (int i = 0; i < temporario.size(); i++){
				System.out.println("Valor tempor�rio " + temporario.get(i));
				grafo.getArestas().get(valor).remove(temporario.get(i));
				for  (int j = 0; j < arestasChave.size(); j++){
					if (grafo.getArestas().get(arestasChave.get(j)) != null && 
							grafo.getArestas().get(arestasChave.get(j)).contains(temporario.get(i))){
						aindaTem = true; // ainda tem liga��es
						break;
					}
				}

				if (aindaTem == false && temporario != null){
					listaEntrada.add(temporario.get(i));
					System.out.println("Adiciona a lista entrada " + temporario.get(i));
				}
			}
			temporario.clear();
		}
		return elOrdenados;
	}

}

