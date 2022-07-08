package com.ms.ui;

import com.ms.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    private final UserIO io;
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int PrintMenuAndGetSelection(){
        io.print("Menu");
        io.print("1. Enter Money");
        io.print("2. View Wallet");
        io.print("3. View Items");
        io.print("4. Select Items");
        io.print("5. Return Change");
        io.print("6. Exit");

        return io.readInt("Please select from above choices : ");
    }

    public void DisplayItems(List<Item> items){
        for(Item currentItem : items){
            String information = String.format("\nID: %s Name : %s Price : %s Quantity : %s", currentItem.getITEM_ID(),
                    currentItem.getTitle(), currentItem.getPrice(), currentItem.getQuantity());
            io.print(information);
        }
        System.out.println();
    }


    public void DisplayErrorMessage(String message) {
        io.print(message);
    }

    public BigDecimal EnterMoney() {
        BigDecimal money = io.readBigDecimal("Enter the amount of money you are inserting into the machine : £");
        return money;
    }

    public void DisplayWalletValue(BigDecimal wallet) {
        io.print("Wallet contains " + wallet.toString());
    }

    public String GetItemSelection() {
        return io.readString("Enter the item that you would like to buy: ");
    }

    public void displayItem(Item selectedItem) {
        io.print(selectedItem.toString());
    }

    public void DisplayChange(String change) {
        io.print(change);
    }
}
