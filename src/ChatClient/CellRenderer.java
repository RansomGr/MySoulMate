package ChatClient;
import Entites.User.Client;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;


/**
 * A Class for Rendering users images / name on the Clientlist.
 */
public class CellRenderer implements Callback<ListView<Client>,ListCell<Client>>{
        @Override
    public ListCell<Client> call(ListView<Client> p) {

        ListCell<Client> cell = new ListCell<Client>(){

            @Override
            protected void updateItem(Client user, boolean bln) {
                super.updateItem(user, bln);
                setGraphic(null);
                setText(null);
                if (user != null) {
                    HBox hBox = new HBox();

                    Text name = new Text(user.getNom());

                    ImageView statusImageView = new ImageView();
                    Image statusImage = new Image(getClass().getClassLoader().getResource("images/" + user.getStatus().toString().toLowerCase() + ".png").toString(), 16, 16,true,true);
                    statusImageView.setImage(statusImage);

                    ImageView pictureImageView = new ImageView();
                    System.out.println(user.getProfil().getPhoto());
                    Image image = new Image(getClass().getClassLoader().getResource("images/"+user.getProfil().getPhoto()).toString(),50,50,true,true);
                    pictureImageView.setImage(image);

                    hBox.getChildren().addAll(statusImageView, pictureImageView, name);
                    hBox.setAlignment(Pos.CENTER_LEFT);

                    setGraphic(hBox);
                }
            }
        };
        return cell;
    }
}