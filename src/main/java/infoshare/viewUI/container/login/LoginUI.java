package infoshare.viewUI.container.login;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.spring.security.VaadinSecurity;
import org.vaadin.spring.security.web.VaadinRedirectStrategy;

/**
 * Created by hashcode on 2015/10/19.
 */
@SpringUI(path = "/login")
@Theme(ValoTheme.THEME_NAME)
public class LoginUI extends UI {
    @Autowired
    VaadinSecurity vaadinSecurity;
    @Autowired
    VaadinRedirectStrategy redirectStrategy;
    private TextField userName;
    private PasswordField passwordField;
    private CheckBox rememberMe;
    private Label loginFailedLabel;
    private Label loggedOutLabel;
    private Button signin;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Welcome to Info Share");
        Component loginForm = buildLoginForm();
        loginForm.setSizeUndefined();
        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setSpacing(true);
        loginLayout.setSizeUndefined();

        if (request.getParameter("logout") != null) {
            loggedOutLabel = new Label("You have been logged out!");
            loggedOutLabel.addStyleName(ValoTheme.LABEL_SUCCESS);
            loggedOutLabel.setSizeUndefined();
            loginLayout.addComponent(loggedOutLabel);
            loginLayout.setComponentAlignment(loggedOutLabel, Alignment.BOTTOM_CENTER);
        }

        loginLayout.addComponent(loginFailedLabel = new Label());
        loginLayout.setComponentAlignment(loginFailedLabel, Alignment.BOTTOM_CENTER);
        loginFailedLabel.setSizeUndefined();
        loginFailedLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        loginFailedLabel.setVisible(false);

        loginLayout.addComponent(loginForm);
        loginLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

        VerticalLayout rootLayout = new VerticalLayout(loginLayout);
        rootLayout.setSizeFull();
        rootLayout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
        setContent(rootLayout);
        setSizeFull();

        Notification notification = new Notification(
                "Welcome to Info Share");
        notification.setDescription("<span>An Information, Media and Analytics  </a>.</span> <span>" +
                "Multi-Tenancy <b>Community </b> Platform.</span>");
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);

        notification.show(Page.getCurrent());
        notification.setDelayMsec(10000);
    }


    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(rememberMe = new CheckBox("Remember me"));
        return loginPanel;
    }


    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");

        userName = new TextField("Email Address");
        userName.setIcon(FontAwesome.ENVELOPE);
        userName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        passwordField = new PasswordField("Password");
        passwordField.setIcon(FontAwesome.LOCK);
        passwordField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        signin = new Button("Sign In");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.setDisableOnClick(true);

        signin.focus();

        fields.addComponents(userName, passwordField, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);
        signin.addClickListener(event -> login());
        return fields;
    }


    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");
        Label welcome = new Label("&nbsp;Welcome to ", ContentMode.HTML);
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_LIGHT);
        labels.addComponent(welcome);

        Label title = new Label("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Info Share", ContentMode.HTML);
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_BOLD);
        title.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(title);
        return labels;
    }

    private void login() {
        try {
            vaadinSecurity.login(userName.getValue(), passwordField.getValue(), rememberMe.getValue());
            getPage().setLocation("/");
        } catch (AuthenticationException ex) {
            userName.focus();
            userName.selectAll();
            passwordField.setValue("");
            loginFailedLabel.setValue(String.format("Login failed: %s", "Wrong Credentials "));
            loginFailedLabel.setVisible(true);
            if (loggedOutLabel != null) {
                loggedOutLabel.setVisible(false);
            }
        } catch (Exception ex) {
            Notification.show("An unexpected error occurred", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
            LoggerFactory.getLogger(getClass()).error("Unexpected error while logging in", ex);
        } finally {
            signin.setEnabled(true);
        }
    }

}