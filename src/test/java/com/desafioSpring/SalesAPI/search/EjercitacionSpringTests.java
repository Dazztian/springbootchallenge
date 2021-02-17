package com.desafioSpring.SalesAPI.search;


import com.desafioSpring.SalesAPI.search.DAO.impl.ApiSearchDAOImpl;
import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.desafioSpring.SalesAPI.search.DTO.searchRequestDTO;
import com.desafioSpring.SalesAPI.search.service.SearchService;
import com.desafioSpring.SalesAPI.search.service.impl.SearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;


@SpringBootTest
@AutoConfigureMockMvc
class EjercitacionSpringTests {

	@Autowired
	private MockMvc mockMvc;


	//¿Está bien elegido el nombre?
	//O toodo deriva de que yo puse mal las responsabilidades?
	@Mock
	private SearchServiceImpl localMockRepository;


	/*
	¿¿Esto qué onda?? yo lo copié tal cual el ejemplo
	¿Es totalmente necesario? ¿Yo debería tener una versión que se ajuste a mi código?

	@Mock
	private ApiSearchDAOImpl apiSearchDAOImpl;

	SearchService searchService;

	@BeforeEach
	void setup () {
		initMocks(this);
		searchService = new SearchServiceImpl(apiSearchDAOImpl,searchService);
	}*/


	@Test
	void contextLoads() {
	}


	String searchURL= "http://localhost:8080/api/v1/articles";


	//Esto debería estar bajo un tag o algo que represente  el seteo inicial necesario

	//ESTOS PRODUCTOS DEBEN SER LOS MISMOS DEL product.json



	ProductDTO p1=new ProductDTO(1,"Desmalezadora", "Herramientas", "Makita",9600, 5,true, "****");
	ProductDTO p2=new ProductDTO(2, "Taladro", "Herramientas", "Black & Decker", 12500, 7, true, "****");



	ProductDTO p3=new ProductDTO(3,
		"Soldadora",
		"Herramientas",
		"Black & Decker",
		7215,
		10,
		true,
		"***");

	ProductDTO p4=new ProductDTO(4,
			"Zapatillas Deportivas",
			"Deportes",
			"Nike",
			14000,
			4,
			true,
			"*****");

	ProductDTO p5=new ProductDTO(5,
			"Zapatillas Deportivas",
			"Deportes",
			"Adidas",
			13650,
			6,
			true,
			"*****");


	ProductDTO p6=new ProductDTO(6,
			"Camiseta",
			"Deportes",
			"Topper",
			2300,
			2,
			false,
			"***");

	ProductDTO p7=new ProductDTO(7,
			"Redmi Note 9",
			"Celulares",
			"Xiaomi",
			40000,
			15,
			true,
			"****");

	ProductDTO p8=new ProductDTO(8,
			"Smartwatch",
			"Celulares",
			"Noga",
			1900,
			20,
			false,
			"**");

	ProductDTO p9=new ProductDTO(9,
			"Remera",
			"Indumentaria",
			"Taverniti",
			2300,
			2,
			false,
			"***");

	ProductDTO p10=new ProductDTO(10,
			"Chomba",
			"Indumentaria",
			"Taverniti",
			2400,
			6,
			false,
			"***");

	ProductDTO p11=new ProductDTO(11,
			"Medias",
			"Indumentaria",
			"Mistral",
			500,
			8,
			false,
			"*");

	ProductDTO p12=new ProductDTO(12,
			"Short",
			"Indumentaria",
			"Lacoste",
			3900,
			9,
			true,
			"***");




	@Test
	void get4Products(){
		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		productos.add(p4);


		//SearchServiceImpl localMockRepository = Mockito.mock(SearchServiceImpl.class);

		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());

		Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		assertEquals(result.size(), 4);

	}

	@Test
	void getProductsByCategory(){

		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p5);//NO Es de categoria herramienta
		productos.add(p6);//NO Es de categoria herramienta

		//SearchServiceImpl localMockRepository = Mockito.mock(SearchServiceImpl.class);

		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.of("Herramientas"),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());

		//Acá hardcodeamos lo que queremos q devuelva
		Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		assertEquals(result.size(), 2);

	}

	//Hay que arreglar el test
	@Test
	void getProductsFilteredByCategoryAndFreeShipping(){

		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p1); // es herramienta con freeshipping
		productos.add(p2); // es herramienta con freeshipping
		productos.add(p4); // es herramienta con freeshipping


		//SearchServiceImpl localMockRepository = Mockito.mock(SearchServiceImpl.class);

		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.of("Herramientas"),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(true),Optional.empty(),Optional.empty(),Optional.empty());

		//Acá hardcodeamos lo que queremos q devuelva
		//En este caso pisamos lo que devuelve la búsqueda de productos con la lista armada más arriba
		/*Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		assertEquals(result.size(), 3);*/

		SearchServiceImpl searchService= new SearchServiceImpl();
		List<ProductDTO> response =   searchService.getProducts(searchRequestDTO);

		/*asserJsonEquals

		assertEquals(response, productos);*/


		//assertEquals(response.size(), productos.size());



	}


	//Ejercicio 4
	//ordenado ASC
	@Test
	void getProductsSortedASC(){

		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p6);
		productos.add(p10);
		productos.add(p1);
		productos.add(p11);


		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(0));

		Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		//Hardcodeamos los resultados para que nos devuelva la lista ordenada de arriba
		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		//Acá deberíamos comprar contra la list de productos ordenada
		//Sin embargo... contra qué comparo?
		//Esta es la parte que no logro entender

		//assertEquals(result, );

		//Básicamente mi consulta vuelva a lo mismo una y otra vez, "de que me sirve mockear" en estos casos si luego
		//volveré a comparar contra lo mismo, diferente sería que "limite" el scope de mi función a los elementos
		//mockeados y compare contra ellos. Ejemplo: cargo 4 productos y luego cuando REALMENTE llamo a mi controlador
		//lo hago para que opere contra esos 4 productos
	}

	//Ejercicio 4
	//ordenado DESC
	@Test
	void getProductsSortedDESC(){

		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p4);
		productos.add(p5);
		productos.add(p2);
		productos.add(p3);


		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(1));

		Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		//Acá deberíamos comprar contra la list de productos ordenada
		assertEquals(result.size(), 3);

	}

	//Ejercicio 5
	//ordenado HighestPrice
	@Test
	void getProductsSorteHighestPrice(){

		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p7);
		productos.add(p4);
		productos.add(p5);
		productos.add(p2);


		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(1));

		Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		//Acá deberíamos comprar contra la list de productos ordenada
		assertEquals(result.size(), 3);

	}

	//Ejercicio 5
	//ordenado lowestPrice
	@Test
	void getProductsSorteLowestPrice(){

		ArrayList<ProductDTO> productos = new ArrayList<>();

		productos.add(p11);
		productos.add(p8);
		productos.add(p6);
		productos.add(p9);


		searchRequestDTO searchRequestDTO = new searchRequestDTO(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(1));

		Mockito.when(localMockRepository.getProducts(searchRequestDTO)).thenReturn(productos);

		List<ProductDTO> result = localMockRepository.getProducts(searchRequestDTO);

		//Acá deberíamos comprar contra la list de productos ordenada
		assertEquals(result.size(), 3);

	}

	
}
