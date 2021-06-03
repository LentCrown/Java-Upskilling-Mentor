package org.mentor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mentor.model.Product;

@Data
@AllArgsConstructor
public class Pack {
    private Product product;
    private Integer amount;
}
