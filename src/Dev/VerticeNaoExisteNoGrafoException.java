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
		super("A formata��o do arquivo n�o est� correta.\nExiste ao menos uma aresta ligado a um v�rtice no arquivo usado que n�o "
				+ "est� definido nos v�rtices."
				+ "\nDsesconsideramos, mas s� dessa vez... Mentira, se rodar de novo funciona. Mas corrija.\n\n");
	}	
}
