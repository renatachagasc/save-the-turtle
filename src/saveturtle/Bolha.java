/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveturtle;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author 20171148060029
 */
public class Bolha {
    
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;

    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 2;

    public Bolha(int x, int y){
       
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("bolha.png");
        imagem = referencia.getImage(); 

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);      

        isVisivel = true;

    }
    public void movimentar(){
        this.x += VELOCIDADE;
        if (this.x > LARGURA_TELA){
            isVisivel = false;
        }
        
    }
    
    public boolean isVisivel(){
        return isVisivel;
    }
    public void setVisivel (boolean isVisivel){
        this.isVisivel = isVisivel;
    }

    public Image getImagem(){
        return imagem;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }
}