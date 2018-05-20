/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Comp_Rating_FOController implements Initializable {

    @FXML
    private ImageView first;
    @FXML
    private ImageView second;
    @FXML
    private ImageView third;
    @FXML
    private ImageView fourth;
    @FXML
    private ImageView fifth;
    private final Image glow = new Image("images/activated_s.png");
    private final Image fade = new Image("images/disactivated_s.png");
    private int rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rating = 0;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return this.rating;
    }
    public void setSeeOnly()
    {
        
    }

    @FXML
    private void fade(MouseEvent event) {
        fade_or_not_to_fade();
    }

    @FXML
    private void glow(MouseEvent event) {
        first.setImage(glow);
        second.setImage(fade);
        third.setImage(fade);
        fourth.setImage(fade);
        fifth.setImage(fade);
    }

    @FXML
    private void change_state_1(ActionEvent event) {
      
        if (rating==1) {
            rating=0;
            first.setImage(fade);
            second.setImage(fade);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        } else {
            rating = 1;
            first.setImage(glow);
            second.setImage(fade);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        }
    }

    @FXML
    private void fade_2(MouseEvent event) {
        fade_or_not_to_fade();
    }

    @FXML
    private void glow_2(MouseEvent event) {
        first.setImage(glow);
        second.setImage(glow);
        third.setImage(fade);
        fourth.setImage(fade);
        fifth.setImage(fade);
    }

    @FXML
    private void change_state_2(ActionEvent event) {
        
        if (rating==2) {
            rating=0;
            first.setImage(fade);
            second.setImage(fade);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        } else {
            rating = 2;
            first.setImage(glow);
            second.setImage(glow);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        }
    }

    @FXML
    private void fade_3(MouseEvent event) {
        fade_or_not_to_fade();
    }

    @FXML
    private void glow_3(MouseEvent event) {
        first.setImage(glow);
        second.setImage(glow);
        third.setImage(glow);
        fourth.setImage(fade);
        fifth.setImage(fade);
    }

    @FXML
    private void change_state_3(ActionEvent event) {
        if (rating==3) {
            rating=0;
            first.setImage(fade);
            second.setImage(fade);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        } else {
            rating=3;
            first.setImage(glow);
            second.setImage(glow);
            third.setImage(glow);
            fourth.setImage(fade);
            fifth.setImage(fade);
        }
    }

    @FXML
    private void fade_4(MouseEvent event) {
        fade_or_not_to_fade();
    }

    @FXML
    private void glow_4(MouseEvent event) {
        first.setImage(glow);
        second.setImage(glow);
        third.setImage(glow);
        fourth.setImage(glow);
        fifth.setImage(fade);
    }

    @FXML
    private void change_state_4(ActionEvent event) {
        if (rating==4) {
            rating=0;
            first.setImage(fade);
            second.setImage(fade);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        } else {
            rating=4;
            first.setImage(glow);
            second.setImage(glow);
            third.setImage(glow);
            fourth.setImage(glow);
            fifth.setImage(fade);
        }
    }

    @FXML
    private void fade_5(MouseEvent event) {
        fade_or_not_to_fade();
    }

    @FXML
    private void glow_5(MouseEvent event) {
        first.setImage(glow);
        second.setImage(glow);
        third.setImage(glow);
        fourth.setImage(glow);
        fifth.setImage(glow);
    }

    @FXML
    private void change_state_5(ActionEvent event) {

        if (rating == 5) {
            rating = 0;
            first.setImage(fade);
            second.setImage(fade);
            third.setImage(fade);
            fourth.setImage(fade);
            fifth.setImage(fade);
        } else {
            rating = 5;
            first.setImage(glow);
            second.setImage(glow);
            third.setImage(glow);
            fourth.setImage(glow);
            fifth.setImage(glow);
        }
    }

    private void fade_or_not_to_fade() {
        switch (rating) {
            case 1:
                second.setImage(fade);
                third.setImage(fade);
                fourth.setImage(fade);
                fifth.setImage(fade);
                break;
            case 2:
                third.setImage(fade);
                fourth.setImage(fade);
                fifth.setImage(fade);
                break;
            case 3:
                fourth.setImage(fade);
                fifth.setImage(fade);
                break;
            case 4:
                fifth.setImage(fade);
                break;
            case 5:
                // nothing to see here
                break;
            default:
                first.setImage(fade);
                second.setImage(fade);
                third.setImage(fade);
                fourth.setImage(fade);
                fifth.setImage(fade);
                break;
        }
    }
}
