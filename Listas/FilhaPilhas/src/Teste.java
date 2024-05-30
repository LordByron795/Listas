import Pilhas.Pilha;
import Pilhas.PilhasEmLista;

public class Teste {

	public static void main(String[] args)
	{
		try 
		{
			PilhasEmLista pilha = new  PilhasEmLista();
			pilha.guarde(1);
			System.out.println(pilha);
			
			pilha.guarde(2);
			System.out.println(pilha);
			
			pilha.guarde(3);
			System.out.println(pilha);
			
			pilha.jogueFora();
			System.out.println(pilha);
			
			pilha.espie();
			System.out.println(pilha);
			
		}
		catch(Exception erro)
		{
			System.err.println(erro);
		}
	}
	
}
