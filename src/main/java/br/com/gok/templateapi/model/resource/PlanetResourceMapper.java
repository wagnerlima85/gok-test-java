package br.com.gok.templateapi.model.resource;

import org.springframework.stereotype.Component;

import br.com.gok.templateapi.model.Planet;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class PlanetResourceMapper extends ConfigurableMapper {
	
	@Override
	protected void configure(MapperFactory factory) {
		
		factory.classMap(Planet.class, PlanetMapper.class)
			.customize(new CustomMapper<Planet, PlanetMapper>() {				
				@Override
				public void mapBtoA(PlanetMapper b, Planet a, MappingContext context) {					
					super.mapBtoA(b, a, context);					
				}

				@Override
				public void mapAtoB(Planet a, PlanetMapper b, MappingContext context) {
					super.mapAtoB(a, b, context);
				}				
			}).byDefault().register();
	}	

}
