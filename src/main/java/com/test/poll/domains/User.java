package com.test.poll.domains;

import com.test.poll.domains.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document(collection = "users")
public class User {

    @Id
    private String _id;

    private String name;

    private String username;

    private String password;

    private String status;

    private String rol;

    private Date createdAt;

    public Boolean isActive() {
        return this.status != null && this.status.equals(Status.ACTIVE.name());
    }
}