package com.service.user;

import com.form.user.RegisterForm;
import com.result.Result;

import javax.servlet.http.HttpSession;

public interface RegisterService {
    Result direct(RegisterForm.directForm form);

}
