package com.service.product;

import com.form.product.ScenicSpotForm;
import com.result.Result;

public interface ScenicSpotService {
    Result findById(ScenicSpotForm.findByIdForm form);

    Result list(ScenicSpotForm.listForm form);

    Result add(ScenicSpotForm.addForm form);

    Result update(ScenicSpotForm.updateForm form);

    Result delete(ScenicSpotForm.deleteForm form);
}
