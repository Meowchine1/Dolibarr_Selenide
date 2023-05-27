package tests.functionalTests;

import baseTest.Start;
import data.dataProvider.DataProviderClass;
import exceptions.PageTypeException;
import models.Company;
import org.testng.annotations.Test;
import pages.defaultPages.CompanyPage;
import webApplication.ApplicationRoute;

public class CompanyTests extends Start {

    @Test(enabled = false, dataProvider="validCompanyData", dataProviderClass = DataProviderClass.class)
    public void validCompanyData(Company company) throws PageTypeException {

        CompanyPage companyPage = ApplicationRoute.getAndOpenCompanyPage();
        companyPage.fillCompanyInf(company)
                .succesMessageShouldBe();

    }

    @Test(enabled = false, dataProvider="invalidCompanyData", dataProviderClass = DataProviderClass.class)
    public void invalidCompanyData(Company company) throws PageTypeException {

        CompanyPage companyPage = ApplicationRoute.getAndOpenCompanyPage();
        companyPage.fillCompanyInf(company)
                .succesMessageShouldNotBe();

    }

    @Test(dataProvider="validImage", dataProviderClass = DataProviderClass.class)
    public void validPhotoLoading(String path) throws PageTypeException {
        CompanyPage companyPage = ApplicationRoute.getAndOpenCompanyPage();
        companyPage.setLogo(path);
        companyPage.succesMessageShouldBe();
    }

    @Test(dataProvider="invalidImage", dataProviderClass = DataProviderClass.class)
    public void invalidPhotoLoading(String path) throws PageTypeException {
        CompanyPage companyPage = ApplicationRoute.getAndOpenCompanyPage();
        companyPage.setLogo(path);
        companyPage.succesMessageShouldNotBe();
    }

}
