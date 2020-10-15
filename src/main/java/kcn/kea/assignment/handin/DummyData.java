package kcn.kea.assignment.handin;


import kcn.kea.assignment.handin.controller.RepositoryAccess;
import kcn.kea.assignment.handin.model.Body;
import kcn.kea.assignment.handin.model.Limb;
import kcn.kea.assignment.handin.model.Organ;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DummyData
{
    private RepositoryAccess access;

    public DummyData(RepositoryAccess access)
    {
        this.access = access;
    }

    public void GenerateDummyDate()
    {
        Body body1 = new Body();
        body1.setName("Hans Peter");
        body1.setDescription("Middel, 190cm foroverbøjet");

        Body save1 = access.getBodyRepository().save(body1);

        Limb limb1A = new Limb();
        limb1A.setBody(save1);
        limb1A.setName("Torso");
        limb1A.setDescription("Middle part");



        Limb limb1B = new Limb();
        limb1B.setBody(save1);
        limb1B.setName("Head");
        limb1B.setDescription("Top part");

        Organ brain = new Organ();
        brain.setName("Brain");
        brain.setDescription("Blob");
        Organ savedBrain = access.getOrganRepository().save(brain);

        Organ heart = new Organ();
        heart.setName("Heart");
        heart.setDescription("Thumper");
        Organ savedHeart = access.getOrganRepository().save(heart);

        limb1A.setOrganDependencies(Arrays.asList(heart));
        limb1B.setOrganDependencies(Arrays.asList(brain));
        Limb savedLimb1A = access.getLimbRepository().save(limb1A);



        Limb saveLimb1B = access.getLimbRepository().save(limb1B);

        body1.setLimbs(Arrays.asList(savedLimb1A,saveLimb1B));
    }
}
