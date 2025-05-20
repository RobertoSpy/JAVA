package com.example;
import jakarta.persistence.*;

@Entity
@Table(name = "continents")
@NamedQueries({@NamedQuery(name="Continent.findAll",query="SELECT c from Continent c order by c.name")
})

public class Continent
{ @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "continent_seq")
@SequenceGenerator(name = "continent_seq", sequenceName = "continents_seq", allocationSize = 1)
private Integer id;
  @Column(name = "name")
  private String name;

  public Continent() {}
  public Continent(String name)
  {
      this.name = name;
  }
  public Integer getId()
  {
      return id;
  }
  public void setId(Integer id)
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


  @Override
    public String toString()
    {
      return "Continent [id=" + id + ", name=" + name + "]";
    }
}