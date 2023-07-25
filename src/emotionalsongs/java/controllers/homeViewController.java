package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.ColorsManager;
import views.PlaylistBox;

public class homeViewController {

    @FXML
    private HBox primaryShader;

    @FXML
    private VBox contentContainer;

    @FXML
    private HBox playlistBoxContainer;

    @FXML
    private FlowPane albumBoxContainer;

    @FXML
    private TableView<?> tableViewTopClicked;

    @FXML
    private HBox othersPlaylistBoxContainer;

    @FXML
    private void initialize() {

        CornerRadii cornerRadii = new CornerRadii(8, 8, 0, 0, false);
        BackgroundFill backgroundFill = new BackgroundFill(
                ColorsManager.setGradient(Color.rgb(62, 32, 146, 0.6), Color.rgb(18, 18, 18)),
                cornerRadii,
                null);
        Background background = new Background(backgroundFill);
        primaryShader.setBackground(background);
        Platform.runLater(() -> {
            double width = (contentContainer.getBoundsInLocal().getWidth())-(contentContainer.getPadding().getLeft()*2);
            setView(playlistBoxContainer,othersPlaylistBoxContainer,width);
        });

        // coportamento desiderato (contoller figlio )

    }

    public void resizeHandler(double width) {
        PlaylistBoxWidthManager(playlistBoxContainer, width);
        PlaylistBoxWidthManager(othersPlaylistBoxContainer, width);
    }

    private void setView(HBox hbox1,HBox hbox2, double width) {
        // calcoli
        double minItemWidth = PlaylistBox.MinWidth;
        double maxItemWidth = PlaylistBox.MaxWidth;
        double preferdItemWidth;

        preferdItemWidth = ((maxItemWidth + minItemWidth) / 2);
        int num_itemsFit = (int) (width / preferdItemWidth);
        double requested_Width = (num_itemsFit * preferdItemWidth) + ((num_itemsFit - 1) * hbox1.getSpacing());
        if (requested_Width > width) {
            num_itemsFit--;
        }
        for (int i = 0; i < num_itemsFit; i++) {
            VBox playlistbox1 = new PlaylistBox();
            VBox playlistbox2 = new PlaylistBox();
            HBox.setHgrow(playlistbox1, Priority.ALWAYS);
            HBox.setHgrow(playlistbox2, Priority.ALWAYS);
            hbox1.getChildren().add(playlistbox1);
            hbox2.getChildren().add(playlistbox2);
        }

    }

    private void PlaylistBoxWidthManager(HBox hbox, double width) {
        double hboxFullWidth= hbox.getWidth();
        double spacingWidth = (hbox.getChildren().size()-1)*hbox.getSpacing();
        double TotalNodeWidth= 0;
        boolean minReached = false;
        for(Node node: hbox.getChildren()){
            if(node instanceof VBox){
                VBox child = (VBox) node;
                double childWidth =child.getWidth();
                TotalNodeWidth += childWidth;
                if( child.getMinWidth()+10 >= childWidth)
                {
                    minReached = true;
                }
            }
        }
        System.out.println("Width disponibile : " +(hboxFullWidth-(spacingWidth+TotalNodeWidth)));
        if((hboxFullWidth-(spacingWidth+TotalNodeWidth))>55){
            VBox playlistbox = new PlaylistBox();
            HBox.setHgrow(playlistbox, Priority.ALWAYS);
            hbox.getChildren().add(playlistbox);
        }else if(minReached){
            hbox.getChildren().remove(hbox.getChildren().size()-1);
        }
    }

    private void setFlowPane(FlowPane flowpane, double width) {

    }

}
