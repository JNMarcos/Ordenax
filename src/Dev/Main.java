/**
 * 
 */
package Dev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JN
 *
 */
public class Main {

	public static void main(String[] pelosPoderesDeGreiscolkkk){

		String conteudo = "";
		String[] conteudoRepartido;
		String caminhoArquivo = "grafo.txt";
		Grafo grafo = new Grafo();
		
		List<Integer> listaOrdenada;

		String mensagem;

		FileReader fr;
		BufferedReader br = null;

		try {
			fr = new FileReader(caminhoArquivo);
			br = new BufferedReader(fr);
			conteudo = br.readLine();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado. Verifique se \'caminhoArquivo\' "
					+ "aponta para o caminho de arquivo existente.");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}

		No no;
		conteudoRepartido = conteudo.split(";");

		//Nessa etapa � capturado todos os n�s/v�rtice do grafo e seu conte�do
		System.out.println("A��es: ");
		for (int i = 0; i < conteudoRepartido.length; i++){
			no = new No();
			no.setChave(Integer.parseInt(conteudoRepartido[i].split(":")[0]));
			no.setValor((conteudoRepartido[i].split(":")[1]).replace("\"", ""));

			System.out.print(no.getChave() + " ");
			System.out.println(no.getValor());

			grafo.setVertice(no);
			grafo.setArestas(no.getChave(), new ArrayList<Integer>());
		}

		try {
			conteudo = br.readLine();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}

		conteudoRepartido = conteudo.split(";");

		//Nessa parte � capturado as arestas do grafo
		System.out.println("\nArestas: ");
		for (int i = 0; i < conteudoRepartido.length; i++){
			grafo.setArestaAdj(Integer.parseInt(conteudoRepartido[i].split("->")[0]),
					Integer.parseInt(conteudoRepartido[i].split("->")[1]));

			System.out.print(Integer.parseInt(conteudoRepartido[i].split("->")[0]) + " -> ");
			System.out.println(Integer.parseInt(conteudoRepartido[i].split("->")[1]));
		}

		Algoritmo ordenacao = new Algoritmo(grafo);

		mensagem = ordenacao.isCiclico();
		System.out.println(mensagem);

		if (ordenacao.isCiclico == false){
			listaOrdenada = ordenacao.ordenacaoTopologica();
			System.out.println("\n\nOrdena��o topol�gica: " +  listaOrdenada);
			for (int i = 0; i < listaOrdenada.size(); i++){
				for (int j = 0; j < listaOrdenada.size(); j++){
					if (grafo.getVertices().get(j).getChave() == listaOrdenada.get(i)){
						System.out.println((i+1) + "� " + grafo.getVertices().get(j).getValor());
						break;
					}
				}
			}
		}
	}
}