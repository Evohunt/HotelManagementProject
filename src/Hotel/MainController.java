package Hotel;

import DataSaving.IRoomsSerializer;
import DataSaving.RoomsSerializer;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {

    /// Book TAB content
    public TextField book_name;
    public TextField book_cnp;
    public TextField book_phone;
    public TextField book_email;
    public ToggleGroup roomSize;
    public DatePicker book_check_in;
    public DatePicker book_check_out;
    public RadioButton book_2_beds;
    public RadioButton book_3_beds;
    public RadioButton book_4_beds;
    public Button bookNowBtn;
    private TotalRooms rooms = new TotalRooms();

    public void bookNow(ActionEvent actionEvent) {

        try {
            Client client = new Client();
            client.setName(book_name.getText());
            client.setCnp(book_cnp.getText());
            client.setPhone(book_phone.getText());
            client.setEmail(book_email.getText());
            client.setCheckInDate(book_check_in);
            client.setCheckOutDate(book_check_out);
            if (book_2_beds.isSelected()) {
                client.setRoomSize(2);
            } else if (book_3_beds.isSelected()) {
                client.setRoomSize(3);
            } else if (book_4_beds.isSelected()) {
                client.setRoomSize(4);
            }

            rooms.addReservedRoom(new Room(304, client));
            RoomsSerializer ser = new RoomsSerializer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
