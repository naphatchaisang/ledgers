package com.example.ledger.constants;

public interface Constants {

    public interface PathUrl{
        public static final String USER_BASE_URL = "/api/v1/users";
        public static final String GET_USER_ID = "/{userId}";
        public static final String GET_USER_STATUS = "/{userId}/status";

        public static final String CATEGORY_BASE_URL = "/api/v1/categories";
        public static final String GET_CATEGORY_ID = "/{catId}";
        public static final String GET_CATEGORY_STATUS = "/{catId}/status";
        public static final String GET_CATEGORY_TXTYPE = "/type/{txType}";

        public static final String TRANSACTION_BASE_URL = "/api/v1/transactions";
        public static final String GET_TRANSACTION_ID = "/{ledgerId}";
        public static final String GET_TRANSACTION_USER = "/user/{userId}";
        public static final String GET_TRANSACTION_FILTER = "/filter";
        public static final String GET_TRANSACTION_STATUS = "/{ledgerId}/status";
    }
    
}
