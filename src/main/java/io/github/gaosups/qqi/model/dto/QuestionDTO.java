package io.github.gaosups.qqi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

	private String id;
	private String question;
	private List<String> options;
	private String answer;
}
