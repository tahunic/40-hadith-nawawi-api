package com.api.hadithnawawi.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hadith {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private Integer index;
	private String original;
	private String audioUrl;

	@JsonIgnore
	@OneToMany(mappedBy = "hadith", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HadithData> hadithData;
}
