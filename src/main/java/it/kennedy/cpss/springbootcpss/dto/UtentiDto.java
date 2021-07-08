package it.kennedy.cpss.springbootcpss.dto;

import lombok.Data;

@Data
public class UtentiDto {

	private Integer userID;
	private String username;
	private String newToken;
}
