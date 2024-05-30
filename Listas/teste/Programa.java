public class Programa{
    public static void main(String[] args){
        Lista<String> ls;
        ls = new Lista<String>();
        Lista<String> ls2;
        ls2 = new Lista<String>();
        try{
            System.out.println("----- lista---------");
			ls.insiraNoComeco("J3");
            ls.insiraNoComeco("J3");
            ls.insiraNoComeco("J2");
            ls.insiraNoComeco("J2");
            ls.insiraNoComeco("J2");
            ls.insiraNoComeco("J5");
            System.out.println(ls);
            System.out.println("----- lista sem Repetidos---------");
            ls.removeDuplicatas();
            System.out.println(ls);

            //ls2.insiraNoComeco("J1");
            //ls2.insiraNoComeco("J2");
            //ls.insiraNoFim("JJAA");
			//System.out.println(ls);
            //System.out.println(ls2);
			//System.out.println(ls2.equals(ls));
			//System.out.println(ls2.quantidade());
        }catch(Exception e){
            System.out.println(e);
        }
    }
}