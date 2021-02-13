package uz.zako.springboot.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.zako.springboot.model.Transaction;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {
    private final String URI = "http://localhost:8081/api/transaction";
    public String getMsg(){
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(URI, String.class);
        return msg;
    }

    public ResponseEntity<Transaction> save(Transaction transaction){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Transaction> transaction1 = restTemplate.postForEntity(URI+"/save", transaction, Transaction.class);
        return transaction1;
    }

    public ResponseEntity<List> getAllWithGet_for_Entity(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> list = restTemplate.getForEntity(URI+"/getAll", List.class);
        return list;
    }

    public List<Transaction> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI+"/getAll", HttpMethod.GET, entity, List.class).getBody();
    }

    public Transaction saveExch(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI + "/save", HttpMethod.POST, entity, Transaction.class).getBody();
    }

    public Transaction update(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI + "/update", HttpMethod.PUT, entity, Transaction.class).getBody();
    }
}
