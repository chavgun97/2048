package com.chavgun;

import java.awt.*;

public class Tile {
    //Плитка
    int value;

    Tile(int value){
        this.value = value;
    }

    public Tile() {
        this.value = 0;
    }

    public boolean isEmpty(){
        return this.value == 0;
    }

    public Color getFontColor(){
        return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }

    public Color getTileColor(){
        Integer integer = 0xff0000;
        switch (value){
            case 0:
                integer = 0xcdc1b4;
                break;
            case 2:
                integer = 0xeee4da;
                break;
            case 4:
                integer = 0xede0c8;
                break;
            case 8:
                integer = 0xf2b179;
                break;
            case 16:
                integer = 0xf59563;
                break;
            case 32:
                integer = 0xf67c5f;
                break;
            case 64:
                integer = 0xf65e3b;
                break;
            case 128:
                integer = 0xedcf72;
                break;
            case 256:
                integer = 0xedcc61;
                break;
            case 512:
                integer = 0xedc850;
                break;
            case 1024:
                integer = 0xedc53f;
                break;
            case 2048:
                integer = 0xedc22e;
                break;
        }


        return new Color(integer);
    }

}
