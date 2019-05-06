package com.kabat.petfinder.services;

import com.kabat.petfinder.dtos.PetDto;
import com.kabat.petfinder.entities.Pet;
import com.kabat.petfinder.repositories.PetRepository;
import com.kabat.petfinder.utils.PetMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetDto persistPet(PetDto petDto) {
        Pet petEntity = PetMapper.mapToEntity(petDto);
        LocalDateTime currentLocalDate = LocalDateTime.now();
        petEntity.setCreatedAt(currentLocalDate);
        petEntity.setId(UUID.randomUUID());
        petEntity.getPictures().forEach(picture -> picture.setPetId(petEntity.getId()));

        if (petDto.getLastSeen() == null) {
            petEntity.setLastSeen(currentLocalDate);
        }

        Pet savedPet = petRepository.save(petEntity);
        return PetMapper.mapToDto(savedPet);
    }

    @Override
    public List<PetDto> getAllPetsFromLast30Days() {
        return petRepository.findAllWithCreationDateTimeAfter(LocalDateTime.now().minusDays(30))
                .stream()
                .map(PetMapper::mapToDto)
                .collect(toList());
    }
}
