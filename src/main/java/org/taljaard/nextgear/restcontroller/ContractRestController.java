package org.taljaard.nextgear.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.services.IContractService;

@RestController
@RequestMapping("/api")
public class ContractRestController {
	
	@Autowired
	private IContractService service;

	// list of all approved contracts
	@GetMapping(path = "/contracts/approved")
	public List<Contract> retrieveAllApprovedContracts() {
		return service.findAllApproved();
	}
	
	@GetMapping(path = "/contracts")
	public List<Contract> retrieveAllContracts() {
		return service.findAll();
	}

	// single contract given contract id?
	@GetMapping(path = "/contracts/{id}")
	public Contract retrieveContractGivenContractId(@PathVariable Integer id) {
		return service.findById(id);
	}

	// post create new contract //validation needed
	@PostMapping(path = "/contracts/new")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Contract createNewContract(@RequestBody Contract newContract) {
		return service.save(newContract);
	}

	// patch/put && update contract
	@PutMapping(path = "/contracts/update")
	public Contract updateContract(@RequestBody Contract updatedContract) {
		return service.update(updatedContract);
	}

	// delete contract
	@DeleteMapping(path = "/contracts/delete/{id}")
	public Boolean deleteContract(@PathVariable Integer id) {
		service.deleteById(id);
		return Boolean.TRUE;
	}
}
