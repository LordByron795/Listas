package Pilhas;

public class PilhasEmVetor <X> implements Pilha<X>,
                                         Cloneable
{
    private Object[] vetor;
    private int      ultimo;

    public PilhasEmVetor (int capacidade) throws Exception
    {
        // validar capacidade, dando excecao se for o caso
        // dimensionar this.vetor
        // fazer this.ultimo valer -1
    }

    public void guarde (X x) throws Exception
    {
        // excecao quando nao cabe
        // guardar x de forma que ele seja
        // recuperado nos proximos espie()
        // ate que seja removido pelo
        // proximo jogueFora ou ate outra
        // chamada de guarde(algo) acontecer
    }

    public X espie () throws Exception
    {
        // excecao quando nao ha nada guardado
        // retorna, SEM REMOVER, a ultima
        // coisa guardada
    	return null;
    }

    public void jogueFora () throws Exception
    {
        // excecao quando nao ha nada guardado
        // joga fora a ultima coisa guardada
    }

    // fazer TOOOOODOOOOOS os metodos apocalipticos
}