package com.example.web.services;

import org.springframework.stereotype.Service;

import com.example.web.entities.Animal;
import com.example.web.repositories.AnimalRepository;

@Service
public class AnimalService extends CrudServiceImpl<Animal> {
	public AnimalService(AnimalRepository repository) {
		super(repository);
	}

//	@Autowired
//	private AnimalRepository animalRepo;
//	
//	// Zoo zoo, String name, String gender, String species, String habitat, Integer userId
//	public Animal createAnimal(AnimalInputs input) {
//		Zoo zoo = new Zoo();
//		zoo.setId(input.getZooId());
//		System.out.println(zoo.getId());
//		Animal newAnimal = new Animal(zoo, input.getName(), input.getGender(), input.getAnimalImg(), input.getSpecies(), input.getHabitat(), input.getUserId());
//		animalRepo.save(newAnimal);
//		return newAnimal;
//	}
//	
//	public Optional<Animal> getAnimalById(Integer id) {
//		return animalRepo.findById(id);
//	}
//	
//	public List<Animal> findAllAnimals() {
//		return animalRepo.findAll();
//	}
//	
//	public void deleteAnimalById(Integer id) {
//		animalRepo.deleteById(id);
//	}
//	
//	public Animal saveUpdatedAnimal(Animal animal) {
//		return animalRepo.save(animal);
//	}
}
