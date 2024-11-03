import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import java.applet.*;

public class GuerraInterfaz {
    private Guerra guerra;
    private HashMap <String, ImageIcon> cartasHashMap;
    private int rondas;
    private JFrame frame = new JFrame("Guerra");
    private JButton jugar = new JButton("Jugar");
    private JTextArea cartasJ1 = new JTextArea();
    private JTextArea cartasJ2 = new JTextArea();
    private JLabel labelNumCartasJ1;
    private JLabel labelNumCartasJ2;
    private JLabel labelJugador1 = new JLabel("Jugador 1");
    private JLabel labelJugador2 = new JLabel("Jugador 2");
    private Color colorVerde;
    private Color colorRojo;
    private JLabel labelGuerra = new JLabel("Juego GUERRA");
    private JLabel indicarRondas = new JLabel();
    private JLabel guerraEstatus = new JLabel();
    private JLabel labelCartaJ1 = new JLabel();
    private JLabel labelCartaJ2 = new JLabel();

    public GuerraInterfaz(){
        boolean flag = false;
        colorVerde = new Color(52, 107, 11);
        colorRojo = new Color(144, 12, 63);
        cartasHashMap = new HashMap();
        imagenesCartas();
        labelGuerra.setBounds(320, 10, 400, 100);
        labelGuerra.setForeground(Color.WHITE);
        labelGuerra.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelJugador1.setBounds(50, 90, 120, 40);
        labelJugador1.setForeground(Color.WHITE);
        labelJugador2.setBounds(590, 660, 120, 40);
        labelJugador2.setForeground(Color.WHITE);

        frame.add(labelJugador1);
        frame.add(labelJugador2);
        frame.add(labelGuerra);

        while(!flag){
            try{
                rondas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de rondas"));
                if(rondas < 1){
                    JOptionPane.showMessageDialog(null, "No puedes tener esas rondas!",  "Error", JOptionPane.WARNING_MESSAGE);
                }else{
                    flag = true;
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No puedes tener esas rondas!",  "Error", JOptionPane.WARNING_MESSAGE);

            }
        }

        guerra = new Guerra(rondas);
        indicarRondas = new JLabel("Rondas: " + rondas);
        indicarRondas.setBounds(50, 300, 130, 20);
        indicarRondas.setForeground(Color.WHITE);
        frame.add(indicarRondas);

        guerraEstatus.setBounds(50, 400, 250, 50);
        guerraEstatus.setForeground(Color.WHITE);
        frame.add(guerraEstatus);

        labelNumCartasJ1 = new JLabel("Cartas restantes: ");
        labelNumCartasJ1.setBounds(120, 90, 120, 40);
        labelNumCartasJ1.setForeground(Color.WHITE);
        frame.add(labelNumCartasJ1);

        labelNumCartasJ2 = new JLabel("Cartas restantes: ");
        labelNumCartasJ2.setBounds(450, 660, 120, 40); 
        labelNumCartasJ2.setForeground(Color.WHITE);
        frame.add(labelNumCartasJ2); 

        labelCartaJ1.setBounds(300, 300, 100, 160);
        frame.add(labelCartaJ1);
        labelCartaJ2.setBounds(470, 300, 100, 160);
        frame.add(labelCartaJ2);

        labelNumCartasJ1.setText("Cartas restantes: " + guerra.numeroCartasJugador(1));
        labelNumCartasJ2.setText("Cartas restantes: " + guerra.numeroCartasJugador(2));
        jugar.setBounds(50, 350, 100, 40);
        jugar.addActionListener(new ActionListener() {
            @SuppressWarnings("removal")
            @Override
            public void actionPerformed(ActionEvent e){
                if(rondas > 0){
                    if(guerra.getEstadoGuerra().equals("HAY GUERRA")){
                        guerra.tomarCartasGuerra();
                        guerra.tomarCartas();
                        mostrarCartasJugadores();
                        guerra.compararCartas();
                        guerraEstatus.setText(guerra.getEstadoGuerra());
                        System.out.println(guerra.getEstadoGuerra());
                        guerra.darCartasGanador(); 
                        frame.getContentPane().setBackground(colorVerde);
                        frame.repaint();
                        frame.revalidate();                       
                    }else{
                        AudioClip sound;
                        sound = java.applet.Applet.newAudioClip(getClass().getResource("flipcard-91468.wav"));
                        sound.play();
                        guerra.tomarCartas();
                        mostrarCartasJugadores();
                        guerra.compararCartas();
                        guerraEstatus.setText(guerra.getEstadoGuerra());
                        System.out.println(guerra.getEstadoGuerra());
                        String estadoString = guerra.getEstadoGuerra();
                        if(estadoString.equals("El jugador 1 ganó la ronda")){
                            guerraEstatus.setText(guerra.getEstadoGuerra());
                            guerra.darCartasGanador();
                        }else if(estadoString.equals("El jugador 2 ganó la ronda")){
                            guerraEstatus.setText(guerra.getEstadoGuerra());
                            guerra.darCartasGanador();
                        }else{
                            frame.getContentPane().setBackground(colorRojo);
                            frame.repaint();
                            frame.revalidate();
                        } 
                        rondas--;
                    }
                    System.out.println(guerra.numeroCartasJugador(1));
                    System.out.println(guerra.numeroCartasJugador(2));
                    labelNumCartasJ1.setText("Cartas restantes: " + guerra.numeroCartasJugador(1));
                    labelNumCartasJ2.setText("Cartas restantes: " + guerra.numeroCartasJugador(2));
                    indicarRondas.setText("Rondas: " + rondas);
                    System.out.println("Cartas jugador 1: " + guerra.getJugador1().getManoJugador().mostrarListaRecursivo());
                    System.out.println("Cartas jugador 2: " + guerra.getJugador2().getManoJugador().mostrarListaRecursivo());
                }else{
                    guerraEstatus.setText("Empate");
                }
            }
        });
        frame.add(jugar);
        frame.setLayout(null);
        frame.setSize(850, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(colorVerde);
        dibujarCartasTablero(guerra.getJugador1(), guerra.getJugador2());
        frame.setVisible(true);
    }

    private void imagenesCartas(){
        for(int i = 1; i < 14; i++){
            cartasHashMap.put(i + "Corazon", new ImageIcon(i + "Corazon.png"));
            cartasHashMap.put(i + "Trebol", new ImageIcon(i + "Trebol.png"));
            cartasHashMap.put(i + "Espadas", new ImageIcon(i+ "Espadas.png"));
            cartasHashMap.put(i + "Diamantes", new ImageIcon(i + "Diamantes.png"));
        }
        cartasHashMap.put("CartaVolteada", new ImageIcon("CartaVolteada.png"));
    }

    private void mostrarCartas(JLabel label, String carta, boolean isVisible, int ancho, int alto){
        ImageIcon icon = isVisible ? ajustarTamañoCartas(carta, ancho, alto) : ajustarTamañoCartas("CartaVolteada", ancho, alto);
        label.setIcon(icon);
    }

    private void dibujarCartasTablero(Jugador jugador1, Jugador jugador2){
        int xPosition = 0;
        for(int i = 0; i < 7; i++){
            JLabel label = new JLabel();
            label.setBounds(50 + xPosition, 120, 100, 160);
            frame.add(label);
            mostrarCartas(label, "1Diamantes", false, 100, 160);
            xPosition = xPosition + 50;
        }
        xPosition = 0;
        for(int i = 0; i < 7; i++){
            JLabel label = new JLabel();
            label.setBounds(300 + xPosition, 500, 100, 160);
            frame.add(label);
            mostrarCartas(label, "1Trebol", false, 100, 160);
            xPosition = xPosition + 50;
        }

        frame.revalidate();
        frame.repaint();
    }

    private ImageIcon ajustarTamañoCartas(String carta, int ancho, int alto){
        ImageIcon icon = cartasHashMap.get(carta);
        if (icon != null) {
            Image img = icon.getImage(); 
            Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); 
            return new ImageIcon(imgEscalada);
        }
        return null;
    }

    public void mostrarCartasJugadores(){
        mostrarCarta(labelCartaJ1, guerra.getCartaJ1().getCartaGrafico(), true);
        mostrarCarta(labelCartaJ2, guerra.getCartaJ2().getCartaGrafico(), true);
    }

    public void mostrarCarta(JLabel label, String cartaString, boolean isVisible){
        ImageIcon icon = cartasHashMap.get(cartaString);
        if(isVisible == true){
            icon = cartasHashMap.get(cartaString);
        }else{
            icon = cartasHashMap.get("CartaVolteada");
        }
        Image imagen = icon.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagenRedimensionada));
    }

    public static void main(String[] args) {
        GuerraInterfaz guerraInterfaz = new GuerraInterfaz();

    }
}
