package com.kaganmercan.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

//lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyDto implements Serializable {

    private Long id;

    @NotEmpty(message = "{daily.name.validation.constraints.NotNull.message}")
    private String dailyName;

    @NotEmpty(message = "{daily.description.validation.constraints.NotNull.message}")
    private String dailyDescription;
    private Date createdDate;
}
