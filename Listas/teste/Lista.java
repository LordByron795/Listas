public class Lista<X>{
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


		public boolean equals(Object o)
		{
			 if(o == this)
				return true;
			 if(o == null)
				return false;

			 if(o.getClass() != this.getClass())
				return false;

			No fil = (No)o;

			No aux = this;

			if(!aux.getInfo().equals(fil.getInfo()))
				return false;
			 fil = fil.getProx();

			 aux = aux.getProx();
			if(fil != null)
				if(!fil.equals(aux))
					return false;

			if(aux != null)
				return false;

			return true;
		}

    }

    public int hashcode()
    {
		int ret = 666;

		No atual;

		ret = 7*ret + prim.getInfo().hashCode();

		atual = prim.getProx();

		while(atual.getProx() != null )
		{
			ret += 7*ret + atual.getInfo().hashCode();
			atual = atual.getProx();
		}

		return ret;
	}

        public boolean tem (X i)
    {
        No atual=this.prim;

        while (atual!=null)
        {
            if (i.equals(atual.getInfo()))
                return true;

            atual = atual.getProx();
        }

        return false;
    }


    public void insiraNoComeco(X i) throws Exception{
        if(i == null)
            throw new Exception("Vai se Foder passa alguma coisa");

        this.prim = new No(i,this.prim);

    }


    public void insiraNoFim(X i) throws Exception{
        if(i == null)
            throw new Exception("Vai se Foder passa alguma coisa");
        No novo = new No(i,null);
        if(this.prim == null)
        {
            this.prim = novo;
            return;
        }
        No atual = prim.getProx();

        while(atual.getProx() != null)
            atual = atual.getProx();

        atual.prox = novo;

    }

    public String toString()
    {
        No atual = this.prim;
        String str = "";
        while(atual != null)
        {
            str += " "+atual.getInfo();
            atual = atual.getProx();
        }

        return str ;
    }

    public boolean equals(Object o){
		 if(o == this)
		 	return true;
		 if(o == null)
			return false;

		 if(o.getClass() != this.getClass())
		 	return false;
		Lista<X> fil = (Lista<X>)o;

		if (!this.prim.equals(fil.prim))
			return false;

		return true;
		}

	public void remova (X i) throws Exception
	{
         boolean removeu = false;
		if (i==null)
			throw new Exception ("Informacao ausente");



		while (i.equals(this.prim.getInfo()) && this.prim != null)
        {
            this.prim = this.prim.getProx();
            removeu = true;
        }
		if (this.prim==null)
		{
			if (!removeu)
			  throw new Exception ("Informacao inexistente");
			return;
		}



			No atual=this.prim;

			while (atual.getProx()!=null)
            {

                while(atual.getProx() != null && !i.equals(atual.getProx().getInfo()) )
                    atual = atual.getProx();


				System.out.println(atual.getInfo());

				if (atual.getProx()==null)
				{
					if (!removeu)
						throw new Exception ("Informacao inexistente");

					return;
				}
                while(atual.getProx() != null && i.equals(atual.getProx().getInfo()))
                {
                    atual.setProx (atual.getProx().getProx());
                    removeu = true;
                }

            }

			if(removeu == false)
                throw new Exception("nao existe isso!");
	}

public void removeDuplicatas(){
	No atual = this.prim;

	while(atual != null)
	{

		while(atual.getProx() != null && atual.getInfo().equals(atual.getProx().getInfo()))
		{

			atual.prox = atual.getProx().getProx();
		}
			

		if(atual.getProx() == null)	
			return;

		No dep = atual.getProx();

		while(dep.getProx() != null)
		{
			
			while(dep.getProx() != null && !dep.getProx().getInfo().equals(atual.getInfo()))
				dep = dep.getProx();

			if(dep.getProx() == null)
				break;

			while(dep.getProx() != null && dep.getProx().getInfo().equals(atual.getInfo()))
				dep.setProx(dep.getProx().getProx());	
		}

		atual = atual.getProx();
	}

	}

	public void inverte(){
		No atual = this.prim;
		No proxSalvo = this.prim.getProx();
		No anterior = null;

			while(proxSalvo != null)
			{

				//Faz o ciclo Atual -> Anterior -> Atual -> Prox ---- e chama o proximo para repetir o ciclo
				atual.setProx(anterior);
				anterior = atual;
				atual = proxSalvo;


				proxSalvo = proxSalvo.getProx();
			}
		atual.setProx(anterior);
		anterior = atual;
		prim = atual;


		}

	public int quantidade()
	{
		int ret = 0;

		No aux = this.prim;
		while(aux != null)
		{
			ret++;

			aux = aux.getProx();
		}

		return ret;
	}



}
