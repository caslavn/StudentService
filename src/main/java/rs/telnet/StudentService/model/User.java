package rs.telnet.StudentService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "User", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String role = "";

    public List<String> getRoleList(){
        if(this.role.length()>0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

}
