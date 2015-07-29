package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.content.models.ContentModel;
import infoshare.domain.Content;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by user9 on 2015/07/24.
 */
public class ContentTest {
    @Test
    public void testPOST() throws Exception {
        ContentModel model = new ContentModel();
        model.setDateCreated(new Date());
        model.setCreator("songezo mkumathela");
        model.setSource("source name");
        model.setCategory("treatment");
        model.setTitle("hiv treatment guidelines");
        model.setContent("Two regimens previously classified as Recommended regimens have been moved to the Alternative regimens category, with the rationale stated below:\n" +
                "Atazanavir/ritonavir (ATV/r) plus TDF/FTC (BI)—Based on the results of a large comparative clinical trial showing a greater rate of discontinuation with ATV/r plus TDF/FTC because of toxicities when compared to (DRV/r or RAL) plus TDF/FTC\n" +
                "Efavirenz/TDF/FTC (EFV/TDF/FTC) (BI)—Based on concerns about the tolerability of EFV in clinical trials and practice, especially the high rate of central nervous system (CNS)-related toxicities and a possible association with suicidality\n" +
                "Three regimens (ATV/r plus ABC/3TC, EFV plus ABC/3TC, and rilpivirine/TDF/FTC) that were previously listed as Recommended regimens for baseline HIV RNA <100,000 copies/mL or CD4 T lymphocyte (CD4) count >200 cells/mm3 are now in the Alternative or Other category, with the same caveat about limiting their use in these populations.\n" +
                "Two regimens that use fewer than two nucleoside reverse transcriptase inhibitors (DRV/r plus RAL and lopinavir/ritonavir plus 3TC) are now listed among the Other regimens, with the caveat that their use would be limited to those patients who cannot take either TDF or ABC.\n" +
                "Coformulations of atazanavir (ATV) and darunavir (DRV) with the pharmacokinetic (PK) enhancer cobicistat (COBI) have been added to the Alternative regimen options.");
        model.setContentType("raw");

        RestApiConnectorClass.create(UrlPath.ContentLinks.POST, model, ContentModel.class);
    }
    @Test
    public void testPUT() throws Exception {
        Content content = RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, "78f24db69be185b2edb10fc7f0fedc97",
                Content.class);
        Content content1 = new Content.Builder(content.getTitle()).copy(content)
                .contentType("edited").build();
        RestApiConnectorClass.update(UrlPath.ContentLinks.PUT,content1);
    }

    @Test
    public  void testGet() throws Exception {
        Content content = RestApiConnectorClass.read(UrlPath.ContentLinks.GET_ID, "2e881bde7d739f8a2adc0890592edf91",
                Content.class);

        System.out.println(content.getId());

    }

    @Test
    public void testGetAll() throws Exception {
        List<Content> contents = RestApiConnectorClass.readAll(UrlPath.ContentLinks.GETALL,Content.class);
        System.out.println(contents);
        Assert.assertFalse(contents.isEmpty());
    }
}
