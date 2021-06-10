package it.kennedy.cpss.springbootcpss.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UtentiDto {

	private Integer UserID;
	private String Username;
	private String Password;
}
