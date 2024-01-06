/**
  * Contiene le classi necessarie a implementare funzioni utili 
 * alle altri classi del programma
  * @package util 
 * @see package.emotionalsongs.java
 */
package util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 * Progetto laboratorio B: "Emotional Songs", anno 2022-2023
 * 
 * @author Beatrice Bastianello, matricola 751864
 * @author Barbieri Lorenzo , matricola 748695
 * @author Storti Filippo , matricola 749195
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0

 *classe creata estendendo la classe VBox,  utilizzata per rappresentare un singolo commento all'interno di un'interfaccia utente
 */

public class CommentVBox extends VBox{

    Label commentLabel;
    Label usernameLabel;

    public CommentVBox(String comment, String username) {
        super();
       
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(1);
        this.setPadding(new Insets(7,15,15,15));
        this.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        this.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Label commentLabel = new Label(comment);
        commentLabel.setFont(new Font(16));
        commentLabel.setPadding(new Insets(9));
        commentLabel.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        commentLabel.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        commentLabel.getStyleClass().add("commentLabel");
        Label usernameLabel = new Label(username);
        usernameLabel.setFont(new Font(14));
        usernameLabel.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        usernameLabel.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        usernameLabel.setPadding(new Insets(0,0,0,3));
        this.getChildren().addAll(usernameLabel, commentLabel);

    }

}
