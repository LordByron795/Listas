public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			ListaDesordenada<String> nomes;
			nomes = new ListaDesordenada<String> ();

			System.out.println(nomes);
			nomes.insiraNoFim ("Adriana");
			System.out.println(nomes);
			nomes.insiraNoFim ("Natalia");
			System.out.println(nomes);
			nomes.insiraNoFim ("Carina");
			System.out.println(nomes);
			nomes.insiraNoFim ("Bianca");
			System.out.println(nomes);
			nomes.insiraNoFim ("Luana");
			System.out.println(nomes);

			if (nomes.tem("Carina"))
				System.out.println("Tem Carina"); //<<<
			else
				System.out.println("Nao tem Carina");

			nomes.remova ("Carina");

			if (nomes.tem("Carina"))
				System.out.println("Tem Carina");
			else
				System.out.println("Nao tem Carina"); //<<<
		}
		catch (Exception erro)
		{
			System.err.println (erro.getMessage());
		}
	}
}