package com.rest.gate.base;

import com.rest.gate.routes.UserRoutes;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Api{

    public static Response createUser(Object payload) throws IOException {
        return RestResource.post(UserRoutes.CREATE_USER, payload);
    }

    public static Response getAllUsers() throws IOException {
        return RestResource.get(UserRoutes.GET_ALL_USERS);
    }

    public static Response getUser(int userId) throws IOException {
        return RestResource.get(UserRoutes.GET_USER_BY_ID, userId);
    }

    public static Response updateUser(int userId, Object payload) throws IOException {
        return RestResource.put(UserRoutes.UPDATE_USER, userId, payload);
    }

    public static Response deleteUser(int userId) throws IOException {
        return RestResource.delete(UserRoutes.DELETE_USER, userId);
    }


    public static Response uploadFile(File file) throws IOException {
        return RestResource.upload(UserRoutes.UPLOAD_FILE, file);
    }

    public static Response sendFormData(Map<String, String> formData) throws IOException {
        return RestResource.formUrlEncoded(UserRoutes.FORM_ENCODED,  formData);
    }
}
