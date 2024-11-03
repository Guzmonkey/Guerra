import java.util.Scanner;
public class Guerra{
    private ListaSimple cartasJugadas = new ListaSimple<>();
    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();
    private Baraja baraja = new Baraja();
    private int ganador;
    private String estadoGuerra = "";
    private int estado;
    private int countBarajas = baraja.getNumCartas();
    private Carta cartaJ1;
    private Carta cartaJ2;
    private int rondas;

    public Guerra(int rondas){
        this.rondas = rondas;
        baraja.mezclarBaraja();
        repartirCartas(jugador1, countBarajas);
        repartirCartas(jugador2, countBarajas);

    }

    public void repartirCartas(Jugador jugador, int n){
        ListaSimple listaAux = new ListaSimple<>();
        for(int i = 0; i < countBarajas / 2; i++){
            listaAux.insertaFin(baraja.tomarCarta());
        }
        jugador.getManoJugador().setInicio(listaAux.getInicio());
    }

    public void tomarCartas(){
        cartaJ1 = jugador1.tomarCarta();
        cartaJ2 = jugador2.tomarCarta();
        System.out.println(cartaJ1);
        System.out.println(cartaJ2);
        cartasJugadas.insertaFin(cartaJ1);
        cartasJugadas.insertaFin(cartaJ2);
    }

    public void jugarRonda(){
        if(rondas > 0){
            tomarCartas();
            cartasJugadas.insertaFin(cartaJ1);
            cartasJugadas.insertaFin(cartaJ2);
            estado = cartaJ1.compareTo(cartaJ2);
            if(estado == 0){
                System.out.println("HAY GUERRA");
                guerra();
            }else if(estado == 1){
                System.out.println("El jugador 1 ganó la ronda");
                jugador1.getManoJugador().insertaFin(cartaJ1);
                jugador1.getManoJugador().insertaFin(cartaJ2);
            }else if(estado == -1){
                System.out.println("El jugador 2 ganó la ronda");
                jugador2.getManoJugador().insertaFin(cartaJ1);
                jugador2.getManoJugador().insertaFin(cartaJ2);
            }
            rondas--;
        }else{
            ganadorJuego();
        }
    }

    public void guerra(){
        for(int i = 0; i < 3; i++){
            cartasJugadas.insertaFin(cartaJ1);
            cartasJugadas.insertaFin(cartaJ2);
        }
        tomarCartas();
        estado = cartaJ1.compareTo(cartaJ2);
        if(estado == 0){
            System.out.println("HAY OTRA GUERRA");
            guerra();
        }else if(estado == 1){
            System.out.println("El jugador 1 ganó la ronda");

            while (!cartasJugadas.listaVacia()) {
                jugador1.getManoJugador().insertaFin((Carta) cartasJugadas.eliminarInicio());
            }

            jugador1.getManoJugador().insertaFin(cartaJ1);
            jugador1.getManoJugador().insertaFin(cartaJ2);
            
        }else if(estado == -1){
            System.out.println("El jugador 2 ganó la ronda");

            while(!cartasJugadas.listaVacia()){
                jugador2.getManoJugador().insertaFin((Carta) cartasJugadas.eliminarInicio());
            }

            jugador2.getManoJugador().insertaFin(cartaJ1);
            jugador2.getManoJugador().insertaFin(cartaJ2);
        }
        System.out.println(cartasJugador(jugador1));
        System.out.println(comprobarNumCartas(jugador1));
        System.out.println("\n");
        System.out.println(cartasJugador(jugador2));
        System.out.println(comprobarNumCartas(jugador2));
    }


    public Jugador ganadorJuego(){
        int numJ1 = jugador1.getManoJugador().cantidadNodos();
        int numJ2 = jugador2.getManoJugador().cantidadNodos();
        if(numJ1 > numJ2){
            System.out.println("El jugador 1 ganó la partida");
            return jugador1;
        }else if(numJ1 < numJ2){
            System.out.println("El jugador 2 ganó la partida");
            return jugador2;
        }else{
            System.out.println("Empate");
            return null;
        }
    }

    public int comprobarNumCartas(Jugador jugador){
        if(jugador.getManoJugador().cantidadNodos() > 0){
            return jugador.getManoJugador().cantidadNodos();
        }else{
            return 0;
        }
    }

    public String cartasJugador(Jugador jugador){
        return jugador.getManoJugador().mostrarListaRecursivo();
    }

    public int compararCartas(){
        int comparacion = cartaJ1.compareTo(cartaJ2);
        if(comparacion == 1){
            estadoGuerra = "El jugador 1 ganó la ronda";
        }else if(comparacion == -1){
            estadoGuerra = "El jugador 2 ganó la ronda"; 
        }else if(comparacion == 0){
            estadoGuerra = "HAY GUERRA";
        }
        return comparacion;
    }

    public String getEstadoGuerra(){
        return estadoGuerra;
    }

    public int numeroCartasJugador(int n){
        if(n == 1){
            return jugador1.getManoJugador().cantidadNodos();
        }else if(n == 2){
            return jugador2.getManoJugador().cantidadNodos();
        }
        return 0;
    }

    public void darCartasGanador(){
        if(estadoGuerra.equals("El jugador 1 ganó la ronda")){
            while(!cartasJugadas.listaVacia()){
                jugador1.getManoJugador().insertaFin((Carta) cartasJugadas.eliminarInicio());
            }
        }else if(estadoGuerra.equals("El jugador 2 ganó la ronda")){
            while(!cartasJugadas.listaVacia()){
                jugador2.getManoJugador().insertaFin((Carta) cartasJugadas.eliminarInicio());
            }
        }
    }

    public void tomarCartasGuerra(){
        for(int i = 0; i < 3; i++){
            cartasJugadas.insertaFin(jugador1.getManoJugador().eliminarInicio());
            cartasJugadas.insertaFin(jugador2.getManoJugador().eliminarInicio());
        }
    }

    public Carta getCartaJ1() {
        return cartaJ1;
    }

    public Carta getCartaJ2(){
        return cartaJ2;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }
    
    public static void main(String[] args) {
        Guerra guerra = new Guerra(10);
        guerra.jugarRonda();
    }
}