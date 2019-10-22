package es.udc.juanporta.psi.clean.app.domain.artist.datasource;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.artist.Artist;

public interface ArtistDatasource {

    List<Artist> searchArtists(String textToSearch);
}
