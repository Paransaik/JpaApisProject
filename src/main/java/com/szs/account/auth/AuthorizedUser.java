package com.szs.account.auth;

import lombok.ToString;

@ToString
public class AuthorizedUser {

    private long id;

    private long expire;

    public boolean isExpired() {
        return System.currentTimeMillis() >= expire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public AuthorizedUser(long id, long expire) {
        this.id = id;
        this.expire = expire;
    }

}
