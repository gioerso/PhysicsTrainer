package src.server.jointx.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Table(name = "blocks")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Block {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "block",fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Question> questions;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "block",fetch = FetchType.EAGER)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<UserBlock> blockFinishedUsers;
}
