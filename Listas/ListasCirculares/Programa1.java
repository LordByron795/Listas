public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			ListaCircularSimples<String> nomes;
			nomes = new ListaCircularSimples<String> ();
			nomes.insiraNoComeco ("Adriana");
			System.out.println(nomes);
			nomes.insiraNoComeco ("Joana");
			System.out.println(nomes);
			nomes.insiraNoComeco ("Aquidauana");
			System.out.println(nomes);
			ListaCircularSimples<String> nomes2  =(ListaCircularSimples<String>) nomes.clone();


			System.out.println("nomes2: "+nomes2);
			/*nomes.insiraNoFim ("Bianca");
			System.out.println(nomes);
			nomes.insiraNoFim ("Luana");
			System.out.println(nomes);*/

/**			if (nomes.tem("Carina"))
				System.out.println("Tem Carina"); //<<<
			else
				System.out.println("Nao tem Carina");
 **/
			//nomes.invertaSe ();
			System.out.println(nomes);

			if(nomes2.equals(nomes))
				System.out.println("Jóia");
			else	
				System.out.println("Pifou");

			System.out.println(nomes.hashCode());	
/**	
			if (nomes.tem("Carina"))
				System.out.println("Tem Carina");
			else
				System.out.println("Nao tem Carina"); //<<<* **/
		}
		catch (Exception erro)
		{
			System.err.println (erro);
		}
	}
}