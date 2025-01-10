**Exchange Rate API Documentation**  
**Overview**

This application uses Spring Boot and ExchangeRate-API to fetch real-time exchange rates of currencies and perform currency conversions**.**

## **Authentication**

To use this API, you need an API key from ExchangeRate-API. This key should be included in the `application.properties` file.

## **Endpoints**

### **1\. Get Exchange Rates**

Endpoint: `/api/rates`

Method: `GET`

Parameters:

* `base` (optional): The base currency code (default is `USD`).

Example Request:

(http)GET /api/rates?base=EUR

Example Response:

(Json)

{

  "USD": 1.234,

  "GBP": 0.876,

  ...

}

### **2\. Convert Currency**

Endpoint: `/api/convert`

Method: `POST`

Request Body**:**

**(Json)**

{

  "from": "USD",

  "to": "EUR",

  "amount": 100

}

Example Response:

{

  "from": "USD",

  "to": "EUR",

  "amount": 100,

  "convertedAmount": 82.34

}

## **Request/Response Formats**

### **Get Exchange Rates**

* Request Parameters: Base currency code (`base`), default is `USD`.  
* Response: A JSON object containing key-value pairs of currency codes and their corresponding exchange rates.

### **Convert Currency**

* Request Body: JSON object with `from` currency code, `to` currency code, and `amount` to be converted.  
* Response: JSON object containing the original amount, converted amount, and the currencies involved.

## **Error Codes**

* `400 Bad Request`: Invalid input or missing required parameters.  
* `500 Internal Server Error`: Server error while processing the request.

**Example Error Response:**

**(Json)**

{

  "error": "Invalid input",

  "message": "Invalid 'from' or 'to' currency code provided."

}

## **Usage Examples**

### **Get Exchange Rates**

(http)

GET /api/rates?base=USD

**Convert Currency**

POST /api/convert

Content-Type: application/json

{

  "from": "USD",

  "to": "INR",

  "amount": 100

}

