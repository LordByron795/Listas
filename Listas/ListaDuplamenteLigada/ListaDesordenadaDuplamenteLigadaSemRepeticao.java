public class ListaDesordenadaDuplamenteLigadaSemRepeticao <X>
{
    private class No
    {
        private X  info;
        private No prox;
        private No ante;

        public No (No a, X i, No p)
        {
			this.ante = a;
            this.info = i;
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

		public void setAnte (No novo)
        {
	    	this.ante = novo;
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

    private No prim=null, ulti=null;

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

    public void insiraNoInicio (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

		if (this.tem(i))
			throw new Exception ("Informacao repetida");

        X info;
        if (i instanceof Cloneable)
        	info = meuCloneDeX(i);
        else
            info = i;

        this.prim = new No (null,info,this.prim);

        if (this.ulti==null)
            this.ulti = this.prim;

        if (this.prim.getProx()!=null)
            this.prim.getProx().setAnte(this.prim);
    }

    public void insiraNoFim (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

		X info;
        if (i instanceof Cloneable)
        	info = meuCloneDeX(i);
        else
            info = i;

        this.ulti = new No (this.ulti,info,null);

        if (this.prim==null)
            this.prim = this.ulti;

        if (this.ulti.getAnte()!=null)
            this.ulti.getAnte().setProx(this.ulti);
    }

    public boolean tem (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

        No atual=this.ulti;

        while (atual!=null)
        {
            if (i.equals(atual.getInfo())
                return true;

            atual = atual.getAnte();
        }

        return false;
    }
    /*
    public boolean tem (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

        No atual=this.prim;

        while (atual!=null)
        {
            if (i.equals(atual.getInfo())
                return true;

            atual = atual.getProx();
        }

        return false;
    }
    */

	public void remova (X i) throws Exception
	{
		if (i==null)
			throw new Exception ("Informacao ausente");

		if (this.prim==null /* &&this.ulti==null */)
		    throw new Exception ("Informacao inexistente");

		if (i.equals(this.prim.getInfo())
		{
			if (this.prim==this.ulti)
			{
				this.prim = null;
				this.ulti = null;
				return;
			}

			this.prim = this.prim.getProx();
			this.prim.setAnte(null);
			return;
		}

		if (i.equals(this.ulti.getInfo())
		{
			this.ulti = this.ulti.getAnte();
			this.ulti.setProx(null);
			return;
		}

		No atual=this.prim;

		while (atual.getProx()!=null &&
			   !i.equals(atual.getProx().getInfo())
			atual=atual.getProx();

		if (atual.getProx()==null)
			throw new Exception ("Informacao inexistente");

		atual.setProx (atual.getProx().getProx());
		atual.getProx().setAnte(atual);
	}

    // reescrever tudo daqui para baixo

    public void invertaSe ()
    {
		if (this.prim==null)
			return;

		if (this.prim.getProx()==null)
			return;

		No anterior=null, atual=this.prim, seguinte=this.prim.getProx();

		while (seguinte!=null)
		{
			atual.setProx(anterior);
			anterior=atual;
			atual   =seguinte;
			seguinte=seguinte.getProx();
		}

		atual.setProx(anterior);
		this.prim = atual;
	}

    public int getQtd ()
    {
		int ret=0;

        No atual=this.prim;
        while (atual!=null)
        {
            ret++;
            atual= atual.getProx();
        }

        return ret;
	}

    public String toString ()
    {
        String ret="";

        No atual=this.prim;
        while (atual!=null)
        {
            ret += atual.getInfo()+" ";
            atual= atual.getProx();

        }

        return ret;
    }

    public boolean equals (Object obj)
    {
		if (this==obj)
		    return true;

		if (obj==null)
		    return false;

		if (this.getClass()!=obj.getClass())
		    return false;

		ListaDesordenada<X> lis=(ListaDesordenada<X>)obj;

		No atualThis=this.prim, atualLis=lis.prim;

		while (atualThis!=null && atualLis!=null)
		{
			if (!atualThis.getInfo().equals(atualLis.getInfo()))
				return false;

			atualThis=atualThis.getProx();
			atualLis =atualLis .getProx();
		}

		if (atualThis!=null)
			return false;

		if (atualLis!=null)
			return false;

		return true;
	}

	public int hashCode ()
	{
		int ret=666;

		No atual=this.prim;
		while (atual!=null)
		{
			ret = ret*7 + atual.getInfo().hashCode();
			atual= atual.getProx();
        }

		return ret;
	}

	public ListaDesordenada (ListaDesordenada<X> modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");

		if (modelo.prim==null)
		{
			this.prim=null;
			return;
		}

		this.prim = new No (modelo.prim.getInfo(),null);

		if (modelo.prim.getProx()==null)
			return;

		No atualThis=this.prim, atualModelo=modelo.prim;

		while (atualModelo.getProx()!=null)
		{
			atualThis.setProx (new No (atualModelo.getProx().getInfo(),null));

			atualThis  =atualThis  .getProx();
			atualModelo=atualModelo.getProx();
		}
	}

	public Object clone ()
	{
		ListaDesordenada<X> ret=null;

		try
		{
			ret=new ListaDesordenada<X> (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}