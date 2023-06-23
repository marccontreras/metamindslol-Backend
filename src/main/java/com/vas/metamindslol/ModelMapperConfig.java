package com.vas.metamindslol;

import com.vas.metamindslol.champion.StaticChampion;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Data
public class ModelMapperConfig {

  private ModelMapper modelMapper= new ModelMapper();
  @Bean
  public ModelMapper modelMapper() {
    return modelMapper= new ModelMapper();
  }



  public <S, T> List<T> mapAsList(List<S> source, Class<T> targetClass) {
    return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
  }


  public <S, T> T map(S source, Class<T> targetClass) {
      return modelMapper.map(source,targetClass);
  }
}