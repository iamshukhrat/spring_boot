package uz.zako.springboot.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.springboot.model.Transaction;
import uz.zako.springboot.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionResource {
    private  final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction/test")
    public ResponseEntity getMsg(){
        return  ResponseEntity.ok(transactionService.getMsg());
    }

    @GetMapping("/test")
    public ResponseEntity getTest(){
        return ResponseEntity.ok("Ok injiq!");
    }

    @PostMapping("/transactions")
    public ResponseEntity save(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping("/transaction/findAll")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping("/transaction/findAllWithEntity")
    public ResponseEntity getAllWithEntity(){
        return ResponseEntity.ok(transactionService.getAllWithGet_for_Entity());
    }

    @PostMapping("/transaction/save")
    public ResponseEntity saveWithExch(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.saveExch(transaction));
    }

    @PutMapping("/transaction/updateWithExch")
    public ResponseEntity update(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.update(transaction));
    }
}
