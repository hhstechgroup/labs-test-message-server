package com.engagepoint.university.messaging.uib;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean(name = "leftMenuBar")
@RequestScoped
public class LeftMenuBarBean {
    private MenuModel model;
    private static final String ACTIVE = "active";
    private static final String EMAIL_PAGE="/emailPage.xhtml";
    private static final String SMS_PAGE="/smsPage.xhtml";
    private static final String JMS_PAGE="/jmsPage.xhtml";
    private static final String SOAP_SERVLET_PAGE="/soapServletPage.xhtml";
    private static final String INFO_PAGE="/infoPage.xhtml";

    @PostConstruct
    public void initModel() {
        model = new DefaultMenuModel();
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        String viewId = viewRoot.getViewId();

        MenuItem menuItem = new MenuItem();
        menuItem.setId("emailItem");
        if (viewId.endsWith(EMAIL_PAGE)) {
            menuItem.setStyleClass(ACTIVE);
        }
        menuItem.setValue("Emails");
        menuItem.setUrl("/pages" + EMAIL_PAGE);
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("smsItem");
        if (viewId.endsWith(SMS_PAGE)) {
            menuItem.setStyleClass(ACTIVE);
        }
        menuItem.setValue("SMS");
        menuItem.setUrl("/pages" + SMS_PAGE);
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("jmsItem");
        if (viewId.endsWith(JMS_PAGE)) {
            menuItem.setStyleClass(ACTIVE);
        }
        menuItem.setValue("JMS");
        menuItem.setUrl("/pages" + JMS_PAGE);
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("soapServletItem");
        if (viewId.endsWith(SOAP_SERVLET_PAGE)) {
            menuItem.setStyleClass(ACTIVE);
        }
        menuItem.setValue("SoapServlet");
        menuItem.setUrl("/pages" + SOAP_SERVLET_PAGE);
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("infoItem");
        if (viewId.endsWith(INFO_PAGE)) {
            menuItem.setStyleClass(ACTIVE);
        }
        menuItem.setValue("Info");
        menuItem.setUrl("/pages" + INFO_PAGE);
        model.addMenuItem(menuItem);
    }

    public MenuModel getModel() {
        return model;
    }
}
