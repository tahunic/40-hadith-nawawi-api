package com.api.hadithnawawi.data.models;

import com.api.hadithnawawi.data.enums.Translation;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HadithData {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String header;
	private String title;
	@Column(columnDefinition = "text")
	private String summary;
	private Translation translation;

	@ManyToOne
	private Hadith hadith;
}
