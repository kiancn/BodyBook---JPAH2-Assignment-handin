package kcn.kea.assignment.handin.controller;

import kcn.kea.assignment.handin.model.Body;
import kcn.kea.assignment.handin.model.Limb;
import kcn.kea.assignment.handin.model.Organ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controls crud-type controller events regarding bodies
 */
@Controller
public class BodyController
{
    @Autowired
    private RepositoryAccess access;

    @GetMapping("/registerNewBody")
    public String registerNewBody()
    {
        return "new-body";
    }

    @PostMapping("/saveNewBody")
    public String saveNewBody(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> description,
            Model model)
    {
        if(!name.isPresent() || !description.isPresent()){return "redirect:/registerNewBody";}

        Body newBody = new Body();
        newBody.setName(name.get());
        newBody.setDescription(description.get());

        Body savedBody = access.getBodyRepository().save(newBody);

        model.addAttribute("body", savedBody);

        return editBody(model);
    }

    /**
     * All controller methods updating limbs or organs redirect here
     * after their activity
     */
    @GetMapping("/editBody")
    public String editBody(Model model)
    {
        return "edit-body";
    }

    @PostMapping("/editSpecificBody")
    public String editBody(
            @RequestParam int bodyID,
            Model model)
    {
        Optional<Body> bodyById = access.getBodyRepository().findById(bodyID);
        model.addAttribute("body",bodyById.get());
        return "edit-body";
    }

    @PostMapping("/updateBody")
    public String updateBody(
            @RequestParam int bodyID,
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> description,
            Model model)
    {
        Optional<Body> byId = access.getBodyRepository().findById(bodyID);
        if(byId.isPresent())
        {
            byId.get().setName(!name.isPresent() ? byId.get().getName() : name.get());
            byId.get().setDescription(!description.isPresent() ? byId.get().getDescription() : description.get());
            Body savedBody = access.getBodyRepository().save(byId.get());
            model.addAttribute("body", savedBody);
            return editBody(model);
        }

        return "redirect:/";
    }

    @PostMapping("/updateLimb")
    public String updateLimb(
            @RequestParam int limbID,
            @RequestParam int bodyID,
            @RequestParam Optional<String> limbName,
            @RequestParam Optional<String> limbDescription,
            Model model
                            )
    {

        Optional<Limb> limb = access.getLimbRepository().findById(limbID);

        if(limb.isPresent())
        {
            limb.get().setName(limbName.get());
            limb.get().setDescription(limbDescription.get());
            access.getLimbRepository().save(limb.get());
        }

        Optional<Body> byId = access.getBodyRepository().findById(bodyID);
        if(byId.isPresent())
        {
            model.addAttribute("body", byId.get());
            return editBody(model);
        }

        return "redirect:/";
    }

    @PostMapping("/createLimb")
    public String createLimb(
            @RequestParam int bodyID,
            @RequestParam Optional<String> limbName,
            @RequestParam Optional<String> limbDescription,
            Model model
                            )
    {

        Limb limb = new Limb();

        limb.setName(!limbName.isPresent() ? "Unnamed Limb" : limbName.get());
        limb.setDescription(!limbDescription.isPresent() ? "No Description" : limbDescription.get());

        Optional<Body> bodyById = access.getBodyRepository().findById(bodyID);
        if(bodyById.isPresent())
        {
            limb.setBody(bodyById.get());
            access.getLimbRepository().save(limb);

            model.addAttribute("body", bodyById.get());
            return editBody(model);
        }

        return "redirect:/"; // this only happens if body not present
    }


    @PostMapping("updateOrgan")
    public String updateOrgan(
            @RequestParam int bodyID,
            @RequestParam int limbID,
            @RequestParam int organID,
            @RequestParam Optional<String> organName,
            @RequestParam Optional<String> organDescription

                             )
    {
        return "redirect:/";
    }

    @PostMapping("createOrgan")
    public String createOrgan(
            @RequestParam int bodyID,
            @RequestParam int limbID,
            @RequestParam Optional<String> organName,
            @RequestParam Optional<String> organDescription,
            Model model
                             )
    {
        Optional<Limb> limbByID = access.getLimbRepository().findById(limbID);

        if(limbByID.isPresent()){
            Organ organ = new Organ();

            ArrayList<Limb> limbs = new ArrayList<>();
            limbs.add(limbByID.get());

            organ.setLimbs(limbs);
            organ.setName(!organName.isPresent()?"Organ Not Named":organName.get());
            organ.setDescription(!organDescription.isPresent()?"Organ has no description":organDescription.get());

            Organ savedOrgan = access.getOrganRepository().save(organ);
            limbByID.get().getOrganDependencies().add(savedOrgan);
            access.getLimbRepository().save(limbByID.get());
        }

        Optional<Body> bodyById = access.getBodyRepository().findById(bodyID);
        if(bodyById.isPresent()){
            model.addAttribute("body",bodyById.get());
            return editBody(model);
        }

        return "redirect:/";
    }
}