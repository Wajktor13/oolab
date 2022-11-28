package agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private final VBox box = new VBox();

    public GuiElementBox(int imageSize, String imageUrl){
        try {
            Image image = new Image(new FileInputStream(imageUrl));
            ImageView view = new ImageView(image);
            view.setFitHeight(imageSize);
            view.setFitWidth(imageSize);

            this.box.getChildren().add(view);
            this.box.setAlignment(Pos.CENTER);

        } catch (FileNotFoundException err) {
            System.out.printf(err.toString() + "'%s'%n", imageUrl);
        }
    }

    public VBox getBox(){
        return box;
    }
}
