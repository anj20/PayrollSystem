package src;

import src.controller.PayrollController;
import src.view.PayrollView;



public class PayrollSystem {
    public static void main(String[] args) {
        PayrollController controller = new PayrollController();
        PayrollView view = new PayrollView(controller);
        view.displayMenu();
    }
}
