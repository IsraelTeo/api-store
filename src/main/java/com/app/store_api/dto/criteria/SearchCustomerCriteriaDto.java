package com.app.store_api.dto.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCustomerCriteriaDto {

    private UUID customerId;

    private String name;

    private String lastName;

    private String dni;

    private LocalDate creationDate;

    private String sortField; //campo a ordenar

    private String sortingDirection; //direccion de ordenamiento, ascendente o descendente

    private Integer pageActual = 0; //representa la página en la que se encuentra

    private Integer pageSize = 10; //representa la cantidad de registros se mostrarán por página

    // paginación ejemplo: tengo 12 registros y con pageSize pédimos 4 registros por página, tendríamos 3 páginas
    // luego podemos: ir a la página 2 con pageActual y pedir los primeros 2 registros de ahí con pageSize

}
