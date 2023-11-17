file:///C:/Users/hp/Desktop/LabB/LabB2023/src/emotionalsongs/java/controllers/playlistViewController.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/hp/Desktop/LabB/LabB2023/src/emotionalsongs/java/controllers/playlistViewController.java
text:
```scala
package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pkg.Track;
import util.TableViewManager;

public class playlistViewController {

    @FXML
    private TextField cerca;

    @FXML
    private HBox buttonCont;

    @FXML
    private VBox container;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    @FXML
    private TextField playlistName;

    @FXML
    private VBox editcontainter;

    @FXML
    private VBox tableContainer;

    @FXML
    void initialize() {
        editcontainter.setVisible(false);
        Platform.runLater(() -> {
            double width = container.getWidth() - 10;
            initializeElements();
        });
    }

    private void initializeElements() {
        TableViewManager mainTable = new TableViewManager(true, false);
        TableViewManager addTable = new TableViewManager(false, true);
        TableViewManager editTable = new TableViewManager(false, false);

        mainTable.setMinHeight(400);
        addTable.setMinHeight(400);
        addTable.setVisible(false);
        editTable.setMinHeight(400);

        tableContainer.getChildren().add(0, mainTable);

        ObservableList<Track> data = FXCollections.observableArrayList(
                new Track("1", "Song 1", 180, "Artist 1", "Album 1", "img0", "img1", "img2"),
                new Track("2", "Song 2", 180, "Artist 2", "Album 2", "img3", "img4", "img5"),
                new Track("3", "Song 3", 180, "Artist 3", "Album 3", "img6", "img7", "img8"),
                new Track("4", "Song 4", 180, "Artist 4", "Album 4", "img9", "img10", "img11"),
                new Track("5", "Song 5", 180, "Artist 5", "Album 5", "img12", "img13", "img14")
        );
        ObservableList<Track> data2 = FXCollections.observableArrayList(
                new Track("1", "Song 1", 180, "Artist 1", "Album 1", "img0", "img1", "img2"),
                new Track("2", "Song 2", 180, "Artist 2", "Album 2", "img3", "img4", "img5"),
                new Track("3", "Song 3", 180, "Artist 3", "Album 3", "img6", "img7", "img8"),
                new Track("4", "Song 4", 180, "Artist 4", "Album 4", "img9", "img10", "img11"),
                new Track("5", "Song 5", 180, "Artist 5", "Album 5", "img12", "img13", "img14")
        );

        mainTable.setItems(data);
        editTable.setItems(data);
        addTable.setItems(data2);

        cerca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                addTable.setVisible(false); // Hide TableView when the TextField is empty
            } else {
                addTable.setVisible(true); // Show TableView when there is text in the TextField
            }
        });

        editButton.setOnMouseClicked(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                editButton.setDisable(true);
                editcontainter.setVisible(true);
                tableContainer.getChildren().remove(0);
                tableContainer.getChildren().add(0, editTable);
                Button button = new Button("Done");
                button.getStyleClass().add("button_cp");
                buttonCont.getChildren().add(1, button);

                button.setOnMouseClicked(param -> {
                    editcontainter.setVisible(false);
                    buttonCont.getChildren().remove(button);
                    editButton.setDisable(false);
                    tableContainer.getChildren().remove(0);
                    tableContainer.getChildren().add(0, mainTable);
                    tableContainer.getChildren().remove(addTable);
                });

                tableContainer.getChildren().add(2, addTable);
            }
        });

        deleteButton.setOnMouseClicked(event -> editcontainter.setVisible(false));
    }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:42)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:60)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:60)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:60)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:81)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:99)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator