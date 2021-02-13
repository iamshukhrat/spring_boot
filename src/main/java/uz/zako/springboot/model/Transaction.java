package uz.zako.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private Long id;
    private Double amount;
    private String reasons;
    private Long userId;
}
