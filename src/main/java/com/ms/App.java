package com.ms;

import com.ms.controller.VendingMachineController;
import com.ms.dao.VendingMachineDao;
import com.ms.dao.VendingMachineDaoFileImpl;
import com.ms.sevicelayer.ServiceLayer;
import com.ms.sevicelayer.VendingMachineServiceLayerImpl;
import com.ms.ui.UserIO;
import com.ms.ui.UserIOConsoleImpl;
import com.ms.ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        ServiceLayer serviceLayer = new VendingMachineServiceLayerImpl(dao);
        VendingMachineController machineController = new VendingMachineController(view, serviceLayer);
        machineController.run();

    }
}
