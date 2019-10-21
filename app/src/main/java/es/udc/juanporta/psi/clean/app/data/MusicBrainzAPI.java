package es.udc.juanporta.psi.clean.app.data;

import es.udc.juanporta.psi.clean.app.domain.artist.Artists;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicBrainzAPI {

    // http://musicbrainz.org/ws/2/artist/?query=artist:rancid&fmt=json
    @GET("artist/")
    Call<Artists> searchArtistByName(@Query("query") String query,
                                     @Query("fmt") String format);
}
