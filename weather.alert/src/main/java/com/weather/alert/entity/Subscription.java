package com.weather.alert.entity;//this class belong to entity package

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity  //mark this class as database table and hibernet read this annotation and create table subscription
@Table(name = "subscriptions")//specifies the exact table name in the database
@Data                         //lombok annotion , generate getters and setters
@NoArgsConstructor //generate empty constructor
@AllArgsConstructor
public class Subscription {

    @Id             //mark this as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tell the database auto increment the id
    private Long id; //Long store reference pointing to the object in heap memory

    @Column(nullable = false) // city cannot be null
    private String city;

    @Column(nullable = false) //cannot be null
    private Double temperatureThreshold;

    @Column(nullable = false)//cannot be null it must true or false
    private Boolean isActive = true;// when user subscribe they active as default

    @Column(nullable = false, updatable = false)//cannot be null and cannot be updatable
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime lastAlertSentAt;

    @ManyToOne(fetch = FetchType.LAZY)//many subscription belong to one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

   //one to many relationship to AlertHistory
    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlertHistory> alertHistories = new ArrayList<>();


}

