package Desenhos;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Desenho extends JFrame {
    public Desenho(){
        setResizable(false);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //BOTAO TRANSLADAR PARA CIMA
        setLayout(null);
        add(b1_cima);
        b1_cima.setBounds(580,50,100,50);
        b1_cima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transladar=transladar-50;
                repaint();
            }
        });

        //BOTAO TRANSLADAR PARA BAIXO
        setLayout(null);
        add(b2_baixo);
        b2_baixo.setBounds(580,110,100,50);
        b2_baixo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transladar=transladar+50;
                repaint();
            }
        });

        //JLABEL TRANSLADAR
        add(LTransladar);
        LTransladar.setBounds(600,10,100,50);
    }

    //BOTOES
    JButton b1_cima = new JButton("Cima");
    JButton b2_baixo = new JButton("Baixo");
    JLabel LTransladar = new JLabel("Transladar");

    private static int POINT_SIZE = 3;
    public static String qtdRetas;
    Integer retas=Integer.parseInt(qtdRetas);
    int x;
    int y;
    int x1;
    int y1;
    int x2;
    int y2;
    int transladar;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCircle(g, 350, 350+transladar, 100, retas, Color.BLACK);
    }
    public void drawCircle(Graphics g, int posicaoX, int posicaoY,
                           int raio, int quantidadeDePontos, Color cor) {

        double distanciaEntrePontos = 2 * Math.PI / quantidadeDePontos;

        for (int i = 0; i < quantidadeDePontos; i++) {
            double cos = Math.cos(i * distanciaEntrePontos);
            double sin = Math.sin(i * distanciaEntrePontos);

            x = (int) ( cos * raio + posicaoX );
            y = (int) ( sin * raio + posicaoY );
            if (i % 2 == 0) {
                x1=x;
                y1=y;
            } else {
                x2=x;
                y2=y;
            }
            g.setColor(cor);
            g.fillRect(x, y, POINT_SIZE, POINT_SIZE);
            g.drawLine(x2,y2,x1,y1);
        }
    }

    public static void escolherRetas() {
        int count=0;
        while(count==0){
            qtdRetas = JOptionPane.showInputDialog("Digite um numero par de retas para criação da elipse");
            if (Integer.parseInt(qtdRetas)>3){
                if (Integer.parseInt(qtdRetas) % 2 == 0) {
                    JOptionPane.showMessageDialog(null,"Você escolheu formar uma figura com "+qtdRetas+" retas");
                    count++;
                } else {
                    JOptionPane.showMessageDialog(null, "Valor digitado não é par");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Quantidades de retas preprecisam ser maior que 3 para formar uma figura Geometrica");
            }
        }
    }
}