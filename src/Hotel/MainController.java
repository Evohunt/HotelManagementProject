package Hotel;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.event.ActionListener;

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

    /// Cancel Book TAB content
    public Button UpdateRoomButton;
    public ListView cancelBookList;
    public Button CancelBookBtn;
    public Label cancelBookStatusLabel;

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

            book_name.setText("");
            book_cnp.setText("");
            book_email.setText("");
            book_phone.setText("");
            book_2_beds.setSelected(false);
            book_3_beds.setSelected(false);
            book_4_beds.setSelected(false);
            book_check_in.getEditor().clear();
            book_check_out.getEditor().clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UpdateRooms(ActionEvent actionEvent) {
        cancelBookList.getItems().clear();
        for (Room iterator : rooms.getReservedRooms()) {
            cancelBookList.getItems().add(iterator.getRoomNumber() + "     -     " +
                                          iterator.getClient().getName() + "     -     " +
                                          iterator.getClient().getStringCheckInDate() + "     -     " +
                                          iterator.getClient().getStringCheckOutDate());
        }
    }

    public void RemoveBookedRoom(MouseEvent mouseEvent) {
        //book_name.setText(cancelBookList.getSelectionModel().getSelectedItems().toString());
    }

    public void CancelBook(ActionEvent actionEvent) {
        if (cancelBookList.getSelectionModel().getSelectedIndex() == -1) {
            cancelBookStatusLabel.setText("Please select a room first!");
        } else {
            rooms.cancelReservedRoom(Integer.parseInt(rooms.processRoomNumber(cancelBookList.getSelectionModel().getSelectedItems().toString())));
        }
    }
}
