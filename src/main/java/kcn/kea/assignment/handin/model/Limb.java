package kcn.kea.assignment.handin.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Limb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "body_id",nullable = true)
    private Body body;

    @ManyToMany(mappedBy = "limbs", fetch = FetchType.EAGER)
    private List<Organ> organDependencies;

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Body getBody()
    {
        return body;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }

    public List<Organ> getOrganDependencies()
    {
        return organDependencies;
    }

    public void setOrganDependencies(List<Organ> organDependencies)
    {
        this.organDependencies = organDependencies;
    }
}


