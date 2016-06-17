package infoshare.client.content.profile.profile.from;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import infoshare.app.facade.OrganisationFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.fields.UICheckBox;
import infoshare.app.util.fields.UIComponentHelper;
import infoshare.app.util.fields.UIListSelectHelper;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.client.content.profile.profile.model.ProfileModel;
import infoshare.domain.demographics.Role;
import infoshare.domain.person.Person;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2016/04/19.
 */
public class ProfileFrom extends FormLayout {
    private final ProfileModel bean;
    public final BeanItem<ProfileModel> item;
    public final FieldGroup binder;

    public ProfileFrom() {
        bean = new ProfileModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        binder.setReadOnly(true);
        setSizeFull();
        final Person person = GetUserCredentials.getUser();
        Set<Role> userRoles = PeopleFacade.personService.getRoles(person.getId());

        Component avatar = getAvatar();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(avatar);
        VerticalLayout verticalLayout = new VerticalLayout();

        Label HeaderName = new Label("<h2>"+person.getFirstName().toUpperCase()+" "+person.getLastName().toUpperCase()+"</h2>", ContentMode.HTML);
        Label HeaderEmail = new Label(person.getEmailAddress(), ContentMode.HTML);
        verticalLayout.addComponent(HeaderName);
        verticalLayout.addComponent(HeaderEmail);
        horizontalLayout.addComponent(verticalLayout);

        UIComponentHelper uiComponentHelper = new UIComponentHelper();
        UICheckBox checkBox = new UICheckBox();
        UIListSelectHelper listSelect = new UIListSelectHelper();

        final GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();
        grid.setSpacing(true);
        TextField lastName = uiComponentHelper.getGridTextField("Last Name","lastName", ProfileModel.class,binder);
        TextField firstName = uiComponentHelper.getGridTextField("First Name","firstName", ProfileModel.class,binder);
        TextField company = uiComponentHelper.getGridTextField("Organisation","company", ProfileModel.class,binder);
        TextField emailAddress = uiComponentHelper.getGridTextField("Email Address","emailAddress", ProfileModel.class,binder);
        TextField middleName = uiComponentHelper.getGridTextField("Middle Name","middleName", ProfileModel.class,binder);
        ListSelect roles = listSelect.getSelector("User Roles", userRoles);
        CheckBox enabled = checkBox.getCheckBox("Is Account Active","enabled",ProfileModel.class,binder);
        CheckBox credentialsNonExpired = checkBox.getCheckBox("Has Password Expired","credentialsNonExpired",ProfileModel.class,binder);
        CheckBox accountNonExpired = checkBox.getCheckBox("Has Account Locked","accountNonExpired",ProfileModel.class,binder);
        CheckBox accountNonLocked = checkBox.getCheckBox("Is Account Locked","accountNonLocked",ProfileModel.class,binder);

        Label userRole = new Label(getRoles(userRoles),ContentMode.HTML);
        userRole.setCaption("User Roles");
        grid.addComponent(firstName,0,0);
        grid.addComponent(lastName,1,0);
        grid.addComponent(middleName,2,0);
        grid.addComponent(company,0,1);
        grid.addComponent(emailAddress,1,1);
        grid.addComponent(userRole,2,1);
        grid.addComponent(enabled,0,3);
        grid.addComponent(credentialsNonExpired,1,3);
        grid.addComponent(accountNonExpired,2,3);
        grid.addComponent(accountNonLocked,0,4);
        Panel panel = new Panel(person.getFirstName().toUpperCase()+" 'S PROFILE SUMMARY");
        panel.addStyleName("panel");
        panel.setSizeFull();
        panel.setContent(grid);


        addComponent(horizontalLayout);
        addComponent(panel);

    }

    private String getRoles(Set<Role> roles) {
        return roles
                .stream()
                .map(value -> value.getDescription())
                .collect(Collectors.joining(",</br>"));
    }

    private static Component getAvatar(){
        final HorizontalLayout logo = new HorizontalLayout();
        Image logoImage;
        try {

            ExternalResource resource = new ExternalResource(PeopleFacade.personImagesService
                    .findByPersonId(OrganisationUtil.getCompanyCode(),GetUserCredentials.getUser().getId())
                    .iterator().next().getUrl());
            logoImage = new Image(null,resource);
        }catch (Exception e) {
            FileResource resource = new FileResource(

                    new File("src/main/webapp/VAADIN/themes/dashboard/profile.png"));
            logoImage= new Image(null,resource);
        }

        logoImage.addStyleName("avatar");
        logo.addComponent(logoImage);
        Responsive.makeResponsive(logo);
        return logo;
    }

}
