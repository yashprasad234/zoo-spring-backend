package com.example.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.entities.Animal;
import com.example.web.services.CrudService;

@RestController
@RequestMapping("/animal")
public class AnimalController extends CrudController<Animal, Integer>{

	protected AnimalController(CrudService<Animal, Integer> service) {
		super(service);
	}

//	@Autowired
//	private AnimalService animalService;
//	
//	@Autowired
//	private ZooService zooService;
//	
//	@PostMapping("/create")
//	public ResponseEntity<Animal> create(@RequestBody AnimalInputs input) {
//		Animal newAnimal = animalService.createAnimal(input);
//		return ResponseEntity.status(201).body(newAnimal);
//	}
//	
//	@GetMapping("/list")
//	public ResponseEntity<?> fetchAnimals() {
//		List<Animal> animals = animalService.findAllAnimals();
//		return ResponseEntity.status(200).body(animals);
//	}
//	
//	@GetMapping("/id/{id}")
//	public ResponseEntity<?> fetchAnimal(@PathVariable Integer id) {
//		Optional<Animal> animal = animalService.getAnimalById(id);
//		if(animal == null) return ResponseEntity.status(404).body("Failed to fetch animals");
//		return ResponseEntity.status(200).body(animal);
//	}
//
//	@PutMapping("/id/{id}")
//	public ResponseEntity<?> transferAnimal(@PathVariable Integer id, @RequestParam Integer zooId) {
//		Optional<Animal> animal = animalService.getAnimalById(id);
//		if(animal == null) return ResponseEntity.status(404).body("Failed to fetch animals");
//		Optional<Zoo> zoo = zooService.findZooById(zooId);
//		if(zoo == null) return ResponseEntity.status(404).body("No zoo with the given Id found");
//		animal.get().setZoo(zoo.get());
//		return ResponseEntity.status(200).body(animalService.saveUpdatedAnimal(animal.get()));
//	}
//
//	@DeleteMapping("/id/{id}")
//	public ResponseEntity<?> deleteAnimal(@PathVariable Integer id) {
//		Optional<Animal> animal = animalService.getAnimalById(id);
//		if(animal == null) return ResponseEntity.status(404).body("Failed to fetch animals");
//		animalService.deleteAnimalById(id);
//		return ResponseEntity.status(200).body("Animal deleted successfully");
//	}
//	
//	@GetMapping("/zoo/{id}")
//	public ResponseEntity<?> fetchAnimalsForZoo(@PathVariable Integer id) {
//		Optional<Zoo> zoo = zooService.findZooById(id);
//		if(zoo == null) return ResponseEntity.status(404).body("No zoo with the given id exists");
//		else {
//			ArrayList<Animal> list = new ArrayList<>();
//			List<Animal> allAnimals = animalService.findAllAnimals();
//			
//			for(Animal a: allAnimals) {
//				if(zoo.get().getId() == a.getZoo().getId() ) {
//					list.add(a);
//				}
//			}
//			if(list.size() == 0) return ResponseEntity.status(400).body("No animals in the zoo");
//			return ResponseEntity.status(200).body(list);
//		}
//	}
}
