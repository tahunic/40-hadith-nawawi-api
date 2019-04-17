package com.api.hadithnawawi.repositories;

import com.api.hadithnawawi.data.enums.Translation;
import com.api.hadithnawawi.data.models.HadithData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HadithDataRepository extends CrudRepository<HadithData, UUID> {
	List<HadithData> findByTranslation(Translation translation);
	HadithData findByHadithIndexAndTranslation(Integer index, Translation translation);
}
