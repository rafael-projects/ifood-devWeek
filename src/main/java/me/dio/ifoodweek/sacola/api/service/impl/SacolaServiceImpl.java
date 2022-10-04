package me.dio.ifoodweek.sacola.api.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.ifoodweek.sacola.api.enumeration.FormaPagamento;
import me.dio.ifoodweek.sacola.api.model.Item;
import me.dio.ifoodweek.sacola.api.model.Restaurante;
import me.dio.ifoodweek.sacola.api.model.Sacola;
import me.dio.ifoodweek.sacola.api.repository.ItemRepository;
import me.dio.ifoodweek.sacola.api.repository.ProdutoRepository;
import me.dio.ifoodweek.sacola.api.repository.SacolaRepository;
import me.dio.ifoodweek.sacola.api.resource.dto.ItemDto;
import me.dio.ifoodweek.sacola.api.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;

    private final ItemRepository itemRepository;
    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if (sacola.isFechada()){
            throw new RuntimeException("Esta sacola está fechada");

        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidate())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        ( ) -> {
                            throw new RuntimeException("Esta sacola não existe!");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
            if(itensDaSacola.isEmpty()){
                itensDaSacola.add(itemParaSerInserido);
            }else{
                Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
                Restaurante itemParaAdicionatRestaurante = itemParaSerInserido.getProduto().getRestaurante();

                if(restauranteAtual.equals(itemParaAdicionatRestaurante)){
                    itensDaSacola.add(itemParaSerInserido);
                }else{
                        throw new RuntimeException("Esta sacola só pode conter items do mesmo restaurante");
                }
            }

            List<Double> valorDosItems = new ArrayList<>();
            for (Item itemDaSacola: itensDaSacola) {
                double valorTotalItems =
                        itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
                valorDosItems.add(valorTotalItems);
            }


            double valorTotalSacola = valorDosItems.stream()
                            .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                                    .sum();

            sacola.setValorTotal(valorTotalSacola);
            sacolaRepository.save(sacola);
            return   itemParaSerInserido;


    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Esta sacola não existe!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int formaPagamento1) {
        Sacola  sacola = verSacola(id);
        if(sacola.getItens().isEmpty()){
            throw new RuntimeException("Esta sacola esta vazia, inclua itens na sacola!");
        }


        //ternario
        FormaPagamento  formaPagamento =
                formaPagamento1 == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }
}
