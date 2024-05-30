import java.lang.reflect.*;

public class ListaDuplamenteLigadaCircularOrdenadaSemRepeticao <X extends Comparable<X>>
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
    }

    private No prim;
    private No ulti;

	public ListaDuplamenteLigadaCircularOrdenadaSemRepeticao ()
	{
		this.prim=null;
		this.ulti=null;
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

    public void insira (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

	    X info;
	    if (i instanceof Cloneable)
	        info = meuCloneDeX (i);
	    else
	        info = i;

	    if (this.prim==null/* && this.ulti==null */)
	    {
			this.prim = new No (null,info,null);
			this.prim.setProx(this.prim);
			this.prim.setAnte(this.prim);
			this.ulti = this.prim;
			return;
		}

		int comp=i.compareTo(this.prim.getInfo());

		if (comp<0)
		{
			this.prim = new No (this.ulti,info,this.prim);
			this.prim.getProx().setAnte(this.prim);
			this.ulti.setProx(this.prim);
			return;
		}

		if (comp==0)
			throw new Exception ("Informacao repetida");

        // rever daqui para frente
		No atual=this.prim;

		for(;;)
		{
			if (atual.getProx()==this.prim)
			    break;

			comp=i.compareTo(atual.getProx().getInfo());

			if (comp==0)
			    throw new Exception ("Informacao repetida");

			if (comp<0)
			    break;

			atual=atual.getProx();
		}

		atual.setProx (new No (atual,info,atual.getProx()));
		atual = atual.getProx();
		atual.getAnte().setProx(atual);
		atual.getProx().setAnte(atual);
		if (atual.getProx()==this.prim)
			this.ulti = atual;
    }

    public boolean tem (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");

        No atual=this.prim;

        for(;;)
        {
			if (atual==null)
			    break;

			int comp = i.compareTo(atual.getInfo());

			if (comp==0)
                return true;

            if (comp<0) // i � menor, logo atual.getInfo() � maior
                break;

            atual = atual.getProx();
        }

        return false;
    }

	public void remova (X i) throws Exception
	{
		if (i==null)
			throw new Exception ("Informacao ausente");

		if (this.prim==null)
		    throw new Exception ("Informacao inexistente");

		if (i.equals(this.prim.getInfo())){
			if(this.prim == this.prim.getProx())
			{
				this.prim = null;
				this.ulti = null;
				return;
			}	
			this.prim = this.prim.getProx();
			this.prim.setAnte(this.ulti);
			this.ulti.setProx(this.prim);
		}
			
		else
		{
			No atual=this.prim;

			for(;;)
			{
				if (atual.getProx()==this.prim)
					throw new Exception ("Informacao inexistente");

				int comp = i.compareTo(atual.getProx().getInfo());

				if (comp==0)
				{
					if(this.prim == this.prim.getProx())
					{
						this.prim = null;
						this.ulti = null;
						return;
					}	
					atual.setProx(atual.getProx().getProx());
					atual.getProx().setAnte(atual);
					return;
			    }

				if (comp<0) // i � menor, logo atual.getProx().getInfo() � maior
					throw new Exception ("Informacao inexistente");

				atual = atual.getProx();
			}
		}
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
		if(this.prim == null)
			return "";
        String ret="";

		No atual=this.prim;

        while (atual.getProx()!=this.prim)
        {
            ret += atual.getInfo()+" ";
            atual= atual.getProx();

		}
		ret += atual.getInfo()+" ";

        return ret;
	}
	
	
    public String toStringInverso ()
    {
        String ret="";

		No atual=this.ulti;

        while (atual.getAnte()!=this.ulti)
        {
            ret += atual.getInfo()+" ";
            atual= atual.getAnte();

		}
		ret += atual.getInfo()+" ";

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

		ListaDesordenadaSemRepeticao<X> lis=(ListaDesordenadaSemRepeticao<X>)obj;

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

	public ListaDesordenadaSemRepeticao (ListaDesordenadaSemRepeticao<X> modelo) throws Exception
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
		ListaDesordenadaSemRepeticao<X> ret=null;

		try
		{
			ret=new ListaDesordenadaSemRepeticao<X> (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}