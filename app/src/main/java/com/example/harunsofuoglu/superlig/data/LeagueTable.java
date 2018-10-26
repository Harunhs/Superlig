package com.example.harunsofuoglu.superlig.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by harunsofuoglu on 26.10.2018.
 */


@Getter
@Setter
public class LeagueTable {

    @SerializedName("teamId")
    @Expose
    private Integer teamId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("overall")
    @Expose
    private Integer overall;

    @SerializedName("teamLogo")
    @Expose
    private String teamLogo;

}
