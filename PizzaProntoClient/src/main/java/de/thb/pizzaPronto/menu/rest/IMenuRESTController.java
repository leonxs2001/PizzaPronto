package de.thb.pizzaPronto.menu.rest;

import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.exception.NoAuthenticatedUserException;

import java.io.InputStream;
import java.io.OutputStream;

public interface IMenuRESTController {

    void importFromInputStream(InputStream fileInputStream) throws FailedRESTCallException, NoAuthenticatedUserException;

    OutputStream exportMenu() throws FailedRESTCallException, NoAuthenticatedUserException;

    void addDish(DishVO dish) throws FailedRESTCallException, NoAuthenticatedUserException;

    void deleteDish(DishVO dish) throws FailedRESTCallException, NoAuthenticatedUserException;
    MenuVO getMenu() throws FailedRESTCallException, NoAuthenticatedUserException;


}
