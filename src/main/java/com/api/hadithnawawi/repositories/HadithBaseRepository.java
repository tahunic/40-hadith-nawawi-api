package com.api.hadithnawawi.repositories;

import com.api.hadithnawawi.data.models.HadithBase;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HadithBaseRepository extends CrudRepository<HadithBase, UUID> {
}
