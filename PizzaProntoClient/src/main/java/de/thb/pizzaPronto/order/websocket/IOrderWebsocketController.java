package de.thb.pizzaPronto.order.websocket;

import de.thb.pizzaPronto.generalGui.IMainGUIObserver;
import de.thb.pizzaPronto.menu.rest.DiscountVO;
import de.thb.pizzaPronto.menu.rest.MenuVO;

public interface IOrderWebsocketController extends IMainGUIObserver {
    void updateDiscount(DiscountVO discount);
    void updateMenu(MenuVO menu);
}
