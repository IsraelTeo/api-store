package com.app.store_api.controller.resource;

import com.app.store_api.dto.CustomerDto;
import com.app.store_api.dto.criteria.SearchCustomerCriteriaDto;
import com.app.store_api.exception.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "customer", description = "Operations related with customer entity")
public interface ReservationResource {

    @Operation(
            summary = "Get list of customers with filters and pagination",
            description = """ 
        Retrieves a paginated list of customers based on the provided search criteria.
        You can filter by name, last name, dni, or creationDate.
        You can also specify the page number and size, and sort by a specific field and direction. 
        """,
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "name",
                            description = "Filter by customer's first name",
                            example = "John"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "lastName",
                            description = "Filter by customer's last name",
                            example = "Doe"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "dni",
                            description = "Filter by DNI",
                            example = "12345678"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "creationDate",
                            description = "Filter by creation date (yyyy-MM-dd)",
                            example = "2024-06-01"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "sortField",
                            description = "Field to sort by (e.g., 'lastName')",
                            example = "lastName"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "sortingDirection",
                            description = "Sort direction: 'asc' or 'desc'",
                            example = "asc"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "pageActual",
                            description = "Page number (starts at 0)",
                            example = "0"
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "pageSize",
                            description = "Number of records per page",
                            example = "10"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the information of customers",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerDto.class))
                            )

                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No customers found matching the search criteria",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error occurred while retrieving customers",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    ResponseEntity<List<CustomerDto>> getCustomers(SearchCustomerCriteriaDto criteriaDto);

    @Operation(
            summary = "Get the information of a single customer",
            description = """
        Retrieves detailed information about a customer based on the provided customer ID (UUID).
        """,
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Id of the customer to search",
                            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the information of a customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CustomerDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error occurred while retrieving customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    ResponseEntity<CustomerDto> getCustomerById(@Min(1) @PathVariable("id") UUID id);

    @Operation(
            summary = "Register a customer",
            description = """
        Registers a new customer in the system with the provided details.
        The request must include the customer's personal information such as name, last name, DNI, etc.
        A unique identifier (UUID) will be generated upon successful creation.
        """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Customer",
                                            summary = "Example customer to create",
                                            value = "{" +
                                                    "\n    \"name\": \"Andres\"," +
                                                    "\n    \"lastName\": \"Sacco\"," +
                                                    "\n    \"dni\": \"12345678\"" +
                                                    "\n}"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Returns the saved customer's information.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CustomerDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request of the information to persist",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens to register and obtain the customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto);

    @Operation(
            summary = "Update an existing customer",
            description = """
    Updates an existing customer in the system using the provided details.
    The request must include the customer's personal information such as name, last name, and DNI.
    The customer is identified by a unique UUID, which must be included in the request.
    """,
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Id of the customer to update",
                            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Customer",
                                            summary = "Example customer to create",
                                            value = "{" +
                                                    "\n    \"name\": \"Andres\"," +
                                                    "\n    \"lastName\": \"Sacco\"," +
                                                    "\n    \"dni\": \"12345678\"" +
                                                    "\n}"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Returns the saved customer's information.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CustomerDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request of the information to persist",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens to register and obtain the customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    ResponseEntity<CustomerDto> updateCustomer(@Min(1) @PathVariable("id") UUID id, @RequestBody @Valid CustomerDto customerDto);

    ResponseEntity<Void> deleteCustomer(@Min(1) @PathVariable UUID id);





}
