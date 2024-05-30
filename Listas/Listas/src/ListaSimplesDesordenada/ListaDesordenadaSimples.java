package ListaSimplesDesordenada;

import java.lang.reflect.*;

public class ListaDesordenadaSimples <X extends Comparable> implements Cloneable
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

        // fazer apocalipticos
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
        	int ret = 666;
        	
        	if(this.prox != null) {
        	ret = ret * 13 + this.info.hashCode();
        	ret = ret * 13 + this.prox.hashCode();
        	}
        	else 
        		ret = ret * 13 + this.info.hashCode();
        	
        	return ret;
        }

        public boolean equals (Object obj)
        {
        	if(this == obj)
        		return true;
        	
        	if(obj == null)
        		return false;
        	
        	if(this.getClass() != obj.getClass())
        		return false;
        	
        	No no = (No)obj;
        	if(!this.getInfo().equals(no.getInfo()))
        		return false;
        	
        	if(this.getProx() != null && no.getProx()!=null)
        	if(!this.getProx().equals(no.getProx()))
        		return false;
        	
        	return false;
        	
        }

        public No (No modelo) throws Exception
        {
        	if(modelo == null)
        		throw new Exception("Modelo ausente!");
        	
           if(modelo.getProx() == null)
    	        this.setInfo(modelo.getInfo());
           else 
             {
    	       this.setInfo(modelo.getInfo());
    	       this.setProx(new No(modelo.getProx()));
             }
        	
       }

        public Object clone ()
        {
        	No n = null;
        	try {
        		n = new No(this);
        	}catch(Exception erro)
        	{}
           return n;
        }
        
    }

    private No prim;

    public ListaDesordenadaSimples ()
    {
        this.prim = null;
    }

    public void insiraNoComeco (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        this.prim = new No (i, this.prim);
    }

    public void insiraNoFim (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        if (this.prim==null)
            this.prim = new No (i);
        else
        {
            No ultimo;

            for (ultimo=this.prim; ultimo.getProx()!=null; ultimo=ultimo.getProx())
                ;

            ultimo.setProx (new No (i));
        }
    }

    public boolean tem (X i)
    {
   
        for (No atual = this.prim; atual != null; atual = atual.getProx())
          if(i.equals(atual.getInfo())) 
        	  return true;
          
        	  return false;
    }
    public String toString ()
    {
        String resp="{";

        for (No atual=this.prim; atual!=null; atual=atual.getProx())
            resp += atual.getInfo()+(atual.getProx()!=null?",":"");

        resp+="}";
        return resp;
    }
    
    public void removaPrimeiro ()
    {
    	if(this.prim == null)
    		System.out.println("Lista sem informação");
    	else
    		this.prim = this.prim.getProx();
    }

    public void removaUltimo ()
    {
    	if(this.prim == null)
    		System.out.println("Informação inexistente");
    	
    	No ultimo;
    	for(ultimo = this.prim; ultimo.getProx().getProx() != null; ultimo = ultimo.getProx());
    		ultimo.setProx(null);
    }

    public void remova (X i) throws Exception
    {
    	boolean removeu = false;
    	
    	while(this.prim != null && this.prim.getInfo().equals(i)) {
    		this.prim = this.prim.getProx();
    		removeu = true;
    	}
    	
    	No atual = this.prim;
    	
    	while(atual != null ) {
    		while(atual.getProx() != null && !atual.getProx().getInfo().equals(i))
    			atual = atual.getProx();
    		    
    		if(atual.getProx() != null) {
    			atual.setProx(atual.getProx().getProx());
    			removeu = true;
    		}
    		else 
    			atual = atual.getProx();
    	}
    	if(!removeu)
    		throw new Exception("Informação inexistente");
    	
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prim == null) ? 0 : prim.hashCode());
		return result;
	}

    @Override
	public boolean equals(Object obj) {
    	
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		ListaDesordenadaSimples other = (ListaDesordenadaSimples) obj;
		if (prim == null) {
			if (other.prim != null)
				return false;
			
		} else if (!prim.equals(other.prim))
			return false;
		
		return true;
	}
    
    
    private X meuCloneDeX (X modelo)
    {
    	X ret=null;
    	
    	try {
    		Class<?> classe = modelo.getClass();
    		Class<?>[] tiposDosParametrosFormais = null;
    		Method metodo = classe.getMethod("clone", tiposDosParametrosFormais);
    		Object[] parametrosReais = null;
    		ret = (X)metodo.invoke(modelo, parametrosReais);
    	}
    	catch (NoSuchMethodException erro)
    	{}
    	catch (InvocationTargetException erro)
    	{}
    	catch(IllegalAccessException erro)
    	{}
    	return ret;
    }

    public ListaDesordenadaSimples (ListaDesordenadaSimples modelo) throws Exception
    {
    	 if (modelo==null)
             throw new Exception ("Modelo ausente");

         if (modelo.prim==null)
             return;

       //  this.prim = new No (this.meuCloneDeX (modelo.prim.getInfo()));

         for (No atuMod=modelo.prim.getProx(), atuThis=this.prim;
              atuMod!=null;
              atuMod=atuMod.getProx(), atuThis=atuThis.getProx())
              if (atuMod.getInfo() instanceof Cloneable)
                  atuThis.setProx (new No (this.meuCloneDeX(atuMod.getInfo())));
              else
                  atuThis.setProx (new No (atuMod.getInfo()));

    }

    public Object clone ()
    {
    	ListaDesordenadaSimples ret = null;
    	
    	try 
    	{
    			ret = new ListaDesordenadaSimples(this);	
    	}
    	catch(Exception erro)
    	{}
    	return ret;
    }
    
    
    public void InvertaSe()
    {
    	if(this.prim == null)
    		return;
    	
    	if(this.prim.getProx() == null)
    		return;
    	
    	No anteAtu = null,
    	   atual = this.prim,
    	   proxAtu = this.prim.getProx();
    	
    	
    	for(;;) 
    	{
    	  atual.setProx(anteAtu);
    	  anteAtu = atual;
    	  atual = proxAtu;
    	  
    	  if(atual == null)
    		  break;
    	  
    	  proxAtu = proxAtu.getProx();
    	}
    		
    	this.prim = anteAtu;
    	
    }
    
    public void InsiraApos(X oqInserir, X aposOqInserir)
    {
    	if(this.prim == null)
    		return;
    	No novo = new No(oqInserir);
    	No atual;
    
    	for(atual = this.prim;atual.getProx() != null; atual = atual.getProx())
    		if(atual.getProx().getInfo().equals(aposOqInserir)) 
    		{
              No proxatu = atual.getProx();            
    			atual.setProx(novo);
    			novo.setProx(proxatu);
    		}
    }
    
    public void OrdeneSe() throws Exception
    {
    	if(this.prim==null)
    		throw new Exception("Lista Vazia, nada para ordenar!");
          No atual;
          
    	if(this.prim.getProx()!=null)
    	{
    		for(atual=this.prim;atual.getProx()!=null; atual=atual.getProx()) 
    		{
    		if(atual.getProx().getInfo().compareTo(atual.getInfo())<0) {
    			
    				No proxAtu = atual.getProx();
    			    atual.setInfo(proxAtu.getInfo());	    			    
    		}
    	  }
    	}
    	
    }
    

}












