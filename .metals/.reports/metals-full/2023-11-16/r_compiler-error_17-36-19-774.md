file:///C:/Users/hp/Desktop/LabB/LabB2023/src/emotionalsongs/java/controllers/allTrackViewController.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/hp/Desktop/LabB/LabB2023/src/emotionalsongs/java/controllers/allTrackViewController.java
text:
```scala
package controllers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import pkg.ServerInterface;
import pkg.Track;
import util.TableViewManager;

public class allTrackViewController {

    @FXML
    private VBox tableContainer;
    ObservableList<Track> list;

    @FXML
    void initialize() throws RemoteException, NotBoundException {
        try {
            Registry r = LocateRegistry.getRegistry(1099);
        ServerInterface si = (ServerInterface) r.lookup("SERVER");
        ArrayList<String> ar = si.getTrackId("Ricordami");
        ArrayList<Track> res = si.getAllTrackInformation(ar, 0, ar.size());
        TableViewManager t = new TableViewManager(true, false);
        ObservableList<Track> o = FXCollections.observableArrayList(res);
        t.setItems(o);
        tableContainer.getChildren().add(t);
        } catch (Exception e) {
            System.out.println(e);        }
        
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