package de.thb.pizzaPronto.generalGui;

import de.thb.pizzaPronto.authentication.rest.IAuthenticationRESTController;
import de.thb.pizzaPronto.customer.rest.ICustomerRESTController;
import de.thb.pizzaPronto.menu.rest.IMenuRESTController;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        mainView.getMainPanel().getLayoutPanel().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent evt) {
            }

            @Override
            public void componentShown(ComponentEvent evt) {
                load();
            }
        });

    }

    public void onLogin(){
        notifyMainGUIObservers();
        mainView.getMainPanel().showLayoutPanel();
    }

    public void load(){
        HomePanel homePanel = mainView.getMainPanel().getLayoutPanel().getHomePanel();
        SidePanel sidePanel = mainView.getMainPanel().getLayoutPanel().getSidePanel();

        if(authenticationRESTController.getAuthenticatedUserVO().getRole().getName().equals("customer")){
            sidePanel.getMenuButton().setVisible(false);
            sidePanel.getCustomerButton().setVisible(false);
            sidePanel.getStaffButton().setVisible(false);

            homePanel.getMenuPanel().setVisible(false);
            homePanel.getCustomerPanel().setVisible(false);
            homePanel.getStaffPanel().setVisible(false);
        }
    }
}
