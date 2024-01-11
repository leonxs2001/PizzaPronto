package de.thb.pizzaPronto.generalGui;

import de.thb.pizzaPronto.authentication.rest.IAuthenticationRESTController;
import de.thb.pizzaPronto.customer.rest.ICustomerRESTController;
import de.thb.pizzaPronto.menu.rest.IMenuRESTController;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainGUIController extends MainGUIObservable{
    private MainView mainView;

    private IAuthenticationRESTController authenticationRESTController;
    private ICustomerRESTController customerRESTController;
    private IMenuRESTController menuRESTController;

    public MainGUIController(IAuthenticationRESTController authenticationRESTController, ICustomerRESTController customerRESTController, IMenuRESTController menuRESTController, MainView mainView){
        super();
        setMainView(mainView);
        setCustomerRESTController(customerRESTController);
        setMenuRESTController(menuRESTController);
        setAuthenticationRESTController(authenticationRESTController);

    }

    public void onLogin(){
        notifyMainGUIObservers();
        mainView.getMainPanel().showLayoutPanel();
    }
}
