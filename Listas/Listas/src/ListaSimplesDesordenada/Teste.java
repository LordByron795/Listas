package ListaSimplesDesordenada;

public class Teste
{
    public static void main (String[] args)
    {
        try
        {
            ListaDesordenadaSimples<Integer> lista = new ListaDesordenadaSimples<Integer> ();

            System.out.println  (lista);

            lista.insiraNoComeco(4);
            System.out.println  (lista);

            /*lista.insiraNoComeco(3);
            /System.out.println  (lista);

            lista.insiraNoComeco(2);
            System.out.println  (lista);

            lista.insiraNoComeco(1);
            System.out.println  (lista);

            lista.insiraNoComeco(0);
            System.out.println  (lista);

            lista.insiraNoFim  (5);
            System.out.println (lista);

            lista.insiraNoFim  (6);
            System.out.println (lista);*/

            lista.insiraNoFim  (1);
            System.out.println (lista);

            lista.insiraNoFim  (8);
            System.out.println (lista);

            lista.insiraNoFim  (2);
            System.out.println (lista);

            /*if (lista.tem (0))
                System.out.println ("Tem 0");
            else
                System.out.println ("Não tem 0");

            if (lista.tem (1))
                System.out.println ("Tem 1");
            else
                System.out.println ("Não tem 1");

            if (lista.tem (2))
                System.out.println ("Tem 2");
            else
                System.out.println ("Não tem 2");

            if (lista.tem (3))
                System.out.println ("Tem 3");
            else
                System.out.println ("Não tem 3");

            if (lista.tem (4))
                System.out.println ("Tem 4");
            else
                System.out.println ("Não tem 4");

            if (lista.tem (5))
                System.out.println ("Tem 5");
            else
                System.out.println ("Não tem 5");

            if (lista.tem (6))
                System.out.println ("Tem 6");
            else
                System.out.println ("Não tem 6");

            if (lista.tem (7))
                System.out.println ("Tem 7");
            else
                System.out.println ("Não tem 7");

            if (lista.tem (8))
                System.out.println ("Tem 8");
            else
                System.out.println ("Não tem 8");

            if (lista.tem (9))
                System.out.println ("Tem 9");
            else
                System.out.println ("Não tem 9");

            if (lista.tem (10))
                System.out.println ("Tem 10");
            else
                System.out.println ("Não tem 10");

            lista.remova       (0);
            System.out.println (lista);

            lista.remova       (3);
            System.out.println (lista);

            lista.remova       (6);
            System.out.println (lista);

            lista.remova       (9);
            System.out.println (lista);*/
            
            lista.OrdeneSe();
            System.out.println(lista);
            
            Listona list2 = new Listona();
            
            	System.out.println(list2.getQtdDoChar('a'));
           
            
        }
        catch (Exception erro)
        {
            System.err.println (erro);
        }
    }
}