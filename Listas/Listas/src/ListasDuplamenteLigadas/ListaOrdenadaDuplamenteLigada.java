package ListasDuplamenteLigadas;

import java.lang.reflect.*;

public class ListaOrdenadaDuplamenteLigada <X extends Comparable<X>> implements Cloneable
{
    private class No implements Cloneable
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
        	String resp = "{";
        			
        			if(this.prox != null)
        				resp = this.ante+ "," + this.info+  "," + this.prox;
        			else 
        				resp = this.info+"";
        			
        			return resp;
        }
        
        
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((ante == null) ? 0 : ante.hashCode());
			result = prime * result + ((info == null) ? 0 : info.hashCode());
			result = prime * result + ((prox == null) ? 0 : prox.hashCode());
			return result;
		}
        
        private ListaOrdenadaDuplamenteLigada getOuterType() {
			return ListaOrdenadaDuplamenteLigada.this;
		}
        
        public No (No modelo) throws Exception
        {
        	 if(modelo == null)
        		 throw new Exception("Modelo ausente");
        	 
        	 if(modelo.getProx() == null)
        		 this.setInfo(modelo.getInfo());
        	 else 
        		 this.setInfo(modelo.getInfo());
        	     this.setProx(new No(modelo.getProx()));
        	 
        }

        public Object clone ()
        {
        	No ret = null;
        	
        	try {
        		ret = new No(this);
        	}
        	catch(Exception erro) 
        	{}
        	return ret;
        }
    }

    private No prim, ulti;

    public ListaOrdenadaDuplamenteLigada ()
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
public void insira (X i) throws Exception
{ 
	if (i==null)
    throw new Exception ("Informacao ausente");

   if (this.prim==null)
     {
       this.prim = new No (i);
	   this.ulti = this.prim;
     }
   else
      {
        No ultimo;
	    boolean inseriu = false;
	    No novo = new No(i);
	if((this.prim.getInfo().compareTo(i)>0||this.prim.getInfo().equals(i)))
			{
				novo.setProx(this.prim);
				this.prim.setAnte(novo);
				this.prim =novo;
				inseriu = true;
			}
	 else
	{
   for (ultimo=this.prim; ultimo.getProx()!=null&&!inseriu; ultimo=ultimo.getProx())
    {
		if((ultimo.getInfo().compareTo(i)<0||ultimo.getInfo().equals(i)) && (ultimo.getProx().getInfo().compareTo(i)>0||ultimo.getProx().getInfo().equals(i)))
		{							
			No aux = ultimo.getProx();						
			ultimo.setProx(novo);
			novo.setProx(aux);
			novo.setAnte(ultimo);
			aux.setAnte(novo);
			inseriu = true;
		}

    }		
  
     if(ultimo.getInfo().compareTo(i)<0 && ultimo.getProx()==null)
     {
    	 ultimo.setProx(novo);
    	 novo.setAnte(this.ulti);
    	 novo.setProx(null);
    	 this.ulti = novo;
     }
				
	}
	        }
}

    public boolean tem (X i) throws Exception // melhorar
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        for (No atual=this.prim; atual!= null; atual=atual.getProx()) 
        {
        	
            if (i.equals(atual.getInfo()))
                return true;
        
            if(i.compareTo(atual.getInfo())<0)
        	   return false;
        }
        return false;
        	
        
    }

    public void removaPrimeiro ()
    {
    	if(this.prim== null)
    		System.out.println("Informação inexistente!");
    	if(this.prim.getProx()!= null) 
    	{
    	this.prim.getProx().setAnte(null);
    	this.prim = this.prim.getProx();
    	}
    	else
    	{
    		if(this.prim.getProx()==null) 
    		{
    		  this.prim = null;
    		  this.ulti = this.prim;
    		}
    	}
    	
    	
    }

    public void removaUltimo ()
    {
    	if(this.prim == null)
    		System.out.println("Informações inexistentes");
    	
    	if(this.ulti.getAnte() != null) 
    	{
    	   this.ulti.getAnte().setProx(null);
    	   this.ulti = this.ulti.getAnte();
    	}
    	else
    	{
    		if(this.ulti.getAnte()==null)
    		{
    			this.ulti = null;
    			this.prim = this.ulti;
    		}
    	}
    	
    }

    public void remova (X i) throws Exception // melhorar
    {
    	while(this.prim!= null && this.prim.getInfo().equals(i)) {
    		this.prim.getProx().setAnte(null);
    	    this.prim = this.prim.getProx();}
    	
    	No atual = this.prim;
        while(!(i.compareTo(atual.getInfo())<0)) 
        {
        	while(atual.getProx() != null && !atual.getProx().getInfo().equals(i)) 
        		atual = atual.getProx();
        	
        if(atual.getProx().getInfo().equals(i)) 
        {
        	atual.setProx(atual.getProx().getProx());
        	atual.getProx().setAnte(atual);
        	 
        	}
        	       
        else 
        	break;
        }
      
    }

    public String toString ()
    {
        String resp="{";

        for (No atual=this.prim; atual!=null; atual=atual.getProx())
            resp += atual.getInfo()+(atual.getProx()!=null?",":"");

        resp+="}\n{";

        for (No atual=this.ulti; atual!=null; atual=atual.getAnte())
            resp += atual.getInfo()+(atual.getProx()!=null?",":"");

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

        ListaOrdenadaDuplamenteLigada<X> lista =
        (ListaOrdenadaDuplamenteLigada<X>)obj;

        No noThis =this .prim,
           noLista=lista.prim;

        while (noThis!=null && noLista!=null)
        {
            if (!noThis.equals(noLista))
                return false;

            noThis  = noThis .getProx();
            noLista = noLista.getProx();
        }

        if (noThis!=null || noLista!=null)
            return false;

        return true;
    }

   
    public int hashCode ()
    {
    	int ret = super.hashCode();
    	
    	for(No atual= this.prim;atual!=null;atual=atual.getProx())
    		ret = ret * 13 + atual.getInfo().hashCode();
    	return ret;
    }

    private X meucloneDeX(X modelo)
    {
    	X ret = null;
    	try 
    	{
    		Class<?> classe = modelo.getClass();
    		Class[] tiposDeParametrosFormais = null;
    		Method metodo = classe.getMethod("clone",tiposDeParametrosFormais);
    		Object[] parametrosReais = null;
    		ret = (X)metodo.invoke(modelo, parametrosReais);
    	}
    	catch(NoSuchMethodException erro)
    	{}
    	catch(InvocationTargetException erro)
    	{}
    	catch(IllegalAccessException erro)
    	{}
    	return ret;
    	
    }
    
    public ListaOrdenadaDuplamenteLigada (ListaOrdenadaDuplamenteLigada<X> modelo) throws Exception
    {
    	if(modelo==null)
    		return;
    	if(modelo.prim==null)
    		return;
    	
    	for(No atuMetod= modelo.prim.getProx(),atuThis=this.prim;atuMetod!=null;atuMetod=atuMetod.getProx(),atuThis=atuThis.getProx())
    		if(atuMetod.getInfo() instanceof Cloneable)
    			atuThis.setProx(new No(this.meuCloneDeX(atuMetod.getInfo())));
    		else 
    			atuThis.setProx(new No(atuMetod.getInfo()));
    }

     public Object clone ()
    {
    	 ListaOrdenadaDuplamenteLigada ret = null;
    	 try {
    		 ret = new ListaOrdenadaDuplamenteLigada(this);
    	 }
    	 catch(Exception erro) {}
    	 return ret;
    }
    
}












