package infoshare.factories.util;




import infoshare.app.util.DomainState;
import infoshare.app.util.security.GetUserCredentials;
import infoshare.app.util.security.KeyGenerator;
import infoshare.domain.util.Mail;

import java.util.Date;

/**
 * Created by hashcode on 2015/11/28.
 */
public class MailFactory {
    public static
    Mail createMailConf(String key, String value, String host, String port) {
        Mail mail = new Mail.Builder()
                .orgId(GetUserCredentials.getUser().getOrg())
                .date(new Date())
                .host(host)
                .id(KeyGenerator.getEntityId())
                .key(key)
                .port(port)
                .state(DomainState.ACTIVE.name())
                .value(value)
                .build();
        return mail;
    }

}
