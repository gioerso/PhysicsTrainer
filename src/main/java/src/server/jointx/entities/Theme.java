package src.server.jointx.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Table(name = "themes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theme {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @JsonManagedReference
  @OneToOne(mappedBy = "theme", optional = false)
  private Theory theory;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "theme",fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<ThemeQuestion> themeQuestions;
}
