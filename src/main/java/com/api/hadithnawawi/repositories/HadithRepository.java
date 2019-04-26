package com.api.hadithnawawi.repositories;

import com.api.hadithnawawi.data.enums.Translation;
import com.api.hadithnawawi.data.models.Hadith;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HadithRepository extends CrudRepository<Hadith, UUID> {
	List<Hadith> findByTranslation(Translation translation);
	Hadith findByHadithBaseIndexAndTranslation(Integer index, Translation translation);
}
