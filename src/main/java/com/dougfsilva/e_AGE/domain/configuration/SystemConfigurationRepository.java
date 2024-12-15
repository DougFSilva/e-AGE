package com.dougfsilva.e_AGE.domain.configuration;

import java.util.Optional;

public interface ConfigurationRepository {
	
	Optional<SystemConfiguration> findByID(String ID);
	Optional<SystemConfiguration> findByKey(String key);
    void save(SystemConfiguration configuration);
}
