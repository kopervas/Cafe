package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public interface Client {
    ArrayList<Image> icons = new ArrayList<Image>();

/*    private ImageView happy;
    private ImageView comfortable;
    private ImageView sad;
    private ImageView evil;*/

    Image getEmotion(int i);
    void setIcons();

}
