package ListasCircularesDuplamenteLigadas;
import java.lang.reflect.*;
public class ListaDesordenadaDuplamenteLigadaCircular <X> implements Cloneable
{
    private class No
    {
        private No ante;
        private X  info;
        private No prox;

        public No (No a, X i, No p)
        {
            this.ante = a;
            this.info = i;
            this.prox = p;
        }

        public No (X i)
        {
            this (null,i,null);
        }

        public void setAnte (No a)
        {
            this.ante = a;
        }

        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setProx (No p)
        {
            this.prox = p;
        }

        public No getAnte ()
        {
            return this.ante;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

        public boolean equals (Object obj)
        {
            if (this==obj)
                return true;

            if (obj==null)
                return false;

            if (this.getClass()!=obj.getClass())
                return false;

            No no = (No)obj;

            if (!this.info.equals(no.info))
                return false;

            return true;
        }

        
        public String toString ()
        {
        	String ret = "{";
        	if(this.prox!=null)
        		ret = this.ante +"," + this.info+ "," + this.prox;
        	else 
        		ret = this.info+"";
        	return ret;
        }

        public int hashCode ()
        {
        	int ret = super.hashCode();
        	
        	ret = ret * 13 + this.prox.getInfo().hashCode();
        	
        	return ret;
        }

        public No (No modelo) throws Exception
        {
        	if(modelo==null)
        		throw new Exception("Modelo não fornecido!");
        	if(modelo.getProx()==null)
        		modelo.setInfo(modelo.getInfo());
        	else 
        	{
        		modelo.setInfo(modelo.getInfo());
        		modelo.setProx(new No(modelo.getInfo()));
        	}
        }

        public Object clone ()
        {
        	No ret = null;
        	
        
        	try 
        	{
        	   ret = new No(this);	
        	}
        	catch(Exception erro)
        	{}
        	return ret;
        }
       /* */
    }

    private No prim, ulti;

    public ListaDesordenadaDuplamenteLigadaCircular ()
    {
        this.prim = null;
        this.ulti = null;
    }

    private X meuCloneDeX (X modelo)
    {
      /*
      String s = "COTUCA";
      char   c;

      c = s.charAt(0); // poe 'C' em c
      // a linha acima e as linhas abaixo fazem a mesma coisa
      Class<?> classe = s.getClass();
      Integer parametro=0;
      Class<?>[] tiposDosParametrosFormais = new Class<?>[1]; // 1 porque charAt tem 1 parametro
      tiposDosParametrosFormais[0] = parametro.getClass();
      Method metodo = classe.getMethod ("charAt", tiposDosParametrosFormais);
      Object[] parametrosReais = new Object [1]; // 1 porque charAt tem 1 parametro
      parametrosReais[0] = parametro;
      c = ((Character)metodo.invoke (s, parametrosReais)).charValue();
      */
      //return modelo.clone();

        X ret=null;

        try
        {
            Class<?> classe = modelo.getClass();
            Class<?>[] tiposDosParametrosFormais = null;
            Method metodo = classe.getMethod ("clone", tiposDosParametrosFormais);
            Object[] parametrosReais = null;
            ret = (X)metodo.invoke (modelo, parametrosReais);
        }
        catch (NoSuchMethodException erro)
        {}
        catch (InvocationTargetException erro)
        {}
        catch (IllegalAccessException erro)
        {}

        return ret;
    }

    public void insiraNoComeco (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        if (i instanceof Cloneable)
            this.prim = new No (null,this.meuCloneDeX(i),this.prim);
        else
            this.prim = new No (null,i,this.prim);

        if (this.prim.getProx()==null)
            this.ulti = this.prim;
        else
            this.prim.getProx().setAnte(prim);

        this.prim.setAnte (this.ulti);
        this.ulti.setProx (this.prim);
    }

    public void insiraNoFim (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        if (this.prim==null)
        {
            if (i instanceof Cloneable)
                this.prim = new No (this.meuCloneDeX(i));
            else
                this.prim = new No (i);

            this.ulti = this.prim;
        }
        else
        {
            if (i instanceof Cloneable)
                this.ulti.setProx (new No (this.ulti,this.meuCloneDeX(i),null));
            else
                this.ulti.setProx (new No (this.ulti,i,null));

            this.ulti = this.ulti.getProx();
        }

        this.prim.setAnte (this.ulti);
        this.ulti.setProx (this.prim);
    }

    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        if (this.prim==null)
            return false;

        No atual;

        for (atual=this.prim;atual.getProx()!=this.prim;atual=atual.getProx())
             if (i.equals(atual.getInfo()))
                return true;
 
        // o for acima nao examina o ultimo no
        // por issso estou o examinando aqui
        if (i.equals(atual.getInfo()))
            return true;

        return false;
    }

    public void removaPrimeiro ()
    {
    	if(this.prim==null)
    		System.out.println("Lista fazia!");
    	if(this.prim.getProx()==null)
    	{
    		this.prim = null;
    		this.ulti = this.prim;
    	}
    	else 
    	{
    		
    		this.prim.getProx().setAnte(this.ulti);
    		this.prim = this.prim.getProx();
    		this.ulti.setProx(this.prim);
    	}
    }

    public void removaUltimo ()
    {
    	if(this.prim==null)
    		System.out.println("Lista sem informação!");
    	if(this.prim.getProx()==null)
    	{
    		this.prim = null;
    		this.ulti = this.prim;
    		this.prim.setAnte(ulti);
    		this.ulti.setProx(prim);
    	}
    }

    public void remova (X i) throws Exception
    {
    }

    public String toString ()
    {
        if (this.prim==null)
            return "{}";

        String resp="{";

        No atual;

        for (atual=this.prim; atual.getProx()!=this.prim; atual=atual.getProx())
            resp += atual.getInfo()+",";

        resp += atual.getInfo();

        resp+="}\n{";
        No atu;
        for (atu=this.ulti; atu.getAnte()!=this.ulti; atu=atu.getAnte())
            resp += atu.getInfo()+",";

        resp += atu.getInfo();

        resp+="}";

        return resp;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaDesordenadaDuplamenteLigadaCircular<X> lista =
        (ListaDesordenadaDuplamenteLigadaCircular<X>)obj;

        if (this.prim==null && lista.prim==null)
            return true;

        if ((this.prim==null && lista.prim!=null) ||
            (this.prim!=null && lista.prim==null))
            return false;

        No noThis =this .prim,
           noLista=lista.prim;

        while (noThis .getProx()!=this .prim &&
               noLista.getProx()!=lista.prim)
        {
            if (!noThis.equals(noLista))
                return false;

            noThis  = noThis .getProx();
            noLista = noLista.getProx();
        }

        if (noThis .getProx()!=this .prim ||
            noLista.getProx()!=lista.prim)
            return false;
            
        if (!noThis.equals(noLista))
            return false;

        return true;
    }

    /*
    public int hashCode ()
    {
    }

    public ListaDesordenadaDuplamenteLigadaCircular (ListaDesordenadaDuplamenteLigadaCircular<X> modelo) throws Exception
    {
    }

    public Object clone ()
    {
    }

    public void invertaSe ()
    {
    }

    public void insiraApos (X oqInserir, X aposOqInserir) throws Exception
    {
    }
    */
}