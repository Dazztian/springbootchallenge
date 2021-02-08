package com.desafioSpring.SalesAPI.search;


import com.desafioSpring.SalesAPI.purchase.ApiPurchaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SearchApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	String searchURL= "http://localhost:8080/api/v1/articles";
	String purchaseURL= "/api/v1/purchase-request";


	//El test rompe únicamente xq los sorts le cambian el orden
	@Test
	void getAllProducts() throws Exception {
	String request =  searchURL;
		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":1,\"category\":\"Herramientas\",\"name\":\"Desmalezadora\",\"brand\":\"Makita\",\"price\":9600,\"quantity\":5,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":2,\"category\":\"Herramientas\",\"name\":\"Taladro\",\"brand\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":3,\"category\":\"Herramientas\",\"name\":\"Soldadora\",\"brand\":\"Black & Decker\",\"price\":7215,\"quantity\":10,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":5,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":6,\"category\":\"Deportes\",\"name\":\"Camiseta\",\"brand\":\"Topper\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":7,\"category\":\"Celulares\",\"name\":\"Redmi Note 9\",\"brand\":\"Xiaomi\",\"price\":40000,\"quantity\":15,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":8,\"category\":\"Celulares\",\"name\":\"Smartwatch\",\"brand\":\"Noga\",\"price\":1900,\"quantity\":20,\"freeShipping\":false,\"prestige\":\"**\"},{\"id\":9,\"category\":\"Indumentaria\",\"name\":\"Remera\",\"brand\":\"Taverniti\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":10,\"category\":\"Indumentaria\",\"name\":\"Chomba\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":11,\"category\":\"Indumentaria\",\"name\":\"Medias\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"freeShipping\":false,\"prestige\":\"*\"},{\"id\":12,\"category\":\"Indumentaria\",\"name\":\"Short\",\"brand\":\"Lacoste\",\"price\":3900,\"quantity\":9,\"freeShipping\":true,\"prestige\":\"***\"}]")));
	}

	@Test
	void getProductsFilteredByCategory() throws Exception {
		String request = searchURL.concat("?category=Deportes");

		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":5,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":6,\"category\":\"Deportes\",\"name\":\"Camiseta\",\"brand\":\"Topper\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"}]")));
	}

	@Test
	void getProductsFilteredByCategoryNamePrice() throws Exception {
		String request = searchURL.concat("?category=deportes&name=zapatillas deportivas&price=14000");
		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"}]")));
	}



	@Test
	void getAllProductsSortByASC() throws Exception {
		String request = searchURL.concat("?sortMethod=0");
		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":6,\"category\":\"Deportes\",\"name\":\"Camiseta\",\"brand\":\"Topper\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":10,\"category\":\"Indumentaria\",\"name\":\"Chomba\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":1,\"category\":\"Herramientas\",\"name\":\"Desmalezadora\",\"brand\":\"Makita\",\"price\":9600,\"quantity\":5,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":11,\"category\":\"Indumentaria\",\"name\":\"Medias\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"freeShipping\":false,\"prestige\":\"*\"},{\"id\":7,\"category\":\"Celulares\",\"name\":\"Redmi Note 9\",\"brand\":\"Xiaomi\",\"price\":40000,\"quantity\":15,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":9,\"category\":\"Indumentaria\",\"name\":\"Remera\",\"brand\":\"Taverniti\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":12,\"category\":\"Indumentaria\",\"name\":\"Short\",\"brand\":\"Lacoste\",\"price\":3900,\"quantity\":9,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":8,\"category\":\"Celulares\",\"name\":\"Smartwatch\",\"brand\":\"Noga\",\"price\":1900,\"quantity\":20,\"freeShipping\":false,\"prestige\":\"**\"},{\"id\":3,\"category\":\"Herramientas\",\"name\":\"Soldadora\",\"brand\":\"Black & Decker\",\"price\":7215,\"quantity\":10,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":2,\"category\":\"Herramientas\",\"name\":\"Taladro\",\"brand\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":5,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"freeShipping\":true,\"prestige\":\"*****\"}]")));
	}

	@Test
	void getAllProductsSortByDESC() throws Exception {
		String request = searchURL.concat("?sortMethod=1");
		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":5,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":2,\"category\":\"Herramientas\",\"name\":\"Taladro\",\"brand\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":3,\"category\":\"Herramientas\",\"name\":\"Soldadora\",\"brand\":\"Black & Decker\",\"price\":7215,\"quantity\":10,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":8,\"category\":\"Celulares\",\"name\":\"Smartwatch\",\"brand\":\"Noga\",\"price\":1900,\"quantity\":20,\"freeShipping\":false,\"prestige\":\"**\"},{\"id\":12,\"category\":\"Indumentaria\",\"name\":\"Short\",\"brand\":\"Lacoste\",\"price\":3900,\"quantity\":9,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":9,\"category\":\"Indumentaria\",\"name\":\"Remera\",\"brand\":\"Taverniti\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":7,\"category\":\"Celulares\",\"name\":\"Redmi Note 9\",\"brand\":\"Xiaomi\",\"price\":40000,\"quantity\":15,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":11,\"category\":\"Indumentaria\",\"name\":\"Medias\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"freeShipping\":false,\"prestige\":\"*\"},{\"id\":1,\"category\":\"Herramientas\",\"name\":\"Desmalezadora\",\"brand\":\"Makita\",\"price\":9600,\"quantity\":5,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":10,\"category\":\"Indumentaria\",\"name\":\"Chomba\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":6,\"category\":\"Deportes\",\"name\":\"Camiseta\",\"brand\":\"Topper\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"}]")));
	}

	@Test
	void getAllProductsSortByHighestPrice() throws Exception {
		String request = searchURL.concat("?sortMethod=2");
		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":7,\"category\":\"Celulares\",\"name\":\"Redmi Note 9\",\"brand\":\"Xiaomi\",\"price\":40000,\"quantity\":15,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":5,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":2,\"category\":\"Herramientas\",\"name\":\"Taladro\",\"brand\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":1,\"category\":\"Herramientas\",\"name\":\"Desmalezadora\",\"brand\":\"Makita\",\"price\":9600,\"quantity\":5,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":3,\"category\":\"Herramientas\",\"name\":\"Soldadora\",\"brand\":\"Black & Decker\",\"price\":7215,\"quantity\":10,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":12,\"category\":\"Indumentaria\",\"name\":\"Short\",\"brand\":\"Lacoste\",\"price\":3900,\"quantity\":9,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":10,\"category\":\"Indumentaria\",\"name\":\"Chomba\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":6,\"category\":\"Deportes\",\"name\":\"Camiseta\",\"brand\":\"Topper\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":9,\"category\":\"Indumentaria\",\"name\":\"Remera\",\"brand\":\"Taverniti\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":8,\"category\":\"Celulares\",\"name\":\"Smartwatch\",\"brand\":\"Noga\",\"price\":1900,\"quantity\":20,\"freeShipping\":false,\"prestige\":\"**\"},{\"id\":11,\"category\":\"Indumentaria\",\"name\":\"Medias\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"freeShipping\":false,\"prestige\":\"*\"}]")));
	}

	@Test
	void getAllProductsSortByLowestPrice() throws Exception {
		String request = searchURL.concat("?sortMethod=3");
		this.mockMvc.perform(get( request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":11,\"category\":\"Indumentaria\",\"name\":\"Medias\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"freeShipping\":false,\"prestige\":\"*\"},{\"id\":8,\"category\":\"Celulares\",\"name\":\"Smartwatch\",\"brand\":\"Noga\",\"price\":1900,\"quantity\":20,\"freeShipping\":false,\"prestige\":\"**\"},{\"id\":6,\"category\":\"Deportes\",\"name\":\"Camiseta\",\"brand\":\"Topper\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":9,\"category\":\"Indumentaria\",\"name\":\"Remera\",\"brand\":\"Taverniti\",\"price\":2300,\"quantity\":2,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":10,\"category\":\"Indumentaria\",\"name\":\"Chomba\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"freeShipping\":false,\"prestige\":\"***\"},{\"id\":12,\"category\":\"Indumentaria\",\"name\":\"Short\",\"brand\":\"Lacoste\",\"price\":3900,\"quantity\":9,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":3,\"category\":\"Herramientas\",\"name\":\"Soldadora\",\"brand\":\"Black & Decker\",\"price\":7215,\"quantity\":10,\"freeShipping\":true,\"prestige\":\"***\"},{\"id\":1,\"category\":\"Herramientas\",\"name\":\"Desmalezadora\",\"brand\":\"Makita\",\"price\":9600,\"quantity\":5,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":2,\"category\":\"Herramientas\",\"name\":\"Taladro\",\"brand\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"freeShipping\":true,\"prestige\":\"****\"},{\"id\":5,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":4,\"category\":\"Deportes\",\"name\":\"Zapatillas Deportivas\",\"brand\":\"Nike\",\"price\":14000,\"quantity\":4,\"freeShipping\":true,\"prestige\":\"*****\"},{\"id\":7,\"category\":\"Celulares\",\"name\":\"Redmi Note 9\",\"brand\":\"Xiaomi\",\"price\":40000,\"quantity\":15,\"freeShipping\":true,\"prestige\":\"****\"}]")));
	}
	
	//Agregar tests que rompan


	
}
