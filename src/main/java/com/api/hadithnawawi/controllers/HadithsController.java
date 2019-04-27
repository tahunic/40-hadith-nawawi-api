package com.api.hadithnawawi.controllers;

import com.api.hadithnawawi.data.commons.Response;
import com.api.hadithnawawi.data.dtos.HadithBaseDto;
import com.api.hadithnawawi.data.enums.Translation;
import com.api.hadithnawawi.data.models.HadithBase;
import com.api.hadithnawawi.data.models.Hadith;
import com.api.hadithnawawi.repositories.HadithRepository;
import com.api.hadithnawawi.repositories.HadithBaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hadiths")
@CrossOrigin
public class HadithsController {

	private final HadithBaseRepository hadithBaseRepository;
	private final HadithRepository hadithRepository;

	public HadithsController(HadithBaseRepository hadithBaseRepository, HadithRepository hadithRepository) {
		this.hadithBaseRepository = hadithBaseRepository;
		this.hadithRepository = hadithRepository;
	}

	@GetMapping()
	public ResponseEntity all() {
		Iterable<HadithBase> all = hadithBaseRepository.findAll();
		List<HadithBaseDto> hadithBaseDtos = new ArrayList<>();
		all.forEach(h -> hadithBaseDtos.add(new HadithBaseDto(h)));

		Response response = Response.builder()
			.success(true)
			.data(hadithBaseDtos)
			.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/translation/{translation}")
	public ResponseEntity translation(@PathVariable("translation") String translation) {
		try {
			List<Hadith> hadithList = hadithRepository.findByTranslation(Translation.valueOf(translation.toUpperCase()));
			hadithList.sort(Comparator.comparing(h -> h.getHadithBase().getIndex()));

			Response response = Response.builder()
				.success(true)
				.data(hadithList)
				.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Response response = Response.builder()
				.success(false)
				.message("Invalid translation value.")
				.build();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/byIndex")
	public ResponseEntity translation(@RequestParam Integer index, @RequestParam String translation) {
		if (index == null || StringUtils.isEmpty(translation)) {
			Response response = Response.builder()
				.success(false)
				.message("Invalid index or translation value.")
				.build();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Hadith hadith =
			hadithRepository.findByHadithBaseIndexAndTranslation(index, Translation.fromString(translation.toUpperCase()));

		Response response = Response.builder()
				.success(true)
				.data(hadith)
				.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity search(@RequestParam String term, @RequestParam String translation) {
		Integer index = null;
		try {
			index = Integer.parseInt(term);
		} catch (NumberFormatException e) {
		}

		try {
			List<Hadith> hadithList = new ArrayList<>();
			if (StringUtils.isEmpty(term)) {
				hadithList = hadithRepository.findByTranslation(Translation.valueOf(translation.toUpperCase()));
			} else if (index != null && index >= 1 && index <= 42) {
				Hadith hadith = hadithRepository
					.findByHadithBaseIndexAndTranslation(index, Translation.valueOf(translation.toUpperCase()));
				if (hadith != null) {
					hadithList.add(hadith);
				}
			}
			else {
				hadithList = hadithRepository
					.findByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCaseOrCommentContainingIgnoreCase(term, term, term)
					.stream()
					.filter(h -> h.getTranslation().equals(Translation.valueOf(translation.toUpperCase())))
					.collect(Collectors.toList());
			}
			hadithList.sort(Comparator.comparing(h -> h.getHadithBase().getIndex()));

			Response response = Response.builder()
				.success(true)
				.data(hadithList)
				.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Response response = Response.builder()
				.success(false)
				.message("Invalid translation value.")
				.build();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
