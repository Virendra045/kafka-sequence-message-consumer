package com.example.kafkasequencemessageconsumer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trade {
    private String id;
    private String securityId;
    private String fundShortName;
    private String value;
}
