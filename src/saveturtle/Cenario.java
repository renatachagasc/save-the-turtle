/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveturtle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author 20171148060029
 */
public class Cenario extends JPanel implements ActionListener {

    private Image background;
    private Turtle turtle;
    private Timer timer;

    private boolean emJogo;
    private boolean venceu;

    private List<LixoMaritimo> lixos;

    private int[][] coordenadas = {
        {2380, 29}, {2600, 59}, {1380, 89},
        {780, 109}, {580, 139}, {880, 239}, {790, 259},
        {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
        {920, 300}, {856, 328}, {456, 320},
        {2380, 29}, {2600, 59}, {1380, 89},
        {780, 109}, {580, 139}, {880, 239}, {790, 259},
        {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
        {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
        {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
        {920, 300}, {856, 328}, {456, 320},
        {2380, 29}, {2600, 59}, {1380, 89},
        {780, 109}, {580, 139}, {880, 239}, {790, 259},
        {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
        {920, 300}, {856, 328}, {456, 320},
        {2380, 29}, {2600, 59}, {1380, 89},
        {780, 109}, {580, 139}, {880, 239}, {790, 259},
        {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
        {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
        {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
        {920, 300}, {856, 328}, {456, 320},};

    public Cenario() {

        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        ImageIcon referencia = new ImageIcon("background.jpg");
        background = referencia.getImage();
        turtle = new Turtle();

        emJogo = true;
        venceu = true;
        
        recolherLixo();

        timer = new Timer(5, this);
        timer.start();
    }

    public void recolherLixo() {
        lixos = new ArrayList<LixoMaritimo>();

        for (int i = 0; i < coordenadas.length; i++) {
            lixos.add(new LixoMaritimo(coordenadas[i][0], coordenadas[i][1]));
        }
    }

    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(background, 0, 0, null);

        if (emJogo) {

            graficos.drawImage(turtle.getImagem(), turtle.getX(), turtle.getY(), this);

            List<Bolha> bolhas = turtle.getBolhas();

            for (int i = 0; i < bolhas.size(); i++) {
                Bolha b = (Bolha) bolhas.get(i);
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }
            for (int i = 0; i < lixos.size(); i++) {
                LixoMaritimo o = lixos.get(i);
                graficos.drawImage(o.getImagem(), o.getX(), o.getY(), this);
            }

        } else {
            ImageIcon fimJogo = new ImageIcon("gover_restart.png");
            graficos.drawImage(fimJogo.getImage(), 0, 0, this);

        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (lixos.size() == 0) {
            emJogo = false;
        }

        List<Bolha> bolhas = turtle.getBolhas();
        for (int i = 0; i < bolhas.size(); i++) {
            Bolha b = (Bolha) bolhas.get(i);
            if (b.isVisivel()) {
                b.movimentar();
            } else {
                bolhas.remove(i);
            }
        }
        for (int i = 0; i < lixos.size(); i++) {
            LixoMaritimo o = lixos.get(i);
            if (o.isVisivel()) {
                o.movimentar();
            } else {
                lixos.remove(i);
            }
        }
        turtle.movimentar();
        checarColisoes();
        repaint();
    }

    public void checarColisoes() {

        Rectangle formTurtle = turtle.getBounds();
        Rectangle formLixo;
        Rectangle formBolha;

        for (int i = 0; i < lixos.size(); i++) {
            LixoMaritimo tempOleo = lixos.get(i);
            formLixo = tempOleo.getBounds();

            if (formTurtle.intersects(formLixo)) {

                turtle.setVisivel(false);
                tempOleo.setVisivel(false);

                emJogo = false;
            }
        }
        List<Bolha> bolhas = turtle.getBolhas();

        for (int i = 0; i < bolhas.size(); i++) {

            Bolha tempBolha = bolhas.get(i);
            formBolha = tempBolha.getBounds();

            for (int j = 0; j < lixos.size(); j++) {

                LixoMaritimo tempLixo = lixos.get(j);
                formLixo = tempLixo.getBounds();

                if (formBolha.intersects(formLixo)) {
                    tempLixo.setVisivel(false);
                    tempBolha.setVisivel(false);
                }
            }
        }
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                emJogo = true;
                turtle = new Turtle();
                recolherLixo();
            }
            turtle.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            turtle.keyReleased(e);
        }

    }

}
