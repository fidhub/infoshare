package infoshare.restApi;

import infoshare.domain.content.RawContent;
import infoshare.factories.content.RawContentFactory;
import infoshare.restapi.ContentFiles.content.RawContentAPI;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user9 on 2015/07/24.
 */
public class ContentTest {
    @Test
    public void testPOST() throws Exception {

        String cont = " Using any type of tobacco puts you on a collision course with cancer." +
                " Smoking has been linked to various types of cancer — including cancer of the lung, bladder," +
                " cervix and kidney. And chewing tobacco has been linked to cancer of the oral cavity and pancreas." +
                " Even if you don't use tobacco, exposure to secondhand smoke might increase your risk of lung cancer." +
                "Avoiding tobacco — or deciding to stop using it — is one of the most important health decisions you can make." +
                " It's also an important part of cancer prevention. If you need help quitting tobacco," +
                " ask your doctor about stop-smoking products and other strategies for quitting.";
        Map<String,String> stringStringMap = new HashMap<>();

        stringStringMap.put("creator","thulebona Hadebe");
        stringStringMap.put("source","mobile");
        stringStringMap.put("category","uncategorized");
        stringStringMap.put("title","HIV prevention");
        stringStringMap.put("content",cont);
        stringStringMap.put("status","raw");
        stringStringMap.put("contentType","Text");
        stringStringMap.put("org", "CPUT");
        RawContent  model = RawContentFactory.getRawContent(stringStringMap);
        RawContentAPI.save(model);
    }

}
