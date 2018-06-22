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
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {
    //лічильник спроб. Рахується від 0 до 2
    private int count;
    //загальна кількість спроб гравця
    private int total_attempt = 0;
    //головний лічильник балів, набраних гравцем
    private int total_score = 0;
    //поточний лічильник балів, набраних гравцем
    private int score = 10;
    //номер страви, яку обрав клієнт
    private int client_change = 0;
    //номер страви, яку обрав гравець
    private int gamer_change;
    //кількість клієнтів, яку обслуговували - мабуть, це поле є зайвим
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
        setClient();                                                //вибір першого клієнта при завантаженні програми

        fillFoods();                                                //заповнення списку продуків
        first_order.setGraphic(new ImageView(foods.get(0)));
        two_order.setGraphic(new ImageView(foods.get(1)));
        three_order.setGraphic(new ImageView(foods.get(2)));

        fillClients();                                              //заповнення списку клієнтів
        ClientChangeFood(random.nextInt(3));                 //вибір першої страви першим клієнтом

        setClientFoto(number_client, 1);                         //виведення у поле client_foto зображення клієнта

        first_order.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //total_attempt += 1;                                 //збільшується лічильник спроб гравця (на кожного клієнта - 3 спроби)
                count++;
                gamer_change = 0;                                   //номер страви, яку обрав гравець, натиснувши на дану кнопку (потрібно для порівнянн з вибором клієнта)
                change_cafe.setImage(foods.get(0));                 //вивід зображення страви, яку обрав гравець, натиснувши дану кнопку
                Game();                                             //основний метод гри - перевірка кількості набраних гравцем балів і відповідна дія
                change_client.setImage(foods.get(client_change));   //після того, як гравець зробив свій хід, показується страва, обрана клієнтом
                ClientChangeFood(random.nextInt(3));         //клієнт тут же "обирає" нову страву
                label.setText(String.valueOf(count));       //індикатор спроб показує, скільки спроб зроблено
                System.out.println(client_change);
            }

        });

        two_order.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //total_attempt += 1;
                count++;
                gamer_change = 1;
                change_cafe.setImage(foods.get(1));
                Game();
                change_client.setImage(foods.get(client_change));
                ClientChangeFood(random.nextInt(3));
                label.setText(String.valueOf(count));
                System.out.println(client_change);
            }
        });

        three_order.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //total_attempt += 1;
                count++;
                gamer_change = 2;
                change_cafe.setImage(foods.get(2));
                Game();
                change_client.setImage(foods.get(client_change));
                ClientChangeFood(random.nextInt(3) );
                label.setText(String.valueOf(count));
                System.out.println(client_change);
            }
        });

        results.setText("Спроб - 0, балів - 0");
    }

    /**
     * Основний метод гри. Рандомиться вибір клієнта, начисляються бали гравцю,
     * прокручується цикл, поки лічильник набраних гравцем балів не сягає 0 або 200 балів.
     */
    public void Game(){
        Random random = new Random();
        //int count = 0;

        if(client_change == gamer_change)   //збілшення балів, коли гравець вгадав, яку страву обрав клієнт
        {
            score +=10;
            if(score >= 20)
                setClientFoto(number_client, 0);
            else if(score <= 0 && score > -10)
                setClientFoto(number_client, 2);
            else if(score < -10)
                setClientFoto(number_client, 3);
        }
        else{                               //зменшення балів, якщо гравець не вгадав
            score -= 10;
            if(score >= 20)
                setClientFoto(number_client, 0);
            else if(score <= 0 && score > -10)
                setClientFoto(number_client, 2);
            else if(score < -10)
                setClientFoto(number_client, 3);
        }
        checkScore();
    }

    /**
     * Метод перевірки рівня
     */
    private void checkScore(){
        Random random = new Random();
        if (score > -200 && score < 100 ){                                                  //перша умова - якщо  кількість балів
            if(countClient() == true) {                                                     //лежить у допустимих межах, гра продовжується
                //ClientChangeFood(random.nextInt(3));
                results.setText("Кількість спроб " + total_attempt + ". Всього балів " + total_score);
            }
            else{
                setClient();
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            total_attempt += 1;
        }
        else if(score < -200){                                                                  //точка виходу - борг стає більше 200 балів
            client_foto.setImage(clients.get(number_client).getEmotion(3));                         //встановлення іконки емоції клієнта
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setClient();
            setClientFoto(number_client, 1);
            ClientChangeFood(random.nextInt(3));
            results.setText("Кількість спроб " + total_attempt + ". Всього балів " + total_score);
            total_attempt += 1;
            total_score += score;
            score = 0;
        }
        else if(score > 100){                                                                   //точка виходу - виграш стає більше 100 балів
            client_foto.setImage(clients.get(number_client).getEmotion(0));
            setClient();
            setClientFoto(number_client, 1);
            ClientChangeFood(random.nextInt(3));
            results.setText("Кількість спроб " + total_attempt + ". Всього балів " + total_score);
            total_attempt += 1;
            total_score += score;
            score = 0;
        }
    }

    /**
     * Метод перевіряє кількість спроб на кожного клієнта - поле count.
     * На кожного клієнта дається 3 спроби
     */
    private boolean countClient(){
        if(count < 3) {
            count++;
            return true;
        }
        else {
            count = 0;
            setClient();
            count_clients++;
            total_score += score;
            total_attempt += 1;
            score = 10;
            System.out.println("Цикл № "+total_attempt +" закінчився ");
        }
        return false;
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
        int i = rand.nextInt(6);
        number_client = i - 1;
    }

    /**
     * Метод, який встановлює зображення гримаси користувача
     * @param i
     */
    private void setClientFoto(int c, int i){
            client_foto.setImage(clients.get(c).getEmotion(i));
    }
}
