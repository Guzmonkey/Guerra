public class Carta implements Comparable<Carta>{
    private String valor;
    private String palo;
    private int valorReal;
    private boolean isVisible;
    private String cartaGrafico = "";

    public Carta(String valor, String palo, boolean isVisible){
        this.valor = valor;
        this.palo = palo;
        this.isVisible = isVisible;
        valorCartaReal();
        cartaGrafica();
    }

    public void valorCartaReal(){
        switch (valor) {
            case "J":
                valorReal = 11;
                break;
            case "Q":
                valorReal = 12;
                break;
            case "K":
                valorReal = 13;
                break;
            case "As":
                valorReal = 14;
                break;
            default:
                valorReal = Integer.parseInt(valor);
                break;
        }
    }

    public void cartaGrafica(){
        String temp = "";
        switch (palo) {
            case "Diamantes":
                temp = "Diamantes";
                break;
            case "Espadas":
                temp = "Espadas";
                break;
            case "Corazon":
                temp = "Corazon";
                break;
            case "Trebol":
                temp = "Trebol";
                break;
            default:
                break;
        }

        if(valorReal != 14){
            cartaGrafico = valorReal + temp; 
        }else{
            cartaGrafico = "1" + temp; 
        }
    }

    public int compareTo(Carta o){
        if(this.getValorReal() > o.getValorReal()){
            return 1;
        }else if(this.getValorReal() < o.getValorReal()){
            return -1;
        }else if(this.getValorReal() == o.getValorReal()){
            return 0;
        }
        return 10;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getValorReal() {
        return valorReal;
    }

    public void setValorReal(int valorReal) {
        this.valorReal = valorReal;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getCartaGrafico() {
        return cartaGrafico;
    }

    public void setCartaGrafico(String cartaGrafico) {
        this.cartaGrafico = cartaGrafico;
    }

    @Override
    public String toString() {
        return "[" +valor + "|" + palo + "]";
    }

}
