package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    private final VBox box = new VBox();

    public GuiElementBox(int imageSize, IMapElement element){
        try {
            imageSize = Math.floorDiv(imageSize, 2);
            int fontSize = Math.floorDiv(imageSize, 3);

            Image image = new Image(new FileInputStream(element.getImageUrl()));
            ImageView view = new ImageView(image);
            view.setFitHeight(imageSize);
            view.setFitWidth(imageSize);

            Label NameLbl = new Label(element.toString());
            NameLbl.setTextFill(Color.color(1, 1, 1));
            NameLbl.setFont(new Font("Arial", fontSize));

            Label PositionLbl = new Label(element.getPosition().toString());
            PositionLbl.setTextFill(Color.color(1, 1, 1));
            PositionLbl.setFont(new Font("Arial", fontSize));

            this.box.getChildren().addAll(view, NameLbl, PositionLbl);
            this.box.setAlignment(Pos.CENTER);

        } catch (FileNotFoundException err) {
            System.out.printf(err + "'%s'%n", element.getImageUrl());
        }
    }

    public VBox getBox(){
        return this.box;
    }
}
