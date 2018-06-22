package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Man implements Client {
    ArrayList<Image> icons = new ArrayList<Image>();

    public Man() {
        this.icons.add(new Image("./resources/CafeNew/Man/Man-01.png"));
        this.icons.add(new Image("./resources/CafeNew/Man/Man-02.png"));
        this.icons.add(new Image("./resources/CafeNew/Man/Man-03.png"));
        this.icons.add(new Image("./resources/CafeNew/Man/Man-04.png"));
    }

    @Override
    public Image getEmotion(int i) {
        /*if(i <= 0)
            return icons.get(3);*/
        return icons.get(i);
    }

    @Override
    public void setIcons(){

    }
}
