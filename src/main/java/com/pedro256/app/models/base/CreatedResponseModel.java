package com.pedro256.app.models.base;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
public class CreatedResponseModel {
    public Date datetime;
    public boolean created;
    public String font;
}
