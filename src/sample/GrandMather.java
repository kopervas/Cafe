package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class GrandMather implements Client {
    ArrayList<Image> icons = new ArrayList<Image>();

    public GrandMather() {
        this.icons.add(new Image("./resources/CafeNew/GrandMather/GrandMather-01.png"));
        this.icons.add(new Image("./resources/CafeNew/GrandMather/GrandMather-02.png"));
        this.icons.add(new Image("./resources/CafeNew/GrandMather/GrandMather-03.png"));
        this.icons.add(new Image("./resources/CafeNew/GrandMather/GrandMather-04.png"));
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
