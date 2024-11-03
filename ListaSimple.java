public class ListaSimple <T>{
    private Nodo inicio;
    
    public ListaSimple(){}

    public void insertaInicio(T dato){
        Nodo n = new Nodo<>();
        n.setInfo(dato);
        n.setSig(inicio);
        n.setAnt(null);

        if(inicio != null){
            inicio.setAnt(n);
        }
        inicio = n;
    }

    public void insertaFin(T dato){
        Nodo n = new Nodo<>();
        Nodo r = new Nodo<>();
        n.setInfo(dato);
        n.setSig(null);
        if(inicio == null){
            n.setAnt(inicio);
            inicio = n;
        }else{
            r = inicio;
            while(r.getSig() != null){
                r = r.getSig();
            }
            r.setSig(n);
            n.setAnt(r);
        }
    }

    public T eliminarInicio(){
        T dato = null;
        if(inicio == null){
            System.out.println("Lista vacía");
        }else if(inicio.getSig() == null){
            dato = (T) inicio.getInfo();
            inicio = null;
        }else{
            dato = (T) inicio.getInfo();
            inicio = inicio.getSig();
            inicio.setAnt(null);
        }
        return dato;
    }

    public T eliminarFin(){
        T dato = null;
        Nodo r = inicio;
        if(inicio == null){
            System.out.println("Lista vacía");
        }else if(inicio.getSig() == null){
            dato = (T) inicio.getInfo();
            inicio = null;
        }else{
            while(r.getSig() != null){
                r = r.getSig();
            }
            dato = (T) r.getInfo();
            r.getAnt().setSig(null);
        }
        return dato;
    }

    public void mostrar(){
        Nodo r = new Nodo<>();
        if(inicio == null){
            System.out.println("Lista vacía");
        }else{
            r = inicio;
            while(r != null){
                System.out.println(r.getInfo());
                r = r.getSig();
            }
        }
    }

    public int cantidadNodos(){
        Nodo r = inicio;
        int count = 0;
        if(inicio == null){
            return 0;
        }else{
            while(r != null){
                r = r.getSig();
                count++;
            }
            return count;
        }
    } 

    public String mostrarRecursivo(Nodo x){
        if(x == null){
            return "";
        }else{
            return x.getInfo().toString() + "\n" + mostrarRecursivo(x.getSig());
        }
    }

    public String mostrarListaRecursivo(){
        if(inicio == null){
            return "";
        }else{
            return mostrarRecursivo(inicio);
        }
    }

    public T eliminaX(int idx){
        Nodo x = inicio;
        ListaSimple lista = new ListaSimple<>();
        T dato = null;
        int count = 0;

        while(x != null){
            if(count != idx){
                lista.insertaFin(x.getInfo());
            }else{
                dato = (T) x.getInfo();
            }
            count++;
            x = x.getSig();
        }
        inicio = lista.getInicio();
        return dato;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public boolean listaVacia(){
        return inicio == null;
    }
    public static void main(String[] args) {
        ListaSimple listaSimple = new ListaSimple<>();
        listaSimple.insertaFin("Tito");
        listaSimple.insertaInicio("El tito del disco");
        listaSimple.insertaFin("El disco del tito");
        System.out.println(listaSimple.cantidadNodos());

    }
}
