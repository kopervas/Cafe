package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Boy implements Client {
    ArrayList<Image> icons = new ArrayList<Image>();

    public Boy() {
        this.icons.add(new Image("./resources/CafeNew/Boy/Boy-01.png"));
        this.icons.add(new Image("./resources/CafeNew/Boy/Boy-02.png"));
        this.icons.add(new Image("./resources/CafeNew/Boy/Boy-03.png"));
        this.icons.add(new Image("./resources/CafeNew/Boy/Boy-04.png"));
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
