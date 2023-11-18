package util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import tmp.Canzone;

public class CustomCell extends TableCell<Canzone,Void> {
        private final HBox hbox = new HBox();
        private final ImageView imageView = new ImageView();
        private final Label trackNameLabel = new Label();
        private final Label authorNameLabel = new Label();

        public CustomCell() {
            hbox.getStyleClass().add("hboxCustomColumn");
            hbox.setMaxSize(USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
            hbox.setMinSize(USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
            hbox.setSpacing(5);
            hbox.setTranslateY(2);
            VBox tracksdetails = new VBox();
            tracksdetails.getStyleClass().add("track_details");
            authorNameLabel.getStyleClass().add("authorsNames");
            trackNameLabel.getStyleClass().add("trackName");
            tracksdetails.getChildren().addAll(trackNameLabel,authorNameLabel);
            hbox.getChildren().addAll(imageView,tracksdetails);

        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                Canzone track = getTableView().getItems().get(getIndex());
                

                //Set image
                String url = getClass().getResource("/imgs/playlist_img/img2.png").toExternalForm();
                Image image = new Image(url);
                imageView.setFitHeight(43);
                imageView.setFitWidth(43);
                imageView.setPreserveRatio(false);
                imageView.setImage(image);

                // Set track name and author
                trackNameLabel.setText(track.getTrackName());
                authorNameLabel.setText(track.getTrackAlbum());
                

                setGraphic(hbox);
            }
        }
}





