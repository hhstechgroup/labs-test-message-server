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

    @PostConstruct
    public void initModel() {
        model = new DefaultMenuModel();
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        String viewId = viewRoot.getViewId();

        MenuItem menuItem = new MenuItem();
        menuItem.setId("emailItem");
        if (viewId.endsWith("/emailPage.xhtml")) {
            menuItem.setStyleClass("active");
        }
        menuItem.setValue("Emails");
        menuItem.setUrl("/pages/emailPage.xhtml");
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("smsItem");
        if (viewId.endsWith("/smsPage.xhtml")) {
            menuItem.setStyleClass("active");
        }
        menuItem.setValue("SMS");
        menuItem.setUrl("/pages/smsPage.xhtml");
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("jmsItem");
        if (viewId.endsWith("/jmsPage.xhtml")) {
            menuItem.setStyleClass("active");
        }
        menuItem.setValue("Jms");
        menuItem.setUrl("/pages/jmsPage.xhtml");
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("webServiceItem");
        if (viewId.endsWith("/webServicePage.xhtml")) {
            menuItem.setStyleClass("active");
        }
        menuItem.setValue("WebService");
        menuItem.setUrl("/pages/webServicePage.xhtml");
        model.addMenuItem(menuItem);

        menuItem = new MenuItem();
        menuItem.setId("infoItem");
        if (viewId.endsWith("/infoPage.xhtml")) {
            menuItem.setStyleClass("active");
        }
        menuItem.setValue("Info");
        menuItem.setUrl("/pages/infoPage.xhtml");
        model.addMenuItem(menuItem);
    }

    public MenuModel getModel() {
        return model;
    }
}
