package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Woman implements Client {
    ArrayList<Image> icons = new ArrayList<Image>();

    public Woman() {
        this.icons.add(new Image("./resources/CafeNew/Woman/Woman-01.png"));
        this.icons.add(new Image("./resources/CafeNew/Woman/Woman-02.png"));
        this.icons.add(new Image("./resources/CafeNew/Woman/Woman-03.png"));
        this.icons.add(new Image("./resources/CafeNew/Woman/Woman-04.png"));
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
