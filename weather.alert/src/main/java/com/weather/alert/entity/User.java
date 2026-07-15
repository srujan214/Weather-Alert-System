package com.weather.alert.entity;//here it show , this class is belong to entity package
                                 //here we used 3 classes in entity to store different data like
                                 //user to store user detail
                                 //subscibtion to store what subcribed data
                                 //alerHistory to store history data

import jakarta.persistence.*;  //import all classes from jakarta persistence package
import lombok.AllArgsConstructor;
import lombok.Data;              //for @Data annotation
import lombok.NoArgsConstructor;//JPA require a no argument constuctor .
                                // when hibernet loads data from database it create empty object using no-argconstuctor
import java.time.LocalDateTime;//import modern java date and time
import java.util.ArrayList;
import java.util.List;

@Entity //mark this class as database table ,
        // when we use @entity it tell springboot that this class represent the database table
@Table(name = "users")//specifies the exact table name (users) otherwise it will be user as user defualtname
@Data                 //this annotation use to generate getters and setters
@NoArgsConstructor    //create constuctor with no arguments
@AllArgsConstructor

public class User { //used public so other classes can access this

    @Id             //tells JPA that this field is the primary key of the database table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//automatically generate id by increment feature
    private Long id; //other classes can't access private directly.
                     // to access id they have use getters/setters

    @Column(nullable = false, unique = true)//cannot be null,must be unique (configure database table )
    private String email;                   //declare private field , string - email are text

    @Column(nullable = false)//name cannot be null
    private String name;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)//not nullable always has value .not updatable its set once
    private LocalDateTime createdAt = LocalDateTime.now();//sets the creation time to the current moment when the object is created

    @Column
    private LocalDateTime lastLoginAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//define one to many relationship
    private List<Subscription> subscriptions = new ArrayList<>();

}