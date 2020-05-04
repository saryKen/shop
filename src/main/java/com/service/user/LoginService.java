package com.service.user;

import com.form.user.LoginForm;
import com.result.Result;

import javax.servlet.http.HttpSession;

public interface LoginService {
    Result direct(LoginForm.directForm form);

}
