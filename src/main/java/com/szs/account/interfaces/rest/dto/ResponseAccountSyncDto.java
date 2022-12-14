package com.szs.account.interfaces.rest.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseAccountSyncDto {
    private Long accountId;
    private Long lastTransactionId;
    private Long balance;
    private String uuid;

    @Builder
    public ResponseAccountSyncDto(Long accountId, Long lastTransactionId, Long balance, String uuid) {
        this.accountId = accountId;
        this.lastTransactionId = lastTransactionId;
        this.balance = balance;
        this.uuid = uuid;
    }

}
