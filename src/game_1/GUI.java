package game_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author minxr
 */
class GUI {
    private static int keys[];
    private int scale;
    
    JFrame frame;
    NPanel panel;
    Game game;
    
    public GUI(){
        scale = 1; //default scale. :)
        keys = new int[32];
        //generate all keys
        for (int i = 0; i < keys.length; i++)
            keys[i] = 'A' + i; //key codes correspond to char codes for letters at least
        keys[26] = ' ';
        keys[27] = '.';
        keys[28] = '!';
        keys[29] = '?';
        keys[30] = '"';
        keys[31] = '\'';
        
        panel = new NPanel();
        for (int i = 0; i < keys.length; i++){
            panel.getInputMap().put(KeyStroke.getKeyStroke(keys[i], 0, false), keys[i]);
            panel.getActionMap().put(keys[i], new KeyAction(keys[i]));
            
            panel.getInputMap().put(KeyStroke.getKeyStroke(keys[i], 0, true), 0);
            panel.getActionMap().put(0, new KeyAction(0));
        }
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), KeyEvent.VK_PAGE_DOWN);
        panel.getActionMap().put(KeyEvent.VK_PAGE_DOWN, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                scale--;
                if (scale < 1)
                    scale = 1;
                frame.pack();
                frame.repaint();
            }
        });
        
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), KeyEvent.VK_PAGE_UP);
        panel.getActionMap().put(KeyEvent.VK_PAGE_UP, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                scale++;
                frame.pack();
                frame.repaint();
            }
        });
            
        
        frame = new JFrame();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game = new Game();
    }
    
    private class KeyAction extends AbstractAction {

        private final int k;
        
        public KeyAction(int key){
            k = key;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            game.input(k);
            game.update(); //only updates when input changes, soo...
            frame.repaint();
        }
        
    }
    
    private class NPanel extends JPanel {
        
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(256 * scale, 192 * scale);
        }
        
        
        @Override
        public void paintComponent(Graphics g){
            //g.clearRect(0, 0, WIDTH, HEIGHT);
            game.draw(g, scale);
        }
    }
    
}
