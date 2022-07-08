package com.ms.controller;

import com.ms.dao.InsufficientFundsException;
import com.ms.dao.ItemEmptyException;
import com.ms.dao.VendingMachinePersistenceException;
import com.ms.dto.Item;
import com.ms.sevicelayer.ServiceLayer;
import com.ms.ui.VendingMachineView;

import java.math.BigDecimal;

public class VendingMachineController {


    private final VendingMachineView view;

    private final ServiceLayer serviceLayer;

    public VendingMachineController(VendingMachineView view, ServiceLayer serviceLayer) {
        this.view = view;
        this.serviceLayer = serviceLayer;
    }

    public void run() {
        boolean running = true;


        while(running){
            try {
                printMenuSelection();
                int menuSelection = getMenuSelection();
                switch(menuSelection){
                    case 1:
                        enterMoney();
                        break;
                    case 2:
                        viewWallet();
                        break;
                    case 3:
                        viewItems();
                        break;
                    case 4:
                        selectItem();
                        break;
                    case 5:
                        returnChange();
                        break;
                    case 6:
                        running = false;
                        break;
                }
            } catch (ItemEmptyException | VendingMachinePersistenceException | InsufficientFundsException e) {
                view.DisplayErrorMessage(e.getMessage());

            }
        }
        displayExitMessage();

    }

    private void viewWallet() {
        BigDecimal wallet = serviceLayer.getSessionWallet();
        view.DisplayWalletValue(wallet);
    }

    private void displayExitMessage() {

    }

    private void printMenuSelection() {

    }

    private void enterMoney() {
        BigDecimal money = view.EnterMoney();
        serviceLayer.addToWallet(money);
        displayWalletValue();
    }

    private void displayWalletValue() {
        view.DisplayWalletValue(serviceLayer.getSessionWallet());
    }

    private void viewItems() throws ItemEmptyException, VendingMachinePersistenceException {
        view.DisplayItems(serviceLayer.viewItems());
    }

    private void returnChange() {
        view.DisplayChange(serviceLayer.getChange());
    }

    private void selectItem() throws InsufficientFundsException, VendingMachinePersistenceException, ItemEmptyException {
        String itemID = view.GetItemSelection();
        Item selectedItem = serviceLayer.selectItem(itemID);
        if(selectedItem == null){
            throw new VendingMachinePersistenceException("Failed to get item");
        }
        displayItem(selectedItem);
    }

    private void displayItem(Item selectedItem) {
        view.displayItem(selectedItem);
    }

    private int getMenuSelection() {
        return view.PrintMenuAndGetSelection();
    }
}
