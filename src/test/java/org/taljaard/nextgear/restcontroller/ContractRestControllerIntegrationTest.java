package org.taljaard.nextgear.restcontroller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.taljaard.nextgear.enums.ContractType;
import org.taljaard.nextgear.exceptions.dto.ExceptionDTO;
import org.taljaard.nextgear.model.dto.Contract;
import org.taljaard.nextgear.mybatis.dao.Contracts;
import org.taljaard.nextgear.tests.base.ITBase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
public class ContractRestControllerIntegrationTest extends ITBase {

	private static final String BASE_URI = "/api/contracts";

	private MediaType contentMediaType = new org.springframework.http.MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private Contracts contract;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		contract = createNewContractDOA();
	}

	@Test
	public void retrieveAllContracts() throws Exception {
		mockMvc.perform(get(BASE_URI)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentMediaType)).andExpect(jsonPath("$", hasSize(5))).andReturn();

	}

	@Test
	public void retreiveAllApprovedContracts() throws Exception {
		mockMvc.perform(get(BASE_URI + "/approved")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentMediaType)).andExpect(jsonPath("$", hasSize(3)));
	}

	@Test
	public void retrieveContractGivenIdSuccess() throws Exception {
		insertContract(contract);
		mockMvc.perform(get(BASE_URI + "/" + contract.getId())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentMediaType))
				.andExpect(jsonPath("$.amountRequested").value(contract.getAmountRequested()))
				.andExpect(jsonPath("$.businessNumber", is(contract.getBusinessNumber())))
				.andExpect(jsonPath("$.name", is(contract.getName())))
				.andExpect(jsonPath("$.type", is(contract.getType())))
				.andExpect(jsonPath("$.status", is(contract.getStatus())));

		deleteContract(contract.getId());
	}

	@Test
	public void retrieveContractGivenIdNoContractFoundException() throws Exception {
		mockMvc.perform(get(BASE_URI + "/0")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void deleteContractGivenIdSuccess() throws Exception {

		insertContract(contract);

		mockMvc.perform(delete(BASE_URI + "/delete/" + contract.getId())).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void deleteContractGivenIdFailure() throws Exception {

		mockMvc.perform(delete(BASE_URI + "/delete/0")).andDo(print()).andExpect(status().isInternalServerError());
	}

	@Test
	public void saveContractSuccess() throws Exception {

		createNewContractDOA();

		String payload = gsonConverter.toJson(contract);
		MvcResult result = mockMvc.perform(post(BASE_URI + "/new").contentType(contentMediaType).content(payload))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.amountRequested").value(contract.getAmountRequested()))
				.andExpect(jsonPath("$.businessNumber", is(contract.getBusinessNumber())))
				.andExpect(jsonPath("$.name", is(contract.getName())))
				.andExpect(jsonPath("$.type", is(contract.getType())))
				.andExpect(jsonPath("$.status", is(contract.getStatus())))
				.andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(result.getResponse().getContentAsString());
		int id = node.get("id").asInt();
		deleteContract(Integer.valueOf(id));
	}

	@Test
	public void saveContractInValidContractNameMessage() throws Exception {
		createNewContractDOA();
		contract.setName("");

		String payload = gsonConverter.toJson(contract);
		mockMvc.perform(post(BASE_URI + "/new").contentType(contentMediaType).content(payload)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void saveContractInValidContractBusinessNumberMessage() throws Exception {
		createNewContractDOA();
		contract.setBusinessNumber("");

		String payload = gsonConverter.toJson(contract);
		mockMvc.perform(post(BASE_URI + "/new").contentType(contentMediaType).content(payload))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void saveContractInValidContractAmountRequestdedMessage() throws Exception {
		createNewContractDOA();
		contract.setAmountRequested(null);

		String payload = gsonConverter.toJson(contract);
		mockMvc.perform(post(BASE_URI + "/new").contentType(contentMediaType).content(payload))
				.andExpect(status().isBadRequest());

	}
	
	@Test
	public void updateContractNameSuccess() throws Exception {

		MvcResult result = mockMvc.perform(get(BASE_URI + "/3")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentMediaType)).andReturn();
		
		Contract returned = gsonConverter.fromJson(result.getResponse().getContentAsString(), Contract.class);
		
		returned.setName("Updated-Contract-Name");
		
		mockMvc.perform(put(BASE_URI + "/update").contentType(contentMediaType).content(gsonConverter.toJson(returned)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(returned.getName())));

	}
	
	@Test
	public void updateContractRequestedAmountSuccess() throws Exception {

		MvcResult result = mockMvc.perform(get(BASE_URI + "/3")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentMediaType)).andReturn();
		
		Contract returned = gsonConverter.fromJson(result.getResponse().getContentAsString(), Contract.class);
		
		returned.setAmountRequested(BigDecimal.valueOf(80000));
		
		mockMvc.perform(put(BASE_URI + "/update").contentType(contentMediaType).content(gsonConverter.toJson(returned)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amountRequested").value(returned.getAmountRequested()))
				.andExpect(jsonPath("$.type", is(ContractType.Sales.toString())))
				.andExpect(jsonPath("$.id").value(returned.getId()));
	}
	
	@Test
	public void updateContractRequestedAmountInvalid() throws Exception {

		MvcResult result = mockMvc.perform(get(BASE_URI + "/3")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentMediaType)).andReturn();
		
		Contract returned = gsonConverter.fromJson(result.getResponse().getContentAsString(), Contract.class);
		
		returned.setAmountRequested(BigDecimal.ZERO);
		
		result = mockMvc.perform(put(BASE_URI + "/update").contentType(contentMediaType).content(gsonConverter.toJson(returned)))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andReturn();
		
		ExceptionDTO exception = gsonConverter.fromJson(result.getResponse().getContentAsString(), ExceptionDTO.class);
		
		assertTrue(StringUtils.containsIgnoreCase(exception.getMessage(), "Contract amount requested invalid"));
	}

}
