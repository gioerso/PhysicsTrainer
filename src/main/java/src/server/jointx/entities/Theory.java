package src.server.jointx.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Table(name = "theories")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "text")
  private String text;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "theory",fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<TheoryImage> theoryImages;

  @JsonBackReference
  @OneToOne(optional = false)
  @JoinColumn(name = "theme_id")
  private Theme theme;
}
