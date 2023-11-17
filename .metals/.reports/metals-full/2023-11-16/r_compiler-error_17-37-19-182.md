file:///C:/Users/hp/Desktop/LabB/LabB2023/src/emotionalsongs/java/controllers/canzoneViewController.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/hp/Desktop/LabB/LabB2023/src/emotionalsongs/java/controllers/canzoneViewController.java
text:
```scala
package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import tmp.Canzone;

public class canzoneViewController {



    Canzone c = null;

    @FXML
    private VBox rootCanzoneview;
    @FXML
    private Label nomeCanzone;
    @FXML
    private Label nomeAlbum;
    @FXML
    private FlowPane containerEvaluation;




    @FXML
    private void initialize(){

        createEvalBoxes();

    }

    public void setCanzone(Canzone canzone){
        c=canzone;
    }

    public void createEvalBoxes(){
        String[] emotions = {"Amazement","Solemnity","Tenderness","Nostalgia","Calmness","Power","Joy","Tension","Sadness"};
        for (String string : emotions) {

            VBox boxEmozione = new VBox();
            boxEmozione.setAlignment(Pos.CENTER);
            boxEmozione.setSpacing(5);
            boxEmozione.setPrefHeight(100);
            Label tipoEmozione = new Label(string);
            ChoiceBox<Integer> choiceBox = new ChoiceBox<Integer>(FXCollections.observableArrayList(1, 2, 3, 4, 5));
            choiceBox.getStyleClass().add("choiceBox");
            choiceBox.setPrefWidth(100);
            boxEmozione.getChildren().addAll(tipoEmozione, choiceBox);
            containerEvaluation.getChildren().add(boxEmozione);
        }
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