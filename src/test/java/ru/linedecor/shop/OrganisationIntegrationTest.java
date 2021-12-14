package ru.linedecor.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.linedecor.shop.controller.OrganisationController;
import ru.linedecor.shop.service.OrganisationService;
import ru.linedecor.shop.service.OrganisationServiceJpa;

@SpringBootTest
public class OrganisationIntegrationTest {

    @Autowired
    private OrganisationController organisationController;

    private OrganisationServiceJpa organisationService;

//    private OrganisationRepository organisationRepository;

}
