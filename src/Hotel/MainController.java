package Hotel;

import InputValidator.IInputValidation;
import InputValidator.InputValidation;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;

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
    private IInputValidation inputValidator = new InputValidation();

    /// GUI Theme
    public Label btnBlueTheme;
    public Label btnBrownTheme;
    public Pane sidebarPane;
    public Label sidebarLogo;
    private String theme = "Brown";

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

    /// Check Out TAB content
    public ListView checkOutList;
    public Button checkOutBtn;
    public Button checkOutUpdateListBtn;
    public Pane checkOutStatusPanel;
    public Label checkOutRoomCost;
    public Label checkOutExtraCost;
    public Label checkOutTotalCost;
    public Label checkOutRoomNumber;
    public Label checkOutRoomSize;
    public Label checkOutClientName;
    public Label checkOutCheckInDate;
    public Label checkOutCheckOutDate;
    public Label checkOutTotalDays;
    public Button checkOutStatusBtnClose;
    public Button btnCheckOutRoom;
    public Label checkOutSuccessLabel;

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
    public TextField roomExtraCost;
    public Button roomExtraCostAddBtn;

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

            if (inputValidator.onlyContainsNumbers(book_phone.getText(), book_cnp.getText()) &&
                    inputValidator.onlyContainsLetters(book_name.getText(), book_email.getText())) {

                if (rooms.areFreeRoomsAvailable(desiredRoomSize)) {
                    rooms.addReservedRoom(new Room(client, desiredRoomSize), desiredRoomSize);
                    JOptionPane.showMessageDialog(new Frame(), "Room booked successfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    book_name.setText("");
                    book_cnp.setText("");
                    book_email.setText("");
                    book_phone.setText("");
                    book_2_beds.setSelected(false);
                    book_3_beds.setSelected(false);
                    book_4_beds.setSelected(false);
                    book_check_in.getEditor().clear();
                    book_check_out.getEditor().clear();
                }
                else {
                    JOptionPane.showMessageDialog(new Frame(), "No more rooms with " + desiredRoomSize + " beds available!", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(new Frame(), "Invalid Input", "Warning", JOptionPane.WARNING_MESSAGE);
            }

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

    public void CheckOutRoom(ActionEvent actionEvent) {
        if (checkOutList.getSelectionModel().getSelectedIndex() != -1) {
            checkOutStatusPanel.setVisible(true);
            for (Room iterator : rooms.getBusyRooms()) {
                String string = rooms.processRoomNumber(checkOutList.getSelectionModel().getSelectedItems().toString());
                if (Integer.toString(iterator.getRoomNumber()).equals(string)) {
                    checkOutRoomNumber.setText(Integer.toString(iterator.getRoomNumber()));
                    checkOutRoomSize.setText(Integer.toString(iterator.getRoomSize()));
                    checkOutClientName.setText(iterator.getClient().getName());
                    checkOutCheckInDate.setText(iterator.getClient().getStringCheckInDate());
                    checkOutCheckOutDate.setText(iterator.getClient().getStringCheckOutDate());
                    checkOutTotalDays.setText(Long.toString(iterator.calculateTotalDays()));
                    checkOutRoomCost.setText(Long.toString(iterator.calculateRoomPrice()));
                    checkOutExtraCost.setText(Integer.toString(iterator.getExtraCost()));
                    checkOutTotalCost.setText("RON " + Long.toString(iterator.calculateRoomPrice() + iterator.getExtraCost()));
                }
            }
        }
    }

    public void CheckOutUpdateList(ActionEvent actionEvent) {
        checkOutList.getItems().clear();
        for (Room iterator : rooms.getBusyRooms()) {
            checkOutList.getItems().add(iterator.getRoomNumber() + "     -     " +
                    iterator.getClient().getName() + "     -     " +
                    iterator.getClient().getStringCheckInDate() + "     -     " +
                    iterator.getClient().getStringCheckOutDate());
        }
    }

    public void checkOutClose(ActionEvent actionEvent) {
        checkOutStatusPanel.setVisible(false);
        checkOutSuccessLabel.setText("");
    }

    public void CheckoutAndPay(ActionEvent actionEvent) {
        rooms.checkOutRoom(Integer.parseInt(checkOutRoomNumber.getText()));
        checkOutSuccessLabel.setText("Checkout complete!");
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

    public void AddExtraCost(ActionEvent actionEvent) {
        rooms.addExtraCostForRoom(Integer.parseInt(roomStatusRoomNumber.getText()),
                                  roomStatusRoomStatus.getText(),
                                  Integer.parseInt(roomExtraCost.getText()));
        roomExtraCost.setText("");
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
        if (theme.equals("Blue")) {
            btnHome.setStyle("-fx-border-color: transparent");
            btnHome.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnHome.setStyle("-fx-border-color: transparent");
            btnHome.setStyle("-fx-background-color:  #97581d");
        }

    }



    public void tabChangeBook(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(1);
    }

    public void TabChangeBookHover(MouseEvent mouseEvent) {
        btnBook.setStyle("-fx-border-color: white");
    }

    public void TabChangeBookExit(MouseEvent mouseEvent) {
        if (theme.equals("Blue")) {
            btnBook.setStyle("-fx-border-color: transparent");
            btnBook.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnBook.setStyle("-fx-border-color: transparent");
            btnBook.setStyle("-fx-background-color:  #97581d");
        }
    }



    public void tabChangeCheckIn(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(2);
    }

    public void TabChangeCheckInHover(MouseEvent mouseEvent) {
        btnCheckIn.setStyle("-fx-border-color: white");
    }

    public void TabChangeCheckInExit(MouseEvent mouseEvent) {
        if (theme.equals("Blue")) {
            btnCheckIn.setStyle("-fx-border-color: transparent");
            btnCheckIn.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnCheckIn.setStyle("-fx-border-color: transparent");
            btnCheckIn.setStyle("-fx-background-color:  #97581d");
        }
    }



    public void tabChangeCheckOut(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(3);
    }

    public void TabChangeCheckOutHover(MouseEvent mouseEvent) {
        btnCheckOut.setStyle("-fx-border-color: white");
    }

    public void TabChangeCheckOutExit(MouseEvent mouseEvent) {
        if (theme.equals("Blue")) {
            btnCheckOut.setStyle("-fx-border-color: transparent");
            btnCheckOut.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnCheckOut.setStyle("-fx-border-color: transparent");
            btnCheckOut.setStyle("-fx-background-color:  #97581d");
        }
    }



    public void tabChangeRooms(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(4);
    }

    public void TabChangeRoomsHover(MouseEvent mouseEvent) {
        btnRooms.setStyle("-fx-border-color: white");
    }

    public void TabChangeRoomsExit(MouseEvent mouseEvent) {
        if (theme.equals("Blue")) {
            btnRooms.setStyle("-fx-border-color: transparent");
            btnRooms.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnRooms.setStyle("-fx-border-color: transparent");
            btnRooms.setStyle("-fx-background-color:  #97581d");
        }
    }



    public void tabChangeCancelBook(MouseEvent mouseEvent) {
        mainTabPane.getSelectionModel().select(5);
    }

    public void TabChangeCancelBookHover(MouseEvent mouseEvent) {
        btnCancelBook.setStyle("-fx-border-color: white");
    }

    public void TabChangeCancelBookExit(MouseEvent mouseEvent) {
        if (theme.equals("Blue")) {
            btnCancelBook.setStyle("-fx-border-color: transparent");
            btnCancelBook.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnCancelBook.setStyle("-fx-border-color: transparent");
            btnCancelBook.setStyle("-fx-background-color:  #97581d");
        }
    }


    public void ExitApp(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void btnExitHover(MouseEvent mouseEvent) {
        btnExit.setStyle("-fx-border-color: white");
    }

    public void btnExitExit(MouseEvent mouseEvent) {
        if (theme.equals("Blue")) {
            btnExit.setStyle("-fx-border-color: transparent");
            btnExit.setStyle("-fx-background-color: #28056b");
        } else if (theme.equals("Brown")) {
            btnExit.setStyle("-fx-border-color: transparent");
            btnExit.setStyle("-fx-background-color:  #97581d");
        }
    }

    public void ChangeBlueTheme(MouseEvent mouseEvent) {
        theme = "Blue";
        sidebarPane.setStyle("-fx-background-color: #190342");
        sidebarLogo.setStyle("-fx-background-color: #28056b");
        btnExit.setStyle("-fx-background-color: #350187");
        btnHome.setStyle("-fx-background-color: #28056b");
        btnBook.setStyle("-fx-background-color: #28056b");
        btnCheckIn.setStyle("-fx-background-color: #28056b");
        btnCheckOut.setStyle("-fx-background-color: #28056b");
        btnRooms.setStyle("-fx-background-color: #28056b");
        btnCancelBook.setStyle("-fx-background-color: #28056b");

    }

    public void ChangeBrownTheme(MouseEvent mouseEvent) {
        theme = "Brown";
        sidebarPane.setStyle("-fx-background-color: #643206");
        sidebarLogo.setStyle("-fx-background-color: #97581d");
        btnExit.setStyle("-fx-background-color: #97581d");
        btnHome.setStyle("-fx-background-color: #97581d");
        btnBook.setStyle("-fx-background-color: #97581d");
        btnCheckIn.setStyle("-fx-background-color: #97581d");
        btnCheckOut.setStyle("-fx-background-color: #97581d");
        btnRooms.setStyle("-fx-background-color: #97581d");
        btnCancelBook.setStyle("-fx-background-color: #97581d");
    }
}
