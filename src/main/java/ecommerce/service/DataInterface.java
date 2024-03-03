package ecommerce.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DataInterface {

    ResponseEntity<String> addData(String sorting);
    ResponseEntity<List<String>> getAllData();
    ResponseEntity<String> deleteData(String sorting);
    ResponseEntity<String> updateData(String sorting);

//    ResponseEntity<String> addSort(String sorting);
//    ResponseEntity<List<String>> getAllSorting();
//    ResponseEntity<String> deleteSort(String sorting);
//    ResponseEntity<String> updateSort(String sorting);


    // Data for prices

//    ResponseEntity<String> addPrices(String prices);
//    ResponseEntity<List<String>> getAllPrices();
//    ResponseEntity<String> deletePrice(String prices);
}
