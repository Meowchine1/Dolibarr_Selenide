package tests.functionalTests;

import baseTest.Start;
import data.dataProvider.DataProviderClass;
import exceptions.PageTypeException;
import models.Company;
import org.testng.annotations.Test;
import pages.defaultPages.CompanyPage;
import webApplication.ApplicationRoute;

public class CompanyTests extends Start {

    @Test(dataProvider="validCompanyData", dataProviderClass = DataProviderClass.class)
    public void validCompanyData(Company company) throws PageTypeException {

        CompanyPage companyPage = ApplicationRoute.getAndOpenCompanyPage();
        companyPage.fillCompanyInf(company);

    }

    @Test(dataProvider="invalidCompanyData", dataProviderClass = DataProviderClass.class)
    public void invalidCompanyData(Company company) throws PageTypeException {

        CompanyPage companyPage = ApplicationRoute.getAndOpenCompanyPage();
        companyPage.fillCompanyInf(company);

    }
}
