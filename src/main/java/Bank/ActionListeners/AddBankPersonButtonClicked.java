package Bank.ActionListeners;

import Bank.Types.AccountType;
import Bank.Views.Dialogs.PersonForm;
import FW.Model.Accounts.IAccount;
import FW.Model.Customer.ICustomer;
import FW.Singletons.InstanceManager;
import FW.Types.CustomerType;
import FW.Views.IDataAccessView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by bishal on 2/6/17.
 */
public class AddBankPersonButtonClicked implements ActionListener, IDataAccessView {

    JFrame parentFrame;
    public AddBankPersonButtonClicked(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void actionPerformed(ActionEvent event) {
//        FinCo finCoForm=null;
        PersonForm pac = new PersonForm(parentFrame, this);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

//        if (newaccount){
//            // add row to table
//            rowdata[0] = accountnr;
//            rowdata[1] = clientName;
//            rowdata[2] = city;
//            rowdata[3] = "C";
//            rowdata[4] = accountType;
//            rowdata[5] = "0";
//            model.addRow(rowdata);
//            JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
//            newaccount=false;
//        }
    }

    public void setData(HashMap<String, String> data){
        IAccount account;// = InstanceManager.getFactoryInstance().getAccount(FW.Types.AccountType.DEFAULT);

        if(data.get("accountType").equals("Savings")){
            account = InstanceManager.getFactoryInstance().getAccount(AccountType.SAVINGS); // for savingsAccount
        }
        else {
//            if(data.get("accountType").equals("Savings")){
            account = InstanceManager.getFactoryInstance().getAccount(AccountType.CHECKINGS); //for CheckingsAccount
        }

        account.setAccountNumber(data.get("accountNumber"));

        ICustomer customer = InstanceManager.getFactoryInstance().getCustomer(CustomerType.PERSON);
        customer.setName(data.get("city"));
        customer.setStreet(data.get("street"));
        customer.setCity(data.get("city"));
        customer.setZip(data.get("zip"));
        customer.setState(data.get("state"));

        InstanceManager.getControllerInstance().addAccount(customer, account);
    }

    public JFrame getParentFrame(){
        return parentFrame;
    }
}