/*
 * package com.exchange.test;
 * 
 * import static org.mockito.ArgumentMatchers.anyDouble; import static
 * org.mockito.Matchers.any; import static org.mockito.Mockito.when; import
 * static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
 * org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 * 
 * import com.exchange.controller.BillController; import
 * com.exchange.model.BillRequest; import com.exchange.service.DiscountService;
 * 
 * @WebMvcTest(BillController.class) public class BillControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @MockBean private DiscountService discountService;
 * 
 * private BillRequest bill;
 * 
 * @BeforeEach public void setUp() {
 * 
 * bill = new BillRequest(); bill.setTotalAmount(1150);
 * bill.setAffiliate(false); bill.setEmployee(false);
 * 
 * bill.setCustomerTenure(3); bill.setOriginalCurrency("USD");
 * bill.setTargetCurrency("EUR");
 * 
 * }
 * 
 * @Test public void calculate_ShouldReturnFinalAmount() throws Exception {
 * 
 * double expectedFinalAmount = 1035.66;
 * 
 * when(discountService.applyDiscounts(bill)).thenReturn(1035.66);
 * when(discountService.calculateFinalAmount(anyDouble(),
 * any())).thenReturn(expectedFinalAmount);
 * 
 * mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate").contentType(
 * MediaType.APPLICATION_JSON) .content("{\n" + "  \"items\": [\n" +
 * "    {\"name\": \"Laptop\", \"category\": \"electronics\", \"price\": 1000},\n"
 * +
 * "    {\"name\": \"Headphones\", \"category\": \"electronics\", \"price\": 150}\n"
 * + "  ],\n" + "  \"totalAmount\": 1150,\n" + "  \"userType\": \"regular\",\n"
 * + "  \"customerTenure\": 3,\n" + "  \"originalCurrency\": \"USD\",\n" +
 * "  \"targetCurrency\": \"EUR\",\n" + "  \"isEmployee\": false,\n" +
 * "  \"isAffiliate\": false\n" + "}")
 * .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
 * .andExpect(jsonPath("$").value(expectedFinalAmount)); } }
 */