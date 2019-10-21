package es.udc.juanporta.psi.clean.app.domain.gig;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gigs {

    @SerializedName("setlist")
    private List<Gig> gigs;

    public Gigs(List<Gig> gigs) {

        this.gigs = gigs;
    }

    public List<Gig> getGigs() {

        return gigs;
    }
}
