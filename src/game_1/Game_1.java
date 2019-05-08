/*
    Version 1.0 of random typing game "GAME_1"
*/
package game_1;

import javax.swing.SwingUtilities;

public class Game_1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                runGUI();
            }
        });
    }

    public static void runGUI(){
        GUI gui = new GUI();
    }
    
}
