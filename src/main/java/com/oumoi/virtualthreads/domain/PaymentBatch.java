package com.oumoi.virtualthreads.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentBatch {

    private String batchId;

    private String batchNo;

    private Double batchAmount;

    private String postedOn;
}
