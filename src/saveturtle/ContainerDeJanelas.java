/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saveturtle;

import javax.swing.JFrame;

/**
 *
 * @author 20171148060029
 */
public class ContainerDeJanelas extends JFrame{
    public ContainerDeJanelas(){
        
        add(new Cenario());
        setTitle("Save The Turtle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
    }    
}