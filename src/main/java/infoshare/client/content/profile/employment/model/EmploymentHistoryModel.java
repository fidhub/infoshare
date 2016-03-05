package infoshare.client.content.profile.employment.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hashcode on 2015/12/20.
 */
public class EmploymentHistoryModel implements Serializable {
    private String companyName;
    private String companyAddress;
    private String companyTelephone;
    private String applicatSupervisor;
    private Boolean contactSupervisor;
    private String reasonsForLeaving;
    private Date startDate;
    private Date endDate;
    private BigDecimal startingSalary;
    private BigDecimal endingSalary;
    private String currencyId;
    private String jobResponsibility;
    private String jobId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobResponsibility() {
        return jobResponsibility;
    }

    public void setJobResponsibility(String jobResponsibility) {
        this.jobResponsibility = jobResponsibility;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getEndingSalary() {
        return endingSalary;
    }

    public void setEndingSalary(BigDecimal endingSalary) {
        this.endingSalary = endingSalary;
    }

    public BigDecimal getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(BigDecimal startingSalary) {
        this.startingSalary = startingSalary;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReasonsForLeaving() {
        return reasonsForLeaving;
    }

    public void setReasonsForLeaving(String reasonsForLeaving) {
        this.reasonsForLeaving = reasonsForLeaving;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getContactSupervisor() {
        return contactSupervisor;
    }

    public void setContactSupervisor(Boolean contactSupervisor) {
        this.contactSupervisor = contactSupervisor;
    }

    public String getApplicatSupervisor() {
        return applicatSupervisor;
    }

    public void setApplicatSupervisor(String applicatSupervisor) {
        this.applicatSupervisor = applicatSupervisor;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
