package org.example.api.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Unicorn {
    private  String name;
    private  String tail;
    @SerializedName("_id")
    private String id;

}
