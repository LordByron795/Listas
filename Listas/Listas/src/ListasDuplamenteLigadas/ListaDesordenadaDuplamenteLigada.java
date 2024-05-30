package ListasDuplamenteLigadas;

import java.lang.reflect.*;

public class ListaDesordenadaDuplamenteLigada <X> implements Cloneable
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

        @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			
			if (obj == null)
				return false;
			
			if (getClass() != obj.getClass())
				return false;
			
			No other = (No) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			
			if (ante == null) {
				if (other.ante != null)
					return false;
				
			} else 
				if (!ante.equals(other.ante))
				return false;
			
			if (info == null) {
				if (other.info != null)
					return false;
				
			} else
				if (!info.equals(other.info))
				return false;
			if (prox == null) {
				if (other.prox != null)
					return false;
			} else 
				if (!prox.equals(other.prox))
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

		private ListaDesordenadaDuplamenteLigada getOuterType() {
			return ListaDesordenadaDuplamenteLigada.this;
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

    public ListaDesordenadaDuplamenteLigada ()
    {
        this.prim = null;
        this.ulti = null;
    }

    public void insiraNoComeco (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        this.prim = new No (null,i,this.prim);

        if (this.prim.getProx()==null)
            this.ulti = this.prim;
        else
            this.prim.getProx().setAnte(prim);
    }

    public void insiraNoFim (X i) throws Exception
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
            this.ulti.setProx(new No (this.ulti,i,null));
            this.ulti = this.ulti.getProx();
        }
    }

    public boolean tem (X i)
    {
        for (No atual=this.prim; atual!=null; atual=atual.getProx())
            if (i.equals(atual.getInfo()))
                return true;

        return false;
    }

    public void removaPrimeiro ()
    {
    	if(this.prim == null)
    		System.out.println("Informação inexistente");
    	
    	if(this.prim.getProx()!=null) 
    	{    		
    	this.prim.getProx().setAnte(null);
    	this.prim = this.prim.getProx();
    	}
    	else
    		if(this.prim.getProx() == null) {
    		this.prim = null;
    		this.ulti = this.prim;
    		}
    	    
    }

    public void removaUltimo ()
    {
    	if(this.prim == null)
    		System.out.println("Informação inexistente");
    	if(this.ulti.getAnte()!= null) 
    	{
        this.ulti.getAnte().setProx(null);
    	this.ulti = this.ulti.getAnte();
    	}
    	else 
    		this.ulti = null;
    	    this.prim = this.ulti;
    }

    public void remova (X i) throws Exception
    {
    	while(this.prim!= null && this.prim.getInfo().equals(i)) {
    		this.prim.getProx().setAnte(null);
    	    this.prim = this.prim.getProx();}
    	
    	No atual = this.prim;
        while(this.prim!= null) {
        	while(atual.getProx() != null && !atual.getProx().getInfo().equals(i)) 
        		atual = atual.getProx();
        
        if(atual.getProx() != null) {
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

        ListaDesordenadaDuplamenteLigada<X> lista =
        (ListaDesordenadaDuplamenteLigada<X>)obj;

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

    
    @Override
  	public int hashCode()
    {
      int ret = 666;
      
      ret = ret * 13 + this.prim.hashCode();
      ret = ret * 13 + this.ulti.hashCode();
      
      return ret;
  	}
    
    private X meuCloneDeX(X modelo) 
    {
    	X ret = null;
    	
    	try{
    	   Class<?> classe = modelo.getClass();
    	   Class[] tiposDeParametrosFormais = null;
    	   Method metodo = classe.getMethod("clone", tiposDeParametrosFormais);
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

    public ListaDesordenadaDuplamenteLigada (ListaDesordenadaDuplamenteLigada modelo) throws Exception
    {
    	if(modelo == null)
    		throw new Exception("Modelo não fornecido");
    	  
    	if(modelo.prim == null)
    		return;
    	
    	for(No atuMod= modelo.prim.getProx(), atuThis=this.prim; atuMod!=null; atuMod = atuMod.getProx(), atuThis=atuThis.getProx())
    		if(atuMod.getInfo() instanceof Cloneable)
    			 atuThis.setProx (new No (this.meuCloneDeX(atuMod.getInfo())));
    		else
    			atuThis.setProx(new No(atuMod.getInfo()));
    	
    }

    public Object clone ()
    {
    	ListaDesordenadaDuplamenteLigada ret = null;
    	
    	try{
    	
    		ret = new ListaDesordenadaDuplamenteLigada(this);
    	}
    	catch(Exception erro)
    	{}
    	return ret;
    }

 public void invertaSe ()
    {
    	if(this.prim == null)
    		return;
    	
    	if(this.prim.getProx() == null)
    		return;
    	
    	No anteAtu = null,
    	   atual = this.prim,
    	   proxAtu = this.prim.prox;
           
    for(;;){
    		atual.setProx(anteAtu);
    		anteAtu= atual;
    		atual=proxAtu;
    		 
    	 if(atual == null)
    		 break; 
    	
    	 proxAtu = proxAtu.getProx();
    	
    } 
    this.prim = anteAtu;
    No ult;
     for(ult= this.prim; ult.getProx()!= null; ult = ult.getProx());
     
     this.ulti = ult;
     
    }

    public void insiraApos (X oqInserir, X aposOqInserir) throws Exception
    {
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
    }
    
}












