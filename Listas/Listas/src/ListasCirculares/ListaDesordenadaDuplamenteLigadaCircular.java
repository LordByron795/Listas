package ListasCirculares;
import java.lang.reflect.*;

public class ListaDesordenadaDuplamenteLigadaCircular <X extends Comparable<X>> implements Cloneable
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
        	ret = this.getAnte() + "," + this.getProx()+ "," + this.getProx() + ",";
        	else 
        		ret = this.getInfo()+"";
        	
        	ret = "}";
        	return ret;
        }

        public int hashCode ()
        {
        	int ret = super.hashCode();
        	
        	ret = ret * 13 + this.getAnte().hashCode();
        	ret = ret * 13 + this.getInfo().hashCode();
        	ret = ret * 13 + this.getProx().hashCode();
        	
        	return ret;
        }

        public No (No modelo) throws Exception
        {
        	if(modelo==null)
        		throw new Exception ("Modelo ausente");
        	
        	if(this.getProx()==null)
        		this.setInfo(modelo.getInfo());
        	else 
        		this.setInfo(modelo.getInfo());
        	    this.setProx(new No(modelo.getProx()));
        	
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

    
	private No ulti,prim;

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
     if(this.prim.getProx()==this.ulti)
     {
    	 this.prim = null;
    	 this.ulti = this.prim;
     }
     else
     {
    	
    	this.prim = this.prim.getProx();
    	this.prim.setAnte(this.ulti);
    	this.ulti.setProx(this.prim);
     }
    }

    public void removaUltimo ()
    {
    	if(this.prim==null)
    		System.out.println("Lista vazia!");
    	
    	if(this.prim.getProx()==this.ulti)
    	{
    		this.prim = null;
    		this.ulti = this.prim;
    	}
    	else
    	{
    	  this.ulti = this.ulti.getAnte();
    	  this.ulti.setProx(this.prim);
    	  this.prim.setAnte(this.ulti);
    	}
    }

    public void remova (X i) throws Exception
    {
    	boolean removeu = false;
    	
    	while(this.prim!=null && this.prim.getInfo().equals(i))
    	{
    		this.prim = this.prim.getProx();
    		this.prim.setAnte(this.ulti);
    		this.ulti.setProx(this.prim);
    		removeu = true;
    		
    	}
    	No atual=this.prim;
    	while(this.prim!=null)
    	{
    		while(atual.getProx()!=this.prim && !atual.getProx().getInfo().equals(i))
    			atual = atual.getProx();
    		
    		if(atual.getProx().getInfo().equals(i))
    		{
    			atual.setProx(atual.getProx().getProx());
    			atual.getProx().setAnte(atual);
    			removeu = true;
    		}
    		else 
    			break;
    	}
    	if(!removeu)
    		throw new Exception("Informação inexistente!");
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
        No atual2;
        for (atual2=this.ulti; atual2.getAnte()!=this.ulti; atual2=atual2.getAnte())
            resp += atual2.getInfo()+",";

        resp += atual2.getInfo();

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

    
    public int hashCode ()
    {
    	int ret = super.hashCode();
    	
    	for(No atual=this.prim;atual.getProx()!=this.prim;atual=atual.getProx())
    		ret = ret * 13 + atual.hashCode();
    	return ret;
    }

    public ListaDesordenadaDuplamenteLigadaCircular (ListaDesordenadaDuplamenteLigadaCircular<X> modelo) throws Exception
    {
    	if(modelo==null)
    		throw new Exception("Modelo ausente");
    	
    	if(modelo.prim==null)
    		return;
    	
    	No atuMod,atuThis;
    	for(atuMod=modelo.prim.getProx(),atuThis=this.prim;atuMod!=modelo.prim;atuMod=atuMod.getProx(),atuThis=atuThis.getProx())
    		if(atuMod.getInfo() instanceof Cloneable)
    			atuMod.setProx(new No(this.meuCloneDeX(atuMod.getInfo())));
    		else 
    			atuMod.setProx(new No(atuMod.getInfo()));
    	
    	if(atuMod.getProx().equals(modelo.prim))
    	{
    		if(atuMod.getProx() instanceof Cloneable)
    			atuMod.setProx(new No(this.meuCloneDeX(atuMod.getInfo())));
    		else
    			atuMod.setProx(new No(atuMod.getInfo()));
    	}
    }

    public Object clone ()
    {
    	ListaDesordenadaDuplamenteLigadaCircular ret = null;
    	
    	try
    	{
    		ret = new ListaDesordenadaDuplamenteLigadaCircular(this);
    	}
    	catch(Exception erro)
    	{}
    	return ret;
    }

    public void invertaSe ()
    {

    	if(this.prim==null)
    		return;
    	if(this.prim.getProx()==this.prim)
    		return;
    	
    	No anteAtu=this.ulti,
    	   atual = this.prim,
    	   proxAtu = this.prim.getProx();
    	
    	for(;;)
    	{
    		atual.setProx(anteAtu);
    		atual.setAnte(proxAtu);
    		anteAtu=atual;
    		atual=proxAtu;
    		
    		if(atual==this.prim)
    			break;
    		
    		proxAtu = proxAtu.getProx();
    	}
    	this.prim = anteAtu;
    	this.ulti = proxAtu;
    }

    public void insiraApos (X oqInserir, X aposOqInserir) throws Exception
    {
    	
			if(oqInserir.equals(null))
			throw new Exception ("Informação não fornecida");
					if(this.prim==null)
					{
						throw new Exception("Lista Sem Informação");
					}
					boolean inseriu = false;
					No novo = new No(oqInserir);
					No atual;
			       for (atual=this.prim; atual.getProx()!=null && !inseriu; atual=atual.getProx())
			       {
					   if(atual.getProx().getInfo().equals(aposOqInserir))
					   {
							No aux = atual.getProx();
							atual.setProx(novo);
							novo.setProx(aux);
							novo.setAnte(atual);
							aux.setAnte(novo);
	
							inseriu = true;
					   }
				   }
				   if(!inseriu)
				   throw new Exception ("A informação não existe na Lista");
        } 
    
    public void TornarOrdenada() throws Exception
    {
    	if(this.prim==null)
    		throw new Exception("Lista vazia");
    
    	No i,j;
    	
    	for(i=this.prim;i.getProx()!=this.prim;i = i.getProx()) {
    		for(j=this.prim;j.getProx().getProx()!=this.prim;j=j.getProx()) {
    			
    			if(j.getInfo().compareTo(j.getProx().getInfo())>0)
    			{
    				No aux = j;
    				j = j.getProx();
    				j.setProx(aux);
    				
    			}
    		}
    	}
    }
    
}
