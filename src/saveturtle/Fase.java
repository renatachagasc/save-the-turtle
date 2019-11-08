/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveturtle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author 20171148060029
 */
public class Fase extends JPanel implements ActionListener{
    
    private Image background;
    private Turtle turtle;
    private Timer timer;

    public Fase(){
        
        setFocusable(true);
        setDoubleBuffered(true);    
        
        addKeyListener(new TecladoAdapter());   
        
        ImageIcon referencia = new ImageIcon("background.jpg");        
        background = referencia.getImage();       
        
        turtle = new Turtle();
        
        timer = new Timer (5, this);
        timer.start();
    }
    public void paint(Graphics g){
        
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(background, 0, 0, null);
        graficos.drawImage(turtle.getImagem(), turtle.getX(), turtle.getY(), this);
        
        List<Bolha> bolhas = turtle.getBolhas();
        
        for(int i = 0; i < bolhas.size(); i++){        
            Bolha b = (Bolha) bolhas.get(i);
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        List<Bolha> bolhas = turtle.getBolhas();
        for(int i = 0; i < bolhas.size(); i++){        
            Bolha b = (Bolha) bolhas.get(i);
            if (b.isVisivel()){
                b.movimentar();
            }else {
                bolhas.remove(i);
            }
        }
        turtle.movimentar();
        repaint();
    }
    
    private class TecladoAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            turtle.keyPressed(e); 
        }
        @Override
        public void keyReleased(KeyEvent e) {
            turtle.keyReleased(e); 
        }
        
    }
    
    
}
