package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Girl implements Client{
    ArrayList<Image> icons = new ArrayList<Image>();

    public Girl() {
        this.icons.add(new Image("./resources/CafeNew/Girl/Girl-01.png"));
        this.icons.add(new Image("./resources/CafeNew/Girl/Girl-02.png"));
        this.icons.add(new Image("./resources/CafeNew/Girl/Girl-03.png"));
        this.icons.add(new Image("./resources/CafeNew/Girl/Girl-04.png"));
    }

    @Override
    public Image getEmotion(int i) {
        if(i <= 0)
            return icons.get(3);
        return icons.get(1);
    }

    @Override
    public void setIcons(){

    }
}
