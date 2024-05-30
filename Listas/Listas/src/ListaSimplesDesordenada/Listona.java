package ListaSimplesDesordenada;


public class Listona
{
    public class Nozao
    {
        public class Listinha
        {
            public class Nozinho
            {
                private char    infozinha;
                private Nozinho proxizinho;

                // fazer construtores, getters e setters
                public Nozinho (char i, Nozinho p)
                {
                	this.infozinha = i;
                	this.proxizinho = p;
                }
                
                public void setInfozinha(char info)
                {
                	this.infozinha = info;
                }
                
                public void setProxizinha(Nozinho prox)
                {
                	this.proxizinho = prox;
                }
                
                public char getInfozinha()
                {
                	return this.infozinha;
                }
                
                public Nozinho getProxizinha()
                {
                	return this.proxizinho;
                }
            }

            private Nozinho iniciozinho;
            
            public Listinha()
            {
            	this.iniciozinho=null;
            }
            
            public boolean tem (char i)
            {
           
                for (Nozinho atual = this.iniciozinho; atual != null; atual = atual.getProxizinha())
                  if(i==atual.getInfozinha())
                	  return true;
                  
                	  return false;
            }
        }
        private Listinha infozona;
        private Nozao    proxizao;

        // fazer construtores, getters e setters
        public Nozao(Listinha in, Nozao pro)
        {
        	this.infozona = in;
        	this.proxizao = pro;
        }
        
        public void setInfozona(Listinha i)
        {
        	this.infozona  =i;
        }
        
        public void setProxizao(Nozao p)
        {
        	this.proxizao  = p;
        }
        
        public Listinha getInfozona() 
        {
          return this.infozona;	
        }
        
        public Nozao getProxizao()
        {
        	return this.proxizao;
        }
}
    private Nozao iniciozao;

    public Listona()
    {
     this.iniciozao = null;	
    }
    
    public int getQtdDoChar (char c) throws Exception
    {
        if(this.iniciozao==null)
        	throw new Exception("Listona vazia!");
        int contador = 0;
        for(Nozao atual=this.iniciozao;atual.getProxizao()!= null; atual = atual.getProxizao())
        {	
        	if(atual.infozona.tem(c))
        	{
        		contador=+1;
        	}
        	else 
        		throw new Exception("Valor não encontrado na lista!");
        }
        
        return contador;
    }

}
