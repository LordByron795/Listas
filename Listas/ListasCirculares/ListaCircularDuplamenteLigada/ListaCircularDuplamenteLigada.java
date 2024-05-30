import java.lang.reflect.Method;

public class ListaCircularDuplamenteLigada<X> implements Cloneable
{
    private No prim = null, ulti = null;

    private class No
    {
        private No ante = null;
        private X info = null;
        private No prox = null;

        private No(No a,X i, No p)
        {
            this.info = i;
            this.prox = p;
            this.ante = a;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

        public No getAnte ()
        {
            return this.ante;
        }

		public void setInfo (X i)
		{
			this.info = i;
		}

		public void setProx (No novo)
        {
	    	this.prox = novo;
        }

        public void setAnte (No novo)
        {
	    	this.ante = novo;
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
        //if(this.tem(i))
            //throw new Exception("Poutz ja existe um registro desses");
		X info;
		if (i instanceof Cloneable)
			info=meuCloneDeX(i);
		else
			info=i;


		if(this.prim == null)
		{
			this.prim = new No (this.prim,info,this.prim);
            this.prim.setProx(this.prim);
            this.prim.setAnte(this.prim);
            this.ulti = this.prim;

			return;
		}

        this.prim = new No(this.ulti,i,this.prim);
        this.prim.getProx().setAnte(this.prim);
        ulti.setProx(this.prim);

    }

    public void insiraNoFim (X i) throws Exception
    {
		if (i==null)
			throw new Exception ("Informacao ausente");
        //if(this.tem(i))
            //throw new Exception("Poutz ja existe um registro desses");
		X info;
		if (i instanceof Cloneable)
			info=meuCloneDeX(i);
		else
			info=i;


		if(this.ulti == null)
		{
			this.ulti = new No (this.ulti,info,this.ulti);
            this.ulti.setProx(this.ulti);
            this.ulti.setAnte(this.ulti);
            this.prim = this.ulti;

			return;
		}

        this.ulti = new No(this.ulti,i,this.prim);
        this.ulti.getAnte().setProx(this.ulti);
        prim.setAnte(this.ulti);

    }

    public boolean tem(X x) throws Exception
    {
		if(x == null)
			throw new Exception("Passe algo");
 		if(this.prim.getInfo().equals(x) || this.ulti.getInfo().equals(x))
			return true;

		No atual = this.prim.getProx();

		while(atual != this.ulti)
		{
			if(atual.getInfo().equals(x))
			   return true;
		    atual = atual.getProx();
		}

		return false;

	}

    public void remova(X x) throws Exception
    {
		boolean deletou = false;

		if(x == null)
			throw new Exception("Passe algo");

 		while(this.prim.getInfo().equals(x))
 		{
			deletou = true;
			if(this.prim == this.ulti)
			{
				this.prim = null;
				this.ulti = null;
				return;
			}
			this.prim = this.prim.getProx();
			this.prim.setAnte(this.ulti);
			this.ulti.setProx(this.prim);
		}

		 while(this.ulti.getInfo().equals(x))
		 {
			 deletou = true;
			if(this.prim == this.ulti)
			{
				this.prim = null;
				this.ulti = null;
				return;
			}
			this.ulti = this.ulti.getAnte();
			this.ulti.setProx(this.prim);
			this.prim.setAnte(this.ulti);
		}

		No atual = this.prim;
		while(atual.getProx() != this.ulti)
		{
			while(atual.getProx() != this.ulti && !atual.getProx().getInfo().equals(x))
			{
				atual=atual.getProx();
			}
			while(atual.getProx() != this.ulti && atual.getProx().getInfo().equals(x))
			{
				deletou = true;
				if(this.prim == this.ulti)
				{
					this.prim = null;
					this.ulti = null;
					return;
				}
				atual.setProx(atual.getProx().getProx());
				atual.prox.setAnte(atual);
				}
			}

		if(!deletou)
			throw new Exception("Não encontramos dados a serem deletados");

	}


	public String toString ()
    {
        String ret="";

		if(this.prim == null)
			return ret;
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

    public String toStringInverso()
    {
        String ret="";

        No atual=this.ulti;

        while (atual.getAnte()!=this.ulti)
        {

            ret += atual.getInfo()+" ";
            atual= atual.getAnte();
        }
       ret += atual.getInfo()+" ";
       // ret += atual.getProx().getInfo()+ " ";
        return ret;
    }

}
