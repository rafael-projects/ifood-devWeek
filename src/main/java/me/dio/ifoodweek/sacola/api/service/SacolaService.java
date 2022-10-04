package me.dio.ifoodweek.sacola.api.service;

import me.dio.ifoodweek.sacola.api.model.Item;
import me.dio.ifoodweek.sacola.api.model.Sacola;
import me.dio.ifoodweek.sacola.api.resource.dto.ItemDto;

public interface SacolaService {

    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);


}
