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

        //BOTAO RODAR PARA DIREITA
        setLayout(null);
        add(b3_rodarDireita);
        b3_rodarDireita.setBounds(580,200,100,50);
        b3_rodarDireita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rodar=rodar+5;
                repaint();
            }
        });

        //BOTAO RODAR PARA ESQUERDA
        setLayout(null);
        add(b4_rodarEsquerda);
        b4_rodarEsquerda.setBounds(580,260,100,50);
        b4_rodarEsquerda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rodar=rodar-5;
                repaint();
            }
        });

        //JLABEL ROTAÇÃO
        add(LRotacao);
        LRotacao.setBounds(600,160,100,50);

        //BOTAO ESCALONAMENTO PARA AFICAR MAIOR
        setLayout(null);
        add(b5_escalaMaior);
        b5_escalaMaior.setBounds(580,350,100,50);
        b5_escalaMaior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escalonamento=escalonamento+5;
                repaint();
            }
        });

        //BOTAO ESCALONAMENTO PARA FICAR MENOR
        setLayout(null);
        add(b6_escalaMenor);
        b6_escalaMenor.setBounds(580,410,100,50);
        b6_escalaMenor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escalonamento=escalonamento-5;
                repaint();
            }
        });

        //JLABEL ROTAÇÃO
        add(LEscalonamento);
        LEscalonamento.setBounds(600,310,100,50);

        //BOTAO CISALHAMENTO NO EIXO Y
        setLayout(null);
        add(b7_escalaMaior);
        b7_escalaMaior.setBounds(580,500,100,50);
        b7_escalaMaior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cisalhamentoY=cisalhamentoY+5;
                repaint();
            }
        });

        //JLABEL CISALHAMENTO
        add(LCisalhamento);
        LCisalhamento.setBounds(590,460,100,50);

        //BOTAO RESETAR TODAS AS MUDANÇAS
        setLayout(null);
        add(b8_reset);
        b8_reset.setBounds(580,600,100,50);
        b8_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transladar=0;
                rodar=0;
                escalonamento=0;
                cisalhamentoY=0;
                repaint();
            }
        });
    }

    //BOTOES
    JButton b1_cima = new JButton("Cima");
    JButton b2_baixo = new JButton("Baixo");
    JLabel LTransladar = new JLabel("Transladar");
    JButton b3_rodarDireita = new JButton("Direita");
    JButton b4_rodarEsquerda = new JButton("Esquerda");
    JLabel LRotacao = new JLabel("Rotação");
    JButton b5_escalaMaior = new JButton("Maior");
    JButton b6_escalaMenor = new JButton("Menor");
    JLabel LEscalonamento = new JLabel("Escalonar");
    JButton b7_escalaMaior = new JButton("Cisalhar");
    JLabel LCisalhamento = new JLabel("Cisalhamento");
    JButton b8_reset = new JButton("Reset");



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
    int rodar;
    int escalonamento;
    int cisalhamentoY;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCircle(g, 350, 350+transladar, 100+escalonamento, retas, Color.BLACK);
    }
    public void drawCircle(Graphics g, int posicaoX, int posicaoY,
                           int raio, int quantidadeDePontos, Color cor) {

        double distanciaEntrePontos = 2 * Math.PI / quantidadeDePontos;

        for (int i = 0; i < quantidadeDePontos; i++) {
            double cos = Math.cos((i+rodar) * distanciaEntrePontos);
            double sin = Math.sin((i+rodar+cisalhamentoY) * distanciaEntrePontos);

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