package util;

import pkg.Track;

import java.util.concurrent.CompletableFuture;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomCell extends TableCell<Track, Void> {
    private final HBox hbox = new HBox();
    private final ImageView imageView = new ImageView();
    private final Label trackNameLabel = new Label();
    private final Label authorNameLabel = new Label();

    public CustomCell() {
        hbox.getStyleClass().add("hboxCustomColumn");
        hbox.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        hbox.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        hbox.setSpacing(5);
        hbox.setTranslateY(2);
        VBox tracksdetails = new VBox();
        tracksdetails.getStyleClass().add("track_details");
        authorNameLabel.getStyleClass().add("authorsNames");
        trackNameLabel.getStyleClass().add("trackName");
        tracksdetails.getChildren().addAll(trackNameLabel, authorNameLabel);
        hbox.getChildren().addAll(imageView, tracksdetails);

    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            Track track = getTableView().getItems().get(getIndex());

            Platform.runLater(() -> {
                String url = track.getAlbum_img2S();
                ImageLoader imageLoader = new ImageLoader();
                CompletableFuture<Image> imageFuture = imageLoader.loadImageAsync(url);
                
                imageFuture.thenAcceptAsync(image ->{
                    if(image != null){
                        imageView.setImage(image);
                        imageView.setFitHeight(43);
                        imageView.setFitWidth(43);
                        imageView.setPreserveRatio(false);
                    }else
                        imageView.setImage(imageLoader.getPlaceHolderImage());

                });

                //Image image = new Image(url);

                // Set track name and author
                trackNameLabel.setText(track.getName());
                authorNameLabel.setText(track.getArtist_name());
            });

            setGraphic(hbox);
        }
    }
}
