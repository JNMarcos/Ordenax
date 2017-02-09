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
	private List<List<Integer>> possiveisCiclos = new ArrayList<List<Integer>>(); 
	private List<List<Integer>> possiveisCiclosTempo = new ArrayList<List<Integer>>();
	int h = 0;
	public Algoritmo(Grafo grafo){
		this.grafo = grafo;
	}

	public String isCiclico(){
		//obtém uma lista de todas as chaves

		List<Integer> temp;
		boolean possuiAoMenosUmaSaida;
		boolean jaFoiAdicionado;
		String mensagem = "\nO grafo não é cíclico. Não fez mais que sua obrigação!";
		List<Integer> arestasChave = new ArrayList<>(grafo.getArestas().keySet());
		this.arestasChave = arestasChave;

		List<Integer> listaTemporaria = new ArrayList<Integer>();
		int nVertices = arestasChave.size();

		int somaArestas = 0;
		int somaContrario = 0;
		int difArestas;

		boolean isEntrada;

		for (int i = nVertices - 1; i >= 0; i--){
			listaTemporaria.add(arestasChave.get(i));
		}

		arestasChave = listaTemporaria;
		listaTemporaria = null;
		for (int i = 0; i < nVertices; i++){
			isEntrada = true;
			possuiAoMenosUmaSaida = false;
			possiveisCiclosTempo = new ArrayList<>();
			for (int j = 0; j < nVertices; j++){
				jaFoiAdicionado = false;
				if (grafo.getArestas().get(arestasChave.get(j)).contains(arestasChave.get(i))){
					isEntrada = false; //se os vértices forem dif então não é de entrada
					if (grafo.getArestas().get(arestasChave.get(i)).contains(arestasChave.get(j))){
						somaContrario = somaContrario + 1;
					}
				}

				System.out.println(arestasChave);
				System.out.println("i "+arestasChave.get(i));
				System.out.println("j "+arestasChave.get(j));

				if (grafo.getArestas().get(arestasChave.get(i)).contains(arestasChave.get(j))){
					System.out.println("iteração " + ++h);
					possuiAoMenosUmaSaida = true;

					System.out.println("Tamanho poss" + possiveisCiclos.size());
					System.out.println(possiveisCiclos);
					possiveisCiclosTempo.clear();
					System.out.println("Tempo " + possiveisCiclosTempo);
					for (int l = 0; l < possiveisCiclos.size(); l++){
						//&& possuiAoMenosUmaSaida == false; l++){
						//System.out.println(possiveisCiclos.get(l));
						possiveisCiclosTempo.add(possiveisCiclos.get(l));
						//System.out.println(possiveisCiclos.get(l));
					}

					if (possiveisCiclos.size() > 0){//existe uma lista
						System.out.println("entrou ali");
						for (int k = 0; k < possiveisCiclos.size(); k++){
							if (possiveisCiclos.get(k).contains(arestasChave.get(i))
									&& possiveisCiclos.get(k).lastIndexOf(arestasChave.get(i)) == possiveisCiclos.get(k).size() - 1
									){
								possiveisCiclosTempo.get(k).add(arestasChave.get(j));
								
								String aux = possiveisCiclosTempo.get(k).toString();
								System.out.println(aux);
								for (int v = 0; v < possiveisCiclosTempo.get(k).size(); v++){
									System.out.println(possiveisCiclosTempo.get(k).get(v));
									if (aux.split("" + possiveisCiclosTempo.get(k).get(v)).length >= 3){
										System.out.println("aux " + aux);
										isCiclico = true;
									}
								}
								System.out.println("Entrou aqui");
							} else {
								System.out.println("é aqui porra");
								temp = new ArrayList<>();
								temp.add(arestasChave.get(i));
								temp.add(arestasChave.get(j));
								possiveisCiclosTempo.add(temp);
								//System.out.println("ciclos" + possiveisCiclos);
								System.out.println("Possiveisteo " + possiveisCiclosTempo);
							}
						}


						possiveisCiclos.add(possiveisCiclosTempo.get(possiveisCiclosTempo.size() - 1));

						//possiveisCiclos = possiveisCiclosTempo;
						//possiveisCiclos.addAll(possiveisCiclosTempo);
						//possiveisCiclosTempo = new ArrayList<>();
					} else {
						System.out.println("Chegou aqui");
						possuiAoMenosUmaSaida = true;
						temp = new ArrayList<>();
						temp.add(arestasChave.get(i));
						temp.add(arestasChave.get(j));
						possiveisCiclos.add(temp);
					}
				}

				System.out.println(possiveisCiclos);

				
				System.out.println("aqui" + possiveisCiclos + "tam " + possiveisCiclos.size());


			}
			
			//REVER CASO DE ERRO
			int retira = 0;
			if (possuiAoMenosUmaSaida == false){
				for (int k = 0; k < possiveisCiclos.size() - retira; k++){
					if (possiveisCiclos.get(k).contains(arestasChave.get(i))){
						System.out.println("SAÍDA " + possiveisCiclos.get(k));
						possiveisCiclos.remove(k);
						System.out.println(possiveisCiclos);
						retira++;
					}
				}
			}

			if (isEntrada == true){
				System.out.println("Entrada: " + arestasChave.get(i) );
				listaEntrada.add(arestasChave.get(i));
			} 

			listaTemporaria = grafo.getArestas().get(arestasChave.get(i));
			if (listaTemporaria != null){
				somaArestas = somaArestas + listaTemporaria.size();
			}
		}

		somaContrario = somaContrario/2;
		difArestas = somaArestas - somaContrario;

		System.out.println("\nNº de Vértices " + arestasChave.size());
		System.out.println("Nº de Arestas: " + somaArestas);
		System.out.println("Nº de Arestas Duplas: " + somaContrario);
		System.out.println("Diferença entre Arestas: " +difArestas);

		if (isCiclico == true){//difArestas >= nVertices && isCiclico == true){
			//isCiclico = true;
			mensagem = "\nO grafo inserido é cíclico. Então não podemos fazer uma ordem "
					+ "topológica dele.\nModifique o arquivo para que o grafo seja não cíclico."
					+ "\nTente, não é difícil.";
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
			System.out.println("\nHá elementos na lista");
			//elOrdenados.add(listaEntrada.get(0));


			valor = listaEntrada.get(0);
			System.out.println("Chave do Vértice " + valor);

			for (int i = 0; i < grafo.getArestas().get(valor).size(); i++){
				temporario.add(grafo.getArestas().get(valor).get(i));
			}

			System.out.println("Lista de Vértices Conectados à Chave" + temporario);
			elOrdenados.add(valor);
			System.out.println("Elementos Parcialmente Ordenados " + elOrdenados);
			System.out.println("Lista Entrada" + listaEntrada);
			listaEntrada.remove(0);
			System.out.println("Removeu da lista de entrada o valor");
			System.out.println("Lista Entrada" + listaEntrada);


			for (int i = 0; i < temporario.size(); i++){
				aindaTem = false;
				System.out.println("Valor temporário " + temporario.get(i));
				grafo.getArestas().get(valor).remove(temporario.get(i));
				for  (int j = 0; j < arestasChave.size(); j++){
					if (grafo.getArestas().get(arestasChave.get(j)) != null && 
							grafo.getArestas().get(arestasChave.get(j)).contains(temporario.get(i))){
						aindaTem = true; // ainda tem ligações
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

