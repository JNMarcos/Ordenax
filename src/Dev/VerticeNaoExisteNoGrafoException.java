/**
 * 
 */
package Dev;

/**
 * @author JN
 *
 */
public class VerticeNaoExisteNoGrafoException extends Exception{

	private static final long serialVersionUID = 1L;
	public VerticeNaoExisteNoGrafoException(){
		super("A formatação do arquivo não está correta.\nExiste ao menos uma aresta ligado a um vértice no arquivo usado que não "
				+ "está definido nos vértices."
				+ "\nDsesconsideramos, mas só dessa vez... Mentira, se rodar de novo funciona. Mas corrija.\n\n");
	}	
}
