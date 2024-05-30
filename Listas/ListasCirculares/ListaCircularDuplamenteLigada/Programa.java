public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			ListaCircularDuplamenteLigada<String> nomes;
			nomes = new ListaCircularDuplamenteLigada<String> ();
			nomes.insiraNoComeco ("Adriana");
			System.out.println(nomes);

			nomes.insiraNoComeco ("Aquidauana");
   			System.out.println(nomes);
			nomes.insiraNoComeco ("Joana");
			System.out.println(nomes);
			nomes.insiraNoComeco ("Aquidauana");
			System.out.println(nomes);
			nomes.insiraNoComeco ("Aquidauana");
			System.out.println(nomes);
		    nomes.insiraNoComeco ("Aquidauana");
			System.out.println(nomes);
			nomes.insiraNoFim ("Aquidauana");
			System.out.println(nomes);
			nomes.insiraNoFim ("Aquidauana");
			System.out.println(nomes);
		    nomes.insiraNoFim ("Aquidauana");
			System.out.println(nomes);
			//System.out.println(nomes.toStringInverso());


			if (nomes.tem("Aquidauana"))
				System.out.println("TEMOS ISSO!!");

			nomes.remova("Aquidauana");
			System.out.println(nomes);
			nomes.remova("Joana");
			System.out.println(nomes);
			nomes.remova("Adrian");
			System.out.println(nomes);

		}
		catch (Exception erro)
		{
			System.err.println (erro);
		}
	}
}