public class Jugador {
    private ListaSimple<Carta> manoJugador;

    public Jugador(){
        this.manoJugador = new ListaSimple<>();
    }

    public ListaSimple<Carta> getManoJugador() {
        return manoJugador;
    }

    public void setManoJugador(ListaSimple<Carta> manoJugador) {
        this.manoJugador = manoJugador;
    }

    public Carta tomarCarta(){
        return (Carta) manoJugador.eliminarInicio();
    }

    @Override
    public String toString() {
        return "Jugador [manoJugador=" + manoJugador + "]";
    }

}
