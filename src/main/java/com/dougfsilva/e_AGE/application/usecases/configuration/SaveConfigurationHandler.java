package com.dougfsilva.e_AGE.application.usecases.configuration;

import java.util.ArrayList;
import java.util.List;

import com.dougfsilva.e_AGE.application.usecases.utilities.StandardLogger;
import com.dougfsilva.e_AGE.domain.configuration.Configuration;
import com.dougfsilva.e_AGE.domain.configuration.ConfigurationRepository;
import com.dougfsilva.e_AGE.domain.exception.ConfigurationOperationException;
import com.dougfsilva.e_AGE.domain.exception.ObjectNotFoundException;
import com.dougfsilva.e_AGE.domain.exception.OccurrenceOperationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveConfigurationHandler {

	private final ConfigurationRepository repository;
	private final StandardLogger logger;

	public List<Configuration> save(List<Configuration> configurations) {
		try {
			List<Configuration> savedConfigurations = new ArrayList<>();
			configurations.forEach(config -> {
				if (repository.existsByKey(config.getKey())) {
					Configuration existingConfig = repository.findByKey(config.getKey());
					existingConfig.setValue(config.getValue());
					savedConfigurations.add(repository.save(existingConfig));
                    logger.info("Updated Configuration with key: " + config.getKey());
				} else {
					savedConfigurations.add(repository.save(config));
                    logger.info("Saved new Configuration with key: " + config.getKey());
				}
			});
			return savedConfigurations;

		} catch (ObjectNotFoundException e) {
			String message = String.format("Error while saving configuration : %s", e.getMessage());
			logger.warn(message, e);
			throw new OccurrenceOperationException(message, e);
		} catch (Exception e) {
			String message = String.format("Unexpected error when saved configuration %s : %s", configurations.toString(), e.getMessage());
			logger.error(message, e);
			throw new ConfigurationOperationException(message, e);
		}
	}

}
