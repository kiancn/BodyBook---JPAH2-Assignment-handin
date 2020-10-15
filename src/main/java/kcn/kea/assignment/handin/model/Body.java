package kcn.kea.assignment.handin.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Body
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "body")
    private List<Limb> limbs;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public List<Limb> getLimbs()
    {
        return limbs;
    }

    public void setLimbs(List<Limb> limbs)
    {
        this.limbs = limbs;
    }
}
/* https://www.objectdb.com/api/java/jpa/OneToMany */
/* https://www.objectdb.com/api/java/jpa/ManyToMany */
