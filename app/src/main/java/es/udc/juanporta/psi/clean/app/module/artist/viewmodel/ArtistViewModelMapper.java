package es.udc.juanporta.psi.clean.app.module.artist.viewmodel;

import java.util.ArrayList;
import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.Artist;

public class ArtistViewModelMapper {

    private List<Artist> mArtists;

    public ArtistViewModelMapper(List<Artist> artists) {

        mArtists = artists;
    }

    public List<ArtistViewModel> map() {

        List<ArtistViewModel> artists = new ArrayList<>();
        for (Artist artist : mArtists) {
            artists.add(new ArtistViewModel(artist.getName()));
        }
        return artists;
    }
}