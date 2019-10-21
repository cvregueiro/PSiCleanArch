package es.udc.juanporta.psi.clean.app.domain.gig.service;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.gig.Gig;

public interface GigService {

    List<Gig> searchGig(String artistId);
}
