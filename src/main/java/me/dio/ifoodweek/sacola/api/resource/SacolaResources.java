package me.dio.ifoodweek.sacola.api.resource;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.dio.ifoodweek.sacola.api.model.Item;
import me.dio.ifoodweek.sacola.api.model.Sacola;
import me.dio.ifoodweek.sacola.api.resource.dto.ItemDto;
import me.dio.ifoodweek.sacola.api.service.SacolaService;
import org.springframework.web.bind.annotation.*;


@Api("/ifood-devweek/sacolas")
@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaResources {

    private final SacolaService sacolaService;


    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }


    @PatchMapping("/fecharSacola/{idSacola}")
    public Sacola fecharSacola(@PathVariable("idSacola") Long idSacola, @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(idSacola, formaPagamento);
    }
}
