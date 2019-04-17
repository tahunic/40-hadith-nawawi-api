package com.api.hadithnawawi.controllers;

import com.api.hadithnawawi.data.commons.Response;
import com.api.hadithnawawi.data.dtos.HadithDto;
import com.api.hadithnawawi.data.enums.Translation;
import com.api.hadithnawawi.data.models.Hadith;
import com.api.hadithnawawi.data.models.HadithData;
import com.api.hadithnawawi.repositories.HadithDataRepository;
import com.api.hadithnawawi.repositories.HadithRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/hadiths")
@CrossOrigin
public class HadithsController {

	private final HadithRepository hadithRepository;
	private final HadithDataRepository hadithDataRepository;

	public HadithsController(HadithRepository hadithRepository, HadithDataRepository hadithDataRepository) {
		this.hadithRepository = hadithRepository;
		this.hadithDataRepository = hadithDataRepository;
	}

	@GetMapping()
	public ResponseEntity all() {
		Iterable<Hadith> all = hadithRepository.findAll();
		List<HadithDto> hadithDtos = new ArrayList<>();
		all.forEach(h -> hadithDtos.add(new HadithDto(h)));

		Response response = Response.builder()
			.success(true)
			.data(hadithDtos)
			.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/translation/{translation}")
	public ResponseEntity translation(@PathVariable("translation") String translation) {
		try {
			List<HadithData> hadithDataList = hadithDataRepository.findByTranslation(Translation.valueOf(translation.toUpperCase()));
			hadithDataList.sort(Comparator.comparing(h -> h.getHadith().getIndex()));

			Response response = Response.builder()
				.success(true)
				.data(hadithDataList)
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

		HadithData hadithData =
			hadithDataRepository.findByHadithIndexAndTranslation(index, Translation.fromString(translation.toUpperCase()));

		Response response = Response.builder()
				.success(true)
				.data(hadithData)
				.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
