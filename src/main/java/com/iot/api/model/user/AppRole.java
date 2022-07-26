package com.iot.api.model.user;

import static com.iot.api.model.user.AppRoleConstants.*;

public enum AppRole {

    ROLE_SCHEDULE_READ(SCHEDULE_READ),
    ROLE_SCHEDULE_UPDATE(SCHEDULE_UPDATE),

    ROLE_ROLEGROUP_ADD(ROLEGROUP_ADD),
    ROLE_ROLEGROUP_UPDATE(ROLEGROUP_UPDATE),
    ROLE_ROLEGROUP_DELETE(ROLEGROUP_DELETE),
    ROLE_ROLEGROUP_READ(ROLEGROUP_READ),

    ROLE_APP_USER_ADD(APP_USER_ADD),
    ROLE_APP_USER_UPDATE(APP_USER_UPDATE),
    ROLE_APP_USER_DELETE(APP_USER_DELETE),
    ROLE_APP_USER_READ(APP_USER_READ),


    //MEMBER
    ROLE_MEMBER_ADD(MEMBER_ADD),
    ROLE_MEMBER_READ(MEMBER_READ),
    ROLE_MEMBER_UPDATE(MEMBER_UPDATE),
    ROLE_MEMBER_DELETE(MEMBER_DELETE),

    //AUTHOR
    ROLE_AUTHOR_ADD(AUTHOR_ADD),
    ROLE_AUTHOR_READ(AUTHOR_READ),
    ROLE_AUTHOR_UPDATE(AUTHOR_UPDATE),
    ROLE_AUTHOR_DELETE(AUTHOR_DELETE),
    //BOOK
    ROLE_BOOK_ADD(BOOK_ADD),
    ROLE_BOOK_READ(BOOK_READ),
    ROLE_BOOK_UPDATE(BOOK_UPDATE),
    ROLE_BOOK_DELETE(BOOK_DELETE);


    private String stringValue;

    AppRole(String productUserAdd) {

    }

    void Strings(final String s) {
        stringValue = s;
    }

    public String toString() {
        return stringValue;
    }
}
