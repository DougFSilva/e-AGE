package com.dougfsilva.e_AGE.domain.occurrence;

public interface OccurrenceSignatureCryptography {

    OccurrenceSignature generateSignature(Occurrence occurrence);

    boolean validateSignature(Occurrence occurrence);

}
