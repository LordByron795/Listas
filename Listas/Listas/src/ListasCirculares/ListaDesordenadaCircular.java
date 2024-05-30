package ListasCirculares;

import java.lang.reflect.*;

public class ListaDesordenadaCircular <X> implements Cloneable
{
    private class No
    {
        private X  info;
        private No prox;

        public No (X i, No p)
        {
            this.info = i;
            this.prox = p;
        }

        public No (X i)
        {
            this (i,null);
        }

        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setProx (No p)
        {
            this.prox = p;
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
        	String ret;

   		 if(this.prox != null)
   			ret = this.info+","+this.prox;
   		else
   		ret = this.info+"";

   		return ret;
        }

        public int hashCode ()
        {
        	int ret = super.hashCode();
        	
        	ret = ret * 13 + this.getProx().getInfo().hashCode();
        	
        	return ret;
        }

        public No (No modelo) throws Exception
        {
        if(modelo==null)
        	throw new Exception("Modelo não fornecido");
        
         if(modelo.getProx()==null)
        	 this.setInfo(modelo.getInfo());
         
         else 
         {
        	 this.setInfo(modelo.getInfo());
        	 this.setProx(new No(modelo.getInfo()));
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
        
    }

    No prim;

    public ListaDesordenadaCircular ()
    {
        this.prim = null;
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

        if (this.prim==null)
        {
            if (i instanceof Cloneable)
                this.prim = new No (this.meuCloneDeX(i));
            else
                this.prim = new No (i);

            this.prim.setProx (this.prim);
        }
        else
        {
            No ultimo;

            for (ultimo=this.prim; ultimo.getProx()!=this.prim; ultimo=ultimo.getProx())
                ;

            if (i instanceof Cloneable)
                this.prim = new No (this.meuCloneDeX(i), this.prim);
            else
                this.prim = new No (i, this.prim);

            ultimo.setProx (this.prim);
        }
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

            this.prim.setProx (this.prim);
        }
        else
        {
            No ultimo;

            for (ultimo=this.prim; ultimo.getProx()!=this.prim; ultimo=ultimo.getProx())
                ;

            if (i instanceof Cloneable)
                ultimo.setProx (new No (this.meuCloneDeX(i),this.prim));
            else
                ultimo.setProx (new No (i,this.prim));
        }
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
    		System.out.println("Informações ausentes");
    	
    	No ultimo;
    	for (ultimo=this.prim;ultimo.getProx()!=this.prim;ultimo=ultimo.getProx());
    	
    	if(this.prim.getProx().equals(ultimo))
    	{
    		this.prim = null;
    		ultimo.setProx(this.prim);
    	}
    	else 
    	{
    		this.prim = this.prim.getProx();
    		ultimo.setProx(this.prim);
    	}
    }

    public void removaUltimo ()
    {
       if(this.prim==null)
    	   System.out.println("Informações ausentes");
       
       No ultimo;
       for(ultimo=this.prim;ultimo.getProx().getProx()!=this.prim;ultimo=ultimo.getProx());
          if(ultimo.getProx().equals(prim))      
             this.prim=null;          
          else       
    	    ultimo.setProx(this.prim);             
    }

    public void remova (X i) throws Exception
    {
    	boolean removeu = false;   	
    	No ultimo;
    	
    	
    	for(ultimo=this.prim;ultimo.getProx()!=this.prim;ultimo=ultimo.getProx());
    	
    	if(this.prim.getInfo().equals(i))
    	{
    		this.prim = this.prim.getProx();
    		ultimo.setProx(this.prim);
    		removeu = true;
    	}
    	else 
    	{
    		No atual = this.prim;
    		while(atual!=null) 
    		{    			
    			while(atual.getProx()!=this.prim && !atual.getProx().getInfo().equals(i))
    				atual = atual.getProx();
    			
    			if(atual.getProx().getInfo().equals(i))
    			{
    				atual.setProx(atual.getProx().getProx());
    				removeu = true;
    			}
    			else 
    				break;
    		}
    	    if(!removeu)
    	    {
    	  	   throw new Exception("Informação inexistente!"); 
    	    }
    	}
    	    	
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

        ListaDesordenadaCircular<X> lista =
        (ListaDesordenadaCircular<X>)obj;

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

	   for (No atual=this.prim; atual!=null; atual=atual.getProx())
        ret =13*ret + atual.getInfo().hashCode();

     return ret;
    }

      public ListaDesordenadaCircular (ListaDesordenadaCircular<X> modelo) throws Exception
    {
    	  if(modelo==null)
    		  throw new Exception("Modelo ausente");
    	  
    	  if(modelo.prim==null)
    		  return;
    	  No atuMod,atuThis;
    	  for(atuMod=modelo.prim.getProx(),atuThis=this.prim;atuMod!=modelo.prim;atuMod=atuMod.getProx(),atuThis=atuThis.getProx())
    		  if(atuMod.getInfo() instanceof Cloneable)
    			  atuThis.setProx(new No(this.meuCloneDeX(atuMod.getInfo())));
    		  else 
    			  atuThis.setProx(new No(atuMod.getInfo()));
    	  
    	  if(atuMod.getProx().equals(modelo.prim)) 
    	  {
    		  if(atuMod.getInfo() instanceof Cloneable)
    			  atuThis.setProx(new No(this.meuCloneDeX(atuMod.getInfo())));
    		  else
    			  atuThis.setProx(new No(atuMod.getInfo()));
    	  }
    	  
    }

    public Object clone ()
    {
    	ListaDesordenadaCircular ret = null;
    	try
    	{
    		ret = new ListaDesordenadaCircular(this);
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
    	
    	No anteAtu,
    	   atual = this.prim,
    	   proxAtu = this.prim.getProx();
    	for(anteAtu=this.prim;anteAtu.getProx()!=this.prim;anteAtu=anteAtu.getProx());
    	
    	for(;;)
    	{
    		atual.setProx(anteAtu);
    		anteAtu=atual;
    		atual=proxAtu;
    		
    		if(atual==this.prim)
    			break;
    		
    		proxAtu = proxAtu.getProx();
    	}
    	this.prim = anteAtu;
    }

    public void insiraApos (X oqInserir, X aposOqInserir) throws Exception
    {
    	if(this.prim == null)
    		return;
    	No novo = new No(oqInserir);
    	No atual;
        boolean inseriu=false;
    	
    	for(atual = this.prim;atual.getProx() != this.prim; atual = atual.getProx())
    		if(atual.getProx().getInfo().equals(aposOqInserir)) 
    		{
              No proxatu = atual.getProx();            
    			atual.setProx(novo);
    			novo.setProx(proxatu);
    			inseriu = true;
    		}
    		else 
    		{
    		   if(atual.getProx()==this.prim)
    			   atual.setProx(novo);
    		       this.prim=novo;
    		       inseriu = true;
    		}
    	
    	if(!inseriu)
    		throw new Exception("Informação inexistente");
    }
    

}








