package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //головний лічильник балів, набраних гравцем
    private int total_score = 0;
    //поточний лічильник балів, набраних гравцем
    private int score = 0;
    //номер страви, яку обрав клієнт
    private int client_change = 0;
    //номер страви, яку обрав гравець
    private int gamer_change;
    //загальна кількість спроб гравця
    private int total_attempt = 0;
    //кількість спроб гравця
    private int count_attempt = 0;
    //кількість клієнтів, яку обслуговували
    private int count_clients = 0;
    //індекс клієнта, який генерується методом setClient()
    private int number_client;

    //Загальний список клієнтів - 6 типів
    ArrayList<Client> clients = new ArrayList<Client>();
    //Загальний список страв - 3 типи
    private  ArrayList<Image> foods = new ArrayList<Image>();

    @FXML
    private AnchorPane pane;

    @FXML
    private ToggleButton first_order;

    @FXML
    private ToggleButton two_order;

    @FXML
    private ToggleButton three_order;

    //Поле результату, в яке передаються змінні score та count_clients;
    @FXML
    private TextField results;

    @FXML
    private ImageView change_client;

    @FXML
    private ImageView change_cafe;

    @FXML
    private ImageView client_foto;

    @FXML
    Label label;

    /**
     * Метод ініціалізації контролера. У методі відбувається заповнення списків клієнтів
     * і страв, обирається перший клієнт і перша його страва. На кнопки вибору страви вішаються обробники.
     *
     * @param location
     * @param resources
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Random random = new Random();
        number_client = random.nextInt(6);

        fillFoods();
        first_order.setGraphic(new ImageView(foods.get(0)));
        two_order.setGraphic(new ImageView(foods.get(1)));
        three_order.setGraphic(new ImageView(foods.get(2)));

        fillClients();
        ClientChangeFood(random.nextInt(2));
        setClient();
        setClientFoto(number_client, 1);


        first_order.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count_attempt += 1;
                gamer_change = 0;
                change_cafe.setImage(foods.get(0));
                Game();
                change_client.setImage(foods.get(client_change));
                ClientChangeFood(random.nextInt(2));
                label.setText(String.valueOf(count_attempt));
                System.out.println(client_change);
            }

        });

        two_order.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count_attempt += 1;
                gamer_change = 1;
                change_cafe.setImage(foods.get(1));
                Game();
                change_client.setImage(foods.get(client_change));
                ClientChangeFood(random.nextInt(2));
                label.setText(String.valueOf(count_attempt));
                System.out.println(client_change);
            }
        });

        three_order.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count_attempt += 1;
                gamer_change = 2;
                change_cafe.setImage(foods.get(2));
                Game();
                change_client.setImage(foods.get(client_change));
                ClientChangeFood(random.nextInt(2));
                label.setText(String.valueOf(count_attempt));
                System.out.println(client_change);
            }
        });

        results.setText("Спроб - 0, балів - 0");
        //client_foto.setImage(new Boy().getEmotion());//(new ImageView("./resources/CafeNew/Boy/Boy-01.png")));
        client_foto.getImage();
        //setClientFoto(10);
    }

    /**
     * Основний метод гри. Рандомиться вибір клієнта, начисляються бали гравцю,
     * прокручується цикл, поки лічильник набраних гравцем балів не сягає 0 або 200 балів.
     */
    public void Game(){
        Random random = new Random();
        if(client_change == gamer_change)
        {
            score +=10;
        }
        else{
            score -= 5;
        }

        if (score >= 0 && score < 200 ){
            ClientChangeFood(random.nextInt(2));
        }
        else if(score < 0){
            try{
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../resources/wrong.fxml"));
                stage.setTitle("Ви програли!");
                stage.setMinHeight(150);
                stage.setMinWidth(300);
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(pane.getScene().getWindow());
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
            client_foto.setImage(clients.get(number_client).getEmotion(3));
            setClient();
            setClientFoto(number_client, 1);
            ClientChangeFood(random.nextInt(2));
            results.setText("Кількість спроб " + total_attempt + ". Всього балів " + total_score);
            total_attempt += 1;
            total_score += score;
            score = 0;
        }
        else if(score > 200){
            try{
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../resources/wins.fxml"));
                stage.setTitle("Ви виграли!");
                stage.setMinHeight(150);
                stage.setMinWidth(300);
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(pane.getScene().getWindow());
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
            client_foto.setImage(clients.get(number_client).getEmotion(0));
            setClient();
            setClientFoto(number_client, 1);
            ClientChangeFood(random.nextInt(2));
            results.setText("Кількість спроб " + total_attempt + ". Всього балів " + total_score);
            total_attempt += 1;
            total_score += score;
            score = 0;
        }
    }

    /**
     * Метод встановлює страву, яку обирає (рандомно) клієнт.
     * @param i
     * @return
     */
    private void ClientChangeFood(int i){
        //change_client.setImage(foods.get(i));
        client_change = i;
    }

    private void setFoodImage(){
        change_client.setImage(foods.get(client_change));
    }

    /**
     * Метод заповнення списку клієнтів. Всього
     * у нас 6 типів клієнтів
     */
    private void fillClients(){
        //Boy b = new Boy();
        clients.add(new Boy());
        clients.add(new Girl());
        clients.add(new Man());
        clients.add(new Woman());
        clients.add(new Grandfather());
        clients.add(new GrandMather());
    }

    /**
     * Метод заповнення списку продуктів і водночас поміщення на кнопки вибору
     * відповідних зображень продуктів
     */
    private void fillFoods(){
        foods.add(new Image("./resources/CafeNew/food/food-01.png"));
        foods.add(new Image("./resources/CafeNew/food/food-02.png"));
        foods.add(new Image("./resources/CafeNew/food/food-03.png"));
    }

    /**
     * Метод встановлює індекс клієнта у поточній ітерації гри.
     */
    private void setClient(){
        Random rand = new Random();
        number_client = rand.nextInt(6);
    }

    /**
     * Метод, який встановлює зображення гримаси користувача
     * @param i
     */
    private void setClientFoto(int c, int i){
            client_foto.setImage(clients.get(c).getEmotion(i));
    }
}
