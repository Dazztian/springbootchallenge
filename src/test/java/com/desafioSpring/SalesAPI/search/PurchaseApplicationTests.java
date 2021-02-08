package com.desafioSpring.SalesAPI.search;


import com.desafioSpring.SalesAPI.purchase.ApiPurchaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiPurchaseApplication.class)
@AutoConfigureMockMvc
class PurchaseApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	String purchaseURL= "/api/v1/purchase-request";
	String shoppingCartURL= "/api/v1/purchase-request/shoppingcart";

	@Test
	void simplePurchase() throws Exception {
		this.mockMvc.perform(post( purchaseURL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":7,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":7\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":2,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":6\n" +
						"        }\n" +
						"    ]\n" +
						"}"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"receipt\":{\"id\":100000,\"status\":\"OK\",\"articles\":[{\"productId\":7,\"discount\":30,\"quantity\":7},{\"productId\":2,\"discount\":30,\"quantity\":6}]},\"totalPrice\":687500,\"statusCode\":{\"code\":200,\"message\":\"La solicitud se completo con exito\"}}"));
	}


	@Test
	void notFoundProductPurchase() throws Exception {
		this.mockMvc.perform(post( purchaseURL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("\n" +
						"    {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":166,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":4\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":7,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":14\n" +
						"        }\n" +
						"    ]\n" +
						"}\n"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"receipt\":{\"id\":100000,\"status\":\"ERROR, NOT FOUND\",\"articles\":[{\"productId\":166,\"discount\":30,\"quantity\":4},{\"productId\":7,\"discount\":30,\"quantity\":14}]},\"totalPrice\":0,\"statusCode\":{\"code\":404,\"message\":\"No se pudo completar la solicitud, no se encontró uno de los productos\"}}"));
	}


	@Test
	void excessQuantityPurchase() throws Exception {
		this.mockMvc.perform(post( purchaseURL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("\n" +
						"    {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":1,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":400\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":7,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":14\n" +
						"        }\n" +
						"    ]\n" +
						"}\n"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"receipt\":{\"id\":100000,\"status\":\"ERROR, WRONG QUANTITY\",\"articles\":[{\"productId\":1,\"discount\":30,\"quantity\":400},{\"productId\":7,\"discount\":30,\"quantity\":14}]},\"totalPrice\":0,\"statusCode\":{\"code\":400,\"message\":\"No se pudo completar la solicitud,  uno de los productos no tiene la cantidad suficiente en stock\"}}"));
	}

	@Test
	void excessQuantitySameItemRepeatedTimesPurchase() throws Exception {
		this.mockMvc.perform(post( purchaseURL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("\n" +
						"    {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":7,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":4\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":7,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":12\n" +
						"        }\n" +
						"    ]\n" +
						"}\n"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"receipt\":{\"id\":100000,\"status\":\"ERROR, WRONG QUANTITY\",\"articles\":[{\"productId\":7,\"discount\":30,\"quantity\":4},{\"productId\":7,\"discount\":30,\"quantity\":12}]},\"totalPrice\":0,\"statusCode\":{\"code\":400,\"message\":\"No se pudo completar la solicitud,  uno de los productos no tiene la cantidad suficiente en stock\"}}"));
	}

	@Test
	void shoppingCarPurchaseSuccess() throws Exception {
		this.mockMvc.perform(post( shoppingCartURL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[\n" +
						"    {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":2,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":2\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":3,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":3\n" +
						"        }\n" +
						"    ]\n" +
						"},\n" +
						"   {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":7,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":5\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":4,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":1\n" +
						"        }\n" +
						"    ]\n" +
						"}\n" +
						"]"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"responseList\":[{\"receipt\":{\"id\":100000,\"status\":\"OK\",\"articles\":[{\"productId\":2,\"discount\":30,\"quantity\":2},{\"productId\":3,\"discount\":30,\"quantity\":3}]},\"totalPrice\":159650,\"statusCode\":{\"code\":200,\"message\":\"La solicitud se completo con exito\"}},{\"receipt\":{\"id\":100000,\"status\":\"OK\",\"articles\":[{\"productId\":7,\"discount\":30,\"quantity\":5},{\"productId\":4,\"discount\":30,\"quantity\":1}]},\"totalPrice\":656000,\"statusCode\":{\"code\":200,\"message\":\"La solicitud se completo con exito\"}}],\"totalPrice\":815650}"));
	}



	//Te permite hacer la primera compra, la cual descuenta del stock
	//Luego al querer hacer la 2da compra del carrito no podés x falta de stock
	//Te informa que la 1er compra tiene éxito y la 2da no pudo realizarse
	@Test
	void shoppingCartWithoutStockAfter1stPurchase() throws Exception {
		this.mockMvc.perform(post( shoppingCartURL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[\n" +
						"    {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":8,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":2\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":8,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":4\n" +
						"        }\n" +
						"    ]\n" +
						"},\n" +
						"   {\n" +
						"    \"username\":\"perroloco\",\n" +
						"    \"articles\":\n" +
						"    [\n" +
						"        {\n" +
						"            \"productId\":8,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":6\n" +
						"        },\n" +
						"        {\n" +
						"            \"productId\":8,\n" +
						"            \"discount\":30,\n" +
						"            \"quantity\":10\n" +
						"        }\n" +
						"    ]\n" +
						"}\n" +
						"]"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"responseList\":[{\"receipt\":{\"id\":100000,\"status\":\"OK\",\"articles\":[{\"productId\":8,\"discount\":30,\"quantity\":2},{\"productId\":8,\"discount\":30,\"quantity\":4}]},\"totalPrice\":76000,\"statusCode\":{\"code\":200,\"message\":\"La solicitud se completo con exito\"}},{\"receipt\":{\"id\":100000,\"status\":\"ERROR, WRONG QUANTITY\",\"articles\":[{\"productId\":8,\"discount\":30,\"quantity\":6},{\"productId\":8,\"discount\":30,\"quantity\":10}]},\"totalPrice\":0,\"statusCode\":{\"code\":400,\"message\":\"No se pudo completar la solicitud,  uno de los productos no tiene la cantidad suficiente en stock\"}}],\"totalPrice\":76000}"));
	}





	
}
