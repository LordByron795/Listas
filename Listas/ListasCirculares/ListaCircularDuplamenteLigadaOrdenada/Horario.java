public class Horario implements Cloneable, Comparable
{
	private int horas, minutos, segundos;

	public void setHoras (int h) throws Exception
	{
		if (h<0 || h>23)
			throw new Exception ("Horas invalidas");

		this.horas = h;
	}

	public void setMinutos (int m) throws Exception
	{
		if (m<0 || m>59)
			throw new Exception ("Minutos invalidos");

		this.minutos = m;
	}

	public void setSegundos (int s) throws Exception
	{
		if (s<0 || s>59)
			throw new Exception ("Segundos invalidos");

		this.segundos = s;
	}

	public Horario (int h, int m, int s) throws Exception
	{
		this.setHoras   (h);
		this.setMinutos (m);
		this.setSegundos(s);
	}

	public int getHoras ()
	{
		return this.horas;
	}

	public int getMinutos ()
	{
		return this.minutos;
	}

	public int getSegundos ()
	{
		return this.segundos;
	}

	public String toString ()
	{
		return (this.horas   <9?"0":"")+this.horas  +":"+
		       (this.minutos <9?"0":"")+this.minutos+":"+
		       (this.segundos<9?"0":"")+this.segundos;
	}

	public boolean equals (Object obj)
	{
		if (this==obj)
		    return true;

		if (obj==null)
		    return false;

		if (this.getClass()!=obj.getClass())
		    return false;

		Horario hor = (Horario)obj;

		if (this.horas!=hor.horas)
		    return false;

		if (this.minutos!=hor.minutos)
		    return false;

		if (this.segundos!=hor.segundos)
		    return false;

		return true;
	}

	public int hashCode ()
	{
		int ret=666;

		ret = ret*2 + new Integer (this.horas   ).hashCode();
		ret = ret*2 + new Integer (this.minutos ).hashCode();
		ret = ret*2 + new Integer (this.segundos).hashCode();

		return ret;
	}

	//construtor de copia
	public Horario (Horario modelo) throws Exception
	{
		if (modelo==null)
		    throw new Exception ("Modelo ausente");

		this.horas   =modelo.horas;
		this.minutos =modelo.minutos;
		this.segundos=modelo.segundos;
	}

	public Object clone ()
	{
		Horario ret=null;

		try
		{
    		ret = new Horario (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}