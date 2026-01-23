package com.deliverytech.delivery_api.config;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.model.Produto;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

@Configuration

public class DataLoader {
    @Bean
    public CommandLineRunner initData(
        ClienteRepository clienteRepository,
        RestauranteRepository restauranteRepository,
        ProdutoRepository produtoRepository,
        PedidoRepository pedidoRepository
    ) {
        return args ->{
            System.out.println("Iniciando carregamento de dados...");

            Cliente cliente1 = new Cliente();
            cliente1.setNome("Raiel Godinho");
            cliente1.setEmail("raiellgod@gmail.com");
            cliente1.setTelefone("11942869870");
            cliente1.setEndereco("Rua Exemplo, 123, São Paulo, SP");
            cliente1.setAtivo(true);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Maria Silva");
            cliente2.setEmail("maria.silva@gmail.com");
            cliente2.setTelefone("11987654321");
            cliente2.setEndereco("Av. Paulista, 1000, São Paulo, SP");
            cliente2.setAtivo(true);

            clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));


            Restaurante restaurante1 = new Restaurante();
            restaurante1.setNome("Thai Godinho Confeitaria Afetiva");
            restaurante1.setCategoria("Confeitaria");
            restaurante1.setEndereco("Rua das Flores, 123, São Paulo, SP");
            restaurante1.setTelefone("11987654321");
            restaurante1.setAvaliacao(new BigDecimal("4.5"));
            restaurante1.setTaxaEntrega(new BigDecimal("5.00"));
            restaurante1.setAtivo(true);

            Restaurante restaurante2 = new Restaurante();
            restaurante2.setNome("Restaurante B");
            restaurante2.setCategoria("Comida brasileira");
            restaurante2.setEndereco("Av. Paulista, 456, São Paulo, SP");
            restaurante2.setTelefone("11987654322");
            restaurante2.setAvaliacao(new BigDecimal("4.8"));
            restaurante2.setTaxaEntrega(new BigDecimal("10.00"));
            restaurante2.setAtivo(true);

            restauranteRepository.saveAll(Arrays.asList(restaurante1, restaurante2));

            Produto produto1 = new Produto();
            produto1.setNome("Bolo de Chocolate");
            produto1.setDescricao("Delicioso bolo de chocolate com cobertura de brigadeiro");
            produto1.setPreco(new BigDecimal("25.00"));
            produto1.setCategoria("Confeitaria");
            produto1.setDisponivel(true);
            produto1.setRestaurante(restaurante1);

            Produto produto2 = new Produto();
            produto2.setNome("Coxinha");
            produto2.setDescricao("Coxinha de frango com catupiry");
            produto2.setPreco(new BigDecimal("8.00"));
            produto2.setCategoria("Salgados");
            produto2.setDisponivel(true);
            produto2.setRestaurante(restaurante2);

            produtoRepository.saveAll(Arrays.asList(produto1, produto2));
        }
    }
}
