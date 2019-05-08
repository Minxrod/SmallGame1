package game_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author minxr
 */
class Game {
    private int keys;
    private int score;
    private int scoreInc;
    private String text;
    
    public Game(){
        text = "THIS IS A FUN GAME.";
    }
    
    public void input(int buttons){
        keys = buttons;
    }
    
    public void update(){
        if (keys == 0)
            return; //only check actual keypresses, not release.
        
        if (keys == text.charAt(0)){
            text = text.substring(1);
            score += scoreInc;
            scoreInc++;
        } else {
            score-=scoreInc;//lose points on incorrect guess
            if (score < 0)
                score = 0; //no negative score.
            
            if (scoreInc > 1) //needs to be above 1 or else score would get stuck sometimes
                scoreInc--;
        }
        
        if (text.isEmpty())
            text = generate(score + 12);
    }
    
    public void draw(Graphics grp, int scale) {
        BufferedImage img = new BufferedImage(256, 192, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        
        g.setColor(Color.getHSBColor((score%1024)/1024f, 1f, 1f));
        g.fillRect(0, 0, 256, 192);
        g.setColor(Color.BLACK);
        g.drawString("TYPE THE CHRACTERS BELOW...", 0, 12);
        
        g.drawString(text, 0, 24); //when will I learn text draws from the bottom? who knows?
        g.drawString("SCORE: " + score, 0, 36);
        
        grp.drawImage(img, 0, 0, 256 * scale, 192 * scale, null);
    }

    /**
     * Generates a new string of random characters.
     * @param amount
     * @return 
     */
    private String generate(int amount) {
        String generated = "";
        for (int i = 0; i < amount; i++)
            generated += (char)((Math.random() * 26) + 'A');
        return generated;
    }
    
}
