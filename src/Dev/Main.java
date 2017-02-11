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
		String imprimir;
		String vertices = "";

		FileReader fr;
		BufferedReader br = null;

		try {
			fr = new FileReader(caminhoArquivo);
			br = new BufferedReader(fr);
			conteudo = br.readLine();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado. Verifique se \'caminhoArquivo\' "
					+ "aponta para o caminho de arquivo existente.");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}

		No no;
		conteudoRepartido = conteudo.split(";");

		//Nessa etapa é capturado todos os nós/vértice do grafo e seu conteúdo
		imprimir = "Ações: \n";
		for (int i = 0; i < conteudoRepartido.length; i++){
			no = new No();
			try{
				no.setChave(Integer.parseInt(conteudoRepartido[i].split(":")[0]));
			} catch (NumberFormatException e) {
				System.out.println("Formatação do documento está comprometida.");
			}

			no.setValor((conteudoRepartido[i].split(":")[1]).replace("\"", ""));

			vertices += "-" + no.getChave() + "-";
			imprimir += no.getChave() + " " + no.getValor() + "\n";

			grafo.setVertice(no);
			grafo.setArestas(no.getChave(), new ArrayList<Integer>());
		}

		try {
			conteudo = br.readLine();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}

		conteudoRepartido = conteudo.split(";");

		//Nessa parte é capturado as arestas do grafo
		imprimir += "\nArestas: \n";
		VerticeNaoExisteNoGrafoException e = null;
		for (int i = 0; i < conteudoRepartido.length; i++){
if (vertices.contains("-" + conteudoRepartido[i].split("->")[0] + "-")
					&& vertices.contains("-" + conteudoRepartido[i].split("->")[1] + "-")){
				grafo.setArestaAdj(Integer.parseInt(conteudoRepartido[i].split("->")[0]),
						Integer.parseInt(conteudoRepartido[i].split("->")[1]));

				imprimir += Integer.parseInt(conteudoRepartido[i].split("->")[0]) + " -> ";
				imprimir += Integer.parseInt(conteudoRepartido[i].split("->")[1]) + "\n";
			} else {
				if (e == null){
					e = new VerticeNaoExisteNoGrafoException();
					System.out.println(e.getMessage());
					break;
				}
			}
		}
		
		try {
			conteudo = br.readLine();
		} catch (IOException e1) {
			System.out.println("Erro na leitura do arquivo");
		}

		System.out.println("Conteúdo " + conteudo);
		System.out.println(imprimir);

		Algoritmo ordenacao = new Algoritmo(grafo, conteudo);

		mensagem = ordenacao.isCiclico();
		System.out.println(mensagem);

		if (ordenacao.isCiclico == false){
			listaOrdenada = ordenacao.ordenacaoTopologica();
			System.out.println("\n\nOrdenação topológica: " +  listaOrdenada);
			for (int i = 0; i < listaOrdenada.size(); i++){
				for (int j = 0; j < listaOrdenada.size(); j++){
					if (grafo.getVertices().get(j).getChave() == listaOrdenada.get(i)){
						System.out.println((i+1) + "º " + grafo.getVertices().get(j).getValor());
						break;
					}
				}
			}
		}
	}
}