package com.api.hadithnawawi.data.dtos;

import com.api.hadithnawawi.data.models.Hadith;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class HadithDto {

	private UUID id;
	private Integer index;
	private String original;
	private String audioUrl;
	private List<HadithDataDto> hadithData;

	public HadithDto(Hadith hadith) {
		this.id = hadith.getId();
		this.index = hadith.getIndex();
		this.original = hadith.getOriginal();
		this.audioUrl = hadith.getAudioUrl();
		this.hadithData = hadith.getHadithData() != null ?
			hadith.getHadithData().stream().map(HadithDataDto::new).collect(Collectors.toList()) : null;
	}
}
