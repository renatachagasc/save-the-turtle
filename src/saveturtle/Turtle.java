/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveturtle;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author 20171148060029
 */
public class Turtle {
    private int x, y;
    private int dx, dy;
    private int altura, largura;    

    private Image imagem;
    
    private List<Bolha> bolhas;

    
    public Turtle(){
        ImageIcon referencia = new ImageIcon("turtle.png");
        imagem = referencia.getImage();
        
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        
        bolhas = new ArrayList<Bolha>();        
        
        this.x = 100;
        this.y = 100;
        
    }

    public List<Bolha> getBolhas() {
        return bolhas;
    }
    
    public void movimentar(){
        x += dx; // 1 e 462
        y += dy; // 1 e 340

        if(this.x < 1){
            x = 1;
        }
        if(this.x > 525){
            x = 525;
        }
        if(this.y < 1){
            y = 1;
        }
        if(this.y > 320){
            y = 320;
        }
    }
    
    public int getX() {
        return x;
    }

    
    public int getY() {
        return y;
    }

    
    public Image getImagem() {
        return imagem;
    }
    public void soltarBolhas(){
        this.bolhas.add(new Bolha(x + largura, y  + altura /8));
    }
    
    public void keyPressed(KeyEvent tecla){
        
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_SPACE){
            soltarBolhas();
        }
        
        if (codigo == KeyEvent.VK_UP){
            dy = -1;            
        }
        if (codigo == KeyEvent.VK_DOWN){
            dy = 1;            
        }
        if (codigo == KeyEvent.VK_LEFT){
            dx = -1;            
        }
        if (codigo == KeyEvent.VK_RIGHT){
            dx = 1;            
        }
        
    }
        public void keyReleased(KeyEvent tecla){
            
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_UP){
            dy = 0;            
        }
        if (codigo == KeyEvent.VK_DOWN){
            dy = 0;            
        }
        if (codigo == KeyEvent.VK_LEFT){
            dx = 0;            
        }
        if (codigo == KeyEvent.VK_RIGHT){
            dx = 0;            
        }
        
    }
}

