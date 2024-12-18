package com.dougfsilva.e_AGE.domain.configuration;

import java.util.List;

public interface ConfigurationRepository {

	Configuration save(Configuration configuration);
	Configuration findByKey(SystemSettingKey key);
	List<Configuration> findAll();
	Boolean existsByKey(SystemSettingKey key);
}
