package com.api.hadithnawawi.data.dtos;

import com.api.hadithnawawi.data.enums.Translation;
import com.api.hadithnawawi.data.models.HadithData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class HadithDataDto {

	private UUID id;
	private String header;
	private String title;
	private String summary;
	private Translation translation;
	private String comment;

	public HadithDataDto(HadithData hadithData) {
		this.id = hadithData.getId();
		this.header = hadithData.getHeader();
		this.title = hadithData.getTitle();
		this.summary = hadithData.getSummary();
		this.comment = hadithData.getComment();
	}
}
