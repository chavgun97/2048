package com.chavgun;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score;
    protected int maxTile;
    Model(){
        resetGameTiles();
    }
    protected void resetGameTiles(){
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        maxTile = 0;
        score = 0;
    }

    public Tile[][] getGameTiles(){
        return gameTiles;
    }
    public boolean canMove() {
        if(!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[j][i].value == gameTiles[j-1][i]. value)return true;
            }
        }
        return false;
    }

    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if(!emptyTiles.isEmpty()) {
            int rundomTile = (int) (emptyTiles.size() * Math.random());
            Tile tile = emptyTiles.get(rundomTile);
            tile.value = (Math.random() < 0.9) ? 2 : 4;
        }
    }




    private List<Tile> getEmptyTiles(){
        List emptyTiles = new ArrayList();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if(gameTiles[i][j].isEmpty()){
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }

        return emptyTiles;
    }
    //Cжатие первое
    private boolean compressTiles(Tile[] tiles){
        boolean isEdit = false;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j <tiles.length-1 ; j++) {
                if(tiles[j].isEmpty() && !tiles[j+1].isEmpty()){
                    isEdit = true;
                    Tile tTemp = tiles[j];
                    tiles[j] = tiles[j+1];
                    tiles[j+1] = tTemp;
                }
            }
        }
        return isEdit;
    }


    /*public static void main(String[] args) {
        Model model = new Model();
        while (true){

            System.out.println();
            model.print();
            model.right();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/
    //Слияние второе
    private boolean mergeTiles(Tile[] tiles){
        boolean isEdit = false;
        for (int i = 0; i < tiles.length-1; i++) {
            if(tiles[i].value == tiles[i+1].value && !tiles[i].isEmpty() && !tiles[i+1].isEmpty()){
                tiles[i].value +=  tiles[i+1].value;
                tiles[i+1].value = 0;
                isEdit = true;
                score += tiles[i].value;
                if(tiles[i].value > maxTile){
                    maxTile = tiles[i].value;
                }
            }
        }
        return isEdit;
    }
    public void left(){
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if ( mergeTiles(gameTiles[i]) | compressTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
        }
    }

    public void up(){
        turn90(1);
        left();
        turn90(3);
    }

    public void right(){
        turn90(2);
        left();
        turn90(2);
    }
    public void down(){
        turn90(3);
        left();
        turn90(1);
    }
    private void turn90(int y){
        for (int i = 0; i < y; i++) {


            Tile[][] rotMat = new Tile[FIELD_WIDTH][FIELD_WIDTH];

            for (int rw = 0; rw < FIELD_WIDTH; rw++)
                for (int cl = 0; cl < FIELD_WIDTH; cl++) {
                    rotMat[FIELD_WIDTH - 1 - cl][rw] = gameTiles[rw][cl];
                }

            gameTiles = rotMat;
        }
    }
     void print(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(gameTiles[i][j].value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
