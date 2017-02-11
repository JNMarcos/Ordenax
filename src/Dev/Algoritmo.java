/**
 * 
 */
package Dev;

import java.util.ArrayList;
import java.util.Arrays;
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
	private String tipoOrdenacao;
	
	public Algoritmo(Grafo grafo, String tipoOrdenacao){
		this.grafo = grafo;
		this.tipoOrdenacao = tipoOrdenacao;
	}

	public String isCiclico(){
		//obtém uma lista de todas as chaves

		List<Integer> temp;
		boolean possuiAoMenosUmaSaida;


		boolean jaFoiAdicionado;
		String mensagem = "\nO grafo não é cíclico. Não fez mais que sua obrigação!";
		List<Integer> arestasChave = new ArrayList<>(grafo.getArestas().keySet());
		this.arestasChave = arestasChave;

		int[] estaNoGrafo = new int[arestasChave.size()];
		Arrays.fill(estaNoGrafo, 0);

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
		for (int i = 0; isCiclico == false && i < nVertices; i++){
			isEntrada = true;
			possuiAoMenosUmaSaida = false;
			possiveisCiclosTempo = new ArrayList<>();
			for (int j = 0; isCiclico == false && j < nVertices; j++){
				jaFoiAdicionado = false;
				if (grafo.getArestas().get(arestasChave.get(j)).contains(arestasChave.get(i))){
					isEntrada = false; //se os vértices forem dif então não é de entrada
					if (grafo.getArestas().get(arestasChave.get(i)).contains(arestasChave.get(j))){
						somaContrario = somaContrario + 1;
					}
				}

				if (grafo.getArestas().get(arestasChave.get(i)).contains(arestasChave.get(j))){
					possuiAoMenosUmaSaida = true;

					estaNoGrafo[i] = 1;
					estaNoGrafo[j] = 1;

					possiveisCiclosTempo.clear();

					for (int l = 0; l < possiveisCiclos.size(); l++){
						possiveisCiclosTempo.add(possiveisCiclos.get(l));
					}

					if (possiveisCiclos.size() > 0){//existe uma lista
						for (int k = 0; k < possiveisCiclos.size() && isCiclico == false; k++){
							if (possiveisCiclos.get(k).contains(arestasChave.get(i))
									&& possiveisCiclos.get(k).lastIndexOf(arestasChave.get(i)) == possiveisCiclos.get(k).size() - 1
									){
								possiveisCiclosTempo.get(k).add(arestasChave.get(j));

								for (int v = 0; v < possiveisCiclosTempo.get(k).size() - 1; v++){
									for (int w = v + 1; w < possiveisCiclosTempo.get(k).size(); w++){
										if (possiveisCiclosTempo.get(k).get(v) == possiveisCiclosTempo.get(k).get(w)){
											isCiclico = true;
											mensagem = "\nPrimeiro ciclo encontrado: " + possiveisCiclos.get(k) + "\n";
											break;
										}
									}
								}
							} else {
								temp = new ArrayList<>();
								temp.add(arestasChave.get(i));
								temp.add(arestasChave.get(j));
								possiveisCiclosTempo.add(temp);
							}
						}


						possiveisCiclos.add(possiveisCiclosTempo.get(possiveisCiclosTempo.size() - 1));
					} else {
						possuiAoMenosUmaSaida = true;
						temp = new ArrayList<>();
						temp.add(arestasChave.get(i));
						temp.add(arestasChave.get(j));
						possiveisCiclos.add(temp);
					}
				}
			}

			//REVER CASO DE ERRO
			int retira = 0;
			if (possuiAoMenosUmaSaida == false){
				for (int k = 0; k < possiveisCiclos.size() - retira; k++){
					if (possiveisCiclos.get(k).contains(arestasChave.get(i))){
						possiveisCiclos.remove(k);
						retira++;
					}
				}
			}

			if (isEntrada == true && estaNoGrafo[i] == 1){
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

		if (isCiclico == true){//difArestas >= nVertices && isCiclico == true){
			//isCiclico = true;
			mensagem += "\nO grafo inserido é cíclico. Então não podemos fazer uma ordem "
					+ "topológica dele.\nModifique o arquivo para que o grafo seja não cíclico."
					+ "\nTente, não é difícil.";
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
			//reorganizar aqui
			if (tipoOrdenacao != null && tipoOrdenacao.equals(">") && listaEntrada.size() >=1){
				listaEntrada = reordenarMaior(listaEntrada);
			} else if (tipoOrdenacao != null && tipoOrdenacao.equals("<") && listaEntrada.size() >=1){
				listaEntrada = reordenarMenor(listaEntrada);
			}//se for diferente disso faz o padrão
			
			System.out.println("\nHá elementos na lista");
			//elOrdenados.add(listaEntrada.get(0));

			System.out.println("Lista de Entrada" + listaEntrada);
			valor = listaEntrada.get(0);
			System.out.println("1º Vértice da Lista de Entrada " + valor);

			for (int i = 0; i < grafo.getArestas().get(valor).size(); i++){
				temporario.add(grafo.getArestas().get(valor).get(i));
			}

			System.out.println("Lista de Vértices Conectados à Chave " + temporario);
			elOrdenados.add(valor);
			System.out.println("Elementos Parcialmente Ordenados " + elOrdenados);
			listaEntrada.remove(0);
			System.out.println("Removeu da lista de entrada o valor");
			System.out.println("Lista Entrada" + listaEntrada);


			for (int i = 0; i < temporario.size(); i++){
				aindaTem = false;
				System.out.println("Valor Temporário (próximo a tentar ser adicionado) " + temporario.get(i));
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
					System.out.println("Adiciona " + temporario.get(i) + " Lista de Entrada");
				}
			}

			temporario.clear();
		}
		return elOrdenados;
	}

	private List<Integer> reordenarMaior(List<Integer> listaAOrdenar){
		List<Integer> novaLista = new ArrayList<Integer>();
		novaLista.add(listaAOrdenar.get(0));
		for (int i = 1; i < listaAOrdenar.size(); i++){
			for (int j = 0; j < novaLista.size(); j++){
				if (listaAOrdenar.get(i) > novaLista.get(j)){
					novaLista.add(j, listaAOrdenar.get(i));
					break;
				}
			}
			
			if (!novaLista.contains(listaAOrdenar.get(i))){
				novaLista.add(listaAOrdenar.get(i));
			}
		}
		return novaLista;
	}

	private List<Integer> reordenarMenor(List<Integer> listaAOrdenar){
		List<Integer> novaLista = new ArrayList<Integer>();
		novaLista.add(listaAOrdenar.get(0));
		for (int i = 1; i < listaAOrdenar.size(); i++){
			for (int j = 0; j < novaLista.size(); j++){
				if (listaAOrdenar.get(i) < novaLista.get(j)){
					novaLista.add(j, listaAOrdenar.get(i));
					System.out.println("NOva " + novaLista);
					break;
				}
			}
			
			if (!novaLista.contains(listaAOrdenar.get(i))){
				novaLista.add(listaAOrdenar.get(i));
			}
		}
		
		return novaLista;
	}
}

