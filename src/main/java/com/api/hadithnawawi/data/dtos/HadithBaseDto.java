package com.api.hadithnawawi.data.dtos;

import com.api.hadithnawawi.data.models.HadithBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class HadithBaseDto {

	private UUID id;
	private Integer index;
	private String original;
	private String audioUrl;

	public HadithBaseDto(HadithBase hadithBase) {
		this.id = hadithBase.getId();
		this.index = hadithBase.getIndex();
		this.original = hadithBase.getOriginal();
		this.audioUrl = hadithBase.getAudioUrl();
	}
}
