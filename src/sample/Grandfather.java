package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Grandfather implements Client {
    ArrayList<Image> icons = new ArrayList<Image>();

    public Grandfather() {
        this.icons.add(new Image("./resources/CafeNew/Grandfather/Grandfather-01.png"));
        this.icons.add(new Image("./resources/CafeNew/Grandfather/Grandfather-02.png"));
        this.icons.add(new Image("./resources/CafeNew/Grandfather/Grandfather-03.png"));
        this.icons.add(new Image("./resources/CafeNew/Grandfather/Grandfather-04.png"));
    }

    @Override
    public Image getEmotion(int i) {
/*        if(i <= 0)
            return icons.get(3);*/
        return icons.get(i);
    }

    @Override
    public void setIcons(){

    }
}
