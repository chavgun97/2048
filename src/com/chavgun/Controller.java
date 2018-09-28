package com.chavgun;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private final static int WINNING_TILE = 2048;
    private Model model;

    public View getView() {
        return view;
    }

    private View view;

    Controller(Model model){
        this.model = model;
        view = new View(this);
    }


    public Tile[][] getGameTiles(){
        return model.getGameTiles();
    }
    public int getScore(){
        return model.score;
    }


    public void resetGame(){
        model.score = 0;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();

    }

    @Override
   public void keyPressed(KeyEvent keyEvent){
        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
            resetGame();
        }
        if(!model.canMove())
            view.isGameLost = true;

        if(!view.isGameLost && !view.isGameWon){
            switch (keyEvent.getKeyCode()){

                case KeyEvent.VK_LEFT :
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;


            }
        }
        if(model.maxTile == WINNING_TILE){
            view.isGameWon = true;
        }
        view.repaint();


    }
}
