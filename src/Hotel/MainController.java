package Hotel;

import DataSaving.RoomsSerializer;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


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
    public Label book_status_submit;
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
            int desiredRoomSize = 0;
            if (book_2_beds.isSelected()) {
                desiredRoomSize = 2;
            } else if (book_3_beds.isSelected()) {
                desiredRoomSize = 3;
            } else if (book_4_beds.isSelected()) {
                desiredRoomSize = 4;
            }

            if (rooms.areFreeRoomsAvailable(desiredRoomSize)) {
                rooms.addReservedRoom(new Room(client, desiredRoomSize), desiredRoomSize);
                book_status_submit.setText("");
            }
            else {
                book_status_submit.setText("No more rooms available!");
            }

            RoomsSerializer ser = new RoomsSerializer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
