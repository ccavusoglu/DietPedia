package com.dietpedia.app.domain.model;

/**
 * Created by Q on 21.06.2016.
 */
public class Diet extends BaseEntity {
    public int name;
    public String info;
    public int categoryId;

    public String breakfast;
    public String lunch;
    public String dinner;

    public String snack1;
    public String snack2;

    public String snack3;
    public String snack4;

    public String snack5;
    public String snack6;

    public Diet() {
    }
}
