package com.epam.esm.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Map;
@Data
@AllArgsConstructor
public class SortProp  {
   private String property;
   private String direction;
}
