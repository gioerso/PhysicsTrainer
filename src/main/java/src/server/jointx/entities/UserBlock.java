package src.server.jointx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user_block",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "block_id"})})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBlock {

    public  UserBlock (User user, Block block){
        this.user = user;
        this.block = block;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;
}
