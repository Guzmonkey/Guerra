import java.util.Random;
public class Baraja {
    protected String[] palos = {"Trebol", "Diamantes", "Espadas", "Corazon"};
    protected String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "As"};
    ListaSimple baraja = new ListaSimple<>();

    public Baraja(){
        crearBaraja();
    }

    public void crearBaraja(){
        for(int i = 0; i < palos.length; i++){
            for(int j = 0; j < valores.length; j++){
                Carta carta = new Carta(valores[j], palos[i], false);
                baraja.insertaFin(carta);
            }
        }
    }

    public void mezclarBaraja(){
        Carta cartaAux;
        ListaSimple listaAux = new ListaSimple<>();
        Random random = new Random();
        int count = baraja.cantidadNodos();
        for(int i = 0; i < count; i++){
            cartaAux = (Carta) baraja.eliminaX(random.nextInt(baraja.cantidadNodos()));
            listaAux.insertaFin(cartaAux);
        }
        baraja.setInicio(listaAux.getInicio());
    }

    public String getMostrarCartas(){
        return baraja.mostrarListaRecursivo();
    }

    public Carta tomarCarta(){
        return (Carta) baraja.eliminarInicio();
    }

    public int getNumCartas(){
        return baraja.cantidadNodos();
    }

    /* 
    public static void main(String[] args) {
        Baraja bvBaraja = new Baraja();
        System.out.println(bvBaraja.getMostrarCartas());
        bvBaraja.mezclarBaraja();
        System.out.println(bvBaraja.getMostrarCartas());
        System.out.println(bvBaraja.getNumCartas());
    } */
}
