public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			ListaDuplamenteLigadaCircularOrdenadaSemRepeticao<String> nomes;
			nomes = new ListaDuplamenteLigadaCircularOrdenadaSemRepeticao<String>();

			nomes.insira ("a");
			System.out.println(nomes.toStringInverso());
			nomes.insira ("b");
			System.out.println(nomes.toStringInverso());
			nomes.insira ("c");	
			System.out.println(nomes.toStringInverso());

			 nomes.remova("b");
			 System.out.println(nomes);

			 nomes.remova("a");
			 System.out.println(nomes);

			 nomes.remova("c");
			 System.out.println(nomes);
		}
		catch (Exception erro)
		{
			System.err.println (erro.getMessage());
		}
	}
}