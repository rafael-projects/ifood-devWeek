package me.dio.ifoodweek.sacola.api.resource.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class ItemDto {
    private Long produtoId;
    private int quantidate;
    private Long sacolaId;
}
