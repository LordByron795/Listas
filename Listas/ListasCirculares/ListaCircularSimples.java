public class ListaCircularSimples<X> implements Cloneable
{
    private No prim = null;

    private class No
    {
        private X info = null;
        private No prox = null;

        private No(X i, No p)
        {
            this.info = i;
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

		public void setInfo (X i)
		{
			this.info = i;
		}

		public void setProx (No novo)
        {
	    	this.prox = novo;
		}
	}

    private X meuCloneDeX (X x)
    {
        X ret=null;

        try
        {
            //ret = (X)x.clone();
            Class<?> classe = x.getClass();
            Class<?>[] tipoParametroFormal = null; // null pq clone tem 0 parametros
            Method metodo = classe.getMethod ("clone", tipoParametroFormal);
            Object[] parametroReal = null; // null pq clone tem 0 parametros
            ret = (X)metodo.invoke (x, parametroReal);
        }
        catch (Exception erro)
        {}

        return ret;
    }

    public void insiraNoComeco (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");
        if(this.tem(i))
            throw new Exception("Poutz ja existe um registro desses");
		X info;
		if (i instanceof Cloneable)
			info=meuCloneDeX(i);
		else
			info=i;


		if(this.prim == null)
		{
			this.prim = new No (info,this.prim);
            this.prim.setProx(this.prim);

			return;
		}
		No ult = this.prim;
		while(this.prim != ult.getProx())
		{
			ult = ult.getProx();
		}

		this.prim = new No(i,this.prim);
        ult.setProx(this.prim);

    }

    public void insiraNoFim (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

		X info;
		if (i instanceof Cloneable)
			info=meuCloneDeX(i);
		else
		    info=i;

        No novo = new No (info, this.prim);

        if (this.prim==null)
        {
			this.prim=novo;
			this.prim.setProx(novo);
            return;
        }

		No atual = this.prim;

        while (atual.getProx() != this.prim)
        {
            if(atual.getProx().getInfo().equals(i))
                throw new Exception("Poutz ja existe um registro desses");
            atual = atual.getProx();
        }


		atual.setProx (novo);
    }

    public boolean tem (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");
        if(this.prim == null)
            return false;
		No atual=this.prim;

        while (atual.getProx() != this.prim)
        {
            if (i.equals(atual.getInfo()))
                return true;

            atual = atual.getProx();
        }
        if(atual.getInfo().equals(i))
        {
            return true;
        }
        return false;
    }
	public String toString ()
    {
        String ret="";

        No atual=this.prim;

        while (atual.getProx()!=this.prim)
        {

            ret += atual.getInfo()+" ";
            atual= atual.getProx();
        }
       ret += atual.getInfo()+" ";
       // ret += atual.getProx().getInfo()+ " ";
        return ret;
    }
    public void remova (X i) throws Exception
	{
		if (i==null)
			throw new Exception ("Informacao ausente");
        boolean removeu=false;

        while((this.prim != this.prim.getProx()) && (this.prim.getInfo().equals(i)))
        {
            this.prim = this.prim.getProx();
        }

        No atual = this.prim ;

		while (this.prim!= atual.getProx())
		{
            while(atual.getProx().getInfo().equals(i))
            {
                atual.setProx(atual.getProx().getProx());
                removeu=true;
            }
            atual = atual.getProx();
        }


        if(removeu == false)
            throw new Exception("nada foi removido");
    }

    public void invertaSe ()
    {
		if (this.prim==null)
			return;

		if (this.prim.getProx()==this.prim)
			return;

        No anterior=this.prim, atual=this.prim, seguinte=this.prim.getProx();

        while(anterior.getProx() != this.prim)
           anterior =  anterior.getProx();
		while (seguinte!=this.prim)
		{

			atual.setProx(anterior);
			anterior=atual;
			atual   =seguinte;
			seguinte=seguinte.getProx();
		}

		atual.setProx(anterior);
		this.prim = atual;
	}

	public ListaCircularSimples()
	{

	}
	public ListaCircularSimples(ListaCircularSimples<X> l) throws Exception
	{
			if(l == null)
				throw new Exception("Passe algo");

			if(l.prim == null)
			{
                this.prim = null;
                return;
			}

            this.prim = new No(l.prim.getInfo(), null);
            
            if(l.prim.getProx() == l.prim)
            {
                this.prim.setProx(this.prim);
                return;
            }    
            
			No atualPrim = this.prim , atualL = l.prim;

			while(atualL.getProx() != l.prim)
			{
                atualPrim.setProx(new No(atualL.getProx().getInfo(),null));

               atualPrim = atualPrim.getProx();
               atualL = atualL.getProx();
            }
            
            atualPrim.setProx(this.prim);
	}

    public Object clone ()
	{
		ListaCircularSimples<X> ret=null;

		try
		{
			ret=new ListaCircularSimples<X> (this);
		}
		catch (Exception erro)
		{}

		return ret;
    }
    
    //metodos obrigatorios 
    public boolean equals(Object obj) {
        if(obj == this)
            return true;        
        if(obj == null)
            return false;

        if(obj.getClass() != this.getClass())
            return false;

        ListaCircularSimples<X> lista = (ListaCircularSimples<X>)obj ;

        No atualThis = this.prim;
        No atualObj = lista.prim;

        while(atualThis.getProx() != this.prim && atualObj.getProx() != lista.prim)
        {
            if (!atualThis.getInfo().equals(atualObj.getInfo()))
                return false;
            atualThis = atualThis.getProx();
            atualObj = atualObj.getProx();
        }
            
        if(atualObj.getProx() != lista.prim)
            return false;

        if(atualThis.getProx() != this.prim)
            return false;            

        return true;
    }

    public int hashCode() {
     int ret = 7;
     
    No atual = this.prim;
    
    while(atual.getProx() != this.prim)
    {
        ret += atual.getInfo().hashCode();
        atual  = atual.getProx(); 
    }

     return ret;
    }

}
