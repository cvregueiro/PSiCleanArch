package es.udc.juanporta.psi.clean.app.domain;

import com.google.gson.annotations.SerializedName;

public class SetList {

    @SerializedName("eventDate")
    private String eventDate;

    public SetList(String eventDate) {

        this.eventDate = eventDate;
    }

    public String getEventDate() {

        return eventDate;
    }
}
