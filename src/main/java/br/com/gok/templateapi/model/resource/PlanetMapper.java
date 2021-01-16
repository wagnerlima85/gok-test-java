package br.com.gok.templateapi.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PlanetMapper{
	private Long id;
    private String name;
    private String climate;
    private String terrain;
    private Long population;
}
