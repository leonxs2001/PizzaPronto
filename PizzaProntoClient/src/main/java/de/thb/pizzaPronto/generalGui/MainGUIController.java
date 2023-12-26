package de.thb.pizzaPronto.generalGui;

import de.thb.pizzaPronto.authentication.rest.IAuthenticationRESTController;
import de.thb.pizzaPronto.customer.rest.ICustomerRESTController;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainGUIController extends MainGUIObservable{
    private MainView mainView;

    private IAuthenticationRESTController authenticationRESTController;
    private ICustomerRESTController customerRESTController;

    public MainGUIController(IAuthenticationRESTController authenticationRESTController, ICustomerRESTController customerRESTController, MainView mainView){
        super();
        setMainView(mainView);
        setCustomerRESTController(customerRESTController);
        setAuthenticationRESTController(authenticationRESTController);

    }

    public void onLogin(){
        notifyMainGUIObservers();
        mainView.getMainPanel().showLayoutPanel();
    }
}
