package com.api.hadithnawawi.repositories;

import com.api.hadithnawawi.data.models.Hadith;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HadithRepository extends CrudRepository<Hadith, UUID> {
}
