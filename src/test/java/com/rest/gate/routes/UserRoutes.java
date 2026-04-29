package com.rest.gate.routes;

public class UserRoutes {
        // All endpoints are centralized here — no hardcoding in tests
        public static final String GET_ALL_USERS  = "/users";
        public static final String GET_USER_BY_ID = "/users/{id}";
        public static final String CREATE_USER    = "/users/add";
        public static final String UPDATE_USER    = "/users/{id}";
        public static final String DELETE_USER    = "/users/{id}";
        public static final String SEARCH_USERS   = "/users/search";
    }
