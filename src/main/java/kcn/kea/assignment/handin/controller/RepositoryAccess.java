package kcn.kea.assignment.handin.controller;

import kcn.kea.assignment.handin.persistence.BodyRepository;
import kcn.kea.assignment.handin.persistence.LimbRepository;
import kcn.kea.assignment.handin.persistence.OrganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/* This pseudo-controller takes on the role of an family repository in a 'repository'-persistence pattern */
@Controller
public class RepositoryAccess
{
    @Autowired
    private BodyRepository bodyRepository;
    @Autowired
    private LimbRepository limbRepository;
    @Autowired
    private OrganRepository organRepository;

    public BodyRepository getBodyRepository()
    {
        return bodyRepository;
    }

    public void setBodyRepository(BodyRepository bodyRepository)
    {
        this.bodyRepository = bodyRepository;
    }

    public LimbRepository getLimbRepository()
    {
        return limbRepository;
    }

    public void setLimbRepository(LimbRepository limbRepository)
    {
        this.limbRepository = limbRepository;
    }

    public OrganRepository getOrganRepository()
    {
        return organRepository;
    }

    public void setOrganRepository(OrganRepository organRepository)
    {
        this.organRepository = organRepository;
    }
}
