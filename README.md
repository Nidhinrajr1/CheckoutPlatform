# CheckoutPlatform
Simplified e-commerce API with a single endpoint that performs a checkout action.

Checkout platform provides an single REST API end point which take a list of watches and return the total cost.

Watch catalogue:
 Below is a catalogue of four watches and their associated prices:
	_________________________________________________
	|Watch ID | Watch Name   | Unit Price | Discount |
	|001 	  | Rolex 		 | 100 		  |3 for 200 |
	|002 	  | Michael Kors | 80 		  |2 for 120 |
	|003 	  | Swatch 		 | 50         |          |
	|004 	  | Casio 		 | 30         |          |
    --------------------------------------------------
There are a few requirements worth noting here:
	● The first two products have a possible discount. As an example, if the user attempts to
	checkout three or six Rolex watches then they will receive the discount price once or twice,
	respectively.
	● There is no limit to the number of items or combinations of watches a user can checkout.
	● There is no limit to the number of times a discount can be used.
	● Similarly, a user can checkout a single item if they wish.
	
Endpoint reference
	As a further guideline here's an endpoint definition that you can use to design your API endpoint.

Request:
	POST http://localhost:8080/checkout
# Headers
	Accept: application/json
	Content-Type: application/json
# Body
	[
	"001",
	"002",
	"001",
	"004",
	"003"
	]

Response:
# Headers
	Content-Type: application/json
# Body
	{ "price": 360 }
	

API is developed using Spring Boot (version 2.6)framework and java (Version 11.0) language. 

Checkout logic for applying above offers is implemented in spring service layer. Controller accepts the list of watches and return its total price.

For the time being list of watches and offers/discounts are reding from json file. We can use a relational databases for storing these details.

Application uses spring MockMvc and @WebMvcTest for web layer tesing. 
