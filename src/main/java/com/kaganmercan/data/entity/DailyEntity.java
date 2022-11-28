package com.kaganmercan.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Entity
@Entity
@Table(name = "daily")
public class DailyEntity extends BaseEntity implements Serializable {

    // dailyHeader
    private String dailyName;
    // dailyContent
    private String dailyDescription;
}
