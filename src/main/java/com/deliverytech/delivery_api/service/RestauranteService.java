package com.deliverytech.delivery_api.service;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.requests.RestauranteDTO;
import com.deliverytech.delivery_api.dto.responses.RestauranteResponseDTO;
import com.deliverytech.delivery_api.exceptions.BusinessException;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
public class RestauranteService {
    private final RestauranteRepository repository;
    private final ModelMapper mapper;

    public RestauranteService(RestauranteRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public RestauranteResponseDTO cadastrar(RestauranteDTO dto){
        if(repository.existsByNome(dto.getNome())){
            throw new BusinessException("Restaurante com esse nome já cadastrado.");
        }

        Restaurante r = mapper.map(dto, Restaurante.class);
        r.setAtivo(true);
        r.setAvaliacao(BigDecimal.ZERO);
        Restaurante salvo = repository.save(r);
        return mapper.map(salvo, RestauranteResponseDTO.class);
    }

    public List<Restaurante> listarAtivos(){
        return repository.findByAtivoTrue();
    }

    public List<Restaurante> buscarPorCategoria(String categoria){
        return repository.findByCategoriaAndAtivoTrue(categoria);
    }

    public Restaurante buscarPorId(Long id){
        return repository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Restaurante não encontrado."));
    }

    public void desativar(Long id){
        Restaurante restaurante =  buscarPorId(id);
        restaurante.setAtivo(false);
        repository.save(restaurante);
    } 

    
}
