package Hotel;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
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
    private TotalRooms rooms = new TotalRooms();

    /// TAB Changer Buttons
    public TabPane mainTabPane;
    public Label btnHome;
    public Label btnBook;
    public Label btnCheckIn;
    public Label btnCheckOut;
    public Label btnRooms;
    public Label btnCancelBook;
    public Label btnExit;

    /// Cancel Book TAB content
    public Button UpdateRoomButton;
    public ListView cancelBookList;
    public Button CancelBookBtn;
    public Label cancelBookStatusLabel;

    /// Check In TAB content
    public TextField checkInName;
    public Button checkInSearchBtn;
    public ListView checkInList;
    public Button checkInSubmitBtn;

    /// Check Room TAB content
    public ListView busyRoomsList;
    public ListView reservedRoomsList;
    public Button checkRoomBtn;
    public Button updateListsBtn;
    public Pane roomStatusPanel;
    public Button roomStatusBtnOk;
    public Label roomStatusRoomNumber;
    public Label roomStatusRoomSize;
    public Label roomStatusRoomStatus;
    public Label roomStatusClientName;
    public Label roomStatusClientCNP;
    public Label roomStatusClientPhone;
    public Label roomStatusClientEmail;
    public Label roomStatusCheckIn;
    public Label roomStatusCheckOut;

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
                JOptionPane.showMessageDialog(new Frame(), "Room booked successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(new Frame(), "No more rooms with " + desiredRoomSize + " beds available!", "Warning", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(new Frame(), "Please select a room first!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            rooms.cancelReservedRoom(Integer.parseInt(rooms.processRoomNumber(cancelBookList.getSelectionModel().getSelectedItems().toString())));
            cancelBookList.getItems().clear();
            for (Room iterator : rooms.getReservedRooms()) {
                cancelBookList.getItems().add(iterator.getRoomNumber() + "     -     " +
                        iterator.getClient().getName() + "     -     " +
                        iterator.getClient().getStringCheckInDate() + "     -     " +
                        iterator.getClient().getStringCheckOutDate());
            }
        }
    }


    public void CheckInSearch(ActionEvent actionEvent) {
        checkInList.getItems().clear();
        for (Room iterator : rooms.getReservedRooms()) {
            if (iterator.getClient().getName().contains(checkInName.getText())) {
                checkInList.getItems().add(iterator.getRoomNumber() + "     -     " +
                        iterator.getClient().getName() + "     -     " +
                        iterator.getClient().getStringCheckInDate() + "     -     " +
                        iterator.getClient().getStringCheckOutDate());
            }
        }
    }

    public void CheckInSubmit(ActionEvent actionEvent) {
        if (checkInList.getSelectionModel().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(new Frame(), "Please make a selection!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new Frame(), "Check In successful!", "Information", JOptionPane.INFORMATION_MESSAGE);
            rooms.moveRoomFromReservedToBusy(Integer.parseInt(rooms.processRoomNumber(checkInList.getSelectionModel().getSelectedItems().toString())));
            checkInList.getItems().clear();
            for (Room iterator : rooms.getReservedRooms()) {
                checkInList.getItems().add(iterator.getRoomNumber() + "     -     " +
                        iterator.getClient().getName() + "     -     " +
                        iterator.getClient().getStringCheckInDate() + "     -     " +
                        iterator.getClient().getStringCheckOutDate());
            }
        }
    }

    public void CheckRoom(ActionEvent actionEvent) {
        if (busyRoomsList.getSelectionModel().getSelectedIndex() != -1) {
            roomStatusPanel.setVisible(true);
            for (Room iterator : rooms.getBusyRooms()) {
                String string = rooms.processRoomNumber(busyRoomsList.getSelectionModel().getSelectedItems().toString());
                if (Integer.toString(iterator.getRoomNumber()).equals(string)) {
                    roomStatusRoomNumber.setText(Integer.toString(iterator.getRoomNumber()));
                    roomStatusRoomSize.setText(Integer.toString(iterator.getRoomSize()) + " beds");
                    roomStatusRoomStatus.setText("Busy");
                    roomStatusClientName.setText(iterator.getClient().getName());
                    roomStatusClientCNP.setText(iterator.getClient().getCnp());
                    roomStatusClientPhone.setText(iterator.getClient().getPhone());
                    roomStatusClientEmail.setText(iterator.getClient().getEmail());
                    roomStatusCheckIn.setText(iterator.getClient().getStringCheckInDate());
                    roomStatusCheckOut.setText(iterator.getClient().getStringCheckOutDate());
                }
            }
        } else if (reservedRoomsList.getSelectionModel().getSelectedIndex() != -1) {
            roomStatusPanel.setVisible(true);
            for (Room iterator : rooms.getReservedRooms()) {
                String string = rooms.processRoomNumber(reservedRoomsList.getSelectionModel().getSelectedItems().toString());
                if (Integer.toString(iterator.getRoomNumber()).equals(string)) {
                    roomStatusRoomNumber.setText(Integer.toString(iterator.getRoomNumber()));
                    roomStatusRoomSize.setText(Integer.toString(iterator.getRoomSize()) + " beds");
                    roomStatusRoomStatus.setText("Reserved");
                    roomStatusClientName.setText(iterator.getClient().getName());
                    roomStatusClientCNP.setText(iterator.getClient().getCnp());
                    roomStatusClientPhone.setText(iterator.getClient().getPhone());
                    roomStatusClientEmail.setText(iterator.getClient().getEmail());
                    roomStatusCheckIn.setText(iterator.getClient().getStringCheckInDate());
                    roomStatusCheckOut.setText(iterator.getClient().getStringCheckOutDate());
                }
            }
        } else {
            JOptionPane.showMessageDialog(new Frame(), "Please make a selection!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void UpdateLists(ActionEvent actionEvent) {
        busyRoomsList.getItems().clear();
        reservedRoomsList.getItems().clear();
        for (Room iterator : rooms.getBusyRooms()) {
            busyRoomsList.getItems().add(iterator.getRoomNumber() + "     -     " +
                    iterator.getClient().getName() + "     -     " +
                    iterator.getClient().getStringCheckInDate() + "     -     " +
                    iterator.getClient().getStringCheckOutDate());
        }
        for (Room iterator : rooms.getReservedRooms()) {
            reservedRoomsList.getItems().add(iterator.getRoomNumber() + "     -     " +
                    iterator.getClient().getName() + "     -     " +
                    iterator.getClient().getStringCheckInDate() + "     -     " +
                    iterator.getClient().getStringCheckOutDate());
        }

    }

    public void busyRoomsSelect(MouseEvent mouseEvent) {
        reservedRoomsList.getSelectionModel().select(-1);
    }

    public void reservedRoomsSelect(MouseEvent mouseEvent) {
        busyRoomsList.getSelectionModel().select(-1);
    }

    public void roomStatusClose(ActionEvent actionEvent) {
        roomStatusPanel.setVisible(false);
    }

    public void tabChangeHome(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(0);
    }

    public void TabChangeHomeHover(MouseEvent mouseEvent) {
        btnHome.setStyle("-fx-border-color: white");
    }

    public void TabChangeHomeExit(MouseEvent mouseEvent) {
        btnHome.setStyle("-fx-border-color: transparent");
        btnHome.setStyle("-fx-background-color: #28056b");
    }



    public void tabChangeBook(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(1);
    }

    public void TabChangeBookHover(MouseEvent mouseEvent) {
        btnBook.setStyle("-fx-border-color: white");
    }

    public void TabChangeBookExit(MouseEvent mouseEvent) {
        btnBook.setStyle("-fx-border-color: transparent");
        btnBook.setStyle("-fx-background-color: #28056b");
    }



    public void tabChangeCheckIn(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(2);
    }

    public void TabChangeCheckInHover(MouseEvent mouseEvent) {
        btnCheckIn.setStyle("-fx-border-color: white");
    }

    public void TabChangeCheckInExit(MouseEvent mouseEvent) {
        btnCheckIn.setStyle("-fx-border-color: transparent");
        btnCheckIn.setStyle("-fx-background-color: #28056b");
    }



    public void tabChangeCheckOut(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(3);
    }

    public void TabChangeCheckOutHover(MouseEvent mouseEvent) {
        btnCheckOut.setStyle("-fx-border-color: white");
    }

    public void TabChangeCheckOutExit(MouseEvent mouseEvent) {
        btnCheckOut.setStyle("-fx-border-color: transparent");
        btnCheckOut.setStyle("-fx-background-color: #28056b");
    }



    public void tabChangeRooms(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(4);
    }

    public void TabChangeRoomsHover(MouseEvent mouseEvent) {
        btnRooms.setStyle("-fx-border-color: white");
    }

    public void TabChangeRoomsExit(MouseEvent mouseEvent) {
        btnRooms.setStyle("-fx-border-color: transparent");
        btnRooms.setStyle("-fx-background-color: #28056b");
    }



    public void tabChangeCancelBook(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(5);
    }

    public void TabChangeCancelBookHover(MouseEvent mouseEvent) {
        btnCancelBook.setStyle("-fx-border-color: white");
    }

    public void TabChangeCancelBookExit(MouseEvent mouseEvent) {
        btnCancelBook.setStyle("-fx-border-color: transparent");
        btnCancelBook.setStyle("-fx-background-color: #28056b");
    }

    public void ExitApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void btnExitHover(MouseEvent mouseEvent) {
        btnExit.setStyle("-fx-border-color: white");
    }

    public void btnExitExit(MouseEvent mouseEvent) {
        btnExit.setStyle("-fx-border-color: transparent");
        btnExit.setStyle("-fx-background-color: #28056b");
    }

}
