package com.api.hadithnawawi.data.dtos;

import com.api.hadithnawawi.data.enums.Translation;
import com.api.hadithnawawi.data.models.Hadith;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class HadithDto {

	private UUID id;
	private String header;
	private String title;
	private String summary;
	private Translation translation;
	private String comment;

	public HadithDto(Hadith hadith) {
		this.id = hadith.getId();
		this.header = hadith.getHeader();
		this.title = hadith.getTitle();
		this.summary = hadith.getSummary();
		this.comment = hadith.getComment();
	}
}
