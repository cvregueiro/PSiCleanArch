package es.udc.juanporta.psi.clean.app.domain.gig;

import com.google.gson.annotations.SerializedName;

public class Gig {

    @SerializedName("eventDate")
    private String eventDate;

    public Gig(String eventDate) {

        this.eventDate = eventDate;
    }

    public String getEventDate() {

        return eventDate;
    }
}
