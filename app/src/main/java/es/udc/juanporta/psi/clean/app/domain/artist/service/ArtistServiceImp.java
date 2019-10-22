package es.udc.juanporta.psi.clean.app.domain.artist.service;

import java.util.List;

import es.udc.juanporta.psi.clean.app.data.artist.ArtistDatasourceImp;
import es.udc.juanporta.psi.clean.app.domain.artist.Artist;
import es.udc.juanporta.psi.clean.app.domain.artist.datasource.ArtistDatasource;

public class ArtistServiceImp implements ArtistService {

    private ArtistDatasource mDatasource = new ArtistDatasourceImp();

    @Override
    public List<Artist> searchArtists(String textToSearch) {

        return mDatasource.searchArtists(textToSearch);
    }
}
