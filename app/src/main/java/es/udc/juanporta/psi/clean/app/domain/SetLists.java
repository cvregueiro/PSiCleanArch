package es.udc.juanporta.psi.clean.app.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SetLists {

    @SerializedName("setlist")
    private List<SetList> setLists;

    public SetLists(List<SetList> setLists) {

        this.setLists = setLists;
    }

    public List<SetList> getSetLists() {

        return setLists;
    }
}
