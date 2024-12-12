package com.dougfsilva.e_AGE.application.usecases.certificate;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.certificate.Certificate;
import com.dougfsilva.e_AGE.domain.certificate.CertificateRepository;
import com.dougfsilva.e_AGE.domain.exception.CertificateOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificateDeleter {

	private final CertificateRepository repository;
	private final CertificateFinder certificateFinder;
	private final StandardLogger logger;
	
	public void deleteByID(String ID) {
		try {
			Certificate certificate = certificateFinder.findByID(ID);
			repository.delete(certificate);
			logger.info(String.format("Deleted certificate ID %s", ID));
		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while deleting certificate ID %s : %s", ID, e.getMessage());
			logger.warn(message, e);
			throw new CertificateOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when deleting certificate ID %s : %s", ID, e.getMessage());
			logger.error(message, e);
			throw new CertificateOperationException(message, e);
		}
	}
}
