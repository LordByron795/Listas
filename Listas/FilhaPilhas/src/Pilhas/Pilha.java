package Pilhas;

public interface Pilha <X>
{
    public void guarde    (X x) throws Exception;
    public X    espie     ()    throws Exception;
    public void jogueFora ()    throws Exception;
}