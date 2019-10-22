package es.udc.juanporta.psi.clean.app.domain.gig.datasource;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.gig.Gig;

public interface GigDatasource {

    List<Gig> searchGig(String artistId);
}
