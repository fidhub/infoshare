package infoshare.filterSearch;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.domain.User;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by user9 on 2016/02/11.
 */
public class UserFilter {
    private UserService userService = new UserServiceImpl();
    public TextField field = new TextField();
    public UserFilter() {
        getField();
    }
    public synchronized List<User> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (User user : userService.findAll()) {

            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || user.getUsername().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase())
                        || user.getFirstName().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase())
                        || user.getLastName().toString().toLowerCase().trim()
                        .contains(stringFilter.toLowerCase());

                if (passesFilter) {
                    arrayList.add(user);
                }
            } catch (Exception ex) {
                Logger.getLogger(ex.getLocalizedMessage());
            }
        }

        return arrayList;
    }
    private TextField getField(){
        field.setInputPrompt("Filter Users ...");
        field.setWidth("260px");
        field.setIcon(FontAwesome.FILTER);
        field.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        field.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        return field;
    }
}
